package controleur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import vue.*;

public class ControleurAccueil  implements Initializable, EnfantFxml {
	ChargeurFxml monChargeurFxml;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void fixeEcranParent(ChargeurFxml ecranParent){
    	monChargeurFxml = ecranParent;
    }

    @FXML
    private void ouvrirPageRegle(ActionEvent event){
    	System.out.println("regles");
    	monChargeurFxml.fixeEcran(Interface.ECRAN_REGLES);
    }
    
    @FXML
    private void ouvrirPageJeu (ActionEvent event){
    	System.out.println("jeu");
    	monChargeurFxml.fixeEcran(Interface.ECRAN_REGLES);//jeu à mettre à la place de regles
    }
    
    @FXML
    private void ouvrirPageCharger(ActionEvent event){
    	System.out.println("charger");
    	monChargeurFxml.fixeEcran(Interface.ECRAN_REGLES);//chargeur à mettre à la place de regles
    }
}