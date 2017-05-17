package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.Coordonnees;
import model.Humain;
import model.IA;

public class ControleurJeu extends ControleurPere implements Initializable, EcranCourant {
	
	GestionnaireEcransFxml gestionnaireFxmlCourant;
	
    @FXML private Button bouton_defaire, bouton_indice, bouton_annuler, bouton_finTour, bouton_faire;
    @FXML private Text label_tourDe;
    @FXML private HBox box_boutons_tour;
    @FXML private AnchorPane box_tour_distant;
    
    //zone menu
    @FXML private ImageView imageSon, imageMusique;
    final FileChooser fileChooser = new FileChooser();
    @FXML AnchorPane optionbox;
    @FXML Button roue;
    
    //zone joueur
    @FXML private AnchorPane anchorPane_j1, anchorPane_j2, anchorPane_j3, anchorPane_j4;
    @FXML private Arc banquise_j1, banquise_j2, banquise_j3, banquise_j4;
    @FXML private Label nom_j1, nom_j2, nom_j3, nom_j4;
    @FXML private HBox reglette_j1, reglette_j2, reglette_j3, reglette_j4;
    @FXML private ImageView reglette_j1_1, reglette_j1_2, reglette_j1_3, reglette_j1_4,
    						reglette_j2_1, reglette_j2_2, reglette_j2_3, reglette_j2_4,
    						reglette_j3_1, reglette_j3_2, reglette_j3_3, reglette_j3_4,
    						reglette_j4_1, reglette_j4_2, reglette_j4_3, reglette_j4_4;
    @FXML private Label score_poissons_j1, score_poissons_j2, score_poissons_j3, score_poissons_j4;
    @FXML private Label score_tuiles_j1, score_tuiles_j2, score_tuiles_j3, score_tuiles_j4;

    //banquise
    @FXML AnchorPane banquise;
    @FXML private ImageView t11,t12,t13,t14,t15,t16,t17,
    						t21,t22,t23,t24,t25,t26,t27,t28,
    						t31,t32,t33,t34,t35,t36,t37,
    						t41,t42,t43,t44,t45,t46,t47,t48,
    						t51,t52,t53,t54,t55,t56,t57,
    						t61,t62,t63,t64,t65,t66,t67,t68,
    						t71,t72,t73,t74,t75,t76,t77,
    						t81,t82,t83,t84,t85,t86,t87,t88;
    
