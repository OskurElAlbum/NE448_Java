package TD3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTCPenvoie {

    public static void main(String[] args) throws Exception
    {
        TD3.ClientTCP clientTCP = new TD3.ClientTCP();
        clientTCP.execute();
    }

    private void execute() throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5099);
        socket.connect(adrDest);

        // Envoi de la requete
        byte[] bufE = new String("question du client").getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye");

//        // Attente de la reponse
//        byte[] bufR = new byte[2048];
//        InputStream is = socket.getInputStream();
        socket.close();
//        byte[] bufR = new byte[2048];
//        int lenBufR = is.read(bufR);
//
//        if (lenBufR!=-1)
//        {
//            String reponse = new String(bufR, 0 , lenBufR );
//            System.out.println("Reponse recue = "+reponse);
//        }

//        // Fermeture de la socket
//        socket.close();
        System.out.println("Arret du client .");
    }
}
