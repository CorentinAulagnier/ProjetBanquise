package model;


/**
 * Class IA : un joueur est une IA
 */

public class IA extends Joueur {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		this.nbPingouin = 3;
		this.myPingouins = new Pingouin[3];
		this.initPingouins();
		this.niveau = 1;
		this.cheminMiniature = "";
		this.couleur = -1;
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
		this.nbPingouin = nbP;
		this.myPingouins = new Pingouin[nbP];
		this.initPingouins();
		this.niveau = level;
		this.cheminMiniature = "";
		this.couleur = -1;
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
	 * @param cheminMigna
	 *            Chemin de sa photo.
	 * @param color
	 *            Couleur du joueur.
	 */	
	
	public IA(String name, int nbP, int level, String cheminMigna, int color){
		this.nbTuiles = 0;
		this.poissonsManges = 0;
		this.nom = name;
		this.nbPingouin = nbP;
		this.myPingouins = new Pingouin[nbP];
		this.initPingouins();
		this.niveau = level;
		this.cheminMiniature = cheminMigna;
		this.couleur = color;
	}

	/**
	 * Affichage.
	 *            
	 * @return Un string correspondant à une IA.
	 */	
	
	public String toString() {
		String s =  "IA nbTuiles " + nbTuiles + " poissonsManges " + poissonsManges + " nbPingouin " + nbPingouin
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
		try {
			Thread.sleep(1000);
		} catch (Exception e) {e.printStackTrace();}
		
		switch (level) {
			case 1:
				return player.algoFacile(p);
			case 2:
				return player.algoMoyen(p);
			case 3:
				return player.algoDifficile(p);
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
		try {
			Thread.sleep(1000);
		} catch (Exception e) {e.printStackTrace();}
		
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