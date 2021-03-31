package logika;

public enum Igralec {
	
	B, W;
	
	public Igralec nasprotnik() {
		return (this == B ? W : B);
		}


}
