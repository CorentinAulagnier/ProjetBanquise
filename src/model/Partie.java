package model;


/**
 * Class Partie -> cree et gere la partie
 */

public class Partie {
	/**
	 * Nombre de joueurs
	 */
	
	public int nbJoueurs ;
	
	/**
	 * Prochain joueur
	 */
	
	public int joueurActif ;
	
	/**
	 * Plateau du jeu
	 */
	
	public Banquise b;

	/**
	 * Tableau de joueurs
	 */
	
	public Joueur[] joueurs;

	/**
	 * Constructeurs
	 */
	public Partie(){
		this.b = null;
		this.joueurs = null;
		this.nbJoueurs = 0;
		this.joueurActif = 0;
	}
	
	public Partie(int nbPlayer){
		this.b = null;
		this.joueurs = new Joueur[nbPlayer];
		this.nbJoueurs = nbPlayer;
		this.joueurActif = 0;
	}
	
	public Partie(Banquise plateau){
		this.b = plateau;
		this.joueurs = null;
		this.nbJoueurs = 0;
		this.joueurActif = 0;
	}

	public Partie(Banquise plateau, int nbPlayer){
		this.b = plateau;
		this.joueurs = new Joueur[nbPlayer];
		this.nbJoueurs = nbPlayer;
		this.joueurActif = 0;
	}
	
	public Partie(Banquise plateau, Joueur[] j){
		this.b = plateau;
		this.joueurs = j;
		this.nbJoueurs = j.length;
		this.joueurActif = 0;
	}

	/**
	 * Execute un tour de jeu
	 */
	
	public void tourDeJeu() {
		// TODO implement me
		joueurs[joueurActif].jouer();
		joueurActif = (joueurActif + 1) %nbJoueurs;
	}

	/**
	 * Renvoie vrai, si tout les pingouins sont bloquees
	 */
	
	public boolean estPartieFini() {
		for(int i = 0; i<nbJoueurs; i++)
			for(int j = 0; j<joueurs[i].nbPingouin; j++)
				if (joueurs[i].myPingouins[j].actif)
					return false;
		return true;
	}

}

