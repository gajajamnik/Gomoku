package inteligenca;

import logika.Igra;
import logika.Igralec;
import logika.Polje;

public class OceniPozicijo {
	
	//mogoce deluje pravilno?
	
	static int zmagovalneTocke = 1000000;
	
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		Igralec nasprotnik = jaz.nasprotnik();
		int mojaOcena = dobiOceno(igra, jaz);
		int nasprotnikovaOcena = dobiOceno(igra, nasprotnik);
		return mojaOcena - nasprotnikovaOcena;
	}
	
	public static int dobiOceno(Igra igra, Igralec jaz) {
		Polje[][] plosca = igra.getPlosca();
		return oceniVodoravno(plosca, igra, jaz) + oceniNavpicno(plosca, igra, jaz) + oceniDiagonalno(plosca, igra, jaz);
	}
	
	public static int oceniVodoravno(Polje[][] plosca, Igra igra, Igralec jaz) {						//na plosco gledamo le v vodoravni smeri
		int zaporedni = 0;																				//stevilo zaporednih zetonov
		int blokiranje = 2;																				//stevilo blokiranih strani nasih zetonov (najvec blokirani iz 2 strani)
		int ocena = 0;																					//koncna ocena za vodoravno gledano plosco
		
		for (int i = 0; i < Igra.N; i++) {
			for (int j = 0; j < Igra.N; j++) {
				if (plosca[i][j] == jaz.getPolje()) {													//najdemo nase polje
					zaporedni++;																		//pristejemo k zaporednim zetonom
				}
				if (plosca[i][j] == Polje.PRAZNO) {														//najdemo prazno polje
					if (zaporedni > 0) {																//pogledamo, ali je kaj zaporednih
						blokiranje--;																	//ker je polje prazno na tej strani nismo blokirani, odstejemo mejo za racunanje polozaja
						ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);					//izraèunamo oceno polozaja
						zaporedni = 0;																	//resetiramo zaporedne
						blokiranje = 1;																	//nastavimo stevilo mej na 1, ker tukaj ne bomo blokirani v prihodnje
					}
					else {																				//ni zaporednih zetonov
						blokiranje = 1;																	//nastavimo stevilo mej na 1, ker tukaj ne bomo blokirani v prihodnje
					}
				}
				else if (zaporedni > 0) {																//najdemo nasprotnikovo polje
					ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);						//ocenimo nas polozaj
					zaporedni = 0;																		//resetiramo zaporedne
					blokiranje = 2;																		//ker je tukaj nasprotnik, smo na tej strani blokirani, zato nastavimo meje nazaj na 2
				}
				if (zaporedni > 0) {																	//smo na koncu, pogledamo, ali je se kaj zaporednih
					ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);
				}
				zaporedni = 0;																			//resetiramo, saj smo na koncu vrste
				blokiranje = 2;																			//resetiramo, saj smo na koncu vrste
			}
		}
		return ocena;																					//vrnemo oceno
	}
	
	public static int oceniNavpicno(Polje[][] plosca, Igra igra, Igralec jaz) {							//na plosco gledamo le v navpicni smeri
		int zaporedni = 0;																				//stevilo zaporednih zetonov
		int blokiranje = 2;																				//stevilo blokiranih strani nasih zetonov (najvec blokirani iz 2 strani)
		int ocena = 0;																					//koncna ocena za navpicno gledano plosco
		
		for (int i = 0; i < Igra.N; i++) {
			for (int j = 0; j < Igra.N; j++) {
				if (plosca[j][i] == jaz.getPolje()) {													//najdemo nase polje
					zaporedni++;																		//pristejemo k zaporednim zetonom
				}
				if (plosca[j][i] == Polje.PRAZNO) {														//najdemo prazno polje
					if (zaporedni > 0) {																//pogledamo, ali je kaj zaporednih
						blokiranje--;																	//ker je polje prazno na tej strani nismo blokirani, odstejemo mejo za racunanje polozaja
						ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);					//izraèunamo oceno polozaja
						zaporedni = 0;																	//resetiramo zaporedne
						blokiranje = 1;																	//nastavimo stevilo mej na 1, ker tukaj ne bomo blokirani v prihodnje
					}
					else {																				//ni zaporednih zetonov
						blokiranje = 1;																	//nastavimo stevilo mej na 1, ker tukaj ne bomo blokirani v prihodnje
					}
				}
				else if (zaporedni > 0) {																//najdemo nasprotnikovo polje
					ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);						//ocenimo nas polozaj
					zaporedni = 0;																		//resetiramo zaporedne
					blokiranje = 2;																		//ker je tukaj nasprotnik, smo na tej strani blokirani, zato nastavimo meje nazaj na 2
				}
				if (zaporedni > 0) {																	//smo na koncu, pogledamo ali je se kaj zaporednih
					ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);
				}
				zaporedni = 0;																			//resetiramo, saj smo na koncu stolpca
				blokiranje = 2;																			//resetiramo, saj smo na koncu stolpca
			}
		}
		return ocena;																					//vrnemo oceno
	}
	
	public static int oceniDiagonalno(Polje[][] plosca, Igra igra, Igralec jaz) {						//na plosco gledamo le v diagonalnih smeri
		int zaporedni = 0;																				//stevilo zaporednih zetonov
		int blokiranje = 2;																				//stevilo blokiranih strani nasih zetonov (najvec blokirani iz 2 strani)
		int ocena = 0;																					//koncna ocena za diagonalno gledano plosco
		
		for (int k = 0; k < 2 * (Igra.N - 1); k++) {													//po diagonali od spodaj levo do zgoraj desno
			int zacetek = Math.max(0, k - Igra.N + 1);
			int konec = Math.min(Igra.N - 1, k);
			for (int i = zacetek; i <= konec; i++) {
				int j = k - i;
				
				if (plosca[j][i] == jaz.getPolje()) {													//najdemo nase polje
					zaporedni++;																		//pristejemo k zaporednim zetonom
				}
				if (plosca[j][i] == Polje.PRAZNO) {														//najdemo prazno polje
					if (zaporedni > 0) {																//pogledamo, ali je kaj zaporednih
						blokiranje--;																	//ker je polje prazno na tej strani nismo blokirani, odstejemo mejo za racunanje polozaja
						ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);					//izraèunamo oceno polozaja
						zaporedni = 0;																	//resetiramo zaporedne
						blokiranje = 1;																	//nastavimo stevilo mej na 1, ker tukaj ne bomo blokirani v prihodnje
					}
					else {																				//ni zaporednih zetonov
						blokiranje = 1;																	//nastavimo stevilo mej na 1, ker tukaj ne bomo blokirani v prihodnje
					}
				}
				else if (zaporedni > 0) {																//nasprotnikovo polje
					ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);						//ocenimo nas polozaj
					zaporedni = 0;																		//resetiramo zaporedne
					blokiranje = 2;																		//ker je tukaj nasprotnik, smo na tej strani blokirani, zato nastavimo meje nazaj na 2
				}
				if (zaporedni > 0) {																	//smo na koncu, pogledamo ali je se kaj zaporednih
					ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);
				}
				zaporedni = 0;																			//resetiramo, saj smo na koncu diagonale
				blokiranje = 2;																			//resetiramo, saj smo na koncu diagonale
			}
		}
		
		for (int k = 1 - Igra.N; k < Igra.N; k++) {														//po diagonali od zgoraj levo do spodaj desno
			int zacetek = Math.max(0, k);
			int konec = Math.min(Igra.N + k - 1, Igra.N - 1);
			for (int i = zacetek; i <= konec; i++) {
				int j = k - i;
				
				if (plosca[j][i] == jaz.getPolje()) {													//najdemo nase polje
					zaporedni++;																		//pristejemo k zaporednim zetonom
				}
				if (plosca[j][i] == Polje.PRAZNO) {														//prazno polje
					if (zaporedni > 0) {																//pogledamo, ali je kaj zaporednih
						blokiranje--;																	//ker je polje prazno na tej strani nismo blokirani, odstejemo mejo za racunanje polozaja
						ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);					//izraèunamo oceno polozaja
						zaporedni = 0;																	//nastavimo stevilo zaporednih na 0
						blokiranje = 1;																	//nastavimo stevilo mej na 1, ker tukaj ne bomo blokirani v prihodnje
					}
					else {																				//ni zaporednih zetonov
						blokiranje = 1;																	//nastavimo stevilo mej na 1, ker tukaj ne bomo blokirani v prihodnje
					}
				}
				else if (zaporedni > 0) {																//najdemo nasprotnikovo polje
					ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);						//ocenimo nas polozaj
					zaporedni = 0;																		//resetiramo zaporedne
					blokiranje = 2;																		//ker je tukaj nasprotnik, smo na tej strani blokirani, zato nastavimo meje nazaj na 2
				}
				if (zaporedni > 0) {																	//smo na koncu, pogledamo ali je se kaj zaporednih
					ocena += zaporednaOcenaPolje(zaporedni, blokiranje, igra, jaz);
				}
				zaporedni = 0;																			//resetiramo, saj smo na koncu diagonale
				blokiranje = 2;																			//resetiramo, saj smo na koncu diagonale
			}
		}
		return ocena;
	}
	
	

	public static int zaporednaOcenaPolje(int zaporedni, int blokiranje, Igra igra, Igralec jaz) {		//ocenimo zaporedna polja
		final int zagotovljenaZmaga = 1000000;															//ce je zmaga zagotovljena
		
		if (zaporedni < 5 && blokiranje == 2) return 0;													//ce je manj kot 5 zetonov in smo na obeh straneh blokirani, je ta polozaj nekoristen (npr. X000X)
		switch(zaporedni) {
			case 5: {																					//zaporednih je 5, smo ze zmagali
				return zmagovalneTocke;
			}
			case 4: {
				if (igra.naPotezi == jaz) {
					return zagotovljenaZmaga;
				}
				else {
					if (blokiranje == 0) {
						return zagotovljenaZmaga/4;														//4je zaporedni, mi smo na potezi in lahko zmagamo, ker ni blokirano na nobeni strani
					}
					else return 200;																	//se zmeraj dober polozaj za nas
				}
			}
			case 3: {
				if (blokiranje == 0) {
					if (igra.naPotezi == jaz) {
						return 50000;																	//trije zetoni brez blokiranja na naso potezo je zmaga (dobimo 4 brez mej)
					}
					else return 200;																	//se zmeraj uredu polozaj
				}
			}
			case 2 :{
				if (blokiranje == 0) {
					if (igra.naPotezi == jaz) {
						return 7;																		//ne slabo, lahko bi bilo bolje
					}
					else return 5;																		//nismo na potezi, vendar vseeno lahko slabse
				}
				else {
					return 3;																			//zakaj bi igral tukaj?
				}
			}
			case 1: {
				return 1;																				//prazno mesto?
			}
		}
		return zmagovalneTocke * 2;																		//vec kot 5 zetonov nekako, dvojna zmaga
	}

}
