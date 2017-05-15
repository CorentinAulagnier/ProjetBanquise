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

public class PingouinClient {

    private static final int PORT = 9001;

    public static void main(String[] args) throws Exception {
    	 // Make connection and initialize streams
    	Socket socket = null;
    	Pattern pattern;
    	Matcher matcher;
    	final String REGEX_IP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    	pattern = Pattern.compile(REGEX_IP);

    	while (true) {
	    	try {
		        String serverAddress = getServerAddress();        		                                         
		        Matcher matchAddr = pattern.matcher(serverAddress);		        		                                         
		        if (matchAddr.matches() || serverAddress.equals("")) {
		        	socket = new Socket(serverAddress, PORT);
		        	break;
		        } else {
					System.out.println("Connexion impossible. Réessayer avec une IP valide.");
		        }
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
	                	out.writeObject(creerPartie());
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
    
    /**
	 *  - demande le nombre de joueurs et creee la partie adequate
	 *  - genere aleatoirement la banquise
	 *  - demande le nom/type de joueur
	 *  
	 * @param br
	 *            Buffer de la console.
	 * @return la partie cree
	 */
	
	public static Partie creerPartie() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Partie p = null;
		try {
			System.out.println("Creation de la partie !");
			/* ----- Choix du nombre de joueurs et IA----- */
			//Humains
			boolean nb_humain_ok = false;
			int nb_humains = 1;
			while (!nb_humain_ok) {
				try {
					System.out.println("A combien de joueurs (humains) voulez-vous jouer ? (1 à 4)");
					nb_humains = Integer.valueOf(br.readLine());
					if(nb_humains<= 4 && nb_humains>=1) {
						nb_humain_ok = true;
						System.out.println("Création d'une partie à "+String.valueOf(nb_humains)+" joueurs.");
					} else {
						System.out.println("Nombre de joueurs incorrect. Réessayez.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Nombre de joueurs incorrect. Réessayez.");
				}
			}
			//IAs
			int nb_ias = 0;
			if(nb_humains<4) {
				boolean nb_ia_ok = false;
				while (!nb_ia_ok) {
					try {
						System.out.println("Combien d'IA(s) voulez-vous ajouter ? (0 à "+(4-nb_humains)+")");
						nb_ias = Integer.valueOf(br.readLine());
						if(nb_ias<= (4-nb_humains) && nb_ias>=0) {
							nb_ia_ok = true;
							if (nb_ias>0)
								System.out.println("Ajout de "+String.valueOf(nb_ias)+" IAs à la partie.");
						} else {
							System.out.println("Nombre de joueurs incorrect. Réessayez.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Nombre de joueurs incorrect. Réessayez.");
					}
				}
			}
			
			/* ----- Creation partie + init banquise ----- */
			int nb_joueurs_tot = nb_humains+nb_ias;
			p = new Partie(nb_joueurs_tot);
			p.b = new Banquise();
			
			/* ----- Ajout des joueurs et IA à la partie ------*/
			for(int i = 0; i<nb_joueurs_tot;i++) {
				if(i<nb_humains) {
					p.joueurs[i]= new Humain("",6-nb_joueurs_tot);
				} else {
					int niv_IA;
					while(true) {
						try {
							System.out.println("Quel niveau voulez vous pour l'IA ? (1 à 3)");
							niv_IA = Integer.valueOf(br.readLine());
							if(niv_IA<=3 && niv_IA>=1) {
								break;
							} else {
								System.out.println("Entrée incorrecte. Réessayez.");
							}
						} catch (Exception e) {
							e.printStackTrace(System.out);
						}
					}
					p.joueurs[i] = new IA("IA"+(i-nb_humains+1), 6-nb_joueurs_tot, niv_IA);
                    for(int j = 0; j<6-nb_joueurs_tot;j++) {
                    	p.joueurs[i].myPingouins[j] = new Pingouin();
                    }
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
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