package reseau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.animation.Timeline;
import model.*;


public class PingouinServer extends MoteurConsole{


    private static final int PORT = 9001;
    private static int NBPINGOUINS;
    private static ObjectOutputStream writers[];
    private static int nbClients = 0;
    private static Moteur m;
    private static boolean phaseConnexion = true;
	private static PrintStream so = System.out;
    
    public static void main(Moteur m) throws Exception {
        System.out.println("Pingouins's server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
            System.out.println("Pingouins's server stopped.");
        }
    }


    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private int num;
        private ObjectInputStream in; 
        private ObjectOutputStream out; 

        public Handler(Socket socket) {
            this.socket = socket;            
        }

        public void run() {

            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in =  new ObjectInputStream(socket.getInputStream()) ;
	            if(phaseConnexion) {
	            	getNom(in,out);
	            	envoyerMoteurAuxClients(m);
	            } else {
	            	out.writeObject("NOSLOT");
	            	so.println("Tentative de connexion depuis "+socket.getRemoteSocketAddress().toString()+" impossible.\nCAUSE : Nombre maximum de joueurs atteint.");
	            }
	            
	            
	            /* ----- ATTENTE AUTRES JOUEURS ----- */
	            while(true) {
	            	synchronized (m) {
		            	if(!phaseConnexion) break;
					}

	            }
	            
	            /* ----- PHASE PLACEMENT & DEPLACEMENT ----- */
                while (true) {
                	synchronized (m) {
	                	if(!m.phaseVictoire) {
							if (m.partie.joueurs[m.partie.joueurActif] instanceof IA) { // Tour de l'IA
								m.faireJouerIAS(new Timeline());
							} else if (m.partie.joueurActif == num) { 							
			                    Object obj = in.readObject();
			                    if (obj instanceof Moteur) {
			                    	m = (Moteur)obj;
			                    } else {
			                    	so.println("Phase placement, impossible de lire les coordonnées envoyées par "+m.partie.joueurs[num].nom);
			                    }
							}
							envoyerMoteurAuxClients(m, num);	
	                	} else {
	                		break;
	                	}
                	}
                }

                
            } catch (Exception e) {
                e.printStackTrace(System.err);
            	so.println("ERR : La connexion avec "+name+" a été perdue.");
            } finally {
                if (name != null) {
                	so.println(name+" s'est déconnecté.");
                    nbClients--;
                }
                if (out != null) {
                    writers[num]=null;
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        
        /*
         * Recupere le nom auprès du client et ajoute ce client à la liste des clients connectés
         */
        
        public void getNom(ObjectInputStream in, ObjectOutputStream out) {
        	try {
        		synchronized (m) {
	                out.writeObject("SUBMITNAME "+String.valueOf(num));
	                Object obj = in.readObject();
	                if (obj instanceof String) {
	                	name = ((String)obj).substring(0, 13); //limite la taille du nmo à 12 caractères
	                } else {
	                	name = "inconnu";
	                }
                	num = nbClients;
                	m.partie.joueurs[num].nom = name;
                    for(int i = 0; i<NBPINGOUINS;i++) {
                    	m.partie.joueurs[num].myPingouins[i] = new Pingouin();
                    }
                    //Maj phaseConnexion
    	            nbClients++;
    	            if(nbClients == m.partie.nbJoueurs || m.partie.joueurs[nbClients].getClass()==IA.class) {
    	            	phaseConnexion = false;
    	            }

		            writers[num] = out;		            
		            so.println(m.partie.joueurs[num].nom + " vient de se connecter.");
        		}
        	} catch (Exception e) {
        		e.printStackTrace(System.err);
        	}
        }
        
        /*
         * Envois le moteur à tout les clients sauf au num
         */
        
        public void envoyerMoteurAuxClients(Moteur m, int num) {
        	try {
	        	for(int i = 0; i<nbClients; i++) {
	        		if(i!=num) writers[i].writeObject(m);
	        	}
        	}catch (Exception e) {
        		e.printStackTrace(System.err);
        	}
        }
        
        /*
         * Envois le moteur à tout les clients
         */
        
        public void envoyerMoteurAuxClients(Moteur m) {
        	envoyerMoteurAuxClients(m, -1);
        }
        
    }
}