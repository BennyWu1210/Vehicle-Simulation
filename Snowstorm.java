import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Rain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snowstorm extends Effect
{
    
    private int maxShift, xShift, yShift;
    private int midX, midY;
    private final int MAX_TICK = 450;
    public Snowstorm(int x, int y){
        // Calls super constructor, then set ticks (duration of snow storm) as MAX_TICK
        super(x, y);
        setTick(MAX_TICK);
        maxShift = 150;
    }
    
    public void addedToWorld (World w){
        // create an image that's a little bit bigger than the World
        //image = new GreenfootImage (w.getWidth() + 100, w.getHeight() + 100);
        image = drawSnowStorm(w.getWidth() + 200, w.getHeight() + 280, 100);
        setImage(image);
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) w.getObjects(Vehicle.class);
        for (Vehicle v : vehicles){
            v.snowy = true;
        }
        midX = getX() + (int)Math.random() * 10;
        midY = getY();
    }
    
    public void act()
    {
        
        // Recover the speed of all vehicles just as it ends
        if (actCounter < 2){
            ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) getWorld().getObjects(Vehicle.class);
            for (Vehicle v : vehicles){
                v.snowy = false;
            }
        }
        
        // The snow moves around
        if (getX() == midX){
            midX = getX() + (int)(Math.random() * 10);
        }
        
        if (actCounter % 2 == 0) setLocation(getX() + (midX - getX()) / 2, getY() + 1);
        
        // Calls super's act method
        super.act();
        
        // Slowy fade in
        if (MAX_TICK - actCounter < 120){
            getImage().setTransparency((MAX_TICK - actCounter) * 2);
        } 
        
    }
    
    /**
     * severity should be 1-100. 100 will be almost completely white
     */
    public static GreenfootImage drawSnowStorm (int width, int height, int severity){

        Color[] swatch = new Color [32];
        int red = 128;
        for (int i = 0; i < swatch.length; i++){
            swatch[i] = new Color (red, 240, 255);
            red+=2;
        }

        GreenfootImage temp = new GreenfootImage (width, height);
        // Run this loop one time per "severity"
        for (int i = 0; i < severity; i++){
            for (int j = 0; j < 100; j++){ // draw 100 dots

                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(32);
                int randTrans = Greenfoot.getRandomNumber(50) + 205; // almost opaque for dots
                //temp.setColor (swatch[randColor]);
                 temp.setColor(Color.WHITE);
                temp.setTransparency(randTrans);
                // random locations for our dot
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);
                temp.drawLine (randX, randY, randX, randY); // silly way to draw a dot..
            }
        }
        for (int i = 0; i < severity; i++){
            for (int j = 0; j < 100; j++){ // draw 100 circles

                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(32);
                int randTrans = Greenfoot.getRandomNumber(50) + 90; // around half transparent
                temp.setColor (swatch[randColor]);
                temp.setTransparency(randTrans);
                // random locations for our dot
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);
                int randSize = Greenfoot.getRandomNumber (5) + 1;
                temp.fillOval (randX, randY, randSize, randSize); // silly way to draw a dot..
            }
        }

        return temp;
    }
}
