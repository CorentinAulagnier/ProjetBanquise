package controleur;

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
import javafx.scene.text.Text;
import model.Coordonnees;
import model.Humain;
import model.IA;
import model.Joueur;
import model.Moteur;
import model.Partie;
import reseau.Multijoueur;
import reseau.PingouinClient;
import vue.EcranCourant;
import vue.GestionnaireEcransFxml;

public class ControleurCreerPartie extends ControleurPere implements Initializable, EcranCourant {
	
	GestionnaireEcransFxml liste_Ecran;
	
    //zone menu
    @FXML private ImageView imageSon, imageMusique;
    @FXML AnchorPane optionbox;
    @FXML Button roue;
    //@FXML StackPane pilejaune;
   // @FXML Button droitjaune;
    int indice = model.Proprietes.JOUEUR;;
	
/**RECUPERATION DE TOUS LES ELEMENTS DE FXML**/
    
    int[] modeJeu = new int[4];    
    int[] image = new int[4];
    StackPane pileMode = new StackPane();
    StackPane[] pileNom = new StackPane[4];
    StackPane pileImage = new StackPane();
    Button flecheDroiteImage = new Button();
    Button flecheGaucheImage = new Button();
    Button[] flecheDroiteMode = new Button[4];
    Button[] flecheGaucheMode = new Button[4];
    
    @FXML private StackPane nom1, pilejaune,
							label1, label2, label3,label4;

						    
							
    @FXML private Button droitjaune, gauchejaune, 
						 modejaunedroit, modejaunegauche, moderougedroit, moderougegauche, modevertdroit, modevertgauche, modebleudroit, modebleugauche ;
    
    /**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	/*MODE DE JEU ET IMAGE INITIALES*/
    	modeJeu[model.Proprietes.JAUNE]= model.Proprietes.JOUEUR;
 		modeJeu[model.Proprietes.VERT]= model.Proprietes.JOUEUR;
 		modeJeu[model.Proprietes.ROUGE]= model.Proprietes.JOUEUR;
 		modeJeu[model.Proprietes.BLEU]= model.Proprietes.JOUEUR;
 		
 		image[model.Proprietes.JAUNE]=model.Proprietes.JOUEUR;
 		image[model.Proprietes.VERT]=model.Proprietes.JOUEUR;
 		image[model.Proprietes.ROUGE]=model.Proprietes.JOUEUR;
 		image[model.Proprietes.BLEU]=model.Proprietes.JOUEUR;
 		
 		/*INITIALISATION DES PILES*/
    	
    	pileMode=nom1;
        
        pileNom[model.Proprietes.JAUNE]=label1;
    	pileNom[model.Proprietes.VERT]=label2;
    	pileNom[model.Proprietes.ROUGE]=label3;
        pileNom[model.Proprietes.BLEU]=label4;
        
        pileImage=pilejaune;

       	flecheDroiteImage= droitjaune;

		flecheGaucheImage = gauchejaune;
		
		flecheDroiteMode[model.Proprietes.JAUNE]= modejaunedroit;
		flecheDroiteMode[model.Proprietes.VERT] = modevertdroit;
		flecheDroiteMode[model.Proprietes.ROUGE] = moderougedroit;
		flecheDroiteMode[model.Proprietes.BLEU]= modebleudroit;
		
		flecheGaucheMode[model.Proprietes.JAUNE] = modejaunegauche;
		flecheGaucheMode[model.Proprietes.VERT] = modevertgauche;
		flecheGaucheMode[model.Proprietes.ROUGE] = moderougegauche;
		flecheGaucheMode[model.Proprietes.BLEU] = modebleugauche;
		/*
		retour.setStyle(model.Proprietes.STYLE_NORMAL);
		lancer.setStyle(model.Proprietes.STYLE_NORMAL);
		*/
		//modeValide();
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
    	
	    
    	Joueur[] tableauDeJoueur = new Joueur[nbJoueurs];
    	
    	for (int i = 0 ; i < 4 ; i++){
    		tableauDeJoueur[i] = creerJoueur(i,nbPingouins);
    	}
	    
	    
    	
    	Partie p = new Partie(nbJoueurs);
    	p.joueurs = tableauDeJoueur;
    	p.setHistorique();
    	
    	liste_Ecran.moteur = new Moteur(p);

    	String[] args = {"Local", "", "COCO"};
    	
    	Multijoueur m = new Multijoueur(liste_Ecran.moteur, args);

    	nettoyerMenu(optionbox, roue);
    	/*
    	liste_Ecran.chargeEcran(model.Proprietes.ECRAN_JEU, model.Proprietes.ECRAN_JEU_FXML);
    	
    	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_JEU);  
*/
       // liste_Ecran.moteur.execPremiereIA();

    }
    
    public Joueur creerJoueur(int couleur, int nbP){
    	String name = "";

    	if (couleur == 0 || !(modeJeu[couleur] == model.Proprietes.JOUEUR)) {
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
    	} else {
    		name = "";
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
    

    /**
     * implementation demande par l'interface EcranCourant : vide car n'a pas d'utilite ici
     */
	public void miseAjour(){}
	 
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
    	/*
    	if (gestionnairefxml.son){
    	MediaPlayer media = new MediaPlayer(new Media(new File(model.Proprietes.BUTTON_PATH).toURI().toString()));
		media.play();
		}
		*/
    	
    }
    
    @FXML
    public void fleche(MouseEvent event){
    	
    	pilejaune.getChildren().get(indice).setVisible(false);
    	if (((Button) event.getTarget() ) == droitjaune ){							// 0 1 2 3 4
    		indice = (indice+1)%5;
    		if (indice == 0){indice=1;}
    	}else{
    			indice= (indice+4)%5;
    			if (indice == 0){indice=4;}
    	}
    	pilejaune.getChildren().get(indice).setVisible(true);  	
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