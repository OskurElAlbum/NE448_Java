package TD7;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;

public class Pi_multi  extends Thread{
    long debut;
    long fin;
    double Thread_Pi;
    
    public Pi_multi(long Idebut,long Ifin){
        super();
        this.debut=Idebut;
        this.fin=Ifin;
    }

    public void run(){
        long ibcl;
        double pi=0;
        double frac;

         for(ibcl=this.debut;ibcl<=this.fin;ibcl++){
            frac=(Math.pow(-1,ibcl))/((2*ibcl)+1);
            pi=pi+frac;
            if (ibcl <25007){
                System.out.println("fraction ajouté vaut ="+frac);
                System.out.println("Iteration n°"+ibcl+", Pi vaut :"+pi*4);
            }
            //System.out.println("Iteration n°"+ibcl+", Pi vaut :"+pi);
            }
            Thread_Pi=pi;
        
    }

    public static void main(String[] args) throws InterruptedException{
        long Iteration=50000l;
        int ibclTH;
        int NbThread=4;
        // int IterationThread = Iteration/NbThread;
        // for (ibclTH=1;ibclTH<=NbThread;NbThread++){
        //     Pi_multi = new Pi_multi(ibclTH, NbThread){

        //     }
        // }
        Pi_multi Pi1 = new Pi_multi(0,25000l);
        Pi_multi Pi2 = new Pi_multi(25001l,50000l);
        // Pi_multi Pi3 = new Pi_multi(0,25000l);
        // Pi_multi Pi4 = new Pi_multi(25001l,50000l);

        Pi1.start();
        Pi2.start();
        Pi1.join();
        Pi2.join();

        double resultatPI = Pi1.Thread_Pi+Pi2.Thread_Pi;
        System.out.println("Pi1 vaut est="+Pi1.Thread_Pi+" et Pi2 vaut"+Pi2.Thread_Pi);
        System.out.println("Pi est="+resultatPI*4);
    }




}
