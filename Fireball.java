import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Effect
{
    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
  
    private boolean hasTarget;
    private Pedestrian target;
    private int[] destination;
    private int travelTime, speed;
    private GreenfootSound sound;
    
    public Fireball(int x, int y){
        super(x, y);
        this.speed = 3;
        this.isGif = true;
        
        this.gifImage = new GifImage("flame02.gif");
        this.gifImageList = gifImage.getImages();
        this.gifChangeRate = 5;
        this.gifCounter = this.gifChangeRate;
        this.gifIndex = 0;
        
        this.sound = new GreenfootSound("Fireball_sound.mp3");
        this.sound.setVolume(30);
        
        // Set size for all explosion effects
        
        for (GreenfootImage gfi: gifImageList){
            gfi.scale(30, 30);
        }
    }
    /**
     * Throws fireball towards enemy
     */
    public void act() 
    {
        this.sound.play();
        travelTime ++;
        
        Pedestrian touched = (Pedestrian)getOneIntersectingObject(Pedestrian.class);
        if (touched != null && touched.getClass() != Hero.class){
            touched.knockDown();
            disappear();
            getWorld().removeObject(this);
            return;
        }
        if(hasTarget && target.getWorld() != null)
        {
            
            if(travelTime > 90)
            {

                disappear();
                getWorld().removeObject(this);
                return;
            }
            

            destination[0] = target.getX();
            destination[1] = target.getY();
            move(target.getX(), target.getY());
        }
        else
        {
            if(travelTime > 90 || target.getWorld() != null && intersects(target))
            {
                
                disappear();
                getWorld().removeObject(this);
                return;

            }
            if(destination[0]!=0)
            {
                move(destination[0], destination[1]);
            }
            else
            {
                getWorld().removeObject(this);
            }
        }
        super.act();

    }    
    
    public void setTarget(Pedestrian ped){
        hasTarget = true;
        target = ped;
        destination = new int[]{target.getX(), target.getY()};
    }
    
    public void disappear(){
        if (target.getWorld() != null) target.knockDown();
        Effect explosion = new Explosion(getX(), getY());
        getWorld().addObject(explosion, getX(), getY());
    }
    
    public void move(int x, int y){
        double d = distanceFrom(x, y);
        double blocks = d/speed;
        double xd = (x - getX())/blocks;
        double yd = (y - getY())/blocks;
        setLocation(getX()+(int)xd, getY()+(int)(yd+0.5));
    }
}
