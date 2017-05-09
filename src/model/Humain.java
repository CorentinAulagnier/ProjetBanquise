package model;


/**
 * Class Humain : un joueur est un humain
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
	
	/**
	 * Constructeurs.
	 * 
	 * @param name
	 *            Nom du joueur.
	 * @param nbP
	 *            Nombre de piongouins.
	 */	

	public Humain(String name, int nbP){
		this.nbTuiles = 0;
		this.poissonsManges = 0;
		this.nom = name;
		this.myPingouins = new Pingouin[nbP];
		this.nbPingouin = nbP;
	}
	
	/**
	 * Affichage.
	 *            
	 * @return Un string correspondant à un Humain
	 */	
	
	public String toString() {
		String s =  "Humain nbTuiles " + nbTuiles + " poissonsManges " + poissonsManges + " nbPingouin " + nbPingouin
				+ " nom " + nom + " myPingouins\n";
		for(int i = 0; i < nbPingouin; i++) 
			s = s + myPingouins[i];
		
		return s;
	}
	
	/**
	 * Execute un tour de jeu.
	 * 
	 * @param p
	 *            L'etat de la partie.
	 * @return Un Couple de Coordonnees representant le deplacement du pingouin (dep,arr) .
	 */	
	
	public CoupleCoordonnees jouer(Partie p) {
		return null;
	}
	
	/**
	 * Renvoie oû placer un pingouin.
	 * 
	 * @param p
	 *            L'etat de la partie.
	 * @return Une Coordonnees representant la premiere position du pingouin.
	 */	
	
	public Coordonnees placement(Partie p) {
		return null;
	}
	
	

}