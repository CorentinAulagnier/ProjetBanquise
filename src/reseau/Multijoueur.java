package reseau;


public class Multijoueur {

	// Lancement : main("CreeServer") OU main("ConnexionServer")
	public static void main(String[] args) {
		String[] s = new String[1];

		try {
			if (args[0].equals("CreeServer")) {
				new ExecServer().start();
				s[0] = "local";

			} else {
				s[0] = "distant";

			}

			PingouinClient.main(s);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
    private static class ExecServer extends Thread {

        public ExecServer() {}

        public void run() {
        	try {
				PingouinServer.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

}
