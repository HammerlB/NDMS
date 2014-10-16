package at.bmlvs.NDMS.presentation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;

public class SettingWindow extends javax.swing.JFrame
{
	private JFrame settingFrame;

	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	//TEST
	public SettingWindow()
	{
		settingFrame = new JFrame("Einstellungen");
		settingFrame.setSize(600, 400);
		settingFrame.setLocationRelativeTo(null);
		settingFrame.setIconImage(new ImageIcon("icons/ndms.png").getImage());
		
		JButton open = new JButton("Öffnen");
		JButton open1 = new JButton("Öffnen");
		JButton open2 = new JButton("Öffnen");
		
		
		JPanel settingpanel = new JPanel();
		settingpanel.setLayout( new GridLayout(0, 3));
		
		JPanel gridpanel = new JPanel();
		gridpanel.setLayout(new FlowLayout());
		
		settingpanel.add(new JLabel(" "));
		settingpanel.add(new JLabel(" "));
		settingpanel.add(new JLabel(" "));
		
		settingpanel.add(new JLabel("Programmpfad: "));
		settingpanel.add(new JLabel("C://hugo/sitzt/am/Klo"));
		settingpanel.add(new JLabel(" "));
		
		settingpanel.add(new JLabel(" "));
		settingpanel.add(new JLabel(" "));
		settingpanel.add(new JLabel(" "));
		
		settingpanel.add(new JLabel("Templates:"));
		//settingpanel.add(setField1(new JTextField("do is da pfad",10)));
		settingpanel.add(open);
		open.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				fc.showOpenDialog(null);
				fc.getCurrentDirectory();
			}
		});
		
		settingpanel.add(new JLabel(" "));
		settingpanel.add(new JLabel(" "));
		settingpanel.add(new JLabel(" "));
		
		settingpanel.add(new JLabel("Konfigurations-Dateien         "));
		settingpanel.add(new JTextField("do is des drin", 10));
		settingpanel.add(open1);
		open1.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				fc.showOpenDialog(null);
			}
		});
		
		settingpanel.add(new JLabel(" "));
		settingpanel.add(new JLabel(" "));
		settingpanel.add(new JLabel(" "));
		
		settingpanel.add(new JLabel("Backup-Dateien"));
		settingpanel.add(new JTextField("do is des drin", 10));
		settingpanel.add(open2);
		open2.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				fc.showOpenDialog(null);
			}
		});
		
		gridpanel.add(settingpanel);
		
		settingFrame.add(gridpanel);
		settingFrame.setVisible(true);
	}


	public JTextField getField1()
	{
		return field1;
	}


	public void setField1(JTextField field1)
	{
		this.field1 = field1;
	}


	public JTextField getField2()
	{
		return field2;
	}


	public void setField2(JTextField field2)
	{
		this.field2 = field2;
	}


	public JTextField getField3()
	{
		return field3;
	}


	public void setField3(JTextField field3)
	{
		this.field3 = field3;
	}

}
