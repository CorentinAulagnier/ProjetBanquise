package vue;

import model.Partie;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Interface extends Application {   
    
	public static int largeurFenetre;
	public static int hauteurFenetre;
	public static Partie partie;  

	public static void creer(String[] args,Partie p,int h, int l) {
    	partie = p;
    	hauteurFenetre = h*100 +30;
    	largeurFenetre = l*100;
        Application.launch(Interface.class, args);
    }
	
	@Override
	public void start(Stage primaryStage) {
		
		 
		GestionnaireEcransFxml gestionnaireEcransFXML = new GestionnaireEcransFxml(partie);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_ACCUEIL, model.Proprietes.ECRAN_ACCUEIL_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_REGLES, model.Proprietes.ECRAN_REGLES_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_MODE, model.Proprietes.ECRAN_MODE_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_JEU, model.Proprietes.ECRAN_JEU_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_VICTOIRE, model.Proprietes.ECRAN_VICTOIRE_FXML);
		
		
		String path = "src/ressources/decor/ping.mp3";
		Media media = new Media(new File(path).toURI().toString());
		gestionnaireEcransFXML.musique = new MediaPlayer(media);
		gestionnaireEcransFXML.musique.setAutoPlay(true);
		gestionnaireEcransFXML.musique.play();
        
		gestionnaireEcransFXML.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
        
        Group root = new Group();
        root.getChildren().addAll(gestionnaireEcransFXML);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        primaryStage.setHeight(hauteurFenetre);
        primaryStage.setWidth(largeurFenetre);
        primaryStage.getIcons().add(new Image("ressources/decor/favicon.png"));
        primaryStage.setTitle("Pinguouins");
        primaryStage.setResizable(false);
        primaryStage.show();
	    }
	

}
