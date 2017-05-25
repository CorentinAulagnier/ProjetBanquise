package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.java.swing.plaf.motif.resources.motif_sv;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.Coordonnees;
import model.Humain;
import model.IA;
import model.Joueur;
import model.Moteur;
import model.Partie;
import reseau.Multijoueur;
import reseau.PingouinClient;
import reseau.PingouinServer;
import vue.EcranCourant;
import vue.GestionnaireEcransFxml;

public class ControleurCredits extends ControleurPere implements Initializable, EcranCourant {
	
	GestionnaireEcransFxml liste_Ecran;
						    
							
    @FXML private Button retour;
    
    /**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }

    @FXML
    public void retour(MouseEvent event){   
    	faireTourner(retour);
    	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
	/**
	 * implementation demande par l'interface EcranCourant : met a jour le noeud fxml parent associe a ce controleur
	 */
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	liste_Ecran = ecranParent;
    }
    
    public void miseAjour(){
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
