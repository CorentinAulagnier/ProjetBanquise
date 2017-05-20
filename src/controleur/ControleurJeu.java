package controleur;

import vue.GestionnaireEcransFxml;
import vue.EcranCourant;
import model.Partie;
import model.Banquise;
import model.Coordonnees;
import model.CoupleGenerique;
import model.Humain;
import model.IA;

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
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ControleurJeu extends ControleurPere implements Initializable, EcranCourant {
	/**
	 * Noeud FXML associe au controleur
	 */
	GestionnaireEcransFxml liste_Ecran;
	Coordonnees coord_pingouin_encours;
    int pingouinAdeplacer;
	
	//boutons d'actions
    @FXML private Button bouton_defaire, bouton_indice, bouton_annuler, bouton_finTour, bouton_faire;
    @FXML private Text text_tourDe,text_attenteJoueur;
    @FXML private AnchorPane box_tour_local, box_tour_distant, box_demarrer;
    
    //zone menu
    @FXML private ImageView imageSon, imageMusique;
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
    	desactiverAnchorPane(anchorPane_j1); desactiverAnchorPane(anchorPane_j2); desactiverAnchorPane(anchorPane_j3); desactiverAnchorPane(anchorPane_j4);
    	desactiverAnchorPane(box_demarrer); desactiverAnchorPane(box_tour_local); desactiverAnchorPane(box_tour_distant);
    	
    	bouton_defaire.setStyle(model.Proprietes.STYLE_NORMAL);
    	desactiverBouton(bouton_defaire);
    	bouton_faire.setStyle(model.Proprietes.STYLE_NORMAL);
    	desactiverBouton(bouton_faire);
    	
    	bouton_indice.setStyle(model.Proprietes.STYLE_NORMAL);
    	bouton_annuler.setStyle(model.Proprietes.STYLE_NORMAL);
    	bouton_finTour.setStyle(model.Proprietes.STYLE_NORMAL);
    	
    	bouton_finTour.setDisable(true);
    	coord_pingouin_encours = new Coordonnees();
    	pingouinAdeplacer = -1;
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
    	    
    	    //lance une timeline, qui verifie toutes 0.5secondes si l'inteface doit être raffraichie (une IA a joué...) et lance "miseAjour_tourDeJeu()"
        	Timeline timeline = new Timeline( new KeyFrame(  Duration.seconds(0.5) , new EventHandler<ActionEvent>(){
        						@Override public void handle(ActionEvent actionEvent) {
        							if(liste_Ecran.moteur.aRafraichir){
        								miseAjour_tourDeJeu();
        								liste_Ecran.moteur.partieRafraichie();
        								}
        							}}));
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
     */
    public void miseAjour_initiale(AnchorPane[] anchorPanes, Arc[] banquises, Paint[] p, Label[] noms){
    	
    	for(int j=0 ; j < liste_Ecran.moteur.partie.joueurs.length ; j++){
    		activerAnchorPane(anchorPanes[j]);
    		banquises[j].setFill(p[(liste_Ecran.moteur.partie.joueurs[j]).couleur]);
    		noms[j].setText(liste_Ecran.moteur.partie.joueurs[j].nom);	
    		
    		//maj des miniatures des pingouins 		
			String path = liste_Ecran.moteur.partie.joueurs[j].cheminMiniature;
			for(int ping = 0; ping < liste_Ecran.moteur.partie.joueurs[j].nbPingouin ; ping++){
				reglettes.get(j).get(ping).setImage(new Image(path));
			}
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
			   /* public void initZoneJoueur(int joueur, AnchorPane anchors, Arc banquisette, Paint p, Label nom){
			    	activerAnchorPane(anchors);
			    	banquisette.setFill(p);
			    	nom.setText(liste_Ecran.moteur.partie.joueurs[joueur].nom);
			    	
			    	//maj des miniatures des pinguouins 		
					String path = liste_Ecran.moteur.partie.joueurs[joueur].cheminMiniature;
					for(int ping = 0; ping < liste_Ecran.moteur.partie.joueurs[joueur].nbPingouin ; ping++){
						reglettes.get(joueur).get(ping).setImage(new Image(path));
					}
			    }*/
   
    public void nettoyerBanquise(){
    	for (int i = 0; i< 8; i++){
			for(int j = 0; j< 8; j++){
					banquise.get(i).get(j).setEffect(null);
			}
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
    	Partie partie = liste_Ecran.moteur.partie;
    	
    	//maj des scores du joueur
    	for( int j=0 ; j < partie.joueurs.length ; j++ ){
    		scores_poissons.get(j).setText( String.valueOf(partie.joueurs[j].poissonsManges) );
    		scores_tuiles.get(j).setText( String.valueOf(partie.joueurs[j].nbTuiles) );	
    	}

    	//maj de l'affichage la banquise
    	majBanquise();
    	
    	//maj des pingouins
    	majPingouins();
    	
    	//maj text tour de (temporaire) :
    	if(liste_Ecran.moteur.phasePlacement){text_tourDe.setText(liste_Ecran.moteur.partie.getJoueurActif().nom+" : placez un pingouin.");}
    	else if(liste_Ecran.moteur.phaseJeu){text_tourDe.setText(liste_Ecran.moteur.partie.getJoueurActif().nom+" : déplacez un pingouin.");}
    	
    	//maj effet sur joueur actif
    	Arc[] banquises = {banquise_j1,banquise_j2,banquise_j3,banquise_j4};
    	
    	nettoyerBanquise();
    	
    	if (liste_Ecran.moteur.phasePlacement){
	    	for (int i = 0; i< 8; i++){
				for(int j = 0; j< 8; j++){
					if ( partie.isPlacementValide(new Coordonnees(i,j)) ){
						banquise.get(i).get(j).setEffect(new Glow(1));
					}
				}
			}
    	}
    	

    	for (int j = 0; j<liste_Ecran.moteur.partie.nbJoueurs;j++ ){
    		for (int i = 0; i< 	liste_Ecran.moteur.partie.joueurs[j].nbPingouin; i++){
    			if (j == liste_Ecran.moteur.partie.joueurActif){
    				if (liste_Ecran.moteur.phaseJeu){
    					((ImageView) reglettes.get(j).get(i)).setEffect(new Glow(1));
    				}
    				banquises[liste_Ecran.moteur.partie.joueurs[j].couleur].setEffect(new Glow(1));
    			}else{
    				if (liste_Ecran.moteur.phaseJeu){
    					((ImageView) reglettes.get(j).get(i)).setEffect(null);
    				}
    				banquises[liste_Ecran.moteur.partie.joueurs[j].couleur].setEffect(new Lighting());
    			}
	    	}
    	}

    	//maj zone des boutons
    	majActionsDisponibles();	
    	
    } 
    
    /**
     * raffraichit la banquise
     */
    public void majBanquise(){
    	Banquise banquiseEnMemoire = liste_Ecran.moteur.partie.b;
    	for (int i = 0 ; i < banquiseEnMemoire.terrain.length ; i++){
    		for (int j = 0 ; j <  banquiseEnMemoire.terrain[i].length ; j++){
    			if ( !((i%2 == 0) && (j == 7)) ) {
    				placeUneTuile(banquiseEnMemoire.terrain[i][j].nbPoissons, i, j);
    			}	    	
    		}
    	}
    }
    
	/**
	 * verifie si chaque pingouin de chaque joueur est bien à sa place attendue en mémoire, sinon le déplace là où il devrait être
	 */
	public void majPingouins(){
		for(int jEncours = 0; jEncours < liste_Ecran.moteur.partie.joueurs.length ; jEncours++){
			for( int pingEncours = 0; pingEncours < liste_Ecran.moteur.partie.joueurs[jEncours].myPingouins.length ; pingEncours++ ){	
				//si ce pingouin est actif ( a été placé et n'a pas encore été noyé )
				if(liste_Ecran.moteur.partie.joueurs[jEncours].myPingouins[pingEncours].actif){
					Coordonnees pingouin_en_memoire = liste_Ecran.moteur.partie.joueurs[jEncours].myPingouins[pingEncours].position;
					ImageView miniature_pingouin = reglettes.get(jEncours).get(pingEncours);
					Coordonnees pingouin_en_ihm = getXY(miniature_pingouin.getX(), miniature_pingouin.getY());
					
					//verifier si le pingouin en mémoire est pas à la même place sur l'ihm
					if(!pingouin_en_memoire.equals(pingouin_en_ihm)){
						translaterPingouin(pingEncours,jEncours, pingouin_en_memoire);
					}
				}
			}
		}
	}
	
	public void majActionsDisponibles(){
		Partie partie = liste_Ecran.moteur.partie;
		//TODO gerer les tours en fonctions du reseau
		
		// le joueur actif est humain
		if(partie.getJoueurActif() instanceof Humain){
			activerAnchorPane(box_tour_local);
			desactiverAnchorPane(box_tour_distant);
			desactiverAnchorPane(box_demarrer);
			
			//il est le seul humain => utilisation ou pas de faire defaire
			if( liste_Ecran.moteur.partie.utiliseHistorique){// s'il y a un et un seul humain alors on peut defaire et faire
		    	bouton_defaire.setDisable(false);
		    	bouton_defaire.setVisible(false);
		    	bouton_faire.setDisable(false);
		    	bouton_faire.setVisible(false);
			}
		} 
		//sinon si le joueur est une IA
		else if (partie.getJoueurActif() instanceof IA){
			
			// si la partie commence (aucun pingouin n'est placé) par une IA (le joueur actif est le joueur 0) 
			if( partie.numPingouinAPlacer() == 0 && partie.joueurActif == 0){
				desactiverAnchorPane(box_tour_local);
				desactiverAnchorPane(box_tour_distant);
				activerAnchorPane(box_demarrer);

			}
			// aux autres tours
			else {
				desactiverAnchorPane(box_tour_local);
				activerAnchorPane(box_tour_distant);
				this.text_attenteJoueur.setText("Attente de "+partie.getJoueurActif().nom);
				desactiverAnchorPane(box_demarrer);
			}
		}  
	}
    

	
    /****************************************/
    /*		gestion actions des joueurs		*/
    /****************************************/
    
	/**
	 * 
	 * @param event
	 */
    @FXML private void annulerTours(MouseEvent event){
    	// TODO
		//first_clic = true;
    	System.out.println("annulerTours");
    }
    
    /**
     * 
     * @param event
     */
    @FXML private void refaireTours(MouseEvent event){
    	// TODO
    	System.out.println("refaireTours");
    }
    
    /**
     * 
     * @param event
     */
    @FXML private void demanderIndice(MouseEvent event){
    	// TODO
    	System.out.println("demanderIndice");
    }
     
    /**
     * 
     * @param event
     */
    @FXML private void reinitiailiserTour(MouseEvent event){
    	// TODO
    	majPingouins();
    	System.out.println("reinitiailiserTour");
    }
    
    /**
     * Debute une partie si c'est a une IA de jouer
     * @param event
     */
    @FXML private void lancerIA(MouseEvent event){
		liste_Ecran.moteur.execPremiereIA();
    	miseAjour_tourDeJeu();
    }
    
    /**
     * Valide un tour de joueur actif
     * @param event
     */
    @FXML private void validerTour(MouseEvent event){
		
    	if (liste_Ecran.moteur.phasePlacement) {
			liste_Ecran.moteur.placement(coord_pingouin_encours);
	    	coord_pingouin_encours = new Coordonnees();
		}
		else if (liste_Ecran.moteur.phaseJeu){
			liste_Ecran.moteur.deplacement(new CoupleGenerique<Coordonnees, Coordonnees>(liste_Ecran.moteur.partie.getJoueurActif().myPingouins[pingouinAdeplacer].position, coord_pingouin_encours));		
			coord_pingouin_encours = new Coordonnees();
		} else if (liste_Ecran.moteur.phaseVictoire){
			//TODO
		}
    	
    	coord_pingouin_encours = new Coordonnees();
    	miseAjour_tourDeJeu();
    
    }
    
 
    /************************************************/
    /*		gestion clics sur la banquise			*/
    /************************************************/
    
    /**
     * action à produire après un clic sur la banquise, utile et dispobnible uniquement pour un joueur humain
     * @param event evenement souris attendu : clic
     */
	public void anchorClick(MouseEvent event) {
		Partie partie = liste_Ecran.moteur.partie;
		int jActif = partie.joueurActif;
		int pingouinAplacer;
		
		bouton_finTour.setDisable(true);
		coord_pingouin_encours = new Coordonnees();	
		Coordonnees indicesBanquise = getXY(event.getX(), event.getY());
		
		if ((partie.getJoueurActif() instanceof Humain) && coordValide(indicesBanquise)) {
			
			if (liste_Ecran.moteur.phasePlacement) {
				
				if ( partie.isPlacementValide(indicesBanquise) ) {// si bloc innoccupé de 1poisson
					pingouinAplacer = partie.numPingouinAPlacer();
					translaterPingouin(pingouinAplacer,jActif,indicesBanquise);
					coord_pingouin_encours = indicesBanquise;
					bouton_finTour.setDisable(false);
				}
			}
			else if (liste_Ecran.moteur.phaseJeu) {

				//TODO on sélectionne un pingouin depuis sa tuile
				//pingouinAdeplacer = rendPingouinAdeplacer(indicesBanquise);
				int numPingTemporaire = rendPingouinAdeplacer(indicesBanquise);
				System.out.println("ping a deplacer (clic sur tuile) "+numPingTemporaire);
				if (numPingTemporaire!= -1){
					nettoyerBanquise();
					pingouinAdeplacer = numPingTemporaire;
					selectionnerPingouinAdeplacer(banquise.get(jActif).get(pingouinAdeplacer));
					
					//affichage des chemins
					ArrayList<ArrayList<Coordonnees>> accessibles = liste_Ecran.moteur.partie.b.deplacementPossible(indicesBanquise);
					for( ArrayList<Coordonnees> col : accessibles ){
						for ( Coordonnees lin : col){
							banquise.get(lin.x).get(lin.y).setEffect(new Glow(1));
						}
					}
					
					
	
				}
				 
				//on a déjà selectionné un pingouin donc on peut le bouger
				if ( (pingouinAdeplacer!= -1 ) && partie.isDeplacementValide( partie.getJoueurActif().myPingouins[pingouinAdeplacer].position , indicesBanquise ) ) {
					System.out.println("pingouin bouge");
					translaterPingouin(pingouinAdeplacer,jActif,indicesBanquise);
					coord_pingouin_encours = indicesBanquise;
					bouton_finTour.setDisable(false);
				}else{	System.out.println("pingouin bouge pas");}
			}
			
		}
		
	}
	
	//CETTE FONCTION EST DEVENUE INUTILE.  De la part de Seb.
	/**
	 * action à produire après un clic sur un pingouin pendant la phase jeu
	 * @param event evenement souris attendu : clic
	 */
	public void choisirPingouinAdeplacer(MouseEvent event) {
		selectionnerPingouinAdeplacer((ImageView)event.getTarget());
	}
	

	/************************************************/
	/*			méthodes spécifiques au jeu			*/
	/************************************************/
	
	/**
	 * met dans pingouinAdeplacer le numéro du pingouin a deplacer en fonction d'une miniature de pingouin cliquée
	 * @param pingouinCible miniature sur laquelle on a cliqué
	 */
	public void selectionnerPingouinAdeplacer(ImageView pingouinCible){
		//Partie partie = liste_Ecran.moteur.partie;
		int jActif =  liste_Ecran.moteur.partie.joueurActif;
		bouton_finTour.setDisable(true);
		
		if (liste_Ecran.moteur.phaseJeu) {
			//IL FAUDRAIT PEUT ETRE EVITER LE MAJ CI DESSOUS MAIS C'EST LE PLUS SIMPLE.  De la part de Seb.
			majPingouins();
			// le pingouin numéro "pingouinAdeplacer" a déjà été déplacé aux coordonnées coord_pingouin_encours
			if (coord_pingouin_encours.estInvalide() && pingouinAdeplacer != 1) {
				// on veux en bouger un autre alors on annule son mouvement
				translaterPingouin(pingouinAdeplacer, jActif, liste_Ecran.moteur.partie.getJoueurActif().myPingouins[pingouinAdeplacer].position);
			}
			if(pingouinCible!=null){
				//pingouinAdeplacer = reglettes.get( liste_Ecran.moteur.partie.joueurActif).indexOf(pingouinCible);
				System.out.println("ping a deplacer (clic sur pingouin)"+pingouinAdeplacer);
			}
			coord_pingouin_encours = new Coordonnees();
		}
	}
	
	
	/**
	 * rend le pingouin(ImageView) positionné au dessus de la tuile cliquée, s'il est au joueur actif
	 * @param tuileCliquee une tuile du plateau
	 * @return rend le pingouin(ImageView) positionné au dessus de la tuile cliquée s'il est au joueur actif
	 */
	public int rendPingouinAdeplacer( Coordonnees indicesBanquise){
		for(int p=0 ;  p < liste_Ecran.moteur.partie.getJoueurActif().myPingouins.length ; p++){
			if(liste_Ecran.moteur.partie.getJoueurActif().myPingouins[p].actif){
				if( indicesBanquise.equals(liste_Ecran.moteur.partie.getJoueurActif().myPingouins[p].position) ){
					return p;
				}
			}
		}
		return -1;
	}
	
	 /**
     * affiche la bonne tuile (image) dans l'objet Imageview correspondant aux coordonnées données et au nombre de poissons attendus
     * @param nombreDePoissons
     * @param i indice de la ligne de la tuile dans le tableau banquise 
     * @param j indice de la colonne de la tuile dans le tableau banquise
     */
	public void placeUneTuile(int nombreDePoissons, int i, int j) {
		switch (nombreDePoissons) {
		case 0:
			banquise.get(i).get(j).setImage(null);
			break;
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
	
	/**
	 * Déplace le n-ième pingouin du joueur j aux coordonnees "to" (de type indice de tableau)
	 * @param n numéro du pinguoin à déplacer
	 * @param j numéro du joueur possédant le pingouin à déplacer
	 * @param to couple d'indice représentant une tuile de la banquise
	 */
	public void translaterPingouin (int n, int j, Coordonnees to ){
		ImageView miniatureAdeplacer = reglettes.get(j).get(n);
		ImageView tuileTo = banquise.get(to.x).get(to.y);
		Point2D coord2DTo = ancrePourPingouin(tuileTo);

		TranslateTransition tt = new TranslateTransition(Duration.millis(300), miniatureAdeplacer);
		tt.setToX(coord2DTo.getX() - miniatureAdeplacer.getX());
		tt.setToY(coord2DTo.getY() - miniatureAdeplacer.getY());
		tt.play();
	}
	
	/**
	 * Rend les coordonées pour ancrer un pingouin au dessus de la tuile donnée 
	 * @param tuile tuile de la banquise au dessus de laquelle on veux déposer un pingouin
	 * @return coordonnées dans le noeud FXML expoitable pour ancrer un pingouin au dessus de la tuile donnée
	 */
	public Point2D ancrePourPingouin(ImageView tuile) {
		double xDansBanquise = tuile.getBoundsInParent().getMinX() + (tuile.getBoundsInParent().getWidth() / 3);
		double yDansBanquise = tuile.getBoundsInParent().getMinY();
		
		double xDansEcran = Math.floor(anchorBanquise.getBoundsInParent().getMinX() + xDansBanquise);
		double yDansEcran = anchorBanquise.getBoundsInParent().getMinY() + yDansBanquise;
		
		return new Point2D(xDansEcran, yDansEcran);
	}
	
	/**
	 * Transorme les coodonnées du clic dans l'anchorPane banquise en coordonnées exploitables par la classe Banquise (tuile spécifique)
	 * @param x coordonnées liées à la hauteur (0 sur le haut de la fenetre)
	 * @param y coordonnées liées à la largeur (0 sur la gauche de la fenetre)
	 * @return un couple de coordonnées correspondant aux indices des tableaux banquise
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

	/**
	 * vérifie que les coordonnées du couple xy sont des indices valides dans un tableau Banquise
	 * @param xy un couple de coordonnées correspondant aux indices des tableaux banquise
	 * @return vrai si le couple est valide, faux sinon.
	 */
	public boolean coordValide(Coordonnees xy) {
		return (xy.x != -1) && (xy.y != -1);
	}
	
	/**
	 * Transorme les coodonnées du clic dans l'anchorPane banquise en coordonnées exploitables par la classe Banquise (taille de tuile indiférente
	 * @param x coordonnées liées à la hauteur (0 sur le haut de la fenetre)
	 * @param y coordonnées liées à la largeur (0 sur la gauche de la fenetre)
	 * @return un couple de coordonnées correspondant aux indices des tableaux banquise
	 */
	public Coordonnees getCoordonnees(double x, double y) {
		//Valeurs à renseigner
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
	
	public void activerAnchorPane(AnchorPane a){
		a.setDisable(false);
    	a.setVisible(true);
	}
	public void desactiverAnchorPane(AnchorPane a){
		a.setDisable(true);
    	a.setVisible(false);
	}
	public void activerBouton(Button b){
		b.setDisable(false);
    	b.setVisible(true);
	}
	public void desactiverBouton(Button b){
		b.setDisable(true);
    	b.setVisible(false);		
	}

	
	
	
	/****************************************/
    /* 			boutons de la roue			*/
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
    	sauver(liste_Ecran.moteur);
    }
    
    /**
     * change d'ecran pour celui des regles
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	appelerRegles(liste_Ecran, model.Proprietes.ECRAN_JEU);
    }
    
    /**
     * renvoi à l'écran accueil
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageAccueil(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	alertAccueil(liste_Ecran, model.Proprietes.ECRAN_JEU);
    }
    
    
    /**
     * ouvre une popup de confirmatio avant de quitter l'application 
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void quitter(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	alert_quitter(liste_Ecran);
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
}
