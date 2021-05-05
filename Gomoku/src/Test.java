import logika.Igralec;
import logika.Polje;
import splosno.Koordinati;
import logika.Igra;

public class Test {

	public static void main(String[] args) {
		
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		
		plosca[2][3] = Polje.B;
		plosca[2][4] = Polje.B;
		plosca[2][5] = Polje.B;
		plosca[2][6] = Polje.B;
		plosca[2][7] = Polje.B;
		
		Igra.plosca = plosca;

		System.out.println("Velikost plosce je " + Igra.plosca.length);
		System.out.println("Na mestu (0,0) je " + Igra.plosca[0][0]);
		System.out.println("Na mestu (2,3) je " + Igra.plosca[2][3]);
		System.out.println("Na mestu (2,4) je " + Igra.plosca[2][4]);
		System.out.println("Na mestu (1,3) je " + Igra.plosca[1][3]);
		System.out.println("Ali smo zmagali: " + zmaga);
		
	}

	
	public static Igra igra = new Igra();
	
	public static Polje[][] plosca = new Polje[15][15];
	
	public static Igralec igralec = Igralec.B;
	
	public static Koordinati k = new Koordinati(2, 3);
	
	public static boolean zmaga = igra.smoZmagali(k, igralec);

}
