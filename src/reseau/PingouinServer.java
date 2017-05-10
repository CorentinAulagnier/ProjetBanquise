package reseau;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import model.Coordonnees;
import model.CoupleCoordonnees;
import model.CoupleGenerique;
import model.Humain;
import model.IA;
import model.Partie;
import model.Pingouin;
import model.Tuile;


public class PingouinServer {


    private static final int PORT = 9001;
    private static String[] names = new String[4];
    private static ObjectOutputStream[] writers = new ObjectOutputStream[4];
    private static int nbClients = 0;
    private static Partie p = null;

    
    public static void main(String[] args) throws Exception {
        System.out.println("Pingouins server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        //Initialisation de la partie
        p = new Partie(4);
        //Fin initialisation
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }


    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private int numClient;

        public Handler(Socket socket) {
            this.socket = socket;            
        }

        public void run() {

            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                
            	if(nbClients<4) {
            		numClient = nbClients;
            		nbClients ++;
            		
	       			in =  new ObjectInputStream(socket.getInputStream()) ;	
        			
      /*
	                while (true) {
	                    out.writeObject("SUBMITNAME");
	                    name = (String)in.readObject();
	                    if (name == null) {
	                        return;
	                    }
	                    synchronized (names) {
	                        if (!names.contains(name)) {
	                            names.add(name);
	                            System.out.println("Joueur "+name+" vient de se connecter.");
	                            break;
	                        }
	                    }
	                }*/
	       			Object newObj = null;
                    out.writeObject("SUBMITNAME");
                    newObj = in.readObject();
                    if (newObj instanceof String) {
                    	name = (String)newObj;
                    } else {
                    	System.out.println("Nom non reconnu");
                    }
                    p.joueurs[numClient] = new Humain();
                    p.joueurs[numClient].myPingouins[0] = new Pingouin();
                    p.joueurs[numClient].myPingouins[1] = new Pingouin();
                    p.joueurs[numClient].myPingouins[2] = new Pingouin();
                    p.joueurs[numClient].nom = name;
                    
                    names[numClient] = name;
                    
	                //out.writeObject("NAMEACCEPTED");
	                //writers[numClient] = out;
	                /*
	                 * 
	                 */
                    
                    out.writeObject("WAITMATE");
                	System.out.println("Attente joueurs");
                    while (nbClients < 4) {}
                	System.out.println("Lancement de la partie");
                	out.writeObject("STARTGAME");
                	
	                Partie p2 = null;
	                newObj = null;

	                while (!p.estPartieFini() && !p.placementPingouinsFini()){
System.out.println("Debut de la partie");

		                while (!p.estPartieFini() && p.joueurActif != numClient) {
		                	if (!p2.equals(p)) {
		                		out.writeObject(p);
		                		p2 = p.clone();
		                	}
		                }
		                if (!p.placementPingouinsFini()) {
		                	out.writeObject("POSITIONPINGOUIN");
		                	newObj = in.readObject();
		                    
		                	if (newObj instanceof Coordonnees) {
			                	Coordonnees c = (Coordonnees)newObj;
			                	Tuile t = p.b.getTuile(c);
			                	t.mettrePingouin();
								p.joueurs[numClient].myPingouins[p.numPingouinAPlacer(p.joueurs[numClient])] = new Pingouin(c);
		                	}
		                } else if (!p.estPartieFini()) {
		                	out.writeObject("DEPLACEMENTPINGOUIN");
		                	newObj = in.readObject();
		                    
		                	if (newObj instanceof CoupleCoordonnees) {
		                		CoupleCoordonnees cc =  (CoupleCoordonnees) newObj;
			                	p.deplacement(cc);
			                }
		                }
	                }
	                p.afficherScores();

            	} else {
            		out.writeObject("NOSLOT");
            	}
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
                if (name != null) {
                    names[numClient] = null;
                    System.out.println("Joueur " +name+" s'est déconnecté.");
                }
                if (out != null) {
                    writers[numClient] = null;
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}