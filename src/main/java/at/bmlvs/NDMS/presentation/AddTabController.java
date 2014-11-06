package at.bmlvs.NDMS.presentation;

import java.io.IOException;
import java.util.Collections;

import at.bmlvs.NDMS.domain.Instance;
import at.bmlvs.NDMS.domain.Interface;
import at.bmlvs.NDMS.domain.connectors.SNMPConnector;
import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.presentation.elements.RestrictiveTextField;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class AddTabController
{
	private String tabname = "";

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

	// private SSHConnector shc;

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
				tabname = ipaddress1.getText() + "." + ipaddress2.getText()
						+ "." + ipaddress3.getText() + "."
						+ ipaddress4.getText();
				 
				try
				{
					SSHConnector sshc = new SSHConnector(tabname, "Herkel",
							"gwdH_2014");
					sshc.connect();
					SNMPConnector snmpc = new SNMPConnector("udp:" + tabname + "/161", "gwdSNMP_2014");
					Instance inst = new Instance(tabname,
							sshc.getSSHFingerprint(), tabname, sshc, snmpc);
					inst.populateAll();
					//inst.checkInterfaces();
					ServiceFactory.getDomainService().getInstances().addSingleOnlineInstance(inst);
					addTab(inst);
					portview(ServiceFactory.getDomainService().getInstances().getInstances().indexOf(inst));

				}
				catch (Exception e)
				{
					errorlabel.setText("Verbindung war nicht erfolgreich! \n("
							+ e.getMessage() + ")");
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
				tabname = iprange1.getText() + "." + iprange2.getText() + "."
						+ iprange3.getText() + "." + iprange4.getText() + " - "
						+ iprange5.getText() + "." + iprange6.getText() + "."
						+ iprange7.getText() + "." + iprange8.getText();
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
				addTab(new Tab(tabname));
				
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

		offline1.setDisable(false);

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
		dotListener(ipaddress1, ipaddress2);
		dotListener(ipaddress2, ipaddress3);
		dotListener(ipaddress3, ipaddress4);

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

		ipaddress1.setDisable(false);
		ipaddress2.setDisable(false);
		ipaddress3.setDisable(false);
		ipaddress4.setDisable(false);

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

		ipaddress1.setDisable(true);
		ipaddress2.setDisable(true);
		ipaddress3.setDisable(true);
		ipaddress4.setDisable(true);

		ipaddress1.setText("");
		ipaddress2.setText("");
		ipaddress3.setText("");
		ipaddress4.setText("");

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

	private void portview(int id)
	{
		TilePane portview1 = new TilePane();

		int counter = 0;
		
		//portview1.setPadding(new Insets(5, 0, 5, 0));

		portview1.setVgap(0);
		portview1.setHgap(0);

		portview1.setMaxWidth(700);

		for (Interface interf : ServiceFactory.getDomainService().getInstances().getInstances().get(id).getInterfaces())
		{
			if((counter % 6) == 0)
			{
				portview1.getChildren().add(new Label(""));
				
			} 
			
			if(((counter % 24) == 0) && (counter != 0)){
				
				portview1.getChildren().add(new Label(""));
				portview1.getChildren().add(new Label(""));
				portview1.getChildren().add(new Label(""));

				portview1.getChildren().add(new Label(""));
				portview1.getChildren().add(new Label(""));
				portview1.getChildren().add(new Label(""));	
				
				portview1.getChildren().add(new Label(""));
				portview1.getChildren().add(new Label(""));
				portview1.getChildren().add(new Label(""));

				portview1.getChildren().add(new Label(""));
				portview1.getChildren().add(new Label(""));
				portview1.getChildren().add(new Label(""));
				
				portview1.getChildren().add(new Label(""));
				portview1.getChildren().add(new Label(""));
			}
			
			if(counter < 9)
			{
				portview1.getChildren().add(new Button(" " + interf.getPortnameshort() + " "));
			}else{
				portview1.getChildren().add(new Button(interf.getPortnameshort()));
			}
			
			counter++;
		}
		
		//id stuff to int: Integer.parseInt(interf.getPortid()), 
		
		// Add something in Tab
		VBox tabbox = new VBox();
		tabbox.getChildren().addAll(portview1);
		PresentationService.getMainWindowController().getTabPane().getTabs().get(id).setContent(tabbox);
		PresentationService.getMainWindowController().getTabPane().getTabs().add(PresentationService.getMainWindowController().getTabPane().getTabs().get(id));

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

}
