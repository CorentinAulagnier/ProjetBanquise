package model;

import java.io.Serializable;

/**
 * Class Coordonnees : position sur le plateau de jeu
 */

public class Coordonnees implements Serializable{

	public int x;
	public int y;

	/**
	 * Constructeurs
	 */
	public Coordonnees(){
		this.x = 0;
		this.y = 0;
	}
	
	public Coordonnees(int a, int b){
		this.x = a;
		this.y = b;
	}
	
	public String toString(){
		return "(" + x + ", " + y + ")";		
	}

	public boolean equals(Coordonnees c) {
		return (this.x == c.x && this.y == c.y);
	}
	
}

