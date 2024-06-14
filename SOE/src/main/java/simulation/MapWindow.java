package simulation;

import javax.swing.*;
import java.awt.*;

public class MapWindow {
    public static void main(String[] args) {
        Map map = new Map("island");

        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(map.sizeOfMap,map.sizeOfMap));
        frame.setLocationRelativeTo(null);

        map.showMap();

        for (int i = 0; i < map.sizeOfMap ; i++) {
            for ( int j = 0; j < map.sizeOfMap ; j++) {
                JPanel lol = new JPanel();
                if (map.map[0][i][j] == 'L') {
                    lol.setBackground(Color.green);
                }
                else {
                    lol.setBackground(Color.blue);
                }
                frame.add(lol);
            }
        }

        frame.setVisible(true);


    }
}
