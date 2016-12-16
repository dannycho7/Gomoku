import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends Actor
{
    public Menu(String Type)
    {
        createImage(Type);
    }
    /**
     * Act - do whatever the Menu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("1")){
            for(int i = 1 ; i < 20 ; i++)
            {
                for(int j = 1 ; j< 20 ; j++){
                    getWorld().addObject(new Beginner(), 25 * i, 25 * j);
                }   
            }
            getWorld().removeObject(this);
        }
        if(Greenfoot.isKeyDown("2")){
            for(int i = 1 ; i < 20 ; i++)
            {
                for(int j = 1 ; j< 20 ; j++){
                    getWorld().addObject(new ConnectionPoint(), 25*i, 25*j); 
                }   
            }
            getWorld().removeObject(this);
        }
    }    
    
    /**
     * Create the score board image
     * @param 
     * @return void
     */  
    public void createImage(String msg)
    {
        GreenfootImage image = new GreenfootImage(350, 150);
        
        image.setColor(new Color(255, 255, 255, 128));
        image.fillRect(0, 0, 350, 350);
        image.setColor(new Color(0, 0, 0));
        image.fillRect(5, 5, 350 - 10, 350 - 10);
        Font font = image.getFont(); 
        font = font.deriveFont(20.0f);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(msg, 75, 70);
        image.drawString("Press 1 for 2-Player Mode", 65, 100);
        image.drawString("2 for Player vs CPU" , 80, 130);
        setImage(image);
    }
}
