package controleur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.java.swing.plaf.motif.resources.motif_sv;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import model.Coordonnees;
import model.Humain;
import model.IA;
import model.Joueur;
import model.Moteur;
import model.Partie;
import model.Pingouin;
import reseau.Multijoueur;
import reseau.PingouinClient;
import reseau.PingouinServer;
import vue.EcranCourant;
import vue.GestionnaireEcransFxml;

public class ControleurCreerPartie extends ControleurPere implements Initializable, EcranCourant {
	
	GestionnaireEcransFxml liste_Ecran;
	
    //zone menu
    @FXML private ImageView imageSon, imageMusique;
    @FXML AnchorPane optionbox;
    @FXML Button roue,retour;
	
/**RECUPERATION DE TOUS LES ELEMENTS DE FXML**/
    
    int[] modeJeu = new int[4];
    StackPane[] pileMode = new StackPane[4];
    StackPane[] pileNom = new StackPane[4];
    /*************/
    Label NOM = new Label();
    /*************/
    Button[] flecheDroiteMode = new Button[4];
    Button[] flecheGaucheMode = new Button[4];
    @FXML private TextField nom1;
    @FXML private StackPane pilejaune, 
    						name1, name2, name3, name4;
    
    @FXML private Label label1;
						    
							
    @FXML private Button droitjaune, gauchejaune, 
						 moderougedroit, moderougegauche, modevertdroit, modevertgauche, modebleudroit, modebleugauche ;
    
    /**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	/*MODE DE JEU ET IMAGE INITIALES*/
    	modeJeu[model.Proprietes.JAUNE]= model.Proprietes.JOUEUR;
 		modeJeu[model.Proprietes.VERT]= model.Proprietes.JOUEUR;
 		modeJeu[model.Proprietes.ROUGE]= model.Proprietes.AUCUN;
 		modeJeu[model.Proprietes.BLEU]= model.Proprietes.AUCUN;
 		 		
 		/*INITIALISATION DES PILES*/
    	
    	pileMode[model.Proprietes.JAUNE]=name1;
    	pileMode[model.Proprietes.VERT]=name2;
    	pileMode[model.Proprietes.ROUGE]=name3;
        pileMode[model.Proprietes.BLEU]=name4;
        
        NOM = label1;
		
		flecheDroiteMode[model.Proprietes.VERT] = modevertdroit;
		flecheDroiteMode[model.Proprietes.ROUGE] = moderougedroit;
		flecheDroiteMode[model.Proprietes.BLEU]= modebleudroit;
		
