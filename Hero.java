import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Pedestrian
{
    public Hero(int direction){
        // call super constructor
        super(direction);
        
        // set a random speed and direction
        this.maxSpeed = this.speed = 5 + (Math.random() * 1);
        this.canHit = false;
        setImage("hero.png");
    }
    public void act()
    {
        super.act();
    }
}
