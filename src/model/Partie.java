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
			System.out.println("Erreur : la case à manger ne contient pas de pingouin \n (tuile.aUnPingouin == false) \n Coordonnees : "+c);
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
			tuileArr.mettrePingouin();
			
		} else {
			System.out.println("Erreur : deplacement du pingouin impossible, pas de pingouin à la case : " + dep);
		}
	}

	/**
	 * Retourne les coordonnees des pingouins deplacables du joueur j
	 * 
	 * ATTENTION : Il peut y avoir des cases "null"
	 */
	
	public Coordonnees[] positionPingouins(Joueur j) {
		Coordonnees[] c = new Coordonnees[j.nbPingouin];
		
		for(int i = 0; i < j.nbPingouin; i++) {
			//Verification de la mobilite du pingouin
			if ((j.myPingouins[i].actif) && (b.nePeutPlusBouger(j.myPingouins[i]))) {
				j.myPingouins[i].actif = false;
			}
			
			if (j.myPingouins[i].actif) {
				c[i] = j.myPingouins[i].position;

			} else {
				c[i] = null;
			}
		}
		return c;
	}
	
	/**
	 * Retourne les coordonnees des pingouins deplacables du joueur courant
	 * 
	 * ATTENTION :  Il peut y avoir des cases "null"
	 */
	
	public Coordonnees[] pingouinsDeplacable() {
		return positionPingouins(joueurs[joueurActif]);
	}
	
	/**
	 * Retourne vrai si le joueur j peut jouer
	 */
	
	public boolean peutJouer(Joueur j) {
		Coordonnees[] c = positionPingouins(j);
		
		for(int i = 0; i < c.length; i++) {
			if (c[i] != null)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Retourne vrai si le joueur courant peut jouer
	 */
	
	public boolean peutJouer() {
		return peutJouer(joueurs[joueurActif]);
	}
	
	/**
	 * Retourne l'entier correspondant au joueur à qui appartient le pingouin a la position (i, j)
	 */
	
	public int appartientPingouin(int a, int b) {
		Coordonnees c = new Coordonnees(a, b);
		
		for(int i = 0; i<nbJoueurs; i++)
			for(int j = 0; j<joueurs[i].nbPingouin; j++)
				if (joueurs[i].myPingouins[j].position.equals(c))
					return i;
		return -1;
	}
	

	/**
	 * Retourne le joueur gagnant
	 */

	public Joueur getGagnant() {
		Joueur j = joueurs[0];
		for(int i = 1; i<nbJoueurs;i++ ) {
			if(joueurs[i].poissonsManges>j.poissonsManges || (joueurs[i].poissonsManges==j.poissonsManges && joueurs[i].nbTuiles>j.nbTuiles)) {
				j = joueurs[i];
			}
		}
		return j;
	}
	
	/**
	 * Affichage
	 */
	
	public String toString() {
		String s = "";
		Tuile t;
		int numligne = 0;
		
		s = s + "  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |\n";
		s = s + "--|---|---|---|---|---|---|---|---|\n";
		
		for (int i = 0; i<8; i++) {
			s = s + i + " |";

			if (i%2 == 0) { 	//Ligne pair
				numligne = 7;
				s = s + " ";

			} else {
				numligne = 8; 	//Ligne impair
			}
			
			for (int j = 0; j < numligne; j++) {
				t = b.getTuile(new Coordonnees(i, j));
				int app = appartientPingouin(i, j);
				if (t.aUnPingouin && (app != -1)) {
					s = s + "j" + app + (numligne == 7 ? "| " : " |");

				} else {
					s = s + (numligne == 7 ? " " : "") + (t.nbPoissons == 0 ? " " : t.nbPoissons)+ (numligne == 7 ? "| " : "  |");
				}
	
			}
			if (numligne == 7) {
				s = s + "  |";
			}
			s = s + "\n";
		}
		s = s + "----------------------------------|\n";
		s = s + "nbJoueurs " + nbJoueurs + " joueurActif " + joueurActif + "\n";
		
		for (int i = 0; i<nbJoueurs; i++)
			s = s + joueurs[i] + "\n";

		return s;
	}
	

	/**
	 * Affichage
	 */
	
	public String toStringTerminal() {
		String s = "";
		Tuile t;
		int numligne = 0;
		
		s = s + "  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |\n";
		s = s + "--|---|---|---|---|---|---|---|---|\n";
		
		for (int i = 0; i<8; i++) {
			s = s + i + " |";

			if (i%2 == 0) { 	//Ligne pair
				numligne = 7;
				s = s + " ";

			} else {
				numligne = 8; 	//Ligne impair
			}
			
			for (int j = 0; j < numligne; j++) {
				t = b.getTuile(new Coordonnees(i, j));
				int app = appartientPingouin(i, j);
				if (t.aUnPingouin && (app != -1)) {
					s = s + "j" + app + (numligne == 7 ? "| " : " |");

				} else {
					s = s + (numligne == 7 ? " " : "") + (t.nbPoissons == 0 ? " " : t.nbPoissons)+ (numligne == 7 ? "| " : "  |");
				}
	
			}
			if (numligne == 7) {
				s = s + "  |";
			}
			s = s + "\n";
		}

		return s;
	}
}

