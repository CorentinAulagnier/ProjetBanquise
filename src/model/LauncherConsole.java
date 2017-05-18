package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Class de test pour jouer dans la console,
 * Pour plus de renseignements, demander a Mathieu ou Corentin
 */

public class LauncherConsole extends MoteurConsole {
	
	/**
	 * Main
	 * @param args
	 *            Parametre du main.
	 */
	
	public static void main(String[] args) {
		ArrayList<PrintStream> writers = new ArrayList<>();
		writers.add(System.out);
		Partie p = creerPartie();
		setJoueursHumains(p);
		p.setHistorique();
		phasePlacement(p);
		phaseMangerLesPoissons(p);
		finPartie(p);
	}
	
	/**
	 * place les pingouins de chaque joueur/IA a tour de role 	
	 *   
	 * @param p
	 *            La partie.
	 */

	public static void phasePlacement(Partie p) {
		afficherPlateau(p);
		int nbPingouinsMax = p.joueurs[0].nbPingouin;
		for(int numPingouin = 0; numPingouin<nbPingouinsMax; numPingouin++) {
			for(int numJoueur = 0; numJoueur<p.nbJoueurs; numJoueur++) {
				Coordonnees c = getPlacementPingouin(numJoueur, numPingouin, p);
				p.setPlacementPingouin(c, numJoueur, numPingouin);
				afficherPlateau(p);
			}
		}
		System.out.println("Tout les pingouins on été positionnés.");
	}
	
	/**
	 * deroulement du jeu explicit dans la fonction
	 * 
	 * @param p
	 *            La partie.
	 */

	public static void phaseMangerLesPoissons(Partie p) {
		while(!p.estPartieFini()) {
			afficherPlateau(p);
			tourDeJeuConsole( p);
			p.verifierPingouinActif();
		}
	}
	
	/**
	 * si le joueur actif peut jouer :
	 * - affiche la liste des pingouins capable d'effectuer un deplacement
	 * - affiche tous les deplacements possibles du pingouin selectionne
	 * - effectue le deplacement choisi puis passe la main au joueur suivant
	 * 
	 * @param p
	 *            La partie.
	 */

	public static void tourDeJeuConsole(Partie p) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if(p.utiliseHistorique && p.joueurs[p.joueurActif].getClass()==Humain.class) demandeAnnulerRefaire(br, p);
		if(p.peutJouer()){
			System.out.println("A "+p.joueurs[p.joueurActif].nom+" (Joueur "+String.valueOf(p.joueurActif)+") de jouer!");
			boolean joue = false;
			while(!joue) {
				if (p.joueurs[p.joueurActif].getClass() == IA.class) { // Tour de l'IA
					System.out.println("L'IA " + p.joueurs[p.joueurActif].nom + " cherche son coup.");
					CoupleGenerique<Coordonnees, Coordonnees> cc = p.joueurs[p.joueurActif].jouer(p);
					if(cc!=null) {
						p.deplacement(cc.e1, cc.e2);
						System.out.println("L'IA " + p.joueurs[p.joueurActif].nom + " joue en "+cc.e2);
						joue = true;
					} else {
						System.out.println("L'IA " + p.joueurs[p.joueurActif].nom + " joue a un endroit impossible.");
					}
							
				} else { // Tour de l'Humain
					CoupleGenerique<Coordonnees,Coordonnees> cg = getDeplacement(p);
					p.deplacement(cg.e1, cg.e2);
					joue = true;
				}
			}				
			//Fin du tour 
			System.out.println(""+p.joueurs[p.joueurActif].nom+" (Joueur "+String.valueOf(p.joueurActif)+") terminé.");
		} else {
			System.out.println(p.joueurs[p.joueurActif].nom+" (Joueur "+String.valueOf(p.joueurActif)+") ne peut plus jouer");
		}	
		p.majProchainJoueur();
	}
	

	private static void demandeAnnulerRefaire(BufferedReader br, Partie p) {
		boolean fini = false;
		boolean annuler = p.h.peutAnnuler();
		boolean refaire = p.h.peutRefaire();
		while(!fini && (annuler || refaire)) {
			System.out.println("Entrez la valeur de l'action que vous voulez faire : (vide pour passer)");
			if(annuler) {
				System.out.println("1 - annuler coup précédent");
			}
			if(refaire) {
				System.out.println("2 - refaire dernier coup annulé");
			}
			String entree = null;
			try {
				entree = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(entree.equals("1")) {
				p.annuler();
				afficherPlateau(p);
				annuler = p.h.peutAnnuler();
				refaire = p.h.peutRefaire();
			} else if(entree.equals("2")) {
				p.retablir();
				afficherPlateau(p);
				annuler = p.h.peutAnnuler();
				refaire = p.h.peutRefaire();
			} else {
				fini = true;
			}
		}
	}


}