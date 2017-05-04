package model;

import java.util.Objects;

/**
 *
 * @author carrieme
 */
public class CoupleCoordonneesInt {
    Coordonnees c;
	int i;
	
	
	public CoupleCoordonneesInt(Coordonnees c, int i){
		this.c = c;
		this.i = i;
	}

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public boolean equals(CoupleCoordonneesInt obj) {
        return (this.c.equals(obj.c) && (obj.i == this.i));
    }
        
        
}