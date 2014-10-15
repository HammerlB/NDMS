package at.bmlvs.NDMS.presentation;

import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SettingWindow extends javax.swing.JFrame
{
	JFrame settingFrame = new JFrame("Einstellungen");

	public void settingwindow()
	{
		settingFrame.setSize(600, 400);
		settingFrame.setLocationRelativeTo(null);
		settingFrame.setIconImage(new ImageIcon("icons/ndms.png").getImage());

		JPanel settingpanel = new JPanel();
		settingpanel.setLayout(new GridLayout(0, 2));

		JScrollPane settingpane = new JScrollPane(settingpanel);

		settingpanel.add(new JLabel("               Programmpfad: "));
		settingpanel.add(new JLabel("C://hugo/sitzt/am/Klo"));
		settingpanel.add(new JLabel("               Templates:"));
		settingpanel.add(new JButton("Öffnen"));
		settingpanel.add(new JLabel("               Konfigurations-Dateien"));
		settingpanel.add(new JLabel("D9irectory Chooser"));
		settingpanel.add(new JLabel("               Backup-Dateien"));
		settingpanel.add(new JLabel("Directory Chooser"));
		
		//
		this.getContentPane().add(settingpane);
		pack();
		
		settingFrame.add(settingpanel);
		settingFrame.setVisible(true);
	}

}
