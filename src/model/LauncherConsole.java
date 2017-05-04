package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LauncherConsole {
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Partie p = creerPartie(br);
		phasePlacement(br, p);
		phaseMangerLesPoissons(br, p);
		finPartie(p);
	}
	
	public static Partie creerPartie(BufferedReader br) {
		Partie p = null;
		try {
			System.out.println("Bienvenue dans PINGOUINS !");
			/* ----- Choix du nombre de joueurs ----- */
			boolean nb_joueurs_ok = false;
			int nb_joueurs = 2;
			while (!nb_joueurs_ok) {
				System.out.println("A combien de joueurs voulez-vous jouer ? (2 à 4)");
				nb_joueurs = Integer.valueOf(br.readLine());
				if(nb_joueurs<= 4 && nb_joueurs>=2) {
					nb_joueurs_ok = true;
					System.out.println("Création d'une partie à "+String.valueOf(nb_joueurs)+" joueurs.");
				} else {
					System.out.println("Nombre de joueurs incorrect. Réessayez.");
				}
			}
			/* ----- Creation partie + init banquise ----- */
			p = new Partie(nb_joueurs);
			p.b.initBanquise();
			/* ----- Creation des joueurs ----- */
			for(int num_joueur = 0; num_joueur<p.nbJoueurs;num_joueur++) {
				System.out.println("Joueur "+String.valueOf(num_joueur)+", quel est votre nom ?");
				String nom = br.readLine();
				p.joueurs[0] = new Humain(nom, 6-nb_joueurs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static void phasePlacement(BufferedReader br, Partie p) {
		try {
			int nb_pingouin_max = p.joueurs[0].nbPingouin;
			for(int num_pingouin = 0; num_pingouin<nb_pingouin_max; num_pingouin++) {
				for(int num_joueur = 0; num_joueur<p.nbJoueurs; num_joueur++) {
					boolean position_dispo = false;
					while(!position_dispo) {
						System.out.println("Joueur "+String.valueOf(num_joueur)+": "+p.joueurs[num_joueur].nom+", choisissez la position initiale de votre pingouin "+String.valueOf(num_pingouin)+":");
						System.out.println("x: ");
						int x = Integer.valueOf(br.readLine());
						System.out.println("y: ");
						int y = Integer.valueOf(br.readLine());
						Coordonnees c = new Coordonnees(x, y);
						Tuile t = p.b.getTuile(c);
						if(t.nbPoissons != 0 && !t.aUnPingouin) {//Placement autorisé ici
							t.mettrePingouin();
							System.out.println("Votre pingouin "+String.valueOf(num_pingouin)+" a bien été positionné en ("+String.valueOf(x)+","+String.valueOf(y)+").");
							position_dispo = true;
						} else {
							System.out.println("La position ("+String.valueOf(x)+","+String.valueOf(y)+") n'est pas disponible");
						}
					}
				}
			}
			System.out.println("Tout les pingouins on été positionnés.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void phaseMangerLesPoissons(BufferedReader br, Partie p) {
		while(!p.estPartieFini()) {
			afficherPlateau(p);
			tourDeJeuConsole(br, p);
		}
	}
	
	public static void afficherPlateau(Partie p) {
		System.out.println("Etat du plateau de jeu :");
		System.out.println(p.b);
	}
	
	public static void tourDeJeuConsole(BufferedReader br, Partie p) {
		if(p.peutJouer()){
			System.out.println("A "+p.joueurs[p.joueurActif].nom+"(Joueur "+String.valueOf(p.joueurActif)+") de jouer!");
			boolean joue = false;
			while(!joue) {
				Pingouin pingouin = choixPingouin(br,p);
				Coordonnees dep = choixDeplacement(br, p, pingouin);
				if(dep!=null) {
					p.deplacement(pingouin.position, dep);
					joue = true;
				}
			}				
			/* ----- Fin du tour ----- */
			System.out.println(""+p.joueurs[p.joueurActif].nom+"(Joueur "+String.valueOf(p.joueurActif)+") terminé.");
			p.joueurActif = (p.joueurActif+1)%p.nbJoueurs;
		} else {
			System.out.println(p.joueurs[p.joueurActif].nom+"(Joueur "+String.valueOf(p.joueurActif)+") ne peut plus jouer");
		}		
	}
	
	public static Pingouin choixPingouin(BufferedReader br, Partie p) {
		int num_p = -1;
		Pingouin[] pingouins = p.joueurs[p.joueurActif].myPingouins; 
		try {
			System.out.println("Vos pinguoins déplacables sont:");
			for(int i =0; i<pingouins.length;i++) {
				if(pingouins[i].actif) {
					System.out.println(String.valueOf(i) + " - " + pingouins[i]);
				}
			}
			System.out.println("Entrez le numero de celui que vous voulez déplacer:");
			num_p = Integer.valueOf(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pingouins[num_p];
	}
	
	public static Coordonnees choixDeplacement(BufferedReader br, Partie p, Pingouin pingouin) {
		try {
			Chemins chemins = new Chemins(p, pingouin.position);
			System.out.println(chemins);
			System.out.println("Numero de l'axe choisi(0 pour retourner au choix du pingouin):");
			int numAxe = Integer.valueOf(br.readLine());
			ArrayList<Coordonnees> array;
			switch (numAxe) {
				case 1 : {
					array = chemins.hautDroit;
				} case 2 : {
					array = chemins.milieuDroit;
				} case 3 : {
					array = chemins.basDroit;
				} case 4 : {
					array = chemins.basGauche;
				} case 5 : {
					array = chemins.milieuGauche;
				} case 6 : {
					array = chemins.hautGauche;
				} default : array = null;
			}
			if(array!=null) {
				System.out.println("Numero du déplacement choisi:");
				int numDep = Integer.valueOf(br.readLine());
				return array.get(numDep-1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void finPartie(Partie p) {
		Joueur j = p.getGagnant();
		System.out.println("Partie terminée.\n"+j.nom+"a gagné la partie avec "+String.valueOf(j.poissonsManges)+" poissons mangés !");
	}
	
	
	
}