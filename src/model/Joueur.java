package model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Class Joueur : gere un joueur
 */

public abstract class Joueur implements Serializable{
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
	 * Execute un tour de jeu.
	 * 
	 * @param p
	 *            L'etat de la partie.
	 * @return Un Couple de Coordonnees representant le deplacement du pingouin (dep,arr) .
	 */	
	
	public abstract CoupleCoordonnees jouer(Partie p) ;

	/**
	 * Renvoie oû placer un pingouin.
	 * 
	 * @param p
	 *            L'etat de la partie.
	 * @return Une Coordonnees representant la premiere position du pingouin.
	 */	
	
	public abstract Coordonnees placement(Partie p);
	
	/**
	 * Affichage.
	 *            
	 * @return Un string correspondant à un joueur
	 */	
	
	public abstract String toString();
	

}