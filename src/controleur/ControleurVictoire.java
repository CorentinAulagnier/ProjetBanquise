package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class ControleurVictoire  implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;
 
	 @FXML 
	 private Button accueil;
	 @FXML 
	 private Button nouvelle_partie;
	 @FXML 
	 private Button nouvelle_manche;
	 public void miseAjour(){}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	accueil.setStyle(model.Proprietes.STYLE_NORMAL);
    	nouvelle_partie.setStyle(model.Proprietes.STYLE_NORMAL);
    	nouvelle_manche.setStyle(model.Proprietes.STYLE_NORMAL);
    }
    
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    }

    @FXML
    private void ouvrirPageMode(MouseEvent event){
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_MODE);
    }
    
    @FXML
    private void ouvrirPageJeu (MouseEvent event){
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_JEU);
    }
    
    @FXML
    private void ouvrirPageAccueil(MouseEvent event){
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    
    
/**-------------------------------------------PRESSION DES BOUTONS-------------------------------------------**/
    
    @FXML
    public void boutonPresse(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_PRESSED);
    }

    
    
    /**-------------------------------------------RELACHEMENT DES BOUTONS-------------------------------------------**/
    
    @FXML
    public void boutonLache(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_NORMAL);
    }
}