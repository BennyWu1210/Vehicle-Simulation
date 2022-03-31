import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoldCoin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoldCoin extends Effect
{
    /**
     * Act - do whatever the GoldCoin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public GoldCoin(int x, int y){
        // Calls super constructor, then create images
        super(x, y, 5);
        this.isGif = true;
        this.gifImage = new GifImage("goldCoin.gif");
        
        this.gifImageList = gifImage.getImages();
        this.gifChangeRate = 5;
        this.gifCycle = 2;
        
        
         // Set size for all gold coin effects
        
        for (GreenfootImage gfi: gifImageList){
            gfi.scale(40, 40);
        }
    }
    
    public void act()
    {
        // Calls super's act method
        super.act();
        
    }
    
    
}
