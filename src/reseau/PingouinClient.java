package reseau;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.*;

public class PingouinClient extends Thread{

    private static final int PORT = 9001;
    private static Moteur moteur;
    public static int numClient;
    public static ObjectOutputStream out;
    public String[] args;
    
    public PingouinClient(Moteur m, String[] a) {
    	this.args = a;
    	this.moteur = m;
    }
    
	public static void main(String[] args) {
	}
	
    public static void majMoteurSurServeur(Moteur m) {
    	try {
			out.writeObject(m.clone());
			moteur = m;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

   // public static void main(String[] args) throws Exception 
    public void run() {
    	 // Make connection and initialize streams
    	Socket socket = null;
    	Pattern pattern;
    	final String REGEX_IP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    	pattern = Pattern.compile(REGEX_IP);
    	Matcher matchAddr = null;
    	
    	if (args[0].equals("local")) {
        	try {
				socket = new Socket(args[1], PORT);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
    	} else {		//(args[0].equals("distant"))
        	while (true) {
    	    	try {
    		        String serverAddress = args[1];        		                                         
    		        matchAddr = pattern.matcher(serverAddress);		        		                                         
    		        if (matchAddr.matches() || serverAddress.equals("")) {
							socket = new Socket(serverAddress, PORT);
    		        	break;
    		        } else {
    					System.out.println("Connexion impossible. Réessayer avec une IP valide.");
    		        }
    	    	} catch (Exception e) {
    				System.out.println("Connexion impossible. Réessayer avec une IP valide.");
    			}
        	}
    	}

    	
        ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(socket.getInputStream());
	        out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        // Process all messages from server, according to the protocol.
        while (!moteur.phaseVictoire) {
            Object obj;
			try {
				
				obj = in.readObject();
	            if(obj instanceof Moteur) {
	            	moteur = (Moteur)obj;
System.out.println(moteur.partie.toString2());
	            	//refresh plateau
	            } else if (obj instanceof String) {
	            	String line = (String)obj;
	                if (line.startsWith("SUBMITNAME")) {
	                	numClient = Integer.valueOf(line.substring(11));
	                    out.writeObject(args[2]);
	                } else if (line.startsWith("NOSLOT")) {
	                	System.out.println("Le serveur de PINGOUINS est plein. Réessayez plus tard.");
	                	socket.close();
	                }        	
	            } else {
	            	System.out.println("Impossible de récupérer le type de l'objet.");
	            }
	            
			} catch (Exception e) {
				System.out.println("Vous avez été déconnecté du serveur. Relancez l'application pour réessayer.");
				System.exit(0);
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
        try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}