package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import vue.EcranCourant;
import vue.GestionnaireEcransFxml;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleurRegles implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;  
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    };
    
    @FXML
    private void ouvrirPageAcceuil(MouseEvent event){
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
}