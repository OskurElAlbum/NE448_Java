package TD2_V2;

import java.awt.Color;

import javax.swing.JFrame;
public class caseUdp3
{
    public static void main(String[] args) throws Exception
    {
        caseUdp3 c = new caseUdp3();
        c.execute();
    }

    private void execute() throws Exception
    {
        JFrame frame = new JFrame("Chenillard1");
        frame.setSize(300, 300);

        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(2000);
    }
}