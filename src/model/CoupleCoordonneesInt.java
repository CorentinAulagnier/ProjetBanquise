package model;

import java.util.Objects;

/**
 * Class de CoupleCoordonneesInt (Coordonnees, Int)
 */
public class CoupleCoordonneesInt {
	
    Coordonnees c;
	int i;
	
	/**
	 * Constructeur.
	 * 
	 * @param c
	 *            Des coordonnees.
	 * @param i
	 *            Un entier.
	 */
	
	public CoupleCoordonneesInt(Coordonnees c, int i){
		this.c = c;
		this.i = i;
	}
	
	/**
	 * Verifie si this est identique au CoupleCoordonneesInt en parametre.
	 * 
	 * @param obj
	 *            Le CoupleCoordonneesInt a tester.
	 *            
	 * @return Vrai si this est identique au CoupleCoordonneesInt en parametre.
	 */
	
    public boolean equals(CoupleCoordonneesInt obj) {
        return (this.c.equals(obj.c) && (obj.i == this.i));
    }
        
}