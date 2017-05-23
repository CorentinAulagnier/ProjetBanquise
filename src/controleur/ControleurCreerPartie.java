package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Coordonnees;
import reseau.PingouinClient;
import vue.EcranCourant;
import vue.GestionnaireEcransFxml;

public class ControleurCreerPartie extends ControleurPere implements Initializable, EcranCourant {
	
	GestionnaireEcransFxml liste_Ecran;
	
    //zone menu
    @FXML private ImageView imageSon, imageMusique;
    @FXML AnchorPane optionbox;
    @FXML Button roue;
	
    /**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    /**
     * implementation demande par l'interface EcranCourant : vide car n'a pas d'utilite ici
     */
	public void miseAjour(){}
	 
	/**
	 * implementation demande par l'interface EcranCourant : met a jour le noeud fxml parent associe a ce controleur
	 */
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	liste_Ecran = ecranParent;
    }
    
    /**
     * gere la modification de l'affichage d'un bouton lorsque la souris est presse
     * @param event evenement souris attendu : pressed
     */
    @FXML
    public void boutonPresse(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_PRESSED);
    	/*
    	if (gestionnairefxml.son){
    	MediaPlayer media = new MediaPlayer(new Media(new File(model.Proprietes.BUTTON_PATH).toURI().toString()));
		media.play();
		}
		*/
    	
    }
}