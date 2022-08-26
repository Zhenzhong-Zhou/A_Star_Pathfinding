package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {
    Node parent;
    int col, row, gCost, hCost, fCost;
    boolean start, goal, solid, open, checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;

        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        addActionListener(this);
        this.setOpaque(true);
    }

    public void setAsStart() {
        this.setBorderPainted(false);
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
        setText("Start");
        start = true;
    }

    public void setAsGoal() {
        this.setBorderPainted(false);
        setBackground(Color.YELLOW);
        setForeground(Color.BLACK);
        setText("Goal");
        goal = true;
    }

    public void setAsSolid() {
        this.setBorderPainted(false);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        solid = true;
    }

    public void setAsOpen() {
        open = true;
    }

    public void setAsChecked() {
        if(! start && ! goal) {
            this.setBorderPainted(false);
            setBackground(Color.ORANGE);
            setForeground(Color.BLACK);
        }
        checked = true;
    }

    public void setAsPath() {
        this.setBorderPainted(false);
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setBorderPainted(false);
        setBackground(Color.ORANGE);
    }
}
