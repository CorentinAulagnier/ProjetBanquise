package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;
import vue.Interface;

public class ControleurModeJeu implements Initializable,  EcranCourant {
	GestionnaireEcransFxml monChargeurFxml;
	
	 /**GESTION DES BOUTONS**/
    @FXML 
    private Button retour;
    @FXML 
    private MenuButton options;
    @FXML 
    private Button lancer;
    
    
    @FXML
    private void ouvrirPageAccueil(MouseEvent event){
    	monChargeurFxml.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	monChargeurFxml = ecranParent;
    }
	
        
    /**RECUPERATION DE TOUS LES ELEMENTS DE FXML**/
    
    final static int AUCUN = 0 ;
    final static int JOUEUR = 1 ;
    final static int CREVETTE = 2 ;
    final static int EVE = 3 ;
    final static int ORQUE = 4 ;
    
    final static int JAUNE = 0 ; 
    final static int VERT = 1 ;
    final static int ROUGE = 2 ;
    final static int BLEU = 3 ;
        
    
    int[] modeJeu = new int[4];
    
    int[] image = new int[4];
    
    @FXML 
    private StackPane name1;
    @FXML 
    private StackPane name2;
    @FXML 
    private StackPane name3;
    @FXML 
    private StackPane name4;
   
        
    StackPane[] pileMode = new StackPane[4];
    
    @FXML 
    private StackPane label1;
    @FXML 
    private StackPane label2;
    @FXML 
    private StackPane label3;
    @FXML 
    private StackPane label4;
    
    StackPane[] pileNom = new StackPane[4];
    
    @FXML 
    private StackPane pilerouge;
    @FXML 
    private StackPane pilejaune;
    @FXML 
    private StackPane pileverte;
    @FXML 
    private StackPane pilebleue;
   
    StackPane[] pileImage = new StackPane[4];
    
    @FXML 
    private Button droitjaune;
    @FXML 
    private Button droitrouge;
    @FXML 
    private Button droitbleu;
    @FXML 
    private Button droitvert;
    @FXML 
    private Button gauchejaune;
    @FXML 
    private Button gaucherouge;
    @FXML 
    private Button gauchebleu;
    @FXML 
    private Button gauchevert;
    
    Button[] flecheDroiteImage = new Button[4];
    Button[] flecheGaucheImage = new Button[4];
    
    @FXML 
    private Button modejaunedroit ;
    @FXML 
    private Button modejaunegauche ;
    @FXML 
    private Button moderougedroit ;
    @FXML 
    private Button moderougegauche ;
     @FXML 
    private Button modevertdroit ;
    @FXML 
    private Button modevertgauche ;
     @FXML 
    private Button modebleudroit ;
    @FXML 
    private Button modebleugauche ;
    
    
    
    
    Button[] flecheDroiteMode = new Button[4];
    Button[] flecheGaucheMode = new Button[4];
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	/*MODE DE JEU ET IMAGE INITIALES*/
    	modeJeu[JAUNE]=JOUEUR;
 		modeJeu[VERT]= CREVETTE;
 		modeJeu[ROUGE]= AUCUN;
 		modeJeu[BLEU]= AUCUN;
 		
 		image[JAUNE]=JOUEUR;
 		image[VERT]=CREVETTE;
 		image[ROUGE]=AUCUN;
 		image[BLEU]=AUCUN;
 		
 		/*INITIALISATION DES PILES*/
    	
    	pileMode[JAUNE]=name1;
    	pileMode[VERT]=name2;
    	pileMode[ROUGE]=name3;
        pileMode[BLEU]=name4;
        
        pileNom[JAUNE]=label1;
    	pileNom[VERT]=label2;
    	pileNom[ROUGE]=label3;
        pileNom[BLEU]=label4;
        
        pileImage[JAUNE]=pilejaune;
    	pileImage[VERT]=pileverte;
    	pileImage[ROUGE]=pilerouge;
        pileImage[BLEU]=pilebleue;
        
       	flecheDroiteImage[JAUNE]= droitjaune;
		flecheDroiteImage[VERT] = droitvert;
		flecheDroiteImage[ROUGE] = droitrouge;
		flecheDroiteImage[BLEU]= droitbleu;
		
