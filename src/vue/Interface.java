package vue;

import model.Partie;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Interface extends Application {
	
	public static final String ECRAN_ACCUEIL = "accueil";
    public static final String ECRAN_ACCUEIL_FXML = "accueil.fxml";
    public static final String ECRAN_REGLES = "regles";
    public static final String ECRAN_REGLES_FXML = "regles.fxml"; 
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
		InterfaceurFxml interfaceurPinguouins = new InterfaceurFxml();
		interfaceurPinguouins.chargeEcran(Interface.ECRAN_ACCUEIL,Interface.ECRAN_ACCUEIL_FXML);
		interfaceurPinguouins.chargeEcran(Interface.ECRAN_REGLES,Interface.ECRAN_REGLES_FXML);
		interfaceurPinguouins.fixeEcran(Interface.ECRAN_ACCUEIL);
		
		Group root = new Group();
		root.getChildren().addAll(interfaceurPinguouins);
		Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	    primaryStage.show(); 
	    }
	
}
