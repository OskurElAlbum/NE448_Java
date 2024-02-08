package TD8;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

import tdm.tdm07.exo3.checker.CodeChecker;

public class TCPserveurThread extends Thread {
    private static final int START_PORT = 21000;
    private static final int END_PORT = 23000;
    private int portVariable;
    public static int compteurTCP;

    public TCPserveurThread (int port)
    {
        super();
        this.portVariable = port;
    }
    public void run()
    {
        try 
        {
            int compteur;
            while (true) {
                // Le serveur se declare aupres de la couche transport
                ServerSocket socketEcoute = new ServerSocket();
                socketEcoute.bind(new InetSocketAddress(this.portVariable));

                // Attente de la connexion d'un client
                System.out.println("Attente de la connexion du client ...");
                Socket socketConnexion = socketEcoute.accept();

                // Affichage du port et de l'ip du client
                System.out.println("Un client est connecté");
                System.out.println("IP:"+socketConnexion.getInetAddress());
                System.out.println("Port:"+socketConnexion.getPort());

                // Un client s'est connecte, le serveur lit le message envoye par le client (sans garantie de lire tout le message)
                byte[] bufR = new byte[2048];
                InputStream is = socketConnexion.getInputStream();
                int lenBufR = is.read(bufR);
                String Retour="NUMERO=";

                System.out.println(lenBufR);
                String message = new String(bufR, 0 , lenBufR);
                System.out.println("Message recu = "+message);
                
                if(lenBufR!=-1){
                    if(message.equals("NUMERO?")!=true){
                        int lenBufRAdd = is.read(bufR);
                        while(message.equals("NUMERO?")!=true){
                            String messageAdd = new String(bufR, 0 , lenBufRAdd);
                            System.out.println(lenBufRAdd);
                            message=message+messageAdd;
                            System.out.println(message);
                        } 
                    }
                    compteur= Incrementeur(portVariable);
                    Retour="NUMERO="+compteur;
                }
                else{
                    Retour="VOUS AVEZ FAIT UNE ERREUR";
                }

                // Emission d'un message en retour
                byte[] bufE = new String(Retour).getBytes();
                OutputStream os = socketConnexion.getOutputStream();
                os.write(bufE);
                System.out.println("Message envoye = "+Retour);

                // Fermeture de la socket de connexion
                socketConnexion.close();


                // Arret du serveur
                socketEcoute.close();
                System.out.println("Arret du serveur .");
            }
        }
        catch(Exception e){
        e.printStackTrace();
        } 
    }

    public synchronized int Incrementeur(int numPort) {
        compteurTCP++;
        return compteurTCP;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Demarrage du serveur ...");
            for (int port = START_PORT; port <= END_PORT; port++) {
                TCPserveurThread TCPlistener = new TCPserveurThread(port);
                TCPlistener.start();
            }
    }
}
