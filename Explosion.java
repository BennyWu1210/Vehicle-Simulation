import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Effect
{
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Explosion(int x, int y){
        super(x, y, 10);
        this.isGif = true;
        
        this.gifImage = new GifImage("explosion01.gif");
        this.gifImageList = gifImage.getImages();
        this.gifChangeRate = 5;
        this.gifCounter = this.gifChangeRate;
        this.gifIndex = 0;
        
        // Set size for all explosion effects
        
        for (GreenfootImage gfi: gifImageList){
            gfi.scale(50, 50);
        }
    }
    public void act()
    {
         
        super.act();
        
        
    }
    
    public GreenfootImage getImage(){
        return gifImageList.get(gifIndex);
    }
}
