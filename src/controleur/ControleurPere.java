package controleur;

import vue.GestionnaireEcransFxml;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.application.Platform;

public class ControleurPere {
	
	/**
     * gere la modification de l'affichage d'un bouton lorsque la souris est presse
     * @param event evenement souris attendu : pressed
     */
    @FXML
    public void boutonPresse(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_PRESSED);
    }

    /**
     * gere la modification de l'affichage d'un bouton lorsque la souris est relache
     * @param evenement souris attendu : released 
     */
    @FXML
    public void boutonLache(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_NORMAL);
    }
    
    
    /**
	 * permet de fermer la roue si necessaire
	 * @param optionbox zone du menu que l'on doit cacher
	 * @param roue bouton provoquant l'action
	 */
    public void nettoyerMenu(AnchorPane optionbox, Button roue){
    	if (!(optionbox.isDisable())){
    		optionFermerRoue(optionbox, roue);
    	}
    }
	 
	  /**
	     * gere l'ouverture du menu d'options cache par le bouton roue
		 * @param optionbox zone du menu que l'on doit cacher
		 * @param roue bouton provoquant l'action
	     */
	 public void optionOuvrirRoue(AnchorPane optionbox, Button roue){
		 optionbox.setDisable(false);
 		FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
 		ft.setFromValue(0);
 		ft.setToValue(1);
 		
 		ScaleTransition st = new ScaleTransition(Duration.millis(500), optionbox);
     	st.setFromX(0);
     	st.setToX(1);
 		
     	RotateTransition rt = new RotateTransition(Duration.millis(500), roue);
 	     rt.setByAngle(180);
 	     
 		ParallelTransition pt = new ParallelTransition(optionbox,ft,rt,st/*,tt*/ );
 	     pt.play();
	    }
	 
		 /**
		     * gere la fermeture du menu d'options cache par le bouton roue
			 * @param optionbox zone du menu que l'on doit cacher
			 * @param roue bouton provoquant l'action
		     */
	 public void optionFermerRoue(AnchorPane optionbox, Button roue){
		 optionbox.setDisable(true);
		 	FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
	 		ft.setFromValue(1);
	 		ft.setToValue(0);
		 
	     	ScaleTransition st = new ScaleTransition(Duration.millis(500), optionbox);
	     	st.setFromX(1);
	     	st.setToX(0);
	     	
 		RotateTransition rt = new RotateTransition(Duration.millis(500), roue);
 	     rt.setByAngle(-180);
	     	ParallelTransition pt = new ParallelTransition(optionbox, ft, st ,rt/*,tt*/);
	     	pt.play();
	 }
	 
	 
	 /**
	  * gere la modification du volume de la musique
	  */
	 public void changerMusique(ImageView iv, MediaPlayer media, GestionnaireEcransFxml gestionnaireFxmlCourant){
		// TODO
		 if(gestionnaireFxmlCourant.musique){
			 gestionnaireFxmlCourant.musique = false;
			 media.pause();
			 iv.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEOFF_PATH));
			 System.out.println("couper Musique");
		 }
		 else{
			 gestionnaireFxmlCourant.musique = true;
			 media.seek(media.getStartTime());
	    		media.play();
			 iv.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEON_PATH));
			 System.out.println("remettre musique");
		 }
	 }
	 
	 /**
	  * gere la modification des bruitages
	  */
	 public void changerSon(ImageView iv, GestionnaireEcransFxml gestionnaireFxmlCourant){
		// TODO
		 if(gestionnaireFxmlCourant.son){
			 gestionnaireFxmlCourant.son = false;
			 iv.setImage(new Image(model.Proprietes.IMAGE_SONOFF_PATH));
			 System.out.println("couper Son");
		 }
		 else{
			 gestionnaireFxmlCourant.son = true;
			 iv.setImage(new Image(model.Proprietes.IMAGE_SONON_PATH));
			 System.out.println("remettre Son");
		 }
	 }
	 
	 /**
	  * Fonction pour ouvrir la fenetre de confirmation de fermeture de l'application (version jdk 8u40)
	  * @param titre titre de la fenetre
	  * @param contenu texte a afficher dans le corps de la fenetre
	  * @param boutonOk texte a afficher sur le bouton valider
	  * @param boutonNok texte a afficher sur le bouton annuler
	  * @param boutonPeutetre texte optionnel a afficher a la place du bouton non pour rendre fou l'utilisateur
	  */
	 public void alert_quitter(GestionnaireEcransFxml gestionnaireFxmlCourant, String titre, String contenu, String boutonOk, String boutonNok, String boutonPeutetre){
		 
		 Alert alert = new Alert(AlertType.CONFIRMATION);
		 alert.setHeaderText(null);
		 alert.setTitle(titre);
		 alert.setContentText(contenu);
		 
		 ButtonType buttonOui = new ButtonType(boutonOk);
	    	ButtonType buttonJoker = new ButtonType(boutonPeutetre, ButtonData.CANCEL_CLOSE);
	    	ButtonType buttonNon= new ButtonType(boutonNok, ButtonData.CANCEL_CLOSE);
	    	
	    	if(boutonNok.equals(""))
	    		alert.getButtonTypes().setAll(buttonJoker, buttonOui);
	    	else 
	    		alert.getButtonTypes().setAll(buttonNon, buttonOui );
	   
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == buttonOui){
	    		Platform.exit();
	    	} 
	    	else if (result.get() == buttonJoker){
	    		String contenu2 = "Vous etes vraiment sur ? Ils vont se sentir très très seuls...";
	    		alert_quitter(gestionnaireFxmlCourant, "Ne partez pas...", contenu2, "Partir sans un regard!", "D'accord je reste...", "");
	    	} else {
	    		gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
	    	}
	    	
	    }
}
