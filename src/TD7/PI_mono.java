package TD7;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;

public class PI_mono extends Thread{
    long N;
    

    public PI_mono(long Niteration){
        super();
        this.N=Niteration;
    }

    public void run(){
        long ibcl;
        double pi=1;
        double frac;
         for(ibcl=1;ibcl<=N;ibcl++){
            frac=(Math.pow(-1,ibcl))/((2*ibcl)+1);
            
            pi=pi+frac;
            // if (ibcl <7){
            //     System.out.println("fraction ajouté vaut ="+frac);
            //     System.out.println("Iteration n°"+ibcl+", Pi vaut :"+pi*4);
            // }
            System.out.println("Iteration n°"+ibcl+", Pi vaut :"+pi*4);
        }
        
    }

    public static void main(String[] args) {
        long Iteration=500000000;
        PI_mono Pi = new PI_mono(Iteration);
        Pi.start();
    }
}
