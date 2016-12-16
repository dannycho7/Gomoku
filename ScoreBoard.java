import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class Scoreboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoard extends Actor
{
        
    /**
     * Create a score board for the final result.
     * @param integer
     */
    public ScoreBoard(int color)
    {
        if(color == 1)
            createImage("White Wins!");
        else if(color == 2)
            createImage("Black Wins!");
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("3")){
            Greenfoot.setWorld(new Board());
        }
    }
    /**
     * Create the score board image
     * @param 
     * @return void
     */  
    public void createImage(String msg)
    {
        GreenfootImage image = new GreenfootImage(350, 350);
        
        image.setColor(new Color(255, 255, 255, 128));
        image.fillRect(0, 0, 350, 350);
        image.setColor(new Color(0, 0, 128));
        image.fillRect(5, 5, 350 - 10, 350 - 10);
        Font font = image.getFont();
        font = font.deriveFont(30.0f);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(msg, 60, 100);
        image.drawString("press 3 to play again",50, 130);
        setImage(image);
    }
}
