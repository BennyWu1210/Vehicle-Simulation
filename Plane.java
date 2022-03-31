import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plane extends Vehicle
{
    private GreenfootSound sound;
    public Plane(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        //Set up values for Bus
        maxSpeed = 8.5 + (Math.random() * 4);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 15;
        
        // set sound
        sound = new GreenfootSound("airplane.wav");
        sound.setVolume(68);
    }
    

    public void act()
    {
        
        // Play airplane sound effect
        sound.play();
        
        // Fly the plane!
        drive();
        
        // Call super's act method
        super.act();
    }
    
    public boolean checkHitPedestrian(){
        // unnecessary method
        return false;
    }
}
