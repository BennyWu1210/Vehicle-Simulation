import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoldCoin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoldCoin extends Animation
{
    /**
     * Act - do whatever the GoldCoin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public GoldCoin(int x, int y){
        super(x, y);
        this.isGif = true;
        this.gifImage = new GifImage("goldCoin.gif");
        this.gifImageList = gifImage.getImages();
        this.gifChangeRate = 5;
        
        
    }
    
    public void act()
    {
        if (isGif){
            System.out.println("udfis");
            gifCounter --;
            if (gifCounter <= 0){
                getImage();
                gifCounter = gifChangeRate;
            }
        }

    }
    
    
}
