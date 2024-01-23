package TD8;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;
import java.util.Random;

public class lphilosophusLatinus extends Thread{

    int number;
    public lphilosophusLatinus (int number)
    {
        this.number = number;
    }
    Random time_task= new Random ();
    public void run()
    {
        try 
        {
            int time;
            int state =0; // si o : il mange,  si 1 : il parle.
            while(true)
            {
                time = time_task.nextInt(11);
                if (state ==0)
                {
                    System.out.println("philosophe " +this.number+" : mange pendant : "+time);
                    Thread.sleep(time*1000); 
                    state = 1;
                }
                if (state ==1)
                {
                    System.out.println("philosophe " +this.number+" : Alors moi ma vie c'est lala lali lalo");
                    Thread.sleep(time*1000); 
                    state = 0;
                }
            }
        }
        catch(Exception e){}
    }
    public static void main(String[] args) throws Exception
    {
        lphilosophusLatinus jubert = new lphilosophusLatinus (1);
        lphilosophusLatinus albert = new lphilosophusLatinus (2);
        lphilosophusLatinus aubert = new lphilosophusLatinus (3);
        lphilosophusLatinus robert = new lphilosophusLatinus (4);
        lphilosophusLatinus hubert = new lphilosophusLatinus (5);
        
        jubert.start();
        albert.start();
        aubert.start();
        robert.start();
        hubert.start();
    }
}
