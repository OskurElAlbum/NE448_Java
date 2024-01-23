package TD2_V2;

import java.awt.Color;

import javax.swing.JFrame;

public class exo1
{
    public static void main(String[] args) throws Exception
    {
        exo1 c = new exo1();
        c.execute();
    }

    private void execute() throws Exception
    {
        JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);

        //
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(2000);


        //
        frame.getContentPane().setBackground(Color.RED);
        frame.setVisible(true);
        Thread.sleep(2000);


        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(2000);

        frame.dispose();

    }
}