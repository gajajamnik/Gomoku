package logika;

import java.util.LinkedList;
import java.util.List;

import splosno.Koordinati;

public class Igra {
	
	//velikost polja
	public static int N = 15;
	//stevilo zetonov, da zmagamo
	public static int M = 5;
	//zahtevnost alfabete
	public static int ZAHTEVNOST = 5;
	
	//nekaj dodatnih spremenljivk
	private Polje[][] plosca;
	public Igralec naPotezi;
	public Stanje stanje;
	public int stevec;
	
	
	public static final List<Vrsta> VRSTE = new LinkedList<Vrsta>();
	
	//metoda, ki naredi vse mozne vrste na začetnku igre
	static {
		int[][] smer = {{1,0}, {0,1}, {1,1}, {1,-1}};
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int[] s : smer) {
					int dx = s[0];
					int dy = s[0];
					if ((0 <= x + (M - 1) * dx) && (x + (M - 1) * dx < N) && 
						(0 <= y + (M - 1) * dy) && (y + (M - 1) * dy < N)) {
						int[] vrsta_x = new int[M];
						int[] vrsta_y = new int[M];
						for (int k = 0; k < M; k++) {
							vrsta_x[k] = x + dx * k;
							vrsta_y[k] = y + dy * k;
						}
						VRSTE.add(new Vrsta(vrsta_x, vrsta_y));
					}
				}
			}
		}	
	}
	
	
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
	
	// ustvari kopijo igre
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
		

		if (this.plosca[k.getX()][k.getY()] == Polje.PRAZNO) {
			this.plosca[k.getX()][k.getY()] = naPotezi.getPolje();
			
			
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
		int glavni = 0;
		int[][] smer = {{1,0}, {1,1}, {0,1}, {-1,1}};
		for (int[] s : smer) {
			int dx = s[0];
			int dy = s[1];
			int stetje = 0;
			for (int n = 1; n < 5; n++) {
				if ((0 <= x + n * dx && x + n * dx < plosca.length) && (0 <= y + n*dy && y + n * dy < plosca.length)) {
					if (plosca[x + n * dx][y + n * dy] == naPotezi.getPolje()) stetje++;
					else break;
				}
			}
			for (int m = -1; m > -5; m--) {
				if ((0 <= x + m * dx && x + m * dx < plosca.length) && (0 <= y + m * dy && y + m * dy < plosca.length)) {
					if (plosca[x + m * dx][y + m * dy] == naPotezi.getPolje()) stetje++;
					else break;
				}
			}
			if (stetje > glavni) glavni = stetje;
		}
		if (glavni >= (M - 1)) return true;
		else return false;
	}
	
//	private Igralec cigavaVrsta(Vrsta t) {
//		int count_B = 0;
//		int count_W = 0;
//		for (int k = 0; k < M && (count_B == 0 || count_W == 0); k++) {
//			switch (plosca[t.x[k]][t.y[k]]) {
//			case B: count_B += 1; break;
//			case W: count_W += 1; break;
//			case PRAZNO: break;
//			}
//		}
//		if (count_B == M) { return Igralec.B; }
//		else if (count_W == M) { return Igralec.W; }
//		else { return null; }
//	}
//	
//	public Vrsta zmagovalnaVrsta() {
//		for (Vrsta t : VRSTE) {
//			Igralec lastnik = cigavaVrsta(t);
//			if (lastnik != null) return t;
//		}
//		return null;
//	}
//	
//	public Stanje stanje() {
//		// Ali imamo zmagovalca?
//		Vrsta t = zmagovalnaVrsta();
//		if (t != null) {
//			switch (plosca[t.x[0]][t.y[0]]) {
//			case B: return Stanje.ZMAGA_B; 
//			case W: return Stanje.ZMAGA_W;
//			case PRAZNO: assert false;
//			}
//		}
//		// Ali imamo kakĹˇno prazno polje?
//		// ÄŚe ga imamo, igre ni konec in je nekdo na potezi
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (plosca[i][j] == Polje.PRAZNO) return Stanje.V_TEKU;
//			}
//		}
//		// Polje je polno, rezultat je neodloÄŤen
//		return Stanje.NEODLOCENO;
//	}
//	
//	public boolean odigraj(Koordinati p) {
//		if (plosca[p.getX()][p.getY()] == Polje.PRAZNO) {
//			plosca[p.getX()][p.getY()] = naPotezi.getPolje();
//			naPotezi = naPotezi.nasprotnik();
//			return true;
//		}
//		else {
//			return false;
//		}
//	}

	
	
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