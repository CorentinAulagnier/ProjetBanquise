package reseau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Coordonnees;
import model.CoupleGenerique;
import model.Partie;

public class PingouinClient {

    private static final int PORT = 9001;
    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Pingouins");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);


    public PingouinClient() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        // Add Listeners
        textField.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key in the textfield by sending
             * the contents of the text field to the server.    Then clear
             * the text area in preparation for the next message.
             */
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

    /**
     * Prompt for and return the address of the server.
     */
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private String getUsername() {
        return JOptionPane.showInputDialog(
            frame,
            "Identifiant:",
            "Connexion",
            JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Connects to the server then enters the processing loop.
     * @throws ClassNotFoundException 
     */
    private void run() throws IOException, ClassNotFoundException {

        // Make connection and initialize streams
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, PORT);
        ObjectInputStream in =  new ObjectInputStream(socket.getInputStream()) ;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Process all messages from server, according to the protocol.
        while (true) {
            String line = (String)in.readObject();
            
            if (line.startsWith("SUBMITNAME")) {
				System.out.println("quel est votre nom ?");
				String nom = br.readLine();
            	out.writeObject(nom);
            } else if (line.startsWith("NAMEACCEPTED")) {

            } else if (line.startsWith("NEWPARTIE")) {
                Partie p = (Partie)in.readObject();
				System.out.println(p);

            } else if (line.startsWith("POSITIONPINGOUIN")) {
				System.out.println("Joueur, choisissez la position initiale de votre pingouin :");
				System.out.print("x: ");
				int x = Integer.valueOf(br.readLine());
				System.out.print("y: ");
				int y = Integer.valueOf(br.readLine());
            	Coordonnees c = new Coordonnees(x, y);
            	out.writeObject(c);
            } else if (line.startsWith("DEPLACEMENTPINGOUIN")) {
				System.out.println("Joueur, choisissez le pingouin e deplacer:");
				System.out.print("x: ");
				int x = Integer.valueOf(br.readLine());
				System.out.print("y: ");
				int y = Integer.valueOf(br.readLine());
            	Coordonnees c = new Coordonnees(x, y);
				System.out.println("Joueur, choisissez ou lacher le pingouin :");
				System.out.print("x: ");
				int x2 = Integer.valueOf(br.readLine());
				System.out.print("y: ");
				int y2 = Integer.valueOf(br.readLine());
            	Coordonnees c2 = new Coordonnees(x2, y2);
            	
            	CoupleGenerique<Coordonnees, Coordonnees> cc = new CoupleGenerique<Coordonnees, Coordonnees>(c, c2);
                out.writeObject(cc);
            } else if (line.startsWith("")) {

            } else if (line.startsWith("")) {

            }
        }
    }

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
    	PingouinClient client = new PingouinClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}