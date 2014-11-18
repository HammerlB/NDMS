package at.bmlvs.NDMS.domain.instances;

import java.util.ArrayList;

import javafx.scene.control.TabPane;

public class Instances extends TabPane
{
	private ArrayList<Instance> instances;

	public Instances()
	{
		setInstances(new ArrayList<Instance>());
	}

	public ArrayList<Instance> getInstances()
	{
		return instances;
	}

	public void setInstances(ArrayList<Instance> instances)
	{
		this.instances = instances;
	}

	public void addSingleInstance(Instance instance)
	{
		getInstances().add(instance);
		getTabs().add(getInstances().indexOf(instance),
				getInstances().get(getInstances().indexOf(instance)));
	}

	/*
	@SuppressWarnings("static-access")
	public void convertSingleOfflineInstanceToOnlineInstance(InstanceOffline instanceOffline, String ipv4address, String username, String password, String enablepassword, String snmpcommunitystring)
	{
		for(Instance inst: getInstances())
		{
			if(inst.equals(instanceOffline))
			{
				IPv4Address address = new IPv4Address(ipv4address);
				
				if (IPv4Address.isAlive(address.getIPv4Address()))
				{
					try
					{
						SSHConnector sshc = new SSHConnector(address.getIPv4Address(), username, password,
								enablepassword);

						sshc.connect();
						
						boolean alreadyfound = false;

						for (Instance instsecond : getInstances())
						{
							if (instsecond.getClass() == InstanceOnline.class)
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
							TFTPConnector tftpc = new TFTPConnector(address.getIPv4Address());

							tftpc.setSSHFingerprint(sshc.getSSHFingerprint());
							sshc.doPrepareSnapshot();
							
							DateFormat dateformat = new SimpleDateFormat(
									"_dd-MM-yyyy_HH-mm-ss_");
							Date date = new Date();
							tftpc.takeSnapshot("Initial",
									"Initial Snapshot from " + address.getIPv4Address() + " "
											+ dateformat.format(date));
							
							InstanceOnline newOnline = new InstanceOnline(address.getIPv4Address(),
									sshc.getSSHFingerprint(), address.getIPv4Address(), sshc,
									tftpc, new SNMPConnector("udp:" + address.getIPv4Address()
											+ "/161", snmpcommunitystring));
							

							newOnline.populateAll();

							newOnline.nameProperty().addListener(
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
									newOnline.checkInstance();
									newOnline.checkInterfaces();
								}
							}, 1000, 5000);
							
							getInstances().remove(inst);
							
							addSingleInstance(newOnline);
							
							ServiceFactory.getPresentationService().getMainWindowController().getTabcontrol().portview(getInstances()
									.indexOf(newOnline));

							newOnline.setOnClosed(new EventHandler<Event>()
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
						}
					}
					catch(Exception e)
					{
						
					}
				}
				
				break;
			}
		}
	}
	*/
}