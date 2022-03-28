import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AddOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heart extends Effect
{
    
    public Heart(double x, double y){
        super(x, y, 180);
        this.getImage().scale(32, 32);
        
    }
    
    public void act()
    {
        super.act();
        System.out.println("HEART");
        
    }
}
