import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the superclass for Vehicles.
 * 
 */
public abstract class Vehicle extends SuperSmoothMover
{
    protected double maxSpeed;
    protected double speed;
    protected int direction; // 1 = right, -1 = left
    protected boolean moving;
    protected boolean snowy;
    protected int yOffset;
    protected VehicleSpawner origin;
    protected int stoppedTick;
    
    protected abstract boolean checkHitPedestrian ();

    public Vehicle (VehicleSpawner origin) {
        this.origin = origin;
        moving = true;
        if (origin.facesRightward()){
            direction = 1;
            
        } else {
            direction = -1;
            getImage().mirrorHorizontally();
        }
    }
    
    public void addedToWorld (World w){
        setLocation (origin.getX() - (direction * 100), origin.getY() - yOffset);
    }

    /**
     * A method used by all Vehicles to check if they are at the edg
     */
    protected boolean checkEdge() {
        if (direction == 1){
            if (getX() > getWorld().getWidth() + 200){
                return true;
            }
        } else {
            if (getX() < -200){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that deals with movement. Speed can be set by individual subclasses in their constructors
     */
    public void drive() 
    {
        // Ahead is a generic vehicle - we don't know what type BUT
        // since every Vehicle "promises" to have a getSpeed() method,
        // we can call that on any vehicle to find out it's speed
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        if (this.getClass() != Plane.class){
            if (ahead == null && !snowy || ahead != null && ahead.getClass() == Plane.class) // make sure the groud vehicles do not follow the plane in front of them
            {
                speed = maxSpeed;
            } else if (ahead == null && snowy){
                speed = maxSpeed / 3;
            } else {
                speed = ahead.getSpeed();
            } 
        }
        move (speed * direction);
    }   
    
    public void Explode(){
        
    }

    public void setMaxSpeed(double speed){
        this.maxSpeed = speed;
    }
    /**
     * An accessor that can be used to get this Vehicle's speed. Used, for example, when a vehicle wants to see
     * if a faster vehicle is ahead in the lane.
     */
    public double getSpeed(){
        return this.speed;
    }
    
    public double getMaxSpeed(){
        return this.maxSpeed;
    }
    
    public void setSnowState(boolean snowState){
        this.snowy = snowState;
    }
}
