package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private void ouvrirPageAccueil(MouseEvent event){
    	System.out.println("accueil");
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
    
    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 4 4 4 4 ;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6 4 4 6;";
    
    
    int[] type = new int[4];
    
    int[] image = new int[4];
    
    @FXML 
    private StackPane name1;
    @FXML 
    private StackPane name2;
    @FXML 
    private StackPane name3;
    @FXML 
    private StackPane name4;
   
        
    StackPane[] name = new StackPane[4];
    
    @FXML 
    private StackPane label1;
    @FXML 
    private StackPane label2;
    @FXML 
    private StackPane label3;
    @FXML 
    private StackPane label4;
    
    StackPane[] label = new StackPane[4];
    
    @FXML 
    private StackPane pilerouge;
    @FXML 
    private StackPane pilejaune;
    @FXML 
    private StackPane pileverte;
    @FXML 
    private StackPane pilebleue;
   
    StackPane[] pile = new StackPane[4];
    
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
    
    Button[] droitImage = new Button[4];
    Button[] gaucheImage = new Button[4];
    
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
    
    
    
    
    Button[] droitMode = new Button[4];
    Button[] gaucheMode = new Button[4];
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	name[JAUNE]=name1;
    	name[VERT]=name2;
    	name[ROUGE]=name3;
        name[BLEU]=name4;
        
        label[JAUNE]=label1;
    	label[VERT]=label2;
    	label[ROUGE]=label3;
        label[BLEU]=label4;
        
        pile[JAUNE]=pilejaune;
    	pile[VERT]=pileverte;
    	pile[ROUGE]=pilerouge;
        pile[BLEU]=pilebleue;
        
        type[JAUNE]=JOUEUR;
		type[VERT]= CREVETTE;
		type[ROUGE]= AUCUN;
		type[BLEU]= AUCUN;
		
		image[JAUNE]=JOUEUR;
		image[VERT]=CREVETTE;
		image[ROUGE]=AUCUN;
		image[BLEU]=AUCUN;
		
		droitImage[JAUNE]= droitjaune;
		droitImage[VERT] = droitvert;
		droitImage[ROUGE] = droitrouge;
		droitImage[BLEU]= droitbleu;
		
		gaucheImage[JAUNE] = gauchejaune;
		gaucheImage[VERT] = gauchevert;
		gaucheImage[ROUGE] = gaucherouge;
		gaucheImage[BLEU] = gauchevert;
		
		droitMode[JAUNE]= modejaunedroit;
		droitMode[VERT] = modevertdroit;
		droitMode[ROUGE] = moderougedroit;
		droitMode[BLEU]= modebleudroit;
		
		gaucheMode[JAUNE] = modejaunegauche;
		gaucheMode[VERT] = modevertgauche;
		gaucheMode[ROUGE] = moderougegauche;
		gaucheMode[BLEU] = modevertgauche;
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
    	image[ROUGE] = selectImage(pile[ROUGE],image[ROUGE],true);
    }
    @FXML
    public void rougegauche(MouseEvent event){
    	image[ROUGE] = selectImage(pile[ROUGE],image[ROUGE],false);
    }
    /*BLEU*/
    @FXML
    public void bleudroite(MouseEvent event){
    	image[BLEU] = selectImage(pile[BLEU],image[BLEU],true);
    }
    @FXML
    public void bleugauche(MouseEvent event){
    	image[BLEU] = selectImage(pile[BLEU],image[BLEU],false);
    }
    /*VERT*/
    @FXML
    public void vertdroite(MouseEvent event){
    	image[VERT] = selectImage(pile[VERT],image[VERT],true);
    }
    @FXML
    public void vertgauche(MouseEvent event){
    	image[VERT] = selectImage(pile[VERT],image[VERT],false);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroite(MouseEvent event){
    	image[JAUNE] = selectImage(pile[JAUNE],image[JAUNE],true);
    }
    @FXML
    public void jaunegauche(MouseEvent event){
    	image[JAUNE] = selectImage(pile[JAUNE],image[JAUNE],false);
    }
    
    
    /** SELECTION D'UN MODE DE PERSONNAGE **/

    
    public int selectType(StackPane stck, int indice, boolean direction){//false = gauche, true = droit
    	
    	stck.getChildren().get(indice).setVisible(false);
    	if (direction){	indice = (indice+1)%5;
    	}else{			
    		indice = (indice+4)%5;}
    	stck.getChildren().get(indice).setVisible(true);
    	
    	return indice;
    	
    }
  
    
    public void selectLabel(int i, int type, StackPane stck){
    	if (type ==1) {
    		stck.getChildren().get(0).setVisible(true);
    		stck.getChildren().get(0).setDisable(false);
    		stck.getChildren().get(1).setVisible(false);
		}else{
			stck.getChildren().get(0).setVisible(false);
			stck.getChildren().get(0).setDisable(true);
			stck.getChildren().get(1).setVisible(true);
		}
    }
    
    @FXML
    public void droite1(MouseEvent event){
    	type[JAUNE] = selectType(name[JAUNE],type[JAUNE],true);
    	if(type[JAUNE] != 1) {
    		droitjaune.setVisible(false);
    		gauchejaune.setVisible(false);
    	}else{
        		droitjaune.setVisible(true);
        		gauchejaune.setVisible(true);
        }
    	selectLabel(1, type[JAUNE], label[JAUNE]);
    }
    @FXML
    public void gauche1(MouseEvent event){
    	type[JAUNE]=selectType(name[JAUNE],type[JAUNE],false);
    	if(type[JAUNE] != 1) {
    		droitjaune.setVisible(false);
    		gauchejaune.setVisible(false);
    	}else{
    		droitjaune.setVisible(true);
    		gauchejaune.setVisible(true);
        }
    	selectLabel(1,type[JAUNE],label[JAUNE]);
    }
    @FXML
    public void droite2(MouseEvent event){
    	type[VERT]=selectType(name[VERT],type[VERT],true);
    	if(type[VERT] != 1) {
    		droitvert.setVisible(false);
    		gauchevert.setVisible(false);
    	}else{
    		droitvert.setVisible(true);
    		gauchevert.setVisible(true);
        }
    	selectLabel(2,type[VERT],label[VERT]);
    }
    @FXML
    public void gauche2(MouseEvent event){
    	type[VERT]=selectType(name[VERT],type[VERT],false);
    	if(type[VERT] != 1) {
    		droitvert.setVisible(false);
    		gauchevert.setVisible(false);
    	}else{
    		droitvert.setVisible(true);
    		gauchevert.setVisible(true);
        }
    	selectLabel(2,type[VERT],label[VERT]);
    }
    @FXML
    public void droite3(MouseEvent event){
    	type[ROUGE]=selectType(name[ROUGE],type[ROUGE],true);
    	if(type[ROUGE] != 1) {
    		droitrouge.setVisible(false);
    		gaucherouge.setVisible(false);
    	}else{
    		droitrouge.setVisible(true);
    		gaucherouge.setVisible(true);
        }
    	selectLabel(3,type[ROUGE],label[ROUGE]);
    }
    @FXML
    public void gauche3(MouseEvent event){
    	type[ROUGE]=selectType(name[ROUGE],type[ROUGE],false);
    	if(type[ROUGE] != 1) {
    		droitrouge.setVisible(false);
    		gaucherouge.setVisible(false);
    	}else{
    		droitrouge.setVisible(true);
    		gaucherouge.setVisible(true);
        }
    	selectLabel(3,type[ROUGE],label[ROUGE]);
    }
    @FXML
    public void droite4(MouseEvent event){
    	type[BLEU]=selectType(name[BLEU],type[BLEU],true);
    	if(type[BLEU] != 1) {
    		droitbleu.setVisible(false);
    		gauchebleu.setVisible(false);
    	}else{
    		droitbleu.setVisible(true);
    		gauchebleu.setVisible(true);
        }
    	selectLabel(4,type[BLEU],label[BLEU]);
    }
    @FXML
    public void gauche4(MouseEvent event){
    	type[BLEU]=selectType(name[BLEU],type[BLEU],false);
    	if(type[BLEU] != 1) {
    		droitbleu.setVisible(false);
    		gauchebleu.setVisible(false);
    	}else{
    		droitbleu.setVisible(true);
    		gauchebleu.setVisible(true);
        }
    	selectLabel(4,type[BLEU],label[BLEU]);
    }
	
    
    
    /**PRESSION DES BOUTONS**/
    
    @FXML
    public void rougedroitpresse(MouseEvent event){
    	droitImage[ROUGE].setStyle(STYLE_PRESSED);
    }
    @FXML
    public void rougegauchepresse(MouseEvent event){
    	gaucheImage[ROUGE].setStyle(STYLE_PRESSED);
    }
    /*BLEU*/
    @FXML
    public void bleudroitpresse(MouseEvent event){
    	droitImage[BLEU].setStyle(STYLE_PRESSED);
    }
    @FXML
    public void bleugauchepresse(MouseEvent event){
    	gaucheImage[BLEU].setStyle(STYLE_PRESSED);
    }
    /*VERT*/
    @FXML
    public void vertdroitpresse(MouseEvent event){
    	droitImage[VERT].setStyle(STYLE_PRESSED);
    }
    @FXML
    public void vertgauchepresse(MouseEvent event){
    	gaucheImage[VERT].setStyle(STYLE_PRESSED);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroitepresse(MouseEvent event){
    	droitImage[JAUNE].setStyle(STYLE_PRESSED);
    }
    @FXML
    public void jaunegauchepresse(MouseEvent event){
    	gaucheImage[JAUNE].setStyle(STYLE_PRESSED);
    }
    
    /**RELACHEMENT BOUTON**/
    
    @FXML
    public void rougedroitlache(MouseEvent event){
    	droitImage[ROUGE].setStyle(STYLE_NORMAL);
    }
    @FXML
    public void rougegauchelache(MouseEvent event){
    	gaucheImage[ROUGE].setStyle(STYLE_NORMAL);
    }
    /*BLEU*/
    @FXML
    public void bleudroitlache(MouseEvent event){
    	droitImage[BLEU].setStyle(STYLE_NORMAL);
    }
    @FXML
    public void bleugauchelache(MouseEvent event){
    	gaucheImage[BLEU].setStyle(STYLE_NORMAL);
    }
    /*VERT*/
    @FXML
    public void vertdroitlache(MouseEvent event){
    	droitImage[VERT].setStyle(STYLE_NORMAL);
    }
    @FXML
    public void vertgauchelache(MouseEvent event){
    	gaucheImage[VERT].setStyle(STYLE_NORMAL);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroitelache(MouseEvent event){
    	droitImage[JAUNE].setStyle(STYLE_NORMAL);
    }
    @FXML
    public void jaunegauchelache(MouseEvent event){
    	gaucheImage[JAUNE].setStyle(STYLE_NORMAL);
    }
    
    @FXML
    public void rougedroitmodepresse(MouseEvent event){
    	droitMode[ROUGE].setStyle(STYLE_PRESSED);
    }
    @FXML
    public void rougegauchemodepresse(MouseEvent event){
    	gaucheMode[ROUGE].setStyle(STYLE_PRESSED);
    }
    /*BLEU*/
    @FXML
    public void bleudroitmodepresse(MouseEvent event){
    	droitMode[BLEU].setStyle(STYLE_PRESSED);
    }
    @FXML
    public void bleugauchemodepresse(MouseEvent event){
    	gaucheMode[BLEU].setStyle(STYLE_PRESSED);
    }
    /*VERT*/
    @FXML
    public void vertdroitmodepresse(MouseEvent event){
    	droitMode[VERT].setStyle(STYLE_PRESSED);
    }
    @FXML
    public void vertgauchemodepresse(MouseEvent event){
    	gaucheMode[VERT].setStyle(STYLE_PRESSED);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroitemodepresse(MouseEvent event){
    	droitMode[JAUNE].setStyle(STYLE_PRESSED);
    }
    @FXML
    public void jaunegauchemodepresse(MouseEvent event){
    	gaucheMode[JAUNE].setStyle(STYLE_PRESSED);
    }
    
    /**RELACHEMENT BOUTON**/
    
    @FXML
    public void rougedroitmodelache(MouseEvent event){
    	droitMode[ROUGE].setStyle(STYLE_NORMAL);
    }
    @FXML
    public void rougegauchemodelache(MouseEvent event){
    	gaucheMode[ROUGE].setStyle(STYLE_NORMAL);
    }
    /*BLEU*/
    @FXML
    public void bleudroitmodelache(MouseEvent event){
    	droitMode[BLEU].setStyle(STYLE_NORMAL);
    }
    @FXML
    public void bleugauchemodelache(MouseEvent event){
    	gaucheMode[BLEU].setStyle(STYLE_NORMAL);
    }
    /*VERT*/
    @FXML
    public void vertdroitmodelache(MouseEvent event){
    	droitMode[VERT].setStyle(STYLE_NORMAL);
    }
    @FXML
    public void vertgauchemodelache(MouseEvent event){
    	gaucheMode[VERT].setStyle(STYLE_NORMAL);
    }
    /*JAUNE*/
    @FXML
    public void jaunedroitemodelache(MouseEvent event){
    	droitMode[JAUNE].setStyle(STYLE_NORMAL);
    }
    @FXML
    public void jaunegauchemodelache(MouseEvent event){
    	gaucheMode[JAUNE].setStyle(STYLE_NORMAL);
    }
    
}
