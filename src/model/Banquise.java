package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

/**
 * La banquise est le terrain de jeu,
 * elle indique le nombre de poissons sur chaque case
 */	

public class Banquise implements Serializable, Cloneable{
	/**
	 * Tableau du terrain de jeu
	 */
	
	public Tuile[][] terrain;

	/**
	 * Constructeur d'une banquise de taille 8x8, initialisé avec des poissons.
	 */	
	public Banquise(){
		terrain = new Tuile[8][8];
		initBanquise();
	}

	/**
	 * Initialise la banquise avec des valeurs de poissons aleatoire.
	 */	

/*******************************************************************************************************/

	public void initBanquise() {
		int unPoisson = 30;
		int deuxPoissons = 20;
		int troisPoissons = 10;
		int nbCasesRestante = 60;
		int alea = 0;
		int numligne;
		
		Tuile t = new Tuile();
		Random r = new Random();
		
		for (int i = 0; i<8; i++) {

			if (i%2 == 0) { //Ligne pair
				numligne = 7;
			} else {
				numligne = 8; //Ligne impair
			}

			for (int j = 0; j < numligne; j++) {
				alea = r.nextInt(nbCasesRestante);
				
				if (alea < unPoisson) {
					t.mettrePoissons(1);
					this.terrain[i][j] = new Tuile(t);
					unPoisson --;

					
				} else if (alea < (unPoisson + deuxPoissons)) {
					t.mettrePoissons(2);
					this.terrain[i][j] = new Tuile(t);
					deuxPoissons --;

					
				} else if (alea < (unPoisson + deuxPoissons + troisPoissons)) {
					t.mettrePoissons(3);
					this.terrain[i][j] = new Tuile(t);
					troisPoissons --;

				} else {
					System.out.println("Erreur : Initialisation banquise");
				}

				nbCasesRestante --;
			}
		}
	}

/*******************************************************************************************************/

	/**
	 * Renvoie la toile en memoire aux coordonnees c.
	 * 
	 * @param c
	 *            Position à recuperer.
	 * @return Un tuile.
	 */	
	
