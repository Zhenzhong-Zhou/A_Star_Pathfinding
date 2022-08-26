package main;

import javax.swing.*;

public class Window extends JFrame {
    public Window(Scene scene) {
        setTitle("A Star Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(scene);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
