package model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Class Partie : cree et gere la partie
 */

public class Partie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		this.b = new Banquise();
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
		this.nbJoueurs = j.length;
		this.joueurs = new Joueur[this.nbJoueurs];
    	for (int k = 0; k<this.nbJoueurs; k++) {
    		joueurs[k] = j[k];
    	}
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
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name+".banquise"));
			oos.writeObject(this);
			if(utiliseHistorique) {
				h.sauvegarder(name);
			}
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Sauvegarde la partie en cours dans le fichier "no_name.banquise" par default.
	 */
	
	public void sauvegarder() {
		sauvegarder("/home/a/aulagnco/git/ProjetBanquise/save1/no_name");
	}
	
	/**
	 * Charge la partie en parametre. (Avec Serializable)
	 * 
	 * @param name
	 *            Nom du fichier a recuperer
	 */
	
	public void charger(String name) {
		try {
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(name)) ;		
			Partie p = (Partie)ois.readObject() ;
			this.b = p.b;
			this.joueurActif = p.joueurActif;
			this.nbJoueurs = p.nbJoueurs;
			this.joueurs = p.joueurs;
			//this.utiliseHistorique = p.utiliseHistorique;
			this.setHistorique();// = p.setHistorique();

			if(utiliseHistorique) {
				this.h.charger(name.substring(0, name.length()-9)+".historique");
				
			}
			ois.close();
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
	 * Charge la partie en parametre. (Sans Serializable)
	 * 
	 * @param name
	 *            Nom du fichier a recuperer
	 */
	
	public void chargerTXT(String namePartie) {
		try {
			String ligne;
			String[] elements;
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(namePartie)));

			/* Fichier a lire
			 * 
			 * | 1 2 3 1 2 3 1 |
			 * |1 2 3 1 2 3 1 2|
			 * | 1 2 3 1 2 3 1 |
			 * |1 2 3 1 2 3 1 2|
			 * | 1 2 3 1 2 3 1 |
			 * |1 2 3 1 2 3 1 2|
			 * | 1 2 3 1 2 3 1 |
			 * 
			 */

			this.b = new Banquise(br);

			ligne=br.readLine();
			System.out.println(ligne);

			elements=ligne.split(" ");
			this.nbJoueurs = Integer.parseInt(elements[1]);

			ligne=br.readLine();
			elements=ligne.split(" ");
			this.joueurActif = Integer.parseInt(elements[1]);
			
			this.joueurs = recupererJoueurs(br, nbJoueurs);
			
			this.utiliseHistorique = false;
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Erreur : FileNotFoundException "+ namePartie + " introuvables");
			
		} catch (Exception e) {
			System.out.println("Erreur : recuperation de la partie");
		}
	}

	/**
	 * recupere les joueurs d'une partie.
	 * 
	 * @param br
	 *            le BufferedReader
	 * @param nbJoueurs
	 *            le nombre de joueurs
	 *            
	 * @return le tableau des joueurs.
	 * @throws IOException 
	 */
	
	private Joueur[] recupererJoueurs(BufferedReader br, int nbJoueurs) throws IOException {
		Joueur[] j = new Joueur[nbJoueurs];
		
		String ligne;
		String[] elements;
		
		for(int i=0; i<nbJoueurs; i++) {
			ligne=br.readLine();
			elements=ligne.split(" ");
			if (elements[0].equals("IA")) {		//IA
				j[i] = new IA(elements[8], Integer.parseInt(elements[6]), Integer.parseInt(elements[14]), elements[12], Integer.parseInt(elements[10]));
				
			} else {	//Humain
				j[i] = new Humain(elements[8], Integer.parseInt(elements[6]), elements[12], Integer.parseInt(elements[10]));
			}
			j[i].poissonsManges = Integer.parseInt(elements[4]);
			j[i].nbTuiles = Integer.parseInt(elements[2]);
			j[i].myPingouins = recupererPingouins(br, j[i].nbPingouin);
			//ligne=br.readLine();

		}
		return j;
	}

	/**
	 * recupere les pingouins d'un joueur.
	 * 
	 * @param br
	 *            le BufferedReader
	 * @param nbPingouin
	 *            le nombre de pingouins
	 *            
	 * @return le tableau de ses pingouins.
	 * @throws IOException 
	 */
	
	private Pingouin[] recupererPingouins(BufferedReader br, int nbPingouin) throws IOException {
		Pingouin[] p = new Pingouin[nbPingouin];
		
		String ligne;
		String[] elements;
		
		for(int i=0; i<nbPingouin; i++) {
			ligne=br.readLine();
			elements=ligne.split(" ");
			
			p[i] = new Pingouin();
			//new Coordonnees(Integer.parseInt(elements[2].substring(1, 2)), Integer.parseInt(elements[3].substring(0, 1))));
			if (elements[4].equals("actif")) {
				Coordonnees c = new Coordonnees(Integer.parseInt(elements[2].substring(1, 2)), Integer.parseInt(elements[3].substring(0, 1)));
				this.b.getTuile(c).mettrePingouin();
				p[i].position = c;
				p[i].actif = true;
			}
		}	
		return p;
	}
	
	/**
	 * recupere je joueur actif.
	 * 
	 * @return le joueur actif.
	 */
	
	public Joueur getJoueurActif() {
		return joueurs[joueurActif] ;
	}


