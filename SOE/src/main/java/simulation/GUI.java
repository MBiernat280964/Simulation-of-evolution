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
    /**
     * Contains all the components placed in GUI frame
     * Sets GroupLayout for frame
     * Called out in default constructor
     */
    public void initComponents()
    {
        biomes.add(island);
        biomes.add(riverside);
        biomes.add(seaside);
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
        seaside.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapName = "seaside";
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mapOfCreatures.put("wolves", (Integer)l1.getValue());
                mapOfCreatures.put("humans", (Integer)l2.getValue());
                mapOfCreatures.put("cockroach", (Integer)l3.getValue());
                mapOfCreatures.put("dinos", (Integer)l4.getValue());
                mapOfCreatures.put("bird", (Integer)l5.getValue());
                mapOfCreatures.put("fish", (Integer)l6.getValue());

                System.out.println(mapName);
                dispose();
                Simulation.main(null);
            }
        });
        //this.getContentPane().setBackground(Color.BLUE);

        GroupLayout layout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);

        //this fragment sets the group layout for first frame
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(161, 161, 161))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(label1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(141, 141, 141)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(label2)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addComponent(l2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(l1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(label5, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                                                                .addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(label7, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(label8, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addGap(18, 18, 18)
                                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(l5, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(l4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(l6, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                        .addGap(18, 18, 18)
                                                                                        .addComponent(l3, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))))))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(island, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seaside, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(riverside, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3)
                                        .addComponent(l1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(l2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(l3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label5))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(l4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label6))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(l5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label7))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(l6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label8))
                                .addGap(53, 53, 53)
                                .addComponent(label2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(island)
                                        .addComponent(seaside)
                                        .addComponent(riverside))
                                .addGap(103, 103, 103)
                                .addComponent(button)
                                .addGap(19, 19, 19))
        );

        pack();
    }

    //components
    JRadioButton island = new JRadioButton("Island");
    JRadioButton riverside = new JRadioButton("Riverside");
    JRadioButton seaside = new JRadioButton("Seaside");
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
    JSpinner l1 = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
    JSpinner l2 = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
    JSpinner l3 = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
    JSpinner l4 = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
    JSpinner l5 = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));
    JSpinner l6 = new JSpinner(new SpinnerNumberModel(1, 1, 300, 1));


    public static void main(String[] args) {
        new GUI();
    }

}
