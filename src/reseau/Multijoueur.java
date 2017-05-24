package reseau;

import model.Moteur;

public class Multijoueur {

	// Lancement : main("local", IP, NOM) OU main("Distant", IP, NOM)
	
	public static void main(Moteur m, String[] args) {
		
		try {
			if (args[0].equals("local")) {
				new ExecServer(m).start();

			} else {
				
			}

			PingouinClient.main(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
    private static class ExecServer extends Thread {

    	Moteur mot;
    	
        public ExecServer(Moteur m) {
        	this.mot = m;
        }

        public void run() {
        	try {
        		while (true) {
    				PingouinServer.main(mot);
        		}

			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

}
