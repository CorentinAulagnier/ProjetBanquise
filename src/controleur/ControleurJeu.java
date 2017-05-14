package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;
import model.Humain;
import model.IA;

public class ControleurJeu  implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;
	
    @FXML private Button bouton_defaire, bouton_indice, bouton_annuler, bouton_finTour, bouton_faire;
    @FXML private Text label_tourDe;
    @FXML private HBox box_boutons_tour;
    
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

    //recupere le fxml associe a  ce controleur (et donc la partie en cours)
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	anchorPane_j3.setVisible(false);
    	anchorPane_j4.setVisible(false);
    	bouton_defaire.setVisible(false);
    	bouton_faire.setVisible(false);
    	box_boutons_tour.setVisible(false);
    }
    
    
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
    	    reglettes.add(j1_reglette); 
    	    reglettes.add(j2_reglette);
    	    reglettes.add(j3_reglette);
    	    reglettes.add(j4_reglette);
    	    
    	    miseAjour_initiale(anchorPanes,banquises,p,noms,score_poissons,score_tuiles);
    	    
    	    miseAjour_tourDeJeu(reglettes);
    	}
    }
    
    public void miseAjour_initiale(AnchorPane[] anchorPanes, Arc[] banquises, Paint[] p, Label[] noms, Label[] score_poissons, Label[] score_tuiles){
    	int compteurDhumain =0;
    	for(int k=0; k<gestionnaireFxmlCourant.partie.joueurs.length; k++){
			majZoneJoueur(k,(AnchorPane)anchorPanes[k],(Arc)banquises[k], p[k],(Label)noms[k],(Label)score_poissons[k],(Label)score_tuiles[k]);
			if ( ( Humain.class ).equals( (gestionnaireFxmlCourant.partie.joueurs[k]).getClass() ) ){
				compteurDhumain++;
			}

			if( compteurDhumain == 1){
		    	bouton_defaire.setVisible(true);
		    	bouton_faire.setVisible(true);
			}
    	
		}
    		
    }
    
    public void miseAjour_tourDeJeu(ArrayList<ImageView[]> reglettes){
    	//maj tour de untel
    	label_tourDe.setText("Tour de "+gestionnaireFxmlCourant.partie.joueurs[gestionnaireFxmlCourant.partie.joueurActif].nom+" :");
  
    	//maj des pinguouins sur les reglettes 
    	for(int j=0; j < gestionnaireFxmlCourant.partie.joueurs.length; j++){
    		//String path = "/ressources/joueurs/" + String.valueOf(j+1) + String.valueOf(gestionnaireFxmlCourant.partie.joueurs[j].couleur) ;
    		//String path = model.Proprietes.IMAGE_JOUEUR_PATH + String.valueOf(j+1) + String.valueOf(gestionnaireFxmlCourant.partie.joueurs[j].couleur) ;
    		//String path = gestionnaireFxmlCourant.partie.joueurs[j].cheminMignature
    		//System.out.println(gestionnaireFxmlCourant.partie.joueurs[j].nom+" : "+j+" "+path);
    		for(int p=0; p < gestionnaireFxmlCourant.partie.joueurs[j].nbPingouin ; p++){
    			reglettes.get(j)[p].setImage(new Image("/ressources/joueurs/Pingouin.png"));
    			//reglettes.get(j)[p].setImage(new Image(path));
    		}
    	}

    	
    	//maj de la banquise
    	
    	//si joueur actif = humain alors montrer hbox boutons sinon attente
    	if ( !( IA.class ).equals( (gestionnaireFxmlCourant.partie.joueurs[gestionnaireFxmlCourant.partie.joueurActif]).getClass() ) ){
    		box_boutons_tour.setVisible(true);
    	}
    }
    
    
    
    public void majZoneJoueur(int joueur, AnchorPane anchors, Arc banquise, Paint p, Label nom,Label score_poissons,Label score_tuiles){
    	anchors.setVisible(true);
    	banquise.setFill(p);
    	nom.setText(gestionnaireFxmlCourant.partie.joueurs[joueur].nom);
    	score_poissons.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[joueur].poissonsManges) );
    	score_tuiles.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[joueur].nbTuiles) );
    	//reglette
    }
    
    
    /********************************************/
    /*			Gestion des boutons 			*/
    /********************************************/
    
    @FXML private void annulerTours(MouseEvent event){
    	System.out.println("annulerTours");
    }
    
    @FXML private void demanderIndice(MouseEvent event){
    	System.out.println("demanderIndice");
    }
    
    @FXML private void reinitiailiserTour(MouseEvent event){
    	System.out.println("reinitiailiserTour");
    }
    
    @FXML private void validerTour(MouseEvent event){
    	System.out.println("validerTour");
    }
    
    @FXML private void refaireTours(MouseEvent event){
    	System.out.println("refaireTours");
    }
    
    
    /*
    
    /** DESIGN AJOUTE CE WEEK END**/
    
    @FXML AnchorPane optionbox;
    @FXML Button roue;
    
	 public void optionWheelOpen(){
		 optionbox.setDisable(false);
 		FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
 		ft.setFromValue(0);
 		ft.setToValue(1);
 		
 		ScaleTransition st = new ScaleTransition(Duration.millis(500), optionbox);
     	st.setFromX(0);
     	st.setToX(1);
 		
    	TranslateTransition tt = new TranslateTransition(Duration.millis(500), optionbox);
     	tt.setByY(-100);
     	
     	
 		RotateTransition rt = new RotateTransition(Duration.millis(500), roue);
 	     rt.setByAngle(180);
 	     
 		ParallelTransition pt = new ParallelTransition(optionbox,ft,rt,st,tt );
 	     pt.play();
	    }
	 public void optionWheelClose(){
		 optionbox.setDisable(true);
		 	FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
	 		ft.setFromValue(1);
	 		ft.setToValue(0);
		 
	     	ScaleTransition st = new ScaleTransition(Duration.millis(500), optionbox);
	     	st.setFromX(1);
	     	st.setToX(0);
	     	
	     	TranslateTransition tt = new TranslateTransition(Duration.millis(500), optionbox);
	     	tt.setByY(100);
	     	
 		RotateTransition rt = new RotateTransition(Duration.millis(500), roue);
 	     rt.setByAngle(-180);
	     	ParallelTransition pt = new ParallelTransition(optionbox, ft, st ,rt,tt);
	     	pt.play();
	 }
 
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		optionWheelOpen();    		
    	}else{
    		optionWheelClose();
    	}
    }
    
/**-------------------------------------------PRESSION DES BOUTONS-------------------------------------------**/
    
    @FXML
    public void boutonPresse(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_PRESSED);
    }

    
    
    /**-------------------------------------------RELACHEMENT DES BOUTONS-------------------------------------------**/
    
    @FXML
    public void boutonLache(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_NORMAL);
    }

    
}