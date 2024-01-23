package TD8;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;
import java.util.Random;
import tdm.tdm07.exo3.checker.*;

public class philosopheBaguette extends Thread{
    int number;
    static int  NPhilo=6;
    Arbitre Le_serveur_en_faites = new Arbitre(NPhilo);
    Random time_task= new Random ();

    public philosopheBaguette (int number)
    {
        super();
        this.number = number;
    }
    
    public void run()
    {
        try 
        {
            int time;
            int state =1; // si o : il mange,  si 1 : il parle.
            while(true)
            {
                time = time_task.nextInt(11);
                if (state == 0)
                {
                    while (Le_serveur_en_faites.autorisation(this.number)) {
                        System.out.println("moi philosophe " +this.number+" je veux ces putains de baguette");
                        Thread.sleep(1000); 
                    }
                    CodeChecker.startEating(this.number);
                    System.out.println("philosophe " +this.number+" : mange pendant : "+time);
                    Thread.sleep(time*1000); 
                    Le_serveur_en_faites.liberation(this.number);
                    state = 1;
                    CodeChecker.stopEating(this.number);
                }
                if (state ==1)
                {
                    System.out.println("philosophe " +this.number+" : Alors moi ma vie c'est "+time+" secondes");
                    Thread.sleep(time*1000); 
                    state = 0;
                }
            }
        }
        catch(Exception e){}
    }
    public static void main(String[] args) throws Exception
    {
        int ibcl;
        for(ibcl=0;ibcl<NPhilo;ibcl++){
            new philosopheBaguette(ibcl).start();
        }
        
    }
}
