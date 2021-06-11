package inteligenca;

import logika.Igra;
import splosno.KdoIgra;
import splosno.Koordinati;

/**
 * Racunalnikova inteligenca
 */
public abstract class Inteligenca extends KdoIgra {

	public Inteligenca(String ime) {
		super(ime);
	}
	
	public abstract Koordinati izberiPotezo(Igra igra);
	
	

}