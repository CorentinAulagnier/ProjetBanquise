package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
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
import javafx.util.Duration;
import model.Coordonnees;
import model.Humain;
import model.Partie;

public class ControleurJeu extends ControleurPere implements Initializable, EcranCourant {
	/**
	 * Noeud FXML associe au controleur
	 */
	GestionnaireEcransFxml liste_Ecran;
	Coordonnees coord_pingouin_encours;
    //Coordonnees depart = new Coordonnees();
    //Coordonnees arrivee = new Coordonnees();
	
	
	//boutons d'actions
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
    
    ArrayList<Label> scores_poissons, scores_tuiles;
    @FXML private Label score_poissons_j1, score_poissons_j2, score_poissons_j3, score_poissons_j4;
    @FXML private Label score_tuiles_j1, score_tuiles_j2, score_tuiles_j3, score_tuiles_j4;
    ArrayList<ArrayList<ImageView>> reglettes;
    ArrayList<ImageView> reglette_j1, reglette_j2, reglette_j3, reglette_j4;
    @FXML private ImageView reglette_j1_1, reglette_j1_2, reglette_j1_3, reglette_j1_4,
    						reglette_j2_1, reglette_j2_2, reglette_j2_3, reglette_j2_4,
    						reglette_j3_1, reglette_j3_2, reglette_j3_3, reglette_j3_4,
    						reglette_j4_1, reglette_j4_2, reglette_j4_3, reglette_j4_4;

    //banquise
    @FXML AnchorPane anchorBanquise;
    ArrayList<ArrayList<ImageView>> banquise;
    ArrayList <ImageView> banquiseLigne1, banquiseLigne2, banquiseLigne3, banquiseLigne4, banquiseLigne5, banquiseLigne6, banquiseLigne7, banquiseLigne8;
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
    	bouton_finTour.setDisable(true);
    	
