package input;

import main.Scene;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

public class KetInputs implements KeyListener {
    Scene scene;

    public KetInputs(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE -> scene.search();
            case KeyEvent.VK_ENTER -> {
                long drawStart = 0;
                drawStart = System.nanoTime();

                scene.autoSearch();

                long drawEnd = System.nanoTime();
                long duration = drawEnd - drawStart;
                double second = (double) duration / 1000000000.0;
                DecimalFormat convert = new DecimalFormat();
                convert.setMaximumFractionDigits(8);

                System.out.println(convert.format(second));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
