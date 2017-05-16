package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.Humain;
import model.IA;
import model.Joueur;
import model.Partie;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

public class ControleurModeJeu extends ControleurPere implements Initializable,  EcranCourant {
	GestionnaireEcransFxml monChargeurFxml;
	
	 /**GESTION DES BOUTONS**/
    @FXML 
    private Button retour;
    @FXML 
    private Button lancer;
    
    
       
    
    @FXML
    private void lancerPartie(MouseEvent event){
    	
    	int nbJoueurs = 0;
    	for (int i = 0 ; i < 4 ; i++){
    		if (modeJeu[i]!=model.Proprietes.AUCUN){
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
    	
    	
    	Joueur jjaune = creerJoueur(model.Proprietes.JAUNE,nbPingouins);
    	Joueur jrouge = creerJoueur(model.Proprietes.ROUGE,nbPingouins);
    	Joueur jbleu = creerJoueur(model.Proprietes.BLEU,nbPingouins);
    	Joueur jvert = creerJoueur(model.Proprietes.VERT,nbPingouins);    	
    	
    	Joueur[] tableauDeJoueur = new Joueur[nbJoueurs];
    	int i = 0;
    	if (jjaune!=null){
    		tableauDeJoueur[i]=jjaune;
    		i++;
    	}
    	if (jvert!=null){
    		tableauDeJoueur[i]=jvert;
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
    	    	
    	bulleVisible(bulle,1);
    	   	
    	monChargeurFxml.partie.joueurs=tableauDeJoueur;
    	
    	nettoyerRoue(optionbox, roue);
    	
    	monChargeurFxml.chargeEcran(model.Proprietes.ECRAN_JEU, model.Proprietes.ECRAN_JEU_FXML);
    	
    	monChargeurFxml.changeEcranCourant(model.Proprietes.ECRAN_JEU);    	
    	
    	System.out.println("lancement de la partie");
    	
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
    		i++;
    	}
    	
    	
    	
    		String path = model.Proprietes.IMAGE_JOUEUR_PATH+modeJeu[couleur]+""+couleur+".png";
    				
	    	    	
	    	if (modeJeu[couleur] == model.Proprietes.JOUEUR){
	    		return new Humain(name, nbP, path, couleur);
	    	}else if (modeJeu[couleur] == model.Proprietes.CREVETTE){
	    		return new IA(name, nbP, 1 ,path, couleur);
	    	}else if (modeJeu[couleur] == model.Proprietes.EVE){
	    		return new IA(name, nbP, 2 ,path, couleur);
	    	}else if (modeJeu[couleur] == model.Proprietes.ORQUE){
	    		return new IA(name, nbP, 3 , path, couleur);
	    	}else{
	    		return null;
    	}
    }
    
    
    @FXML
    private void ouvrirPageAccueil(MouseEvent event){
    	nettoyerRoue(optionbox, roue);
    	monChargeurFxml.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	monChargeurFxml = ecranParent;
    }
	
        
    /**RECUPERATION DE TOUS LES ELEMENTS DE FXML**/
    
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
    	modeJeu[model.Proprietes.JAUNE]= model.Proprietes.JOUEUR;
 		modeJeu[model.Proprietes.VERT]= model.Proprietes.CREVETTE;
 		modeJeu[model.Proprietes.ROUGE]= model.Proprietes.AUCUN;
 		modeJeu[model.Proprietes.BLEU]= model.Proprietes.AUCUN;
 		
 		image[model.Proprietes.JAUNE]=model.Proprietes.JOUEUR;
 		image[model.Proprietes.VERT]=model.Proprietes.CREVETTE;
 		image[model.Proprietes.ROUGE]=model.Proprietes.AUCUN;
 		image[model.Proprietes.BLEU]=model.Proprietes.AUCUN;
 		
 		/*INITIALISATION DES PILES*/
    	
    	pileMode[model.Proprietes.JAUNE]=name1;
    	pileMode[model.Proprietes.VERT]=name2;
    	pileMode[model.Proprietes.ROUGE]=name3;
        pileMode[model.Proprietes.BLEU]=name4;
        
        pileNom[model.Proprietes.JAUNE]=label1;
    	pileNom[model.Proprietes.VERT]=label2;
    	pileNom[model.Proprietes.ROUGE]=label3;
        pileNom[model.Proprietes.BLEU]=label4;
        
        pileImage[model.Proprietes.JAUNE]=pilejaune;
    	pileImage[model.Proprietes.VERT]=pileverte;
    	pileImage[model.Proprietes.ROUGE]=pilerouge;
        pileImage[model.Proprietes.BLEU]=pilebleue;
        
       	flecheDroiteImage[model.Proprietes.JAUNE]= droitjaune;
		flecheDroiteImage[model.Proprietes.VERT] = droitvert;
		flecheDroiteImage[model.Proprietes.ROUGE] = droitrouge;
		flecheDroiteImage[model.Proprietes.BLEU]= droitbleu;
		
		flecheGaucheImage[model.Proprietes.JAUNE] = gauchejaune;
		flecheGaucheImage[model.Proprietes.VERT] = gauchevert;
		flecheGaucheImage[model.Proprietes.ROUGE] = gaucherouge;
		flecheGaucheImage[model.Proprietes.BLEU] = gauchebleu;
		
		flecheDroiteMode[model.Proprietes.JAUNE]= modejaunedroit;
		flecheDroiteMode[model.Proprietes.VERT] = modevertdroit;
		flecheDroiteMode[model.Proprietes.ROUGE] = moderougedroit;
		flecheDroiteMode[model.Proprietes.BLEU]= modebleudroit;
		
		flecheGaucheMode[model.Proprietes.JAUNE] = modejaunegauche;
		flecheGaucheMode[model.Proprietes.VERT] = modevertgauche;
		flecheGaucheMode[model.Proprietes.ROUGE] = moderougegauche;
		flecheGaucheMode[model.Proprietes.BLEU] = modebleugauche;
		
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
    	if ( ((Button) event.getTarget() ) ==  flecheDroiteImage[model.Proprietes.ROUGE]){
        	image[model.Proprietes.ROUGE] = selectImage(pileImage[model.Proprietes.ROUGE],image[model.Proprietes.ROUGE],true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheImage[model.Proprietes.ROUGE]){
    		image[model.Proprietes.ROUGE] = selectImage(pileImage[model.Proprietes.ROUGE],image[model.Proprietes.ROUGE],false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteImage[model.Proprietes.BLEU]){
    		image[model.Proprietes.BLEU] = selectImage(pileImage[model.Proprietes.BLEU],image[model.Proprietes.BLEU],true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheImage[model.Proprietes.BLEU]){
    		image[model.Proprietes.BLEU] = selectImage(pileImage[model.Proprietes.BLEU],image[model.Proprietes.BLEU],false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteImage[model.Proprietes.VERT]){
    		image[model.Proprietes.VERT] = selectImage(pileImage[model.Proprietes.VERT],image[model.Proprietes.VERT],true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheImage[model.Proprietes.VERT]){
    		image[model.Proprietes.VERT] = selectImage(pileImage[model.Proprietes.VERT],image[model.Proprietes.VERT],false);
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteImage[model.Proprietes.JAUNE]){
    		image[model.Proprietes.JAUNE] = selectImage(pileImage[model.Proprietes.JAUNE],image[model.Proprietes.JAUNE],true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheImage[model.Proprietes.JAUNE]){
    		image[model.Proprietes.JAUNE] = selectImage(pileImage[model.Proprietes.JAUNE],image[model.Proprietes.JAUNE],false);
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
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteMode[model.Proprietes.JAUNE]){
    		selectMode(model.Proprietes.JAUNE,true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheMode[model.Proprietes.JAUNE]){
    		selectMode(model.Proprietes.JAUNE,false);
    	}
    	modeValide();
    }
    
    @FXML
    public void textEntre(KeyEvent event){
    	modeValide();
    }
  
    public void modeValide(){
    	boolean JRV = ((modeJeu[model.Proprietes.JAUNE] == model.Proprietes.AUCUN) && (modeJeu[model.Proprietes.ROUGE] == model.Proprietes.AUCUN) && (modeJeu[model.Proprietes.VERT] == model.Proprietes.AUCUN));
    	boolean JRB = ((modeJeu[model.Proprietes.JAUNE] == model.Proprietes.AUCUN) && (modeJeu[model.Proprietes.ROUGE] == model.Proprietes.AUCUN) && (modeJeu[model.Proprietes.BLEU] == model.Proprietes.AUCUN));
    	boolean JBV = ((modeJeu[model.Proprietes.JAUNE] == model.Proprietes.AUCUN) && (modeJeu[model.Proprietes.BLEU] == model.Proprietes.AUCUN) && (modeJeu[model.Proprietes.VERT] == model.Proprietes.AUCUN));
    	boolean BRV = ((modeJeu[model.Proprietes.BLEU] == model.Proprietes.AUCUN) && (modeJeu[model.Proprietes.ROUGE] == model.Proprietes.AUCUN) && (modeJeu[model.Proprietes.VERT] == model.Proprietes.AUCUN));
    	boolean JOK = (pileNom[model.Proprietes.JAUNE].getChildren().get(model.Proprietes.JOUEUR).isVisible() && ( (( (TextField)pileNom[model.Proprietes.JAUNE].getChildren().get(model.Proprietes.JOUEUR)).getText()).equals("") ) ) ;
    	boolean ROK = (pileNom[model.Proprietes.ROUGE].getChildren().get(model.Proprietes.JOUEUR).isVisible() && ( (( (TextField)pileNom[model.Proprietes.ROUGE].getChildren().get(model.Proprietes.JOUEUR)).getText()).equals("") ) ) ;
    	boolean BOK = (pileNom[model.Proprietes.BLEU].getChildren().get(model.Proprietes.JOUEUR).isVisible() && ( (( (TextField)pileNom[model.Proprietes.BLEU].getChildren().get(model.Proprietes.JOUEUR)).getText()).equals("") ) ) ;
    	boolean VOK = (pileNom[model.Proprietes.VERT].getChildren().get(model.Proprietes.JOUEUR).isVisible() && ( (( (TextField)pileNom[model.Proprietes.VERT].getChildren().get(model.Proprietes.JOUEUR)).getText()).equals("")) ) ;
    	if (!(JRV||JRB||JBV||BRV||JOK||BOK||ROK||VOK)){
    		lancer.setDisable(false);
    		bulleVisible(bulle,0);
    	}else{
    		lancer.setDisable(true);
    		bulleVisible(bulle,2);
    	}
    }
	
    
    
   
    
    @FXML AnchorPane optionbox;
    @FXML Button roue;
    
	 public void optionWheelOpen(){
		 optionbox.setDisable(false);
 		TranslateTransition tt = new TranslateTransition(Duration.millis(500), optionbox);
 	     tt.setByX(200);
 		FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
 		ft.setFromValue(0);
 		ft.setToValue(1);
 		RotateTransition rt = new RotateTransition(Duration.millis(500), roue);
 	     rt.setByAngle(180);
 		ParallelTransition pt = new ParallelTransition(optionbox,ft, tt,rt );
 	     pt.play();
	    }
	 public void optionWheelClose(){
		 optionbox.setDisable(true);
 		TranslateTransition tt = new TranslateTransition(Duration.millis(500), optionbox);
	     	tt.setByX(-200);
	     	FadeTransition ft = new FadeTransition(Duration.millis(500), optionbox);
	     	ft.setFromValue(1);
	     	ft.setToValue(0);
 		RotateTransition rt = new RotateTransition(Duration.millis(500), roue);
 	     rt.setByAngle(-180);
	     	ParallelTransition pt = new ParallelTransition(optionbox, ft, tt,rt);
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
    
    public void netoyerEcran(){
    	if (!(optionbox.isDisable())){
    		optionWheelClose();
    	}
    }
   
    public void miseAjour(){}
    
}
