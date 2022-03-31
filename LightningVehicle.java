import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The Lightning Vehicle subclass
 */



public class LightningVehicle extends Vehicle
{
    protected int hitTime, hitSpeed;
    protected int radius;
    protected Pedestrian target;
    
    
    public LightningVehicle(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        this.maxSpeed = 1 + (Math.random() * 0.2);
        this.speed = maxSpeed;
        this.yOffset = 0;
        this.hitSpeed = 300;
        this.hitTime = hitSpeed;
        this.radius = 120;
        getImage().scale(120, 80);
    }

    public void act()
    {
        // Check hit pedestrian
        checkHitPedestrian();
        

        checkHitPedestrian();

        // Keep track of its target for lightning strike
        if (target == null || hitTime == 0){
            hitTime = hitSpeed;
            List<Pedestrian> pedList = getObjectsInRange(radius, Pedestrian.class);
            target = pedList.size() > 0 ? pedList.get(0) : null;
            if (target != null){
                Effect lightning = new LightningStrike(target.getX(), target.getY() - 30, target.getX(), target.getY());
                getWorld().addObject(lightning, getX(), getY() - yOffset);
            }
        }
        hitTime --;
        
        // Calls super's act method
        super.act();

    }
    

    public boolean checkHitPedestrian () {
        
        return false;
    }
}
