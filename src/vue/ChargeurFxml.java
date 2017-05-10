package vue;

import java.net.URL;
import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

	/**
	 * Controle le passage entre scenes FXML (comme dans le tutoriel de Angela Caicedo).
	 * @author Riou Sebastien, Thisse Noémi.
	 */
	public class ChargeurFxml extends StackPane {

	    private final HashMap<String, Node> listeEcrans = new HashMap<>();

	    public ChargeurFxml() {
	        super();
	    }

	    public void ajouteEcran(String nomEcran, Node ecran) {
	        listeEcrans.put(nomEcran, ecran);
	    }

	    public Node donneEcran(String nomEcran) {
	        return listeEcrans.get(nomEcran);
	    }

	    /**
	     * charge l'ecran du fichier FichierFXML, et permet de revenir a l'ecran parent associe 
	     * loadScreen(String id, String resource) This method loads the fxml file specified by resource, 
	     * and it gets the top Node for the screen. We can also get the controller associated to this screen, 
	     * which allows us to set the parent for the screen, as all the controllers shared the common type ControlledScreen. 
	     * Finally the screen is added to the screens hash map. As you can see from the code, the loaded fxml file, 
	     * doesn't get added to the scene graph, so the loaded screen doesn't get displayed or loaded to the screen.
	     * @param nomEcran
	     * @param FichierFXML
	     * @return
	     */
	    public boolean chargeEcran(String nomEcran, String FichierFXML) {
	        try {
	        	// Localisation du fichier FXML url  = FichierFXML .
	        	// Création du loader.
	            FXMLLoader monChargeur = new FXMLLoader(getClass().getResource(FichierFXML));
	            
	            Parent EcranCourant = (Parent) monChargeur.load();
	            EnfantFxml monControleurDecran = ((EnfantFxml) monChargeur.getController());
	            monControleurDecran.fixeEcranParent(this);
	            System.out.println("plop");
	            ajouteEcran(nomEcran, EcranCourant);
	            return true;
	        } catch (Exception e) {
	            System.out.println("ca bug :"+e.getMessage());
	            return false;
	        }
	    }
	    
	    public FXMLLoader chargeEcranTest(String nomEcran, String FichierFXML) {
	        try {
	        	// Localisation du fichier FXML url  = FichierFXML .
	        	// Création du loader.
	            FXMLLoader monChargeur = new FXMLLoader(getClass().getResource(FichierFXML));
	            
	            /*Parent EcranCourant = (Parent) monChargeur.load();
	            EnfantFxml monControleurDecran = ((EnfantFxml) monChargeur.getController());
	            monControleurDecran.fixeEcranParent(this);;
	            ajouteEcran(nomEcran, EcranCourant);
	            return true;*/
	            return monChargeur;
	        } catch (Exception e) {
	            System.out.println("ca bug :"+e.getMessage());
	            return null;
	        }
	    }
	    public boolean fixeEcran(final String nomEcran) {
	        if (listeEcrans.get(nomEcran) != null) {   //ecran en mémoire ok

	            System.out.println("1");
	            final DoubleProperty opacity = opacityProperty();

	            if (!getChildren().isEmpty()) {    //il y a bien un autre ecran en memoire

		            System.out.println("2");
	                Timeline fade = new Timeline(
	                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
	                        new KeyFrame(new Duration(300), (ActionEvent t) -> {
	                            getChildren().remove(0);                    //remove the displayed screen
	                            getChildren().add(0, listeEcrans.get(nomEcran));     //add the screen
	                            Timeline fadeIn = new Timeline(
	                                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
	                                    new KeyFrame(new Duration(300), new KeyValue(opacity, 1.0)));
	                            fadeIn.play();
	                        }, new KeyValue(opacity, 0.0)));
	                fade.play();

		            System.out.println("3");

	            } else {
	                setOpacity(0.0);
	                getChildren().add(listeEcrans.get(nomEcran));       //no one else been displayed, then just show
	                Timeline fadeIn = new Timeline(
	                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
	                        new KeyFrame(new Duration(400), new KeyValue(opacity, 1.0)));
	                fadeIn.play();
	            }
	            return true;
	        } else {
	            System.out.println("screen hasn't been loaded!!! \n");
	            return false;
	        }
	    }

	    public boolean deChargeEcran(String nomEcran) {
	        if (listeEcrans.remove(nomEcran) == null) {
	            return false;
	        } else {
	            return true;
	        }
	    }

	}
