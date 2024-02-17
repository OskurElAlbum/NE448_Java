package TD4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class TCPClientEchange {

    private static final boolean TRUE = false;
	private static final boolean FALSE = false;

	public static void main(String[] args) throws Exception
    {
		TCPClientEchange clientTCP = new TCPClientEchange();
        clientTCP.execute();                
    }

    private void execute() throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 7500);
        socket.connect(adrDest);        
        
        // Attente de la reponse BELA
        byte[] bufR = new byte[2048];
        InputStream is = socket.getInputStream();
        int lenBufR = is.read(bufR);
        
        char[] charArray2 = new char[10];
        char[] charArray;
        ArrayList<Character> charList = new ArrayList<>();
        
        boolean variable = FALSE;

        while (lenBufR!=-1)
        { 
        	// On met dans une charList chaque élément de la réponse
        	String reponse = new String(bufR, 0 , lenBufR ); 
            System.out.println("Reponse recue = "+reponse);
            charArray = reponse.toCharArray();
            
            // On remplit tout dans une ArrayList
            for (int i=0;i< charArray.length;i++) {
            	charList.add(charArray[i]);
            	System.out.println("test1");
            }
            
            // On parcours la réponse caractère par caractère pour trouver un "?" 
            for (int j=0;j< charArray.length;j++) {
            	System.out.println("test2");
            	if (charArray[j] == '?'){
                	System.out.println("test3");
                  variable = TRUE;
            	
                  // Si on le trouve, on récupère tous les caractères avant pour en faire un string
                  for(int k=0;k< j;j++) {
                	  System.out.println("test4");
                	  charArray2[k]=charList.get(k);
                	  charList.remove(k);       
                      String newString = new String(charArray2);
                      String[] parties = newString.split("[+]");
                      int resultat= Integer.parseInt(parties[0]) +Integer.parseInt(parties[1]);
                      byte[] bufE = new String(resultat +";").getBytes();
                      System.out.println(resultat +";");
                      OutputStream os = socket.getOutputStream();
                      os.write(bufE);
                  }                  

                 
                }
                
            }
            
            // Cas où on ne reçoit pas le premier calcul dans son intégralité
            if(variable == FALSE) {
            byte[] bufE = new String("").getBytes();
            OutputStream os = socket.getOutputStream();
            os.write(bufE);
            reponse = new String(bufR, 0 , lenBufR ); 
            System.out.println("Reponse recue = "+reponse);
            charArray = reponse.toCharArray();
            }
            
            
            lenBufR = is.read(bufR);

        }
        
        
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
}