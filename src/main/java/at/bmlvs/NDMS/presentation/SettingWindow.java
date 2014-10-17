package at.bmlvs.NDMS.presentation;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import at.bmlvs.NDMS.service.ServiceFactory;

public class SettingWindow extends javax.swing.JFrame
{
	private JFrame settingFrame;

	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	
	public SettingWindow()
	{
		
		ServiceFactory.getPresentationService().getMainWindow().mainFrame.setEnabled(false);
		
		settingFrame = new JFrame("Einstellungen");
		settingFrame.setSize(600, 250);
		settingFrame.setLocationRelativeTo(null);
		settingFrame.setIconImage(new ImageIcon("icons/ndms.png").getImage());
		
		JButton open = new JButton("Öffnen");
		JButton open1 = new JButton("Öffnen");
		JButton open2 = new JButton("Öffnen");
		JButton save = new JButton("Speichern");
		JButton bruch = new JButton("Abbrechen");
		
		field1 = new JTextField(ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_APP() + "\\" + ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_TEMPLATE_DIRECTORY(), 10);
		field2 = new JTextField(ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_APP() + "\\" + ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_CONFIG_DIRECTORY(), 10);
		field3 = new JTextField(ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_APP() + "\\" + ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_BACKUP_DIRECTORY(), 10);
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 30, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		settingFrame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel_5 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 0;
		settingFrame.getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridx = 4;
		gbc_lblNewLabel_6.gridy = 0;
		settingFrame.getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblNewLabel = new JLabel("Programmpfad: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		settingFrame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_APP());
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		settingFrame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_8 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridx = 4;
		gbc_lblNewLabel_6.gridy = 2;
		settingFrame.getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_6);
		
		JLabel lblNewLabel_2 = new JLabel("Templates: ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 3;
		settingFrame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 2;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 3;
		settingFrame.getContentPane().add(field1, gbc_textField);
		field1.setColumns(10);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 3;
		settingFrame.getContentPane().add(open, gbc_btnNewButton);
		open.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				fc.showOpenDialog(null);
				field1.setText(fc.getSelectedFile().toString());
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Konfigurations-Dateien: ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 4;
		settingFrame.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 4;
		settingFrame.getContentPane().add(field2, gbc_textField_1);
		field2.setColumns(10);
		
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 6;
		gbc_btnNewButton_2.gridy = 4;
		settingFrame.getContentPane().add(open1, gbc_btnNewButton_2);
		open1.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				fc.showOpenDialog(null);
				field2.setText(fc.getSelectedFile().toString());
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("Backup-Dateien: ");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 5;
		settingFrame.getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 5;
		settingFrame.getContentPane().add(field3, gbc_textField_2);
		field3.setColumns(10);
		
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 6;
		gbc_btnNewButton_1.gridy = 5;
		settingFrame.getContentPane().add(open2, gbc_btnNewButton_1);
		open2.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				fc.showOpenDialog(null);
				field3.setText(fc.getSelectedFile().toString());
			}
		});
		
		JLabel lblNewLabel_7 = new JLabel("                                          ");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.gridx = 5;
		gbc_lblNewLabel_7.gridy = 6;
		settingFrame.getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
	
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_3.gridx = 4;
		gbc_btnNewButton_3.gridy = 7;
		settingFrame.getContentPane().add(save, gbc_btnNewButton_3);
		save.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				ServiceFactory.getAppConfig().setNDMS_DEFAULT_PATH_TEMPLATE_DIRECTORY(field1.getText());
				ServiceFactory.getAppConfig().setNDMS_DEFAULT_PATH_CONFIG_DIRECTORY(field2.getText());
				ServiceFactory.getAppConfig().setNDMS_DEFAULT_PATH_BACKUP_DIRECTORY(field3.getText());
				ServiceFactory.getPersistenceService().saveAppConfig();
				ServiceFactory.getPresentationService().getMainWindow().mainFrame.setEnabled(true);
				settingFrame.dispose();
			}
		});
		
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_4.gridx = 5;
		gbc_btnNewButton_4.gridy = 7;
		settingFrame.getContentPane().add(bruch, gbc_btnNewButton_4);
		bruch.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				ServiceFactory.getPresentationService().getMainWindow().mainFrame.setEnabled(true);
				settingFrame.dispose();
			}
		});
		
		settingFrame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	        	ServiceFactory.getPresentationService().getMainWindow().mainFrame.setEnabled(true);
	        }

	    });
		
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
