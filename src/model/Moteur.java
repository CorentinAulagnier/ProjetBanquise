package model;


public class Moteur {
    
	public Partie partie;
	public boolean phaseJeu, phasePlacement, phaseVictoire;
	public boolean aRafraichir, IAProchainJoueur;
	
	public Moteur(Partie p) {
		this.partie = p;
		this.phasePlacement = false;
		this.phaseJeu = false;
		this.phaseVictoire = false;
		this.aRafraichir = true;
		this.IAProchainJoueur = false;
		verifPhase();
		//System.out.println("phasePlacement : "+phasePlacement+" phaseJeu : "+phaseJeu+" phaseVictoire : "+phaseVictoire);
	}
	
	public void verifPhase() {
		phasePlacement = !this.partie.placementPingouinsFini();			
		if (!phasePlacement) {
			phaseJeu = !this.partie.jeuPingouinsFini();	
			if (!phaseJeu) {
				phaseVictoire = true;
			}
		}
	}
	
	/**
	 * Renvoie la partie en cours
	 * 
	 * @return la partie en cours
	 */
	
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
	
	/**
	 * Effectue le placement d'un pingouins et met a jour la partie
	 * 
	 * @param c
	 * 			Les coordonnees de la case du pingouin
	 */
	
	public void placement(Coordonnees c) {
		System.out.println("placement : "+c);
		partie.setPlacementPingouin(c, partie.joueurActif, partie.numPingouinAPlacer());
		
/*************/
System.out.println("Placement en "+c);
System.out.println(partie);
/*************/
		
		phasePlacement = !partie.placementPingouinsFini();			
		if (!phasePlacement) {
			phaseJeu = true;
		}
		
		//Passage joueur suivant (majProchainJoueur a ete modifie)
		partie.joueurActif = (partie.joueurActif+1)%partie.nbJoueurs;
		
		//Message IHM
		this.partieARafraichir();

		if (partie.getJoueurActif() instanceof IA) {
			//this.IAProchainJoueur = true;
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}*/
			/*
			while (true) {
				synchronized (partie) {
					if (!aRafraichir) {break;}	
				}
			}*/
			faireJouerIAS();
		}
	}
	
	/**
	 * Effectue le deplacement d'un pingouins et met a jour la partie
	 * 
	 * @param cc
	 * 			Les coordonnees de la case de depart et d'arrive du pingouin
	 */
	
	public void deplacement(CoupleGenerique<Coordonnees, Coordonnees> cc) {
		partie.deplacement(cc);
		
/*************/
System.out.println("Deplacement en "+cc);
System.out.println(partie);
/*************/
    	
		phaseJeu = !partie.jeuPingouinsFini();	
		
		if (!phaseJeu) {
			phaseVictoire = true;
			
		} else {
			//Suppression pingouins inactif
			partie.verifierPingouinActif();
			
			//Maj prochain joueur (on passe les joueurs innactifs)
			partie.majProchainJoueur();
		
			//Message IHM
			this.partieARafraichir();
	
			if (partie.getJoueurActif() instanceof IA) {
				//this.IAProchainJoueur = true;
				/*
				while (true) {
					if (!aRafraichir) {break;}
				}*/
				faireJouerIAS();
			}
		}
	}
	
/****************************************************************************************/

	/**
	 * Fait jouer une IA si c'est elle qui lance la partie
	 */
	
	public void execPremiereIA() {
		if (this.partie.getJoueurActif() instanceof IA) {
			this.faireJouerIAS();
		}		
	}
    
	/**
	 * Fait jouer une IA suivant la phase de jeu ou l'on se trouve
	 */
	
    public void faireJouerIAS() {
    	Joueur j = partie.getJoueurActif();
    	System.out.println("Tour IA");

    	if (phasePlacement) {
			placement(j.placement(partie));

		} else if(phaseJeu) {
			deplacement(j.jouer(partie));
		}
	}
    
    public CoupleGenerique<Coordonnees, Coordonnees> demanderIndice() {
    	Partie p = this.partie.clone();
    	Joueur j = p.getJoueurActif();
    	
    	IA i = new IA(j.nom, j.nbPingouin, 2, j.cheminMiniature, j.couleur);
    	i.myPingouins = j.myPingouins;
    	i.nbTuiles = j.nbTuiles;
    	i.poissonsManges = j.poissonsManges;
    	
    	p.joueurs[p.joueurActif] = i;
    	return i.jouer(p);
    }

}
