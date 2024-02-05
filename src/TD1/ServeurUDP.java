package TD1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Serveur basique UDP
 */
public class ServeurUDP
{

	public static void main(String[] args) throws Exception
	{
		ServeurUDP serveurUDP = new ServeurUDP();
		serveurUDP.execute();
		
	}
		
		

	private void execute() throws IOException
	{
		//
		System.out.println("Demarrage du serveur ...");
		
		// Le serveur se declare aupres de la couche transport
		// sur le port 3000
		DatagramSocket socket = new DatagramSocket(null);
		socket.bind(new InetSocketAddress(3000));

		// Attente du premier message
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		socket.receive(dpR);
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());
		System.out.println("Message recu = "+message);
		
		System.out.println("IP = "+dpR.getAddress().getHostAddress());
		System.out.println("Port = "+dpR.getPort());
		
		
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du serveur .");
	}
		
}