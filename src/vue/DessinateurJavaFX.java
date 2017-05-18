package vue;

import model.Banquise;
import model.Joueur;
import model.Partie;
import model.Pingouin;
import model.Tuile;
import model.Visiteur;

public class DessinateurJavaFX extends Visiteur {

	GestionnaireEcransFxml listeEcrans ;
	
	/**
	 * effectue le raffraichissement graphique du noeud FXML en debut de la liste d'écrans en mémoire
	 * @param l GestionnaireEcransFxml du jeu
	 */
	public DessinateurJavaFX(GestionnaireEcransFxml l) {
		listeEcrans = l;
    }
	
	/**
	 * effectue le raffraichissement graphique de ce qui compose une partie
	 * @param p une partie
	 * @return faux par défaut
	 */
	@Override public boolean visite(Partie p) {
    	//TODO
		return false;
	}
    
    /**
	 * effectue le raffraichissement graphique de ce qui compose une banquise
	 * @param p une partie
	 * @return faux par défaut
	 */
	@Override public boolean visite(Banquise b) {
    	//TODO
		return false;
	}
    
    /**
	 * effectue le raffraichissement graphique de ce qui compose un joueur à l'écran
	 * @param p une partie
	 * @return faux par défaut
	 */
	@Override public boolean visite(Joueur j) {
    	//TODO
		return false;
    }
    
    /**
	 * effectue le raffraichissement graphique d'un pingouin
	 * @param p une partie
	 * @return faux par défaut
	 */
	@Override public boolean visite(Pingouin p) {
    	//TODO
		return false;
	}
    
    /**
	 * effectue le raffraichissement graphique d'une tuile de la banquise
	 * @param p une partie
	 * @return faux par défaut
	 */
	@Override public boolean visite(Tuile t) {
    	//TODO
		return false;
    }
	
    

}
