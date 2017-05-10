package vue;

import model.Partie;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Interface extends Application {
	
	public static final String ECRAN_ACCUEIL = "Menu";
    public static final String ECRAN_ACCUEIL_FXML = "fxml/Menu.fxml";
    public static final String ECRAN_REGLES = "PremiereRegle";
    public static final String ECRAN_REGLES_FXML = "fxml/PremiereRegle.fxml"; 
	public static int largeurFenetre = 800;
	public static int hauteurFenetre = 600;
	public static Partie partie;
	

	public static void creer(String[] args,Partie p,int h, int l) {
    	partie = p;
    	largeurFenetre = h*100;
    	hauteurFenetre = l*100;
        Application.launch(Interface.class, args);
        
    }
	
	@Override
	public void start(Stage primaryStage) {
		/*ChargeurFxml chargeurFxml1er= new ChargeurFxml();
		chargeurFxml1er.chargeEcran(Interface.ECRAN_ACCUEIL,Interface.ECRAN_ACCUEIL_FXML);
		//chargeurFxml1er.chargeEcran(Interface.ECRAN_REGLES,Interface.ECRAN_REGLES_FXML);
		
		chargeurFxml1er.fixeEcran(Interface.ECRAN_ACCUEIL);
		
		Group root = new Group();
		root.getChildren().addAll(chargeurFxml1er);
		Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	   */
		
		try {
		      // Localisation du fichier FXML.
		      final URL url = getClass().getResource(ECRAN_ACCUEIL_FXML);
		      // Création du loader.
		      final FXMLLoader fxmlLoader = new FXMLLoader(url);
		      // Chargement du FXML.
		      final AnchorPane root = (AnchorPane) fxmlLoader.load();
		      // Création de la scène.
		      final Scene scene = new Scene(root, hauteurFenetre, hauteurFenetre);
		      primaryStage.setScene(scene);
		    } catch (Exception ex) {
		      System.err.println("Erreur au chargement: " + ex);
		    }
		primaryStage.setHeight(hauteurFenetre);
	    primaryStage.setWidth(largeurFenetre);
	    primaryStage.setResizable(false);
	    primaryStage.show(); 
	    }
	
	
}
