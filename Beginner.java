import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Beginner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Beginner extends Pieces
{
    private static ArrayList<Integer> arr = new ArrayList<Integer>();

    /**
     * Act - do whatever the Beginner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        bestPossibleMove();
    }    

    /**
     * This method determines the move best suited for this computer's difficulty level.
     * @param null
     * @return void
     */
    public void bestPossibleMove()
    { 

        Board board = (Board)getWorld();
        int xPlacement = 0; //this stores the location (x) AI should put a white piece on
        int yPlacement = 0; //this stores the location (y) AI should put a white piece on
        int counterPlacement = 0; //this makes sure that the AI will stop placing white pieces after they have placed one down.
        if(Greenfoot.mouseClicked(this))  
        {
            int x = getX()/25; //x coordinate + 1 in array 
            int y = getY()/25; //y coordinate + 1 in array
            getWorld().addObject(new Black() , getX() , getY());
            inputs[x - 1][y - 1] = 2; //update array 
            boolean youCannotWin = false; //this makes sure that you can't win by simply having a counter of 4 color pieces.

            //initialize variables everytime a piece has been put down
            int counterBlackRight = 0; //number of black pieces to the right of the current
            int counterBlackLeft = 0; //number of black pieces to the left of the current
            int counterEmpty = 0; // allows for one space to be empty between the three
            //checks horizontal
            for(int i = 1; i < 5 && x + i < 19; i++)
            {
                //checks 5 to right
                //if the color of the one on the right is same as current, 
                //implement the counter if their colors are black
                if(inputs[x - 1][y - 1] == inputs[x + i - 1][y - 1])
                    counterBlackRight++;
                else if(inputs[x + i - 1][y - 1] == 0 && counterEmpty == 0) //if encountered the first blank space
                {
                    //enter if statement if pieces next to the blank space are black
                    if(x + i - 2 > -1 && inputs[x + i - 2][y - 1] == inputs[x + i][y - 1]) 
                    {
                        xPlacement = x + i;
                        yPlacement = y;
                        youCannotWin = true;
                    }
                    counterEmpty++;
                }
                else{
                    counterEmpty = 0; //reset counter
                    break;
                }
            }

            for(int j = 1; j < 5 && x - j > 0; j++)
            {
                //checks 5 to the left
                //if the color of the one on the left is same as current, 
                //implement the counter if their colors are black
                if(inputs[x - 1][y - 1] == inputs[x - j - 1][y - 1])
                    counterBlackLeft++;
                else if(inputs[x - j - 1][y - 1] == 0 && counterEmpty == 0) //if encountered the first blank space
                {
                    //enter if statement if pieces next to the blank space are black
                    if(x - j - 2 > 0 && inputs[x - j][y - 1] == inputs[x - j - 2][y - 1])
                    {     
                        xPlacement = x - j;
                        yPlacement = y;
                        youCannotWin = true;
                    }
                    counterEmpty++;
                }
                else {
                    counterEmpty = 0; //reset counter
                    break;
                }
            }

            if(counterBlackRight + counterBlackLeft >= 4 && !youCannotWin){
                //show win board
                board.gameOver(2); //end game
            }
            //if there are 2 or more (not including current) in a row
            if(counterBlackRight + counterBlackLeft >= 2 && (x + counterBlackRight + 1 < 20 || x - counterBlackLeft - 1 > -1)){
                //if a blank exists in the row
                if(xPlacement != 0 && yPlacement != 0 &&  inputs[xPlacement - 1][yPlacement - 1] == 0 && counterPlacement == 0)
                {
                    getWorld().addObject(new White(), xPlacement * 25, yPlacement* 25);
                    inputs[xPlacement - 1][yPlacement - 1] = 1; //update array
                    counterPlacement++;
                } //if there's a blank space on the right end
                else if(inputs[x + counterBlackRight][y - 1] == 0 && counterPlacement == 0)
                {
                    getWorld().addObject(new White(), (x + counterBlackRight + 1) * 25, y * 25);
                    inputs[x + counterBlackRight][y - 1] = 1; //update array
                    counterPlacement++;
                } //if there's a blank space on the left end
                else if(inputs[x - counterBlackLeft - 2][y - 1] == 0 && counterPlacement == 0)
                {
                    getWorld().addObject(new White(), (x - counterBlackLeft - 1 ) * 25, y * 25);
                    inputs[x - counterBlackLeft - 2][y - 1] = 1; //update array
                    counterPlacement++;
                }
            } 

            int counterBlackVertUp = 0;
            int counterBlackVertDown = 0;
            xPlacement = 0;
            yPlacement = 0;
            youCannotWin = false;
            //checks vertically
            for(int i = 1; i < 5 && y + i < 19; i++)
            {
                //checks down
                if(inputs[x - 1][y - 1] == inputs[x - 1][y + i - 1])
                    counterBlackVertDown++;
                else if(inputs[x - 1][y + i - 1] == 0 && counterEmpty == 0 ){

                    if(inputs[x - 1][y + i - 2] == inputs[x - 1][y + i])
                    {   
                        xPlacement = x;
                        yPlacement = y + i;
                    }
                    counterEmpty++;
                }
                else{
                    counterEmpty = 0;
                    break;
                }
            }
            for(int j = 1; j < 5 && y - j > 0; j++)
            {
                //checks up
                if(inputs[x - 1][y - 1] == inputs[x - 1][y - j - 1])
                    counterBlackVertUp++;
                else if(inputs[x - 1][y - j - 1] == 0 && counterEmpty == 0 ){
                    if(y - j - 2 > 0 && inputs[x - 1][y - j - 2] == inputs[x - 1][y - j])
                    {   
                        xPlacement = x;
                        yPlacement = y-j;
                        youCannotWin = true;
                    }
                    counterEmpty++;
                }
                else{
                    counterEmpty = 0;
                    break;
                }
            }                

            if(counterBlackVertUp + counterBlackVertDown >= 4 && !youCannotWin){
                board.gameOver(2);
 
            }
            if(counterBlackVertUp + counterBlackVertDown >= 2 && (y + counterBlackVertDown + 1 < 20 || y - counterBlackVertUp - 1 > -1)){
                if(xPlacement != 0 && yPlacement != 0 && inputs[xPlacement - 1][yPlacement - 1] == 0 && counterPlacement == 0)
                {
                    getWorld().addObject(new White(), xPlacement * 25, yPlacement * 25);
                    inputs[xPlacement - 1][yPlacement - 1] = 1;
                    counterPlacement++;
                }
                else if(inputs[x - 1][y + counterBlackVertDown] == 0 && counterPlacement == 0)
                {
                    getWorld().addObject(new White(), x * 25, (y + counterBlackVertDown + 1) * 25);
                    inputs[x - 1][y + counterBlackVertDown] = 1;
                    counterPlacement++;
                }
                else if(inputs[x - 1][y - counterBlackVertUp - 2] == 0 && counterPlacement == 0){
                    getWorld().addObject(new White(), x * 25, (y - counterBlackVertUp - 1) * 25);
                    inputs[x - 1][y - counterBlackVertUp - 2]  = 1;
                    counterPlacement++;
                }
            }

            int counterBlackDiagUpRight = 0;
            int counterBlackDiagDownLeft = 0;
            xPlacement = 0;
            yPlacement = 0;

            youCannotWin = false;
            //checks diagonally up right down left
            for(int i = 1; i < 5 && x + i < 20 && y - i > 0; i++)
            {
                //checks up right
                //coordinate shift: x++  ;  y--
                if(inputs[x - 1][y - 1] == inputs[x + i - 1][y - i - 1])
                {
                    counterBlackDiagUpRight++;
                }
                else if(inputs[x + i - 1][y - i - 1] == 0 && counterEmpty == 0 )
                {
                    if(x + i < 19 && y - i - 2 > -1 && inputs[x + i - 2][y - i] == inputs[x + i][y - i - 2]){
                        xPlacement = x + i;
                        yPlacement  = y - i;
                        youCannotWin = true;
                    }
                    counterEmpty++;
                }
                else{
                    counterEmpty = 0;
                    break;
                }
            }
            for(int j = 1; j < 5 && x - j > 0 && y + j < 19; j++)
            {
                //checks down left
                if(inputs[x - 1][y - 1] == inputs[x - j - 1][y + j - 1])
                    counterBlackDiagDownLeft++;
                else if(inputs[x - j - 1][y + j - 1] == 0 && counterEmpty == 0 )
                {
                    if(x - j - 2 > -1 && y + j - 2 < 19 && inputs[x - j][y + j - 2] == inputs[x - j - 2][y + j]){
                        xPlacement = x - j;
                        yPlacement = y + j;
                        youCannotWin = true;
                    }
                    counterEmpty++;
                }
                else {
                    counterEmpty = 0;
                    break;
                }
            }

            if(counterBlackDiagUpRight + counterBlackDiagDownLeft >= 4 && !youCannotWin){
                board.gameOver(2);

            }
            if(counterBlackDiagUpRight + counterBlackDiagDownLeft >= 2){
                if(xPlacement != 0 && yPlacement != 0 && inputs[xPlacement - 1][yPlacement - 1] == 0 && counterPlacement == 0)
                {
                    getWorld().addObject(new White(), (xPlacement) * 25, (yPlacement) * 25);
                    inputs[xPlacement - 1][yPlacement - 1] = 1;
                    counterPlacement++;
                }
                if(y - counterBlackDiagUpRight - 1 > -1 && x + counterBlackDiagUpRight + 1 < 20 && inputs[x + counterBlackDiagUpRight][y - counterBlackDiagUpRight - 2] == 0 && counterPlacement == 0)
                {
                    getWorld().addObject(new White(), (x + counterBlackDiagUpRight + 1) * 25, (y - counterBlackDiagUpRight - 1) * 25);
                    inputs[x + counterBlackDiagUpRight][y - counterBlackDiagUpRight - 2] = 1;
                    counterPlacement++;
                }
                else if(x - counterBlackDiagDownLeft - 1 > -1 && y + counterBlackDiagDownLeft + 1 < 20 && inputs[x - counterBlackDiagDownLeft - 2][y + counterBlackDiagDownLeft] == 0 && counterPlacement == 0){
                    getWorld().addObject(new White(), (x - counterBlackDiagDownLeft - 1) * 25, (y + counterBlackDiagDownLeft +1) * 25);
                    inputs[x - counterBlackDiagDownLeft - 2][y  + counterBlackDiagDownLeft] = 1;
                    counterPlacement++;
                }
            }

            int counterBlackDiagUpLeft = 0;
            int counterBlackDiagDownRight = 0;
            xPlacement = 0;
            yPlacement = 0;
            youCannotWin = false;
            //checks diagonally up left down right
            for(int i = 1; i < 5 && x - i > 0 && y - i > 0; i++)
            {
                //checks left
                if(inputs[x - 1][y - 1] == inputs[x - i -1 ][y - i - 1]){
                    counterBlackDiagUpLeft++;
                }
                else if(inputs[x-i -1][y-i - 1] == 0 && counterEmpty == 0 )
                {
                    if(y - i - 2 > - 1 && x - i - 2 >-1 && inputs[x - i][y - i] == inputs[x - i - 2][y - i - 2]){
                        xPlacement = x - i;
                        yPlacement = y - i;
                        youCannotWin = true;
                    }
                    counterEmpty++;
                }
                else{
                    counterEmpty = 0;
                    break;
                }
            }
            for(int j = 1; j < 5 && x + j < 20  && y + j < 20; j++)
            {
                //checks right
                if(inputs[x - 1][y - 1] == inputs[x + j - 1][y + j - 1])
                {
                    counterBlackDiagDownRight++;
                }
                else if(inputs[x + j - 1][y + j -1] == 0 && counterEmpty == 0 )
                {
                    if(x + j < 19 && y + j < 19 && inputs[x + j ][y + j] == inputs[x + j - 2][y + j - 2]){
                        xPlacement = x + j;
                        yPlacement = y + j;
                        youCannotWin = true;
                    }
                    counterEmpty++;
                }
                else {
                    counterEmpty = 0;
                    break;
                }

            }

            if(counterBlackDiagDownRight + counterBlackDiagUpLeft == 4  && !youCannotWin){
                board.gameOver(2);

            }
            if( counterBlackDiagDownRight + counterBlackDiagUpLeft >= 2){
                if(xPlacement != 0 && yPlacement != 0 && inputs[xPlacement - 1][yPlacement - 1] == 0 && counterPlacement == 0){
                    getWorld().addObject(new White(), (xPlacement) * 25, (yPlacement) * 25);
                    inputs[xPlacement - 1][yPlacement - 1] = 1;
                    counterPlacement++;
                }
                if(x - counterBlackDiagUpLeft - 2 > -1 && y - counterBlackDiagUpRight - 2 > 0 && inputs[x - counterBlackDiagUpLeft - 2][y - counterBlackDiagUpLeft - 2] == 0 && counterPlacement == 0){
                    getWorld().addObject(new White(), (x - counterBlackDiagUpLeft - 1) * 25, (y - counterBlackDiagUpLeft - 1 ) * 25);
                    inputs[x - counterBlackDiagUpLeft -2][y - counterBlackDiagUpLeft - 2] = 1;
                    counterPlacement++;
                }
                else if(x + counterBlackDiagDownRight < 19 && y + counterBlackDiagDownRight < 19 &&  inputs[x + counterBlackDiagDownRight][y + counterBlackDiagDownRight] == 0 && counterPlacement == 0){
                    getWorld().addObject(new White(), (x + counterBlackDiagDownRight + 1) * 25, (y + counterBlackDiagDownRight + 1) * 25);
                    inputs[x + counterBlackDiagDownRight][y+counterBlackDiagDownRight] = 1;
                    counterPlacement++;
                }
            }                   

            if(counterPlacement == 0)
                whiteMove(counterPlacement);

            int randomX = Greenfoot.getRandomNumber(3) - 1 ;
            int randomY = Greenfoot.getRandomNumber(3) - 1;
            int counter = 0;
            //randomX nor randomY should never be zero because if it is zero it means that the white piece placement won't be different from the original black one. 
            while((x + randomX - 1< 0 || y + randomY - 1 < 0 ) || (x + randomX - 1 > 18 || y + randomY - 1 > 18
                || (randomX == 0 && randomY == 0) || inputs[x + randomX - 1][y + randomY - 1] != 0)){
                randomX = Greenfoot.getRandomNumber(3) - 1 ;
                randomY = Greenfoot.getRandomNumber(3) - 1;
            }

            //makes the move if there is no three in a row
            if(x + randomX - 1 > -1 && y + randomY - 1 > -1 && x + randomX - 1 < 19 && y + randomY - 1 < 19 && counterPlacement == 0 && inputs[x + randomX - 1][y + randomY - 1] == 0) {
                getWorld().addObject(new White() , (x + randomX) * 25 , (y + randomY) * 25);
                inputs[x + randomX - 1][y + randomY - 1] = 1;
            }
        }
    }

    /**
     * This method find the location which AI need to place to win.
     * @param 
     * @return ????? int? arr?
     */
    public void whiteMove(int move)
    { 
        int whiteCounter = 0;

        //horizontal
        for(int r = 0; r < 19; r++)
        {

            for(int c = 0; c < 19; c++)
            {
                if(c + 1 < 19 && inputs[r][c] == inputs[r][c++] && inputs[r][c] == 1)
                {
                    whiteCounter++;
                }

                if(whiteCounter >= 2)
                {
                    if(c + 2 < 19 && inputs[r][c + 2] == 0)
                    { 
  
                        //getWorld().addObject(new White() , r * 25 , (c + 3) * 25);
                        //inputs[r][c + 2] = 1;
                        move++;
                        arr.add(r);
                        arr.add(c);
                    }

                    if(c - 1 > -1 && inputs[r][c - 1] == 0)
                    {
                        arr.add(r);
                        arr.add(c - 1);
                    }

                    if(r - 4 > -1 && inputs[r][c] != 0 && inputs[r - 4][c] != 0)
                    {
                        whiteCounter = 0;
                    }
                }

            }
        }

        arr.add(55555);

        //down right up left
        //         for(int c = 15; c > -1; c--)
        //         {
        //             for(int r = 0, c2 = c; r < 15 && c2 < 18; r++, c2++)
        //             {
        //                 if(counter == 3)
        //                 {
        //                     if(inputs[r][c2] == 0)
        //                     {
        //                         arr.add(r);
        //                         arr.add(c2);
        //                     }
        //                     else if(inputs[r][c2 - 3] == 0)
        //                     {
        //                         arr.add(r);
        //                         arr.add(c2 - 3);
        //                     }
        //                     else
        //                         counter = 0;
        //                 }
        //                 else if(inputs[r][c2] == inputs[r][c2++] && inputs[r][c2] == 1)
        //                     counter++;
        //                 else
        //                     counter = 0;
        //             }
        //         }
    }
    //     
    //     public void printArr(){
    //         for(int i = 0 ; i < 19 ; i++){
    //             for(int j = 0 ; j <19 ; j++){
    //                 System.out.print(inputs[j][i]);
    //             }
    //             System.out.println();
    //         }
    //     }
}

