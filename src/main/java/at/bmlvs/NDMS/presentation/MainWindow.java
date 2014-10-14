package at.bmlvs.NDMS.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JToolTip;
import javax.swing.filechooser.FileFilter;

import at.bmlvs.NDMS.domain.templates.Templates;
import at.bmlvs.NDMS.linker.TemplatesToPathLinker;
import at.bmlvs.NDMS.service.ServiceFactory;

public class MainWindow
{
	
	JFrame mainFrame = new JFrame("NDSM");
	
	
	public void addTopMenu()
	{
		
		mainFrame.setSize(1024, 768);
		mainFrame.setLocationRelativeTo(null);
		
        JMenuBar bar = new JMenuBar();
        
        JMenu datei = new JMenu("Datei");
        JMenu konfig = new JMenu("Konfiguration");
        JMenu werkzeug = new JMenu("Werkzeug");
        JMenu hilfe = new JMenu("Hilfe");
        
        bar.add(datei);
        bar.add(konfig);
        bar.add(werkzeug);
        bar.add(hilfe);
        
        //Dateimenü
        JMenuItem neuverb = new JMenuItem("Neue Verbindung", new ImageIcon("icons/smallnew.png"));
        neuverb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane op = new JOptionPane();
                String input = op.showInputDialog("Verbinden zu..");
                
                PresentationFactory.getTabbedWindow().addTabbedMenu(input);
                
            }
        });
            
        JMenuItem importe = new JMenuItem("Importieren", new ImageIcon("icons/smallimport.png"));
        importe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	load();
                
            }
        });
        JMenuItem export = new JMenuItem("Exportieren", new ImageIcon("icons/smallexport.png"));
        JMenuItem einst = new JMenuItem("Einstellungen", new ImageIcon("icons/smallsettings.png"));
        JMenuItem exit = new JMenuItem("Beenden", new ImageIcon("icons/smallclose.png"));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	mainFrame.dispose();
                
            }
        });
        
        datei.add(neuverb);
        datei.addSeparator();
        datei.add(importe);
        datei.add(export);
        datei.addSeparator();
        datei.add(einst);
        datei.addSeparator();
        datei.add(exit);
        
        //Konfigmenü
        JMenuItem portkonf = new JMenuItem("Port-Konfiguration", new ImageIcon("icons/smallportkonf.png"));
        JMenuItem vorlagen = new JMenuItem("Vorlagen", new ImageIcon("icons/smallvorlagen.png"));
        
        konfig.add(portkonf);
        konfig.addSeparator();
        konfig.add(vorlagen);
        
        //Werkzeuge
        JMenuItem tostandart = new JMenuItem("Zur Standartkonfiguration zurücksetzten", new ImageIcon("icons/smallresetstandart.png"));
        JMenuItem tomanage = new JMenuItem("Zur Managementkonfiguration zurücksetzten", new ImageIcon("icons/smallresetmanag.png"));
        JMenuItem diagnose = new JMenuItem("Diagnose", new ImageIcon("icons/smalldiag.png"));
        
        werkzeug.add(tostandart);
        werkzeug.add(tomanage);
        werkzeug.addSeparator();
        werkzeug.add(diagnose);
        
        //Hilfe
        JMenuItem handbook = new JMenuItem("Benutzerhandbuch", new ImageIcon("icons/smallbook.png"));
        JMenuItem about = new JMenuItem("Über NDSM", new ImageIcon("icons/smallabout.png"));
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	JOptionPane.showMessageDialog(mainFrame,
            		    "NDMS - Network Device Management Solution\n\nBy Herkel Dominik, Rieppel Alexander und Bernhard Hammerl!\n\nDen glorreichen GWD's der Datenkommunikation!\n\n\nButtons by creative commons\nhttp://creativecommons.org/licenses/by/3.0/\n\nSollten Sie Fehler und Bugs finden ist das Ihr Problem DANKE!");
            }
        });
        
        hilfe.add(handbook);
        hilfe.addSeparator();
        hilfe.add(about);
        
        //Toolbar
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JToolBar toolbar1 = new JToolBar();

        ImageIcon newi = new ImageIcon("icons/new.png");
        ImageIcon impi = new ImageIcon("icons/import.png");
        ImageIcon expi = new ImageIcon("icons/export.png");
        ImageIcon konfi = new ImageIcon("icons/portkonf.png");
        ImageIcon vorli = new ImageIcon("icons/vorlagen.png");
        ImageIcon standi = new ImageIcon("icons/resetstandart.png");
        ImageIcon diagi = new ImageIcon("icons/diag.png");
        ImageIcon booki = new ImageIcon("icons/book.png");

        JButton newb = new JButton(newi);
        JButton impb = new JButton(impi);
        JButton expb = new JButton(expi);
        JButton konfb = new JButton(konfi);
        JButton vorlb = new JButton(vorli);
        JButton standb = new JButton(standi);
        JButton diagb = new JButton(diagi);
        JButton bookb = new JButton(booki);

        newb.setFocusable(false);
        newb.setToolTipText("Neue Verbindung");
        impb.setFocusable(false);
        impb.setToolTipText("Importieren");
        expb.setFocusable(false);
        expb.setToolTipText("Exportieren");
        konfb.setFocusable(false);
        konfb.setToolTipText("Port-Konfiguration");
        vorlb.setFocusable(false);
        vorlb.setToolTipText("Vorlagen");
        standb.setFocusable(false);
        standb.setToolTipText("Zurücksetzen zur Standart-Konfiguration");
        diagb.setFocusable(false);
        diagb.setToolTipText("Diagnose");
        bookb.setFocusable(false);
        bookb.setToolTipText("Hilfe");
        
        toolbar1.add(newb);
        toolbar1.addSeparator();
        toolbar1.add(impb);
        toolbar1.add(expb);
        toolbar1.addSeparator();
        toolbar1.add(konfb);
        toolbar1.add(vorlb);
        toolbar1.addSeparator();
        toolbar1.add(standb);
        toolbar1.add(diagb);
        toolbar1.addSeparator();
        toolbar1.add(bookb);
     
        toolbar1.setAlignmentX(0);
        toolbar1.setFloatable(false);
        panel.add(toolbar1);

        mainFrame.add(panel, BorderLayout.NORTH);
        
        mainFrame.setJMenuBar(bar);
		mainFrame.setVisible(true);
	}
	
	private void save()
	{
		/*
		JFileChooser f = new JFileChooser();
		f.setAcceptAllFileFilterUsed(false);
		f.setFileFilter(new FileFilter()
		{
			public String getDescription()
			{
				return "XML-Files(*.xml)";
			}

			// shows only directories and xml-files in the file chooser
			public boolean accept(File f)
			{
				if (f.isDirectory())
					return true;
				return f.getName().toLowerCase().endsWith(".xml")
						|| f.isDirectory();
			}
		});

		int returnValue = f.showSaveDialog(M.this);
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			// Gets the selected file and attaches the file extension
			File fileToSave = f.getSelectedFile();
			String fa = fileToSave.getPath();
			if (!fa.toLowerCase().endsWith(".xml"))
			{
				fa += ".xml";
			}

			for (TemplatesToPathLinker m : ServiceFactory.getPersistenceService().getTemplates())
			{
				if (m.getElement().equals(
						ServiceFactory.getModelService().getActiveModel()))
				{
					m.setPath(fa);
				}
			}

			ServiceFactory.getPersistenceService().saveModel(
					ServiceFactory.getModelService().getActiveModel());
		}
		*/
	}
	
	public void load()
	{
		JFileChooser f = new JFileChooser();
		f.setFileFilter(new FileFilter()
		{
			public String getDescription()
			{
				return "XML-Dateien(*.xml)";
			}

			@Override
			public boolean accept(File f)
			{
				return f.getName().toLowerCase().endsWith(".xml")
						|| f.isDirectory();
			}
		});

		f.setFileSelectionMode(JFileChooser.FILES_ONLY);

		f.showOpenDialog(null);

		File file = f.getSelectedFile();

		if (file != null)
		{
			Templates templates = ServiceFactory.getPersistenceService().loadTemplates(file.getAbsolutePath()).getElement();
		}
	}
}
