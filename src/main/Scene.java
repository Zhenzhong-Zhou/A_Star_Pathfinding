package main;

import javax.swing.*;
import java.awt.*;

import static utilities.Constant.SceneConstant.*;

public class Scene extends JPanel {
    // SCENE SETTINGS
    final int maxCol = 15;
    final int maxRow = 10;
    final int nodeSize = 70;
    final int sceneWidth = maxCol * nodeSize;
    final int sceneHeight = maxRow * nodeSize;

    // NODE
    Node[][] node = new Node[maxCol][maxRow];

    public Scene() {
        this.setPreferredSize(new Dimension(sceneWidth, sceneHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow, maxCol));

        int col = 0;
        int row = 0;

        while(col < maxCol && row < maxRow) {
            node[col][row] = new Node(col,row);
            this.add(node[col][row]);

            col++;
            if(col == maxCol) {
                col = 0;
                row++;
            }
        }
    }

    private void setSceneSize() {
        Dimension size = new Dimension(SCENE_WIDTH, SCENE_HEIGHT);
        setPreferredSize(size);
        setBackground(Color.BLACK);
        setLayout(new GridLayout(MAX_SCREEN_COL, MAX_SCREEN_ROW));
    }

    private void placeNodes() {
        int col = 0;
        int row = 0;

        while(col < MAX_SCREEN_COL && row < MAX_SCREEN_ROW) {
            node[col][row] = new Node(col,row);
            add(node[col][row]);

            col++;
            if(col == MAX_SCREEN_COL) {
                col = 0;
                row++;
            }
        }
    }
}
