package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;

public class ControleurJeu  implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;
	
    @FXML private Button bouton_defaire, bouton_indice, bouton_annuler, bouton_finTour, bouton_faire;
    @FXML private AnchorPane anchorPane_j1,anchorPane_j2,anchorPane_j3,anchorPane_j4;
    @FXML private Arc banquise_j1,banquise_j2,banquise_j3,banquise_j4;
    @FXML private Label nom_j1,nom_j2,nom_j3,nom_j4;
  /*  @FXML private ArrayList<Label> noms_joueurs;
    <fx:define>
    <ArrayList fx:id="noms_joueurs" >
        <fx:reference source="nom_j1"/>
        <fx:reference source="nom_j2"/>
        <fx:reference source="nom_j3"/>
        <fx:reference source="nom_j4"/>
    </ArrayList>
</fx:define>*/
    @FXML private HBox reglette_j1,reglette_j2,reglette_j3,reglette_j4;
    @FXML private Label score_poissons_j1,score_poissons_j2,score_poissons_j3,score_poissons_j4;
    @FXML private Label score_tuiles_j1,score_tuiles_j2,score_tuiles_j3,score_tuiles_j4;

    //récupère le fxml associé à ce controleur (et donc la partie en cours)
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void miseAjour(){
    	
    	if(gestionnaireFxmlCourant.partie!=null){
    		Paint p[] = {Paint.valueOf("ffda4a"),Paint.valueOf("37cc2c"),Paint.valueOf("950303"),Paint.valueOf("2394f1")};
    		
    		if(gestionnaireFxmlCourant.partie.joueurs.length>=1){
	    		banquise_j1.setFill( p[0]);
	    		nom_j1.setText(gestionnaireFxmlCourant.partie.joueurs[0].nom);
	    		score_poissons_j1.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[0].poissonsManges) );
	    		score_tuiles_j1.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[0].nbTuiles) );
	    		//reglette
    		}
    		
    		if(gestionnaireFxmlCourant.partie.joueurs.length>=2){
	    		banquise_j2.setFill( p[1]);
	    		nom_j2.setText(gestionnaireFxmlCourant.partie.joueurs[1].nom);
	    		score_poissons_j2.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[1].poissonsManges) );
	    		score_tuiles_j2.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[1].nbTuiles) );
	    		//reglette
    		}
    		
    		if(gestionnaireFxmlCourant.partie.joueurs.length>=3){
	    		banquise_j3.setFill( p[2]);
	    		nom_j3.setText(gestionnaireFxmlCourant.partie.joueurs[2].nom);
	    		score_poissons_j3.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[2].poissonsManges) );
	    		score_tuiles_j3.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[2].nbTuiles) );
	    		//reglette
    		}
    		
    		if(gestionnaireFxmlCourant.partie.joueurs.length>=4){
	    		banquise_j4.setFill( p[3]);
	    		nom_j4.setText(gestionnaireFxmlCourant.partie.joueurs[3].nom);
	    		score_poissons_j4.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[3].poissonsManges) );
	    		score_tuiles_j4.setText( String.valueOf(gestionnaireFxmlCourant.partie.joueurs[3].nbTuiles) );
	    		//reglette
    		}
	    	
   
    		for(int k=0; k<gestionnaireFxmlCourant.partie.joueurs.length; k++){
    			//((Arc) banquises_joueurs[k]).setFill(p[k]);
    			//noms_joueurs.get(k).setText( gestionnaireFxmlCourant.partie.joueurs[k].nom );
    			//((Label)scores_poissons_joueurs[k]).setText( String.valueOf( gestionnaireFxmlCourant.partie.joueurs[k].poissonsManges ) );
    			//((Label)scores_tuiles_joueurs[k]).setText( String.valueOf( gestionnaireFxmlCourant.partie.joueurs[k].nbTuiles ) );
    		}
    	}
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

    
}