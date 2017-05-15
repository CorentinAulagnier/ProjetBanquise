package vue;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.Partie;

	/**
	 * Controle le passage entre scenes FXML (comme dans le tutoriel de Angela Caicedo).
	 * @author Riou Sebastien, Thisse Noémi.
	 */
	public class GestionnaireEcransFxml extends StackPane {

	    private final HashMap<String, Node> listeEcrans = new HashMap<>();
	    
	    public Partie partie;

	    public GestionnaireEcransFxml(Partie p) {
	        super();
	        partie = p;
	    }

	    /**
	     * ajoute l'écran fxml écran associé à la clé nomEcran à la liste listeEcran
	     * @param nomEcran clé associée à l'écran
	     * @param ecran ecran FXML
	     */
	    public void ajouteEcran(String nomEcran, Node ecran) {
	        listeEcrans.put(nomEcran, ecran);
	    }
	    
	    /**
	     * retire l'écran associé à la clé nomEcran à  la liste listeEcran
	     * @param nomEcran clé associée à l'écran
	     * @return vrai si l'écran est retiré de la liste listeEcrans, faux sinon
	     */
	    public boolean retireEcran(String nomEcran) {
	        if (listeEcrans.remove(nomEcran) == null) {
	            return false;
	        } else {
	            return true;
	        }
	    }
	    
	    /**
	     * getter
	     * @param nomEcran nomEcran clé associée à l'écran
	     * @return ecran FXML associée s'il existe
	     */
	    public Node donneEcran(String nomEcran) {
	        return listeEcrans.get(nomEcran);
	    }

	    /**
	     * créé le noeud FXML associé au chemin et clé donnée et le charge dans la liste des écrans connus
	     * @param nomEcran clé associée à l'écran
	     * @param FichierFXML localistaion du fichier FXML de l'écran associé
	     * @return vrai si l'opération est réussi, faux sinon
	     */
	    public boolean chargeEcran(String nomEcran, String FichierFXML) {
	        try {
	            FXMLLoader monChargeurFXML = new FXMLLoader(getClass().getResource(FichierFXML));
	            Parent ecranACharger = (Parent) monChargeurFXML.load();            
	            EcranCourant monControleurDecran = ((EcranCourant) monChargeurFXML.getController());
	            monControleurDecran.fixeEcranParent(this);    
	            if(nomEcran.equals(model.Proprietes.ECRAN_JEU)){
	            	monControleurDecran.miseAjour();
	            }
	            ajouteEcran(nomEcran, ecranACharger);
	            return true;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            return false;
	        }
	    }
	    

	    /**
	     * Gère une transition d'affichage entre l'écran donné en paramètre et l'écran courant.
	     * Il vérifie que cet écran à préalablement été ajouté à la liste d'écran connu et chargé.
	     * Effface (et retireAffiche l'écran associée à la clé nomEcran associée après une
	     * s'il a préalablement bien été ajouté à la listeEcrans.
	     * Ajoute une transition pour 
	     * @param nomEcran clé associée à l'écran
	     * @return
	     */
	    
	    public boolean changeEcranCourant(final String nomEcran) {
	        if (listeEcrans.get(nomEcran) != null) {  
	        	
	            final DoubleProperty opacity = opacityProperty();

	            if (!getChildren().isEmpty()) {    //il y a bien un autre ecran en memoire = l'écran courant
	                Timeline fade = new Timeline(
	                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
	                        new KeyFrame(new Duration(500), (ActionEvent t) -> {
	                            getChildren().remove(0);                    //retire de root l'écran courant
	                            getChildren().add(0, listeEcrans.get(nomEcran));     //ajoute à root l'écran en paramètres
	                            Timeline fadeIn = new Timeline(
	                                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
	                                    new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));
	                            fadeIn.play();
	                        }, new KeyValue(opacity, 0.0)));
	                fade.play();
	            } else {//cas du premier écran
	                setOpacity(0.0);
	                getChildren().add(listeEcrans.get(nomEcran));  
	                Timeline fadeIn = new Timeline(
	                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
	                        new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));
	                fadeIn.play();
	            }
	            return true;
	        } else {
	            System.out.println("Problème de chargement du nouvel écran FXML.");
	            return false;
	        }
	    }
	    
	    public void changePartie(Partie p) {
	    	this.partie =p;
	    }
	    

	}