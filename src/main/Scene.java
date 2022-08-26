package main;

import input.KetInputs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static utilities.Constant.SceneConstant.*;

public class Scene extends JPanel {
    // NODE
    Node[][] node = new Node[MAX_SCREEN_COL][MAX_SCREEN_ROW];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    // OTHERS
    boolean goalReached;
    int step;

    public Scene() {
        setSceneSize();
        placeNodes();
        this.addKeyListener(new KetInputs(this));
        this.setFocusable(true);

        // SET START AND GOAL NODE
        setStartNode(6, 9);
        setGoalNode(23, 6);

        // SET SOLID NODES
        for(int i = 0; i < 15; i++) {
            setSolidNode(6 + i, 3 + i);
        }

        setSolidNode(7, 1);
        setSolidNode(7, 2);

        setSolidNode(22, 2);
        setSolidNode(23, 2);
        setSolidNode(21, 2);
        setSolidNode(21, 3);
        setSolidNode(21, 4);
        setSolidNode(21, 5);
        setSolidNode(21, 6);
        setSolidNode(21, 7);
        setSolidNode(21, 8);
        setSolidNode(21, 9);
        setSolidNode(21, 10);
        setSolidNode(22, 10);
        setSolidNode(23, 10);
        setSolidNode(24, 10);
        setSolidNode(24, 11);
        setSolidNode(24, 12);
        setSolidNode(24, 13);

        // SET COST
        setCostOnNodes();
    }

    private void setSceneSize() {
        Dimension size = new Dimension(SCENE_WIDTH, SCENE_HEIGHT);
        setPreferredSize(size);
        setBackground(Color.BLACK);
        setLayout(new GridLayout(MAX_SCREEN_ROW, MAX_SCREEN_COL));
    }

    private void placeNodes() {
        int col = 0;
        int row = 0;

        while(col < MAX_SCREEN_COL && row < MAX_SCREEN_ROW) {
            node[col][row] = new Node(col, row);
            add(node[col][row]);

            col++;
            if(col == MAX_SCREEN_COL) {
                col = 0;
                row++;
            }
        }
    }

    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

    private void setSolidNode(int col, int row) {
        node[col][row].setAsSolid();
    }

    private void setCostOnNodes() {
        int col = 0;
        int row = 0;

        while(col < MAX_SCREEN_COL && row < MAX_SCREEN_ROW) {
            getCost(node[col][row]);
            col++;
            if(col == MAX_SCREEN_COL) {
                col = 0;
                row++;
            }
        }
    }

    private void getCost(Node node) {
        // GET G COST (The distance between the start node and current node)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // GET H COST (The distance between the goal node and current node)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // GET F COST (The total cost)
        node.fCost = node.gCost + node.hCost;

        // DISPLAY THE COST ON NODE
        if(node != startNode && node != goalNode) {
            node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost + "</html>");
        }
    }

    public void search() {
        if(! goalReached && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // OPEN THE UP NODE
            if(row - 1 >= 0) openNode(node[col][row - 1]);
            // OPEN THE LEFT NODE
            if(col - 1 >= 0) openNode(node[col - 1][row]);
            // OPEN THE DOWN NODE
            if(row + 1 >= 0) openNode(node[col][row + 1]);
            // OPEN THE RIGHT NODE
            if(col + 1 >= 0) openNode(node[col + 1][row]);

            // FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++) {
                // Check if this node's F cost is better
                if(openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                // If f cost is equal, check the G cost
                else if(openList.get(i).fCost == bestNodefCost) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            // After the loop, we get the best node which is our next step
            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode) {
                goalReached = true;
            }
        }
        step++;
    }

    public void autoSearch() {
        while(! goalReached && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // OPEN THE UP NODE
            if(row - 1 >= 0) openNode(node[col][row - 1]);
            // OPEN THE LEFT NODE
            if(col - 1 >= 0) openNode(node[col - 1][row]);
            // OPEN THE DOWN NODE
            if(row + 1 >= 0) openNode(node[col][row + 1]);
            // OPEN THE RIGHT NODE
            if(col + 1 >= 0) openNode(node[col + 1][row]);

            // FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++) {
                // Check if this node's F cost is better
                if(openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                // If f cost is equal, check the G cost
                else if(openList.get(i).fCost == bestNodefCost) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            // After the loop, we get the best node which is our next step
            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }

    private void openNode(Node node) {
        if(! node.open && ! node.checked && ! node.solid) {
            // If the node is not opened yet, add it to the open list
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private void trackThePath() {
        // Backtrack and draw the best path
        Node current = goalNode;
        while(current != startNode) {
            current = currentNode.parent;
            if(current != startNode) {
                current.setAsPath();
            }
        }
    }
}
