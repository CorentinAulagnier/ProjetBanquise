package controleur;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Joueur;


public class ControleurVictoire  extends ControleurPere implements Initializable, EcranCourant  {
	GestionnaireEcransFxml gestionnaireFxmlCourant;
 
	 @FXML 
	 private Button accueil;
	 @FXML 
	 private Button nouvelle_partie;
	 @FXML 
	 private Button nouvelle_manche;
	 
	 @FXML private ImageView imageMusique, imageSon;
	
	 @FXML private ImageView winner_1, winner_2, winner_3, winner_4;
	 @FXML private ImageView loser_1, loser_2, loser_3;
	 ArrayList<ImageView> winners;
	 ArrayList<ImageView> losers;

	 @FXML private Label nom_1, nom_2, nom_3, nom_4;
	 @FXML private Label pois_1, pois_2, pois_3, pois_4;
	 @FXML private Label banq_1, banq_2, banq_3, banq_4;
	 
	 ArrayList<Label> noms;
	 ArrayList<Label> poissons ;
	 ArrayList<Label> banquise ;
	 
	 @FXML private Button roue;
	 @FXML private AnchorPane optionbox;
	 
	 
	 public void miseAjour(){
		 ArrayList<Joueur> gagnants = gestionnaireFxmlCourant.moteur.partie.getGagnant();
		 Joueur[] Classement = gestionnaireFxmlCourant.moteur.partie.classement();
		 
		 int i = 0;
		 for(Joueur players : gagnants){
			 winners.get(i).setVisible(true);
			 winners.get(i).setImage(new Image(players.cheminMiniature));
			 noms.get(i).setVisible(true);
			 noms.get(i).setText(players.nom);
			 poissons.get(i).setVisible(true);
			 poissons.get(i).setText(""+players.poissonsManges);
			 banquise.get(i).setVisible(true);
			 banquise.get(i).setText(""+players.nbTuiles);
			 i++;
		}
		 int k = 0;
		 
		 for(int j = i ; j<gestionnaireFxmlCourant.moteur.partie.nbJoueurs; j++ ){
			 losers.get(k).setVisible(true);
			 losers.get(k).setImage(new Image(Classement[j].cheminMiniature));
			 noms.get(k+i).setVisible(true);
			 noms.get(k+i).setText(Classement[j].nom);
			 poissons.get(k+i).setVisible(true);
			 poissons.get(k+i).setText(""+Classement[j].poissonsManges);
			 banquise.get(k+i).setVisible(true);
			 banquise.get(k+i).setText(""+Classement[j].nbTuiles);
			 
			 TranslateTransition tt = new TranslateTransition(Duration.millis(2000), losers.get(k));
			 tt.setFromY(0);
			 tt.setToY(10);
			 tt.setAutoReverse(true);
			 tt.setCycleCount(Timeline.INDEFINITE);
			 tt.setDelay(Duration.millis(k*1000));
			 tt.play();
			 k++;
		 }		 
		 
	 }
	 
	 public void nettoyage(){
		 for (int i = 0; i < 4 ; i++){
		 winners.get(i).setVisible(false);
		 noms.get(i).setVisible(false);
		 poissons.get(i).setVisible(false);
		 banquise.get(i).setVisible(false);
			 if(i!=3){
				 losers.get(i).setVisible(false);
			 }
		 }
	 }
	 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	accueil.setStyle(model.Proprietes.STYLE_NORMAL);
    	nouvelle_partie.setStyle(model.Proprietes.STYLE_NORMAL);
    	nouvelle_manche.setStyle(model.Proprietes.STYLE_NORMAL);
    	 winners = new ArrayList <ImageView>(); Collections.addAll(winners,winner_1, winner_2, winner_3, winner_4);
    	 losers = new ArrayList <ImageView>(); Collections.addAll(losers,loser_1, loser_2, loser_3);
    	 poissons = new ArrayList <Label>(); Collections.addAll(poissons,pois_1, pois_2, pois_3, pois_4);
    	 banquise = new ArrayList <Label>(); Collections.addAll(banquise, banq_1, banq_2, banq_3, banq_4);
    	 noms = new ArrayList <Label>(); Collections.addAll(noms,nom_1,nom_2,nom_3,nom_4);
    	 
    }
    
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    }

    @FXML
    private void ouvrirPageMode(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	nettoyage();
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_MODE);
    }
    
    @FXML
    private void ouvrirPageJeu (MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	nettoyage();
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_JEU);
    }
    
    @FXML
    private void ouvrirPageAccueil(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	nettoyage();
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	gestionnaireFxmlCourant.dernierePage = model.Proprietes.ECRAN_VICTOIRE;
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
    }
    
    /**
     * gere l'ouverture ou la fermeture du menu roue
     * @param event evenement souris attendu : clic
     */
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		majBoutonMusique(gestionnaireFxmlCourant,imageMusique);
    		majBoutonSon(gestionnaireFxmlCourant,imageSon);;
    		optionOuvrirRoue(optionbox, roue) ;
    	}else{
    		 optionFermerRoue(optionbox, roue) ;
    	}
    }
    
    @FXML
    private void quitter(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	nettoyage();
    	alert_quitter(gestionnaireFxmlCourant);
    }
    
    /**
     * gere la modification du volume de la musique
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererMusique(MouseEvent event){
    	changerMusique(imageMusique , gestionnaireFxmlCourant.media, gestionnaireFxmlCourant);
    }
    
    /**
     * gere la modification des bruitages
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererSon(MouseEvent event){
    	changerSon(imageSon, gestionnaireFxmlCourant);
    } 
    
    /**
     * gere la modification de l'affichage d'un bouton lorsque la souris est presse
     * @param event evenement souris attendu : pressed
     */
    @FXML
    public void boutonPresse(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_PRESSED);
    	/*
    	if (gestionnairefxml.son){
    	MediaPlayer media = new MediaPlayer(new Media(new File(model.Proprietes.BUTTON_PATH).toURI().toString()));
		media.play();
		}
		*/
    	
    }
}