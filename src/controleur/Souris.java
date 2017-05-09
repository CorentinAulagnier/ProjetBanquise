package controleur;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import model.*;


public class Souris implements EventHandler<MouseEvent> {
    Banquise maBanquise;
    double hauteur;
    double largeur;
    Partie partie;
    

    public Souris(double hauteur, double largeur,Partie p) {
    	this.partie = p;
        this.maBanquise = partie.b;
        this.hauteur=hauteur;
        this.largeur=largeur;
    }
    
    public void handle(MouseEvent event) {
    	/*Coordonnee de la case clique (dans un rectangle	***|   |   |   |   |***
    	 													*|   |   |   |   |   |*
    														***|   |   |   |   |***
    														*|   |   |   |   |   |*
    														***|   |   |   |   |***/
    	
    	Coordonnees c = retrouverCouple(event.getX() , event.getY());

    			

		
    }
    
    public Coordonnees retrouverCouple(double x, double y) {
    	if (x >= 0 && x <= (largeur*8) && y >= 0 && y <= (hauteur*8)) {
        	int l = (int) ((int) y / hauteur);
        	
        	if ((l % 2) == 1) {
        		x -= largeur / 2;
        	}
        	int c = (int) ((int) x / largeur);
        			
        	return new Coordonnees(l, c);
    	}
    	return null;
    }
    
}
