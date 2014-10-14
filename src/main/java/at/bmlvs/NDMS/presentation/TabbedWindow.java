package at.bmlvs.NDMS.presentation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import at.bmlvs.NDMS.domain.helper.UUIDGenerator;
import at.bmlvs.NDMS.service.ServiceFactory;

public class TabbedWindow
{
	JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP,
			JTabbedPane.SCROLL_TAB_LAYOUT);
	int index;
	
	public TabbedWindow()
	{

	}

	@SuppressWarnings("static-access")
	public void addTabbedMenu(String tabname)
	{
		final String id = UUIDGenerator.generateUUID();
		tabpane.addTab(id, new JPanel());

		ServiceFactory.getPresentationService().getMainWindow().mainFrame.add(tabpane);

		// closebutton
		index = tabpane.indexOfTab(id);
		JPanel pnlTab = new JPanel(new GridBagLayout());
		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(tabname + "  ");
		JButton btnClose = new JButton();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;

		btnClose.setFocusable(false);
		btnClose.setPreferredSize(new Dimension(15, 15));
		btnClose.setIcon(new ImageIcon("icons/x.png"));

		pnlTab.add(lblTitle, gbc);

		gbc.gridx++;
		gbc.weightx = 0;
		pnlTab.add(btnClose, gbc);

		btnClose.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				int index = tabpane.indexOfTab(id);
				
				if (index >= 0)
				{
					tabpane.removeTabAt(index);
				}
			}
		});

		tabpane.setTabComponentAt(index, pnlTab);

	}
}
