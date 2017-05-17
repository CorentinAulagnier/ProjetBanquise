package model;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import model.Coordonnees;

public class Test extends MoteurConsole{

	/**
	 * Test l'affichage de la banquise
	 */
	
	public static void afficherBanquise(){
		Banquise b = new Banquise();
		System.out.println(b);
	}

	/**
	 * Test l'affichage de la position d'un pingouin
	 * 
	 * @param x
	 *            coordonnee.x.
	 * @param y
	 *            coordonnee.y.
	 */
	
	public static void afficherPosPingouin(int x, int y){
		Coordonnees c = new Coordonnees(x, y);
    	//System.out.println("c : "+c);
		Pingouin p = new Pingouin(c);
		System.out.println(p);
	}

	/**
	 * Test l'affichage des voisin de la position (x, y) dans l'axe z :
	 *	- 1 : Haut Droit
	 *	- 2 : Milieu Droit
	 *	- 3 : Bas Droit
	 *	- 4 : Bas Gauche
	 *	- 5 : Milieu Gauche
	 *	- 6 : Haut Gauche
	 * 
	 * @param x
	 *            coordonnee.x.
	 * @param y
	 *            coordonnee.y.
	 * @param z
	 *            Deplacement choisi.
	 */
	
	public static void afficherVoisin(int x, int y, int z){
		Banquise b = new Banquise();
		Coordonnees c = new Coordonnees(x, y);
		System.out.print(c + " -> ");
		if(z>=0 && z<=5) {
			System.out.println(b.getVoisin(z,c));
		} else {
			System.out.println("Voisin demande non reconnu");
		}
	}
	
/*******************************************************************************************************/

	/**
	 * Main
	 * 
	 * @param args
	 *            arguments du main.
	 */
	
	public static void main(String[] args) {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true){
			try {
				int x = 0;
	        	System.out.println("Liste des tests : ");
	        	System.out.println("   - 1 -> affiche une banquise");
	        	System.out.println("   - 2 -> affiche position d'un pingouin");
	       		System.out.println("   - 3 -> affiche la position d'un voisin");
	       		System.out.println("   - 4 -> affiche une partie");
	       		System.out.println("   - 5 -> sauvegarder et charger une partie");
	       		System.out.println("   - 6 -> création d'un clone");
	       		System.out.println("   - 7 -> charger une partie en txt");


	        	x = Integer.parseInt(br.readLine());
	        	int y;
	        	int z;

				switch (x) {
					case 1:
						afficherBanquise();
						break;
						
					case 2: 
			        	System.out.println("Coordonnees du pingouin : ");
			        	System.out.print("x : ");
			        	x = Integer.parseInt(br.readLine());
			        	System.out.print("y : ");
			        	y = Integer.parseInt(br.readLine());
						afficherPosPingouin(x, y);
						break;
						
					case 3: 
						System.out.println("Coordonnees : ");
			        	System.out.print("x : ");
			        	x = Integer.parseInt(br.readLine());
			        	System.out.print("y : ");
			        	y = Integer.parseInt(br.readLine());
			        	System.out.println("Quel voisin voulez vous : ");
			        	System.out.println("	- 1 -> Haut Droit");
			        	System.out.println("	- 2 -> Milieu Droit");
			        	System.out.println("	- 3 -> Bas Droit");
			        	System.out.println("	- 4 -> Bas Gauche");
			        	System.out.println("	- 5 -> Milieu Gauche");
			        	System.out.println("	- 6 -> Haut Gauche");
			        	System.out.println("z : ");
			        	z = Integer.parseInt(br.readLine());
			        	afficherVoisin(x, y, z);
						break;
					case 4:
						Partie p1 = creerPartie();
						LauncherConsole.phasePlacement(p1);
						System.out.println(p1.toString2());
						System.out.println(" 1 seul joueur Humain ? " + p1.peutAnnulerCoup());
						break;
					case 5:
						Partie p = creerPartie();
						LauncherConsole.phasePlacement(p);
						LauncherConsole.tourDeJeuConsole(p);
						LauncherConsole.tourDeJeuConsole(p);
						LauncherConsole.tourDeJeuConsole(p);
						System.out.println("Banquise créée :\n"+p);
						System.out.println("Historique créée :\n"+p.h);
						p.sauvegarder();
						Partie p2 = new Partie();
						p2.charger();
						System.out.println("Banquise restaurée :\n"+p);
						System.out.println("Historique restaurée :\n"+p.h);
					case 6:
						Partie p_origine = creerPartie();
						LauncherConsole.phasePlacement(p_origine);
						System.out.println("Banquise créée :\n"+p_origine);
						Partie p_clone = p_origine.clone();
						System.out.println("Banquise clonée :\n"+p_clone);
					case 7:
						Partie p_new = new Partie();
						
						System.out.print("Numero de la partie a charger");
			        	String pa = br.readLine().substring(0, 1);
			        	System.out.print("Numero de la banquise a charger");
			        	String ba = br.readLine().substring(0, 1);

		        		p_new.chargerTXT("saveBanquises/banquise"+ba, "saveParties/partie"+pa);
						System.out.println("Banquise recupere :\n"+p_new.toString2());
					default :
						System.out.println("Commande non reconnu");
				}
				
	        }catch(Exception e){
	            System.out.println("Exception : Commande");
	            //e.printStackTrace(System.err);
	        }
		}		
	}

}