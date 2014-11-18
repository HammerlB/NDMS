package at.bmlvs.NDMS.presentation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import at.bmlvs.NDMS.domain.connectors.SNMPConnector;
import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.connectors.TFTPConnector;
import at.bmlvs.NDMS.domain.helper.IPv4Address;
import at.bmlvs.NDMS.domain.helper.IPv4Range;
import at.bmlvs.NDMS.domain.instances.Instance;
import at.bmlvs.NDMS.domain.instances.InstanceOffline;
import at.bmlvs.NDMS.domain.instances.InstanceOnline;
import at.bmlvs.NDMS.domain.instances.Interface;
import at.bmlvs.NDMS.presentation.elements.RestrictiveTextField;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AddTabController
{
	private String tabname = "";
	private String activetab = "";

	@FXML
	private ToggleGroup radiotoggle;
	@FXML
	private RadioButton rbaddress;
	@FXML
	private RadioButton rbrange;
	@FXML
	private RadioButton rboff;
	@FXML
	private RestrictiveTextField ipaddress1;
	@FXML
	private RestrictiveTextField ipaddress2;
	@FXML
	private RestrictiveTextField ipaddress3;
	@FXML
	private RestrictiveTextField ipaddress4;
	@FXML
	private RestrictiveTextField iprange1;
	@FXML
	private RestrictiveTextField iprange2;
	@FXML
	private RestrictiveTextField iprange3;
	@FXML
	private RestrictiveTextField iprange4;
	@FXML
	private RestrictiveTextField iprange5;
	@FXML
	private RestrictiveTextField iprange6;
	@FXML
	private RestrictiveTextField iprange7;
	@FXML
	private RestrictiveTextField iprange8;
	@FXML
	private TextField offline1;
	@FXML
	private Label errorlabel;
	@FXML
	private Label vonlabel;
	@FXML
	private Label bislabel;
	@FXML
	private Label namelabel;
	@FXML
	private Label portanzlabel;
	@FXML
	private TextField portanz;
	
	private TFTPConnector tftpc;
	private SSHConnector sshc;
	private GridPane portview1;

	@FXML
	public void initialize()
	{

		ActionEvent event = new ActionEvent();

		dotListener(ipaddress1, ipaddress2);
		dotListener(ipaddress2, ipaddress3);
		dotListener(ipaddress3, ipaddress4);

		ipaddress4.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent ke)
			{
				if (ke.getCode().equals(KeyCode.ENTER))
				{
					try
					{
						startconnection(event);
					}
					catch (IOException e)
					{
					}
				}
			}
		});
	}

	@SuppressWarnings("static-access")
	@FXML
	private void startconnection(ActionEvent event) throws IOException
	{

		if (rbaddress.isSelected())
		{
			if ((!ipaddress1.getText().equals(""))
					&& (!ipaddress2.getText().equals(""))
					&& (!ipaddress3.getText().equals(""))
					&& (!ipaddress4.getText().equals("")))
			{
				IPv4Address address = new IPv4Address(ipaddress1.getText()
						+ "." + ipaddress2.getText() + "."
						+ ipaddress3.getText() + "." + ipaddress4.getText());

				if (address.isAlive(address.getIPv4Address()))
				{
					try
					{

						sshc = new SSHConnector(address.getIPv4Address(), "Herkel", "gwdH_2014",
								"gwd_2014");

						sshc.connect();

						boolean alreadyfound = false;

						for (Instance inst : ServiceFactory.getDomainService()
								.getInstances().getInstances())
						{
							if (inst.getClass() == InstanceOnline.class)
							{
								if (sshc.getSSHFingerprint().equals(
										((InstanceOnline) inst).getFingerprint()))
								{
									alreadyfound = true;
									break;
								}
							}
						}

						if (alreadyfound == false)
						{
							sshc.start();

							// TFTP Initial Snapshot
							tftpc = new TFTPConnector(address.getIPv4Address());

							tftpc.setSSHFingerprint(sshc.getSSHFingerprint());
							sshc.doPrepareSnapshot();

							//tftpc.scanSnapshots();

							DateFormat dateformat = new SimpleDateFormat(
									"_dd-MM-yyyy_HH-mm-ss_");
							Date date = new Date();
							tftpc.takeSnapshot("Initial",
									"Initial Snapshot from " + address.getIPv4Address() + " "
											+ dateformat.format(date), sshc);

							InstanceOnline inst = new InstanceOnline(address.getIPv4Address(),
									sshc.getSSHFingerprint(), address.getIPv4Address(), sshc,
									tftpc, new SNMPConnector("udp:" + address.getIPv4Address()
											+ "/161", "gwdSNMP_2014"));

							inst.populateAll();

							inst.nameProperty().addListener(
									new ChangeListener<Object>()
									{
										@Override
										public void changed(
												ObservableValue<? extends Object> arg0,
												Object arg1, Object arg2)
										{
											Platform.runLater(new Runnable()
											{
												public void run()
												{
													inst.setText(inst.getName());
												}
											});
										}
									});

							Timer timer = new Timer();

							timer.scheduleAtFixedRate(new TimerTask()
							{
								public void run()
								{
									inst.checkInstance();
									inst.checkInterfaces();
								}
							}, 1000, 5000);

							// Platform.runLater(inst);

							// inst.checkInterfaces();
							ServiceFactory.getDomainService().getInstances()
									.addSingleInstance(inst);
							addTab(inst);
							portview(ServiceFactory.getDomainService()
									.getInstances().getInstances()
									.indexOf(inst));

							inst.setOnClosed(new EventHandler<Event>()
							{
								public void handle(Event t)
								{
									System.out.println("tab got closed");
									ServiceFactory.getDomainService()
											.getInstances().getInstances()
											.remove(inst);
									sshc.doDisconnect();

								}
							});
						}
						else
						{
							sshc.getSSHConnection().disconnect();
							errorlabel
									.setText("Es besteht bereits eine Verbindung\nzu dieser Netzwerkkomponente!");
						}
					}
					catch (Exception e)
					{
						errorlabel
								.setText("Verbindung war nicht erfolgreich! \n("
										+ e.getMessage() + ")");
						 e.printStackTrace();
					}
				}
			}
			else
			{
				errorlabel.setText("Geben Sie alle Felder der IP-Addresse an!");
			}

		}
		else if (rbrange.isSelected())
		{
			if ((!iprange1.getText().equals(""))
					&& (!iprange2.getText().equals(""))
					&& (!iprange3.getText().equals(""))
					&& (!iprange4.getText().equals(""))
					&& (!iprange5.getText().equals(""))
					&& (!iprange6.getText().equals(""))
					&& (!iprange7.getText().equals(""))
					&& (!iprange8.getText().equals("")))
			{
				IPv4Range range = new IPv4Range(
						new IPv4Address(iprange1.getText() + "."
								+ iprange2.getText() + "." + iprange3.getText()
								+ "." + iprange4.getText()), new IPv4Address(
								iprange5.getText() + "." + iprange6.getText()
										+ "." + iprange7.getText() + "."
										+ iprange8.getText()));

				for (String tabname : range.getAllAddressesAsStringArray(true))
				{
					try
					{
						sshc = new SSHConnector(tabname, "Herkel", "gwdH_2014",
								"gwd_2014");

						sshc.connect();

						boolean alreadyfound = false;

						for (Instance inst : ServiceFactory.getDomainService()
								.getInstances().getInstances())
						{
							if (inst.getClass() == InstanceOnline.class)
							{
								if (sshc.getSSHFingerprint().equals(
										((InstanceOnline) inst).getFingerprint()))
								{
									alreadyfound = true;
									break;
								}
							}
						}

						if (alreadyfound == false)
						{
							sshc.start();

							// TFTP Initial Snapshot
							tftpc = new TFTPConnector(tabname);

							tftpc.setSSHFingerprint(sshc.getSSHFingerprint());
							sshc.doPrepareSnapshot();

//							tftpc.scanSnapshots();

							DateFormat dateformat = new SimpleDateFormat(
									"_dd-MM-yyyy_HH-mm-ss_");
							Date date = new Date();
							tftpc.takeSnapshot("Initial",
									"Initial Snapshot from " + tabname + " "
											+ dateformat.format(date), sshc);

							InstanceOnline inst = new InstanceOnline(tabname,
									sshc.getSSHFingerprint(), tabname, sshc,
									tftpc, new SNMPConnector("udp:" + tabname
											+ "/161", "gwdSNMP_2014"));

							inst.populateAll();

							inst.nameProperty().addListener(
									new ChangeListener<Object>()
									{
										@Override
										public void changed(
												ObservableValue<? extends Object> arg0,
												Object arg1, Object arg2)
										{
											Platform.runLater(new Runnable()
											{
												public void run()
												{
													inst.setText(inst.getName());
												}
											});
										}
									});

							Timer timer = new Timer();

							timer.scheduleAtFixedRate(new TimerTask()
							{
								public void run()
								{
									inst.checkInstance();
									inst.checkInterfaces();
								}
							}, 1000, 5000);

							// Platform.runLater(inst);

							// inst.checkInterfaces();
							ServiceFactory.getDomainService().getInstances()
									.addSingleInstance(inst);
							addTab(inst);
							portview(ServiceFactory.getDomainService()
									.getInstances().getInstances()
									.indexOf(inst));

							inst.setOnClosed(new EventHandler<Event>()
							{
								public void handle(Event t)
								{
									System.out.println("tab got closed");
									ServiceFactory.getDomainService()
											.getInstances().getInstances()
											.remove(inst);
									sshc.doDisconnect();

								}
							});
						}
						else
						{
							sshc.getSSHConnection().disconnect();
							errorlabel
									.setText("Es besteht bereits eine Verbindung\nzu dieser Netzwerkkomponente!");
						}
					}
					catch (Exception e)
					{
						errorlabel
								.setText("Verbindung war nicht erfolgreich! \n("
										+ e.getMessage() + ")");
						// e.printStackTrace();
					}
				}
			}
			else
			{
				errorlabel.setText("Geben Sie alle Felder der IP-Range an!");
			}
		}
		else if (rboff.isSelected())
		{
			if (!offline1.getText().equals(""))
			{
				tabname = offline1.getText();
				
				int portcount = 0;
				
				InstanceOffline inst = new InstanceOffline(tabname, portcount);
				
				addTab(new Tab(tabname));
				
//				InstanceOnline inst = new InstanceOnline(tabname,
//						sshc.getSSHFingerprint(), tabname, sshc, tftpc,
//						new SNMPConnector("udp:" + tabname + "/161",
//								"gwdSNMP_2014"));
//
//				inst.populateAll();
//
//				ServiceFactory.getDomainService().getInstances()
//						.addSingleOnlineInstance(inst);
//				addTab(inst);

			}
			else
			{
				errorlabel.setText("Geben Sie einen Namen an!");
			}

		}
	}

	@FXML
	private void offtoggle(final ActionEvent event) throws IOException
	{
		iprange1.setDisable(true);
		iprange2.setDisable(true);
		iprange3.setDisable(true);
		iprange4.setDisable(true);
		iprange5.setDisable(true);
		iprange6.setDisable(true);
		iprange7.setDisable(true);
		iprange8.setDisable(true);

		iprange1.setText("");
		iprange2.setText("");
		iprange3.setText("");
		iprange4.setText("");
		iprange5.setText("");
		iprange6.setText("");
		iprange7.setText("");
		iprange8.setText("");

		ipaddress1.setDisable(true);
		ipaddress2.setDisable(true);
		ipaddress3.setDisable(true);
		ipaddress4.setDisable(true);

		ipaddress1.setText("");
		ipaddress2.setText("");
		ipaddress3.setText("");
		ipaddress4.setText("");
		
		vonlabel.setDisable(true);
		bislabel.setDisable(true);
		
		namelabel.setDisable(false);
		portanzlabel.setDisable(false);

		offline1.setDisable(false);
		portanz.setDisable(false);
		
		offline1.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent ke)
			{
				if (ke.getCode().equals(KeyCode.ENTER))
				{
					try
					{
						startconnection(event);
					}
					catch (IOException e)
					{
					}
				}
			}
		});

	}

	@FXML
	private void iptoggle(final ActionEvent event) throws IOException
	{

		iprange1.setDisable(true);
		iprange2.setDisable(true);
		iprange3.setDisable(true);
		iprange4.setDisable(true);
		iprange5.setDisable(true);
		iprange6.setDisable(true);
		iprange7.setDisable(true);
		iprange8.setDisable(true);

		iprange1.setText("");
		iprange2.setText("");
		iprange3.setText("");
		iprange4.setText("");
		iprange5.setText("");
		iprange6.setText("");
		iprange7.setText("");
		iprange8.setText("");

		offline1.setDisable(true);
		offline1.setText("");
		portanz.setDisable(true);
		portanz.setText("");
		
		vonlabel.setDisable(true);
		bislabel.setDisable(true);
		
		namelabel.setDisable(true);
		portanzlabel.setDisable(true);
		
		ipaddress1.setDisable(false);
		ipaddress2.setDisable(false);
		ipaddress3.setDisable(false);
		ipaddress4.setDisable(false);

	}

	@FXML
	private void rangetoggle(final ActionEvent event) throws IOException
	{
		dotListener(iprange1, iprange2);
		dotListener(iprange2, iprange3);
		dotListener(iprange3, iprange4);

		dotListener(iprange5, iprange6);
		dotListener(iprange6, iprange7);
		dotListener(iprange7, iprange8);

		iprange1.setDisable(false);
		iprange2.setDisable(false);
		iprange3.setDisable(false);
		iprange4.setDisable(false);
		iprange5.setDisable(false);
		iprange6.setDisable(false);
		iprange7.setDisable(false);
		iprange8.setDisable(false);

		offline1.setDisable(true);
		offline1.setText("");
		portanz.setDisable(true);
		portanz.setText("");

		ipaddress1.setDisable(true);
		ipaddress2.setDisable(true);
		ipaddress3.setDisable(true);
		ipaddress4.setDisable(true);

		ipaddress1.setText("");
		ipaddress2.setText("");
		ipaddress3.setText("");
		ipaddress4.setText("");
		
		vonlabel.setDisable(false);
		bislabel.setDisable(false);
		
		namelabel.setDisable(true);
		portanzlabel.setDisable(true);

		iprange8.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent ke)
			{
				if (ke.getCode().equals(KeyCode.ENTER))
				{
					try
					{
						startconnection(event);
					}
					catch (IOException e)
					{
					}
				}
			}
		});
	}

	private void addTab(Tab tab)
	{
		PresentationService.getMainWindowController().getTabPane().getTabs()
				.add(tab);
		PresentationService.getMainWindowController().getStage().close();
	}

	@SuppressWarnings("static-access")
	public void portview(int id)
	{
		portview1 = new GridPane();

		int counterrow = 1;
		int countercolumn = 1;

		portview1.setPadding(new Insets(5, 0, 5, 0));

		for (Interface interf : ServiceFactory.getDomainService()
				.getInstances().getInstances().get(id).getInterfaces())
		{
			interf.typeProperty().addListener(new ChangeListener<Object>()
			{
				@Override
				public void changed(ObservableValue<? extends Object> arg0,
						Object arg1, Object arg2)
				{
					Platform.runLater(new Runnable()
					{
						public void run()
						{
							interf.setTextForTooltip();
						}
					});
				}
			});

			interf.vlanProperty().addListener(new ChangeListener<Object>()
			{
				@Override
				public void changed(ObservableValue<? extends Object> arg0,
						Object arg1, Object arg2)
				{
					Platform.runLater(new Runnable()
					{
						public void run()
						{
							interf.setTextForTooltip();
						}
					});
				}
			});

			interf.portstatusProperty().addListener(
					new ChangeListener<Object>()
					{
						@Override
						public void changed(
								ObservableValue<? extends Object> arg0,
								Object arg1, Object arg2)
						{
							Platform.runLater(new Runnable()
							{
								public void run()
								{
									interf.setTextForTooltip();

								}
							});
						}
					});

			if (interf.getPortstatus() == "1")
			{
				interf.setStyle("-fx-base: #b6e7c9;");
			}

			if ((countercolumn == 7))
			{
				portview1.add(new Label("    "), countercolumn, counterrow);
				counterrow++;
				portview1.add(new Label("    "), countercolumn, counterrow);
				countercolumn++;
				counterrow--;
				portview1.add(interf, countercolumn, counterrow);
				counterrow++;

			}
			else if ((countercolumn == 14))
			{
				countercolumn = 1;
				counterrow = counterrow + 2;
				for (int i = 0; i < 14; i++)
				{
					portview1.add(new Label("    "), countercolumn, counterrow);
					countercolumn++;
				}
				countercolumn = 1;
				counterrow++;
				portview1.add(interf, countercolumn, counterrow);
				counterrow++;
			}
			else
			{

				portview1.add(interf, countercolumn, counterrow);

				if ((counterrow == 2) || (counterrow == 5))
				{
					counterrow--;
					countercolumn++;

				}
				else
				{
					counterrow++;
				}
			}
		}

		// Add something in Tab

		VBox tabbox = new VBox();
		FlowPane flow = new FlowPane();

		flow.getChildren().addAll(portview1);
		flow.setAlignment(Pos.CENTER);

		tabbox.getChildren().add(flow);

		PresentationService.getMainWindowController().getTabPane().getTabs()
				.get(id).setContent(tabbox);

	}

	private void dotListener(TextField tf1, final TextField tf2)
	{
		tf1.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent ke)
			{
				if (ke.getCode().equals(KeyCode.PERIOD))
				{
					tf2.requestFocus();
				}
			}
		});
	}

	public String getActivetab()
	{
		return activetab;
	}

	public void setActivetab(String activetab)
	{
		this.activetab = activetab;
	}

	public TFTPConnector getTftpc()
	{
		return tftpc;
	}

	public void setTftpc(TFTPConnector tftpc)
	{
		this.tftpc = tftpc;
	}

	public SSHConnector getSshc()
	{
		return sshc;
	}

	public void setSshc(SSHConnector sshc)
	{
		this.sshc = sshc;
	}

}
