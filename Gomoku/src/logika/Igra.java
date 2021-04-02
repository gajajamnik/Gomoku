package logika;

import java.util.LinkedList;
import java.util.List;

public class Igra {
	
	public int N = 20;
	
	private Polje[][] plosca;
	
	public Igralec naPotezi;
	
	public Stanje stanje;
	
	public Igra() {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		stanje = Stanje.V_TEKU;
		naPotezi = Igralec.B;
	}
	
	
	
	public boolean odigraj(Koordinati p) {
		if (plosca[p.getX()][p.getY()] == Polje.PRAZNO) {
			plosca[p.getX()][p.getY()] = naPotezi.getPolje();
			
			if (smoZmagali(p, naPotezi)) {
				if (naPotezi == Igralec.B) stanje = Stanje.ZMAGA_B;
				else stanje = Stanje.ZMAGA_W;
			}
			
			naPotezi = naPotezi.nasprotnik();
			return true;
		}
		return false;
	}
	
	
	public boolean smoZmagali(Koordinati t, Igralec i) {
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
