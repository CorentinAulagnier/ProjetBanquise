package model;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Banquise
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Set<Tuile> terrain;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Banquise(){
		initBanquise();
	}

	/**
	 * Initialise la banquise avec les poissons
	 */
	
	public void initBanquise() {
		int unPoisson = 30;
		int deuxPoissons = 20;
		int troisPoissons = 10;
		int nbCasesRestante = 60;
		int alea = 0;
		int numligne;

		Random r = new Random();
		
		for (int i = 0; i<8; i++) {
			
			if (i%2 == 0) { //Ligne pair
				numligne = 7;
			} else {
				numligne = 8; //Ligne impair
			}
			
			for (int j = 0; j < numligne; j++) {

				alea = r.nextInt(nbCasesRestante);
				
				if (alea < unPoisson) {
					setTuile(new Coordonnees(i, j), new Tuile(1)) ;
					unPoisson --;
					
				} else if (alea < (unPoisson + deuxPoissons)) {
					setTuile(new Coordonnees(i, j), new Tuile(2)) ;
					deuxPoissons --;
					
				} else if (alea < (unPoisson + deuxPoissons + troisPoissons)) {
					setTuile(new Coordonnees(i, j), new Tuile(3)) ;
					troisPoissons --;
				} else {
					System.out.println("Erreur : Initialisation banquise");
				}
				nbCasesRestante --;
			}
		}
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Tuile getTuile(Coordonnees c) {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setTuile(Coordonnees c, Tuile t) {
		// TODO implement me
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Coordonnees getHD(Coordonnees c) {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Coordonnees getHG(Coordonnees c) {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Coordonnees getMD(Coordonnees c) {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Coordonnees getMG(Coordonnees c) {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Coordonnees getBD(Coordonnees c) {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Coordonnees getBG(Coordonnees c) {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void manger(Coordonnees c) {
		// TODO implement me
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void deplacement(Coordonnees dep, Coordonnees arr) {
		// TODO implement me
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean estBloque(Pingouin p) {
		// TODO implement me
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean estNoye(Pingouin p) {
		// TODO implement me
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Pingouin> pingouinsDeplacable() {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Coordonnees> deplacementPossible(Pingouin p) {
		// TODO implement me
		return null;
	}

}

