package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import vue.EcranCourant;
import vue.GestionnaireEcransFxml;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleurRegles extends ControleurPere implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;  
	
	@FXML private Button accueil, precedent, suivant;
	
	/**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	accueil.setStyle(model.Proprietes.STYLE_NORMAL);
    	precedent.setStyle(model.Proprietes.STYLE_NORMAL);
    	suivant.setStyle(model.Proprietes.STYLE_NORMAL);
    }
    
    /**
     * implementation demande par l'interface EcranCourant : vide car n'a pas d'utilite ici
     */
	public void miseAjour(){}
	 
	/**
	 * implementation demande par l'interface EcranCourant : met a jour le noeud fxml parent associe a ce controleur
	 */
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    }
    
    /**
     * ouvre l'ecran d'accueil
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageAcceuil(MouseEvent event){
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    /**
     * affiche la page de regle suivante
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void pageSuivante(MouseEvent event){
    	//maj sous titre
    	//maj texte
    	//maj image principale
    	//maj reglette petit point
    }
    
    /**
     * affiche la page de regle précédente
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void pagePrecedente(MouseEvent event){
    	//maj sous titre
    	//maj texte
    	//maj image principale
    	//maj reglette petit point
    }
    
    /*
    @FXML public void boutonPresse(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_PRESSED);
    }
   
    @FXML
    public void boutonLache(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_NORMAL);
    }
    */
}