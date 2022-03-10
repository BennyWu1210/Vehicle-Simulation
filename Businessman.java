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
        
        this.speed = 0.8 + (Math.random() * 1.1);
        setImage("businessman.png");
        
    }
    public void act()
    {
        super.act();
        
        spawnGoldCoin();
    }
    
    public void spawnGoldCoin(){
        if (Greenfoot.getRandomNumber(300) == 0){
            System.out.println("HUEH");
            GoldCoin c = new GoldCoin(getX(), getY());
        }
    }
 
}
