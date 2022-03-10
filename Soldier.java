import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Soldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Soldier extends Pedestrian
{
    /**
     * Act - do whatever the Soldier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Soldier(int direction){
        // calls super constructor
        super(direction);
        
        // set a random speed and image
        this.speed = 1.2 + (Math.random() * 1.2);
        setImage("soldier.png");
    }
    public void act()
    {
        super.act();
        // Add your action code here.
    }
}
