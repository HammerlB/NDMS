package at.bmlvs.NDMS.presentation;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedWindow
{
	JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
	
	public TabbedWindow()
	{

	}

	public void addTabbedMenu(String tabname)
	{
		tabpane.addTab(tabname,new JPanel());
		PresentationFactory.getMainWindow().mainFrame.add(tabpane);
	}
}
