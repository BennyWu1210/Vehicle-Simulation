import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The Ambulance subclass
 */
public class Ambulance extends Vehicle
{
    public Ambulance(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        maxSpeed = 2.5;
        speed = maxSpeed;
    }

    /**
     * Act - do whatever the Ambulance wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        drive();
        checkHitPedestrian();
        if (checkEdge()){
            getWorld().removeObject(this);
        }

    }

    public boolean checkHitPedestrian () {
        
        // Take the pedestrian around a certain range
        List<Pedestrian> lst = getObjectsInRange((int)(getImage().getWidth()/2.3), Pedestrian.class);
        
        for (Pedestrian p: lst){
            if (p != null && !p.isAwake()){
                p.healMe();
            }
        }
        
        return lst.size() > 0;
    }
}
