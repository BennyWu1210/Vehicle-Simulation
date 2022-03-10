import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RunningMan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RunningMan extends Pedestrian
{
    
    protected int randomXDest;
    
    /**
     * Act - do whatever the RunningMan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    public RunningMan(int direction){
        // call super constructor
        super(direction);
        
        this.speed = 2 + (Math.random() * 1.5);
        this.canHit = false;
        this.isGif = true;
        this.gifImage = new GifImage("runningMan.gif");
        this.gifImageList = gifImage.getImages();
        this.gifChangeRate = 3;
    }
    
    public void act()
    {
        // sets a random X location to run toward
        if (awake){
            if (Math.abs(getX() - randomXDest) < 5) {
                randomXDest = -80 + getX() + (int)(Math.random() * 160);
                if (getX() < 50) randomXDest += 50;
                if (getX() > 750) randomXDest -= 50;
                
            }
            setLocation(getX() + speed * (randomXDest - getX() > 0 ? 1 : -1), getY());
        }
        super.act();
    }
}
