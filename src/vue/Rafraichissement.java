package vue;

import model.Partie;
import vue.Dessinateur;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Rafraichissement  extends AnimationTimer {
	Canvas can;
	Partie partie;
	Dessinateur dessinateur;
	
	Rafraichissement(Canvas c, Partie p) {
			partie = p;
	        can = c;
	        dessinateur = new Dessinateur(partie,can);
	    }


	    @Override
	    public void handle(long now) {
	        dessinateur.dessine();
	    }
}
