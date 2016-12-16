import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board extends World
{
    /**
     * Constructor for objects of class Board.
     */
    public Board()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 500, 1); 
        setPaintOrder(Menu.class, ScoreBoard.class, Pieces.class, Board.class);
        addObject(new Menu("Welcome to Gomoku!") , getWidth()/2, getHeight()/2);
        Greenfoot.start();
    }

    /**
     * Gameover scene
     * @param integer
     * @return null
     */
    public void gameOver(int color)
    {
        addObject(new ScoreBoard(color), getWidth() / 2, getHeight() / 2);  
    }
}