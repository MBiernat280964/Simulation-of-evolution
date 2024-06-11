package simulation;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Object <code>EndingWindow</code> handles the user interface at the end of the simulation
 */
public class EndingWindow extends JFrame {
    String winner;
    HashMap<Character, Integer> mapOfCreatures = new HashMap<>();

    /**
     * constructor taking two parameters
     * @param mapOfCreatures map with key value pairs, key is a char - first lowercase letter of creature name, value is number of creatures
     * @param winner name of the creature with the highest population after inputted number of simulation cycles
     */
    EndingWindow(HashMap<Character, Integer> mapOfCreatures, String winner)
    {
        this.winner = winner;
        this.mapOfCreatures = mapOfCreatures;
        int heightt = Toolkit.getDefaultToolkit().getScreenSize().height;
        int widthh = Toolkit.getDefaultToolkit().getScreenSize().width;

        this.setSize(widthh / 4, heightt / 4);
        int frameWidth = this.getSize().width;
        int frameHeight = this.getSize().height;
        this.setLocation((widthh - frameWidth) / 2, (heightt - frameHeight) / 2);

        this.setTitle("Simulation Of Evolution");
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage("kukaracza.jpg"));

        this.setResizable(false);
        initComponents();
        this.getContentPane().setBackground(Color.PINK);
    }

    /**
     * Contains and initializes all the components placed in GUI frame
     * Sets GroupLayout for frame
     * Called out in the constructor
     */
    public void initComponents()
    {
        GroupLayout layout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);


        JLabel label = new JLabel("Congratulations!!! " + winner + " won the simulation");
        JLabel labelw = new JLabel("wolf: " + mapOfCreatures.get('w'));
        JLabel labelh = new JLabel("human: " + mapOfCreatures.get('h'));
        JLabel labelc = new JLabel("cockroach: " + mapOfCreatures.get('c'));
        JLabel labeld = new JLabel("dinosaur: " + mapOfCreatures.get('d'));
        JLabel labelb = new JLabel("bird: " + mapOfCreatures.get('b'));
        JLabel labelf = new JLabel("fish: " + mapOfCreatures.get('f'));

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(label)
                        .addComponent(labelw)
                        .addComponent(labelh)
                        .addComponent(labeld)
                        .addComponent(labelb)
                        .addComponent(labelf)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(labelw)
                        .addComponent(labelh)
                        .addComponent(labeld)
                        .addComponent(labelb)
                        .addComponent(labelf)
        );
        layout.setAutoCreateContainerGaps(true);

        layout.setAutoCreateGaps(true);

    }
}
