package model;

public class CoupleCoordonnees {

	public Coordonnees c1;
	public Coordonnees c2;
	
	
	public CoupleCoordonnees(Coordonnees c1, Coordonnees c2){
		this.c1 = c1;
		this.c2 = c2;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c1 == null) ? 0 : c1.hashCode());
		result = prime * result + ((c2 == null) ? 0 : c2.hashCode());
		return result;
	}

	//A tester
	public boolean equals(CoupleCoordonnees obj) {
		return (this.c1.equals(obj.c1) && this.c2.equals(obj.c2));
	}
	
}