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
		this.aRafraichir = true;
	}
	
	public Partie getPartie() {
		return partie;
	}
	
	
	public void partieRafraichie() {
		aRafraichir = false;
	}
	
	public void partieARafraichir() {
		aRafraichir = true;
	}

/****************************************************************************************/
	
	public void placement(Coordonnees c) {
    	System.out.println("Placement en "+c);

		partie.setPlacementPingouin(c, partie.joueurActif, partie.numPingouinAPlacer());
		
		phasePlacement = !partie.placementPingouinsFini();			
		if (!phasePlacement) {
			phaseJeu = true;
		}
		partie.majProchainJoueur();
		//Attendre IHM

		this.partieARafraichir();
    	System.out.println("partieARafraichir");

    	System.out.println(partie);

		if (partie.getJoueurActif() instanceof IA) {
	    	System.out.println("attente rafraichissement");
	    	
	    	
            while(true) {
	            if(!aRafraichir) break;
            }//while (aRafraichir);
			faireJouerIAS();
			
		}
	}
	
	
	public void deplacement(CoupleGenerique<Coordonnees, Coordonnees> cc) {
    	System.out.println("Deplacement en "+cc);

		partie.deplacement(cc);
		
		phaseJeu = !partie.jeuPingouinsFini();			
		if (!phaseJeu) {
			phaseVictoire = true;
		}
		partie.majProchainJoueur();
		//Attendre IHM

		this.partieARafraichir();
    	System.out.println("partieARafraichir");

		
		if (partie.getJoueurActif() instanceof IA) {
	    	System.out.println("attente rafraichissement");

	    	
            while(true) {
	            if(!aRafraichir) break;
            }//while (aRafraichir);
			faireJouerIAS();
		}
		
	}
	
/****************************************************************************************/

    
    public void faireJouerIAS() {
    	Joueur j = partie.getJoueurActif();
    	
    	if (phasePlacement) {
        	System.out.println("avant placement ");

			placement(j.placement(partie));
	    	System.out.println("apres placement");

		} else if(phaseJeu) {
			deplacement(j.jouer(partie));
		}
	}

	public void execPremiereIA() {
		if (this.partie.getJoueurActif() instanceof IA) {
            while(true) {
	            if(!aRafraichir) break;
            }//while (aRafraichir);
			this.faireJouerIAS();
		}		
	}
}
