package TD1;
import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ChenillardUDP1 {

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Chenillard1");
        frame.setSize(300, 300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(1000); // Attendre une seconde

        int receivePort = 12349; // Port sur lequel ce programme reçoit
        int sendPort = 12346;    // Port sur lequel le programme suivant reçoit
        String nextAddress = "localhost"; // Adresse du programme suivant

        try (DatagramSocket socket = new DatagramSocket(receivePort)) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            int flag=0;

            while (true) {
                if (flag==1){
                    socket.receive(packet);

                }
                flag=1;
                frame.getContentPane().setBackground(Color.RED);
                Thread.sleep(1000);
                // Attendre une seconde

                // Envoyer un message au programme suivant
                String msg = "AH";
                byte[] sendData = msg.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, new InetSocketAddress(nextAddress, sendPort));
                socket.send(sendPacket);

                frame.getContentPane().setBackground(Color.GREEN);
                Thread.sleep(1000);

                // Vous pouvez ajouter une pause ici si nécessaire
                }
            }
        }
}