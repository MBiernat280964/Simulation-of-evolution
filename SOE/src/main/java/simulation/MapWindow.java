package simulation;


import javax.swing.*;
import java.awt.*;

public class MapWindow {
    private char[][][] map;
    JPanel [][] jPanelArray = new JPanel[Map.sizeOfMap][Map.sizeOfMap];
    JFrame frame;
    public MapWindow(char [][][] map, JFrame frame) {
        this.map = map;
        this.frame = frame;
        prepareGUI();
    }

    private void prepareGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(Map.sizeOfMap,Map.sizeOfMap));
        frame.setLocationRelativeTo(null);
        for (int i = 0; i < Map.sizeOfMap ; i++) {
            for ( int j = 0; j < Map.sizeOfMap ; j++) {
                this.jPanelArray[i][j] = new JPanel();
                if (this.map[1][i][j] != '\0') {
                    this.jPanelArray[i][j].setBackground(Color.white);
                }else if (this.map[0][i][j] == 'L') {
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

    public void updateDisplay() {
        for (int i = 0; i < Map.sizeOfMap ; i++) {
            for ( int j = 0; j < Map.sizeOfMap ; j++) {
                if (this.map[1][i][j] != '\0') {
                    this.jPanelArray[i][j].setBackground(Color.white);
                } else if (this.map[0][i][j] == 'L') {
                    this.jPanelArray[i][j].setBackground(Color.green);
                } else {
                    this.jPanelArray[i][j].setBackground(Color.blue);
                }
            }
        }

    }
}