package at.bmlvs.NDMS.presentation;

import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class SettingWindow extends javax.swing.JFrame
{
	JFrame settingFrame = new JFrame("Einstellungen");

	public void settingwindow()
	{
		settingFrame.setSize(600, 400);
		settingFrame.setLocationRelativeTo(null);
		settingFrame.setIconImage(new ImageIcon("icons/ndms.png").getImage());
		settingFrame.setVisible(true);

		Panel settingpanel = new Panel();
		settingpanel.setLayout(new GridLayout());
		
		JScrollPane settingpane = new JScrollPane( settingpanel );

		
		
		
		
		pack();	
	}

}
