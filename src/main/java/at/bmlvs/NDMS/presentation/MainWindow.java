package at.bmlvs.NDMS.presentation;

import java.awt.BorderLayout;
import java.awt.Color;

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
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

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
        
        //Toolbar
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JToolBar toolbar1 = new JToolBar();

        ImageIcon newi = new ImageIcon("icons/new.png");
        ImageIcon imp = new ImageIcon("icons/import.png");
        ImageIcon exp = new ImageIcon("icons/export.png");

        JButton newb = new JButton(newi);
        JButton impb = new JButton(imp);
        JButton expb = new JButton(exp);

        toolbar1.add(newb);
        toolbar1.add(impb);
        toolbar1.add(expb);
        toolbar1.setAlignmentX(0);

        panel.add(toolbar1);

        mainFrame.add(panel, BorderLayout.NORTH);
        
        mainFrame.setJMenuBar(bar);
		mainFrame.setVisible(true);
	}
}
