package vodja;

import java.util.Map;
import javax.swing.SwingWorker;
import java.util.concurrent.TimeUnit;

import gui.GlavnoOkno;
import inteligenca.AlfaBeta;
import inteligenca.Inteligenca;
import logika.Igra;
import logika.Igralec;
import splosno.KdoIgra;
import splosno.Koordinati;

public class Vodja {
	
	//Slovarja ki beležita vrsto igralcev in kdo igra
	public static Map<Igralec,VrstaIgralca> vrstaIgralca;
	public static Map<Igralec,KdoIgra> kdoIgra;
	
	//Okno v katerem se izvaja igra
	public static GlavnoOkno okno;
	
	//Igra
	public static Igra igra = null;
	
	//Ali je na vrsti clovek
	public static boolean clovekNaVrsti = false;
	
	//Zacetek nove igre
	public static void igramoNovoIgro() {
		igra = new Igra();
		igramo();
	}

	// se izevede med igro
	public static void igramo() {
		okno.osveziGUI();
		switch (igra.stanje()) {
		case ZMAGA_B: 
		case ZMAGA_W: 
		case NEODLOCENO: 
			return; 
		case V_TEKU: 
			Igralec igralec = igra.naPotezi();
			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
			switch (vrstaNaPotezi) {
			case C: 
				clovekNaVrsti = true;
				break;
			case R:
				igrajRacunalnikovoPotezo();
				break;
			}
		}
	}
	
	public static Inteligenca racunalnikovaInteligenca = new AlfaBeta(5);
	
	//Igra racunalnikovo potezo z uporabo inteligence
	public static void igrajRacunalnikovoPotezo() {
		Igra zacetkaIgra = igra;
		SwingWorker<Koordinati, Void> worker = new SwingWorker<Koordinati, Void> () {
			@Override
			protected Koordinati doInBackground() {
				Koordinati poteza = racunalnikovaInteligenca.izberiPotezo(igra);

				try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {};
				return poteza;
				
			}
			@Override
			protected void done () {
				Koordinati poteza = null;
				try {poteza = get();} catch (Exception e) {};
				if (igra == zacetkaIgra) {
					igra.odigraj(poteza);
					igramo ();
				}
			}
		};
		worker.execute();
	}
	
	//Igra clovekovo potezo
	public static void igrajClovekovoPotezo(Koordinati poteza) {
		if (igra.odigraj(poteza)) clovekNaVrsti = false;
		igramo ();
	}

}
