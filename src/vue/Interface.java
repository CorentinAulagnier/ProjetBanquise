package vue;

import model.Partie;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


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

		GestionnaireEcransFxml gestionnaireEcransFXML = new GestionnaireEcransFxml();
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_ACCUEIL, model.Proprietes.ECRAN_ACCUEIL_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_REGLES, model.Proprietes.ECRAN_REGLES_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_MODE, model.Proprietes.ECRAN_MODE_FXML);
//		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_JEU, model.Proprietes.ECRAN_JEU_FXML);
        
		gestionnaireEcransFXML.changeEcranCourant(model.Proprietes.ECRAN_MODE_FXML);
        
        Group root = new Group();
        root.getChildren().addAll(gestionnaireEcransFXML);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        primaryStage.setHeight(hauteurFenetre);
        primaryStage.setWidth(largeurFenetre);
        primaryStage.setTitle("Pinguouins");
        primaryStage.setResizable(false);
        primaryStage.show();
	    }
	

}
