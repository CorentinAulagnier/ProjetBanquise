package vue;

import model.Partie;
import vue.Interface;
import vue.Rafraichissement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Interface extends Application {
	
	public int largeur = 800;
	public int hauteur = 600;
	Canvas can;
	public Partie partie;
	
	  public static void main(String[] args) {
	        Application.launch(Interface.class, args);
	    }

	  @Override
	  public void start(Stage primaryStage) {
	        BorderPane root = new BorderPane();
	    	Scene scene = new Scene(root, largeur, hauteur, Color.LIGHTGRAY);
	   	 
	    	can = new Canvas(largeur,hauteur-30);
	    	root.setCenter(can);
	    	Rafraichissement r = new Rafraichissement(can, partie);
	        r.start();
	   	 
	    	primaryStage.setTitle("Hey that's my fish !");
	    	primaryStage.setScene(scene);
	    	
	    	primaryStage.show();
	    	

	    }
	
}
