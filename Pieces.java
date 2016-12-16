import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pieces here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pieces extends Actor
{
    public static boolean playerTurn;
    public static int value = 0;
    public static int[][] inputs = new int[19][19];
    
    protected int newOne = 0;
    public void act(){
        if(newOne == 0){
            for(int i = 0 ; i<19 ; i++){
                for(int j = 0 ; j < 19 ; j++){
                    inputs[i][j] = 0;
                }
            }
            newOne = 1;
        }
        if(value == 1)
        {
            value = 0;
            if(playerTurn){
                playerTurn=false;
            }
            else{
                playerTurn=true;
            }
        }
    }

    /** 
     * This method allows the child (ConnectionPoint) to check whose turn it is (Black or white)
     * @param null
     * @return boolean
     */
    public static boolean getPlayerTurn()
    {
        return playerTurn;
    }

}