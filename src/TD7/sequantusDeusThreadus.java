package TD7;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;

public class sequantusDeusThreadus extends Thread{
    long res = 1;
    long end;
    long name;
    public sequantusDeusThreadus(long name, long end)
    {
        this.name = name;
        this.end = end;
    }


    public void run()
    {
        for (long i = 0; i <= this.end; i++)
        {
            System.out.println("hey bro it's :" +this.name+"; I am on step "+i);
            //Thread.sleep(1000);
        }
        System.out.println("hey bro it's :" +this.name+"; I finish");
        res =0 ;
    }
    public static void main(String[] args) throws InterruptedException
    {
        sequantusDeusThreadus one = new sequantusDeusThreadus (1,2);
        sequantusDeusThreadus two = new sequantusDeusThreadus (2,4);
        sequantusDeusThreadus three = new sequantusDeusThreadus (3,7);
        sequantusDeusThreadus four = new sequantusDeusThreadus (4,5);
        sequantusDeusThreadus five = new sequantusDeusThreadus (5,2);
        sequantusDeusThreadus six = new sequantusDeusThreadus (6,2);
        sequantusDeusThreadus seven = new sequantusDeusThreadus (7,2);

        one.start();
        one.join();

        //while (one.res != 0 );
        two.start();
        three.start();
        four.start();
        two.join();
        three.join();
        

        //while ((two.res != 0)&(three.res != 0));

        five.start();
        
        four.join();

        //while ((three.res != 0)&(three.res != 0));
        six.start();

        five.join();
        six.join();
        seven.start();
        seven.join();
        System.out.println("hey bro it's finish");
    }
}