/*******************************************************************************************************/

	/**
	 * Mets a jour le prochain joueur de la partie
	 */

	public void majProchainJoueur() {
		int i = 0;
		int sauvJoueur= this.joueurActif;
		this.joueurActif = (this.joueurActif+1)%this.nbJoueurs;
		
		while (i < 8 && this.nbPingouinActif() == 0) {
			this.joueurActif = (this.joueurActif+1)%this.nbJoueurs;
			i ++;
		}
		if (i==8) {
			this.joueurActif = sauvJoueur;
		}
	}
	
	/**
	 * Verifie que l'on peut placer un pingouin a cette case
	 * 
	 * @param c
	 * 			Les coordonnees de la case a tester
	 * 
	 * @return si on peut placer un pingouin
	 */
	
	public boolean isPlacementValide(Coordonnees c) {
		Tuile t = this.b.getTuile(c);
		if(t!= null && t.nbPoissons==1 && !t.aUnPingouin) return true;
		else return false;
	}
	
	/**
	 * Verifie que l'on peut deplacer un pingouin a cette case
	 * 
	 * @param cc
	 * 			Le couple de coordonnees des cases a tester
	 * 
	 * @return si on peut placer un pingouin
	 */
	
	public boolean isDeplacementValide(CoupleGenerique<Coordonnees,Coordonnees> cc) {
			return isDeplacementValide(cc.e1, cc.e2);
	}
	
	/**
	 * Verifie que l'on peut deplacer un pingouin a cette case
	 * 
	 * @param c1
	 * 			Les coordonnees de depart
	 * @param c2
	 * 			Les coordonnees d'arrive
	 * 
	 * @return si on peut placer un pingouin
	 */
	
	public boolean isDeplacementValide(Coordonnees c1, Coordonnees c2) {
		ArrayList<ArrayList<Coordonnees>> al = b.deplacementPossible(c1);
		return appartient(c2, al);
	}
	
	/**
	 * regarde si c appartient a al
	 * 
	 * @param c
	 * 			Ccoordonnees a retrouver
	 * @param al
	 * 			Dans cette liste
	 * 
	 * @return si c appartient a al
	 */
	
	private boolean appartient(Coordonnees c, ArrayList<ArrayList<Coordonnees>> al) {
		Iterator<ArrayList<Coordonnees>> itAl = al.iterator();
		ArrayList<Coordonnees> ac = null;
		
		Iterator<Coordonnees> itC = null;
		Coordonnees co = null;
		
		while (itAl.hasNext()) {
			ac = itAl.next();
			itC = ac.iterator();
			
			while (itC.hasNext()) {
				co = itC.next();
				if (co.equals(c)) {
					return true;
				}
			}
		}
		return false;
	}
	
