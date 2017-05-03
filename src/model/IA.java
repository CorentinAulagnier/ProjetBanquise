package model;


/**
 * Class IA -> un joueur est une IA
 */

public class IA extends Joueur {
	/**
	 * Entier qui indique le niveau de l'ia : 
	 * 	- 1 -> facile
	 *  - 2 -> moyenne
	 *  - 3 -> difficile
	 */
	
	public int niveau;

	/**
	 * Constructeurs
	 */
	public IA(){
		this.nbTuiles = 0;
		this.poissonsManges = 0;
		this.nom = "";
		this.myPingouins = new Pingouin[3];
		this.niveau = 1;
		this.nbPingouin = 3;
	}

	public IA(String name, int nbP, int level){
		this.nbTuiles = 0;
		this.poissonsManges = 0;
		this.nom = name;
		this.myPingouins = new Pingouin[nbP];
		this.niveau = level;
		this.nbPingouin = nbP;
	}
	
	/**
	 * Execute un tour de jeu
	 */
	
	public void jouer() {
		// TODO implement me
	}

}

