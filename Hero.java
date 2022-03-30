import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Pedestrian
{
    private List<Pedestrian> targets;
    private int fireTime;
    public Hero(int direction){
        // call super constructor
        super(direction);
        
        // set a random speed and direction
        this.maxSpeed = this.speed = 1 + (Math.random() * 1);
        this.canHit = false;
        this.targets = new ArrayList<Pedestrian>();
        this.fireTime = 50;
        setImage("hero.png");
        getImage().scale(40, 90);
        
    }
    public void act()
    {
        if (fireTime == 0){
            fireTime = 50;
            
            targets = getObjectsInRange(80, Pedestrian.class);
            for (Pedestrian p: targets){
                if (p.getClass() == Hero.class || p.getClass() == RunningMan.class) continue;
                Fireball fire = new Fireball(getX(), getY());
                fire.setTarget(p);
                getWorld().addObject(fire, getX(), getY());
            }
            
            targets = getIntersectingObjects(Pedestrian.class);
            for (Pedestrian p: targets){
                if (p.getClass() == Hero.class || p.getClass() == RunningMan.class) continue;
                p.knockDown();
            }
        }
        fireTime --;
        super.act();
    }
    
    public void knowckDown(){
        setLocation(getX() - 30 + Math.random() * 60, getY() + Math.random() * 40);
    }
}
