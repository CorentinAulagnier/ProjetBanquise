import model.Moteur;
import model.Partie;
import reseau.PingouinClient;
import reseau.PingouinServer;
import vue.Interface;

public class Launcher {
	
	public static void main(String[] args) {	
		//int hauteurBanquise = 6;
		//int largeurBanquise  = 8;
		//TODO gerer la mise en mémoire des informations de Proprietes depuis un fichier texte 
		//TODO boolean tutoriel un jour
		Moteur m = new Moteur(new Partie());
		Interface.creer(args, m, 600, 800);
	}	
	/*

	public static void main(String[] args) {
		Moteur m = new Moteur(new Partie());

		try {
			new ExecIHM(args, m).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
    private static class ExecIHM extends Thread {
    	
    	Moteur moteur;
    	String[] args;

        public ExecIHM(String[] s, Moteur m) {
        	this.moteur = m;
        	this.args = s;
        }

        public void run() {
        	try {
        		Interface.creer(args, moteur, 600, 800);

			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
*/
}
