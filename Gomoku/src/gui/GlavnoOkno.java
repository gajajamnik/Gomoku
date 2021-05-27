package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;

import javax.swing.*;

import logika.Igra;
import logika.Igralec;
import splosno.KdoIgra;
import vodja.Vodja;
import vodja.VrstaIgralca;

@SuppressWarnings("serial")
public class GlavnoOkno extends JFrame implements ActionListener {
	
	protected IgralnoPolje polje;
	
	//statusna vrstica
	private JLabel status;

	//nekaj gumbov, s katerimi si izberemo nastavitve
	private JMenuItem igraClovekRacunalnik;
	private JMenuItem igraRacunalnikClovek;
	private JMenuItem igraClovekClovek;
	private JMenuItem igraRacunalnikRacunalnik;
	private JMenuItem velikostPolja;
	//private JMenuItem zahtevnostRacunalnika;
	
	public GlavnoOkno() {
		
		this.setTitle("Gomoku");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		polje = new IgralnoPolje();
		
		GridBagConstraints polje_layout = new GridBagConstraints();
		polje_layout.gridx = 0;
		polje_layout.gridy = 0;
		polje_layout.fill = GridBagConstraints.BOTH;
		polje_layout.weightx = 1.0;
		polje_layout.weighty = 1.0;
		getContentPane().add(polje, polje_layout);
		
		status = new JLabel();
		
		status.setFont(new Font(status.getFont().getName(),
							    status.getFont().getStyle(),
							    20));
		GridBagConstraints status_layout = new GridBagConstraints();
		status_layout.gridx = 0;
		status_layout.gridy = 1;
		status_layout.anchor = GridBagConstraints.CENTER;
		getContentPane().add(status, status_layout);
		
		status.setText("Izberite igro!");
		
		JMenuBar igralni_meni = new JMenuBar();
		
		//dodamo menuje
		JMenu igraj = dodajMenu(igralni_meni, "Nova Igra");
		JMenu nastavitve = dodajMenu(igralni_meni, "Nastavitve");
		setJMenuBar(igralni_meni);
		
		//menu igraj
		igraClovekRacunalnik = dodajMenuItem(igraj, "Èlovek - Èrni, Raèunalnik - Beli");
		igraRacunalnikClovek = dodajMenuItem(igraj, "Raèunalnik - Èrni, Èlovek - Beli");
		igraClovekClovek = dodajMenuItem(igraj, "Èlovek - Èrni, Èlovek - Beli");
		igraRacunalnikRacunalnik = dodajMenuItem(igraj, "Raèunalnik - Èrni, Raèunalnik - Beli");
		
		//menu nastavitve
		velikostPolja = dodajMenuItem(nastavitve, "Velikost Polja");
		//zahtevnostRacunalnika = dodajMenuItem(nastavitve, "Zahtevnost Raèunalnika");
	}
	
	//funkcije iz vaj
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
		//nastavimo igralca clovek-racunalnik
		if (source == igraClovekRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.B, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.W, VrstaIgralca.R);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.B, new KdoIgra("Èlovek")); 
			Vodja.kdoIgra.put(Igralec.W, new KdoIgra("Nakljuèni AI"));
			Vodja.igramoNovoIgro();
		}
		//nastavimo igralca racunalnik-clovek
		else if (source == igraRacunalnikClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.B, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.W, VrstaIgralca.C);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.W, new KdoIgra("Èlovek")); 
			Vodja.kdoIgra.put(Igralec.B, new KdoIgra("Nakljuèni AI"));
			Vodja.igramoNovoIgro();
		}
		//nastavimo igralca clovek-clovek
		else if (source == igraClovekClovek) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.B, VrstaIgralca.C); 
			Vodja.vrstaIgralca.put(Igralec.W, VrstaIgralca.C);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.B, new KdoIgra("Èlovek")); 
			Vodja.kdoIgra.put(Igralec.W, new KdoIgra("Èlovek"));
			Vodja.igramoNovoIgro();
		}
		//nastavimo igralca racunalnik-racunalnik
		else if (source == igraRacunalnikRacunalnik) {
			Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
			Vodja.vrstaIgralca.put(Igralec.B, VrstaIgralca.R); 
			Vodja.vrstaIgralca.put(Igralec.W, VrstaIgralca.R);
			Vodja.kdoIgra = new EnumMap<Igralec,KdoIgra>(Igralec.class);
			Vodja.kdoIgra.put(Igralec.B, new KdoIgra("Nakljuèni AI")); 
			Vodja.kdoIgra.put(Igralec.W, new KdoIgra("Nakljuèni AI"));
			Vodja.igramoNovoIgro();
		}
		//moznost spreminjanja polja
		else if (source == velikostPolja) {
			String velikostPolja = JOptionPane.showInputDialog(this, "Vnesite velikost polja!");
			if (velikostPolja != null && velikostPolja.matches("\\d+")) {
				int velikost = Integer.parseInt(velikostPolja);
				if (velikost > 25) Igra.N = 25;
				else if (velikost < 4) Igra.N = 5;
				else Igra.N = velikost;
			}
		}
		//moznost spreminjanja globine (se ne deluje)?
//		else if (source == zahtevnostRacunalnika) {
//			String zahtevnostPolja = JOptionPane.showInputDialog(this, "Vnesite zahtevnost raèunalnika! (globina)");
//			if (zahtevnostPolja != null && zahtevnostPolja.matches("\\d+")) {
//				int zahtevnost = Integer.parseInt(zahtevnostPolja);
//				if (zahtevnost > 9) Igra.ZAHTEVNOST = 9 ;
//				else if (zahtevnost < 0) Igra.ZAHTEVNOST = 0;
//				else Igra.ZAHTEVNOST = zahtevnost;
//				System.out.println(Igra.ZAHTEVNOST);
//			}
//		}
	}

	//osvezi GUI
	public void osveziGUI() {
		if (Vodja.igra == null) {
			status.setText("Igra ni v teku.");
		}
		else {
			switch(Vodja.igra.stanje()) {
			case NEODLOCENO: status.setText("Igra je neodloèena");
				break;
			case V_TEKU: status.setText("Na potezi je " + Vodja.igra.naPotezi + " - " + Vodja.kdoIgra.get(Vodja.igra.naPotezi()).ime());
				break;
			case ZMAGA_B: status.setText("Zmagal je Èrni - " + Vodja.kdoIgra.get(Vodja.igra.naPotezi().nasprotnik()).ime());
				break;
			case ZMAGA_W: status.setText("Zmagal je Beli - " + Vodja.kdoIgra.get(Vodja.igra.naPotezi().nasprotnik()).ime());
				break;
			}
		}
		polje.repaint();
	}

}
