package reseau;

import model.Moteur;

public class Multijoueur {

	public static PingouinClient pc;
	public static Moteur m;
	public static String[] args;
	//public PingouinServer ps;
	
	public Multijoueur(Moteur mo, String[] a) {
		m = mo;
		args = a;
		pc = null;
		//ps = null;
	}
	
	// Lancement : main("local", IP, NOM) OU main("Distant", IP, NOM)
	
	public static void run() {
		
		try {
			if (args[0].equals("local")) {
				(new ExecServer(m)).start();

			} else {
				
			}
			
			//pc = new PingouinClient(m);
			
			ExecClient ec = new ExecClient(args, pc);
			
			ec.start();


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
        			//PingouinServer.main(mot);
        		}

			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
    
    private static class ExecClient extends Thread {

    	String[] args;
    	PingouinClient pc;

        public ExecClient(String[] s, PingouinClient p) {
        	args = s;
        	pc = p;

        }

        public void run() {
        	try {
        		while (true) {
    				pc.main(args);
        		}

			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

}
