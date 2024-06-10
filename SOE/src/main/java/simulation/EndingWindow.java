package simulation;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EndingWindow extends JFrame {
    String path;

    EndingWindow()
    {
        this.setTitle("Simulation Of Evolution");
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("kukaracza.jpg"));
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(path)))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setResizable(false);
        this.pack();
    }
}
