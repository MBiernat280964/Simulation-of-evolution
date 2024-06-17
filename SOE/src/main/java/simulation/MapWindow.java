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
        prepareGUI(map);
    }
    public static void main(String[] args) {
        Map mapka = new Map("island");
        MapWindow window = new MapWindow(mapka.map,new JFrame("Mapeczka"));
    }

    private void prepareGUI(char [][][] map) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(Map.sizeOfMap,Map.sizeOfMap));
        frame.setLocationRelativeTo(null);
        for (int i = 0; i < Map.sizeOfMap ; i++) {
            for ( int j = 0; j < Map.sizeOfMap ; j++) {
                jPanelArray[i][j] = new JPanel();
                if (map[1][i][j] != '\0') {
                    jPanelArray[i][j].setBackground(Color.pink);
                }else if (map[0][i][j] == 'L') {
                    jPanelArray[i][j].setBackground(Color.green);
                }
                else {
                    jPanelArray[i][j].setBackground(Color.blue);
                }
                frame.add(jPanelArray[i][j]);
            }
        }
        frame.setVisible(true);
    }

    public void updateDisplay(char [][][] map) {
        frame.setVisible(false);
        for (int i = 0; i < Map.sizeOfMap ; i++) {
            for ( int j = 0; j < Map.sizeOfMap ; j++) {
                if (map[1][i][j] == '\0') {
                    jPanelArray[i][j].setBackground(Color.white);
                } else if (map[0][i][j] == 'L') {
                    jPanelArray[i][j].setBackground(Color.green);
                } else {
                    jPanelArray[i][j].setBackground(Color.blue);
                }
            }
        }
        frame.setVisible(true);


    }
}