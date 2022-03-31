import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AddOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AddOne extends Effect
{
    

    public AddOne(double x, double y){
        // Calls super constructor, then create image
        super(x, y, 180);
        this.image = new GreenfootImage("AddOne.png");
        this.getImage().scale(32, 32);
        this.getImage().setTransparency(255);
    }
    
    public void act()
    {
        // Calls super's act method
        super.act();
        
    }
}
