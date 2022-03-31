import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Effect
{
    private GreenfootSound sound;
    
    public Explosion(int x, int y){
        // Calls super constructor, then create image
        super(x, y, 10);
        this.isGif = true;
        
        this.gifImage = new GifImage("explosion01.gif");
        this.gifImageList = gifImage.getImages();
        this.gifChangeRate = 5;
        this.gifCounter = this.gifChangeRate;
        this.gifIndex = 0;
        
        // initialize sound effect
        this.sound = new GreenfootSound("bomb_sound.mp3");
        this.sound.setVolume(30);
        
        // Set size for all explosion effects
        for (GreenfootImage gfi: gifImageList){
            gfi.scale(50, 50);
        }
    }
    public void act()
    {
        // calls super's act method
        super.act();
        
        // play sound effect
        this.sound.play();
        
        
    }
    
    public GreenfootImage getImage(){
        return gifImageList.get(gifIndex);
    }
}
