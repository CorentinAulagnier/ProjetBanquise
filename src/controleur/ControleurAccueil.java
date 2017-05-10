package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class ControleurAccueil  implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;
 
	 @FXML 
	 private Button jouer;
	 @FXML 
	 private Button charger;
	 @FXML 
	 private Button regles;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    }

    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
    }
    
    @FXML
    private void ouvrirPageJeu (MouseEvent event){
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_MODE);
    }
    
    @FXML
    private void ouvrirPageCharger(MouseEvent event){
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    
    
    /** GESTION PRESSION DE SBOUTONS**/ /*A MODIFIER COMME PADDING QUI BOUGE BOUTON AUSSI FAUT AJUSTER LE PADDING DES AUTRES*/
    @FXML
    public void lancerpresse(MouseEvent event){
    	jouer.setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void chargerpresse(MouseEvent event){
    	charger.setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void reglespresse(MouseEvent event){
    	regles.setStyle(model.Proprietes.STYLE_PRESSED);
    }
    
    @FXML
    public void lancerlache(MouseEvent event){
    	jouer.setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void chargerlache(MouseEvent event){
    	charger.setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void regleslache(MouseEvent event){
    	regles.setStyle(model.Proprietes.STYLE_NORMAL);
    }
}