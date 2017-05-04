package model;


/**
 * Class Pingouin -> gere un pingouin
 */

public class Pingouin {
	/**
	 * Position du pingouin sur la grille
	 */
	
	public Coordonnees position;

	/**
	 * Indique si le pingouin peut se deplacer
	 */
	
	public boolean actif;

	/**
	 * Constructeurs
	 */
	public Pingouin(){
		this.position = new Coordonnees();
		this.actif = false;
	}
	
	public Pingouin(Coordonnees c){
		this.position = c;
		this.actif = true;
	}

	public String toString() {
		return "Pingouin " + position + (actif ? " actif" : " inactif") ;
	}

}

