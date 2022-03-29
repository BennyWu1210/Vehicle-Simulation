import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Pedestrian that tries to walk across the street
 */
public abstract class Pedestrian extends SuperSmoothMover
{
    protected double speed;
    protected double maxSpeed;
    protected int direction;
    protected boolean awake;
    protected boolean canHit;
    
    
    public Pedestrian(int direction) {
        // start as awake 
        this.awake = true;
        this.direction = direction;
        this.canHit = true;
    }

    /**
     * Act - do whatever the Pedestrian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        // call super act
        super.act();
        
        // If there is a v
        if (awake){
            if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null){
                setLocation (getX(), getY() + (int)(speed*direction));
            }
            if (direction == -1 && getY() < 100 || direction == 1 && getY() > 550){
                getWorld().removeObject(this);
                return;
            }
        }
    }

    /**
     * Method to cause this Pedestrian to become knocked down - stop moving, turn onto side
     */
    
    public int getDirection(){
        return direction;
    }
    
    public void knockDown () {
        speed = 0;
        setRotation (90);
        awake = false;
    }

    /**
     * Method to allow a downed Pedestrian to be healed
     */
    public void healMe () {
        speed = maxSpeed;
        setRotation (0);
        awake = true;
    }
    
    public boolean isAwake () {
        return awake;
    }
    
    public boolean canBeHit(){
        return canHit;
    }
}
