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
        primaryStage.show();
        
        
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
        	@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                TranslateTransition tt = new TranslateTransition(Duration.ONE, gestionnaireEcransFXML);
                double ratio = (newSceneWidth.doubleValue() - largeurFenetre ) /2 ;
                double decalage = oldSceneWidth.doubleValue() / largeurFenetre ;
                tt.setToX(ratio-decalage);
                
                if (primaryStage.getHeight() > (6*primaryStage.getWidth()/8) ){ 
                	ScaleTransition st = new ScaleTransition(Duration.ONE, gestionnaireEcransFXML);
                	 st.setToX( newSceneWidth.doubleValue()/ largeurFenetre);
	                ScaleTransition st2 = new ScaleTransition(Duration.ONE, gestionnaireEcransFXML);
	                TranslateTransition tt2 = new TranslateTransition(Duration.ONE, gestionnaireEcransFXML);
	                double fract = (6*newSceneWidth.doubleValue())/8;
	                double ratio2 = (fract-hauteurFenetre) /2 ;
	                st2.setToY( fract / hauteurFenetre);
	                tt2.setToY( ratio2 );
	            
	                ParallelTransition pt = new ParallelTransition(gestionnaireEcransFXML, st, tt, st2, tt2);
	                pt.play();
	                
                }else{
                	
                	tt.play();
                }
             	
        	}
        });
        
      primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
            	TranslateTransition tt = new TranslateTransition(Duration.ONE, gestionnaireEcransFXML);
                double ratio = (newSceneHeight.doubleValue() - hauteurFenetre ) /2 ;
                double decalage = oldSceneHeight.doubleValue() / hauteurFenetre ;
                tt.setToY(ratio-decalage);
                
                if (primaryStage.getWidth() > (8*primaryStage.getHeight()/6) ){ 
                	ScaleTransition st = new ScaleTransition(Duration.ONE, gestionnaireEcransFXML);
                	 st.setToY( newSceneHeight.doubleValue()/ hauteurFenetre);
	                ScaleTransition st2 = new ScaleTransition(Duration.ONE, gestionnaireEcransFXML);
	                TranslateTransition tt2 = new TranslateTransition(Duration.ONE, gestionnaireEcransFXML);
	                double fract = (8*newSceneHeight.doubleValue())/6;
	                double ratio2 = (fract-largeurFenetre) /2 ;
	                st2.setToX( fract / largeurFenetre);
	                tt2.setToX( ratio2 );
	            
	                ParallelTransition pt = new ParallelTransition(gestionnaireEcransFXML, st, tt, st2, tt2);
	                pt.play();
	                
                }else{
                	
                	tt.play();
                }
            }
        });
        
	    }
	

}
