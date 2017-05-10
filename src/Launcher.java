import model.*;
import vue.*;
import controleur.*;

public class Launcher {
	
	public static void main(String[] args) {	

		int hauteurBanquise = 6;
		int largeurBanquise = 8;
		
		Partie p = new Partie();
		Interface.creer(args, p,hauteurBanquise,largeurBanquise);
	}	

}