/*******************************************************************************************************/

	/**
	 * Verifie si la partie est termine.
	 * 
	 * @return Renvoie vrai, si tout les pingouins de tout les joueurs sont bloquees.
	 */
	
	public boolean estPartieFini() {
		return (placementPingouinsFini() && jeuPingouinsFini() );
	}
	
	/**
	 * Verifie si la phase de jeu est fini.
	 * 
	 * @return Renvoie vrai, si on a fini la phase de jeu.
	 */
	
	public boolean jeuPingouinsFini() {
		for(int i = 0; i<nbJoueurs; i++)
			for(int j = 0; j<joueurs[i].nbPingouin; j++)
				if (joueurs[i].myPingouins[j].actif || joueurs[i].myPingouins[j].position.equals(new Coordonnees(-1, -1)))
					return false;
		return true;
	}
	
	/**
	 * Verifie si tout les pingouins ont ete posés.
	 * 
	 * @return Renvoie vrai, si on a fini de poser les pingouins.
	 */
	
	public boolean placementPingouinsFini() {
		for(int i = 0; i<nbJoueurs; i++)
			for(int j = 0; j<joueurs[i].nbPingouin; j++)
				if (!(joueurs[i].myPingouins[j].actif) && joueurs[i].myPingouins[j].position.equals(new Coordonnees(-1, -1)))
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
	
	public void setPlacementPingouin(Coordonnees c, int numJoueur, int numPingouin) {
		this.b.getTuile(c).mettrePingouin();
		this.joueurs[numJoueur].myPingouins[numPingouin] = new Pingouin(c);
		System.out.println("Le pingouin " + numPingouin+ " de " + this.joueurs[numJoueur].nom +" a bien été positionné en "+c+".");
	}

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
	 * Deplace un pingouin de "cg.e1" vers "cg.e2"
	 * 	 
	 * @param cg
	 *            Le CoupleGenerique de coordonnees correspondant au deplacement d'un pingouin.
	 */
	
	public void deplacement(CoupleGenerique<Coordonnees,Coordonnees> cg) {
		deplacement(cg.e1, cg.e2);
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
		
		if (this.utiliseHistorique && this.getJoueurActif() instanceof Humain) {
			this.h.undo.push(this.clone());
			this.h.redo.clear();
			
		}
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

/*******************************************************************************************************/

	/**
	 * Retourne le numero du pingouin a placer
	 * 
	 * @param j
	 *            Le joueur.
	 *            
	 * @return Retourne le numero du pingouin a placer du joueur j.
	 */


	public int numPingouinAPlacer(Joueur j) {
		Coordonnees[] c = positionPingouins(j);
		int i;
		for(i = 0; i < c.length; i++) {
			if (c[i] == null)
				return i;
		}
		
		return -1;
	}
	
	/**
	 * Retourne le numero du pingouin a placer du joueur actif
	 * 
	 * @param j
	 *            Le joueur.
	 *            
	 * @return Retourne le numero du pingouin a placer du joueur actif.
	 */


	public int numPingouinAPlacer() {
		return numPingouinAPlacer(joueurs[joueurActif]);
	}
	
	/**
	 * Retourne combien de pingouins le joueur j peut deplacer
	 * 
	 * @param j
	 *            Le joueur.
	 *            
	 * @return Retourne le nb de pingouins deplacable du joueur j.
	 */


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
					joueurs[i].nbTuiles ++;
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
	
	public int rechercheNumPingouin(int numJoueur, Coordonnees c) {
		for(int j = 0; j<joueurs[numJoueur].nbPingouin; j++) {
			if (joueurs[numJoueur].myPingouins[j].position.equals(c)) {
				return j;
			}
		}
		return -1;
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
			System.out.println(joueurs[i].nom + " : "+joueurs[i].poissonsManges + " points et "+ joueurs[i].nbTuiles + " tuiles.");
	}
	
	/**
	 * Renvoie le score du joueur j
	 * 
	 * @param j
	 * 			le joueur
	 * 
	 *  @return le score du joueur J (poissons, tuiles)
	 */
	
	public Coordonnees score(Joueur j) {
		return new Coordonnees(j.poissonsManges, j.nbTuiles);
	}
	
	/**
	 * Renvoie le score de tous les joueurs
	 * 
	 *  @return le score de tous les joueurs (poissons, tuiles)
	 */
	
	public Coordonnees[] scores() {
		Coordonnees[] c = new Coordonnees[this.nbJoueurs];
		
		for (int i = 0; i< this.nbJoueurs; i++) {
			c[i] = score(this.joueurs[i]);
		}
		
		return c;
	}
	
	/**
	 * Retourne le score de chaque joueur en string
	 */
	
	public String scoresToString() {
		String s = "";
		for(int i = 0; i<nbJoueurs; i++)
			s+=joueurs[i].nom + " : "+joueurs[i].poissonsManges + " points et "+ joueurs[i].nbTuiles + " tuiles.\n";
		return s;
	}

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
	
	/**       
	 * @return un tableau de joueurs trie par classement a la fin de la partie.
	 */
	
	public Joueur[] classement() {
		Joueur[] jo = joueurs.clone();
		
		Joueur temp;

		int joueurMax = 0;
		int scoreMax = 0;
		int nbTuilesMax = 0;
		
		
		for(int i = 0; i<nbJoueurs;i++ ) {
			
			scoreMax = joueurs[i].poissonsManges;
			joueurMax = i;
			nbTuilesMax = joueurs[i].nbTuiles;

			for(int j = i; j<nbJoueurs;j++ ) {
				if (joueurs[j].poissonsManges > scoreMax || (joueurs[j].poissonsManges == scoreMax && joueurs[j].nbTuiles > nbTuilesMax)) {
					scoreMax = joueurs[j].poissonsManges;
					joueurMax = j;
					nbTuilesMax = joueurs[j].nbTuiles;
				}
			}

			temp = jo[i];
			jo[i] = jo[joueurMax];
			jo[joueurMax] = temp;

		}
		return jo;
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
	
	/**
	 * Affichage2.
	 *     .
	 * @return Un affichage de la banquise et des composants de cette partie.
	 */
	
	public String toString2() {
		String s = this.toString();	
		
		s = s + "nbJoueurs "+ nbJoueurs + "\n";
		s = s + "joueurActif "+ joueurActif + "\n";

		for (int i=0; i< nbJoueurs; i++) {
			s = s + joueurs[i] + "\n";
		}
		
		return s;
	}
	
	/**
	 * Verifie si deux Partie sont egales.
	 *     .
	 * @return this egale à obj.
	 */

	public boolean equals(Partie obj) {
		if (joueurActif != obj.joueurActif) 
			return false;
		if (nbJoueurs != obj.nbJoueurs) 
			return false;
		if (!b.equals(obj.b)) {
			return false;
		}
		for (int i =0; i<nbJoueurs ; i++) {
			if (!joueurs[i].equals(obj.joueurs[i]))
				return false;
		}
		return true;
	}
}