	/**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	anchorPane_j3.setVisible(false);
    	anchorPane_j4.setVisible(false);
    	
    	box_tour_distant.setDisable(true);
    	box_boutons_tour.setDisable(true);
    	box_tour_distant.setVisible(false);
    	box_boutons_tour.setVisible(false);
    	
    	bouton_defaire.setStyle(model.Proprietes.STYLE_NORMAL);
    	bouton_defaire.setDisable(true);
    	bouton_faire.setStyle(model.Proprietes.STYLE_NORMAL);
    	bouton_faire.setDisable(true);
    	
    	bouton_indice.setStyle(model.Proprietes.STYLE_NORMAL);
    	bouton_annuler.setStyle(model.Proprietes.STYLE_NORMAL);
    	bouton_finTour.setStyle(model.Proprietes.STYLE_NORMAL);

    }
    
	/**
	 * implementation demandé par l'interface EcranCourant : met à jour le noeud fxml parent associe a ce controleur
	 */
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    }
    
    /**
     * implementation demande par l'interface EcranCourant : met a jour le noeud fxml parent associe a ce controleur dynamiquement à l'appel de l'écran de Jeu. 
     * Appelle la mise initiale lors du chargement d'écran, ainsi que la mise à jour de chaque tour de jeu 
     */
    public void miseAjour(){
    	
    	if(gestionnaireFxmlCourant!= null && gestionnaireFxmlCourant.partie!=null){
    		
    		AnchorPane[] anchorPanes = {anchorPane_j1,anchorPane_j2,anchorPane_j3,anchorPane_j4};
    		Arc[] banquises = {banquise_j1,banquise_j2,banquise_j3,banquise_j4};
    		Paint[] p = {Paint.valueOf("ffda4a"),Paint.valueOf("37cc2c"),Paint.valueOf("950303"),Paint.valueOf("2394f1")};	
    	    Label[] noms = {nom_j1,nom_j2,nom_j3,nom_j4};
    	    Label[] score_poissons = {score_poissons_j1,score_poissons_j2,score_poissons_j3,score_poissons_j4};
    	    Label[] score_tuiles = {score_tuiles_j1,score_tuiles_j2,score_tuiles_j3,score_tuiles_j4};  
    	    ImageView[] j1_reglette = {reglette_j1_1, reglette_j1_2, reglette_j1_3, reglette_j1_4};
    	    ImageView[] j2_reglette = {reglette_j2_1, reglette_j2_2, reglette_j2_3, reglette_j2_4};
    	    ImageView[] j3_reglette = {reglette_j3_1, reglette_j3_2, reglette_j3_3, reglette_j3_4};
    	    ImageView[] j4_reglette = {reglette_j4_1, reglette_j4_2, reglette_j4_3, reglette_j4_4};
    	    ArrayList<ImageView[]> reglettes = new ArrayList<ImageView[]>();
    	    reglettes.add(j1_reglette); reglettes.add(j2_reglette); reglettes.add(j3_reglette); reglettes.add(j4_reglette);
    	    ArrayList <ImageView> banq1 = new ArrayList <ImageView>(); Collections.addAll(banq1,t11,t12,t13,t14,t15,t16,t17,new ImageView());
    	    ArrayList <ImageView> banq2 = new ArrayList <ImageView>(); Collections.addAll(banq2,t21,t22,t23,t24,t25,t26,t27,t28);
    	    ArrayList <ImageView> banq3 = new ArrayList <ImageView>(); Collections.addAll(banq3,t31,t32,t33,t34,t35,t36,t37,new ImageView());
    	    ArrayList <ImageView> banq4 = new ArrayList <ImageView>(); Collections.addAll(banq4,t41,t42,t43,t44,t45,t46,t47,t48);
    	    ArrayList <ImageView> banq5 = new ArrayList <ImageView>(); Collections.addAll(banq5,t51,t52,t53,t54,t55,t56,t57,new ImageView());
    	    ArrayList <ImageView> banq6 = new ArrayList <ImageView>(); Collections.addAll(banq6,t61,t62,t63,t64,t65,t66,t67,t68);
    	    ArrayList <ImageView> banq7 = new ArrayList <ImageView>(); Collections.addAll(banq7,t71,t72,t73,t74,t75,t76,t77,new ImageView());
    	    ArrayList <ImageView> banq8 = new ArrayList <ImageView>(); Collections.addAll(banq8,t81,t82,t83,t84,t85,t86,t87,t88);
    	    ArrayList<ArrayList<ImageView>> banquise = new ArrayList<ArrayList<ImageView>>();
    	    banquise.add(banq1);banquise.add(banq2);banquise.add(banq3);banquise.add(banq4);banquise.add(banq5);banquise.add(banq6);banquise.add(banq7);banquise.add(banq8);
    	    
    	    
    	    miseAjour_initiale(anchorPanes, banquises, p, noms);
    	    
    	    miseAjour_tourDeJeu(reglettes, score_poissons, score_tuiles, banquise);
    	}
    }
    
    
    /**
     * Charge l'interface depuis les informations de l'objet Partie, à l'ouverture de l'écran de Jeu. (pas à chaque tour)
     * @param anchorPanes tableau des 4 zones réservées à chaque joueur (pour afficher ou cacher)
     * @param banquises tableau des 4 encarts de couleurs réservées à chaque joueur (futures banquises)
     * @param p tableau de couleurs pour les banquises
     * @param noms tableau des noms des joueurs
     * @param score_poissons tableau des scores (en poissons) des joueurs
     * @param score_tuiles tableau des scores (en tuiles) des joueurs
     */
    public void miseAjour_initiale(AnchorPane[] anchorPanes, Arc[] banquises, Paint[] p, Label[] noms){
    	
    	for(int k=0; k<gestionnaireFxmlCourant.partie.joueurs.length; k++){
    		//maj de chaque zones réservées à un joueur
    		initZoneJoueur(k,(AnchorPane)anchorPanes[k],(Arc)banquises[k], p[(gestionnaireFxmlCourant.partie.joueurs[k]).couleur],(Label)noms[k]);
		}
    	
    	if( gestionnaireFxmlCourant.partie.utiliseHistorique){// s'il y a un et un seul humain alors on peut defaire et faire, sinon ces boutons sont initialisés à false
	    	bouton_defaire.setDisable(false);
	    	bouton_faire.setDisable(false);
		}
    }
    
    
    /**
     * met à jour toute la zone réservée à un joueur
     * @param joueur numéro du joueur
     * @param anchors zone entière réservée au joueur
     * @param banquise encart de couleur sous la zone de joueur
     * @param p couleur assoicée au joueur
     * @param nom nom du joueur
     */
    public void initZoneJoueur(int joueur, AnchorPane anchors, Arc banquisette, Paint p, Label nom){
    	anchors.setVisible(true);
    	banquisette.setFill(p);
    	nom.setText(gestionnaireFxmlCourant.partie.joueurs[joueur].nom);
    }
    
    
    /**
     * Gere les modifications de l'interface l'interface appelées à chaque tour de jeu.
     * @param reglettes liste des reglettes accueillant les miniatures de pingouins dans la zone joueur.
     * @param score_poissons tableau des scores (en poissons) des joueurs
     * @param score_tuiles tableau des scores (en tuiles) des joueurs
     * 
     */
    public void miseAjour_tourDeJeu(ArrayList<ImageView[]> reglettes, Label[] score_poissons, Label[] score_tuiles, ArrayList<ArrayList<ImageView>> banquise ){
    	//maj du label "tour de untel"
    	label_tourDe.setText("Tour de "+gestionnaireFxmlCourant.partie.joueurs[gestionnaireFxmlCourant.partie.joueurActif].nom+" :");
    	
    	for(int j=0; j < gestionnaireFxmlCourant.partie.joueurs.length; j++){
    		//maj des scores du joueur
    		((Label)score_poissons[j]).setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[j].poissonsManges) );
        	((Label)score_tuiles[j]).setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[j].nbTuiles) );	
        	
    		//maj des pinguouins sur les reglettes 		
    		String path = gestionnaireFxmlCourant.partie.joueurs[j].cheminMiniature;
    		for(int p=0; p < gestionnaireFxmlCourant.partie.joueurs[j].nbPingouin ; p++){
    			reglettes.get(j)[p].setImage(new Image(path));
    		}
    	}

    	//maj de l'affichage la banquise
    	majBanquise(banquise);
    	
    	//si joueur actif = humain alors montrer hbox boutons sinon attente
    	if ( !( IA.class ).equals( (gestionnaireFxmlCourant.partie.joueurs[gestionnaireFxmlCourant.partie.joueurActif]).getClass() ) ){
    		box_boutons_tour.setDisable(false);
    		box_boutons_tour.setVisible(true);
    	}
    	else{
    		box_tour_distant.setDisable(false);
    		box_tour_distant.setVisible(true);
        	
    	}
    } 
    
    public void majBanquise( ArrayList<ArrayList<ImageView>> banquise ){
   	
    	for (int i = 0 ; i < 8 ; i++){
    		for (int j = 0 ; j< 8 ; j++){
    			if ( !((i%2 == 0) && (j == 7)) ) {
    				switch(gestionnaireFxmlCourant.partie.b.terrain[i][j].nbPoissons){
    					case 1:	banquise.get(i).get(j).setImage(new Image(model.Proprietes.IMAGE_TUILE_1POISSON));break;
    					case 2:	banquise.get(i).get(j).setImage(new Image(model.Proprietes.IMAGE_TUILE_2POISSON));break;
    					case 3:	banquise.get(i).get(j).setImage(new Image(model.Proprietes.IMAGE_TUILE_3POISSON));break;
    				}
    			}	
    		}
    	}

    }
    
    
    
    /****************************************/
    /* 			roue boutons				*/
    /****************************************/

    
    /**
     * gere la modification du volume de la musique
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererMusique(MouseEvent event){
    	changerMusique(imageMusique,gestionnaireFxmlCourant.media,gestionnaireFxmlCourant);
    }
    
    /**
     * gere la modification des bruitages
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererSon(MouseEvent event){
    	changerSon(imageSon,gestionnaireFxmlCourant);
    }
    
    
    /**
     * change d'ecran pour celui des regles
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void sauvegarder(MouseEvent event){
    	nettoyerRoueHorizontale(optionbox, roue);
    	System.out.println("sauvegarder");
    	File file = fileChooser.showOpenDialog(null);
    	String  path = file.getName();
        if (file != null) {
        	path = file.getAbsolutePath();
        	//TODO
        	gestionnaireFxmlCourant.partie.sauvegarder(path);        	
        }   
    }
    
    /**
     * change d'ecran pour celui des regles
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	nettoyerRoueHorizontale(optionbox, roue);
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
    }
    
    /**
     * renvoi à l'écran accueil
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageAccueil(MouseEvent event){
    	nettoyerRoueHorizontale(optionbox, roue);
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    
    /**
     * ouvre une popup de confirmatio avant de quitter l'application 
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void quitter(MouseEvent event){
    	nettoyerRoueHorizontale(optionbox, roue);
    	String contenu = "Etes vous sur de vouloir quitter nos amis les pinguouins? Ils vont se sentir si seuls...";
    	alert_quitter(gestionnaireFxmlCourant, "Bye bye ?", contenu, "Partir", "Annuler" , "" );
    }
    
    
    /**
     * gere l'ouverture ou la fermeture du menu roue
     * @param event evenement souris attendu : clic
     */
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		optionWheelOpen(optionbox, roue);    		
    	}else{
    		optionWheelClose(optionbox, roue);
    	}
    }
       
    
    /************************************/
    /*		gestion action banquise		*/
    /************************************/
    
    @FXML private void annulerTours(MouseEvent event){
    	// TODO
    	System.out.println("annulerTours");
    }
    
    @FXML private void demanderIndice(MouseEvent event){
    	// TODO
    	System.out.println("demanderIndice");
    }
    
    @FXML private void reinitiailiserTour(MouseEvent event){
    	// TODO
    	System.out.println("reinitiailiserTour");
    }
    
    @FXML private void validerTour(MouseEvent event){
    	// TODO
    	System.out.println("validerTour");
    }
    
    @FXML private void refaireTours(MouseEvent event){
    	// TODO
    	System.out.println("refaireTours");
    }
    
    
    
    
    /************************************/
    /*		gestion banquise			*/
    /************************************/
    
    /**
     * action à produire après un clic sur la banquise
     * @param event evenement souris attendu : clic
     */
    public void anchorClick(MouseEvent event) {
		Coordonnees xy = getXY(event.getX(), event.getY());
		if (coordValide(xy)) {
			System.out.println(xy);
			
			/*if( !gestionnaireFxmlCourant.partie.placementPingouinsFini() ){// phase placement
				//si tuile un poisson
					//retire une miniature de sa reglette = unset
					//la place au centre de tuile du clic
				
				
			} else {
				// sinon phase jeu
				//Coordonnees[] pingouins = gestionnaireFxmlCourant.partie.pingouinsDeplacable();
				//TODO
				
				// if (xy.equals(ping[0]) ||xy.equals(ping[1]) || xy.equals(ping[2])
				// || xy.equals(ping[3])){ }
			}*/
			
		}
	}
   
    

	/**
	 * Transorme les coodonnées du clic dans l'anchorPane banquise en coordonnées exploitables par la classe Banquise
	 * @param x coordonnées liées à la hauteur (0 sur le haut de la fenetre)
	 * @param y coordonnées liées à la largeur (0 sur la gauche de la fenetre)
	 * @return un couple de coordonnées correspondant aux indices des tableaux banquise
	 */
	public Coordonnees getXY(double x, double y) {
		int i = 0, j = 0;
		double h = 48 / 3;

		if (((int) (y / h)) % 2 == 1) {
			if (x >= 32) {
				i = (int) ((y / h) / 2);
				if (i % 2 == 1) {
					j = (int) (x / 64);
				} else {
					j = (int) ((x - 32) / 64);
				}
			} else {
				i = -1; 
				j = -1;
			}
		} else {
			j = (int) (x / 32);
			double newX = x % 32;
			double newY = y % 16;
			i = (int) (y / h);
			if (i % 4 == 0) {
				if (j % 2 == 0) {					// 0.5
					if (0.5 * newX - newY > 0) {
						i = (int) ((y / h) / 2) - 1;
						j = (int) (x / 64);
					} else {
						i = (int) ((y / h) / 2);
						j = (int) (x / 64) - 1;
					}
				} else {							// -0.5
					if (-0.5 * newX - newY + 16 > 0) {
						i = (int) ((y / h) / 2) - 1;
						j = (int) (x / 64);
					} else {
						i = (int) ((y / h) / 2);
						j = (int) (x / 64);
					}
				}
			} else if (i % 4 == 2) {
				if (j % 2 == 0) {					// -0.5
					if (-0.5 * newX - newY + 16 > 0) {
						i = (int) ((y / h) / 2) - 1;
						j = (int) (x / 64) - 1;
					} else {
						i = (int) ((y / h) / 2);
						j = (int) (x / 64);
					}
				} else {							// 0.5
					if (0.5 * newX - newY > 0) {
						i = (int) ((y / h) / 2) - 1;
						j = (int) (x / 64);
					} else {
						i = (int) ((y / h) / 2);
						j = (int) (x / 64);
					}
				}
			}
		}
		if ((i < 0) || (j < 0) || (i > 7) || (i % 2 == 0 && j >= 7)) {
			i = -1;
			j = -1;
		}
		return new Coordonnees(i, j);
	}
	
	/**
	 * vérifie que les coordonnées du couple xy sont des indices valides dans un tableau Banquise
	 * @param xy un couple de coordonnées correspondant aux indices des tableaux banquise
	 * @return vrai si le couple est valide, faux sinon.
	 */
	public boolean coordValide(Coordonnees xy) {
		return (xy.x != -1) && (xy.y != -1);
	}
	
	
	

	
	/*
    ImageView[][] banq;
    ImageView[][] pingouins = new ImageView[8][8];
    int fromX;
    int fromY;
    int toX;
    int toY;
    boolean EndOfTurn;
    int largeurHexagone ;
    int hauteurHexagone ;
    
    Image image1PoissonJaune;
    Image image2PoissonsJaunes;
    Image image3PoissonsJaunes;
    Image image1PoissonBlanc;
    Image image2PoissonsBlancs;
    Image image3PoissonsBlancs;
    
    double hauteurbanquise =270 ;
    double largeurbanquise=512 ;
    
    
    
    @FXML ImageView shadowPingouin;
    
    
   @FXML
   public void SelectPingouin(MouseEvent event){
   	if (EndOfTurn) {
   		shadowPingouin.setVisible(false);
   	}    	
   	for (int i = 0; i<8 ; i++){
   		for (int j = 0; j<8 ; j++){
   			if (!( (i%2 == 1) && (j == 7) )){
   				if ( ((ImageView) (banq [i][j] )).getImage() == image3PoissonsJaunes){
   					((ImageView) (banq [i][j] )).setImage(image3PoissonsBlancs) ;
   				}else if ( ((ImageView) (banq [i][j] )).getImage() == image2PoissonsJaunes){
   					((ImageView) (banq [i][j] )).setImage(image2PoissonsBlancs) ;
   				}else if ( ((ImageView) (banq [i][j] )).getImage() == image1PoissonJaune){
   					((ImageView) (banq [i][j] )).setImage(image1PoissonBlanc) ;
   				}
   			}
       	}
   	}
   	EndOfTurn = false;
   	Coordonnees xy = getXY(event.getX(),event.getY());
   	 ArrayList<ArrayList<Coordonnees>> accessibles = deplacementPossible(pingouins[fromX][fromY]);
   	for ( ArrayList<Coordonnees> path : accessibles){
   		for (Coordonnees bloc : path){
   			int i = bloc.x;
   			int j = bloc.y;
   			if ( ((ImageView) (banq [i][j] )).getImage() == image3PoissonsBlancs){
					((ImageView) (banq [i][j] )).setImage(image3PoissonsJaunes) ;
				}else if ( ((ImageView) (banq [i][j] )).getImage() == image2PoissonsBlancs){
					((ImageView) (banq [i][j] )).setImage(image2PoissonsJaunes) ;
				}else if ( ((ImageView) (banq [i][j] )).getImage() == image1PoissonBlanc){
					((ImageView) (banq [i][j] )).setImage(image1PoissonJaune) ;
				}
   		}
   	}
   }
   
   /* 
    @FXML
    public void GoToPingouin(MouseEvent event){
    	toX = getX();
    	toY = getY();
    	if ( estJaune(  ((ImageView) (banq[toX][toY])) .getImage() ) ){
    		TranslateTransition tt = new TranslateTransition(Duration.millis(0), shadowPingouin);
    	     tt.setToX( toX * largeurHexagone);
    	     tt.setToY(toY * hauteurHexagone);
    	     tt.play();
    	     shadowPingouin.setVisible(true);
    	     EndOfTurn = true;
    	}
    }
   
    public boolean estJaune(Image imag){
    	return (imag==image1PoissonJaune)||(imag==image2PoissonsJaunes)||(imag==image3PoissonsJaunes);
    }
    */
    
    /*
    @FXML
    public void ValidatePingouinTranslation(MouseEvent event){
    	if (EndOfTurn){
	    	TranslateTransition tt = new TranslateTransition(Duration.millis(500),pingouin[toX][toY]);
	    	shadowPingouin.setVisible(true);
	    	tt.setFromX( fromX * largeurHexagone);
   	     	tt.setFromY( fromY * hauteurHexagone);
	   	    tt.setToX( toX * largeurHexagone);
		    tt.setToY(toY * hauteurHexagone);    
    	}
    }
    */ 
}