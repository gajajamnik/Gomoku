package inteligenca;

import java.util.List;

import logika.Igra;
import logika.Igralec;
import splosno.Koordinati;

public class AlfaBeta extends Inteligenca {
	
	private static final int ZMAGA = 100;
	private static final int PORAZ = -ZMAGA;
	private static final int NEODLOC = 0;
	
	private int globina;
	
	public AlfaBeta (int globina) {
		super("alphabeta globina " + globina);
		this.globina = globina;
	}
	
	public Koordinati izberiPotezo (Igra igra) {
		
		return alphabetaPoteze(igra, this.globina, PORAZ, ZMAGA, igra.naPotezi()).poteza;
	}
	
	public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
		int ocena;
		
		if (igra.naPotezi() == jaz) {ocena = PORAZ;} else {ocena = ZMAGA;}
		List<Koordinati> moznePoteze = igra.poteze();
		Koordinati kandidat = moznePoteze.get(0);
		for (Koordinati p: moznePoteze) {
			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigraj(p);
			int ocenap;
			switch (kopijaIgre.stanje()) {
			case ZMAGA_B: ocenap = (jaz == Igralec.B ? ZMAGA : PORAZ); break;
			case ZMAGA_W: ocenap = (jaz == Igralec.W ? ZMAGA : PORAZ); break;
			case NEODLOCENO: ocenap = NEODLOC; break;
			default:
				
				if (globina == 1) ocenap = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
				else ocenap = alphabetaPoteze (kopijaIgre, globina - 1, alpha, beta, jaz).ocena;
			}
			if (igra.naPotezi() == jaz) {
				if (ocenap > ocena) {
					ocena = ocenap;
					kandidat = p;
					alpha = Math.max(alpha,ocena);
				}
			} else {
				if (ocenap < ocena) {
					ocena = ocenap;
					kandidat = p;
					beta = Math.min(beta, ocena);					
				}	
			}
			if (alpha >= beta)
				return new OcenjenaPoteza (kandidat, ocena);
		}
		return new OcenjenaPoteza (kandidat, ocena);
	}

}