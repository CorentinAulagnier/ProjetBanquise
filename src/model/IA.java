package model;


/**
 * Class IA : un joueur est une IA
 */

public class IA extends Joueur {
	/**
	 * Entier qui indique le niveau de l'ia : 
	 * 	- 1 : facile
	 *  - 2 : moyenne
	 *  - 3 : difficile
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
	
	/**
	 * Constructeurs.
	 * 
	 * @param name
	 *            Nom du joueur.
	 * @param nbP
	 *            Nombre de piongouins.
	 * @param level
	 *            Niveau de l'ia.
	 */	
	
	public IA(String name, int nbP, int level){
		this.nbTuiles = 0;
		this.poissonsManges = 0;
		this.nom = name;
		this.myPingouins = new Pingouin[nbP];
		this.niveau = level;
		this.nbPingouin = nbP;
	}
	
	/**
	 * Affichage.
	 *            
	 * @return Un string correspondant à une IA.
	 */	
	
	public String toString() {
		String s =  "Joueur nbTuiles " + nbTuiles + " poissonsManges " + poissonsManges + " nbPingouin " + nbPingouin
				+ " nom " + nom + " niveau " + niveau + " myPingouins\n";
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
	
	public CoupleGenerique<Coordonnees, Coordonnees> jouer(Partie p) {
		AlgoIA player = new AlgoIA(this);
		int level = this.niveau;
		
		switch (level) {
			case 1:
				return player.algoFacile(p);
			case 2:
				return player.algoMoyen(p);
			case 3:
				return null;//player.algoDifficile(p);
			default:
			
		}
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
		AlgoIA player = new AlgoIA(this);
		int level = this.niveau;
		
		switch (level) {
			case 1:
				return player.placementFacile(p);
			case 2:
				return player.placementMoyen(p);
			case 3:
				return player.placementMoyen(p);
			default:
		}
		return null;
	}

}