		flecheGaucheImage[JAUNE] = gauchejaune;
		flecheGaucheImage[VERT] = gauchevert;
		flecheGaucheImage[ROUGE] = gaucherouge;
		flecheGaucheImage[BLEU] = gauchevert;
		
		flecheDroiteMode[JAUNE]= modejaunedroit;
		flecheDroiteMode[VERT] = modevertdroit;
		flecheDroiteMode[ROUGE] = moderougedroit;
		flecheDroiteMode[BLEU]= modebleudroit;
		
		flecheGaucheMode[JAUNE] = modejaunegauche;
		flecheGaucheMode[VERT] = modevertgauche;
		flecheGaucheMode[ROUGE] = moderougegauche;
		flecheGaucheMode[BLEU] = modevertgauche;
    }
    
    
    
    /** SELECTION D'UN SKIN DE PERSONNAGE **/
    
    public int selectImage(StackPane stck, int indice , boolean direction){//false = gauche, true = droite
    	
    	stck.getChildren().get(indice).setVisible(false);
    	if (direction){							// 0 1 2 3 4
    		indice = (indice+1)%5;
    	}else{
    			indice= (indice+4)%5;
    	}
    	stck.getChildren().get(indice).setVisible(true);
    	
    	return indice;
    }
    
    /*ROUGE*/
	@FXML
    public void rougedroite(MouseEvent event){
    	image[ROUGE] = selectImage(pileImage[ROUGE],image[ROUGE],true);
    }
    @FXML
    public void rougegauche(MouseEvent event){
    	image[ROUGE] = selectImage(pileImage[ROUGE],image[ROUGE],false);
    }
    /*BLEU*/
    @FXML
    public void bleudroite(MouseEvent event){
    	image[BLEU] = selectImage(pileImage[BLEU],image[BLEU],true);
    }
    @FXML
    public void bleugauche(MouseEvent event){
    	image[BLEU] = selectImage(pileImage[BLEU],image[BLEU],false);
    }
    /*VERT*/
    @FXML
    public void vertdroite(MouseEvent event){
    	image[VERT] = selectImage(pileImage[VERT],image[VERT],true);
    }
    @FXML
    public void vertgauche(MouseEvent event){
    	image[VERT] = selectImage(pileImage[VERT],image[VERT],false);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroite(MouseEvent event){
    	image[JAUNE] = selectImage(pileImage[JAUNE],image[JAUNE],true);
    }
    @FXML
    public void jaunegauche(MouseEvent event){
    	image[JAUNE] = selectImage(pileImage[JAUNE],image[JAUNE],false);
    }
    
    
    /** SELECTION D'UN MODE DE PERSONNAGE **/

    public void justOneVisible(StackPane stck, int indice){
    	for (int i = 0; i<5 ; i++){
    		if (i==indice){
    			stck.getChildren().get(i).setVisible(true);
    		}else{
    			stck.getChildren().get(i).setVisible(false);
    		}
    	}
    }
    
    public int selectType(StackPane stck, int indice, boolean direction){//false = gauche, true = droit
    	
    	stck.getChildren().get(indice).setVisible(false);
    	if (direction){	indice = (indice+1)%5;
    	}else{			
    		indice = (indice+4)%5;}
    	stck.getChildren().get(indice).setVisible(true);
    	
    	return indice;
    	
    }
    
    @FXML
    public void droite1(MouseEvent event){
    	modeJeu[JAUNE] = selectType(pileMode[JAUNE],modeJeu[JAUNE],true);
    	if(modeJeu[JAUNE] != 1) {
    		droitjaune.setVisible(false);
    		gauchejaune.setVisible(false);
    	}else{
        		droitjaune.setVisible(true);
        		gauchejaune.setVisible(true);
        }
    	justOneVisible(pileNom[JAUNE], modeJeu[JAUNE]);

		justOneVisible(pileImage[JAUNE], modeJeu[JAUNE]);
		image[JAUNE]=modeJeu[JAUNE];
    }
    @FXML
    public void gauche1(MouseEvent event){
    	modeJeu[JAUNE]=selectType(pileMode[JAUNE],modeJeu[JAUNE],false);
    	if(modeJeu[JAUNE] != 1) {
    		droitjaune.setVisible(false);
    		gauchejaune.setVisible(false);
    	}else{
    		droitjaune.setVisible(true);
    		gauchejaune.setVisible(true);
        }
    	justOneVisible(pileNom[JAUNE],modeJeu[JAUNE]);

		justOneVisible(pileImage[JAUNE], modeJeu[JAUNE]);
		image[JAUNE]=modeJeu[JAUNE];
    }
    @FXML
    public void droite2(MouseEvent event){
    	modeJeu[VERT]=selectType(pileMode[VERT],modeJeu[VERT],true);
    	if(modeJeu[VERT] != 1) {
    		droitvert.setVisible(false);
    		gauchevert.setVisible(false);
    	}else{
    		droitvert.setVisible(true);
    		gauchevert.setVisible(true);
        }
    	justOneVisible(pileNom[VERT],modeJeu[VERT]);
    	justOneVisible(pileImage[VERT], modeJeu[VERT]);
		image[VERT]=modeJeu[VERT];
    }
    @FXML
    public void gauche2(MouseEvent event){
    	modeJeu[VERT]=selectType(pileMode[VERT],modeJeu[VERT],false);
    	if(modeJeu[VERT] != 1) {
    		droitvert.setVisible(false);
    		gauchevert.setVisible(false);
    	}else{
    		droitvert.setVisible(true);
    		gauchevert.setVisible(true);
        }
    	justOneVisible(pileNom[VERT],modeJeu[VERT]);
    	justOneVisible(pileImage[VERT], modeJeu[VERT]);
		image[VERT]=modeJeu[VERT];
    }
    @FXML
    public void droite3(MouseEvent event){
    	modeJeu[ROUGE]=selectType(pileMode[ROUGE],modeJeu[ROUGE],true);
    	if(modeJeu[ROUGE] != 1) {
    		droitrouge.setVisible(false);
    		gaucherouge.setVisible(false);
    	}else{
    		droitrouge.setVisible(true);
    		gaucherouge.setVisible(true);
        }
    	justOneVisible(pileNom[ROUGE],modeJeu[ROUGE]);
    	justOneVisible(pileImage[ROUGE], modeJeu[ROUGE]);
		image[ROUGE]=modeJeu[ROUGE];
    }
    @FXML
    public void gauche3(MouseEvent event){
    	modeJeu[ROUGE]=selectType(pileMode[ROUGE],modeJeu[ROUGE],false);
    	if(modeJeu[ROUGE] != 1) {
    		droitrouge.setVisible(false);
    		gaucherouge.setVisible(false);
    	}else{
    		droitrouge.setVisible(true);
    		gaucherouge.setVisible(true);
        }
    	justOneVisible(pileNom[ROUGE],modeJeu[ROUGE]);
		justOneVisible(pileImage[ROUGE], modeJeu[ROUGE]);
		image[ROUGE]=modeJeu[ROUGE];
    }
    @FXML
    public void droite4(MouseEvent event){
    	modeJeu[BLEU]=selectType(pileMode[BLEU],modeJeu[BLEU],true);
    	if(modeJeu[BLEU] != 1) {
    		droitbleu.setVisible(false);
    		gauchebleu.setVisible(false);
    	}else{
    		droitbleu.setVisible(true);
    		gauchebleu.setVisible(true);
        }
    	justOneVisible(pileNom[BLEU],modeJeu[BLEU]);
    	justOneVisible(pileImage[BLEU], modeJeu[BLEU]);
		image[BLEU]=modeJeu[BLEU];
    }
    @FXML
    public void gauche4(MouseEvent event){
    	modeJeu[BLEU]=selectType(pileMode[BLEU],modeJeu[BLEU],false);
    	if(modeJeu[BLEU] != 1) {
    		droitbleu.setVisible(false);
    		gauchebleu.setVisible(false);
    	}else{
    		droitbleu.setVisible(true);
    		gauchebleu.setVisible(true);
        }
    	justOneVisible(pileNom[BLEU],modeJeu[BLEU]);
    	justOneVisible(pileImage[BLEU], modeJeu[BLEU]);
		image[JAUNE]=modeJeu[JAUNE];
    }
	
    
    
    /**-------------------------------------------PRESSION DES BOUTONS-------------------------------------------**/
    
    @FXML
    public void rougedroitpresse(MouseEvent event){
    	flecheDroiteImage[ROUGE].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void rougegauchepresse(MouseEvent event){
    	flecheGaucheImage[ROUGE].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    /*BLEU*/
    @FXML
    public void bleudroitpresse(MouseEvent event){
    	flecheDroiteImage[BLEU].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void bleugauchepresse(MouseEvent event){
    	flecheGaucheImage[BLEU].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    /*VERT*/
    @FXML
    public void vertdroitpresse(MouseEvent event){
    	flecheDroiteImage[VERT].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void vertgauchepresse(MouseEvent event){
    	flecheGaucheImage[VERT].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroitepresse(MouseEvent event){
    	flecheDroiteImage[JAUNE].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void jaunegauchepresse(MouseEvent event){
    	flecheGaucheImage[JAUNE].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void rougedroitmodepresse(MouseEvent event){
    	flecheDroiteMode[ROUGE].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void rougegauchemodepresse(MouseEvent event){
    	flecheGaucheMode[ROUGE].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    /*BLEU*/
    @FXML
    public void bleudroitmodepresse(MouseEvent event){
    	flecheDroiteMode[BLEU].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void bleugauchemodepresse(MouseEvent event){
    	flecheGaucheMode[BLEU].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    /*VERT*/
    @FXML
    public void vertdroitmodepresse(MouseEvent event){
    	flecheDroiteMode[VERT].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void vertgauchemodepresse(MouseEvent event){
    	flecheGaucheMode[VERT].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroitemodepresse(MouseEvent event){
    	flecheDroiteMode[JAUNE].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void jaunegauchemodepresse(MouseEvent event){
    	flecheGaucheMode[JAUNE].setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void retourpresse(MouseEvent event){
    	retour.setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void lancerpresse(MouseEvent event){
    	lancer.setStyle(model.Proprietes.STYLE_PRESSED);
    }
    @FXML
    public void optionspresse(MouseEvent event){
    	options.setStyle(model.Proprietes.STYLE_PRESSED);
    }
    
    
    /**-------------------------------------------RELACHEMENT DES BOUTONS-------------------------------------------**/
    
    @FXML
    public void rougedroitlache(MouseEvent event){
    	flecheDroiteImage[ROUGE].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void rougegauchelache(MouseEvent event){
    	flecheGaucheImage[ROUGE].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    /*BLEU*/
    @FXML
    public void bleudroitlache(MouseEvent event){
    	flecheDroiteImage[BLEU].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void bleugauchelache(MouseEvent event){
    	flecheGaucheImage[BLEU].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    /*VERT*/
    @FXML
    public void vertdroitlache(MouseEvent event){
    	flecheDroiteImage[VERT].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void vertgauchelache(MouseEvent event){
    	flecheGaucheImage[VERT].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroitelache(MouseEvent event){
    	flecheDroiteImage[JAUNE].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void jaunegauchelache(MouseEvent event){
    	flecheGaucheImage[JAUNE].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void rougedroitmodelache(MouseEvent event){
    	flecheDroiteMode[ROUGE].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void rougegauchemodelache(MouseEvent event){
    	flecheGaucheMode[ROUGE].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    /*BLEU*/
    @FXML
    public void bleudroitmodelache(MouseEvent event){
    	flecheDroiteMode[BLEU].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void bleugauchemodelache(MouseEvent event){
    	flecheGaucheMode[BLEU].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    /*VERT*/
    @FXML
    public void vertdroitmodelache(MouseEvent event){
    	flecheDroiteMode[VERT].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void vertgauchemodelache(MouseEvent event){
    	flecheGaucheMode[VERT].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroitemodelache(MouseEvent event){
    	flecheDroiteMode[JAUNE].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void jaunegauchemodelache(MouseEvent event){
    	flecheGaucheMode[JAUNE].setStyle(model.Proprietes.STYLE_NORMAL);
    }
    
    @FXML
    public void retourlache(MouseEvent event){
    	retour.setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void lancerlache(MouseEvent event){
    	lancer.setStyle(model.Proprietes.STYLE_NORMAL);
    }
    @FXML
    public void optionslache(MouseEvent event){
    	options.setStyle(model.Proprietes.STYLE_NORMAL);
    }
    
}
