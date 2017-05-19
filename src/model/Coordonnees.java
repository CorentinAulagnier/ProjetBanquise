package model;

import java.io.Serializable;

/**
 * Class Coordonnees : position sur le plateau de jeu
 */

public class Coordonnees implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int x;
	public int y;

	/**
	 * Constructeurs
	 */
	public Coordonnees(){
		this.x = -1;
		this.y = -1;
	}
	
	public Coordonnees(int a, int b){
		this.x = a;
		this.y = b;
	}
	
	public Coordonnees(Coordonnees c){
		this.x = c.x;
		this.y = c.y;
	}
	
	public String toString(){
		return "(" + x + ", " + y + ")";		
	}


	
	public boolean estInvalide() {
		return (this.x >= 0 && this.y >= 0);
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.x;
        hash = 73 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordonnees other = (Coordonnees) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    
        
	
}

