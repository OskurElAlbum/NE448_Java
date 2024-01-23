import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;


public class OrdonnenceurUDP {
    public static void main(String[] args) throws Exception
    {
        OrdonnenceurUDP ordonnenceurUDP = new OrdonnenceurUDP();
        ordonnenceurUDP.execute();

    }

    private void execute() throws IOException {
        //
        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 3000
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(3000));

        // Attente du premier message
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        int nbclient = 0;

        ArrayList<String> ListeClient = new ArrayList<String>();
        String message;

        do {
            socket.receive(dpR);
            message = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("Message recu = " + message);
            ListeClient.add(message);
            nbclient++;
        } while (message.charAt(message.length() - 1) != '!');

        int iBcl;
        int PortActuelle;
        int PortSuivant;
        String dernierElement = ListeClient.get(ListeClient.size() - 1);
        String elementModifie = dernierElement.substring(0, dernierElement.length() - 1);
        ListeClient.set(ListeClient.size() - 1, elementModifie);
        System.out.println(ListeClient.get(ListeClient.size() - 1));

        while (true){
            for (iBcl=0;iBcl < ListeClient.size();iBcl++){
                if(iBcl==ListeClient.size()-1){
                    PortActuelle=Integer.parseInt(ListeClient.get(iBcl));
                    PortSuivant=Integer.parseInt(ListeClient.get(0));
                }
                else{
                    PortActuelle=Integer.parseInt(ListeClient.get(iBcl));
                    PortSuivant=Integer.parseInt(ListeClient.get(iBcl+1));
                }

                InetSocketAddress adrDestActuelle = new InetSocketAddress("127.0.0.1", PortActuelle);
                byte[] bufEActuelle = new String("VERT").getBytes();
                DatagramPacket dpEActuelle  = new DatagramPacket(bufEActuelle, bufEActuelle.length, adrDestActuelle);
                socket.send(dpEActuelle );

                InetSocketAddress adrDestSuivant = new InetSocketAddress("127.0.0.1", PortSuivant);
                byte[] bufESuivant = new String("ROUGE").getBytes();
                DatagramPacket dpESuivant = new DatagramPacket(bufESuivant, bufESuivant.length, adrDestSuivant);
                socket.send(dpESuivant);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Gestion optionnelle de l'interruption
                System.out.println("Thread interrompu");
                return; // Ou une autre logique pour gérer l'interruption
                }
            }
        }
    }
}
