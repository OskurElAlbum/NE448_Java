package TD3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTCPenvoie {

    public static void main(String[] args) throws Exception
    {
        TD3.ClientTCPenvoie clientTCPenvoie = new TD3.ClientTCPenvoie();
        clientTCPenvoie.execute();
    }

    private static void execute() throws IOException
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
        socket.close();
        System.out.println("Arret du client .");

    }
}
