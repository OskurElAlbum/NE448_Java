package TD2_V2;
import java.awt.Color;
import javax.swing.JFrame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class caseUdp1
{
    public static void main(String[] args) throws Exception
    {
        caseUdp1 c = new caseUdp1();
        c.execute();
    }

    private void execute() throws Exception
    {
        JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);
        int myTurn = 1;
        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 3000
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(3000));

        // Attente du premier message
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);


        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 3001);
        byte[] bufE = new String("hello").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);

        System.out.println("Message envoyé.");

        while (true)
        {


            if (myTurn == 1)
            {
                frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                Thread.sleep(1000);
                myTurn = 0;
                socket.send(dpE);
            }
            else
            {
                frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
            }

            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("Message recu = "+message);

            System.out.println("IP = "+dpR.getAddress().getHostAddress());
            System.out.println("Port = "+dpR.getPort());
        }
    }
}
