import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    
    public Car(VehicleSpawner origin) {
        // call the superclass' constructor
        super(origin); 
        
        // set speed and offset
        this.maxSpeed = this.speed = 2 + (Math.random() * 5.5);
        yOffset = 0;
    }

    public void act()
    {
        // Check hit pedestrian
        checkHitPedestrian();
        // Calls super's act method
        super.act();

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
