package model;


public class Moteur {
    
	public Partie partie;
	public boolean phaseJeu, phasePlacement, phaseVictoire;
	public boolean aRafraichir;
	
	public Moteur(Partie p) {
		this.partie = p;
		this.phasePlacement = true;
		this.phaseJeu = false;
		this.phaseVictoire = false;
		this.aRafraichir = false;
		
		if (partie.getJoueurActif() instanceof IA) {
			faireJouerIAS();
		}
	}
	
	public Partie getPartie() {
		return partie;
	}

	public void placement(Coordonnees c) {
		partie.setPlacementPingouin(c, partie.joueurActif, partie.numPingouinAPlacer());
		
		phasePlacement = !partie.placementPingouinsFini();			
		if (!phasePlacement) {
			phaseJeu = true;
		}
		partie.majProchainJoueur();
		//Attendre IHM
		
		aRafraichir = true;
		
		if (partie.getJoueurActif() instanceof IA) {
			while (aRafraichir);
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
		//Attendre IHM

		aRafraichir = true;
		
		if (partie.getJoueurActif() instanceof IA) {
			while (aRafraichir);
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
