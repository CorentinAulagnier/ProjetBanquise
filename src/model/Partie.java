package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class Partie : cree et gere la partie
 */

public class Partie implements Serializable {
	
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
	 * Utilisation historique
	 */	
	
	public boolean utiliseHistorique = false;
	
	public transient Historique h;

	/**
	 * Constructeurs
	 */
	public Partie(){
		this.b = null;
		this.joueurs = null;
		this.nbJoueurs = 0;
		this.joueurActif = 0;
	}
	
	/**
	 * Constructeurs.
	 * 
	 * @param nbPlayer
	 *            Le nombre de joueur.
	 */
	
	public Partie(int nbPlayer){
		this.b = null;
		this.joueurs = new Joueur[nbPlayer];
		this.nbJoueurs = nbPlayer;
		this.joueurActif = 0;
	}
	
	/**
	 * Constructeurs.
	 * 
	 * @param plateau
	 *            La banquise.
	 */
	
	public Partie(Banquise plateau){
		this.b = plateau;
		this.joueurs = null;
		this.nbJoueurs = 0;
		this.joueurActif = 0;
	}
	
	/**
	 * Constructeurs.
	 * 
	 * @param plateau
	 *            La banquise.
	 * @param nbPlayer
	 *            Le nombre de joueur.
	 */

	public Partie(Banquise plateau, int nbPlayer){
		this.b = plateau;
		this.joueurs = new Joueur[nbPlayer];
		this.nbJoueurs = nbPlayer;
		this.joueurActif = 0;
	}
	
	/**
	 * Constructeurs.
	 * 
	 * @param plateau
	 *            La banquise.
	 * @param j
	 *            Le tableau des joueurs.
	 */
	
	public Partie(Banquise plateau, Joueur[] j){
		this.b = plateau;
		this.joueurs = j;
		this.nbJoueurs = j.length;
		this.joueurActif = 0;
	}

/*******************************************************************************************************/

	/**
	 * Clone de la partie actuelle.
	 * 
	 * @return Renvoie la partie clonne
	 */
	
	@Override
	public Partie clone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
	
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Partie) ois.readObject();
		} catch (Exception e){
			return null;
		}
	}

/*******************************************************************************************************/

	/**
	 * Annuler le dernier coup joue.
	 * 
	 * @return vrai si on a le droit d'annuler un coup
	 */
	
	public void annuler() {
		Partie p = h.annuler(this.clone());
		if(p!=null) {
			this.b=p.b;
			//this.h = new Historique(p.h);
			this.joueurActif=p.joueurActif;
			this.nbJoueurs=p.nbJoueurs;
			this.utiliseHistorique = p.utiliseHistorique;
			this.joueurs = p.joueurs;
		} else {
			System.err.println("Impossible d'annuler.");
		}
	}
	
	/**
	 * Refaire le dernier coup annule.
	 * 
	 * @return vrai si on a le droit de retablir un coup
	 */
	
	public void retablir() {
		Partie p = h.retablir(this.clone());
		if(p!=null) {
			this.b=p.b;
			this.joueurActif=p.joueurActif;
			this.nbJoueurs=p.nbJoueurs;
			this.utiliseHistorique = p.utiliseHistorique;
			this.joueurs = p.joueurs;
		} else {
			System.err.println("Impossible de retablir.");
		}
	}

