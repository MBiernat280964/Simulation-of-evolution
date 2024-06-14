package simulation;

import javax.swing.*;
import java.awt.*;

public class MapWindow {
    Map map = new Map("island");
    JPanel [][] jPanelArray = new JPanel[map.sizeOfMap][map.sizeOfMap];

    public MapWindow() {
        prepareGUI();
    }

    public static void main(String[] args) {
        MapWindow mapop = new MapWindow();


        /*EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new mapop.display();
            }
        }*/
    }

    private void prepareGUI() {
        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(map.sizeOfMap,map.sizeOfMap));
        frame.setLocationRelativeTo(null);
        for (int i = 0; i < map.sizeOfMap ; i++) {
            for ( int j = 0; j < map.sizeOfMap ; j++) {
                this.jPanelArray[i][j] = new JPanel();
                if (map.map[0][i][j] == 'L') {
                    this.jPanelArray[i][j].setBackground(Color.green);
                }
                else {
                    this.jPanelArray[i][j].setBackground(Color.blue);
                }
                frame.add(this.jPanelArray[i][j]);
            }
        }
        frame.setVisible(true);
    }

    private void display() {
        for (int i = 0; i < map.sizeOfMap ; i++) {
            for ( int j = 0; j < map.sizeOfMap ; j++) {
                if (map.map[1][i][j] != '\0') {
                    this.jPanelArray[i][j].setBackground(Color.white);
                } else if (map.map[0][i][j] == 'L') {
                    this.jPanelArray[i][j].setBackground(Color.green);
                } else {
                    this.jPanelArray[i][j].setBackground(Color.blue);
                }
            }
        }

    }
}

