package logika;

import java.util.LinkedList;
import java.util.List;

import splosno.Koordinati;

public class Igra {
	
	// velikost plošèe
	public static int N = 15;
	
	Polje[][] plosca;
	public Igralec naPotezi;
	public Stanje stanje;
	public int stevec;
	
	public Igra() {
		
		this.plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		
		this.stevec = N * N;
		this.stanje = Stanje.V_TEKU;
		this.naPotezi = Igralec.B;
	}
	
	public Stanje stanje() {
		return this.stanje;
	}
	
	public Polje[][] getPlosca() {
		return this.plosca;
	}
	
	public boolean odigraj(Koordinati k) {
		
		// preveri ali je polje za potezo prazno, potem je poteza veljavna in jo odigra
		if (this.plosca[k.getX()][k.getY()] == Polje.PRAZNO) {
			this.plosca[k.getX()][k.getY()] = this.naPotezi.getPolje();
			
			// preveri ali bo naša poteza zmagovalna
			if (this.smoZmagali(k)) {
				stanje = this.naPotezi.getStanje();
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

	public boolean smoZmagali(Koordinati t) {
		int x = t.getX();
		int y = t.getY();
		int glavni = 0;
		int[][] smer = {{1,0}, {1,1}, {0,1}, {-1,1}};
		for (int[] s : smer) {
			int dx = s[0];
			int dy = s[1];
			int stetje = 0;
			for (int n = 1; n < 5; n++) {
				if ((0 <= x + n * dx && x + n * dx < plosca.length) && (0 <= y + n*dy && y + n * dy < plosca.length)) {
					if (plosca[x + n * dx][y + n * dy] == this.naPotezi.getPolje()) stetje++;
					else break;
				}
			}
			for (int m = -1; m > -5; m--) {
				if ((0 <= x + m * dx && x + m * dx < plosca.length) && (0 <= y + m * dy && y + m * dy < plosca.length)) {
					if (plosca[x + m * dx][y + m * dy] == this.naPotezi.getPolje()) stetje++;
					else break;
				}
			}
			if (stetje > glavni) glavni = stetje;
		}
		if (glavni >= 4) return true;
		else return false;
	}

	
	
	
	
	// možne poteze (vsebuje prazna polja)
	public List<Koordinati> poteze() {
		
		LinkedList<Koordinati> mp = new LinkedList<Koordinati>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (this.plosca[i][j] == Polje.PRAZNO) {
					mp.add(new Koordinati(i, j));
				}
			}
		}
		return mp;
	}

}
