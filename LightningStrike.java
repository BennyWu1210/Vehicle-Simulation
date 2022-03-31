import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * A type of projectile - lightning strike
 * 
 * @author (Benny Wu) 
 * 
 */
public class LightningStrike extends Effect
{
    
    private GreenfootSound sound;
    /*
     * Standar constructor
     */
    public LightningStrike(){
        // Calls super constructor, then create images
        super(-1, -1);
        this.isGif = true;
        this.gifImage = new GifImage("lightningStrike.gif");
        this.gifImageList = gifImage.getImages();
        this.gifChangeRate = 5;
        this.gifCounter = this.gifChangeRate;
        this.gifIndex = 0;
        this.sound = new GreenfootSound("zap_sound.mp3");
        this.sound.setVolume(22);
        
    }
    
    /*
     * Overload the first constructor if it requires a specific coordinate to strike at
     */
    public LightningStrike(int x1, int y1, int x2, int y2){
        super(-1, -1);
        this.isGif = true;
        this.gifImage = new GifImage("lightningStrike.gif");
        this.gifImageList = gifImage.getImages();
        this.gifChangeRate = 5;
        this.gifCounter = this.gifChangeRate;
        this.gifIndex = 0;
        this.sound = new GreenfootSound("zap_sound.mp3");
        this.sound.setVolume(18);
        strike(new int[]{x1, y1}, new int[]{x2, y2});
    }
    // Strikes enemy with lightning
     
    public void act()
    {
        // Play sound effect
        this.sound.play();
        
        // Calls super's act method
        super.act();        
        
    }
    
    /* 
     * Strike toward the destination
     */
    public void strike(int[] start, int[] end)
    {
        setLocation(end[0], end[1]);
        start[0] = end[0] + 50;
        int xDiff = end[0] - start[0];
        int yDiff = start[1] - end[1];
        
        // Rotate the images accordingly
        for(GreenfootImage img: gifImageList)
        {
            img.scale(200, 200);
            img.rotate((int)30);
        }
        
    }
    
}
