package model;


public class Moteur {
    
	Partie partie;
	boolean phaseJeu, phasePlacement, phaseVictoire;
	boolean peutJouer;
	
	Moteur(Partie p) {
		this.partie = p;
		this.phasePlacement = true;
		this.phaseJeu = false;
		this.phaseVictoire = false;
		this.peutJouer = true;
	}
	
	public Partie getPartie() {
		return partie.clone();
	}
	
    public void rafraichissementDone() {
    	peutJouer = true;
    }
	
	public void placement(Coordonnees c) {
		partie.setPlacementPingouin(c, partie.joueurActif, partie.numPingouinAPlacer());
		
		phasePlacement = !partie.placementPingouinsFini();			
		if (!phasePlacement) {
			phaseJeu = true;
		}
		partie.majProchainJoueur();
		//Attendre IHM
		
		peutJouer = false;
		while (!peutJouer);
		
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
		//Attendte IHM

		peutJouer = false;
		while (!peutJouer);
		
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
