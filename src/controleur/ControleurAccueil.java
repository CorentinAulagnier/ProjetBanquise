package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


public class ControleurAccueil  implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;
 
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
}