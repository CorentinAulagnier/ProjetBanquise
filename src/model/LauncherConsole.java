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
				try {
					System.out.println("A combien de joueurs voulez-vous jouer ? (2 à 4)");
					nb_joueurs = Integer.valueOf(br.readLine());
					if(nb_joueurs<= 4 && nb_joueurs>=2) {
						nb_joueurs_ok = true;
						System.out.println("Création d'une partie à "+String.valueOf(nb_joueurs)+" joueurs.");
					} else {
						System.out.println("Nombre de joueurs incorrect. Réessayez.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Nombre de joueurs incorrect. Réessayez.");
				}
			}
			/* ----- Creation partie + init banquise ----- */
			p = new Partie(nb_joueurs);
			p.b = new Banquise();
			/* ----- Creation des joueurs ----- */
			for(int num_joueur = 0; num_joueur<p.nbJoueurs;num_joueur++) {
				System.out.println("Joueur "+String.valueOf(num_joueur)+", quel est votre nom ? (Entrez \"IA\" pour jouer contre l'ordinateur).");
				String nom = br.readLine();
				if (nom.equals("IA")) {
					System.out.println("Quel niveau voulez vous pour l'IA ? (1 à 2)");
					int niv_IA = Integer.valueOf(br.readLine());
					p.joueurs[num_joueur] = new IA("IA"+num_joueur, 6-nb_joueurs, niv_IA); //6-nb_joueurs
				} else {
					p.joueurs[num_joueur] = new Humain(nom, 6-nb_joueurs); //6-nb_joueurs
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static void phasePlacement(BufferedReader br, Partie p) {
		try {
			System.out.println(p.b);
			int nb_pingouin_max = p.joueurs[0].nbPingouin;
			for(int num_pingouin = 0; num_pingouin<nb_pingouin_max; num_pingouin++) {
				for(int num_joueur = 0; num_joueur<p.nbJoueurs; num_joueur++) {
					boolean position_dispo = false;
					while(!position_dispo) {
						Tuile t = null;
						Coordonnees c = null;
						int x, y;
						try {
							if (p.joueurs[num_joueur].getClass() == IA.class) { // Tour de l'IA
								c = p.joueurs[num_joueur].placement(p);
								System.out.println("Placement du pingouin en : "+c);
								t = p.b.getTuile(c);
								x = c.x;
								y = c.y;
								
							} else { // Tour de l'Humain
								System.out.println("Joueur "+String.valueOf(num_joueur)+": "+p.joueurs[num_joueur].nom+", choisissez la position initiale de votre pingouin "+String.valueOf(num_pingouin+1)+":");
								System.out.print("x: ");
								x = Integer.valueOf(br.readLine());
								System.out.print("y: ");
								y = Integer.valueOf(br.readLine());
								c = new Coordonnees(x, y);
								t = p.b.getTuile(c);
							}
							if(t!=null && t.nbPoissons != 0 && !t.aUnPingouin) {//Placement autorisé ici
								t.mettrePingouin();
								p.joueurs[num_joueur].myPingouins[num_pingouin] = new Pingouin(c);
								
								System.out.println("Le pingouin " + String.valueOf(num_pingouin)+ " de " + p.joueurs[num_joueur].nom +" a bien été positionné en ("+String.valueOf(x)+","+String.valueOf(y)+").");
								position_dispo = true;
							} else {
								System.out.println("La position ("+String.valueOf(x)+","+String.valueOf(y)+") n'est pas disponible");
							}
						}catch (NumberFormatException e) {
							System.out.println("Coordonnées incorrectes.");
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
			p.verifierPingouinActif();
		}
	}
	
	public static void afficherPlateau(Partie p) {
		System.out.println("Etat du plateau de jeu :");
		System.out.println(p.toStringTerminal());
	}
	
	public static void tourDeJeuConsole(BufferedReader br, Partie p) {
		if(p.peutJouer()){
			System.out.println("A "+p.joueurs[p.joueurActif].nom+" (Joueur "+String.valueOf(p.joueurActif)+") de jouer!");
			boolean joue = false;
			while(!joue) {
				if (p.joueurs[p.joueurActif].getClass() == IA.class) { // Tour de l'IA
					System.out.println("L'IA " + p.joueurs[p.joueurActif].nom + " cherche son coup.");
					CoupleCoordonnees cc = p.joueurs[p.joueurActif].jouer(p);
					if(cc!=null) {
						p.deplacement(cc.c1, cc.c2);
						System.out.println("L'IA " + p.joueurs[p.joueurActif].nom + " joue en "+cc.c2);
						joue = true;
					} else {
						System.out.println("L'IA " + p.joueurs[p.joueurActif].nom + " joue en "+cc.c2+" mais cela est impossible.");
					}
							
				} else { // Tour de l'Humain
					Pingouin pingouin = choixPingouin(br,p);
					Coordonnees dep = choixDeplacement(br, p, pingouin);
					if(dep!=null) {
						p.deplacement(pingouin, dep);
						joue = true;
					}
				}
			}				
			/* ----- Fin du tour ----- */
			System.out.println(""+p.joueurs[p.joueurActif].nom+" (Joueur "+String.valueOf(p.joueurActif)+") terminé.");
		} else {
			System.out.println(p.joueurs[p.joueurActif].nom+" (Joueur "+String.valueOf(p.joueurActif)+") ne peut plus jouer");
		}	
		p.joueurActif = (p.joueurActif+1)%p.nbJoueurs;
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
			while (num_p < 0 || num_p >= pingouins.length) {
				System.out.println("Entrez le numero de celui que vous voulez déplacer:");
				num_p = Integer.valueOf(br.readLine());
				if (num_p < 0 || num_p >= pingouins.length) {
					System.out.println("Mauvaise entree. Réessayez.");
				}
			}
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
				case 1: {
					array = chemins.hautDroit;
					break;
				} case 2: {
					array = chemins.milieuDroit;
					break;
				} case 3: {
					array = chemins.basDroit;
					break;
				} case 4: {
					array = chemins.basGauche;
					break;
				} case 5: {
					array = chemins.milieuGauche;
					break;
				} case 6: {
					array = chemins.hautGauche;
					break;
				} default : array = null;
			}
			if(array!=null) {
				int numDep =-1;
				boolean numok = false;
				while(!numok) {
					System.out.println("Numero du déplacement choisi:");
					numDep = Integer.valueOf(br.readLine());
					if(numDep>0 && numDep<=array.size()) {
						numok = true;
					} else {
						System.out.println("Entrée incorrecte. Réessayer.");
					}
				}
				return array.get(numDep-1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void finPartie(Partie p) {
		ArrayList<Joueur> joueurs = p.getGagnant();
		if(joueurs.size()>1) {
			System.out.print("Partie terminée.\nLes joueurs ");
			for(Joueur j : joueurs) {
				System.out.print(j.nom+" ");
			}
			System.out.print("ont gagnés la partie avec "+String.valueOf(joueurs.get(0).poissonsManges)+" poissons mangés !");
		} else {
			System.out.print("Partie terminée.\nLe joueur "+joueurs.get(0).nom+" a gagné la partie avec "+String.valueOf(joueurs.get(0).poissonsManges)+" poissons mangés !");
		}
	}
	
	
	
}
