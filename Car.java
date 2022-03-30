import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    
    public Car(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 2 + (Math.random() * 5.5);
        speed = maxSpeed;
        yOffset = 0;
    }

    public void act()
    {
        drive(); 
        checkHitPedestrian();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * When a Car hit's a Pedestrian, it should knock it over
     */
    public boolean checkHitPedestrian () {
        
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 4, Pedestrian.class);
        
        if (p != null && p.canBeHit()){
            p.knockDown();
            return true;
        }
        return false;
    }
}
