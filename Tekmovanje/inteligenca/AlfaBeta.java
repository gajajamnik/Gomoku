package inteligenca;

import java.util.List;

import logika.Igra;
import logika.Igralec;
import splosno.Koordinati;

//ne deluje pravilno

public class AlfaBeta extends Inteligenca {
	
	private static final int ALFA_W = 100; //beli bo imel vedno alfa
	private static final int BETA_B = -100; //crni bo imel beto
	private static final int NEODLOCENO = 0;
	private int globina;
	
	public AlfaBeta (int globina) {
		super("alphabeta globina " + globina);
		this.globina = globina;
	}
	
	public Koordinati izberiPotezo (Igra igra) {
		return alphabetaPoteze(igra, this.globina, ALFA_W, BETA_B, igra.naPotezi()).poteza;
	}
	
	public static OcenjenaPoteza alphabetaPoteze(Igra igra, int glob, int alpha, int beta, Igralec jaz) {
		
		if (glob == 0) {
			int ocenaPoteze = OceniPozicijo.oceniPozicijo(igra, jaz);
			return new OcenjenaPoteza(null, ocenaPoteze);
		}
		
		OcenjenaPoteza najPoteza = null;
		List<Koordinati> moznePoteze = igra.najdiPoteze();
		
		for (Koordinati p: moznePoteze) {
			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigraj(p);
			OcenjenaPoteza ocenaPot;
			switch (kopijaIgre.stanje()) {
				case ZMAGA_B: ocenaPot = new OcenjenaPoteza(p, BETA_B); break;
				case ZMAGA_W: ocenaPot = new OcenjenaPoteza(p, ALFA_W); break;
				case NEODLOCENO: ocenaPot = new OcenjenaPoteza(p, NEODLOCENO); break;
				default: ocenaPot = alphabetaPoteze(kopijaIgre, glob - 1, ALFA_W, BETA_B, jaz.nasprotnik());
		}
			if (najPoteza == null) {
				najPoteza = ocenaPot;
			}
			else if (jaz == Igralec.W && najPoteza.ocena < ocenaPot.ocena) {
				najPoteza = ocenaPot;
			}
			else if (jaz == Igralec.B && najPoteza.ocena > ocenaPot.ocena) {
				najPoteza = ocenaPot;
			}
		}
		return najPoteza;
	}

}