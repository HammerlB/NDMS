package at.bmlvs.NDMS.presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedWindow
{
	JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
	int index;
	
	public TabbedWindow()
	{

	}

	public void addTabbedMenu(String tabname)
	{
		
		tabpane.addTab(tabname,new JPanel());

		PresentationFactory.getMainWindow().mainFrame.add(tabpane);
		
		
		//closebutton
		index = tabpane.indexOfTab(tabname);
		JPanel pnlTab = new JPanel(new GridBagLayout());
		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(tabname + "  ");
		JButton btnClose = new JButton("x");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;

		pnlTab.add(lblTitle, gbc);

		gbc.gridx++;
		gbc.weightx = 0;
		pnlTab.add(btnClose, gbc);

		tabpane.setTabComponentAt(index, pnlTab);

		//btnClose.addActionListener(myCloseActionHandler);
	}
}
