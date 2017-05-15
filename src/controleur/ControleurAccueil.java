package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    	netoyerEcran();
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
    }
    
    @FXML
    private void ouvrirNouvellePartie (MouseEvent event){
    	netoyerEcran();
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_MODE);
    }
    
    @FXML
    private void ouvrirPageCharger(MouseEvent event){
    	netoyerEcran();
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    @FXML
    private void quitter(MouseEvent event){
    	netoyerEcran();
    	String contenu = "Etes vous sur de vouloir quitter nos amis les pinguouins? Ils vont se sentir si seuls...";
    	popup_quitter("Bye bye ?", contenu, "Bien sûr", "" , "Euh.." );
    }
    
    private void popup_quitter(String titre, String contenu, String boutonOk, String boutonNok, String boutonPeutetre){
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText(null);
    	alert.setTitle(titre);
    	alert.setContentText(contenu);
    	
    	ButtonType buttonOui = new ButtonType(boutonOk);
    	ButtonType buttonJoker = new ButtonType(boutonPeutetre, ButtonData.CANCEL_CLOSE);
    	ButtonType buttonNon= new ButtonType(boutonNok, ButtonData.CANCEL_CLOSE);
    	
    	if(boutonNok.equals("")){
    		alert.getButtonTypes().setAll(buttonJoker, buttonOui);
    	} 
    	else {
    		alert.getButtonTypes().setAll(buttonNon, buttonOui );
    	}


    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == buttonOui){
    		Platform.exit();
    	} else if (result.get() == buttonJoker){
    		String contenu2 = "Vous etes vraiment sur ? Ils vont se sentir très très seuls...";
        	popup_quitter("Ne partez pas...", contenu2, "Partir sans un regard!", "D'accord je reste...", "");
    	} else {
    		gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    	}
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
    @FXML Button roue;
    
	 public void optionWheelOpen(){
		 optionbox.setDisable(false);
 		TranslateTransition tt = new TranslateTransition(Duration.millis(500), optionbox);
 	     tt.setByX(200); // A voir si on ne peut pas l'utiliser pour redimensionner la fenetre ???
 		FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
 		ft.setFromValue(0);
 		ft.setToValue(1);
 		RotateTransition rt = new RotateTransition(Duration.millis(500), roue);
 	     rt.setByAngle(180);
 		ParallelTransition pt = new ParallelTransition(optionbox,ft, tt,rt );
 	     pt.play();
	    }
	 public void optionWheelClose(){
		 optionbox.setDisable(true);
 		TranslateTransition tt = new TranslateTransition(Duration.millis(500), optionbox);
	     	tt.setByX(-200);
	     	FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
	     	ft.setFromValue(1);
	     	ft.setToValue(0);
 		RotateTransition rt = new RotateTransition(Duration.millis(500), roue);
 	     rt.setByAngle(-180);
	     	ParallelTransition pt = new ParallelTransition(optionbox, ft, tt,rt);
	     	pt.play();
	 }
 
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		optionWheelOpen();    		
    	}else{
    		optionWheelClose();
    	}
    }
    
    public void netoyerEcran(){
    	if (!(optionbox.isDisable())){
    		optionWheelClose();
    	}
    }
}