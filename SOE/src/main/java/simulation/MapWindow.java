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
        Map mapka = new Map("riverside");
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
                setBackground(i, j, map);
                frame.add(jPanelArray[i][j]);
            }
        }
        frame.setVisible(true);
        frame.repaint();
        System.out.println();
    }

    public void updateDisplay(char [][][] map) {
        for (int i = 0; i < Map.sizeOfMap ; i++) {
            for ( int j = 0; j < Map.sizeOfMap ; j++) {
                setBackground(i, j, map);
            }
        }
    }

    private void setBackground(int i, int j, char [][][] map) {
        Color background = getColor(map[1][i][j], map[0][i][j]);
        jPanelArray[i][j].setBackground(background);
    }

    private Color getColor(char creatureLayer, char surfaceLayer){
        switch (creatureLayer){
            case 'L':
                return new Color(122, 255, 100);//lime
            case 'W':
                return Color.CYAN;
            case '\0':
                //go to surfaceLayer;
                break;
            default:
                return  Color.WHITE;
        }
        switch (surfaceLayer){
            case 'L':
                return Color.GREEN;
            case 'W':
            default:
                return  Color.BLUE;
        }
    }
}