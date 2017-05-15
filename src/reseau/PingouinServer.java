package reseau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.*;


public class PingouinServer {


    private static final int PORT = 9001;
    private static int MAXPLAYERS;
    private static int NBPINGOUINS;
    private static ObjectOutputStream writers[];
    private static int nbClients = 0;
    private static Partie p;
    private static boolean phasePlacement = true;
    private static boolean phaseJeu = true;
    private static boolean phaseNoms = true;
    private static boolean partieCree = false;
	private static int num_pingouin = 0;
	private static PrintStream so = System.out;
    
    public static void main(String[] args) throws Exception {
        System.out.println("Pingouins's server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        p = new Partie(MAXPLAYERS);
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
	            } else {
	            	out.writeObject("NOSLOT");
	            	so.println("Tentative de connexion depuis "+socket.getRemoteSocketAddress().toString()+" impossible.\nCAUSE : Nombre maximum de joueurs atteint.");
	            }
	            
	            
	            //attente des autres joueurs
	            while(true) {
	            	synchronized (p) {
		            	if(!phaseNoms && partieCree) break;
					}

	            }
	            
	            out.writeObject(p.clone());
	            out.writeObject("MESSAGE "+p.joueurs[p.joueurActif].nom+" est entrain de placer.");
	                
                //phase placement
                while (true) {
                	synchronized (p) {
	                	if(phasePlacement) {
		                	if(p.joueurActif == num || p.joueurs[p.joueurActif].getClass() == IA.class) {
		                		Coordonnees c = null;
								if (p.joueurs[p.joueurActif].getClass() == IA.class) { // Tour de l'IA
									c = p.joueurs[p.joueurActif].placement(p);
								} else { 												// Tour de l'Humain
			                		out.writeObject("PLACEMENT n° "+(num_pingouin+1));
				                    Object obj = in.readObject();
				                    if (obj instanceof Coordonnees) {
				                    	c = (Coordonnees)obj;
				                    } else {
				                    	so.println("Phase placement, impossible de lire les coordonnées envoyées par "+p.joueurs[num].nom);
				                    }
								}
		                    	Tuile t = p.b.getTuile(c);
	                    		Joueur j = p.joueurs[p.joueurActif];
								if(t!=null && t.nbPoissons != 0 && !t.aUnPingouin) {//Placement autorisé ici
									t.mettrePingouin();
									j.myPingouins[num_pingouin] = new Pingouin(c);
									p.verifierPingouinActif();
									
									//Maj prochain joueur
									p.joueurActif = (p.joueurActif+1)%MAXPLAYERS;
									
									//envoie du placement à tout les joueurs
									for(int i = 0; i<nbClients; i++) {
										writers[i].writeObject("MESSAGE "+j.nom+" a posé un pingouin en "+c);
										writers[i].writeObject(p.clone());
									}
									
									//Maj numero du pingouin
									if(p.joueurActif == 0) {
										num_pingouin++;
									}
									
									
									//Test fin de phase placement
									if(num_pingouin == NBPINGOUINS && p.joueurActif == 0) {
										phasePlacement = false;
										for(int i = 0; i<nbClients; i++) {
											writers[i].writeObject("MESSAGE Début de la phase de jeu !\n\n"+p.joueurs[p.joueurActif].nom+" commence à jouer.");
										}
									} else {
										//Affiche qui est le prochain joueur sur tout les clients
										for(int i = 0; i<nbClients; i++) {
											writers[i].writeObject("MESSAGE "+p.joueurs[p.joueurActif].nom+" est entrain de placer.");
										}
									}
								} else {
									out.writeObject("MESSAGE Impossible de poser un pingouin à l'emplacement "+c);
								}
		                	}
							
	                	} else {
	                		break;
	                	}
                	}
                }
                
                //phase jeu
                while (true) {
                	synchronized (p) {
	                	if(phaseJeu) {
		                	if(p.joueurActif == num || p.joueurs[p.joueurActif].getClass()==IA.class) {
		                		if(p.peutJouer()) {
			                		Joueur j = p.joueurs[p.joueurActif];
			                		CoupleCoordonnees cc = null;
			                		if(p.joueurs[p.joueurActif].getClass()==IA.class && p.peutJouer()) {
			                			CoupleGenerique<Coordonnees, Coordonnees> cg = p.joueurs[p.joueurActif].jouer(p);
			                			cc = new CoupleCoordonnees(cg.e1,cg.e2);
			                		} else {//le joueur actif peut jouer
				                		writers[p.joueurActif].writeObject("DEPLACEMENT");
					                    Object obj = in.readObject();
					                    if (obj instanceof CoupleCoordonnees) {
					                    	cc = (CoupleCoordonnees)obj;
					                    } else {
					                    	so.println("Lecture du couple envoyée par "+name+" impossible.");
					                    }   	
					                 }
			                    	p.deplacement(cc);
			                    	p.verifierPingouinActif();
									//Maj prochain joueur
									p.joueurActif = (p.joueurActif+1)%MAXPLAYERS;
									
									//envoie du deplacement à tout les joueurs
									for(int i = 0; i<nbClients; i++) {
										writers[i].writeObject("MESSAGE "+j.nom+" a deplacé un pingouin de "+cc.c1+" vers "+cc.c2);
										writers[i].writeObject(p.clone());
									}
									
									//envoie du nom du prochain joueur devant jouer
									for(int i = 0; i<nbClients; i++) {
										writers[i].writeObject("MESSAGE "+p.joueurs[p.joueurActif].nom+" est entrain de jouer.");
									}
									
									//Test fin de partie
									if(p.estPartieFini()) {
										phaseJeu = false;
									}
			                    } else {//le joueur actif ne peut plus jouer
									for(int i = 0; i<nbClients; i++) {
										writers[i].writeObject("MESSAGE "+p.joueurs[p.joueurActif].nom+" ne peut plus jouer.");
									}
									//Maj prochain joueur
									p.joueurActif = (p.joueurActif+1)%MAXPLAYERS;
									for(int i = 0; i<nbClients; i++) {
										writers[i].writeObject("MESSAGE "+p.joueurs[p.joueurActif].nom+" est entrain de jouer.");
									}
			                    }
		                	}
		                	
	                	} else {
	                		break;
	                	}
                	}
                }
                
                out.writeObject("MESSAGE Fin de la partie ! "+p.getGagnant().get(0).nom+" a gagné !\n"+p.scoresToString());
                
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
        
        public synchronized void getNom(ObjectInputStream in, ObjectOutputStream out) {
        	try {
            	if(nbClients==0) {
            		initGame(in, out);
            	}
	            while (true) {
	                out.writeObject("SUBMITNAME");
	                Object obj = in.readObject();
	                if (obj instanceof String) {
	                	name = (String)obj;
	                    if (name == null) {
	                        return;
	                    }
                    	num = nbClients;
                        p.joueurs[num].nom = name;
                        for(int i = 0; i<NBPINGOUINS;i++) {
                        	p.joueurs[num].myPingouins[i] = new Pingouin();
                        }
        	            nbClients++;
        	            if(nbClients == MAXPLAYERS || p.joueurs[nbClients].getClass()==IA.class) {
        	            	phaseNoms = false;
        	            }
                        break;
	                    
	                } else {
	                	out.writeObject("NAMEREJECTED");
	                }
	            }
	            out.writeObject("NAMEACCEPTED");
	            //Joueurs deja presents dans la partie
	            if(num>0) {
		            String others = "Vous rejoignez ";
					for(int i = 0; i<MAXPLAYERS; i++) {
						if(p.joueurs[i]!=null && i!=num)
							others+= p.joueurs[i].nom + ", ";
					}
					out.writeObject("MESSAGE "+others.substring(0,others.length()-2)+" dans une partie.");
	            }
				//Si ils manquent d'autres joueurs
	            if(num+1==MAXPLAYERS) {
	            	out.writeObject("MESSAGE En attente d'autres joueurs pour lancer la partie.");
	            }
				//envoie du placement à tout les joueurs
				for(int i = 0; i<nbClients; i++) {
					if(writers[i]!=null)
					writers[i].writeObject("MESSAGE "+p.joueurs[num].nom + " a rejoint la partie.");
				}
	            writers[num] = out;
	            
	            so.println(p.joueurs[num].nom + " vient de se connecter.");
        	} catch (Exception e) {
        		e.printStackTrace(System.err);
        	}
        }
        
        public synchronized void afficherJoueursConnectes() {
        	for (int i = 0; i<MAXPLAYERS;i++) {
        		if(p.joueurs[i]!=null) {
        			so.println(p.joueurs[i].nom+ " est dans la partie.");
        		}
        	}
        }
        
        public synchronized void initGame(ObjectInputStream in, ObjectOutputStream out) {
        	try {
	            out.writeObject("INITGAME");
                Object obj = in.readObject();
                if(obj instanceof Partie) {
	            	p = (Partie)obj;
	            	NBPINGOUINS = p.joueurs[0].nbPingouin;
	            	MAXPLAYERS = p.nbJoueurs;
	            	writers = new ObjectOutputStream[MAXPLAYERS];
	            	partieCree = true;
	            }
        	} catch (Exception e){
        		e.printStackTrace(so);
        	}
        }
        
    }
}