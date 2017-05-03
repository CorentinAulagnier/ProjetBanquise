package model;


/**
 * Class Humain -> un joueur est un humain
 */

public class Humain extends Joueur {
	/**
	 * Constructeurs
	 */
	public Humain(){
		this.nbTuiles = 0;
		this.poissonsManges = 0;
		this.nom = "";
		this.myPingouins = new Pingouin[3];
		this.nbPingouin = 3;
	}

	public Humain(String name, int nbP){
		this.nbTuiles = 0;
		this.poissonsManges = 0;
		this.nom = name;
		this.myPingouins = new Pingouin[nbP];
		this.nbPingouin = nbP;
	}
	
	
	/**
	 * Execute un tour de jeu
	 */
	
	public void jouer() {
		// TODO implement me
	}

}

