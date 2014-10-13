package at.bmlvs.NDMS.presentation;

import java.awt.Color;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
        JMenuItem neuverb = new JMenuItem("Neue Verbindung");
        neuverb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane op = new JOptionPane();
                String input = op.showInputDialog("Verbinden zu..");
                
                PresentationFactory.getTabbedWindow().addTabbedMenu(input);
                
            }
        });
            
        JMenuItem importe = new JMenuItem("Importieren");
        JMenuItem export = new JMenuItem("Exportieren");
        JMenuItem einst = new JMenuItem("Einstellungen");
        JMenuItem exit = new JMenuItem("Beenden");
        
        datei.add(neuverb);
        datei.addSeparator();
        datei.add(importe);
        datei.add(export);
        datei.addSeparator();
        datei.add(einst);
        datei.addSeparator();
        datei.add(exit);
        
        //Konfigmenü
        JMenuItem portkonf = new JMenuItem("Portkonfiguration");
        JMenuItem vorlagen = new JMenuItem("Vorlagen");
        
        konfig.add(portkonf);
        konfig.addSeparator();
        konfig.add(vorlagen);
        
        //Werkzeuge
        JMenuItem tomanage = new JMenuItem("Zur Managementkonfiguration zurücksetzten");
        JMenuItem tostandart = new JMenuItem("Zur Standartkonfiguration zurücksetzten");
        JMenuItem diagnose = new JMenuItem("Diagnose");
        
        werkzeug.add(tomanage);
        werkzeug.add(tostandart);
        werkzeug.addSeparator();
        werkzeug.add(diagnose);
        
        //Hilfe
        JMenuItem handbook = new JMenuItem("Benutzerhandbuch");
        JMenuItem about = new JMenuItem("Über NDSM");
        
        hilfe.add(handbook);
        hilfe.addSeparator();
        hilfe.add(about);
        
        mainFrame.setJMenuBar(bar);
		
		mainFrame.setVisible(true);
	}
	
}
