import model.*;
import vue.*;
import controleur.*;
import java.awt.Dimension;

public class Launcher {
	
	public static void main(String[] args) {	

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int hauteurBanquise = (int)dimension.getHeight();
		int largeurBanquise  = (int)dimension.getWidth();
		
		Partie p = new Partie();
		Interface.creer(args, p,hauteurBanquise,largeurBanquise);
	}	

}
