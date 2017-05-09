package vue;

import model.Partie;
import javafx.scene.canvas.Canvas;

public class Dessinateur {
	static final int MENU = 0;
	static final int PERSO = 1;
	static final int PLATEAU = 2;
	static final int SCORE = 3;
	static final int REGLES = 4;
	/*int option = 0 ; regle courante etc ... */
	Canvas can;
	Partie partie;
	int pagecourante=0;
	
	 public Dessinateur(Partie p , Canvas c) {
	        can = c;
	        partie=p;
	    }
	
	public int pageCourante(){
		return pagecourante;
	}
	
	public void dessine(){
		if (pageCourante() == MENU){
			affichageMenu();
		}else if (pageCourante() == PERSO) {
			affichagePerso();
		}else if (pageCourante() == PLATEAU) {
			affichagePlateau();
		}else if (pageCourante() == SCORE) {
			affichageScore();
		}else if (pageCourante() == REGLES) {
			affichageRegles();
		}
	}
	
	public void affichageMenu(){
		
	}
	
	public void affichagePerso(){
		
	}

	public void affichagePlateau(){
	
}

	public void affichageScore(){
		
	}

	public void affichageRegles(){
		
	}
	
}
