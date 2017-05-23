package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

public class ControleurAccueil extends ControleurPere implements Initializable, EcranCourant {
	
	GestionnaireEcransFxml gestionnaireFxmlCourant;
	@FXML private Button jouer, charger, online, roue;
	@FXML private AnchorPane optionbox;
	@FXML private ImageView imageSon, imageMusique;
	
	/**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	jouer.setStyle(model.Proprietes.STYLE_NORMAL);
		charger.setStyle(model.Proprietes.STYLE_NORMAL);
		online.setStyle(model.Proprietes.STYLE_NORMAL);
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
     * change d'ecran pour celui d'une nouvelle partie
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirNouvellePartie (MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_MODE);
    	
    }
    
    /**
     * change d'ecran pour celui de l'acceuil multijoueur
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirMultijoueur (MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	gestionnaireFxmlCourant.chargeEcran(model.Proprietes.ECRAN_MENU_MULTI, model.Proprietes.ECRAN_MENU_MULTI_FXML);
		//System.out.println(gestionnaireFxmlCourant.get(model.Proprietes.ECRAN_MENU_MULTI));
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_MENU_MULTI);
    	
    }
    
    /**
     * ouvre l'ecran de chargement (actuellement ouvre un explorateur windows)
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageCharger(MouseEvent event){
    	// TODO gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_CHARGER);
    	// ou 
    	nettoyerMenu(optionbox, roue);
    	charger(this.gestionnaireFxmlCourant);
    }
    
    /**
     * change d'ecran pour celui des regles
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	gestionnaireFxmlCourant.dernierePage = model.Proprietes.ECRAN_ACCUEIL;
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
    }
    
    /**
     * gere l'ouverture ou la fermeture du menu roue
     * @param event evenement souris attendu : clic
     */
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		majBoutonMusique(gestionnaireFxmlCourant,imageMusique);
    		majBoutonSon(gestionnaireFxmlCourant,imageSon);
    		//TODO si ok nettoyer
    		if (gestionnaireFxmlCourant.musique == false){
        		imageMusique.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEOFF_PATH));
        	}else{
        		imageMusique.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEON_PATH));
        	}   
    		optionOuvrirRoue(optionbox, roue) ;
    	}else{
    		optionFermerRoue(optionbox, roue);
    	}
    }
    
    /**
     * gere la modification du volume de la musique
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererMusique(MouseEvent event){
    	changerMusique(imageMusique , gestionnaireFxmlCourant.media, gestionnaireFxmlCourant);
    }
    
    /**
     * gere la modification des bruitages
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererSon(MouseEvent event){
    	changerSon(imageSon, gestionnaireFxmlCourant);
    }
    
    /**
     * ouvre une popup de confirmatio avant de quitter l'application 
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void quitter(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	alert_quitter(gestionnaireFxmlCourant);
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