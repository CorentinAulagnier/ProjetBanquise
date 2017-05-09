package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;


public class PingouinServer {


    private static final int PORT = 9001;
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    private static int nbClients = 0;
    
    public static void main(String[] args) throws Exception {
        System.out.println("Pingouins server is running.");
        ServerSocket listener = new ServerSocket(PORT);
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
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;            
        }

        public void run() {

            try {
                out = new PrintWriter(socket.getOutputStream(), true);
            
            	if(nbClients<4) {
	                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
	
	
	                while (true) {
	                    out.println("SUBMITNAME");
	                    name = in.readLine();
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
	                }
	
	                out.println("NAMEACCEPTED");
	                writers.add(out);
	
	                while (true) {
	                    String input = in.readLine();
	                    if (input == null) {
	                        return;
	                    }
	                    for (PrintWriter writer : writers) {
	                        writer.println("MESSAGE " + name + ": " + input);
	                    }
	                }
            	} else {
            		out.println("NOSLOT");
            	}
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (name != null) {
                    names.remove(name);
                    System.out.println("Joueur " +name+" s'est déconnecté.");
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}