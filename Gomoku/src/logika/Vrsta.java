package logika;

import java.util.Arrays;

/**
 * Vrsta na plošèi
 */
public class Vrsta {

	//Tabela x koordinat
	public int[] x;
	//Tabela y koordinat
	public int[] y;
	
	public Vrsta(int[] x, int y[]) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Vrsta [x=" + Arrays.toString(x) + ", y=" + Arrays.toString(y) + "]";
	}

}