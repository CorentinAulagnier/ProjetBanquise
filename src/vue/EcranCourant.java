package vue;

/**
 * Permet de donner un nouvel écran à affciher parmi ceux créer.
 * @author Riou Sebastien, Thisse Noémi.
 */
public interface EcranCourant {
	/**
	 * Permet de donner un ecran parent
	 * @param interfaceurFXML ecran parent
	 */
    public void fixeEcranParent(GestionnaireEcransFxml interfaceurFXML);
    
	/**
	 * Permet de mettre à jour l'ecran FXML donné après son chargement
	 * @param interfaceurFXML ecran parent
	 */
    public void miseAjour();
}