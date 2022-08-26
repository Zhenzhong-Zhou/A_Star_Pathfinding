package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        new App();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new Scene());

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
