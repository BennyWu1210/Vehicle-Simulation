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
    protected int gifCounter, gifIndex, gifChangeRate, gifCycle;
    protected GreenfootImage image;
    
    
    
    public Effect(double x, double y, int ticks){
        this.xPos = x; 
        this.yPos = y;
        this.actCounter = ticks;
        this.gifCycle = 1;
        setLocation((int)this.xPos, (int)this.yPos);
    }
    
    public Effect(double x, double y){
        this.xPos = x;
        this.yPos = y;
        this.gifCycle = 1;
        this.actCounter = 1000;
    }
    
    
    public void act()
    {
        // Add your action code here.
        // for later
        if (isGif){
            gifCounter --;
            if (gifCounter <= 0){
                getImage();
                gifIndex ++;
                gifCounter = gifChangeRate;
            }
            if (gifIndex == gifImageList.size()){
                gifIndex = 0;
                gifCycle --;
            }
            if (gifCycle == 0){
                getWorld().removeObject(this);
                return;
            }
        } else{
            if (actCounter > 0){
                actCounter --;
                if (actCounter < 120){
                    getImage().setTransparency(actCounter * 2);
                }
            } else{
                getWorld().removeObject(this);
            }
        }
        
        
        
    }
    
    public double distanceFrom(Actor actor){
        return distanceFrom(actor.getX(), actor.getY());
    }
    
    public double distanceFrom(int x, int y)
    {
        double distance = Math.sqrt(Math.pow(x-getX(), 2) + Math.pow(y-getY(), 2));
        return distance;
    }    
    
    
    
    public void setTick(int ticks){
        this.actCounter = ticks;
    }
    
    public GreenfootImage getImage(){
        return isGif ? gifImageList.get(gifIndex) : image;
    }
    
    
}
