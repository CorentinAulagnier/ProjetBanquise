package controleur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import vue.*;

public class ControleurRegles  implements Initializable, EnfantFxml {
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
    	monChargeurFxml.fixeEcran(Interface.ECRAN_ACCUEIL);
    }
    
}