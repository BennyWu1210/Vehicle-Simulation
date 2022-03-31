import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rectangle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rectangle extends Actor
{
    /**
     * Act - do whatever the Rectangle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    // Set length and width
    private GreenfootImage image;
    private int x1, y1, x2, y2;
    public Rectangle(int x1, int y1, int x2, int y2){
        // Creates the rectangles for detection purposes
        this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
        this.image = new GreenfootImage(x2 - x1, y2 - y1);
        
        image.setColor(new Color(10, 220, 10, 0));
        image.fillRect(x1, y1, x2, y2);
        setImage(image);
    }
    
    /*
     * Checks if it has intersected another rectangle
     */
    public boolean intersects(Rectangle rect){
        return super.intersects(rect);
    }
    
    /*
     * Checks if it enables the vehicle to turn toward this particular direction
     */
    public boolean canTurn(){
        VehicleWorld vw = (VehicleWorld)getWorld();
        if (vw.intersectBlock(this)) {
            return false;
        }
        if (isTouching(Vehicle.class)) return false;
        return true;
    }
    
    // Sets the color of the rectangles for debugging purposes
    public void setColor(int r, int g, int b){
        image.setColor(new Color(r, g, b, 150));
        image.fillRect(x1, y1, x2, y2);
        setImage(image);
    }
    
    
    

    
}
