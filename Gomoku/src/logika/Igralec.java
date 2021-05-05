package logika;

public enum Igralec {
	B, W;
	
	// vrne nasprotnika od na�ega igralca
	public Igralec nasprotnik() {
		return (this == B ? W : B);
		}


	public Polje getPolje() {
		return (this == B ? Polje.W : Polje.B);
	}
}
