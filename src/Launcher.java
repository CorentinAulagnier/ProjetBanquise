import model.Partie;
import vue.Interface;

public class Launcher {
	
	public static void main(String[] args) {	
		//int hauteurBanquise = 6;
		//int largeurBanquise  = 8;
		//TODO gerer la mise en mémoire des informations de Proprietes depuis un fichier texte
		Partie p = new Partie();
		Interface.creer(args, p, 600, 800);
	}	

}
