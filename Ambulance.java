import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The Ambulance subclass
 */
public class Ambulance extends Vehicle
{
    public Ambulance(VehicleSpawner origin){
        // call the superclass' constructor first
        super (origin); 
        
        // Set speed
        this.maxSpeed = this.speed = 4 + (int)(Math.random() * 2.5);
    }

    /**
     * Act - do whatever the Ambulance wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Check hit pedestrian
        checkHitPedestrian();
        // Calls super's act method
        super.act();

    }

    public boolean checkHitPedestrian () {
        
        // Take the pedestrian around a certain range
        List<Pedestrian> lst = getObjectsInRange((int)(getImage().getWidth()/2), Pedestrian.class);
        
        for (Pedestrian p: lst){
            if (p != null && !p.isAwake()){
                // Heals the pedestrian
                p.healMe();
            }
        }
        
        return lst.size() > 0;
    }
}
