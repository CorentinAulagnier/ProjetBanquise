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
    public static final String ECRAN_ACCUEIL = "Menu";
    public static final String ECRAN_ACCUEIL_FXML = "fxml/Menu.fxml";
    public static final String ECRAN_REGLES = "Regles";
    public static final String ECRAN_REGLES_FXML = "fxml/Regles.fxml";
    
	public static int largeurFenetre = 800;
	public static int hauteurFenetre = 600;
	public static Partie partie;
	
    public static String screen1ID = "main";
    public static String screen1File = "fxml/Screen1.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "fxml/Screen2.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "fxml/Screen3.fxml";
    

	
	public static void creer(String[] args,Partie p,int h, int l) {
    	partie = p;
    	//largeurFenetre = h*100;
    	//hauteurFenetre = l*100;
        Application.launch(Interface.class, args);
        
    }
	
	@Override
	public void start(Stage primaryStage) {
		
		StackPane listeTousEcrans = new StackPane();
		
		try {
					URL url1 = getClass().getResource(ECRAN_ACCUEIL_FXML);
					FXMLLoader fxmlLoader1 = new FXMLLoader(url1);
					listeTousEcrans.getChildren().add(fxmlLoader1.load());
					
					URL url2 = getClass().getResource(ECRAN_REGLES_FXML);
					FXMLLoader fxmlLoader2 = new FXMLLoader(url1);
					listeTousEcrans.getChildren().add(fxmlLoader2.load());
					
					// Création de la scène.
					Scene scene = new Scene(listeTousEcrans);
					primaryStage.setScene(scene);
					} catch (Exception ex) {
					System.err.println("Erreur au chargement: " + ex);
				}
		primaryStage.setHeight(625);
	    primaryStage.setWidth(800);
	    primaryStage.setResizable(false);
	    primaryStage.show();

		/*ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(screen1ID, screen1File);
        mainContainer.loadScreen(screen2ID, screen2File);
        mainContainer.loadScreen(screen3ID, screen3File);
        
        mainContainer.setScreen(screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
       // primaryStage.setHeight(625);
        //primaryStage.setWidth(800);
        primaryStage.setResizable(false);
        primaryStage.show();*/
	    }
	

}

