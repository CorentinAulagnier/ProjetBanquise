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
import reseau.PingouinClient;
import vue.EcranCourant;
import vue.GestionnaireEcransFxml;

public class ControleurAccueilMultijoueur extends ControleurPere implements Initializable, EcranCourant {
	
	GestionnaireEcransFxml liste_Ecran;
	
	//boutons d'actions
    @FXML private Button connexion, rejoindre, creer, retour;
    @FXML private TextField ip, name;
    @FXML private AnchorPane pane_connexion, pane_menumulti;
    
    //zone menu
    @FXML private ImageView imageSon, imageMusique;
    @FXML AnchorPane optionbox;
    @FXML Button roue;
	
    /**
	 * initialisation des parametres au chargement du noeud fxml associe a ce controleur
	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	connexion.setStyle(model.Proprietes.STYLE_NORMAL);
    	rejoindre.setStyle(model.Proprietes.STYLE_NORMAL);
		creer.setStyle(model.Proprietes.STYLE_NORMAL);
		
		pane_connexion.setDisable(true);
		pane_connexion.setVisible(false);
		
		//liste_Ecran
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
    

    @FXML private void creerPartie(MouseEvent event){
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
    	try {
	    	System.out.println("connexion");
	    	String ip = this.ip.getText();
	    	String name = this.name.getText();
	    	PingouinClient client = new PingouinClient(liste_Ecran.moteur);
	    	String[] args = {"distant",ip,name};
	    	client.main(args);
    	} catch(Exception e) {
    		e.printStackTrace(System.err);
    	}
    }
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
    
    
    @FXML
    private void boutonRetour(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	faireTourner(retour);
    	if (pane_connexion.isVisible()){
    		pane_connexion.setVisible(false);
    		pane_connexion.setDisable(true);
    		pane_menumulti.setDisable(false);
    		pane_menumulti.setVisible(true);
    	}else{
    		liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_ACCUEIL);
    	}
    }
    
    @FXML
    private void ouvrirPageRegle(MouseEvent event){
    	nettoyerMenu(optionbox, roue);
    	appelerRegles(liste_Ecran, model.Proprietes.ECRAN_MENU_MULTI);
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
