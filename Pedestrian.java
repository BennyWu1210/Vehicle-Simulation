import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pedestrians that try to walk across the street
 */
public abstract class Pedestrian extends SuperSmoothMover
{
    protected double speed;
    protected double maxSpeed;
    protected int direction;
    protected boolean awake;
    protected boolean canHit;
    protected int knockDownTime;
    
    
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
        } else{
            knockDownTime ++;
        }
        
        if (knockDownTime > 300){
            knockDownTime = 0;
            healMe();
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
        knockDownTime = 0;
    }

    /**
     * Method to allow a downed Pedestrian to be healed
     */
    public void healMe () {
        speed = maxSpeed;
        // Heart effect
        Effect a = new Heart(getPreciseX() + 10, getPreciseY() - 10);
        getWorld().addObject(a, (int)getPreciseX() + 10, (int)getPreciseY() - 10);
        setRotation (0);
        awake = true;
    }
    
    /*
     * Check if the pedestrian is awake
     */
    public boolean isAwake () {
        return awake;
    }
    
    /*
     * Check if it is immune to attacks
     */
    public boolean canBeHit(){
        return canHit;
    }
}
