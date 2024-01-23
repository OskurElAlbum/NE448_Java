package TD7;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;

public class Sequencement_Thread extends Thread{
    long res = 1;
    long end;
    long name;
    public Sequencement_Thread(long name, long end)
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
        Sequencement_Thread one = new Sequencement_Thread (1,2);
        Sequencement_Thread two = new Sequencement_Thread (2,4);
        Sequencement_Thread three = new Sequencement_Thread (3,7);
        Sequencement_Thread four = new Sequencement_Thread (4,5);
        Sequencement_Thread five = new Sequencement_Thread (5,2);
        Sequencement_Thread six = new Sequencement_Thread (6,2);

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
        five.join();
        four.join();

        //while ((three.res != 0)&(three.res != 0));
        six.start();
        six.join();
        System.out.println("hey bro it's finish");
    }
}
