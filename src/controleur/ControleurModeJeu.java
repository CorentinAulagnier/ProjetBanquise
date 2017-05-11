package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Humain;
import model.IA;
import model.Joueur;
import model.Partie;
import model.Proprietes;
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
    	
    	int nbJoueurs = 0;
    	for (int i = 0 ; i < 4 ; i++){
    		if (modeJeu[i]!=AUCUN){
    			nbJoueurs++;
    		}
    	}
    	
    	 int nbPingouins = 0;
    	    
    	    if (nbJoueurs == 2){
    	    	nbPingouins = 4 ;
    	    }else if(nbJoueurs == 3){
    	    	nbPingouins = 3;
    	    }else if(nbJoueurs == 4){
    	    	nbPingouins = 2;
    	    }
    	
    	monChargeurFxml.partie = new Partie(nbJoueurs);
    	
    	Joueur jjaune = creerJoueur(JAUNE,nbPingouins);
    	Joueur jrouge = creerJoueur(ROUGE,nbPingouins);
    	Joueur jbleu = creerJoueur(BLEU,nbPingouins);
    	Joueur jvert = creerJoueur(VERT,nbPingouins);
    	
    	Joueur[] tableauDeJoueur = new Joueur[nbJoueurs];
    	int i = 0;
    	if (jjaune!=null){
    		tableauDeJoueur[i]=jjaune;
    		i++;
    	}
    	if (jrouge!=null){
    		tableauDeJoueur[i]=jrouge;
    		i++;
    	}
    	if (jbleu!=null){
    		tableauDeJoueur[i]=jbleu;
    		i++;
    	}
    	if (jvert!=null){
    		tableauDeJoueur[i]=jvert;
    		i++;
    	}
    	
    	bulleVisible(bulle,2);
    	try{
    		Thread.sleep(2);
    	}catch(InterruptedException e){}
    	
    	monChargeurFxml.changeEcranCourant(model.Proprietes.ECRAN_JEU);    	
    	
    }
    
    public Joueur creerJoueur(int couleur, int nbP){
    	String name = "";
    	int i = 0;
    	while ((i<5)&&(name.equals(""))){
    		if (pileNom[couleur].getChildren().get(i).isVisible()){
    			if (pileNom[couleur].getChildren().get(i) instanceof Label){
    				name = ((Label)pileNom[couleur].getChildren().get(i)).getText();
    			}else if (pileNom[couleur].getChildren().get(i) instanceof TextField){
    				name = ((TextField)pileNom[couleur].getChildren().get(i)).getText();
    			}
    		}
    	}
    	
    	   	String path = "";
	    	switch(couleur){
		    	case JAUNE: switch(modeJeu[couleur]){
		    					case JOUEUR :path = model.Proprietes.PING_JAUNE_PATH;
		    					case CREVETTE : path = model.Proprietes.CREVETTE_JAUNE_PATH;
		    					case EVE :  path = model.Proprietes.EVE_JAUNE_PATH;
		    					case ORQUE :  path = model.Proprietes.ORQUE_JAUNE_PATH;
	    					}
		    	case ROUGE :switch(modeJeu[couleur]){
								case JOUEUR :path = model.Proprietes.PING_ROUGE_PATH;
								case CREVETTE : path = model.Proprietes.CREVETTE_ROUGE_PATH;
								case EVE :  path = model.Proprietes.EVE_ROUGE_PATH;
								case ORQUE :  path = model.Proprietes.ORQUE_ROUGE_PATH;
							}
		    	case BLEU :switch(modeJeu[couleur]){
								case JOUEUR :path = model.Proprietes.PING_BLEU_PATH;
								case CREVETTE : path = model.Proprietes.CREVETTE_BLEUE_PATH;
								case EVE :  path = model.Proprietes.EVE_BLEUE_PATH;
								case ORQUE :  path = model.Proprietes.ORQUE_BLEUE_PATH;
							}
		    	case VERT:switch(modeJeu[couleur]){
							case JOUEUR :path = model.Proprietes.PING_VERT_PATH;
							case CREVETTE : path = model.Proprietes.CREVETTE_VERTE_PATH;
							case EVE :  path = model.Proprietes.EVE_VERTE_PATH;
							case ORQUE :  path = model.Proprietes.ORQUE_VERTE_PATH;
						}
	    	}
	    	
	    	
	    	if (modeJeu[couleur] == JOUEUR){
	    		return new Humain(name, nbP, path, couleur);
	    	}else if (modeJeu[couleur] == CREVETTE){
	    		return new IA(name, nbP, 1 ,path, couleur);
	    	}else if (modeJeu[couleur] == EVE){
	    		return new IA(name, nbP, 2 ,path, couleur);
	    	}else if (modeJeu[couleur] == ORQUE){
	    		return new IA(name, nbP, 3 , path, couleur);
	    	}else{
	    		return null;
    	}
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
    private StackPane bulle;
    
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
		
		modeValide();
		
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
    
    public void bulleVisible(StackPane stck, int indice){
    	for (int i = 0; i<3 ; i++){
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
    		selectMode(JAUNE,false);
    	}
    	modeValide();
    }
    
    @FXML
    public void textEntre(KeyEvent event){
    	modeValide();
    }
  
    public void modeValide(){
    	boolean JRV = ((modeJeu[JAUNE] == AUCUN) && (modeJeu[ROUGE] == AUCUN) && (modeJeu[VERT] == AUCUN));
    	boolean JRB = ((modeJeu[JAUNE] == AUCUN) && (modeJeu[ROUGE] == AUCUN) && (modeJeu[BLEU] == AUCUN));
    	boolean JBV = ((modeJeu[JAUNE] == AUCUN) && (modeJeu[BLEU] == AUCUN) && (modeJeu[VERT] == AUCUN));
    	boolean BRV = ((modeJeu[BLEU] == AUCUN) && (modeJeu[ROUGE] == AUCUN) && (modeJeu[VERT] == AUCUN));
    	boolean JOK = (pileNom[JAUNE].getChildren().get(JOUEUR).isVisible() && ( (( (TextField)pileNom[JAUNE].getChildren().get(JOUEUR)).getText()).equals("") ) ) ;
    	boolean ROK = (pileNom[ROUGE].getChildren().get(JOUEUR).isVisible() && ( (( (TextField)pileNom[ROUGE].getChildren().get(JOUEUR)).getText()).equals("") ) ) ;
    	boolean BOK = (pileNom[BLEU].getChildren().get(JOUEUR).isVisible() && ( (( (TextField)pileNom[BLEU].getChildren().get(JOUEUR)).getText()).equals("") ) ) ;
    	boolean VOK = (pileNom[VERT].getChildren().get(JOUEUR).isVisible() && ( (( (TextField)pileNom[VERT].getChildren().get(JOUEUR)).getText()).equals("")) ) ;
    	if (!(JRV||JRB||JBV||BRV||JOK||BOK||ROK||VOK)){
    		lancer.setDisable(false);
    		bulleVisible(bulle,1);
    	}else{
    		lancer.setDisable(true);
    		bulleVisible(bulle,0);
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
