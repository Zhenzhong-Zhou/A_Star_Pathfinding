package main;

import javax.swing.*;
import java.awt.*;

import static utilities.Constant.SceneConstant.*;

public class Scene extends JPanel {
    // NODE
    Node[][] node = new Node[MAX_SCREEN_COL][MAX_SCREEN_ROW];

    public Scene() {
        setSceneSize();
        placeNodes();
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
