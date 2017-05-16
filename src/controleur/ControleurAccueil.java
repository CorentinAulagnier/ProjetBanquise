package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;


public class ControleurAccueil extends ControleurPere implements Initializable, EcranCourant {
	
	GestionnaireEcransFxml gestionnaireFxmlCourant;
	@FXML private Button jouer, charger, regles, roue;
	@FXML private AnchorPane optionbox;
	@FXML private ImageView imageSon, imageMusique;
	final FileChooser fileChooser = new FileChooser();
	
	/**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	jouer.setStyle(model.Proprietes.STYLE_NORMAL);
		charger.setStyle(model.Proprietes.STYLE_NORMAL);
		regles.setStyle(model.Proprietes.STYLE_NORMAL);
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
    	nettoyerRoue(optionbox, roue);
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_MODE);
    	
    }
    
    /**
     * ouvre l'ecran de chargement (actuellement ouvre un explorateur windows)
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageCharger(MouseEvent event){
    	// gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_CHARGER);
    	// ou 
    	
    	nettoyerRoue(optionbox, roue);
    	File file = fileChooser.showOpenDialog(null);
    	String  path = file.getName();
        if (file != null) {
        	path = file.getAbsolutePath();
        	gestionnaireFxmlCourant.partie.charger(path);
        	//gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_JEU_FXML);
        }    	
    }
    
    /**
     * change d'ecran pour celui des regles
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	nettoyerRoue(optionbox, roue);
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
    }
    
    
    
    /**
     * gere l'ouverture ou la fermeture du menu roue
     * @param event evenement souris attendu : clic
     */
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		optionOuvrirRoue(optionbox, roue);    		
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
    	changerMusique(imageMusique , gestionnaireFxmlCourant.musique);
    	
    }
    
    /**
     * gere la modification des bruitages
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererSon(MouseEvent event){
    	changerSon(imageSon);
    }
    
    /**
     * ouvre une popup de confirmatio avant de quitter l'application 
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void quitter(MouseEvent event){
    	nettoyerRoue(optionbox, roue);
    	String contenu = "Etes vous sur de vouloir quitter nos amis les pinguouins? Ils vont se sentir si seuls...";
    	alert_quitter(gestionnaireFxmlCourant, "Bye bye ?", contenu, "Bien sur", "" , "Euh.." );
    }
    
    
}