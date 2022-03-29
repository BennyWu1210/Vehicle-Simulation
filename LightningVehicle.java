import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Lightning Vehicle subclass
 */
public class LightningVehicle extends Vehicle
{
    
    public LightningVehicle(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.5 + (Math.random() * 6);
        speed = maxSpeed;
        yOffset = 10;
    }

    public void act()
    {
        drive(); 
        checkHitPedestrian();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
    }
    

    public boolean checkHitPedestrian () {
        
        return false;
    }
}
