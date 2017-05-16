package model;

import java.io.Serializable;

/**
 * Class Pingouin : gere un pingouin
 */

public class Pingouin implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Position du pingouin sur la grille
	 */
	
	public Coordonnees position;

	/**
	 * Indique si le pingouin peut se deplacer
	 */
	
	public boolean actif;

	/**
	 * Constructeurs.
	 * Ne place pas le pingouin et le met inactif.
	 */
	
	public Pingouin(){
		this.position = new Coordonnees();
		this.actif = false;
	}
	
	/**
	 * Constructeurs.
	 * Place le pingouin a la position c et le met actif.
	 * 
	 * @param c
	 *            Position du nouveau pingouin.
	 */
	
	public Pingouin(Coordonnees c){
		this.position = c;
		this.actif = true;
	}
	
/*******************************************************************************************************/

	/**
	 * Affichage.
	 *            
	 * @return Un string correspondant à un pingouin.
	 */	
	
	public String toString() {
		return "Pingouin position " + position + (actif ? " actif" : " inactif") + "\n" ;
	}

	/**
	 * Verifie si deux Pingouin sont egaux.
	 *  
	 * @param obj
	 *            objet a tester
	 *            
	 * @return this egale à obj.
	 */
	
	public boolean equals(Pingouin obj) {
		if (actif != obj.actif)
			return false;
		if (position == null) {
			if (obj.position != null)
				return false;
		} else if (!position.equals(obj.position))
			return false;
		return true;
	}
}