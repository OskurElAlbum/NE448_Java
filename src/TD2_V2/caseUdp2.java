package TD2_V2;
import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class caseUdp2
{
    public static void main(String[] args) throws Exception
    {
        caseUdp2 c = new caseUdp2();
        c.execute();
    }

    private void execute() throws Exception
    {
        int received_message = 0;

        //creation de la fenêtre :
        JFrame frame = new JFrame("Chenillard2");
        frame.setSize(300,300);

        //creation du serveur
        //creation de la socket serveur
        DatagramSocket socketServeur = new DatagramSocket(null);
        // serveur sur le 3000
        socketServeur.bind(new InetSocketAddress(3001));
        //acceuil du buffer
        byte[] bufR = new byte[2048];

        //creation du client :
        //creation de la socket client :
        DatagramSocket socketClient = new DatagramSocket();
        // Creation et envoi du message à l'adresse 127.0.0.1 et le port 3001
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 3002);
        byte[] bufE = new String("hello").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        //fin de creation du message

        //int message = "toto";

        while (true)
        {
            if (received_message == 1)
            {
                frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                Thread.sleep(1000);
                received_message = 0;

                //envoi un message
                socketClient.send(dpE);
                System.out.println("Message envoyé.");
            }
            else
            {
                frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);

                DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
                socketServeur.receive(dpR);
                String message = new String(bufR, dpR.getOffset(), dpR.getLength());
                System.out.println("Message recu = "+message);
                if ( message.equals("hello"))
                {
                    received_message = 1;
                }
            }
        }
    }
}
