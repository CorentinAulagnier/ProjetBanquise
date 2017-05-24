package controleur;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    	

    	liste_Ecran.chargeEcran(model.Proprietes.ECRAN_CREATION, model.Proprietes.ECRAN_CREATION_FXML);
    	
    	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_CREATION);  
    	
    	/*
    	nettoyerMenu(optionbox, roue);
    	liste_Ecran.chargeEcran(model.Proprietes., model.Proprietes.);
    	liste_Ecran.changeEcranCourant(model.Proprietes.);
    	*/
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
    		
	    	String ip = this.ip.getText();
	    	String name = this.name.getText();

	    	Pattern pattern;
	    	final String REGEX_IP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
	    	pattern = Pattern.compile(REGEX_IP);
	    	Matcher matchAddr = null;
	        matchAddr = pattern.matcher(ip);
	        
	        if (matchAddr.matches()) {
	        	String[] args = {"distant", ip, name};

	        	client = new PingouinClient(liste_Ecran.moteur, args);
	    		client.start();

	        	liste_Ecran.chargeEcran(model.Proprietes.ECRAN_MULTI, model.Proprietes.ECRAN_MULTI_FXML);
	        	liste_Ecran.changeEcranCourant(model.Proprietes.ECRAN_MULTI);  
	        	
	        } else {
				System.out.println("Connexion impossible. Réessayer avec une IP valide.");
	        }
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
    
}
