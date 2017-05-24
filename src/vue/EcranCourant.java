package vue;

import javafx.animation.Timeline;

/**
 * Permet de donner un nouvel écran à afficher parmi ceux créés (comme présenté dans le tutoriel de Angela Caicedo).
 * @author Riou Sebastien, Thisse Noémi, Angela Caicedo.
 */
public interface EcranCourant {
	/**
	 * Permet de lier un écran parent
	 * @param noeudFXML écran parent
	 */
    public void fixeEcranParent(GestionnaireEcransFxml noeudFXML);
    
	/**
	 * Permet de mettre à jour, après son chargement, l'écran FXML donné
	 * @param GestionnaireEcransFxml écran parent
	 */
    public void miseAjour();
    
}