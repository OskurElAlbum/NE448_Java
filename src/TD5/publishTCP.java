package TD5;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;
import java.lang.Object;
import java.util.Random;

public class publishTCP extends Thread  {

    public publishTCP()
    {
        super();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Demarrage du serveur ...");
        	//File copier =new File(Copiteur.txt);
            int OctetLu =0;
            int OctetFinal=0;
            int tailleOctet=10;
            int increOctet;
            String OctetFinalChar="-";
            char OctetFinalAddChar;
            
            FileInputStream Entrer = new FileInputStream("C:\\NAS\\MAXENCE\\Informatique\\code\\Java\\NE448\\src\\TD5\\Copiteur.txt");
            FileOutputStream Sortie = new FileOutputStream("C:\\NAS\\MAXENCE\\Informatique\\code\\Java\\NE448\\src\\TD5\\Copitr.txt");
            
            System.out.println("Il y a "+Entrer.available()+" Octet Disponible");
            
            
            while(OctetLu!=-1) {
            	increOctet=0;
        		byte[] bufEntrer = new byte[tailleOctet];
            	
            	while((increOctet!=tailleOctet) && (OctetLu!=-1)) {
            		OctetLu=Entrer.read(bufEntrer,0,tailleOctet);
            		if(OctetLu!=-1) {
            		Sortie.write(bufEntrer,0,OctetLu);
            		}
            		OctetFinalAddChar=(char)OctetLu;
            		System.out.println(OctetLu);
            	}

            	Sortie.flush();	
            }            
            Sortie.close(); 
    }
}
