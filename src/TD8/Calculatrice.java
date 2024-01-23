package TD8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;

public class Calculatrice extends Thread
{
    private Somme somme;
    public Calculatrice(Somme somme)
    {
        this.somme = somme;
    }
    @Override
    public void run()
    {
        int res = 0;
        for (int i = 0; i < 1000; i++)
        {
            res= somme.somme(res, i);
        }
        System.out.println("1+2+3+4+...+999 = "+res);
    }

    public static void main(String[] args) throws InterruptedException
    {
        Somme somme = new Somme();
        Calculatrice c1 = new Calculatrice(somme);
        Calculatrice c2 = new Calculatrice(somme);
        c1.start();
        c2.start();
    }
    static class Somme
    {
        int c;
        synchronized public int somme(int a, int b)
        {
            
            c=a+b;
            System.out.println("c="+c);
            return c;
        }
    }
}