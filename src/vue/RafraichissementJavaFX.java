package vue;

import model.Visiteur;
import model.Partie;
import java.util.Properties;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;

public class RafraichissementJavaFX extends AnimationTimer {

    Partie partie;
    GestionnaireEcransFxml listeEcrans ;
    DessinateurJavaFX dessinateur;
    int courant;

    RafraichissementJavaFX(Partie p, GestionnaireEcransFxml l) {
    	partie = p;
    	GestionnaireEcransFxml listeEcrans = l;
        dessinateur = new DessinateurJavaFX(listeEcrans);
    }

    @Override
    public void handle(long now) {
    	//TODO methode rafraichit pour partie
    	//TODO methode accepte pour partie
        /*partie.rafraichit(now);
        if (partie.accepte(dessinateur)) {
            Platform.exit();
        }*/
    }
}
