package reseau;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

//java.net.ConnectException	152.77.82.225
import model.*;

public class PingouinClient {

    private static final int PORT = 9001;

    public static void main(String[] args) throws Exception {
    	 // Make connection and initialize streams
    	Socket socket = null;
    	while (socket==null) {
	    	try {
		        String serverAddress = getServerAddress();
		        socket = new Socket(serverAddress, PORT);
	    	} catch (ConnectException | UnknownHostException e) {
				System.out.println("Connexion impossible. Réessayer avec une IP valide.");
			}
    	}
    	
        ObjectInputStream in =  new ObjectInputStream(socket.getInputStream()) ;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        Partie p = null;

        // Process all messages from server, according to the protocol.
        while (true) {
            Object obj;
			try {
				obj = in.readObject();
	            if(obj instanceof Partie) {
	            	p = (Partie)obj;
	            	LauncherConsole.afficherPlateau(p);
	            } else if (obj instanceof String) {
	            	String line = (String)obj;
	                if (line.startsWith("SUBMITNAME")) {
	                    out.writeObject(getUsername());
	                } else if (line.startsWith("PLACEMENT")) {
	                	out.writeObject(getPlacement(line.substring(10)));
	                } else if (line.startsWith("INITGAME")) {
//TODO
	                
	                
	                } else if (line.startsWith("DEPLACEMENT")) {
	                	out.writeObject(getDeplacement(p));
	                } else if (line.startsWith("NAMEACCEPTED")) {
	                	System.out.println("Bienvenue sur PINGOUINS !\n");
	                } else if (line.startsWith("NAMEREJECTED")) {
	                	System.out.println("Le nom envoyé au serveur est invalide !\n");
	                	out.writeObject(getUsername());
	                } else if (line.startsWith("NOSLOT")) {
	                	System.out.println("Le serveur de PINGOUINS est plein. Réessayez plus tard.");
	                	socket.close();
	                } else if (line.startsWith("MESSAGE")) {
	                	System.out.println(line.substring(8) + "\n");
	                }         	
	            } else {
	            	System.out.println("Impossible de récupérer le type de l'objet.");
	            }
	            
			}  catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EOFException e) {
				System.out.println("Vous avez été déconnecté du serveur. Relancez l'application pour réessayer.");
				socket.close();
				System.exit(0);
			}

        }
    }

	private static String getServerAddress() {
    	String s = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
	    	System.out.println("Entrez l'IP du serveur (vide pour localhost) :");
	        s = br.readLine();
    	} catch (Exception e){
    		e.printStackTrace(System.err);
    	}
        return s;
    }

    private static String getUsername() {
    	String s = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		System.out.println("Entrez votre nom :");
    		s = br.readLine();
    	} catch (Exception e){
    		e.printStackTrace(System.err);
    	}
        return s;
    }

	private static Coordonnees getPlacement(String num) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = -1;
		int y = -1;
		try {
			System.out.println("Entrez le placement de votre pingouin "+num);
			System.out.print("x :");
			x = Integer.valueOf(br.readLine());
			System.out.print("y :");
			y = Integer.valueOf(br.readLine());
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return new Coordonnees(x, y);
	}
	
    private static CoupleCoordonnees getDeplacement(Partie p) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean joue = false;
		while(true) {
			Pingouin pingouin = LauncherConsole.choixPingouin(br,p);
			Coordonnees dep = LauncherConsole.choixDeplacement(br, p, pingouin);
			if(dep!=null) {
				return new CoupleCoordonnees(pingouin.position,dep);
			}
		}
	}
}