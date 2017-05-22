package controleur;

import model.Humain;
import model.IA;
import model.Joueur;
import model.Partie;
import vue.GestionnaireEcransFxml;
import vue.EcranCourant;

import java.net.URL;
import java.util.ResourceBundle;
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

public class ControleurModeJeu extends ControleurPere implements Initializable,  EcranCourant {
	GestionnaireEcransFxml gestionnaireFxmlCourant;
	
    @FXML private Button retour, lancer, roue;
    @FXML private ImageView imageSon, imageMusique;
    @FXML private AnchorPane optionbox;
    
/**RECUPERATION DE TOUS LES ELEMENTS DE FXML**/
    @FXML private StackPane bulle;
    
    int[] modeJeu = new int[4];    
    int[] image = new int[4];
    StackPane[] pileMode = new StackPane[4];
    StackPane[] pileNom = new StackPane[4];
    StackPane[] pileImage = new StackPane[4];
    @FXML private StackPane name1, name2, name3, name4,
    						label1, label2, label3,label4, 
    						pilerouge, pilejaune, pileverte, pilebleue ;
    Button[] flecheDroiteImage = new Button[4];
    Button[] flecheGaucheImage = new Button[4];
    Button[] flecheDroiteMode = new Button[4];
    Button[] flecheGaucheMode = new Button[4];
    @FXML private Button droitjaune, droitrouge, droitbleu, droitvert,
    					 gauchejaune, gaucherouge, gauchebleu, gauchevert,
    					 modejaunedroit, modejaunegauche, moderougedroit, moderougegauche, modevertdroit, modevertgauche, modebleudroit, modebleugauche ;

    /**
	 * implementation demande par l'interface EcranCourant : met a jour le noeud fxml parent associe a ce controleur
	 */
    public void fixeEcranParent(GestionnaireEcransFxml ecranParent){
    	gestionnaireFxmlCourant = ecranParent;
    }
    
    /**
     * implementation demande par l'interface EcranCourant : vide car n'a pas d'utilite ici
     */
    public void miseAjour(){}
    
    /**
     * Initializes the controller class.
     */
    @Override public void initialize(URL url, ResourceBundle rb) {
    	
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
    	
    	 int nbPingouins = 0;
    	    
    	    if (nbJoueurs == 2){
    	    	nbPingouins = 4 ;
    	    }else if(nbJoueurs == 3){
    	    	nbPingouins = 3;
    	    }else if(nbJoueurs == 4){
    	    	nbPingouins = 2;
    	    }  	
    	
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
    	gestionnaireFxmlCourant.moteur.partie = new Partie(nbJoueurs);

    	gestionnaireFxmlCourant.moteur.partie.joueurs = tableauDeJoueur;
    		
    	gestionnaireFxmlCourant.moteur.partie.setHistorique();

    	nettoyerMenu(optionbox, roue);
    	
    	gestionnaireFxmlCourant.chargeEcran(model.Proprietes.ECRAN_JEU, model.Proprietes.ECRAN_JEU_FXML);
    	
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_JEU);  

       // liste_Ecran.moteur.execPremiereIA();

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
    	
    	
    	
    		String path = model.Proprietes.IMAGE_JOUEUR_PATH+image[couleur]+""+couleur+".png";
    				
	    	    	
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
    
    
    @FXML private void ouvrirPageAccueil(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    }
    
    
	
    /**
     * SELECTION D'UN SKIN DE PERSONNAGE
     * @param stck
     * @param indice
     * @param direction
     * @return
     */
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
    
    /**
     * 
     * @param event
     */
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
     * 
     * @param stck
     * @param indice
     */
    public void bulleVisible(StackPane stck, int indice){
    	for (int i = 0; i<3 ; i++){
    		if (i==indice){
    			stck.getChildren().get(i).setVisible(true);
    		}else{
    			stck.getChildren().get(i).setVisible(false);
    		}
    	}
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
    	if (direction){	indice = (indice+1)%5;
    	}else{			
    		indice = (indice+4)%5;}
    	stck.getChildren().get(indice).setVisible(true);
    	return indice;
    	
    }
    
    /**
     * 
     * @param couleur
     * @param direction
     */
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
    	}else if ( ((Button) event.getTarget() ) ==  flecheDroiteMode[model.Proprietes.JAUNE]){
    		selectMode(model.Proprietes.JAUNE,true);
    	}else if ( ((Button) event.getTarget() ) ==  flecheGaucheMode[model.Proprietes.JAUNE]){
    		selectMode(model.Proprietes.JAUNE,false);
    	}
    	modeValide();
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    public void textEntre(KeyEvent event){
    	String nom = ((TextField) event.getTarget() ).getText();
    	if ( nom.length() <= 20){
    		modeValide();
    	}else{
    		((TextField) event.getTarget()).deleteText(20, 21);
    	}
    }

    /**
     * 
     */
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
    	alert_quitter(gestionnaireFxmlCourant);
    }
    
    /**
     * change d'ecran pour celui des regles
     * @param event evenement souris attendu : clic
     */
    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	gestionnaireFxmlCourant.dernierePage = model.Proprietes.ECRAN_MODE;
    	gestionnaireFxmlCourant.changeEcranCourant(model.Proprietes.ECRAN_REGLES);
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
    
}