		flecheGaucheMode[model.Proprietes.VERT] = modevertgauche;
		flecheGaucheMode[model.Proprietes.ROUGE] = moderougegauche;
		flecheGaucheMode[model.Proprietes.BLEU] = modebleugauche;

		
		
    }
    
    @FXML
    public void retour(MouseEvent event){   
    	faireTourner(retour);
    	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_MENU_MULTI);
    }
    

    /**
     * créer les joueurs
     * @param couleur indice de la couleur
     * @param nbP nombre de joueurs
     * @return un joueur
     */
    
    @FXML private void lancerPartie(MouseEvent event){
    	
    	int nbJoueurs = 0;
    	for (int i = 0 ; i < 4 ; i++){
    		if (modeJeu[i]!=model.Proprietes.AUCUN){
    			nbJoueurs++;
    		}
    	}

    	int nbPingouins = 6 - nbJoueurs;

	    
    	Joueur[] tableauDeJoueur = new Joueur[nbJoueurs];
    	
    	for (int i = 0 ; i < nbJoueurs ; i++){
    		tableauDeJoueur[i] = creerJoueur(i,nbPingouins);

    	}
	    
	    
    	
    	Partie p = new Partie(nbJoueurs);
    	p.joueurs = tableauDeJoueur;
    	
    	liste_Ecran.moteur = new Moteur(p);
    	liste_Ecran.moteur.phasePlacement = false;

    	String[] args = {"local", "", nom1.getText()};
    	    	   
    	PingouinServer ps = new PingouinServer(liste_Ecran.moteur);
    	ps.start();

    	
    	liste_Ecran.client = new PingouinClient(liste_Ecran.moteur, args);
    	liste_Ecran.client.start();

    	liste_Ecran.chargeEcran(model.Proprietes.ECRAN_MULTI, model.Proprietes.ECRAN_MULTI_FXML);
    	
    	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_MULTI);  

    }
    
    public Joueur creerJoueur(int couleur, int nbP){
    	String name = "";
    	String path =  model.Proprietes.IMAGE_JOUEUR_PATH+modeJeu[couleur]+""+couleur+".png";
/*
    	if (couleur == model.Proprietes.JAUNE) {
    		name = nom1.getText();
    	} else {
    		name = " ";
    	}*/

    	if (modeJeu[couleur] == model.Proprietes.JOUEUR){
    		return new Humain("", nbP, path, couleur);
    	}else if (modeJeu[couleur] == model.Proprietes.CREVETTE){
    		return new IA("IA"+couleur, nbP, 1, path, couleur);
    	}else if (modeJeu[couleur] == model.Proprietes.EVE){
    		return new IA("IA"+couleur, nbP, 2, path, couleur);
    	}else if (modeJeu[couleur] == model.Proprietes.ORQUE){
    		return new IA("IA"+couleur, nbP, 3, path, couleur);
    	}else if (modeJeu[couleur] == 0) {
    		return new IA("IA"+couleur, nbP, 1, model.Proprietes.IMAGE_JOUEUR_PATH+1+""+couleur+".png", couleur);
    	}else {
    		return null;
    	}
    }
    

    /**
     * implementation demande par l'interface EcranCourant : vide car n'a pas d'utilite ici
     */
	public void miseAjour(){}
	public void donnerTimeline(Timeline timeline){}
	 
	/**
	 * implementation demande par l'interface EcranCourant : met a jour le noeud fxml parent associe a ce controleur
	 */
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	liste_Ecran = ecranParent;
    }
    
    /**
     * gere la modification de l'affichage d'un bouton lorsque la souris est presse
     * @param event evenement souris attendu : pressed
     */
    @FXML
    public void boutonPresse(MouseEvent event){
    	((Button) event.getTarget() ).setStyle(model.Proprietes.STYLE_PRESSED);
    	if ((liste_Ecran.son)&&(liste_Ecran.sonActive)){
    	MediaPlayer media = new MediaPlayer(new Media(new File(model.Proprietes.BUTTON_PATH).toURI().toString()));
		media.play();
		}
    	
    }
    
    
    /**
     * 
     * @param event
     */
    @FXML
    public void flecheModeEvent(MouseEvent event){
    	if ( ((Button) event.getTarget() ) ==  flecheDroiteMode[model.Proprietes.ROUGE]){
    		selectMode(model.Proprietes.ROUGE,true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheMode[model.Proprietes.ROUGE]){
    		selectMode(model.Proprietes.ROUGE,false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteMode[model.Proprietes.BLEU]){
    		selectMode(model.Proprietes.BLEU,true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheMode[model.Proprietes.BLEU]){
    		selectMode(model.Proprietes.BLEU,false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteMode[model.Proprietes.VERT]){
    		selectMode(model.Proprietes.VERT,true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheMode[model.Proprietes.VERT]){
    		selectMode(model.Proprietes.VERT,false);
    	}
    	//modeValide();
    }
    
    /**
     * 
     * @param couleur
     * @param direction
     */
    public void selectMode(int couleur, boolean direction){
    	modeJeu[couleur] = selectType(pileMode[couleur],modeJeu[couleur],direction);
    	justOneVisible(pileMode[couleur], modeJeu[couleur]);
    }
    
    /**
     * 
     * @param stck
     * @param indice
     * @param direction
     * @return
     */
    public int selectType(StackPane stck, int indice, boolean direction){//false = gauche, true = droit	
    	stck.getChildren().get(indice).setVisible(false);
    	if (indice == model.Proprietes.JOUEUR){	
    		indice = model.Proprietes.AUCUN;
    	}else{			
    		indice = model.Proprietes.JOUEUR;
    	}
    	stck.getChildren().get(indice).setVisible(true);
    	return indice;
    	
    }
    /**
     * SELECTION D'UN MODE DE PERSONNAGE
     * @param stck
     * @param indice
     */
    public void justOneVisible(StackPane stck, int indice){
    	for (int i = 0; i<5 ; i++){
    		if (i==indice){
    			stck.getChildren().get(i).setVisible(true);
    		}else{
    			stck.getChildren().get(i).setVisible(false);
    		}
    	}
    }
    
    
      
    /**
     * gere la modification du volume de la musique
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererMusique(MouseEvent event){
    	changerMusique(imageMusique , liste_Ecran.media, liste_Ecran);
    }
    
    /**
     * gere la modification des bruitages
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void gererSon(MouseEvent event){
    	changerSon(imageSon, liste_Ecran);
    }    
    
    /**
     * gere l'ouverture ou la fermeture du menu roue
     * @param event evenement souris attendu : clic
     */
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		majBoutonMusique(liste_Ecran,imageMusique);
    		majBoutonSon(liste_Ecran,imageSon);;
    		optionOuvrirRoue(optionbox, roue) ;
    	}else{
    		 optionFermerRoue(optionbox, roue) ;
    	}
    }
    
      
    
    
    @FXML
    private void quitter(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	alert_quitter(liste_Ecran);
    }
    
    /**
     * change d'ecran pour celui des regles
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	liste_Ecran.dernierePage = model.Proprietes.ECRAN_CREATION;
    	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    public void textEntre(KeyEvent event){
    	String nom = ((TextField) event.getTarget() ).getText();
    	if ( nom.length() <= 25){
    	}else{
    		((TextField) event.getTarget()).deleteText(25, nom.length());
    		((TextField) event.getTarget()).setCenterShape(true);
    	}
    }
    
    
}
