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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Joueur;


public class ControleurVictoire  implements Initializable, EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;
 
	 @FXML 
	 private Button accueil;
	 @FXML 
	 private Button nouvelle_partie;
	 @FXML 
	 private Button nouvelle_manche;
	
	 @FXML private ImageView winner_1, winner_2, winner_3, winner_4;
	 @FXML private ImageView loser_1, loser_2, loser_3;
	 ImageView winners[] = {winner_1, winner_2, winner_3, winner_4};
	 ImageView losers[] = {loser_1, loser_2, loser_3};

	 @FXML private Label nomPremier, nomDeuxieme, nomTroisieme, nomQuatrieme;
	 @FXML private Label poissonsPremier, poissonsDeuxieme, poissonsTroisieme, poissonsQuatrieme;
	 @FXML private Label banquisePremier, banquiseDeuxieme, banquiseTroisieme, banquiseQuatrieme;
	 
	 Label[] noms = {nomPremier, nomDeuxieme, nomTroisieme, nomQuatrieme};
	 Label[] poissons = {poissonsPremier, poissonsDeuxieme, poissonsTroisieme, poissonsQuatrieme};
	 Label[] banquise = {banquisePremier, banquiseDeuxieme, banquiseTroisieme, banquiseQuatrieme};
	 
	 
	 public void miseAjour(){
		 ArrayList<Joueur> gagnants = gestionnaireFxmlCourant.moteur.partie.getGagnant();
		 Joueur[] Classement = gestionnaireFxmlCourant.moteur.partie.classement();
		 
		 int i = 0;
		 for(Joueur players : gagnants){
			 winners[i].setVisible(true);
			 winners[i].setImage(new Image(players.cheminMiniature));
			 noms[i].setVisible(true);
			 noms[i].setText(players.nom);
			 poissons[i].setVisible(true);
			 poissons[i].setText(""+players.poissonsManges);
			 banquise[i].setVisible(true);
			 banquise[i].setText(""+players.nbTuiles);
			 
			 i++;
		 }
		 int k = 0;
		 for(int j = i ; j<gestionnaireFxmlCourant.moteur.partie.nbJoueurs; j++ ){
			 losers[k].setVisible(true);
			 losers[k].setImage(new Image(Classement[j].cheminMiniature));
			 noms[k].setVisible(true);
			 noms[k].setText(Classement[j].nom);
			 poissons[k].setVisible(true);
			 poissons[k].setText(""+Classement[j].poissonsManges);
			 banquise[k].setVisible(true);
			 banquise[k].setText(""+Classement[j].nbTuiles);
		 }		 
		 
	 }
	 
	 public void nettoyage(){
		 for (int i = 0; i <4 ; i++){
		 winners[i].setVisible(false);
		 noms[i].setVisible(false);
		 poissons[i].setVisible(false);
		 banquise[i].setVisible(false);
			 if(i!=3){
				 losers[i].setVisible(false);
			 }
		 }
	 }
	 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	accueil.setStyle(model.Proprietes.STYLE_NORMAL);
    	nouvelle_partie.setStyle(model.Proprietes.STYLE_NORMAL);
    	nouvelle_manche.setStyle(model.Proprietes.STYLE_NORMAL);
    }
    
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    }

    @FXML
    private void ouvrirPageMode(MouseEvent event){
    	nettoyage();
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_MODE);
    }
    
    @FXML
    private void ouvrirPageJeu (MouseEvent event){
    	nettoyage();
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_JEU);
    }
    
    @FXML
    private void ouvrirPageAccueil(MouseEvent event){
    	nettoyage();
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    
}