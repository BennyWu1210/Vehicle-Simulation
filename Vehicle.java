import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the superclass for Vehicles.
 * 
 */
public abstract class Vehicle extends SuperSmoothMover
{
    protected double maxSpeed;
    protected double speed;
    protected int direction, yDir; // 1 = right/down, -1 = left/up
    protected boolean moving, changingLane;
    protected boolean snowy;
    protected int yOffset;
    protected VehicleSpawner origin;
    protected int stoppedTick, changeLaneTick;
    protected int lane;
    protected Rectangle up, down; // collision detections
    protected int yPos; // y-position of current lane
    protected GreenfootSound honk;
    
    protected abstract boolean checkHitPedestrian ();

    public Vehicle (VehicleSpawner origin) {
        this.origin = origin;
        this.moving = true;
        this.changeLaneTick = 30;
        if (origin.facesRightward()){
            direction = 1;
            
        } else {
            direction = -1;
            getImage().mirrorHorizontally();
        }
        
        // Sets sound effect when the vehicle switches lanes
        honk = new GreenfootSound("car_horn.wav");
        honk.setVolume(15);
    }
    
    public void addedToWorld (World w){
        setLocation (origin.getX() - (direction * 100), origin.getY() - yOffset);
    }

    public void act(){
        // Stop if there is a hero nearby (scary!)
        if (getObjectsInRange(3, Hero.class).size() != 0){
            moving = false;
            stoppedTick = 30;
        }
        
        // Controls the movement
        if (moving) {
            drive();
        }
        else if (!moving){
            if (stoppedTick == 0){
                moving = true;
                speed = maxSpeed;
            } else{
                stoppedTick --;
            }

        } 
     
        // Remove this from world once it hits edge
        if (checkEdge()){
            getWorld().removeObject(this);
        }
    }
    /**
     * A method used by all Vehicles to check if they are at the edg
     */
    protected boolean checkEdge() {
        if (direction == 1){
            if (getX() > getWorld().getWidth() + 200){
                return true;
            }
        } else {
            if (getX() < -200){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that deals with movement. Speed can be set by individual subclasses in their constructors
     */
    public void drive() 
    {
        // Ahead is a generic vehicle - we don't know what type BUT
        // since every Vehicle "promises" to have a getSpeed() method,
        // we can call that on any vehicle to find out it's speed
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        
        if (this.getClass() != Plane.class){
            if (ahead == null && !snowy || ahead != null && ahead.getClass() == Plane.class) // make sure the groud vehicles do not follow the plane in front of them
            {
                speed = maxSpeed;
            } else if (ahead == null && snowy){
                speed = maxSpeed / 2.5;
            } else if (ahead.getClass() != Plane.class){
                
                speed = ahead.getSpeed();
                if (Greenfoot.getRandomNumber(2) == 0) honk.play();
                if (changeLaneTick < 0){
                    changeLane();
                    changeLaneTick = 30;
                }

            } 
            if (changingLane){
                changeLane();
                changeLaneTick = 30;
            }
            changeLaneTick --;
        }
        move (speed * direction);
        if (up != null && down != null) {
            up.setLocation(getX(), yPos  - 50);
            down.setLocation(getX(), yPos + 50);
        }
        
        // Debugging code: show whether the vehicle can change lanes
        // THIS IS VERY COOL!!!
        
        /*
        if (!up.canTurn()) up.setColor(220, 10, 10);
        else up.setColor(10, 220, 10);
        if (!down.canTurn()) down.setColor(220, 10, 10) ;
        else down.setColor(10, 220, 10);
        */
        
    }   
    
    /*
     * Change lane method! A very simple but also effective algorithm for lane changes! 
     * It only needs to detect two cases (up and down) rather than checking for special cases
     */
    public void changeLane(){
        if (changingLane){
            VehicleWorld vw = (VehicleWorld)getWorld();
            if (yDir == 1 && getY() + yDir * 2 > vw.getSpawnerPos(lane + 1) - yOffset) {
                
                changingLane = false;
                yPos = vw.getSpawnerPos(lane + 1);
                lane ++;
                yDir = 0;
                setLocation(getX(), yPos - yOffset);
                up.setLocation(getX(), yPos  - 50);
                down.setLocation(getX(), yPos + 50);
            } else if (yDir == -1 && getY() + yDir * 2 < vw.getSpawnerPos(lane - 1) - yOffset) {
                changingLane = false;
                yPos = vw.getSpawnerPos(lane - 1);
                lane --;
                yDir = 0;
                setLocation(getX(), yPos - yOffset);
                up.setLocation(getX(), yPos - 50);
                down.setLocation(getX(), yPos + 50);
            }
            setLocation(getX() + 0.5, getY() + yDir * 2);
        }
        else if (!changingLane && up.canTurn()){
            System.out.println("UP");
            changingLane = true;
            yDir = -1;
            
        } else if (!changingLane && down.canTurn()){
            System.out.println("DOWN");
            changingLane = true;
            yDir = 1;
            // down.setColor(10, 220, 10);
        }

        
    }

    public void setMaxSpeed(double speed){
        this.maxSpeed = speed;
    }
    /**
     * An accessor that can be used to get this Vehicle's speed. Used, for example, when a vehicle wants to see
     * if a faster vehicle is ahead in the lane.
     */
    public double getSpeed(){
        return this.speed;
    }
    
    public double getMaxSpeed(){
        return this.maxSpeed;
    }
    
    public void setSnowState(boolean snowState){
        this.snowy = snowState;
    }
    
    /*
     * Intialize the detection boxes for this particular vehicle (up adn down)
     */
    public void setLane(int lane){
        // Set collision boxes (up and down) for lane change
        this.lane = lane;
        VehicleWorld vw = (VehicleWorld)getWorld();
        yPos = vw.getLanePos(lane);
        
        up = new Rectangle(0, 0, getImage().getWidth() - 5, 5);
        down = new Rectangle(0, 0, getImage().getWidth() - 5, 5);
        vw.addObject(up, getX(), yPos - 50); vw.addObject(down, getX(), yPos + 50);
    }
    
    
}
