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
    public LightningStrike(){
        super(-1, -1);
        this.isGif = true;
        this.gifImage = new GifImage("lightningStrike.gif");
        this.gifImageList = gifImage.getImages();
        this.gifChangeRate = 5;
        this.gifCounter = this.gifChangeRate;
        this.gifIndex = 0;
        this.sound = new GreenfootSound("zap_sound.mp3");
        this.sound.setVolume(30);
        
    }
    
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
        this.sound.play();
        super.act();        
        
    }
    
    public void strike(int[] start, int[] end)
    {
        setLocation(end[0], end[1]);
        start[0] = end[0] + 50;
        int xDiff = end[0] - start[0];
        int yDiff = start[1] - end[1];
        /*
        double angle = 0;
        if((xDiff>0 && yDiff>0)) //CAST Rule
        {
            angle = 180-57.3*Math.atan((double)Math.abs(yDiff)/Math.abs(xDiff));
        }
        else if (xDiff<0 && yDiff>0)
        {
            angle = 57.3*Math.atan((double)Math.abs(yDiff)/Math.abs(xDiff));
        }
        
        else if (xDiff<0 && yDiff<0)
        {
            angle = 360-57.3*Math.atan((double)Math.abs(yDiff)/Math.abs(xDiff));
            
        }
        else if((xDiff>0 && yDiff<0) )
        {
            angle = 180+57.3*Math.atan((double)Math.abs(yDiff)/Math.abs(xDiff));
        }
        */
        for(GreenfootImage img: gifImageList)
        {
            img.scale(200, 200);
            img.rotate((int)30);
        }
        
    }
    
}
