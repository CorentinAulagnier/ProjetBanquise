package model;

/**
 * Class de test pour jouer dans la console,
 * Pour plus de renseignements, demander a Mathieu ou Corentin
 */

public class LauncherConsole extends MoteurConsole{
	
	/**
	 * Main
	 * @param args
	 *            Parametre du main.
	 */
	
	public static void main(String[] args) {
		Partie p = creerPartie();
		setJoueursHumains(p);
		p.setHistorique();
		phasePlacement(p);
		phaseMangerLesPoissons(p);
		finPartie(p);
	}


}