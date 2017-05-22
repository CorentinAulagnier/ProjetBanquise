package vue;

import model.Moteur;
import model.Partie;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import java.awt.Dimension;
//import javafx.animation.RotateTransition;
//import javafx.animation.Timeline;
//import javafx.scene.media.MediaView;

public class Interface extends Application {

	private static int largeurFenetre;
	private static int hauteurFenetre;
	private static Partie partie;
	private static Moteur moteur;

	/**
	 * Lie les paramètres de bases à l'interface
	 * @param args 
	 * @param p partie gérant le moteur du jeu 
	 * @param h hauteur native du jeu
	 * @param l largeur native du jeu
	 */
	public static void creer(String[] args, Moteur m, int hauteur, int largeur) {
		moteur = m;
		partie = moteur.getPartie();
		hauteurFenetre = hauteur +30;
		largeurFenetre = largeur;
		launch(args);
	}

	/**
	 * Charge les premiers noeuds FXML, la scene, la musique et les écouteurs de redimentsionnement.
	 */
	@Override
	public void start(Stage primaryStage) {

		GestionnaireEcransFxml gestionnaireEcransFXML = new GestionnaireEcransFxml(moteur);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_ACCUEIL, model.Proprietes.ECRAN_ACCUEIL_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_REGLES, model.Proprietes.ECRAN_REGLES_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_MODE, model.Proprietes.ECRAN_MODE_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_JEU, model.Proprietes.ECRAN_JEU_FXML);
		gestionnaireEcransFXML.chargeEcran(model.Proprietes.ECRAN_VICTOIRE, model.Proprietes.ECRAN_VICTOIRE_FXML);
		gestionnaireEcransFXML.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
		Group root = new Group();
		root.getChildren().addAll(gestionnaireEcransFXML);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("ressources/decor/favicon.png"));
		primaryStage.setTitle("Pinguouins");
		primaryStage.setResizable(true);
		primaryStage.sizeToScene();
		
		// Petit message dans la console quand la fenetre est fermée
        primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
        	@Override public void handle(WindowEvent we) {
                System.out.println("Partie quittée");
            }
        }));        
        
        //TODO raffraichissement
        /*  RafraichissementJavaFX r = new RafraichissementJavaFX(partie,gestionnaireEcransFXML);
        r.start();
        */
		primaryStage.show();
		
		
		//TODO cf projet Armoroides au sujet du redimensionnement
		/*// On redimensionne le composant quand son parent change de taille
        c.widthProperty().bind(b.widthProperty());
        c.heightProperty().bind(b.heightProperty());
        */
		
		/* Redimensionnement */
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double height = (3 * dimension.getHeight()) / 4;
		scaleWidth(primaryStage, gestionnaireEcransFXML, (8 * height) / 6);
		scaleHeight(primaryStage, gestionnaireEcransFXML, height);
		primaryStage.setHeight(height);
		primaryStage.setWidth((8 * height) / 6);
		primaryStage.centerOnScreen();

		primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth) {
				scaleWidth(primaryStage, gestionnaireEcransFXML, newSceneWidth);
			}
		});

		primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				scaleHeight(primaryStage, gestionnaireEcransFXML, newSceneHeight);
			}
		});

	}

	/**
	 * 
	 * @param primaryStage
	 * @param gestionnaireEcransFXML
	 * @param newSceneWidth
	 */
	public void scaleWidth(Stage primaryStage, GestionnaireEcransFxml gestionnaireEcransFXML, Number newSceneWidth) {
		//TODO nettoyage commentaires
		//TODO javadoc ci dessus
		
		//double fract = (6 * newSceneWidth.doubleValue()) / 8;

		// (primaryStage.getHeight() > fract ){

		TranslateTransition tt = new TranslateTransition(Duration.ONE, gestionnaireEcransFXML);
		double ratio = (newSceneWidth.doubleValue() - largeurFenetre) / 2;
		// double decalage = oldSceneWidth.doubleValue() / largeurFenetre ;
		tt.setToX(ratio/*-decalage*/);

		ScaleTransition st = new ScaleTransition(Duration.ONE, gestionnaireEcransFXML);
		st.setToX(newSceneWidth.doubleValue() / largeurFenetre);
		/*
		 * ScaleTransition st2 = new ScaleTransition(Duration.ONE,
		 * gestionnaireEcransFXML); TranslateTransition tt2 = new
		 * TranslateTransition(Duration.ONE, gestionnaireEcransFXML); double
		 * ratio2 = (fract-(hauteurFenetre)) /2 ; st2.setToY( fract /
		 * (hauteurFenetre)); tt2.setToY( ratio2 );
		 */

		ParallelTransition pt = new ParallelTransition(gestionnaireEcransFXML, st,
				tt/* , st2, tt2 */);
		pt.play();

		// }
	}

	/**
	 * 
	 * @param primaryStage
	 * @param gestionnaireEcransFXML
	 * @param newSceneHeight
	 */
	public void scaleHeight(Stage primaryStage, GestionnaireEcransFxml gestionnaireEcransFXML, Number newSceneHeight) {
		//TODO nettoyage commentaires
		//TODO javadoc ci dessus

		//double fract = (8 * newSceneHeight.doubleValue()) / 6;

		// if (primaryStage.getWidth() > fract ){

		TranslateTransition tt = new TranslateTransition(Duration.ONE, gestionnaireEcransFXML);
		double ratio = (newSceneHeight.doubleValue() - (hauteurFenetre)) / 2;
		// double decalage = oldSceneHeight.doubleValue() / hauteurFenetre ;
		tt.setToY(ratio/*-decalage*/);

		ScaleTransition st = new ScaleTransition(Duration.ONE, gestionnaireEcransFXML);
		st.setToY(newSceneHeight.doubleValue() / (hauteurFenetre));
		/*
		 * ScaleTransition st2 = new ScaleTransition(Duration.ONE,
		 * gestionnaireEcransFXML); TranslateTransition tt2 = new
		 * TranslateTransition(Duration.ONE, gestionnaireEcransFXML); double
		 * ratio2 = (fract-largeurFenetre) /2 ; st2.setToX( fract /
		 * largeurFenetre); tt2.setToX(ratio2);
		 */

		ParallelTransition pt = new ParallelTransition(gestionnaireEcransFXML, st,
				tt/* , st2, tt2 */);
		pt.play();
		// }
	}
}
