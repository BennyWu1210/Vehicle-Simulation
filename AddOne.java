import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AddOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AddOne extends Animation
{
    
    public AddOne(double x, double y){
        super(x, y);
        timeMillis = 1000;
        this.getImage().scale(30, 30);
        
    }
    
    public void act()
    {
        if (elapsed.millisElapsed() > timeMillis){
            getWorld().removeObject(this);
        }
        
    }
}
