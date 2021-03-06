package model;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * La banquise est le terrain de jeu,
 * elle indique le nombre de poissons sur chaque case
 */	

public class Banquise implements Serializable {

	/**
	 * Tableau du terrain de jeu
	 */
	
	public Tuile[][] terrain;
	
	/**
	 * Nombre d'axes autour d'une tuile
	 */
	
	private final static int NB_AXES = 6;

	/**
	 * Constructeur d'une banquise de taille 8x8, initialisé avec des poissons.
	 */	
	public Banquise(){
		terrain = new Tuile[8][8];
		initBanquise();
	}
	
	public Banquise(BufferedReader br){
		terrain = new Tuile[8][8];
		try {
			recupBanquise(br);
		} catch (IOException e) {
			System.out.println("Erreur : fichier a lire, banquise initialisée aleatoirement");

			initBanquise();
			//e.printStackTrace();
		}
	}
	
	/**
	 * Constructeur d'une banquise de taille 8x8, avec un fichie texte.
	 */	
	public Banquise(String filename){
		terrain = new Tuile[8][8];
		try {
			recupBanquise2(filename);
		} catch (IOException e) {
			System.out.println("Erreur : fichier a lire, banquise initialisée aleatoirement");
			initBanquise();
		}
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
			} else {		//Ligne impair
				numligne = 8; 
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
			if (numligne == 7) {
				this.terrain[i][numligne] = null;
			}
		}
	}
	
	
	public void recupBanquise2(String filename) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		this.recupBanquise(br);
	}
	
	public void recupBanquise(BufferedReader br) throws IOException {
		Tuile t = new Tuile();
		System.out.println("recupBanquise debut");

		//BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		
		String ligne;
		String[] poissons;
		
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

		for (int i = 0; i<8; i++) {

			ligne=br.readLine();
			poissons = ligne.split(" ");
			int numligne = 0;
			int a = 0;

			if (i%2 == 0) { //Ligne pair
				numligne = 7;
			} else {		//Ligne impair
				numligne = 8; 
			}
			for (int j = 0; j < numligne; j++) {
				if (numligne == 7) {
					a = j+1;
				} else {
					a = j;
				}
				
				if (Integer.parseInt(poissons[a]) == 0) {
					this.terrain[i][j] = new Tuile();
					
				} else {
					if (Integer.parseInt(poissons[a]) == 1) {
						t.mettrePoissons(1);
						this.terrain[i][j] = new Tuile(t);

					} else if (Integer.parseInt(poissons[a]) == 2) {
						t.mettrePoissons(2);
						this.terrain[i][j] = new Tuile(t);

					} else if (Integer.parseInt(poissons[a]) == 3) {
						t.mettrePoissons(3);
						this.terrain[i][j] = new Tuile(t);

					} else {
						System.out.println("Erreur : Nombre de poisson sur la case "+i+" "+j);
					} 
				}
			}/*
			if (numligne == 7) {
				this.terrain[i][7] = null;
			}*/
		}
		//br.close();
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
	 * Renvoie les coordonnees du voisin coder par l'axe d'un point: 0->HD, 1->MD, 2->BD, 3->BG, 4->MG, 5->HG
	 * 
	 * @param c
	 *            Position de depart.
	 * @param axe
	 * 			  Code de l'axe choisi
	 * @return Les coordonnees de son voisin.
	 */	
	
	public Coordonnees getVoisin(int axe, Coordonnees c) {
		switch(axe) {
		
			// AXE HAUT DROIT
			case 0: {
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
		
			// AXE MILIEU DROIT
			case 1: {
				if(boutLigne(c)) { //pas de voisin milieu droit
					return null;
				} else { //voisin
					return new Coordonnees(c.x, c.y+1);
				}
			}
		
			// AXE BAS DROIT
			case 2: {
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
			
			// AXE BAS GAUCHE
			case 3: {
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
			
			// AXE MILIEU GAUCHE
			case 4: {
				if(debutLigne(c)) { //pas de voisin milieu gauche
					return null;
				} else { //voisin
					return new Coordonnees(c.x, c.y-1);
				}
			}
			
			// AXE HAUT GAUCHE
			case 5: {
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
			default: {
				return null;
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
		Tuile t[] = new Tuile[NB_AXES];
		for(int i = 0; i<NB_AXES; i++) {
			t[i] = getTuile(getVoisin(i,p.position));
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
		boolean noye = true;
		for(int i=0;i<NB_AXES && noye;i++) {
			noye = noye && estNoyeSurAxe(i, p.position);
		}
		return noye;
	}
	
	/**
	 * Renvoie un boulean indiquant si la prochaine tuile sur un axe donne est accessible.
	 * 
	 * @param c
	 *            Le pingouin que l'on test.
	 * @param axe
	 * 			  Code de l'axe choisi
	 * 
	 * @return Vrai si le pingouin est entoure de case avec 0 poisson, ou innaccessible.
	 */
	public boolean estNoyeSurAxe(int axe, Coordonnees c) {
		Tuile t = getTuile(getVoisin(axe,c));
		return (t==null || t.nbPoissons==0);
	}

/*******************************************************************************************************/

	/**
	 * Renvoie la liste de points en partant de dep, et en allant dans tout les sens.
	 * 
	 * @param p
	 *            Le pingouin qui veut se deplacer.
	 * @return Un ensemble de points d'arrives possible.
	 */

	public ArrayList<ArrayList<Coordonnees>> deplacementPossible(Pingouin p) {
		return deplacementPossible(p.position);
	}
	
	/**
	 * Renvoie la liste de points en partant de dep, et en allant dans tout les sens.
	 * 
	 * @param c
	 *            c la coordonnee du pingouin qu'on veut deplacer
	 * @return Un ensemble de points d'arrives possible.
	 */

	public ArrayList<ArrayList<Coordonnees>> deplacementPossible(Coordonnees c) {
		ArrayList<ArrayList<Coordonnees>> chemins = new ArrayList<ArrayList<Coordonnees>>();
		for(int i = 0; i<NB_AXES;i++) {
			chemins.add(construireChemin(i,c));
		}
		return chemins;
	}
	
	/**
	 * Renvoie la liste de points en partant de dep, et en allant dans le sens de la fonction f.
	 * 
	 * @param axe
	 *          int qui code l'axe choisi
	 * @param dep
	 * 			Point de départ.
	 * 
	 * @return Une liste de point.
	 */
	
	public ArrayList<Coordonnees> construireChemin(int axe, Coordonnees dep) {
		ArrayList<Coordonnees> chemin = new ArrayList<>();
		Coordonnees next = getVoisin(axe, dep);
		
		while (next!=null && getTuile(next)!=null && getTuile(next).estAccessible()) {
			chemin.add(next);
			next = getVoisin(axe, next);
		}

		return chemin;
	}
	
	/**
	 * Renvoie le string affichant tout les chemins et leur contennu
	 * 
	 * @param chemins
	 *          les 6 chemins accessibles
	 * 
	 * @return le string affichant tout les chemins
	 */
	
	public String cheminsToString(ArrayList<ArrayList<Coordonnees>> chemins) {
		String s="Coordonnées accessibles:\n";
		for(int i=0; i<NB_AXES;i++) {
			s+=stringChemin(chemins.get(i), i)+"\n";
		}
		return s;
	}
	
	/**
	 * Renvoie le string affichant le chemin numAxe
	 * 
	 * @param chemin
	 *          le chemins accessibles
	 * @param numAxe
	 *          l'entier codant le numero du chemin
	 * 
	 * @return le string affichant le contennu du chemin
	 */
	public String stringChemin(ArrayList<Coordonnees> chemin, int numAxe) {
		String s = String.valueOf(numAxe+1)+") Axe "+getNomAxe(numAxe);
		for(int i =0; i< chemin.size(); i++) {
			s+="\t"+String.valueOf(i+1)+": "+chemin.get(i).toString();
		}
		return s;
	}
	
	/**
	 * Renvoie le string affichant le nomde l'axe
	 * 
	 * @param numAxe
	 *          l'entier codant le numero du chemin
	 * 
	 * @return le string affichant le nom de l'axe
	 */
	public String getNomAxe(int axe) {
		switch(axe) {
			case 0: return "Haut Droit";
			case 1: return "Milieu Droit";
			case 2: return "Bas Droit";
			case 3: return "Bas Gauche";
			case 4: return "Milieu Gauche";
			case 5: return "Haut Gauche";
			default: return null;
		}
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

	/**
	 * Verifie si deux Banquise sont egales.
	 *     .
	 * @return this <=> obj.
	 */
	
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