import model.*;
import vue.*;
import controleur.*;
import java.awt.Dimension;

public class Launcher {
	
	public static void main(String[] args) {	

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteurBanquise = 6;
		int largeurBanquise  = 8;
		
		Partie p = new Partie();
		Interface.creer(args, p,hauteurBanquise,largeurBanquise);
	}	

}
