package gol.display;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Grid extends JPanel{

    //variables describing grid size
    final int gridWidth;
    final int gridHeight;
    final int cellSize;

    //array representing the grid
    byte [][] gridArray;

    //default constructor
    public Grid()
    {
        gridWidth = 200;
        gridHeight = 200;
        cellSize = 4;

        //this.setSize(gridWidth * cellSize,gridHeight * cellSize);
        this.setBackground(Color.BLACK);


        gridArray = new byte[gridWidth][gridHeight];
        setGridRandom();
    }

    //function that draws a grid on the window
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        super.paintComponent(g);

        for(int i=0; i<gridWidth; i++)
        {
            for(int j=0; j<gridHeight; j++)
            {
                if(gridArray[i][j] == 1) {
                    g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(gridWidth * cellSize,gridHeight * cellSize);
    }

    //function that sets every array element to either 0 or 1
    public void setGridRandom(){
        for(int i=0; i<gridWidth; i++)
        {
            for(int j=0; j<gridHeight; j++)
            {
                gridArray[i][j] = (byte)ThreadLocalRandom.current().nextInt(0, 2);
            }
        }
    }

    //function that checks if given cell is dead or alive
    private byte isAlive(int x, int y){
        if(x<0 || x>gridWidth - 1)
        {
            return 0;
        }

        if(y<0 || y>gridHeight - 1)
        {
            return 0;
        }
        else
        {
            return this.gridArray[x][y];
        }
    }

    //function that checks how many neighbour cells are alive
    private int countLivingNeighbours(int x, int y){

        int counter = 0;

        counter += isAlive(x-1, y-1);
        counter += isAlive(x-1, y);
        counter += isAlive(x-1, y+1);

        counter += isAlive(x, y-1);
        counter += isAlive(x, y+1);

        counter += isAlive(x+1, y-1);
        counter += isAlive(x+1, y);
        counter += isAlive(x+1, y+1);

        return counter;
    }

    public void gridIteration() {

        byte [][] newGridArray = new byte[gridWidth][gridHeight];

        int neighbours = 0;

        for(int i=0; i<gridWidth; i++)
        {
            for(int j=0; j<gridHeight; j++)
            {
                neighbours = countLivingNeighbours(i, j);

                if(neighbours == 3)
                {
                    newGridArray[i][j] = 1;
                }
                else if(neighbours == 2)
                {
                    if(this.gridArray[i][j] == 1)
                    {
                        newGridArray[i][j] = 1;
                    }
                }
                else
                {
                    newGridArray[i][j] = 0;
                }
            }
        }

        this.gridArray = newGridArray;
    }
}
