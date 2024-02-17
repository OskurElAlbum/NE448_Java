package TD5.TD5Serveur;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;
import java.lang.Object;
import java.util.Random;

public class PublishServeurTCP {
	public static void main(String[] args) throws Exception
	{
		PublishServeurTCP serveurTCP = new PublishServeurTCP();
		serveurTCP.execute();
	}



	private void execute() throws IOException
	{
		//
		System.out.println("Demarrage du serveur ...");

		// Le serveur se declare aupres de la couche transport
		// sur le port 5099
		ServerSocket socketEcoute = new ServerSocket();
		socketEcoute.bind(new InetSocketAddress(6000));

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

		//File copier =new File(Copiteur.txt);
		int OctetLu =0;
		int OctetFinal=0;
		int tailleOctet=10;
		int increOctet;
		String OctetFinalChar="-";
		char OctetFinalAddChar;
		
		if (lenBufR!=-1)
		{
			String message = new String(bufR, 0 , lenBufR);
			System.out.println("Message recu = "+message);
			if(message.compareTo("Copiteur.txt")!=0){
				FileInputStream Entrer = new FileInputStream("C:\\NAS\\MAXENCE\\Informatique\\code\\Java\\NE448\\src\\TD5\\TD5Serveur\\Copiteur.txt");
            	// FileOutputStream Sortie = new FileOutputStream("C:\\NAS\\MAXENCE\\Informatique\\code\\Java\\NE448\\src\\TD5\\TD5Client\\Copiteur.txt");
            	System.out.println("Il y a "+Entrer.available()+" Octet Disponible");
				while(OctetLu!=-1) {
					increOctet=0;
					byte[] bufEntrer = new byte[tailleOctet];
					
					while((increOctet!=tailleOctet) && (OctetLu!=-1)) {
						OctetLu=Entrer.read(bufEntrer,0,tailleOctet);
						// if(OctetLu!=-1) {
						// Sortie.write(bufEntrer,0,OctetLu);
						// }
						OctetFinalAddChar=(char)OctetLu;
						System.out.println(OctetLu);
					}

					// Sortie.flush();	
				}            
				// Sortie.close();
				Entrer.close();
				}
		}

		// // Emission d'un message en retour
		// byte[] bufE = new String("Oui").getBytes();
		// OutputStream os = socketConnexion.getOutputStream();
		// os.write(bufE);
		// System.out.println("Message envoye = OUI");

		// Fermeture de la socket de connexion
		socketConnexion.close();


		// Arret du serveur
		socketEcoute.close();
		System.out.println("Arret du serveur .");
	}

}