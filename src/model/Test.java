package model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

import model.Coordonnees;

public class Test {

	public static void afficherBanquise(){
		Banquise b = new Banquise();
		System.out.println(b);
	}
	
	public static void afficherPosPingouin(int x, int y){
		Coordonnees c = new Coordonnees(x, y);
    	//System.out.println("c : "+c);
		Pingouin p = new Pingouin(c);
		System.out.println(p);
	}
	
	public static void afficherVoisin(int x, int y, int z){
		Banquise b = new Banquise();

		Coordonnees c = new Coordonnees(x, y);
		System.out.print(c + " -> ");

		switch (z) {
			case 1:
				System.out.println(b.getHG(c));
				break;
				
			case 2: 
				System.out.println(b.getHD(c));
				break;
				
			case 3: 
				System.out.println(b.getMG(c));
				break;
				
			case 4:
				System.out.println(b.getMD(c));
				break;
				
			case 5:
				System.out.println(b.getBG(c));
				break;
				
			case 6:
				System.out.println(b.getBD(c));
				break;

			default :
				System.out.println("Voisin demande non reconnu");
		}
	}
	
	public static void afficherPartie(){
		Banquise b = new Banquise();
		Joueur[] j = new Joueur[2];
		Tuile t;
		
		j[0] = new Humain();
		j[0].myPingouins[0] = new Pingouin(new Coordonnees(0, 0));
		t = b.getTuile(new Coordonnees(0, 0));
		t.mettrePingouin();
		
		j[0].myPingouins[1] = new Pingouin(new Coordonnees(2, 2));
		t = b.getTuile(new Coordonnees(2, 2));
		t.mettrePingouin();
		
		j[0].myPingouins[2] = new Pingouin(new Coordonnees(4, 4));
		t = b.getTuile(new Coordonnees(4, 4));
		t.mettrePingouin();
		
		j[1] = new Humain();
		j[1].myPingouins[0] = new Pingouin(new Coordonnees(1, 1));
		t = b.getTuile(new Coordonnees(1, 1));
		t.mettrePingouin();
		
		j[1].myPingouins[1] = new Pingouin(new Coordonnees(3, 3));
		t = b.getTuile(new Coordonnees(3, 3));
		t.mettrePingouin();
		
		j[1].myPingouins[2] = new Pingouin(new Coordonnees(5, 5));
		t = b.getTuile(new Coordonnees(5, 5));
		t.mettrePingouin();
		
		Partie p = new Partie(b, j);

		System.out.println(p);
	}

	public static void main(String[] args) {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		
		while (true){
			try {
	        	System.out.println("Liste des tests : ");
	        	System.out.println("   - 1 -> affiche une banquise");
	        	System.out.println("   - 2 -> affiche position d'un pingouin");
	       		System.out.println("   - 3 -> affiche la position d'un voisin");
	       		System.out.println("   - 4 -> affiche une partie");
	       		System.out.println("   - 5 -> sauvegarder et charger une partie");

	        	int x = Integer.parseInt(br.readLine());
	        	int y;
	        	int z;
				System.out.println(x);

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
			        	System.out.println("	- 1 -> Haut Gauche");
			        	System.out.println("	- 2 -> Haut Droit");
			        	System.out.println("	- 3 -> Milieu Gauche");
			        	System.out.println("	- 4 -> Milieu Droit");
			        	System.out.println("	- 5 -> Bas Gauche");
			        	System.out.println("	- 6 -> Bas Droit");
			        	System.out.println("z : ");
			        	z = Integer.parseInt(br.readLine());
			        	afficherVoisin(x, y, z);
						break;
					case 4:
						afficherPartie();
						break;
					case 5:
						Partie p = LauncherConsole.creerPartie(br);
						LauncherConsole.phasePlacement(br, p);
						p.sauvegarder();
						System.out.println("Banquise créée :\n"+p.toStringTerminal());
						//p = LauncherConsole.creerPartie(new BufferedReader(new InputStreamReader(System.in)));
						//System.out.println("Banquise modifiée :\n"+p);
						Partie p2 = new Partie();
						p2.charger();
						System.out.println("Banquise restaurée :\n"+p.toStringTerminal());
					default :
						System.out.println("Commande non reconnu");
				}
	            
	        }catch(Exception e){
	            System.out.println("Exception : Commande");
	            e.printStackTrace(System.err);
	        }
			
			
			
			
		}		
	}

}
