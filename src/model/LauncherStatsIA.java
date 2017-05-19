package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LauncherStatsIA extends MoteurConsole{

	public static void main(String[] args) {
		try {
			
			//Récupère tout les paramètres du tests
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int nbIAs =0;
			while(nbIAs<2 || nbIAs>4) {
				System.out.println("Combien d'IAs voulez vous faire s'affronter ?");
				nbIAs = Integer.valueOf(br.readLine());
			}
			int[] diff = new int[nbIAs];
			Stats[] scores = new Stats[nbIAs];
			for(int i = 0; i<nbIAs; i++) {
				System.out.println("Difficultée de l'IA"+i+" ?");
				diff[i] = Integer.valueOf(br.readLine());
				scores[i] = new Stats();
			}
			System.out.println("Combien de parties voulez-vous jouer ?");
			int nbParties = Integer.valueOf(br.readLine());
			
			for(int numPartie = 1; numPartie<=nbParties;numPartie++) {
				System.out.println("partie "+numPartie+"/"+nbParties);
				//Initialisation
				Partie p = new Partie(nbIAs);
				p.b = new Banquise();
				for(int i=0; i<nbIAs;i++) {
					p.joueurs[i] = new IA("IA"+i, 6-nbIAs, diff[i]);
                    for(int j = 0; j<6-nbIAs;j++) {
                    	p.joueurs[i].myPingouins[j] = new Pingouin();
                    }
				}
				//Jeu
				phasePlacementStats(p);
				phaseMangerLesPoissonsStats(p);
				
				//Maj Stats
				majScores(scores, p);
			}
			
			//fin des parties
			System.out.println("Fin des calculs.");
			majScoresFinPartie(scores, nbParties);
			
			//Affichage des stats
			for(int i=0; i<nbIAs;i++) {
				System.out.println("IA"+i+scores[i]);
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}
	
	public static void majScores(Stats[] scores, Partie p) {
		for(int i =0; i<p.nbJoueurs;i++) {
			scores[i].nbPoissons += p.joueurs[i].poissonsManges;
			scores[i].nbTuiles += p.joueurs[i].nbTuiles;
		}
		ArrayList<Integer> numGagnants = getNumDesGagnants(p);
		for(int j : numGagnants) {
			scores[j].nbVictoires++;
		}
	}
	
	public static void majScoresFinPartie(Stats[] scores, int nbParties) {
		for(int i = 0; i<scores.length;i++) {
			scores[i].nbPoissons = scores[i].nbPoissons/nbParties;
			scores[i].nbTuiles = scores[i].nbTuiles/nbParties;
		}
	}
	
	public static ArrayList<Integer> getNumDesGagnants(Partie p) {
		int scoremax = p.getScoreMax();
		int nbTuilesMax = p.getNbTuiles(scoremax);
		ArrayList<Integer> gagnants = new ArrayList<Integer>();
		
		for(int i = 0; i<p.nbJoueurs;i++ ) {
			if(p.joueurs[i].poissonsManges == scoremax && p.joueurs[i].nbTuiles == nbTuilesMax) {
				gagnants.add(i);
			}
		}
		return gagnants;
	}
	
	public static void setPlacementPingouinStats(Partie p, Coordonnees c, int numJoueur, int numPingouin) {
		p.b.getTuile(c).mettrePingouin();
		p.joueurs[numJoueur].myPingouins[numPingouin] = new Pingouin(c);
	}
	
	public static void phasePlacementStats(Partie p) {
		int nbPingouinsMax = p.joueurs[0].nbPingouin;
		for(int numPingouin = 0; numPingouin<nbPingouinsMax; numPingouin++) {
			for(int numJoueur = 0; numJoueur<p.nbJoueurs; numJoueur++) {
				Coordonnees c = getPlacementPingouin(numJoueur, numPingouin, p);
				setPlacementPingouinStats(p, c, numJoueur, numPingouin);
			}
		}
	}

	public static void phaseMangerLesPoissonsStats(Partie p) {
		while(!p.estPartieFini()) {
			tourDeJeuStats(p);
			p.verifierPingouinActif();
		}
	}
	
	public static void tourDeJeuStats(Partie p) {
		if(p.peutJouer()){
			boolean joue = false;
			while(!joue) {
				if (p.joueurs[p.joueurActif].getClass() == IA.class) {
					CoupleGenerique<Coordonnees, Coordonnees> cc = p.joueurs[p.joueurActif].jouer(p);
					if(cc!=null) {
						p.deplacement(cc.e1, cc.e2);
						joue = true;
					}					
				} 
			}
		}
		p.majProchainJoueur();
	}
}