    	coord_pingouin_encours = new Coordonnees(-1,-1);
    	
    }
    
    
	/**
	 * implementation demandé par l'interface EcranCourant : met à jour le noeud fxml parent associe a ce controleur
	 */
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	liste_Ecran = ecranParent;
    }
    
    /**
     * implementation demande par l'interface EcranCourant : met a jour le noeud fxml parent associé à ce controleur dynamiquement à l'appel de l'écran de Jeu. 
     * Appelle la mise initiale lors du chargement d'écran, ainsi que la mise à jour de chaque tour de jeu 
     */
    public void miseAjour(){
    	
    	if(liste_Ecran!= null && liste_Ecran.moteur.partie!=null){
    		
    		AnchorPane[] anchorPanes = {anchorPane_j1,anchorPane_j2,anchorPane_j3,anchorPane_j4};
    		Arc[] banquises = {banquise_j1,banquise_j2,banquise_j3,banquise_j4};
    		Paint[] p = {Paint.valueOf("ffda4a"),Paint.valueOf("37cc2c"),Paint.valueOf("950303"),Paint.valueOf("2394f1")};	
    	    Label[] noms = {nom_j1,nom_j2,nom_j3,nom_j4};
    	    
    	    scores_poissons = new ArrayList <Label>(); Collections.addAll(scores_poissons, score_poissons_j1,score_poissons_j2,score_poissons_j3,score_poissons_j4);
    	    scores_tuiles = new ArrayList <Label>(); Collections.addAll(scores_tuiles, score_tuiles_j1, score_tuiles_j2,score_tuiles_j3,score_tuiles_j4);  
    	    
    	    reglette_j1 = new ArrayList <ImageView>(); Collections.addAll(reglette_j1, reglette_j1_1, reglette_j1_2, reglette_j1_3, reglette_j1_4);
    	    reglette_j2 = new ArrayList <ImageView>(); Collections.addAll(reglette_j2, reglette_j2_1, reglette_j2_2, reglette_j2_3, reglette_j2_4);
    	    reglette_j3 = new ArrayList <ImageView>(); Collections.addAll(reglette_j3, reglette_j3_1, reglette_j3_2, reglette_j3_3, reglette_j3_4);
    	    reglette_j4 = new ArrayList <ImageView>(); Collections.addAll(reglette_j4, reglette_j4_1, reglette_j4_2, reglette_j4_3, reglette_j4_4);
    	    reglettes = new ArrayList<ArrayList<ImageView>>();
    	    reglettes.add(reglette_j1); reglettes.add(reglette_j2); reglettes.add(reglette_j3); reglettes.add(reglette_j4);
    	    
    	    banquiseLigne1 = new ArrayList <ImageView>(); Collections.addAll(banquiseLigne1,t11,t12,t13,t14,t15,t16,t17,new ImageView());
    	    banquiseLigne2 = new ArrayList <ImageView>(); Collections.addAll(banquiseLigne2,t21,t22,t23,t24,t25,t26,t27,t28);
    	    banquiseLigne3 = new ArrayList <ImageView>(); Collections.addAll(banquiseLigne3,t31,t32,t33,t34,t35,t36,t37,new ImageView());
    	    banquiseLigne4 = new ArrayList <ImageView>(); Collections.addAll(banquiseLigne4,t41,t42,t43,t44,t45,t46,t47,t48);
    	    banquiseLigne5 = new ArrayList <ImageView>(); Collections.addAll(banquiseLigne5,t51,t52,t53,t54,t55,t56,t57,new ImageView());
    	    banquiseLigne6 = new ArrayList <ImageView>(); Collections.addAll(banquiseLigne6,t61,t62,t63,t64,t65,t66,t67,t68);
    	    banquiseLigne7 = new ArrayList <ImageView>(); Collections.addAll(banquiseLigne7,t71,t72,t73,t74,t75,t76,t77,new ImageView());
    	    banquiseLigne8 = new ArrayList <ImageView>(); Collections.addAll(banquiseLigne8,t81,t82,t83,t84,t85,t86,t87,t88);
    	    banquise = new ArrayList<ArrayList<ImageView>>(); banquise.add(banquiseLigne1); banquise.add(banquiseLigne2); banquise.add(banquiseLigne3);
    	    banquise.add(banquiseLigne4); banquise.add(banquiseLigne5); banquise.add(banquiseLigne6);banquise.add(banquiseLigne7);banquise.add(banquiseLigne8);
    	    
    	    miseAjour_initiale(anchorPanes, banquises, p, noms);
    	    
    	    miseAjour_tourDeJeu();
    	    System.out.println("creation time line");
        	Timeline timeline = new Timeline(
        			new KeyFrame(
        					Duration.seconds(1),
        					new EventHandler<ActionEvent>(){
        						@Override public void handle(ActionEvent actionEvent) {
        							// Call miseAjour_tourDeJeu method for every 2 sec.
        							if(liste_Ecran.moteur.aRafraichir){
        								System.out.println("ecran jeu se met à jour");
        								//miseAjour_tourDeJeu();
        								liste_Ecran.moteur.partieRafraichie();
        								}
        						}
        					}
        					)
        			);
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
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
    	
    	for(int k=0; k<liste_Ecran.moteur.partie.joueurs.length; k++){
    		//maj de chaque zones réservées à un joueur
    		initZoneJoueur(k,(AnchorPane)anchorPanes[k],(Arc)banquises[k], p[(liste_Ecran.moteur.partie.joueurs[k]).couleur],(Label)noms[k]);	
		}
    	
    	if( liste_Ecran.moteur.partie.utiliseHistorique){// s'il y a un et un seul humain alors on peut defaire et faire, sinon ces boutons sont initialisés à false
	    	bouton_defaire.setDisable(false);
	    	bouton_defaire.setVisible(false);
	    	bouton_faire.setDisable(false);
	    	bouton_faire.setVisible(false);
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
    	nom.setText(liste_Ecran.moteur.partie.joueurs[joueur].nom);
    	
    	//maj des miniatures des pinguouins 		
		String path = liste_Ecran.moteur.partie.joueurs[joueur].cheminMiniature;
		for(int ping = 0; ping < liste_Ecran.moteur.partie.joueurs[joueur].nbPingouin ; ping++){
			reglettes.get(joueur).get(ping).setImage(new Image(path));
		}
    }
    
    
    /**
     * Gere les modifications de l'interface l'interface appelées à chaque tour de jeu.
     * @param reglettes liste des reglettes accueillant les miniatures de pingouins dans la zone joueur.
     * @param score_poissons tableau des scores (en poissons) des joueurs
     * @param score_tuiles tableau des scores (en tuiles) des joueurs
     * 
     */
    public void miseAjour_tourDeJeu(){

    	for(int j=0; j < liste_Ecran.moteur.partie.joueurs.length; j++){
    		//maj des scores du joueur
    		scores_poissons.get(j).setText( String.valueOf(liste_Ecran.moteur.partie.joueurs[j].poissonsManges) );
    		scores_tuiles.get(j).setText( String.valueOf(liste_Ecran.moteur.partie.joueurs[j].nbTuiles) );	
    	}

    	//maj de l'affichage la banquise
    	majBanquise();
    	
    	
    	//maj affichage + interaction selon phase :
    	//maj du label "tour X : placer ou jouer un pingouin"
    	if(liste_Ecran.moteur.phasePlacement){
    		label_tourDe.setText(liste_Ecran.moteur.partie.joueurs[liste_Ecran.moteur.partie.joueurActif].nom+" place un pingouin sur la banquise.");
    	}
    	else if(this.liste_Ecran.moteur.phaseJeu){
    		label_tourDe.setText(liste_Ecran.moteur.partie.joueurs[liste_Ecran.moteur.partie.joueurActif].nom+" deplace un pingouin sur la banquise.");
    	}
    		
    	//si joueur actif = humain alors montrer hbox boutons sinon attente
    	if( liste_Ecran.moteur.partie.joueurs[liste_Ecran.moteur.partie.joueurActif] instanceof Humain){
    		box_boutons_tour.setDisable(false);
    		box_boutons_tour.setVisible(true);
    	}
    	else{
    		box_tour_distant.setDisable(false);
    		box_tour_distant.setVisible(true);
    	}
    	
    	
    } 
    
    /**
     * raffraichit la banquise
     * @param banquise 
     */
    public void majBanquise(){
   	
    	for (int i = 0 ; i < 8 ; i++){
    		for (int j = 0 ; j< 8 ; j++){
    			if ( !((i%2 == 0) && (j == 7)) ) {
    				placeUneTuile(liste_Ecran.moteur.partie.b.terrain[i][j].nbPoissons, i, j);
    			}	
    		}
    	}
    }
    
    
	public void placeUneTuile(int nombreDePoissons, int i, int j) {
		switch (nombreDePoissons) {
		case 1:
			banquise.get(i).get(j).setImage(new Image(model.Proprietes.IMAGE_TUILE_1POISSON));
			break;
		case 2:
			banquise.get(i).get(j).setImage(new Image(model.Proprietes.IMAGE_TUILE_2POISSON));
			break;
		case 3:
			banquise.get(i).get(j).setImage(new Image(model.Proprietes.IMAGE_TUILE_3POISSON));
			break;
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
    	changerMusique(imageMusique,liste_Ecran.media,liste_Ecran);
    }
    
    /**
     * gere la modification des bruitages
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererSon(MouseEvent event){
    	changerSon(imageSon,liste_Ecran);
    }
    
    
    /**
     * change d'ecran pour celui des regles
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void sauvegarder(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	System.out.println("sauvegarder");
    	File file = fileChooser.showOpenDialog(null);
    	String  path = file.getName();
        if (file != null) {
        	path = file.getAbsolutePath();
        	//TODO
        	liste_Ecran.moteur.partie.sauvegarder(path);        	
        }   
    }
    
    /**
     * change d'ecran pour celui des regles
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
    }
    
    /**
     * renvoi à l'écran accueil
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageAccueil(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    
    /**
     * ouvre une popup de confirmatio avant de quitter l'application 
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void quitter(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	String contenu = "Etes vous sur de vouloir quitter nos amis les pinguouins? Ils vont se sentir si seuls...";
    	alert_quitter(liste_Ecran, "Bye bye ?", contenu, "Partir", "Annuler" , "" );
    }
    
    
    /**
     * gere l'ouverture ou la fermeture du menu roue
     * @param event evenement souris attendu : clic
     */
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		optionOuvrirRoue(optionbox, roue);    		
    	}else{
    		optionFermerRoue(optionbox, roue);
    	}
    }
       
    
    /************************************/
    /*		gestion action banquise		*/
    /************************************/
    
    @FXML private void annulerTours(MouseEvent event){
    	// TODO
		//first_clic = true;
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
    
    
    
    
    /**
     * Valide un tour de joueur actif
     * @param event
     */
    @FXML private void validerTour(MouseEvent event){
		Partie partie = liste_Ecran.moteur.partie;
		
    	if (liste_Ecran.moteur.phasePlacement) {
			System.out.println("appelle fonction placement au clic valider tour");
			liste_Ecran.moteur.placement(coord_pingouin_encours);
			System.out.println("retour à mouse event valider tour");
		}
		else if (liste_Ecran.moteur.phaseJeu) {
			//liste_Ecran.moteur.deplacement(cc);

			//TODO
			//partie.deplacement(depart, arrivee);
	    	//depart = new Coordonnees();
	    	//phaseJeu = partie.estPartieFini();
	
		} else if (liste_Ecran.moteur.phaseVictoire){
			//TODO
		}
    	
    	//arrivee = new Coordonnees();
    	coord_pingouin_encours = new Coordonnees(-1,-1);
    	
    	
    	if(partie.getJoueurActif() instanceof model.IA){
    		this.box_boutons_tour.setDisable(true);
    		this.box_boutons_tour.setVisible(false);
    		
    		this.box_tour_distant.setDisable(false);
    		this.box_tour_distant.setVisible(true);
    	} else {
    		this.box_boutons_tour.setDisable(false);
    		this.box_boutons_tour.setVisible(true);
    		
    		this.box_tour_distant.setDisable(true);
    		this.box_tour_distant.setVisible(false);
    	}
    	
    	
		/*
		 * Mise a jour de l'activation du bouton fin de tour
		 * 
		 * phasePlacement && !coord_pingouin_encours.estInvalide() || phaseJeu
		 * && !depart.estInvalide() && !arrivee.estInvalide()
		 * 
		 */
		/*
		 * tour suivant => pingouin place = -1 -1
		 */
        
    }
    
    @FXML private void refaireTours(MouseEvent event){
    	// TODO
    	System.out.println("refaireTours");
    }
    
    
    
    
    /************************************/
    /*		gestion banquise			*/
    /************************************/
    
    /**
     * action à produire après un clic sur la banquise, utile et dispobnible uniquement pour un joueur humain
     * @param event evenement souris attendu : clic
     */
	public void anchorClick(MouseEvent event) {
		Partie partie = liste_Ecran.moteur.partie;
		int jActif = partie.joueurActif;
		int pingouinAplacer;
		
		Coordonnees indicesBanquise = getXY(event.getX(), event.getY());
		coord_pingouin_encours = new Coordonnees(-1,-1);		
		
		if ((partie.getJoueurActif() instanceof Humain) && coordValide(indicesBanquise)) {
			
			if (liste_Ecran.moteur.phasePlacement) {
				System.out.println("phase placement");
				if ( partie.isPlacementValide(indicesBanquise) ) {// si bloc innoccupé de 1poisson
					pingouinAplacer = partie.numPingouinAPlacer();
					
					ImageView miniatureActive = reglettes.get(jActif).get(pingouinAplacer);
					ImageView tuileCliquee = banquise.get(indicesBanquise.x).get(indicesBanquise.y);
					Point2D coordArrivee = ancrePourPingouin(tuileCliquee);
					
					TranslateTransition tt = new TranslateTransition(Duration.millis(300), miniatureActive );
					tt.setToX( coordArrivee.getX() - miniatureActive.getX()  );
					tt.setToY( coordArrivee.getY() - miniatureActive.getY() );
					tt.play();
					
					coord_pingouin_encours = indicesBanquise;
					bouton_finTour.setDisable(false);
				}
			}
			else if (liste_Ecran.moteur.phaseJeu) {
				// TODO
				/*if ( partie.appartientPingouin(xy) == partie.joueurActif) {
					depart = xy;
					//first_clic = false;

				} else {
					if (partie.isDeplacementValide(depart, xy)) {
						arrivee = xy;

					} else if (partie.appartientPingouin(xy) == partie.joueurActif) {
						depart = xy;
					}
				}*/
				
				// Coordonnees[] pingouins =
				// liste_Ecran.moteur.partie.pingouinsDeplacable();
			}
		}

	}
	
	
	/**
	 * Rend les coordonées pour ancrer un pingouin audessus de la tuile selectionnée 
	 * 
	 * @param tuile
	 * @return
	 */
	public Point2D ancrePourPingouin(ImageView tuile) {
		double xDansBanquise = tuile.getBoundsInParent().getMinX() + (tuile.getBoundsInParent().getWidth() / 3);
		double yDansBanquise = tuile.getBoundsInParent().getMinY();
		
		double xDansEcran = anchorBanquise.getBoundsInParent().getMinX() + xDansBanquise;
		double yDansEcran = anchorBanquise.getBoundsInParent().getMinY() + yDansBanquise;
		
		return new Point2D(xDansEcran, yDansEcran);
	}
	

	/**
	 * Transorme les coodonnées du clic dans l'anchorPane banquise en
	 * coordonnées exploitables par la classe Banquise
	 * 
	 * @param x
	 *            coordonnées liées à la hauteur (0 sur le haut de la fenetre)
	 * @param y
	 *            coordonnées liées à la largeur (0 sur la gauche de la fenetre)
	 * @return un couple de coordonnées correspondant aux indices des tableaux
	 *         banquise
	 */
	public Coordonnees getXY(double x, double y) {
		//int hauteurTuile = 48;
		//int largeurTuile = 64;
		
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
				if (j % 2 == 0) { // 0.5
					if (0.5 * newX - newY > 0) {
						i = (int) ((y / h) / 2) - 1;
						j = (int) (x / 64);
					} else {
						i = (int) ((y / h) / 2);
						j = (int) (x / 64) - 1;
					}
				} else { // -0.5
					if (-0.5 * newX - newY + 16 > 0) {
						i = (int) ((y / h) / 2) - 1;
						j = (int) (x / 64);
					} else {
						i = (int) ((y / h) / 2);
						j = (int) (x / 64);
					}
				}
			} else if (i % 4 == 2) {
				if (j % 2 == 0) { // -0.5
					if (-0.5 * newX - newY + 16 > 0) {
						i = (int) ((y / h) / 2) - 1;
						j = (int) (x / 64) - 1;
					} else {
						i = (int) ((y / h) / 2);
						j = (int) (x / 64);
					}
				} else { // 0.5
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
	
	
	public Coordonnees getCoordonnees(double x, double y) {
		//Valeurs a renseigner
		double hauteurTotaleTuile = 48; //hauteur carré + triangles haut et bas
		double tuileHauteur = hauteurTotaleTuile*2/3; //hauteur carré + triangle haut
		double tuileLargeur = 64; //largeur d'une tuile
		double c = hauteurTotaleTuile/3; //hauteur d'un "triangle"
		//Valeurs deduites
		double moitieLargeur = tuileLargeur/2;
		double gradient = c/moitieLargeur;
		
	    // Trouve la ligne et la colonne de la "boite"
	    int ligne = (int) (y / tuileHauteur);
	    boolean lignePaire = ligne % 2 == 0;
	    
	    int colonne;
	    if (lignePaire) // ajoute l'indentation si ligne paire
	    	colonne = (int) ((x - moitieLargeur) / tuileLargeur);
	    else
	    	colonne = (int) (x / tuileLargeur);
	    
	    // Trouve la position relative a l'interieur de la "boite"
	    double relY = y - (ligne * tuileHauteur);
	    double relX;
	    if (lignePaire) // ajoute l'indentation si ligne paire
	        relX = (x - (colonne * tuileLargeur)) - moitieLargeur;
	    else
	        relX = x - (colonne * tuileLargeur);
	    
	    // Ajuste ligne et colonne si le point est au dessus des arretes superieurs gauche ou droite
	    if (relY < (-gradient * relX) + c) { // cote gauche
	    	ligne--;
	        if (!lignePaire) {
	        	colonne--;
	        } else {
	        	if(x<moitieLargeur && (relY > (gradient * relX) + c)) {
	        		colonne--;
	        		ligne++;
	        	}
	        }

	    } else if (relY < (gradient * relX) - c) { // cote droit
	    	ligne--;
	        if (lignePaire) colonne++;
	        
	        
	    } else {
        	if(lignePaire && x<moitieLargeur) {
        		colonne--;
        	}
	    }
	    
	    Coordonnees coord = new Coordonnees();
	    if(!((lignePaire && colonne>6) || colonne<0 || ligne<0 || ligne>7)) {
	    	coord = new Coordonnees(colonne,ligne); 
		}
		return coord;
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