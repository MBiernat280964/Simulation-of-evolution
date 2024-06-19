package simulation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Object <code>GUI</code> handles the user interface at the start of the simulation
 */

public class GUI extends JFrame {
    String mapName = "riverside";
    HashMap<Character, Integer> mapOfCreatures = new HashMap<>();

    /**
     * default constructor
     */
    public GUI() {
        int heightt = Toolkit.getDefaultToolkit().getScreenSize().height;
        int widthh = Toolkit.getDefaultToolkit().getScreenSize().width;

        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setResizable(false);

        this.setSize(widthh / 3, heightt / 2);
        int frameWidth = this.getSize().width;
        int frameHeight = this.getSize().height;
        this.setLocation((widthh - frameWidth) / 2, (heightt - frameHeight) / 2);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage("kukaracza.jpg"));
        initComponents();
    }


    /**
     * Contains all the components placed in GUI frame
     * Sets GroupLayout for frame
     * Called out in default constructor
     * if user inputs path to csv file, then simulation will begin with data from file, if not, then it will be input manually by user from JSpinners
     */
    public void initComponents() {
        biomes.add(island);
        biomes.add(riverside);
        biomes.add(lake);
        island.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapName = "island";
            }
        });
        riverside.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapName = "riverside";
            }
        });
        lake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapName = "lake";
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String pathway = textToInput.getText();
                if (pathway.startsWith("C") || pathway.startsWith("D"))
                {
                    ArrayList<Integer> arr = CSVReader.readFromCsv(pathway);
                    mapOfCreatures.put((Character) 'w', arr.get(0));
                    mapOfCreatures.put((Character)'h', arr.get(1));
                    mapOfCreatures.put((Character) 'c', arr.get(2));
                    mapOfCreatures.put((Character) 'd', arr.get(3));
                    mapOfCreatures.put((Character) 'b', arr.get(4));
                    mapOfCreatures.put((Character) 'f', arr.get(5));
                    Integer j = arr.get(6);
                    if (j == 1)
                        mapName = "riverside";
                    else if (j == 2)
                        mapName = "island";
                    else if (j == 3)
                        mapName = "lake";
                }
                else
                {
                    System.out.println("it goes");
                    mapOfCreatures.put((Character) 'w', (Integer) l1.getValue());
                    mapOfCreatures.put((Character)'h', (Integer) l2.getValue());
                    mapOfCreatures.put((Character) 'c', (Integer) l3.getValue());
                    mapOfCreatures.put((Character) 'd', (Integer) l4.getValue());
                    mapOfCreatures.put((Character) 'b', (Integer) l5.getValue());
                    mapOfCreatures.put((Character) 'f', (Integer) l6.getValue());
               }

                dispose();
                System.out.println(mapOfCreatures.get(0));
                System.out.println(mapOfCreatures.get(1));
                System.out.println(mapOfCreatures.get(2));
                System.out.println(mapOfCreatures.get(3));
                System.out.println(mapOfCreatures.get(4));
                System.out.println(mapOfCreatures.get(5));
                Simulation.main(mapOfCreatures, mapName);
            }
        });
        this.getContentPane().setBackground(Color.PINK);

        textToInput.setColumns(20);
        textToInput.setRows(5);
        jScrollPane1.setViewportView(textToInput);

        GroupLayout layout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);

        //this fragment sets the group layout for first frame
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(49, 49, 49)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(label5)
                                                                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(label4))
                                                                .addGap(31, 31, 31)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(label3)
                                                                .addGap(48, 48, 48)
                                                                .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label1)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(134, 134, 134)
                                                .addComponent(label2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(161, 161, 161)
                                                .addComponent(button)))
                                .addContainerGap(16, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(island, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lake, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(riverside, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(44, 44, 44))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(label9)
                                                .addGap(67, 67, 67))))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3)
                                        .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4)
                                        .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label5)
                                        .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label6)
                                        .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label9)
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label2)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(island)
                                        .addComponent(lake)
                                        .addComponent(riverside))
                                .addGap(18, 18, 18)
                                .addComponent(button)
                                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }

    //components
    JRadioButton island = new JRadioButton("Island");
    JRadioButton riverside = new JRadioButton("Riverside");
    JRadioButton lake = new JRadioButton("Lake");
    ButtonGroup biomes = new ButtonGroup();
    JButton button = new JButton("START");
    JLabel label1 = new JLabel("Please choose number of creatures in the beginning of simulation:");
    JLabel label2 = new JLabel("Please choose a biome:");
    JLabel label3 = new JLabel("Wolves");
    JLabel label4 = new JLabel("Humans");
    JLabel label5 = new JLabel("Cockroach");
    JLabel label6 = new JLabel("Dinos");
    JLabel label7 = new JLabel("Bird");
    JLabel label8 = new JLabel("Fish");
    JSpinner l1 = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
    JSpinner l2 = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
    JSpinner l3 = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
    JSpinner l4 = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
    JSpinner l5 = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
    JSpinner l6 = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
    JTextArea textToInput = new JTextArea();
    JLabel label9 = new JLabel("or read from file (input path on your computer)");
    JScrollPane jScrollPane1 = new javax.swing.JScrollPane();


    public static void main(String[] args) {
        new GUI();
    }

}
