package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;

import javax.swing.*;

import logika.Igra;
import logika.Igralec;
import vodja.Vodja;
import vodja.VrstaIgralca;


@SuppressWarnings("serial")
public class GlavnoOkno extends JFrame implements ActionListener {

	protected IgralnoPolje polje;

	private JMenuItem igraClovekRacunalnik;
	private JMenuItem igraRacunalnikClovek;
	private JMenuItem igraClovekClovek;
	private JMenuItem igraRacunalnikRacunalnik;
	private JMenuItem velikostPolja;
	
	public GlavnoOkno() {
		
		this.setTitle("Gomoku");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		polje = new IgralnoPolje();
		add(polje);
		
		JMenuBar igralni_meni = new JMenuBar();
		
		JMenu igraj = dodajMenu(igralni_meni, "Nova Igra");
		JMenu nastavitve = dodajMenu(igralni_meni, "Nastavitve");
		setJMenuBar(igralni_meni);
		
		igraClovekRacunalnik = dodajMenuItem(igraj, "Èlovek - Èrni, Raèunalnik - Beli");
		igraRacunalnikClovek = dodajMenuItem(igraj, "Raèunalnik - Èrni, Èlovek - Beli");
		igraClovekClovek = dodajMenuItem(igraj, "Èlovek - Èrni, Èlovek - Beli");
		igraRacunalnikRacunalnik = dodajMenuItem(igraj, "Raèunalnik - Èrni, Raèunalnik - Beli");
		
		velikostPolja = dodajMenuItem(nastavitve, "Velikost Polja");
	}
	
	public JMenu dodajMenu(JMenuBar menubar, String naslov) {
		JMenu menu = new JMenu(naslov);
		menubar.add(menu);
		return menu;
	}
	
	public JMenuItem dodajMenuItem(JMenu menu, String naslov) {
		JMenuItem menuitem = new JMenuItem(naslov);
		menu.add(menuitem);
		menuitem.addActionListener(this);
		return menuitem;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == igraClovekRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.B, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.W, VrstaIgralca.R);
			Vodja.igramoNovoIgro();
		}
		
		else if (source == igraRacunalnikClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.B, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.W, VrstaIgralca.C);
			Vodja.igramoNovoIgro();
		}
		
		else if (source == igraClovekClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.B, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.W, VrstaIgralca.C);
			Vodja.igramoNovoIgro();
		}
		
		else if (source == igraRacunalnikRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.B, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.W, VrstaIgralca.R);
			Vodja.igramoNovoIgro();
		}
		
		else if (source == velikostPolja) {
			String velikostPolja = JOptionPane.showInputDialog(this, "Velikost Polja");
			if (velikostPolja != null && velikostPolja.matches("\\d+")) {
				int velikost = Integer.parseInt(velikostPolja);
				if (velikost >= 20) Igra.N = 19;
				else if (velikost <= 9) Igra.N = 10;
				else Igra.N = velikost;
			}
		}
	}

	public void osveziGUI() {
		polje.repaint();
		
	}
	
		
		
}
