package at.bmlvs.NDMS.presentation;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RunPresentation
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub       
		JFrame mainFrame = new JFrame("NDSM");
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
        
        mainFrame.setJMenuBar(bar);
		
		mainFrame.setVisible(true);
		

	}

}
