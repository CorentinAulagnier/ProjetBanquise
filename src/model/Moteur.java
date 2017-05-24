package model;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.animation.Timeline;


public class Moteur implements Serializable {
    
	private static final long serialVersionUID = 1L;

	
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

/*******************************************************************************************************/

	/**
	 * Clone de le moteur actuel.
	 * 
	 * @return Renvoie le moteur clonne
	 */
	
	@Override
	public Moteur clone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
	
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Moteur) ois.readObject();
		} catch (Exception e){
			return null;
		}
	}

/****************************************************************************************/
	
	/**
	 * Effectue le placement d'un pingouins et met a jour la partie
	 * 
	 * @param c
	 * 			Les coordonnees de la case du pingouin
	 */
	
	public void placement(Coordonnees c) {
		partie.setPlacementPingouin(c, partie.joueurActif, partie.numPingouinAPlacer());
		
/*************
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
	}
	
	/**
	 * Effectue le deplacement d'un pingouins et met a jour la partie
	 * 
	 * @param cc
	 * 			Les coordonnees de la case de depart et d'arrive du pingouin
	 */
	
	public void deplacement(CoupleGenerique<Coordonnees, Coordonnees> cc) {
		partie.deplacement(cc);
		
/*************
System.out.println("Deplacement en "+cc);
System.out.println(partie);
/*************/

		//Suppression pingouins inactif
		partie.verifierPingouinActif();
		
		phaseJeu = !partie.jeuPingouinsFini();
		if (!phaseJeu) {
			phaseVictoire = true;
			
		}

		
		//Maj prochain joueur (on passe les joueurs innactifs)
		partie.majProchainJoueur();
	
		//Message IHM
		this.partieARafraichir();
	}
	
/****************************************************************************************/

	/**
	 * Fait jouer une IA si c'est elle qui lance la partie
	 */
	
	/*public void execPremiereIA() {
		if (this.partie.getJoueurActif() instanceof IA) {
			this.faireJouerIAS();
		}		
	}*/
    
	/**
	 * Fait jouer une IA suivant la phase de jeu ou l'on se trouve
	 */
	
    public void faireJouerIAS(Timeline timeline) {
    	timeline.stop();
    	Joueur j = partie.getJoueurActif();

    	if (phasePlacement) {
			placement(j.placement(partie));

		} else if(phaseJeu) {
			deplacement(j.jouer(partie));
		}
    	timeline.play();
	}
    
	/**
	 * Fait jouer une IA a la place d un joueur pour lui proposer un coup
	 * 
	 * @return Un couple generique correspondant a un deplacement
	 */
    
    public CoupleGenerique<Coordonnees, Coordonnees> demanderIndice() {

    	Joueur j = this.partie.getJoueurActif();
    	IA i = new IA("", j.nbPingouin, 2);
    	i.myPingouins = j.myPingouins;
    	i.nbTuiles = j.nbTuiles;
    	i.poissonsManges = j.poissonsManges;
    	
    	if (phasePlacement) {
    		return new CoupleGenerique<Coordonnees, Coordonnees>(new Coordonnees(-1, -1), i.placement(this.partie));

		} else {
			return i.jouer(this.partie);
		}
    }

}
