package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;


public class ControleurAccueil  implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;
 
	 @FXML 
	 private Button jouer;
	 @FXML 
	 private Button charger;
	 @FXML 
	 private Button regles;
	 
	
	 public void miseAjour(){}
	 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	jouer.setStyle(model.Proprietes.STYLE_NORMAL);
		charger.setStyle(model.Proprietes.STYLE_NORMAL);
		regles.setStyle(model.Proprietes.STYLE_NORMAL);
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
    
    @FXML AnchorPane optionbox;
    
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		optionbox.setDisable(false);
    		TranslateTransition tt = new TranslateTransition(Duration.millis(500), optionbox);
    	     tt.setByX(200);
    		FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
    		ft.setFromValue(0);
    		ft.setToValue(1);
    		ParallelTransition pt = new ParallelTransition(optionbox, ft, tt);
    	     pt.play();
    		
    	}else{
    		optionbox.setDisable(true);
    		TranslateTransition tt = new TranslateTransition(Duration.millis(500), optionbox);
   	     	tt.setByX(-200);
   	     	FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
   	     	ft.setFromValue(1);
   	     	ft.setToValue(0);
   	     	ParallelTransition pt = new ParallelTransition(optionbox, ft, tt);
   	     	pt.play();
    	}
    }
}