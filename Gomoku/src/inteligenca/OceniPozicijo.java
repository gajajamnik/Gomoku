package inteligenca;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Vrsta;

public class OceniPozicijo {
	
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		int ocena = 0;
		for (Vrsta v : Igra.VRSTE) {
			ocena = ocena + oceniVrsto(v, igra, jaz);
		}
		return ocena;	
	}
	
	public static int oceniVrsto (Vrsta v, Igra igra, Igralec jaz) {
		Polje[][] plosca = igra.getPlosca();
		int count_W = 0;
		int count_B = 0;
		for (int k = 0; k < Igra.N && (count_W == 0 || count_B == 0); k++) {
			switch (plosca[v.x[k]][v.y[k]]) {
			case B: count_B += 1; break;
			case W: count_W += 1; break;
			case PRAZNO: break;
			}
		}
		if (count_B > 0 && count_W > 0) { return 0; }
		else if (jaz == Igralec.B) { return count_B - count_W; }
		else { return count_W - count_B; }
	}

}
