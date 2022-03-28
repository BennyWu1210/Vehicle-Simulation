import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effect extends Actor
{
    protected double xPos, yPos;
    protected int actCounter;
    
    // for gif images
    protected boolean isGif;
    protected List<GreenfootImage> gifImageList;
    protected GifImage gifImage;
    protected int gifCounter, gifIndex, gifChangeRate;
    protected GreenfootImage image;
    
    
    
    public Effect(double x, double y, int ticks){
        this.xPos = x; 
        this.yPos = y;
        this.actCounter = ticks;
        setLocation((int)this.xPos, (int)this.yPos);
    }
    
    
    public void act()
    {
        // Add your action code here.
        // for later
        if (isGif){
            gifCounter --;
            if (gifCounter <= 0){
                getImage();
                gifCounter = gifChangeRate;
            }
        } else{
            if (actCounter > 0){
                actCounter --;
                if (actCounter < 60){
                    getImage().setTransparency(actCounter * 2);
                }
            } else{
                getWorld().removeObject(this);
            }
        }
        
        
        
    }
    
    
}
