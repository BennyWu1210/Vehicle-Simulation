import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Businessman here.
 * 
 * @author Benny 
 * @version (a version number or a date)
 */
public class Businessman extends Pedestrian
{
    
    
    public Businessman(int direction){
        // call super constructor
        super(direction);
        
        // set a random speed and image
        
        this.maxSpeed = this.speed = 1 + (Math.random() * 1.1);
        setImage("businessman.png");
        
    }
    public void act()
    {
        spawnGoldCoin();
        super.act();
    }
    
    /*
     * Spawns gold coin randomly behind
     */
    public void spawnGoldCoin(){
        if (Greenfoot.getRandomNumber(300) == 0){
            GoldCoin c = new GoldCoin(getX(), getY());
            getWorld().addObject(c, getX(), getY());
        }
    }
 
}
