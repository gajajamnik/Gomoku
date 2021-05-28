package logika;

import java.util.LinkedList;
import java.util.List;

import splosno.Koordinati;

public class Igra {
	
	public static int N = 15;					//Velikost polja
	public static int M = 5;					//Stevilo zetonov, potrebnih za zmago
	//public static int ZAHTEVNOST = 5;			//Zahtevnost Alfabete  
	
	private Polje[][] plosca;
	public Igralec naPotezi;
	public Stanje stanje;
	public int stevec;
	
	
	public List<Vrsta> VRSTE = new LinkedList<Vrsta>();
	
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
	
	public Igra(Igra igra) {
		this.plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.plosca[i][j] = igra.plosca[i][j];
			}
		}
		this.naPotezi = igra.naPotezi;
	}
	
	public Igralec naPotezi() {
		return naPotezi;
	}
	
	public Stanje stanje() {
		return stanje;
	}
	
	public Polje[][] getPlosca() {
		return plosca;
	}
	
	
	public boolean odigraj(Koordinati k) {
		int x = k.getX();
		int y = k.getY();
		
		if (this.plosca[x][y] == Polje.PRAZNO) {
			this.plosca[x][y] = naPotezi.getPolje();
			if (this.smoZmagali(k)) {
				stanje = naPotezi.getStanje();
			}
			else {
				if (stevec <= 1) stanje = Stanje.NEODLOCENO;
				else stevec = stevec - 1;
			}
			naPotezi = naPotezi.nasprotnik();
			return true;
		}
		return false;
	}
	
	public boolean smoZmagali(Koordinati t) {
		int x = t.getX();
		int y = t.getY();
		int glavniStevec = 0;
		int[][] smer = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}};
		
		for (int[] s : smer) {
			int dx = s[0];
			int dy = s[1];
			int trenutnoStetje = 0;
			
			for (int n = 1; n < 5; n++) {
				if ((0 <= x + (n * dx) && x + (n * dx) < plosca.length) && (0 <= y + (n * dy) && y + (n * dy) < plosca.length)) {
					if (plosca[x + (n * dx)][y + (n * dy)] == naPotezi.getPolje()) trenutnoStetje++;
					else break;
				}
			}
			for (int m = -1; m > -5; m--) {
				if ((0 <= x + (m * dx) && x + (m * dx) < plosca.length) && (0 <= y + (m * dy) && y + (m * dy) < plosca.length)) {
					if (plosca[x + (m * dx)][y + (m * dy)] == naPotezi.getPolje()) trenutnoStetje++;
					else break;
				}
			}
			if (trenutnoStetje > glavniStevec) glavniStevec = trenutnoStetje;
		}
		if (glavniStevec >= (M - 1)) return true;
		else return false;
	}
	
	public List<Koordinati> najdiPoteze() {
		LinkedList<Koordinati> dobrePoteze = new LinkedList<Koordinati>();
		LinkedList<Koordinati> nujnePoteze = new LinkedList<Koordinati>();
		int razpolovisce = N / 2;
		
		if (razpolovisce % 2 == 0) {
			for (int i = -1; i < 3; i ++) {
				for (int j = -1; j < 3; j++) {
					nujnePoteze.add(new Koordinati(razpolovisce + i, razpolovisce + j));
				}
			}
		}
		
		else {
			for (int i = -1; i < 2; i ++) {
				for (int j = -1; j < 2; j++) {
					nujnePoteze.add(new Koordinati(razpolovisce + i, razpolovisce + j));
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				if (plosca[i][j] != Polje.PRAZNO) {
					continue;
				}
				
				if (i > 0) {
					if (j > 0) {
						if (plosca[i - 1][j - 1] != Polje.PRAZNO || plosca[i][j - 1] != Polje.PRAZNO) {
							dobrePoteze.add(new Koordinati(i, j));
							continue;
						}
					}
					if (j < (N - 1)) {
						if (plosca[i - 1][j + 1] != Polje.PRAZNO || plosca[i][j + 1] != Polje.PRAZNO) {
							dobrePoteze.add(new Koordinati(i, j));
							continue;
						}
					}
					if (plosca[i - 1][j] != Polje.PRAZNO) {
						dobrePoteze.add(new Koordinati(i, j));
					}
				}
				
				if (i < (N - 1)) {
					if (j > 0) {
						if (plosca[i + 1][j - 1] != Polje.PRAZNO || plosca[i][j - 1] != Polje.PRAZNO) {
							dobrePoteze.add(new Koordinati(i, j));
							continue;
						}
					}
					if (j < (N - 1)) {
						if (plosca[i + 1][j + 1] != Polje.PRAZNO || plosca[i][j + 1] != Polje.PRAZNO) {
							dobrePoteze.add(new Koordinati(i, j));
							continue;
						}
					}
					if (plosca[i + 1][j] != Polje.PRAZNO) {
						dobrePoteze.add(new Koordinati(i, j));
					}
				}
			}
		}
		if (dobrePoteze.isEmpty()) {
			return nujnePoteze;
		}
		return dobrePoteze;
	}

}