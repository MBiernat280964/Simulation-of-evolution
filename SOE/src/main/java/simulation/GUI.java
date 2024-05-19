package simulation;
import javax.swing.*;
import java.awt.*;
/**
 * Object <code>GUI</code> handles the user interface
 */

public class GUI {
    public GUI()
    {
        JFrame frame = new JFrame();
        int wys = Toolkit.getDefaultToolkit().getScreenSize().height;
        int szer = Toolkit.getDefaultToolkit().getScreenSize().width;

        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        frame.setSize(szer/10, wys/2);
        int szerRamki = frame.getSize().width;
        int wysRamki = frame.getSize().height;
        frame.setLocation((szer-szerRamki)/2, (wys-wysRamki)/2);

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("kukaracza.jpg"));
    }

}
