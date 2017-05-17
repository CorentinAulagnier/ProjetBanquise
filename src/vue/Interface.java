package vue;

import model.Partie;

import java.io.File;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Interface extends Application {   
    
	public static int largeurFenetre;
	public static int hauteurFenetre;
	public static Partie partie;  
	public static int largeurInitiale;
	public static int hauteurInitiale;

	public static void creer(String[] args,Partie p,int h, int l) {
    	partie = p;
    	hauteurFenetre = h*100 +30;
    	largeurFenetre = l*100;
    	largeurInitiale = largeurFenetre ;
    	hauteurInitiale = hauteurFenetre;
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
		
		
		/*String path = "src/ressources/decor/christmas.mp3";
		Media media = new Media(new File(path).toURI().toString());
		gestionnaireEcransFXML.media = new MediaPlayer(media);
		
		gestionnaireEcransFXML.media.setAutoPlay(true);
		gestionnaireEcransFXML.musique=true;
		gestionnaireEcransFXML.media.play();*/
        
		gestionnaireEcransFXML.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
        
        Group root = new Group();
        root.getChildren().addAll(gestionnaireEcransFXML);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        primaryStage.setHeight(hauteurFenetre);
        primaryStage.setWidth(largeurFenetre);
        primaryStage.getIcons().add(new Image("ressources/decor/favicon.png"));
        primaryStage.setTitle("Pinguouins");
        primaryStage.setResizable(true);
        scene.setRoot(root);
        primaryStage.setScene(scene);
        gestionnaireEcransFXML.autosize();
       // scene.setCamera();
        primaryStage.show();
        
        
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
        	@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                ScaleTransition st = new ScaleTransition(Duration.ONE, gestionnaireEcransFXML);
                TranslateTransition tt = new TranslateTransition(Duration.ONE, gestionnaireEcransFXML);
               
                double ratio = (newSceneWidth.doubleValue() - largeurInitiale ) /2 ;
                
                st.setToX( newSceneWidth.doubleValue()/ largeurInitiale);
                tt.setToX(ratio);
            
                ParallelTransition pt = new ParallelTransition(gestionnaireEcransFXML, st, tt);
             	pt.play();
             	
        	}
        });
        
      primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                ScaleTransition st = new ScaleTransition(Duration.ONE, gestionnaireEcransFXML);
                TranslateTransition tt = new TranslateTransition(Duration.ONE, gestionnaireEcransFXML);
                
                double ratio = (newSceneHeight.doubleValue()-hauteurInitiale) /2 ;
                
                st.setToY( newSceneHeight.doubleValue()/ hauteurInitiale);
                tt.setToY( ratio );
                
             	ParallelTransition pt = new ParallelTransition(gestionnaireEcransFXML, st ,tt);
    	     	pt.play();
             	
            }
        });
        
	    }
	

}