/*******************************************************************************************************/

	/**
	 * Sauvegarde la partie en cours.
	 * 
	 * @param name
	 *            Nom du fichier de sauvegarde
	 */
	
	public void sauvegarder(String name) {
		try {
			File fichier =  new File("save/"+name+".partie") ;
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
			oos.writeObject(this);
			if(utiliseHistorique) {
				h.sauvegarder(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Sauvegarde la partie en cours dans le fichier "no_name.banquise" par default.
	 */
	
	public void sauvegarder() {
		sauvegarder("no_name");
	}
	
	/**
	 * Charge la partie en parametre.
	 * 
	 * @param name
	 *            Nom du fichier a recuperer
	 */
	
	public void charger(String name) {
		try {
			File fichier =  new File("save/"+name+".partie") ;
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;		
			Partie p = (Partie)ois.readObject() ;
			this.b = p.b;
			this.joueurActif = p.joueurActif;
			this.nbJoueurs = p.nbJoueurs;
			this.joueurs = p.joueurs;
			this.utiliseHistorique = p.utiliseHistorique;
			if(utiliseHistorique) {
				this.h.charger(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Charge la partie "no_name.banquise" par default.
	 */
	
	public void charger() {
		charger("no_name");
	}

/*******************************************************************************************************/

	/**
	 * Execute un tour de jeu
	 */
	
	public void tourDeJeu() {
		// TODO implement me
	}

/*******************************************************************************************************/

	/**
	 * Verifie si la partie est termine.
	 * 
	 * @return Renvoie vrai, si tout les pingouins de tout les joueurs sont bloquees.
	 */
	
	public boolean estPartieFini() {
		for(int i = 0; i<nbJoueurs; i++)
			for(int j = 0; j<joueurs[i].nbPingouin; j++)
				if (joueurs[i].myPingouins[j].actif)
					return false;
		return true;
	}

	/**
	 * Retourne si le joueur j peut jouer
	 * 
	 * @param j
	 *            Le joueur.
	 *            
	 * @return Retourne vrai si le joueur j peut jouer.
	 */
	
	public boolean peutJouer(Joueur j) {
		return (nbPingouinActif(j) > 0);
	}
	
	/**
	 * Retourne si le joueur actif peut jouer
	 *         
	 * @return Retourne vrai si le joueur actif peut jouer.
	 */
	
	public boolean peutJouer() {
		return peutJouer(joueurs[joueurActif]);
	}
	

/*******************************************************************************************************/

	/**
	 * Le joueur actif recupere les poissons de la case c.
	 * 
	 * @param c
	 *            La position a manger.
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
	 * Deplace un pingouin de "cc.c1" vers "cc.c2"
	 * 	 
	 * @param cc
	 *            Le CoupleCoordonnees correspondant au deplacement d'un pingouin.
	 */
	
	public void deplacement(CoupleCoordonnees cc) {
		deplacement(cc.c1, cc.c2);
	}

	/**
	 * Deplace un pingouin de "dep" vers "arr"
	 * 	 
	 * @param dep
	 *            Les Coordonnees correspondant a l'emplacement de depart d'un pingouin.
	 * @param arr
	 *            Les Coordonnees correspondant a l'emplacement d'arrive d'un pingouin.
	 */
	
	public void deplacement(Coordonnees dep, Coordonnees arr) {

		int numJoueur = appartientPingouin(dep);
		Pingouin p = recherchePingouin(numJoueur, dep);
		if(p!=null) {
			deplacement(p, arr);
		} else {
			System.out.println("Impossible de récupérer le pinguoin en "+dep);
		}

	}
	
	/**
	 * Deplace un pingouin p vers les coordonnees "arr"
	 * 	 
	 * @param p
	 *            Le pingouin a deplacer.
	 * @param arr
	 *            Les Coordonnees correspondant a l'emplacement d'arrive du pingouin p.
	 */
	
	public void deplacement(Pingouin p, Coordonnees arr) {
		
		manger(p.position);
		Tuile tuileDep = b.getTuile(p.position);
		Tuile tuileArr = b.getTuile(arr);
			
		p.position = arr;
		joueurs[joueurActif].nbTuiles ++;
		
		tuileDep.enlevePingouin();
		tuileArr.mettrePingouin();

	}

/*******************************************************************************************************/

	/**
	 * Retourne les coordonnees des pingouins deplacables du joueur j
	 * ATTENTION : Il peut y avoir des cases "null"
	 * 
	 * @param j
	 *            Le joueur.
	 *            
	 * @return Un tableau de coordonnees.
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
	 * Retourne les coordonnees des pingouins deplacables du joueur actif
	 * ATTENTION : Il peut y avoir des cases "null"
	 *            
	 * @return Un tableau de coordonnees.
	 */
	
	public Coordonnees[] pingouinsDeplacable() {
		return positionPingouins(joueurs[joueurActif]);
	}
	
	/**
	 * Retourne combien de pingouins le joueur j peut deplacer
	 * 
	 * @param j
	 *            Le joueur.
	 *            
	 * @return Retourne le nb de pingouins deplacable du joueur j.
	 */

/*******************************************************************************************************/

	public int nbPingouinActif(Joueur j) {
		Coordonnees[] c = positionPingouins(j);
		int nb = 0;
		for(int i = 0; i < c.length; i++) {
			if (c[i] != null)
				nb++;
		}
		
		return nb;
	}
	
	/**
	 * Retourne combien de pingouins le joueur actif peut deplacer
	 * 
	 * @param j
	 *            Le joueur.
	 *            
	 * @return Retourne le nb de pingouins deplacable du joueur actif.
	 */
	
	public int nbPingouinActif() {
		return nbPingouinActif(joueurs[joueurActif]);
	}

	/**
	 * Tue les pingouins bloques de chaque joueur
	 */
	
	public void verifierPingouinActif() {
		for(int i = 0; i<nbJoueurs; i++) {
			for(int j = 0; j<joueurs[i].nbPingouin; j++) {
				Pingouin pin = joueurs[i].myPingouins[j];
				if (pin.actif == true && b.nePeutPlusBouger(pin)) {
					manger(pin.position);
					b.getTuile(pin.position).enlevePingouin();
					pin.actif = false;
				}
			}
		}
	}
	

/*******************************************************************************************************/
	
	/**
	 * Retourne l'entier correspondant au joueur à qui appartient le pingouin aux coordonnees c
	 * 
	 * @param c
	 *            Les coordonnees du pingouin a tester.
	 *            
	 * @return Le numero du joueur proprietaire du pingouin aux coordonnees c si il existe, -1 sinon.
	 */
	
	public int appartientPingouin(Coordonnees c) {
		for(int i = 0; i<nbJoueurs; i++)
			for(int j = 0; j<joueurs[i].nbPingouin; j++)
				if (joueurs[i].myPingouins[j].position.equals(c))
					return i;
		return -1;
	}
	
	/**
	 * Recherche un pingouin pour un joueur donné et des coordonnees donnees
	 * 
	 * @param numJoueur
	 *            Le numero du joueur a qui appartient le pingouin.
	 * @param c
	 *            Les coordonnees du pingouin a retrouver.
	 *            
	 * @return Le pingouin aux coordonnees c si il existe, null sinon.
	 */
	
	public Pingouin recherchePingouin(int numJoueur, Coordonnees c) {
		for(int j = 0; j<joueurs[numJoueur].nbPingouin; j++) {
			if (joueurs[numJoueur].myPingouins[j].position.equals(c)) {
				return joueurs[numJoueur].myPingouins[j];
			}
		}
		return null;
	}

/*******************************************************************************************************/

	/**
	 * Recupere le ou les joueurs gagants en cas d'egalite
	 *            
	 * @return le ou les joueurs gagants en cas d'egalite.
	 */

	public ArrayList<Joueur> getGagnant() {
		int scoremax = getScoreMax();
		int nbTuilesMax = getNbTuiles(scoremax);
		ArrayList<Joueur> gagnants = new ArrayList<Joueur>();
		
		for(int i = 0; i<nbJoueurs;i++ ) {
			if(joueurs[i].poissonsManges == scoremax && joueurs[i].nbTuiles == nbTuilesMax) {
				gagnants.add(joueurs[i]);
			}
		}
		return gagnants;
	}
	
	/**
	 * Affiche le score de chaque joueur
	 */
	
	public void afficherScores() {
		for(int i = 0; i<nbJoueurs; i++)
			System.out.println(joueurs[i].nom + " : "+joueurs[i].poissonsManges + " points.");
	}

/*******************************************************************************************************/

	/**       
	 * @return le score max parmis tout les joueurs.
	 */
	
	public int getScoreMax() {
		int max =0;
		for(int i = 0; i<nbJoueurs;i++ ) {
			if(joueurs[i].poissonsManges >max) {
				max = joueurs[i].poissonsManges;
			}
		}
		return max;
	}
	
	/**       
	 * @param scoremax
	 *            Le score du ou des gagnant(s).
	 *            
	 * @return le nombre de deplacement max parmis tout les joueurs.
	 */
	
	public int getNbTuiles(int scoremax) {
		int NbTuiles =0;
		for(int i = 0; i<nbJoueurs;i++ ) {
			if(joueurs[i].poissonsManges == scoremax && joueurs[i].nbTuiles > NbTuiles) {
				NbTuiles = joueurs[i].nbTuiles;
			}
		}
		return NbTuiles;
	}

/*******************************************************************************************************/
	
	/**
	 * Verifie si on peut annuler un coup. (Un seul joueur)
	 * 
	 * @return Renvoie vrai, si il n'y a qu'un seul joueur.
	 */
	
	public boolean peutAnnulerCoup() {
		boolean res = false;
		for(int i = 0; i<nbJoueurs; i++) {
			if (this.joueurs[i].getClass() == Humain.class) { 
				if (res) {
					return false;
				} else {
					res = true;
				}
			}
		}
		return res;
	}
	
	/**
	 * Verifie si la partie dispose des fonctions "annuler" et "retablir"
	 * 
	 */

	public void setHistorique() {
		this.utiliseHistorique = peutAnnulerCoup();
		if(utiliseHistorique) {
			this.h = new Historique();
		}
	}
	
	/**
	 * Mets a jour l'historique
	 * ATTENTION : doit etre appele a chaque coup joue
	 */

	public void majHistorique() {
		h.undo.push(this.clone());
		h.redo.clear();
	}
	
/*******************************************************************************************************/

	/**
	 * Affichage.
	 *     .
	 * @return Un affichage de la banquise de cette partie.
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
				Coordonnees c = new Coordonnees(i, j);
				t = b.getTuile(c);
				int app = appartientPingouin(c);
				if (t.aUnPingouin && (app != -1)) {
					s = s + "P" + app + (numligne == 7 ? "| " : " |");

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

