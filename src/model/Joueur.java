package model;


/**
 * Class Joueur -> gere un joueur
 */

public abstract class Joueur {
	/**
	 * Nombre de deplacements effectues
	 */
	
	public int nbTuiles;

	/**
	 * Nombre de poisson manges
	 */
	
	public int poissonsManges;
	
	/**
	 * Nombre de nbPingouin
	 */
	
	public int nbPingouin;

	/**
	 * Nom du joueur
	 */
	
	public String nom;

	/**
	 * Pingouins du joueur
	 */
	
	public Pingouin[] myPingouins;


	/**
	 * Execute un tour de jeu
	 */
	
	public abstract void jouer();

}

