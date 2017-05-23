package controleur;

import vue.GestionnaireEcransFxml;
import model.Moteur;
import model.Partie;

import java.io.File;
import java.util.Optional;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
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
import javafx.stage.FileChooser;
import javafx.application.Platform;

public class ControleurPere {

	final FileChooser fileChooserCharger = new FileChooser();
	
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
	 * gere l'ouverture du menu d'options cache par le bouton roue	 * 
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
	  * gere la modification du bouton musique
	  */
	 public void majBoutonMusique(GestionnaireEcransFxml liste_ecran, ImageView imageMusique){
		 if (liste_ecran.musique == false){
     		imageMusique.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEOFF_PATH));
     	}else{
     		imageMusique.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEON_PATH));
     	}    
	 }
	 
	 /**
	  * gere la modification du volume de la musique
	  */
	 public void changerMusique(ImageView iv, MediaPlayer media, GestionnaireEcransFxml liste_ecran){
		// TODO
		 if(liste_ecran.musique){
			 liste_ecran.musique = false;
			media.pause();
			iv.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEOFF_PATH));
			 System.out.println("couper Musique");
		 }
		 else{
			 liste_ecran.musique = true;
			 media.seek(media.getStartTime());
			 media.play();
			 iv.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEON_PATH));
			 System.out.println("remettre musique");
		 }
		 majBoutonMusique(liste_ecran,iv);
	 }
	 
	 /**
	  * gere la modification du bouton musique
	  */
	 public void majBoutonSon(GestionnaireEcransFxml liste_ecran, ImageView imageSon){
		 if (liste_ecran.son == false){
			 imageSon.setImage(new Image(model.Proprietes.IMAGE_SONOFF_PATH));
     	}else{
     		imageSon.setImage(new Image(model.Proprietes.IMAGE_SONON_PATH));
     	}    
	 }
	 
	 /**
	  * gere la modification des bruitages
	  */
	 public void changerSon(ImageView iv, GestionnaireEcransFxml liste_ecran){
		// TODO
		 if(liste_ecran.son){
			 liste_ecran.son = false;
			// iv.setImage(new Image(model.Proprietes.IMAGE_SONOFF_PATH));
			 System.out.println("couper Son");
		 }
		 else{
			 liste_ecran.son = true;
			// iv.setImage(new Image(model.Proprietes.IMAGE_SONON_PATH));
			 System.out.println("remettre Son");
		 }
		 majBoutonSon(liste_ecran,iv);
	 }
	 
	 /**
	  * Fonction pour ouvrir la fenetre de confirmation de fermeture de l'application (version jdk 8u40)
	  * @param titre titre de la fenetre
	  * @param contenu texte a afficher dans le corps de la fenetre
	  * @param boutonOk texte a afficher sur le bouton valider
	  * @param boutonNok texte a afficher sur le bouton annuler
	  * @param boutonPeutetre texte optionnel a afficher a la place du bouton non pour rendre fou l'utilisateur
	  */
	 //TODO a dégager
	 public void alert_quitter(GestionnaireEcransFxml liste_ecran, String titre, String contenu, String boutonOk, String boutonNok, String boutonPeutetre){
		 
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
	    		alert_quitter(liste_ecran, "Ne partez pas...", contenu2, "Partir sans un regard!", "D'accord je reste...", "");
	    	} else {
	    		liste_ecran.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
	    	}
	    	
	    }
	 
	/**
	 * Fonction pour ouvrir la fenetre de confirmation de fermeture de l'application (version jdk 8u40)
	 * @param liste_ecran noeud FXML
	 */
	public void alert_quitter(GestionnaireEcransFxml liste_ecran) {
		String titre = "Vous partez ?";
		String contenu = "Etes vous sur de vouloir quitter nos amis les pingouins? Ils vont se sentir si seuls...";
		String boutonOk = "Quitter";
		String boutonNOk = "Annuler";
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle(titre);
		alert.setContentText(contenu);

		ButtonType buttonOui = new ButtonType(boutonOk, ButtonData.OK_DONE);
		ButtonType buttonNon = new ButtonType(boutonNOk, ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonNon, buttonOui);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonOui) {
			//TODO vider le gestionnaireFXML
			Platform.exit();
		} 
		else {
			//liste_ecran.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
		}
	}
	
	/**
	 * Fonction pour ouvrir la fenetre de confirmation de retour à l'accueil (version jdk 8u40)
	 * @param liste_Ecran noeud FXML
	 * @param ecran nom de l'écran que l'on quitte
	 */
	public void alertAccueil(GestionnaireEcransFxml liste_Ecran, String ecran){
		String titre = "Quitter partie";
		String contenu = "Etes vous sur de vouloir quitter cette partie?";
		String boutonOk = "Oui";
		String boutonNOk = "Annuler";
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle(titre);
		alert.setContentText(contenu);

		ButtonType buttonOui = new ButtonType(boutonOk, ButtonData.OK_DONE);
		ButtonType buttonNon = new ButtonType(boutonNOk, ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonNon, buttonOui);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonOui) {
			liste_Ecran.retireEcran(ecran);
	    	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
		}     	
	}
 
	/**
	 * appeler la sauvegarde du jeu
	 * @param moteur 
	 */
	public void sauver(Moteur moteur) {
		final FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showSaveDialog(null);
		try{
			String path = file.getName();
			if (file != null) {
				path = file.getAbsolutePath();
				// TODO
				moteur.partie.sauvegarder(path);
			}
		}catch(Exception e){}
	}

	/**
	 * appeler le chargement d'une partie
	 */
	public void charger(GestionnaireEcransFxml liste_Ecran) {
		File file = fileChooserCharger.showOpenDialog(null);
        if (file != null) {
        	String  path = file.getName();
        	path = file.getAbsolutePath();
			Partie p = new Partie();
        	try {
        		//System.out.println(path.substring(path.length()-9, path.length()));
        		if ((path.substring(path.length()-9, path.length())).equals(".banquise")) {
        			p.charger(path);
        			
        		} else {
        			p.chargerTXT(path);

        		}
        	} catch (Exception e) {
        	}
			liste_Ecran.moteur = new Moteur(p);

        	liste_Ecran.chargeEcran(model.Proprietes.ECRAN_JEU, model.Proprietes.ECRAN_JEU_FXML);
        	
        	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_JEU);
            //System.out.println(liste_Ecran.moteur.partie.toString2());
        }   
	}
	
	/**
	 * charger la page règles
	 * @param liste_Ecran noeud FXML
	 * @param ecranAppelant écran d'où on a appelé les règles
	 */
	public void appelerRegles(GestionnaireEcransFxml liste_Ecran, String ecranAppelant) {
		liste_Ecran.dernierePage = ecranAppelant;
		liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
	}
}
