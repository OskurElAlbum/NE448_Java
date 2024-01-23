import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class ClientOrdonnanceurUDP {

    public static void main(String[] args) throws Exception {
        ClientOrdonnanceurUDP clientOrdo = new ClientOrdonnanceurUDP();
        clientOrdo.execute();
    }
    private void execute() throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro de port du client (!) pour le dernier client :");
        String PortClient = scanner.nextLine();
        System.out.println("Vous avez entré : " + PortClient);
        scanner.close();

        JFrame frame = new JFrame(PortClient);
        frame.setSize(300, 300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);

        //Creation de la socket
        DatagramSocket socket = new DatagramSocket();

        // Creation et envoi du message à l'adresse 127.0.0.1 et le port 3000
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 3000);
        byte[] bufE = new String(PortClient).getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);

        System.out.println("Message envoyé.");

        if(PortClient.endsWith("!")) {
            PortClient = PortClient.substring(0, PortClient.length() - 1);
        }
        int iPort = Integer.parseInt(PortClient);
        System.out.println("Le port est " + iPort);


        DatagramSocket socketR = new DatagramSocket(null);
        socketR.bind(new InetSocketAddress(iPort));
        byte[] bufferS = new byte[2048];
        DatagramPacket dpS = new DatagramPacket(bufferS, bufferS.length);

        while (true) {
            socketR.receive(dpS);
            String message = new String(bufferS, dpS.getOffset(), dpS.getLength());
            System.out.println("Message recu = "+message);

            if (message.equals("ROUGE")){
                frame.getContentPane().setBackground(Color.RED);
            }
            else{
                frame.getContentPane().setBackground(Color.GREEN);
            }
        }
    }
}
