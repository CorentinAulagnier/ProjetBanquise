package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import vue.EcranCourant;
import vue.GestionnaireEcransFxml;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleurRegles extends ControleurPere implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant; 
	int pageRegles = 0;
	
	@FXML private Button accueil, precedent, suivant;
	@FXML private Text reglesTitre, reglesCorps;
	@FXML private Circle cercle1, cercle2, cercle3, cercle4, cercle5;
	
	/**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	pageRegles=0;
    	precedent.setVisible(false);
    	suivant.setVisible(true);
    	reglesTitre.setText(model.Proprietes.reglesTitres[pageRegles]);
    	reglesCorps.setText(model.Proprietes.reglesCorps[pageRegles]);
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
    private void retour(MouseEvent event){
    	faireTourner(accueil);
    	gestionnaireFxmlCourant.changeEcranCourant(gestionnaireFxmlCourant.dernierePage);
    }
    
    /**
     * gere l'affichage des fleches de navigation entre les ecrans de règles.
     */
    public void visibiliteBoutons(){
    	if(pageRegles == 0){
    		precedent.setVisible(false);
    	}
    	else if(pageRegles == model.Proprietes.reglesTitres.length-1){
    		suivant.setVisible(false);
    	}
    	else{
    		suivant.setVisible(true);
    		precedent.setVisible(true);
    	}
    }
    
    /**
     * Gere la mise à jour des composnats de l'ecran regle en fonction de la page demande
     */
    public void metAjourRegle(){
    	visibiliteBoutons(); // maj fleches
    	reglesTitre.setText(model.Proprietes.reglesTitres[pageRegles]); //maj titre
    	reglesCorps.setText(model.Proprietes.reglesCorps[pageRegles]); //maj texte	  
    	
    	//TODO maj image principale
    	
    	switch(pageRegles){	//maj reglette petit point
    		case 0:	cercle1.setFill(Color.BLACK); cercle2.setFill(Color.TRANSPARENT);cercle3.setFill(Color.TRANSPARENT);cercle4.setFill(Color.TRANSPARENT);cercle5.setFill(Color.TRANSPARENT);break;
    		case 1:	cercle2.setFill(Color.BLACK); cercle1.setFill(Color.TRANSPARENT);cercle3.setFill(Color.TRANSPARENT);cercle4.setFill(Color.TRANSPARENT);cercle5.setFill(Color.TRANSPARENT);break;
    		case 2:	cercle3.setFill(Color.BLACK); cercle2.setFill(Color.TRANSPARENT);cercle1.setFill(Color.TRANSPARENT);cercle4.setFill(Color.TRANSPARENT);cercle5.setFill(Color.TRANSPARENT);break;
    		case 3:	cercle4.setFill(Color.BLACK); cercle2.setFill(Color.TRANSPARENT);cercle3.setFill(Color.TRANSPARENT);cercle1.setFill(Color.TRANSPARENT);cercle5.setFill(Color.TRANSPARENT);break;
    		case 4:	cercle5.setFill(Color.BLACK); cercle2.setFill(Color.TRANSPARENT);cercle3.setFill(Color.TRANSPARENT);cercle4.setFill(Color.TRANSPARENT);cercle1.setFill(Color.TRANSPARENT);break;
    	}
    }
    
    /**
     * affiche la page de regle suivante
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void pageSuivante(MouseEvent event){
    	pageRegles++;
    	metAjourRegle();
    }
    
    /**
     * affiche la page de regle précédente
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void pagePrecedente(MouseEvent event){
    	pageRegles--;
    	metAjourRegle();
    }
}