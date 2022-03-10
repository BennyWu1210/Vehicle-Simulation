import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animation extends Actor
{
    protected double xPos, yPos;
    protected SimpleTimer elapsed;
    protected double timeMillis;
    
    // for gif images
    protected boolean isGif;
    protected List<GreenfootImage> gifImageList;
    protected GifImage gifImage;
    protected int gifCounter, gifIndex, gifChangeRate;
    
    public Animation(double x, double y){
        this.xPos = x; 
        this.yPos = y;
        elapsed = new SimpleTimer();
        setLocation((int)this.xPos, (int)this.yPos);
    }
    
    public void act()
    {
        // Add your action code here.
        // for later
        
    }
}
