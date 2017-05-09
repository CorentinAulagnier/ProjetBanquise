package model;

import java.io.Serializable;

/**
 * Class Tuile -> une case du jeu
 */

public class Tuile implements Serializable {
	/**
	 * nombre de poisson sur cette case
	 * 0 si la case n'est pas accessible
	 */
	
	public int nbPoissons;

	/**
	 * indique si il y a un pingouin
	 */
	
	public boolean aUnPingouin;

	/**
	 * Affichage
	 */
	public String toString() {
		return "Tuile nbPoissons=" + nbPoissons + ", aUnPingouin=" + aUnPingouin;
	}


	/**
	 * Constructeurs.
	 */
	public Tuile(){
		this.nbPoissons = 0;
		this.aUnPingouin = false;
	}
	
	/**
	 * Constructeurs.
	 * 
	 * @param n
	 *            Le nombre de poissons sur la tuile.
	 */
	
	public Tuile(int n){
		this.nbPoissons = n;
		this.aUnPingouin = false;
	}
	
	/**
	 * Constructeurs.
	 * 
	 * @param n
	 *            Le nombre de poissons sur la tuile.
	 * @param b
	 *            Presence d'un pingouin sur la tuile.
	 */
	
	public Tuile(int n, boolean b){
		this.nbPoissons = n;
		this.aUnPingouin = b;
	}
	
	/**
	 * Constructeurs.
	 * 
	 * @param b
	 *            Presence d'un pingouin sur la tuile.
	 */
	
	public Tuile(boolean b){
		this.nbPoissons = 0;
		this.aUnPingouin = b;
	}
	
	/**
	 * Constructeurs.
	 * 
	 * @param t
	 *            Tuile a copier.
	 */
	
	public Tuile(Tuile t){
		this.nbPoissons = t.nbPoissons;
		this.aUnPingouin = t.aUnPingouin;
	}
	
	/**
	 * Met un pingouin sur la case
	 */
	
	public void mettrePingouin(){
		this.aUnPingouin = true;
	}
	
	/**
	 * Enleve le pingouin de la case
	 */
	
	public void enlevePingouin(){
		this.aUnPingouin = false;
	}
	
	/**
	 * Enleve les poissons de la case
	 */
	
	public void enlevePoissons(){
		this.nbPoissons = 0;
	}
	
	/**
	 * Met nb poissons dans la case
	 */
	
	public void mettrePoissons(int nb){
		this.nbPoissons = nb;
	}

	/**
	 * Indique si un pingouin peut venir sur cette tuile.
	 * 
	 * @return Vrai si la tuile est accessible.
	 */
	
	public boolean estAccessible() {
		return !(aUnPingouin || nbPoissons==0);
	}
	
}