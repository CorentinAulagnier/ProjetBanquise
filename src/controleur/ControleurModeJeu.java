package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import vue.ChargeurFxml;
import vue.EnfantFxml;
import vue.Interface;

public class ControleurModeJeu implements Initializable, EnfantFxml {
	ChargeurFxml monChargeurFxml;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    

    /** SELECTION D'UN SKIN DE PERSONNAGE **/
    
    @FXML 
    private StackPane pilerouge;
    @FXML 
    private StackPane pilejaune;
    @FXML 
    private StackPane pileverte;
    @FXML 
    private StackPane pilebleue;
    
    int imagebleue=0;
    int imagerouge=0;
    int imagejaune=0;
    int imageverte=0;
    
    public void selectImage(StackPane stck, int index, boolean direction){//false = gauche, true = droite
    	stck.getChildren().get(index).setVisible(false);
    	if (direction){
    		index = (index+1)%5;
    	}else{
    		index=(index-1)%5;
    	}
    	stck.getChildren().get(index).setVisible(true);
    }
    
    @FXML
    public void rougedroite(ActionEvent event){
    	selectImage(pilerouge,imagerouge,true);
    }
    @FXML
    public void rougegauche(ActionEvent event){
    	selectImage(pilerouge,imagerouge,false);
    }
    @FXML
    public void bleudroite(ActionEvent event){
    	selectImage(pilebleue,imagebleue,true);
    }
    @FXML
    public void bleugauche(ActionEvent event){
    	selectImage(pilebleue,imagebleue,false);
    }
    @FXML
    public void vertdroite(ActionEvent event){
    	selectImage(pileverte,imageverte,true);
    }
    @FXML
    public void vertgauche(ActionEvent event){
    	selectImage(pileverte,imageverte,false);
    }
    @FXML
    public void jaunedroite(ActionEvent event){
    	selectImage(pilejaune,imagejaune,true);
    }
    @FXML
    public void jaunegauche(ActionEvent event){
    	selectImage(pilejaune,imagejaune,false);
    }
    
    /** SELECTION D'UN MODE DE PERSONNAGE **/
   
    @FXML 
    private StackPane name1;
    @FXML 
    private StackPane name2;
    @FXML 
    private StackPane name3;
    @FXML 
    private StackPane name4;
    
    
    @FXML 
    private StackPane label1;
    @FXML 
    private StackPane label2;
    @FXML 
    private StackPane label3;
    @FXML 
    private StackPane label4;
    
    int type1=0;
    int type2=0;
    int type3=0;
    int type4=0;
    
    public void selectType(StackPane stck, int index, boolean direction){//false = gauche, true = droite
    	stck.getChildren().get(index).setVisible(false);
    	if (direction){
    		index = (index+1)%5;
    	}else{
    		index=(index-1)%5;
    	}
    	stck.getChildren().get(index).setVisible(true);
    }
    
    @FXML
    public void droite1(ActionEvent event){
    	selectImage(name1,type1,true);
    	selectImage(label1,type1,true);
    }
    @FXML
    public void gauche1(ActionEvent event){
    	selectImage(name1,type1,false);
    	selectImage(label1,type1,false);
    }
    @FXML
    public void droite2(ActionEvent event){
    	selectImage(name2,type2,true);
    	selectImage(label2,type2,true);
    }
    @FXML
    public void gauche2(ActionEvent event){
    	selectImage(name2,type2,false);
    	selectImage(label2,type2,false);
    }
    @FXML
    public void droite3(ActionEvent event){
     	selectImage(name3,type3,true);
     	selectImage(label3,type3,true);
    }
    @FXML
    public void gauche3(ActionEvent event){
    	selectImage(name3,type3,false);
    	selectImage(label3,type3,false);
    }
    @FXML
    public void droite4(ActionEvent event){
    	selectImage(name4,type4,true);
    	selectImage(label4,type4,true);
    }
    @FXML
    public void gauche4(ActionEvent event){
    	selectImage(name4,type4,false);
    	selectImage(label4,type4,false);
    }
    
    
    /**GESTION DES BOUTONS**/
    @FXML 
    private Button retour;
    
    @FXML
    private void ouvrirPageAccueil(ActionEvent event){
    	System.out.println("accueil");
    	monChargeurFxml.fixeEcran(Interface.ECRAN_ACCUEIL);
    }
    
    
    
    
    public void fixeEcranParent(ChargeurFxml ecranParent){
    	monChargeurFxml = ecranParent;
    }

    @FXML
    private void ouvrirPageRegle(ActionEvent event){
    	System.out.println("regles");
    	monChargeurFxml.fixeEcran(Interface.ECRAN_REGLES);
    }
	
}
