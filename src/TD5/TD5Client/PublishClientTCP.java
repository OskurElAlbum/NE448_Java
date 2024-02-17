package TD5.TD5Client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class PublishClientTCP{
    public static void main(String[] args) throws Exception
	{
		PublishClientTCP clientTCP = new PublishClientTCP();
		clientTCP.execute();
	}
	/**
	 * Le client cree une socket, envoie un message au serveur
	 * et attend la reponse
	 *
	 */
	public void execute() throws IOException
	{
		//
		System.out.println("Demarrage du client ...");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer le nom du fichier à récupérer :");
        String NomFichier = scanner.nextLine();
        System.out.println("Le fichier à récupérer est : " + NomFichier);
        scanner.close();

		//Creation de la socket
		Socket socket = new Socket();

		// Connexion au serveur
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 6000);
		socket.connect(adrDest);

		// Envoi de la requete
		byte[] bufE = NomFichier.getBytes();
		OutputStream os = socket.getOutputStream();
		os.write(bufE);
		System.out.println("Message envoye");

		// Attente de la reponse
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		int lenBufR = is.read(bufR);

		//FileInputStream Entrer = new FileInputStream("C:\\NAS\\MAXENCE\\Informatique\\code\\Java\\NE448\\src\\TD5\\TD5Serveur\\Copiteur.txt");
        FileOutputStream Sortie = new FileOutputStream("C:\\NAS\\MAXENCE\\Informatique\\code\\Java\\NE448\\src\\TD5\\TD5Client\\Copiteur.txt");
        // System.out.println("Il y a "+Entrer.available()+" Octet Disponible");
		int OctetLu =0;
        int tailleOctet=10;
        int increOctet;
		while(OctetLu!=-1) {
			increOctet=0;
			byte[] bufEntrer = new byte[tailleOctet];
			while((increOctet!=tailleOctet) && (OctetLu!=-1)) {
				//OctetLu=Entrer.read(bufEntrer,0,tailleOctet);
				if(OctetLu!=-1) {
				Sortie.write(bufEntrer,0,OctetLu);
				}
				System.out.println(OctetLu);
			}

			Sortie.flush();	
		}            
		Sortie.close();

		if (lenBufR!=-1)
		{
			String reponse = new String(bufR, 0 , lenBufR );
			System.out.println("Reponse recue = "+reponse);
		}

		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client .");
	}
}