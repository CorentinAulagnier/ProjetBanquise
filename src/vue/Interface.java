package vue;

import model.Partie;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
	public void start(Stage primaryStage) throws Exception {
		ScreensController mainContainer= new ScreensController();
		mainContainer.loadScreen(Interface.ECRAN_ACCUEIL,Interface.ECRAN_ACCUEIL_FXML);
		mainContainer.loadScreen(Interface.ECRAN_REGLES,Interface.ECRAN_REGLES_FXML);
		
		mainContainer.setScreen(Interface.ECRAN_ACCUEIL);
		
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	   
		
		/*try {
		      // Localisation du fichier FXML.
		      final URL url = getClass().getResource(ECRAN_ACCUEIL_FXML);
		      // Création du loader.
		      final FXMLLoader fxmlLoader = new FXMLLoader(url);
		      // Chargement du FXML.
		      final AnchorPane root = (AnchorPane) fxmlLoader.load();
		      // Création de la scène.
		      Scene scene = new Scene(root);
		      primaryStage.setScene(scene);
		    } catch (Exception ex) {
		      System.err.println("Erreur au chargement: " + ex);
		    }*/
		
	    
	    primaryStage.setResizable(false);
	    primaryStage.show(); 
	    }

}