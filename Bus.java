import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Bus subclass
 */
public class Bus extends Vehicle
{
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        //Set up values for Bus
        maxSpeed = 2.5 + (Math.random() * 3.75);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = getImage().getHeight()/2 - 20;
        
    }
    
  
    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
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
        
        int halfWidth = (int)(getImage().getWidth() / 2.0);
        for (int x = -halfWidth; x <= halfWidth; x++){
            
            // Check for pedestrians nearby, if there are any then take them away!
            Pedestrian p = (Pedestrian)getOneObjectAtOffset(x, yOffset, Pedestrian.class);
            
            if (p != null && p.isAwake() && p.canBeHit() && p.getDirection() == -1){
                Effect a = new AddOne(getPreciseX(), getPreciseY());
                getWorld().addObject(a, (int)p.getPreciseX(), (int)p.getPreciseY());
                getWorld().removeObject(p);
                moving = false;
                speed = 0;
                stoppedTick = 40;
                return true;
            }
            
            p = (Pedestrian)getOneObjectAtOffset(x, -yOffset, Pedestrian.class);
            
            if (p != null && p.isAwake() && p.canBeHit() && p.getDirection() == 1){
                Effect a = new AddOne(getPreciseX(), getPreciseY());
                getWorld().addObject(a, (int)p.getPreciseX(), (int)p.getPreciseY());
                getWorld().removeObject(p);
                moving = false;
                speed = 0;
                stoppedTick = 40;
                return true;
            }
            
        }
        
        
        return false;
    }

}
