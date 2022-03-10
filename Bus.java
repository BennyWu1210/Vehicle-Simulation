import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Bus subclass
 */
public class Bus extends Vehicle
{
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        //Set up values for Bus
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = getImage().getHeight()/2 + 3;
    }

    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (moving) {
            drive();
        }
        else if (!moving){
            if (stoppedTimer.millisElapsed() > 1000){
                moving = true;
                speed = maxSpeed;
            } 
            /* else{
                speed = maxSpeed * Math.abs(500 - stoppedTimer.millisElapsed()) / 5000.0;
            }
            */
            // drive();
        } 
        
        checkHitPedestrian();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
        
    }

    
    public boolean checkHitPedestrian () {
        
        int halfWidth = (int)(getImage().getWidth() / 2.0);
        for (int x = -halfWidth; x <= halfWidth; x++){
            // System.out.println(x);
            Pedestrian p = (Pedestrian)getOneObjectAtOffset(x, yOffset, Pedestrian.class);
            
            if (p != null && p.isAwake() && p.canBeHit() && p.getDirection() == -1){
                Animation a = new AddOne(getPreciseX(), getPreciseY());
                getWorld().addObject(a, (int)p.getPreciseX(), (int)p.getPreciseY());
                getWorld().removeObject(p);
                moving = false;
                speed = 0;
                stoppedTimer.mark();
                return true;
            }
            
            p = (Pedestrian)getOneObjectAtOffset(x, -yOffset, Pedestrian.class);
            
            if (p != null && p.isAwake() && p.canBeHit() && p.getDirection() == 1){
                Animation a = new AddOne(getPreciseX(), getPreciseY());
                getWorld().addObject(a, (int)p.getPreciseX(), (int)p.getPreciseY());
                getWorld().removeObject(p);
                moving = false;
                speed = 0;
                stoppedTimer.mark();
                return true;
            }
            
        }
        
        
        return false;
    }

}
