package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Banquise {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Tuile[][] terrain;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Banquise(){
		terrain = new Tuile[8][8];
		initBanquise();
	}

	/**
	 * Initialise la banquise avec les poissons
	 */
	
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
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Tuile getTuile(Coordonnees c) {
		return this.terrain[c.x][c.y];
	}
	
	public void setTuile(Coordonnees c, Tuile t) {
		this.terrain[c.x][c.y].nbPoissons = t.nbPoissons;
		this.terrain[c.x][c.y].aUnPingouin = t.aUnPingouin;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean lignePair(Coordonnees c) {
		return (c.x%2==0);
	}
	
	public boolean boutLigne(Coordonnees c) {
		return ((lignePair(c) && c.y == 6) || (!lignePair(c) && c.y == 7));
	}
	
	public boolean debutLigne(Coordonnees c) {
		return (c.y == 0);
	}
	
	public boolean premiereLigne(Coordonnees c) {
		return (c.x == 0);
	}
	
	public boolean derniereLigne(Coordonnees c) {
		return (c.x == 7);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
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
	
	public Coordonnees getMD(Coordonnees c) {
		if(boutLigne(c)) { //pas de voisin milieu droit
			return null;
		} else { //voisin
			return new Coordonnees(c.x, c.y+1);
		}
	}
	
	public Coordonnees getMG(Coordonnees c) {
		if(debutLigne(c)) { //pas de voisin milieu gauche
			return null;
		} else { //voisin
			return new Coordonnees(c.x, c.y-1);
		}
	}
	
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
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean nePeutPlusBouger(Pingouin p) {
		return ( estBloque(p) || estNoye(p) );
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
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
			if(t[i]!=null) {	
				if (t[i].aUnPingouin) {
						voisinPingouin=true;
				} else if(t[i].nbPoissons!=0) {
						pasDePoisson = false;
				}
			}
		}
		return (voisinPingouin && pasDePoisson);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean estNoye(Pingouin p) {
		return (
				(getTuile(getHD(p.position)).nbPoissons==0)
				&& (getTuile(getMD(p.position)).nbPoissons==0)
				&& (getTuile(getBD(p.position)).nbPoissons==0)
				&& (getTuile(getBG(p.position)).nbPoissons==0)
				&& (getTuile(getMG(p.position)).nbPoissons==0)
				&& (getTuile(getHG(p.position)).nbPoissons==0)
		);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
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
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Coordonnees> construireChemin(Function<Coordonnees, Coordonnees> f, Coordonnees dep) {
		ArrayList<Coordonnees> chemin = new ArrayList<>();
		Coordonnees next = f.apply(dep);
		while(next!=null) {
			chemin.add(next);
			next = f.apply(next);
		}
		return chemin;
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
}

