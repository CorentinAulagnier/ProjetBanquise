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
    private void lancerPartie(MouseEvent event){
    	monChargeurFxml.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    
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
		flecheGaucheImage[BLEU] = gauchebleu;
		
		flecheDroiteMode[JAUNE]= modejaunedroit;
		flecheDroiteMode[VERT] = modevertdroit;
		flecheDroiteMode[ROUGE] = moderougedroit;
		flecheDroiteMode[BLEU]= modebleudroit;
		
		flecheGaucheMode[JAUNE] = modejaunegauche;
		flecheGaucheMode[VERT] = modevertgauche;
		flecheGaucheMode[ROUGE] = moderougegauche;
		flecheGaucheMode[BLEU] = modebleugauche;
		
		retour.setStyle(model.Proprietes.STYLE_NORMAL);
		lancer.setStyle(model.Proprietes.STYLE_NORMAL);
		
    }
    
    
    
    /** SELECTION D'UN SKIN DE PERSONNAGE **/
    
    public int selectImage(StackPane stck, int indice , boolean direction){//false = gauche, true = droite
    	
    	stck.getChildren().get(indice).setVisible(false);
    	if (direction){							// 0 1 2 3 4
    		indice = (indice+1)%5;
    		if (indice == 0){indice=1;}
    	}else{
    			indice= (indice+4)%5;
    			if (indice == 0){indice=4;}
    	}
    	stck.getChildren().get(indice).setVisible(true);
    	
    	return indice;
    }
    
    
    @FXML
    public void flecheImageEvent(MouseEvent event){
    	if ( ((Button) event.getTarget() ) ==  flecheDroiteImage[ROUGE]){
        	image[ROUGE] = selectImage(pileImage[ROUGE],image[ROUGE],true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheImage[ROUGE]){
    		image[ROUGE] = selectImage(pileImage[ROUGE],image[ROUGE],false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteImage[BLEU]){
    		image[BLEU] = selectImage(pileImage[BLEU],image[BLEU],true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheImage[BLEU]){
    		image[BLEU] = selectImage(pileImage[BLEU],image[BLEU],false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteImage[VERT]){
    		image[VERT] = selectImage(pileImage[VERT],image[VERT],true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheImage[VERT]){
    		image[VERT] = selectImage(pileImage[VERT],image[VERT],false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteImage[JAUNE]){
    		image[JAUNE] = selectImage(pileImage[JAUNE],image[JAUNE],true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheImage[JAUNE]){
    		image[JAUNE] = selectImage(pileImage[JAUNE],image[JAUNE],false);
    	}
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
    
    public void selectMode(int couleur, boolean direction){
    	modeJeu[couleur] = selectType(pileMode[couleur],modeJeu[couleur],direction);
    	if(modeJeu[couleur] != 1) {
    		flecheDroiteImage[couleur].setVisible(false);
    		flecheGaucheImage[couleur].setVisible(false);
    	}else{
    		flecheDroiteImage[couleur].setVisible(true);
    		flecheGaucheImage[couleur].setVisible(true);
        }
    	justOneVisible(pileNom[couleur], modeJeu[couleur]);
		justOneVisible(pileImage[couleur], modeJeu[couleur]);
		image[couleur]=modeJeu[couleur];
    }
    
    @FXML
    public void flecheModeEvent(MouseEvent event){
    	if ( ((Button) event.getTarget() ) ==  flecheDroiteMode[ROUGE]){
    		selectMode(ROUGE,true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheMode[ROUGE]){
    		selectMode(ROUGE,false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteMode[BLEU]){
    		selectMode(BLEU,true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheMode[BLEU]){
    		selectMode(BLEU,false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteMode[VERT]){
    		selectMode(VERT,true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheMode[VERT]){
    		selectMode(VERT,false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteMode[JAUNE]){
    		selectMode(JAUNE,true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheMode[JAUNE]){
    		selectMode(JAUNE,true);
    	}
    	modeValide();
    }
    
   
    public void modeValide(){
    	boolean JRV = ((modeJeu[JAUNE] == AUCUN) && (modeJeu[ROUGE] == AUCUN) && (modeJeu[VERT] == AUCUN));
    	boolean JRB = ((modeJeu[JAUNE] == AUCUN) && (modeJeu[ROUGE] == AUCUN) && (modeJeu[BLEU] == AUCUN));
    	boolean JBV = ((modeJeu[JAUNE] == AUCUN) && (modeJeu[BLEU] == AUCUN) && (modeJeu[VERT] == AUCUN));
    	boolean BRV = ((modeJeu[BLEU] == AUCUN) && (modeJeu[ROUGE] == AUCUN) && (modeJeu[VERT] == AUCUN));
    	if (!(JRV||JRB||JBV||BRV)){
    		lancer.setDisable(false);
    	}else{
    		lancer.setDisable(true);
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
