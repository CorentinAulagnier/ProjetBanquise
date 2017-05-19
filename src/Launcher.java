import model.Moteur;
import model.Partie;
import vue.Interface;

public class Launcher {
	
	public static void main(String[] args) {	
		//int hauteurBanquise = 6;
		//int largeurBanquise  = 8;
		//TODO gerer la mise en mémoire des informations de Proprietes depuis un fichier texte
		Moteur m = new Moteur(new Partie());
		Interface.creer(args, m, 600, 800);
	}	

}
