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
	 * Affichage
	 */
	
	public String toString() {
		String s =  "Joueur nbTuiles " + nbTuiles + " poissonsManges " + poissonsManges + " nbPingouin " + nbPingouin
				+ " nom " + nom + " niveau " + niveau + " myPingouins\n";
		for(int i = 0; i < nbPingouin; i++) 
			s = s + myPingouins[i];
		
		return s;
	}
	
	/**
	 * Execute un tour de jeu
	 */
	
	public CoupleCoordonnees jouer(Partie p) {
		AlgoIA player = new AlgoIA(this);
		int level = this.niveau;
		
		switch (level) {
			case 1:
				return player.algoFacile(p.b);
			case 2:
				return player.algoMoyen(p.b);
			case 3:
				//return player.algoDifficile(p.b);
				break;
			default:
			
		}
		return null;
	}
	
	/**
	 * Place un pingouin
	 */
	
	public Coordonnees placement(Partie p) {
		AlgoIA player = new AlgoIA(this);
		int level = this.niveau;
		
		switch (level) {
			case 1:
				return player.placementFacile(p.b);
			case 2:
				return player.placementMoyen(p.b);
			case 3:
				//return player.placementDifficile(p.b);
				break;
			default:
		}
		return null;
	}

}

