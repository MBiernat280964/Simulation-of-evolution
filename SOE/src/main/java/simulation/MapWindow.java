package simulation;


import javax.swing.*;
import java.awt.*;
/**
 * Object <code>MapWindow</code> handles the graphical interface for displaying map during simulation
 */
public class MapWindow {
    private char[][][] map;
    JPanel [][] jPanelArray = new JPanel[Map.sizeOfMap][Map.sizeOfMap];
    JFrame frame;
    /**
     * Constructor for MapWindow class
     */
    public MapWindow(char [][][] map, JFrame frame) {
        this.map = map;
        this.frame = frame;
        prepareGUI(map);
    }
    /**
     * initializes GUI elements
     * is used in mapWindow constructor
     * @param map utilizes map object from Maps class created in Simulation class
     */
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
    /**
     * Updates the display of mapWindow's JFrame
     * @param map utilizes map object from Maps class created in Simulation class
     */
    public void updateDisplay(char [][][] map) {
        for (int i = 0; i < Map.sizeOfMap ; i++) {
            for ( int j = 0; j < Map.sizeOfMap ; j++) {
                setBackground(i, j, map);
            }
        }
    }
    /**
     * Changes background color of a particular JPanel in GridLayout
     * @param map utilizes map object from Maps class created in Simulation class
     * @param i is one of the indexes grouping the JPanels in GroupLayout
     * @param j is the second of the two indexes grouping the JPanels in GroupLayout
     */
    private void setBackground(int i, int j, char [][][] map) {
        Color background = getColor(map[1][i][j], map[0][i][j]);
        jPanelArray[i][j].setBackground(background);
    }
    /**
     * fetches the character of a particular coordinate in Map object and decides on colors used to display it
     * @param creatureLayer 2-dimensional map consisting of creatures
     * @param surfaceLayer 2-dimensional map consisting of a generated biome
     * @return color of a particular JPanel
     */
    private Color getColor(char creatureLayer, char surfaceLayer){
        switch (creatureLayer){
            case 'L':
                return new Color(122, 255, 100);//lime
            case 'W':
                return Color.CYAN;
            case 'b':
                return Color.BLACK;
            case 'c':
                return Color.RED;
            case 'd':
                return Color.MAGENTA;
            case 'f':
                return Color.ORANGE;
            case 'h':
                return Color.YELLOW;
            case '\0':
                //go to surfaceLayer;
                break;
            case 'w':
                return Color.PINK;
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