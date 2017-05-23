package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Coordonnees;
import vue.EcranCourant;
import vue.GestionnaireEcransFxml;

public class ControleurAccueilMultijoueur extends ControleurPere implements Initializable, EcranCourant {
	
	GestionnaireEcransFxml liste_Ecran;
	Coordonnees coord_pingouin_encours;
    int pingouinAdeplacer;
	
	//boutons d'actions
    //@FXML private Button connexion, rejoindre, creer;
   // @FXML private TextField ip,name;
   //@FXML private AnchorPane pane_connexion, pane_menumulti;
    
    //zone menu
    @FXML private ImageView imageSon, imageMusique;
    @FXML AnchorPane optionbox;
    @FXML Button roue;
	
    /**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	/*connexion.setStyle(model.Proprietes.STYLE_NORMAL);
    	rejoindre.setStyle(model.Proprietes.STYLE_NORMAL);
		creer.setStyle(model.Proprietes.STYLE_NORMAL);
		
		pane_connexion.setDisable(true);
		pane_connexion.setVisible(false);*/
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
    
    /****************************************/
    /*		gestion actions des joueurs		*/
    /****************************************/
    

  /*  @FXML private void creerPartie(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	System.out.println("creer partie");
    }

    @FXML private void rejoindrePartie(MouseEvent event){
    	pane_menumulti.setDisable(true);
    	pane_menumulti.setVisible(false);
    	
    	pane_connexion.setDisable(false);
		pane_connexion.setVisible(true);
    }

    @FXML private void connexion(MouseEvent event){
    	System.out.println("connexion");
    }*/
    /****************************************/
    /*		gestion menu roue				*/
    /****************************************/    
    /**
     * gere l'ouverture ou la fermeture du menu roue
     * @param event evenement souris attendu : clic
     */
    @FXML
    public void boutonOption(MouseEvent event){
    	if (optionbox.isDisable()){
    		majBoutonMusique(liste_Ecran,imageMusique);
    		majBoutonSon(liste_Ecran,imageSon);
    		//TODO si ok nettoyer
    		/*if (gestionnaireFxmlCourant.musique == false){
        		imageMusique.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEOFF_PATH));
        	}else{
        		imageMusique.setImage(new Image(model.Proprietes.IMAGE_MUSIQUEON_PATH));
        	}    	*/
    		optionOuvrirRoue(optionbox, roue) ;
    	}else{
    		optionFermerRoue(optionbox, roue);
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
     * ouvre une popup de confirmatio avant de quitter l'application 
     * @param event event evenement souris attendu : clic
     */
    @FXML
    private void quitter(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	alert_quitter(liste_Ecran);
    }
    
}