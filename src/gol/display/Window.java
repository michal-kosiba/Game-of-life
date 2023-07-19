package gol.display;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private Grid grid;

    public Window(){

        //creating grid
        this.grid = new Grid();
        this.add(grid);
        this.pack();

        //setting window parameters
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Game of life");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run() throws InterruptedException {

        for(;;) {
            grid.gridIteration();
            grid.repaint();
            Thread.sleep(100);
        }
    }
}

