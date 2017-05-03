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
		
		/*
		(dep, arr) = joueurs[joueurActif].jouer();
		deplacement(dep, arr);
		*/
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
	
	/**
	 * Le joueur actif recupere les poissons de la case c
	 */
	
	public void manger(Coordonnees c) {
		Tuile t = b.getTuile(c);
		
		if (t.aUnPingouin) { //La case contient bien un pingouin
			joueurs[joueurActif].poissonsManges += t.nbPoissons;
			t. enlevePoissons();	
			
		} else {
			System.out.println("Erreur : la case à manger ne contient pas de pingouin \n(tuile.aUnPingouin == false) \nCoordonnees : "+c);
		}
	}


	/**
	 * Deplace un pingouin de "dep" vers "arr"
	 */
	
	public void deplacement(Coordonnees dep, Coordonnees arr) {
		
		manger(dep);
		Tuile tuileDep = b.getTuile(dep);
		Tuile tuileArr = b.getTuile(arr);
		
		//Recherche du pingouin a deplacer
		int i;
		for (i = 0; (i < joueurs[joueurActif].nbPingouin && !(joueurs[joueurActif].myPingouins[i].position.equals(dep))); i++)
		
		//On a trouve le pingouin, c'est le numero i
		if (i < joueurs[joueurActif].nbPingouin) { 
			
			joueurs[joueurActif].myPingouins[i].position = arr;
			joueurs[joueurActif].nbTuiles ++;
			
			tuileDep.enlevePingouin();
			tuileArr.metrePingouin();
			
		} else {
			System.out.println("Erreur : deplacement du pingouin impossible, pas de pingouin à la case : " + dep);
		}
	}
	
}

