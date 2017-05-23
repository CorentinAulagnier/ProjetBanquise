package reseau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.*;


public class PingouinServer extends MoteurConsole{


    private static final int PORT = 9001;
    private static int NBPINGOUINS;
    private static ObjectOutputStream writers[];
    private static int nbClients = 0;
    private static Moteur m;
    private static boolean phaseNoms = true;
    private static boolean partieCree = false;
	private static int numPingouin = 0;
	private static PrintStream so = System.out;
    
    public static void main(String[] args) throws Exception {
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
	            if(phaseNoms) {
	            	getNom(in,out);
	            	envoyerMoteurAuxClients(m);
	            } else {
	            	out.writeObject("NOSLOT");
	            	so.println("Tentative de connexion depuis "+socket.getRemoteSocketAddress().toString()+" impossible.\nCAUSE : Nombre maximum de joueurs atteint.");
	            }
	            
	            
	            /* ----- ATTENTE AUTRES JOUEURS ----- */
	            while(true) {
	            	synchronized (m) {
		            	if(!phaseNoms && partieCree) break;
					}

	            }
	                
                while (true) {
                	synchronized (m) {
	                	if(!m.phaseVictoire) {
							if (m.partie.joueurs[m.partie.joueurActif] instanceof IA) { // Tour de l'IA
								if(m.phasePlacement) {
									Coordonnees c = getPlacementPingouin(m.partie.joueurActif, numPingouin, m.partie);
		                    		m.partie.setPlacementPingouin(c, m.partie.joueurActif, numPingouin);
		                    		m.partie.verifierPingouinActif();
									m.partie.majProchainJoueur();
									if(m.partie.joueurActif == 0) {
										numPingouin++;
									}
									if(numPingouin == NBPINGOUINS && m.partie.joueurActif == 0) {
										m.phasePlacement = false;
										m.phaseJeu = true;
									}
								} else if(m.phaseJeu) {
			                		CoupleGenerique<Coordonnees, Coordonnees>  cc = m.partie.joueurs[m.partie.joueurActif].jouer(m.partie);
			                		m.partie.deplacement(cc);
			                    	m.partie.verifierPingouinActif();
			                    	m.partie.majProchainJoueur();
									if(m.partie.estPartieFini()) {
										m.phaseJeu = false;
										m.phaseVictoire = true;
									}
								}
							} else if (m.partie.joueurActif == num) { 							
			                    Object obj = in.readObject();
			                    if (obj instanceof Moteur) {
			                    	m = (Moteur)obj;
			                    } else {
			                    	so.println("Phase placement, impossible de lire les coordonnées envoyées par "+m.partie.joueurs[num].nom);
			                    }
							}
							
							//maj partie chez tout les clients
							envoyerMoteurAuxClients(m);	
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
        
        public void getNom(ObjectInputStream in, ObjectOutputStream out) {
        	try {
        		synchronized (m) {
	            	if(nbClients==0) {
	            		initGame(in, out);
	            	}
	                out.writeObject("SUBMITNAME");
	                Object obj = in.readObject();
	                if (obj instanceof String) {
	                	name = (String)obj;
	                    if (name == null) {
	                        return;
	                    }
                    	num = nbClients;
                    	m.partie.joueurs[num].nom = name;
                        for(int i = 0; i<NBPINGOUINS;i++) {
                        	m.partie.joueurs[num].myPingouins[i] = new Pingouin();
                        }
        	            nbClients++;
        	            if(nbClients == m.partie.nbJoueurs || m.partie.joueurs[nbClients].getClass()==IA.class) {
        	            	phaseNoms = false;
        	            }
		            }

		            writers[num] = out;
		            
		            so.println(m.partie.joueurs[num].nom + " vient de se connecter.");
        		}
        	} catch (Exception e) {
        		e.printStackTrace(System.err);
        	}
        }
        
        public void initGame(ObjectInputStream in, ObjectOutputStream out) {
        	try {
	            out.writeObject("INITGAME");
                Object obj = in.readObject();
                if(obj instanceof Partie) {
                	m.partie = (Partie)obj;
	            	NBPINGOUINS = m.partie.joueurs[0].nbPingouin;
	            	writers = new ObjectOutputStream[m.partie.nbJoueurs];
	            	partieCree = true;
	            }
        	} catch (Exception e){
        		e.printStackTrace(so);
        	}
        }
        
        public void envoyerMoteurAuxClients(Moteur m) {
        	try {
	        	for(int i = 0; i<nbClients; i++) {
	        		writers[i].writeObject(m);
	        	}
        	}catch (Exception e) {
        		e.printStackTrace(System.err);
        	}
        }
        
    }
}