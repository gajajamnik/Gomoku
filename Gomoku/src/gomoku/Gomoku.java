package gomoku;

import gui.GlavnoOkno;
import inteligenca.OceniPozicijo;
import logika.Igra;
import logika.Igralec;
import vodja.Vodja;

public class Gomoku {

	public static void main(String[] args) {
		GlavnoOkno glavno_okno = new GlavnoOkno();
		glavno_okno.pack();
		glavno_okno.setVisible(true);
		Vodja.okno = glavno_okno;
				
	}
	
}