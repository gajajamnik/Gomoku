package logika;

import java.util.LinkedList;
import java.util.List;

import splosno.Koordinati;

public class Igra {
	
	// velikost plošèe
	public static int N = 15;
	
	public static int stevec;
	
	//public?
	public static Polje[][] plosca;
	
	public Igralec naPotezi;
	
	public Stanje stanje;
	
	public Igra() {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		stevec = N * N;
		stanje = Stanje.V_TEKU;
		naPotezi = Igralec.B;
	}
	
	
	
	public boolean odigraj(Koordinati p) {
		
		// preveri ali je polje za potezo prazno, potem je poteza veljavna in jo odigra
		if (plosca[p.getX()][p.getY()] == Polje.PRAZNO) {
			plosca[p.getX()][p.getY()] = naPotezi.getPolje();
			
			// preveri ali bo naša poteza zmagovalna
			if (smoZmagali(p, naPotezi)) {
				if (naPotezi == Igralec.B) stanje = Stanje.ZMAGA_B;
				else stanje = Stanje.ZMAGA_W;
			}
			// preveri ali je to zadnja poteza
			else {
				if (stevec <= 1) stanje = Stanje.NEODLOCENO;
				else stevec = stevec - 1;
			}
			
			naPotezi = naPotezi.nasprotnik();
			
			return true;
		}
		return false;
	}
	
	// preveri ali je bila opravljena zmagovalna poteza
	// static?
	public static boolean smoZmagali(Koordinati t, Igralec i) {
		int x = t.getX();
		int y = t.getY();
		
		
		int[][] smer = {{1,0}, {0,1}, {-1, 1}, {1,1}};
		
		for (int[] s : smer) {
			int dx = s[0];
			int dy = s[1];
			
			int stevec = 0;
			
			for (int n = 1; n < 5; n++) {
				if (0 < x + n*dx && x + n*dx < N && 0 < y + n*dy && y + n*dy < N) {
					if (plosca[x + n*dx][y + n*dy] == i.getPolje()) {
						stevec++;
					}
					else break;
				}
			}
			for (int n = -1; n < 5; n++) {
				if (0 < x + n*dx && x + n*dx < N && 0 < y + n*dy && y + n*dy < N) {
					if (plosca[x + n*dx][y + n*dy] == i.getPolje()) {
						stevec++;
					}
					else break;
				}
			}
			if (stevec >= 4) return true;
		}
		return false;
	}
	
	
	// možne poteze (vsebuje prazna polja)
	public List<Koordinati> poteze() {
		LinkedList<Koordinati> mp = new LinkedList<Koordinati>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] == Polje.PRAZNO) {
					mp.add(new Koordinati(i, j));
				}
			}
		}
		return mp;
	}

}
