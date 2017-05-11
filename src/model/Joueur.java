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
	
	public abstract CoupleGenerique<Coordonnees, Coordonnees> jouer(Partie p) ;

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

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		for (int i =0; i<nbPingouin ; i++){
			if (!myPingouins[i].equals(other.myPingouins[i])){
				System.out.println("myPingouins i = "+i);
				return false;
			}
		}
		if (nbPingouin != other.nbPingouin)
			return false;
		if (nbTuiles != other.nbTuiles)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	

}