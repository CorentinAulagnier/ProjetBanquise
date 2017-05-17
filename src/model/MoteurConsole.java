package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class MoteurConsole {
	
    
/* ----------------------------------- PHASE CREATION PARTIE ----------------------------------- */
    
    /**
	 *  - demande le nombre de joueurs et creee la partie adequate
	 *  - genere aleatoirement la banquise
	 *  - demande le nom/type de joueur
	 *  
	 * @param br
	 *            Buffer de la console.
	 * @return la partie cree
	 */
	
	public static Partie creerPartie() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Partie p = null;
		try {
			System.out.println("Creation de la partie !");
			//Choix du nombre de joueurs
			//Humains
			boolean nb_humain_ok = false;
			int nb_humains = 1;
			while (!nb_humain_ok) {
				try {
					System.out.println("A combien de joueurs (humains) voulez-vous jouer ? (0 à 4)");
					nb_humains = Integer.valueOf(br.readLine());
					if(nb_humains<= 4 && nb_humains>=0) {
						nb_humain_ok = true;
						System.out.println("Création d'une partie à "+String.valueOf(nb_humains)+" joueurs.");
					} else {
						System.out.println("Nombre de joueurs incorrect. Réessayez.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Nombre de joueurs incorrect. Réessayez.");
				}
			}
			//IAs
			int nb_ias = 0;
			int min_ia = 0;
			if(nb_humains==0) min_ia = 2;
			else if(nb_humains==1) min_ia = 1;
			if(nb_humains<4) {
				boolean nb_ia_ok = false;
				while (!nb_ia_ok) {
					try {
						System.out.println("Combien d'IA(s) voulez-vous ajouter ? ("+min_ia+" à "+(4-nb_humains)+")");
						nb_ias = Integer.valueOf(br.readLine());
						if(nb_ias<= (4-nb_humains) && nb_ias>=min_ia) {
							nb_ia_ok = true;
							if (nb_ias>0)
								System.out.println("Ajout de "+String.valueOf(nb_ias)+" IAs à la partie.");
						} else {
							System.out.println("Nombre de joueurs incorrect. Réessayez.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Nombre de joueurs incorrect. Réessayez.");
					}
				}
			}
			
			//Creation partie + init banquise
			int nb_joueurs_tot = nb_humains+nb_ias;
			p = new Partie(nb_joueurs_tot);
			p.b = new Banquise();
			
			//Ajout des joueurs et IA à la partie
			for(int i = 0; i<nb_joueurs_tot;i++) {
				if(i<nb_humains) {
					p.joueurs[i]= new Humain("",6-nb_joueurs_tot);
				} else {
					int niv_IA;
					while(true) {
						try {
							System.out.println("Quel niveau voulez vous pour l'IA" + (i-nb_humains) + " ? (1 à 3)");
							niv_IA = Integer.valueOf(br.readLine());
							if(niv_IA<=3 && niv_IA>=1) {
								break;
							} else {
								System.out.println("Entrée incorrecte. Réessayez.");
							}
						} catch (Exception e) {
							e.printStackTrace(System.out);
						}
					}
					p.joueurs[i] = new IA("IA"+(i-nb_humains), 6-nb_joueurs_tot, niv_IA);
                    for(int j = 0; j<6-nb_joueurs_tot;j++) {
                    	p.joueurs[i].myPingouins[j] = new Pingouin();
                    }
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/**
	 *  initilaise le nom des joueurs humains
	 *  
	 * @param p
	 *            partie en cours de creation
	 */
	//Local
	public static void setJoueursHumains(Partie p) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for(int i = 0; i<p.nbJoueurs && !(p.joueurs[i] instanceof IA);i++) {
				System.out.println("Joueur "+String.valueOf(i)+", quel est votre nom ?");
				String nom = br.readLine();
				p.joueurs[i] = new Humain(nom, 6-p.nbJoueurs);
                for(int j = 0; j<6-p.nbJoueurs;j++) {
                	p.joueurs[i].myPingouins[j] = new Pingouin();
                }
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	//Réseau
    public static String getUsername() {
    	String s = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		System.out.println("Entrez votre nom :");
    		s = br.readLine();
    	} catch (Exception e){
    		e.printStackTrace(System.err);
    	}
        return s;
    }
	
/* ----------------------------------- PHASE PLACEMENT ----------------------------------- */
 
    

	public static Coordonnees getPlacementPingouin(int numJoueur, int numPingouin, Partie p) {
			while(true) {
				Coordonnees c = essaiPlacement(p, numJoueur, numPingouin);
				Tuile t = p.b.getTuile(c);
				if(t!= null && t.nbPoissons==1 && !t.aUnPingouin) {
					return c;
				} else {
					System.out.println("La case choisie ne contient pas qu'un poisson. Impossible de placer le pingouin ici.\n");
				}
			}
	}
	

	public static Coordonnees essaiPlacement(Partie p, int numJoueur, int numPingouin) {
		if (p.joueurs[numJoueur] instanceof IA) { // Tour de l'IA
			return p.joueurs[numJoueur].placement(p);
		} else {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int x = -1;
			int y = -1;
			try {
				System.out.println(p.joueurs[numJoueur].nom+", entrez le placement de votre pingouin n°"+numPingouin+" (cases à 1 poisson seulement):");
				System.out.print("x :");
				x = Integer.valueOf(br.readLine());
				System.out.print("y :");
				y = Integer.valueOf(br.readLine());
			} catch (Exception e) {
				//e.printStackTrace(System.out);
				return null;
			}
			return new Coordonnees(x, y);
		}
	}
	

	public static void setPlacementPingouin(Coordonnees c, Partie p, int numJoueur, int numPingouin) {
		p.b.getTuile(c).mettrePingouin();
		p.joueurs[numJoueur].myPingouins[numPingouin] = new Pingouin(c);
		System.out.println("Le pingouin " + String.valueOf(numPingouin)+ " de " + p.joueurs[numJoueur].nom +" a bien été positionné en "+c+".");
	}
	
/* ----------------------------------- PHASE JEU ----------------------------------- */
	
	
    public static CoupleGenerique<Coordonnees,Coordonnees> getDeplacement(Partie p) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				Pingouin pingouin = choixPingouin(br,p);
				Coordonnees dep = choixDeplacement(br, p, pingouin);
				if(dep!=null) {
					return new CoupleGenerique<Coordonnees,Coordonnees>(pingouin.position,dep);
				}
    		} catch (Exception e) {
				System.out.println("Mauvaise entree, on recommence.\n");
    		}
		}
	}
	
	/**
	 * affiche la liste des pingouins deplacables
	 * 
	 * @param br
	 *            Buffer de la console.	 
	 * @param p
	 *            La partie.
	 *            
	 * @return Le pingouin choisi
	 */

	public static Pingouin choixPingouin(BufferedReader br, Partie p) {
		int num_p = -1;
		Pingouin[] pingouins = p.joueurs[p.joueurActif].myPingouins; 
		try {
			System.out.println("Vos pingouins déplacables sont:");
			for(int i =0; i<pingouins.length;i++) {
				if(pingouins[i].actif) {
					System.out.print(String.valueOf(i) + " - " + pingouins[i]);
				}
			}
			while (num_p < 0 || num_p >= pingouins.length) {
				System.out.println("\nEntrez le numero de celui que vous voulez déplacer:");
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

	/**
	 * Affiche la liste des deplacements possibles pour un pingouin
	 *
	 * @param br
	 *            Buffer de la console.	 
	 * @param p
	 *            La partie.	 
	 * @param pingouin
	 *            Le pingouin.
	 *        
	 * @return les coordonnees de deplacement choisis
	 */

	public static Coordonnees choixDeplacement(BufferedReader br, Partie p, Pingouin pingouin) {
		try {
			int numAxe = 0;
			int numDep = 0;
			boolean axeok = false;
			ArrayList<ArrayList<Coordonnees>> chemins = p.b.deplacementPossible(pingouin);
			while (!axeok) {
				System.out.println(p.b.cheminsToString(chemins));
				System.out.println("Numero de l'axe choisi (0 pour retourner au choix du pingouin):");
				numAxe = Integer.valueOf(br.readLine());
				if(numAxe<1 || numAxe>6) {
					return null;
				} else {
					numAxe--; //pour correspondre au réel indice dans le tableau
				}
				boolean numok = false;
				while(!numok) {
					try {
						System.out.println("Numero du déplacement choisi (0 pour changer d'axe):");
						numDep = Integer.valueOf(br.readLine());
						if(numDep>0 && numDep<=chemins.get(numAxe).size()) {
							numok = true;
							axeok = true;
						} else if (numDep == 0) {
							numok = true;
							axeok = false;
							System.out.println("Retour au choix de l'axe !");
						}
					} catch (Exception e) {
						System.out.println("Entrée incorrecte. Réessayez.");
					}
				}
				
			}
			return chemins.get(numAxe).get(numDep-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Affiche l'etat courant du plateau dans la console 
	 * 
	 * @param p
	 *            La partie.
	 */

	public static void afficherPlateau(Partie p) {
		System.out.println("Etat du plateau de jeu :");
		System.out.println(p);
	}
	
/* ----------------------------------- FIN DE PARTIE ----------------------------------- */
	
	/**
	 * affiche la fin de la partie (le gagnant)
	 *
	 * @param p
	 *            La partie.	 
	 */

	public static void finPartie(Partie p) {
		afficherPlateau(p);
		ArrayList<Joueur> joueurs = p.getGagnant();
		if(joueurs.size()>1) {
			System.out.print("Partie terminée.\nLes joueurs ");
			for(Joueur j : joueurs) {
				System.out.print(j.nom+" ");
			}
			System.out.println("ont gagnés la partie avec "+String.valueOf(joueurs.get(0).poissonsManges)+" poissons mangés !");
		} else {
			System.out.println("Partie terminée.\nLe joueur "+joueurs.get(0).nom+" a gagné la partie avec "+String.valueOf(joueurs.get(0).poissonsManges)+" poissons mangés !");
		}
		//score de chaque joueur
		p.afficherScores();
	}

}
