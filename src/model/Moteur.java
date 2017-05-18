package model;


public class Moteur {
    
	Partie partie;
	boolean phaseJeu, phasePlacement, phaseVictoire;

	Moteur(Partie p) {
		this.partie = p;
		this.phasePlacement = true;
		this.phaseJeu = false;
		this.phaseVictoire = false;
	}
	
	public Partie getPartie() {
		return partie.clone();
	}
	
	public void placement(Coordonnees c) {
		partie.setPlacementPingouin(c, partie.joueurActif, partie.numPingouinAPlacer());
		
		phasePlacement = !partie.placementPingouinsFini();			
		if (!phasePlacement) {
			phaseJeu = true;
		}
		partie.majProchainJoueur();
		//Notifier IHM

		if (partie.getJoueurActif() instanceof IA) {
			faireJouerIAS();
		}
	}
	
	
	public void deplacement(CoupleGenerique<Coordonnees, Coordonnees> cc) {
		partie.deplacement(cc);
		
		phaseJeu = !partie.jeuPingouinsFini();			
		if (!phaseJeu) {
			phaseVictoire = true;
		}
		partie.majProchainJoueur();
		//Notifier IHM

		if (partie.getJoueurActif() instanceof IA) {
			faireJouerIAS();
		}
		
	}
	
	
    
    private void faireJouerIAS() {
    	Joueur j = partie.getJoueurActif();
    	
    	if (phasePlacement) {
			placement(j.placement(partie));
			
		} else if(phaseJeu) {
			deplacement(j.jouer(partie));
		}
	}
    
    
    
    
}
