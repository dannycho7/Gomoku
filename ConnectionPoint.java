import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class ConnectionPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConnectionPoint extends Pieces
{
    public int once = 1;

    /**
     * Act - do whatever the ConnectionPoint wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        playerMove();
    }    

    /**
     * checks during the playing of the game if the player has decided to make a move on this point 
     * of the board.
     * @param null
     * @return void
     */
    public void playerMove()
    {
        //if(user is playing an AI) //there is no reason to swap playerTurn.
        Board board = (Board)getWorld();

        if(Greenfoot.mouseClicked(this))
        {
            value++;
            int x = getX()/25;
            int y = getY()/25;
            if(Pieces.getPlayerTurn()){
                getWorld().addObject(new White() , x*25, y*25);
                inputs[x - 1][y - 1] = 1;
                if(winnerYesOrNo(x  - 1, y  - 1, 1))
                {
                    board.gameOver(1);
                }
            }
            else{
                getWorld().addObject(new Black() , x*25 , y*25);
                inputs[x  -1 ][y -1 ] = 2;

                if(winnerYesOrNo(x - 1, y - 1, 2))
                {
                    board.gameOver(2);
                }
            }
        }
    }

    public void printArr(){
        for(int i = 0 ; i < 19 ; i++){
            for(int j = 0 ; j <19 ; j++){
                System.out.print(inputs[j][i]);
            }
            System.out.println();
        }
    }

    /**
     * Checks if the player has 5 pieces in a row horizontally, vertically, or diagonally
     * @param x and y coordinate, and color, which blank is 0, white is 1, and black is 2.
     * @return void
     */
    public static boolean winnerYesOrNo(int x, int y, int color)
    {
        int counterRight = 0; 
        int counterLeft = 0;
        //checks horizontal
        for(int i = 1; i < 5 && x + i < 19; i++)
        {
            //checks right
            if(inputs[x + i][y] == color)
                counterRight++;
            else 
                break;
        }
        for(int j = 1; j < 5 && x - j > 0; j++)
        {
            //checks left
            if(inputs[x - j][y] == color)
                counterLeft++;
            else
                break;
        }
        if(counterLeft + counterRight >= 4)
        {
            return true;
        }

        int counterVertUp = 0;
        int counterVertDown = 0;
        //checks vertically
        for(int i = 1; i < 5 && y + i < 19; i++)
        {
            //checks down
            if(inputs[x][y + i] == color)
                counterVertUp++;
            else 
                break;
        }
        for(int j = 1; j < 5 && y - j > 0; j++)
        {
            //checks up
            if(inputs[x][y - j] == color)
                counterVertDown++;
            else
                break;
        }
        if(counterVertUp + counterVertDown >= 4)
        {
            return true;
        }

        int counterDiagUpRight = 0;
        int counterDiagDownLeft = 0;
        //checks diagonally up right down left
        for(int i = 1; i < 5 && x + i < 19 && y - i > 0; i++)
        {
            //checks up right
            if(inputs[x + i][y - i] == color)
                counterDiagUpRight++;
            else 
                break;
        }
        for(int j = 1; j < 5 && x - j > 0 && y + j < 19; j++)
        {
            //checks down left
            if(inputs[x - j][y + j] == color)
                counterDiagDownLeft++;
            else
                break;
        }
        if(counterDiagUpRight + counterDiagDownLeft >= 4)
        {
            return true;
        }

        int counterDiagUpLeft = 0;
        int counterDiagDownRight = 0;
        //checks diagonally up left down right
        for(int i = 1; i < 5 && x - i > 0 && y - i > 0 ; i++)
        {
            //checks up left
            if(inputs[x - i][y - i] == color)
                counterDiagUpLeft++;
            else 
                break;
        }
        for(int j = 1; j < 5 && x + j < 19 && y + j < 19; j++)
        {
            //checks down right
            if(inputs[x + j][y + j] == color)
                counterDiagDownRight++;
            else
                break;
        }
        if(counterDiagUpLeft + counterDiagDownRight >= 4)
        {
            return true;
        }

        return false;
    }
}