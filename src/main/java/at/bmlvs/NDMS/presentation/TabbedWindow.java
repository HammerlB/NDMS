package at.bmlvs.NDMS.presentation;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedWindow
{
	public TabbedWindow()
	{

	}

	public void addTabbedMenu()
	{
		JPanel panelSwitch = new JPanel();
		JPanel panelRange = new JPanel();

		// Erzeugung eines JTabbedPane-Objektes
		JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.SCROLL_TAB_LAYOUT);

		// Hier werden die JPanels als Registerkarten hinzugefügt
		tabpane.addTab("192.168.10.253", panelSwitch);
		tabpane.addTab("10.0.0.1 - 10.0.0.5", panelRange);

		// JTabbedPane wird unserem Dialog hinzugefügt
		PresentationFactory.getMainWindow().add(tabpane);
	}

}