	public Tuile getTuile(Coordonnees c) {
		try {
			return this.terrain[c.x][c.y];
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Pose la tuile t à la position c.
	 * 
	 * @param c
	 *            Position à modifier.
	 * @param t
	 *            tuile a copier en memoire.
	 */
	
	public void setTuile(Coordonnees c, Tuile t) {
		this.terrain[c.x][c.y].nbPoissons = t.nbPoissons;
		this.terrain[c.x][c.y].aUnPingouin = t.aUnPingouin;
	}

/*******************************************************************************************************/

	/**
	 * Indique si le point est sur une ligne paire.
	 * 
	 * @param c
	 *            Position à tester.
	 * @return Vrai si le point est sur une ligne paire.
	 */	
	
	public boolean lignePair(Coordonnees c) {
		return (c.x%2==0);
	}
	
	/**
	 * Indique si le point est à la fin de la ligne.
	 * 
	 * @param c
	 *            Position à tester.
	 * @return Vrai si le point est à la fin de la ligne.
	 */	
	
	public boolean boutLigne(Coordonnees c) {
		return ((lignePair(c) && c.y == 6) || (!lignePair(c) && c.y == 7));
	}
	
	/**
	 * Indique si le point est au debut de la ligne.
	 * 
	 * @param c
	 *            Position à tester.
	 * @return Vrai si le point est au debut de la ligne.
	 */	
	
	public boolean debutLigne(Coordonnees c) {
		return (c.y == 0);
	}
	
	/**
	 * Indique si le point est à la premiere ligne.
	 * 
	 * @param c
	 *            Position à tester.
	 * @return Vrai si le point est à la premiere ligne.
	 */	
	
	public boolean premiereLigne(Coordonnees c) {
		return (c.x == 0);
	}
	
	/**
	 * Indique si le point est à la derniere ligne.
	 * 
	 * @param c
	 *            Position à tester.
	 * @return Vrai si le point est à la derniere ligne.
	 */	
	
	public boolean derniereLigne(Coordonnees c) {
		return (c.x == 7);
	}

/*******************************************************************************************************/

	/**
	 * Renvoie les coordonnees du voisin haut droit d'un point.
	 * 
	 * @param c
	 *            Position de depart.
	 * @return Les coordonnees de son voisin.
	 */	
	
	public Coordonnees getHD(Coordonnees c) {
		if(premiereLigne(c) || (boutLigne(c) && !lignePair(c))) { //pas de voisin haut droit
			return null;
		} else { //voisin
			if(lignePair(c)) { //ligne pair
				return new Coordonnees(c.x-1, c.y+1);
			} else { //ligne impair
				return new Coordonnees(c.x-1, c.y);
			}		
		}
	}
	
	/**
	 * Renvoie les coordonnees du voisin haut gauche d'un point.
	 * 
	 * @param c
	 *            Position de depart.
	 * @return Les coordonnees de son voisin.
	 */	
	
	public Coordonnees getHG(Coordonnees c) {
		if(premiereLigne(c) || (debutLigne(c) && !lignePair(c))) { //pas de voisin haut gauche
			return null;
		} else { //voisin
			if(lignePair(c)) { //ligne pair
				return new Coordonnees(c.x-1, c.y);
			} else { //ligne impair
				return new Coordonnees(c.x-1, c.y-1);
			}		
		}
	}
	
	/**
	 * Renvoie les coordonnees du voisin milieu droit d'un point.
	 * 
	 * @param c
	 *            Position de depart.
	 * @return Les coordonnees de son voisin.
	 */	
	
	public Coordonnees getMD(Coordonnees c) {
		if(boutLigne(c)) { //pas de voisin milieu droit
			return null;
		} else { //voisin
			return new Coordonnees(c.x, c.y+1);
		}
	}
	
	/**
	 * Renvoie les coordonnees du voisin milieu gauche d'un point.
	 * 
	 * @param c
	 *            Position de depart.
	 * @return Les coordonnees de son voisin.
	 */	
	
	public Coordonnees getMG(Coordonnees c) {
		if(debutLigne(c)) { //pas de voisin milieu gauche
			return null;
		} else { //voisin
			return new Coordonnees(c.x, c.y-1);
		}
	}
	
	/**
	 * Renvoie les coordonnees du voisin bas droit d'un point.
	 * 
	 * @param c
	 *            Position de depart.
	 * @return Les coordonnees de son voisin.
	 */	
	
	public Coordonnees getBD(Coordonnees c) {
		if(derniereLigne(c) || (boutLigne(c) && !lignePair(c))) { //pas de voisin bas droit
			return null;
		} else { //voisin
			if(lignePair(c)) { //ligne pair
				return new Coordonnees(c.x+1, c.y+1);
			} else { //ligne impair
				return new Coordonnees(c.x+1, c.y);
			}		
		}
	}
	
	/**
	 * Renvoie les coordonnees du voisin bas gauche d'un point.
	 * 
	 * @param c
	 *            Position de depart.
	 * @return Les coordonnees de son voisin.
	 */	
	
	public Coordonnees getBG(Coordonnees c) {
		if(derniereLigne(c) || (debutLigne(c) && !lignePair(c))) { //pas de voisin bas gauche
			return null;
		} else { //voisin
			if(lignePair(c)) { //ligne pair
				return new Coordonnees(c.x+1, c.y);
			} else { //ligne impair
				return new Coordonnees(c.x+1, c.y-1);
			}		
		}
	}

/*******************************************************************************************************/

	/**
	 * Renvoie un boulean indiquant si un pingouin peut bouger.
	 * 
	 * @param p
	 *            Le pingouin que l'on test.
	 * @return Vrai si le pingouin ne peut plus bouger.
	 */
	
	public boolean nePeutPlusBouger(Pingouin p) {
		return ( estBloque(p) || estNoye(p) );
	}

	/**
	 * Renvoie un boulean indiquant si un pingouin est bloque.
	 * 
	 * @param p
	 *            Le pingouin que l'on test.
	 * @return Vrai si le pingouin est entoure d'eau, et d'un pingouin.
	 */
	
	public boolean estBloque(Pingouin p) { //p est cerné par de l'eau et au moins un autre pingouin
		boolean voisinPingouin = false;
		boolean pasDePoisson = true;
		Tuile t[] = new Tuile[6];
		t[0] = getTuile(getHD(p.position));
		t[1] = getTuile(getMD(p.position));
		t[2] = getTuile(getBD(p.position));
		t[3] = getTuile(getBG(p.position));
		t[4] = getTuile(getMG(p.position));
		t[5] = getTuile(getHG(p.position));
		for(int i = 0; i<6; i++) {
			if (t[i]==null || t[i].aUnPingouin) {
					voisinPingouin=true;
			} else if(t[i].nbPoissons!=0) {
					pasDePoisson = false;
			}
			
		}
		return (voisinPingouin && pasDePoisson);
	}
	
	/**
	 * Renvoie un boulean indiquant si un pingouin est noye.
	 * 
	 * @param p
	 *            Le pingouin que l'on test.
	 * @return Vrai si le pingouin est entoure de case avec 0 poisson, ou innaccessible.
	 */
	
	public boolean estNoye(Pingouin p) {
		return (
				((getTuile(getHD(p.position))==null) || (getTuile(getHD(p.position)).nbPoissons==0))
				&& ((getTuile(getMD(p.position))==null) || (getTuile(getMD(p.position)).nbPoissons==0))
				&& ((getTuile(getBD(p.position))==null) || (getTuile(getBD(p.position)).nbPoissons==0))
				&& ((getTuile(getBG(p.position))==null) || (getTuile(getBG(p.position)).nbPoissons==0))
				&& ((getTuile(getMG(p.position))==null) || (getTuile(getMG(p.position)).nbPoissons==0))
				&& ((getTuile(getHG(p.position))==null) || (getTuile(getHG(p.position)).nbPoissons==0))
		);

	}

/*******************************************************************************************************/

	/**
	 * Renvoie la liste de points en partant de dep, et en allant dans tout les sens.
	 * 
	 * @param p
	 *            Le pingouin qui veut se deplacer.
	 * @return Un ensemble de points d'arrivés possible.
	 */

	public ArrayList<ArrayList<Coordonnees>> deplacementPossible(Pingouin p) {
		ArrayList<ArrayList<Coordonnees>> chemins = new ArrayList<ArrayList<Coordonnees>>();
		//Function<Coordonnees, Coordonnees> HD = c -> getHD(c);
		chemins.add(construireChemin(c -> getHD(c),p.position));
		chemins.add(construireChemin(c -> getMD(c),p.position));
		chemins.add(construireChemin(c -> getBD(c),p.position));
		chemins.add(construireChemin(c -> getBG(c),p.position));
		chemins.add(construireChemin(c -> getMG(c),p.position));
		chemins.add(construireChemin(c -> getHG(c),p.position));
		return chemins;
	}
	
	/**
	 * Renvoie la liste de points en partant de dep, et en allant dans le sens de la fonction f.
	 * 
	 * @param f
	 *          Une fonction de deplacement.
	 * @param dep
	 * 			Point de départ.
	 * 
	 * @return Une liste de point.
	 */
	
	public ArrayList<Coordonnees> construireChemin(Function<Coordonnees, Coordonnees> f, Coordonnees dep) {
		ArrayList<Coordonnees> chemin = new ArrayList<>();
		Coordonnees next = f.apply(dep);
		while(next!=null && getTuile(next).estAccessible()) {
			chemin.add(next);
			next = f.apply(next);
		}
		return chemin;
	}

/*******************************************************************************************************/

	/**
	 * @return le nombre de tuiles vides de la banquise
	 */	
	
	public int nbTuilesLibres() {
		int nbTuiles = 0;
		int numligne = 0;
		
		for (int i = 0; i<8; i++) {

			if (i%2 == 0) { //Ligne pair
				numligne = 7;
			} else {
				numligne = 8; //Ligne impair
			}

			for (int j = 0; j < numligne; j++) {
				if (this.terrain[i][j].nbPoissons > 0) {
					nbTuiles ++;
				}
			}
		}
		return nbTuiles;
	}

/*******************************************************************************************************/
	
	/**
	 * Affichage.
	 *     .
	 * @return Un affichage de la banquise.
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
				t = getTuile(new Coordonnees(i, j));
				s = s + (numligne == 7 ? " " : "") + (t.nbPoissons == 0 ? " " : t.nbPoissons)+ (numligne == 7 ? "| " : "  |");
	
			}
			if (numligne == 7) {
				s = s + "  |";
			}
			s = s + "\n";
		}
		s = s + "----------------------------------|\n";

		return s;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banquise other = (Banquise) obj;
		int numligne = 0;
		for (int i = 0; i<8; i++) {
			if (i%2 == 0) { //Ligne pair
				numligne = 7;
			} else {
				numligne = 8; //Ligne impair
			}

			for (int j = 0; j < numligne; j++) {
				if (!this.terrain[i][j].equals(other.terrain[i][j]))
					return false;
			}
		}
		return true;
	}
	
}

