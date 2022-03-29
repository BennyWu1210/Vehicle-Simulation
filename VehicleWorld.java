import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>The new and vastly improved 2022 Vehicle Simulation Assignment.</h1>
 * <p> This is the first redo of the 8 year old project. Lanes are now drawn dynamically, allowing for
 *     much greater customization. Pedestrians can now move in two directions. The graphics are better
 *     and the interactions smoother.</p>
 * <p> The Pedestrians are not as dumb as before (they don't want straight into Vehicles) and the Vehicles
 *     do a somewhat better job detecting Pedestrians.</p>
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VehicleWorld extends World
{
    private GreenfootImage background;

    // Color Constants
    public static Color GREY_BORDER = new Color (108, 108, 108);
    public static Color GREY_STREET = new Color (88, 88, 88);
    public static Color YELLOW_LINE = new Color (255, 216, 0);

    // Instance variables / Objects
    private boolean twoWayTraffic, splitAtCenter;
    private int laneHeight, laneCount, spaceBetweenLanes;
    private int[] lanePositionsY;
    private VehicleSpawner[] laneSpawners;
    private int weatherTicks;
    private boolean snowy;
    private Effect weather;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public VehicleWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false); 

        setPaintOrder (Effect.class, Plane.class, LightningVehicle.class, Hero.class, Bus.class, Car.class, Pedestrian.class, Ambulance.class);

        // set up background
        background = new GreenfootImage ("background_image04.jpg");
        background.scale(800, 600);
        setBackground (background);
    
        
        // Set critical variables
        laneCount = 6;
        laneHeight = 48;
        spaceBetweenLanes = 6;
        splitAtCenter = true;
        twoWayTraffic = true;

        // Init lane spawner objects 
        laneSpawners = new VehicleSpawner[laneCount];

        // Prepare lanes method - draws the lanes
        lanePositionsY = prepareLanes (this, background, laneSpawners, 230, laneHeight, laneCount, spaceBetweenLanes, twoWayTraffic, splitAtCenter);

        
        // Init weather ticks
        weatherTicks = 300;
    }
    public void act () {
        spawn();
        
        if (weatherTicks == 0){
            weather = new Snowstorm(getWidth()/2, getHeight()/2);
            addObject(weather, getWidth()/2, getHeight()/2);
            snowy = true;
            weatherTicks = (int)(820 + Math.random() * 600);
        }
        
        if (weather == null || weather.getWorld() == null){
            snowy = false;
        }
        
        weatherTicks --;
    }

    private void spawn () {
        // Chance to spawn a vehicle
        if (Greenfoot.getRandomNumber (35) == 0){
            int lane = Greenfoot.getRandomNumber(laneCount);
            Vehicle vehicleAdded;
            if (!laneSpawners[lane].isTouchingVehicle()){
                int vehicleType = Greenfoot.getRandomNumber(4);
                if (vehicleType == 0){
                    vehicleAdded = new Car(laneSpawners[lane]);
                } else if (vehicleType == 1){
                    vehicleAdded = new Bus(laneSpawners[lane]);
                } else if (vehicleType == 2){
                    vehicleAdded = new Ambulance(laneSpawners[lane]);
                } else if (vehicleType == 3){
                    if (Greenfoot.getRandomNumber(2) == 1) vehicleAdded = new Plane(laneSpawners[lane]);
                    else vehicleAdded = new LightningVehicle(laneSpawners[lane]);
                } else{
                    if (Greenfoot.getRandomNumber(3) == 0) vehicleAdded = new Car(laneSpawners[lane]);
                    else vehicleAdded = new Ambulance(laneSpawners[lane]);
                    
                }
                vehicleAdded.setSnowState(snowy);
                addObject(vehicleAdded, 0, 0);
            }
            
            
            
            
        }

        // Chance to spawn a Pedestrian
        if (Greenfoot.getRandomNumber (20) == 0){
            
            int spawnDir = Greenfoot.getRandomNumber(2) == 0 ? 1 : -1;
            int xLocation = Greenfoot.getRandomNumber (600) + 100; // random between 99 and 699, so not near edges
            int yLocation = spawnDir == 1 ? 50 : 550;
            int pedestrianType = Greenfoot.getRandomNumber(4);
            
            if (pedestrianType == 0){
                addObject(new Soldier(spawnDir), xLocation, yLocation);
            } else if (pedestrianType == 1){
                addObject(new Businessman(spawnDir), xLocation, yLocation);
            } else if (pedestrianType == 2){
                addObject(new RunningMan(spawnDir), xLocation, yLocation);
            } else if (pedestrianType == 3 && Greenfoot.getRandomNumber(3) == 0){
                addObject(new Hero(spawnDir), xLocation, yLocation);
            }
            
            
                    

        }
    }

    /**
     * <p>The prepareLanes method is a static (standalone) method that takes a list of parameters about the desired roadway and then builds it.</p>
     * 
     * <p><b>Note:</b> So far, Centre-split is the only option, regardless of what values you send for that parameters.</p>
     *
     * <p>This method does three things:</p>
     * <ul>
     *  <li> Determines the Y coordinate for each lane (each lane is centered vertically around the position)</li>
     *  <li> Draws lanes onto the GreenfootImage target that is passed in at the specified / calculated positions. 
     *       (Nothing is returned, it just manipulates the object which affects the original).</li>
     *  <li> Places the VehicleSpawners (passed in via the array parameter spawners) into the World (also passed in via parameters).</li>
     * </ul>
     * 
     * <p> After this method is run, there is a visual road as well as the objects needed to spawn Vehicles. Examine the table below for an
     * in-depth description of what the roadway will look like and what each parameter/component represents.</p>
     * 
     * <pre>
     *                  <=== Start Y
     *  ||||||||||||||  <=== Top Border
     *  /------------\
     *  |            |  
     *  |      Y[0]  |  <=== Lane Position (Y) is the middle of the lane
     *  |            |
     *  \------------/
     *  [##] [##] [##| <== spacing ( where the lane lines or borders are )
     *  /------------\
     *  |            |  
     *  |      Y[1]  |
     *  |            |
     *  \------------/
     *  ||||||||||||||  <== Bottom Border
     * </pre>
     * 
     * @param world     The World that the VehicleSpawners will be added to
     * @param target    The GreenfootImage that the lanes will be drawn on, usually but not necessarily the background of the World.
     * @param spawners  An array of VehicleSpawner to be added to the World
     * @param startY    The top Y position where lanes (drawing) should start
     * @param heightPerLane The height of the desired lanes
     * @param lanes     The total number of lanes desired
     * @param spacing   The distance, in pixels, between each lane
     * @param twoWay    Should traffic flow both ways? Leave false for a one-way street (Not Yet Implemented)
     * @param centreSplit   Should the whole road be split in the middle? Or lots of parallel two-way streets? Must also be two-way street (twoWay == true) or else NO EFFECT
     * 
     */
    public static int[] prepareLanes (World world, GreenfootImage target, VehicleSpawner[] spawners, int startY, int heightPerLane, int lanes, int spacing, boolean twoWay, boolean centreSplit){
        // Declare an array to store the y values as I calculate them
        int[] lanePositions = new int[lanes];
        // Pre-calculate half of the lane height, as this will frequently be used for drawing.
        // To help make it clear, the heightOffset is the distance from the centre of the lane (it's y position)
        // to the outer edge of the lane.
        int heightOffset = heightPerLane / 2;

        // draw top border
        target.setColor (GREY_BORDER);
        target.fillRect (0, startY, target.getWidth(), spacing);

        // Main Loop to Calculate Positions and draw lanes
        for (int i = 0; i < lanes; i++){
            // calculate the position for the lane
            lanePositions[i] = startY + spacing + (i * (heightPerLane + spacing)) + heightOffset ;

            // draw lane
            target.setColor(GREY_STREET); 
            // the lane body
            target.fillRect (0, lanePositions[i] - heightOffset, target.getWidth(), heightPerLane);
            // the lane spacing - where the white or yellow lines will get drawn
            target.fillRect(0, lanePositions[i] + heightOffset, target.getWidth(), spacing);

            // Place spawners and draw lines depending on whether its 2 way and centre split
            if (twoWay && centreSplit){
                // first half of the lanes go rightward (no option for left-hand drive, sorry UK students .. ?)
                if ( i < lanes / 2){
                    spawners[i] = new VehicleSpawner(false, heightPerLane);
                    world.addObject(spawners[i], target.getWidth(), lanePositions[i]);
                } else { // second half of the lanes go leftward
                    spawners[i] = new VehicleSpawner(true, heightPerLane);
                    world.addObject(spawners[i], 0, lanePositions[i]);
                }

                // draw yellow lines if middle 
                if (i == lanes / 2){
                    target.setColor(YELLOW_LINE);
                    target.fillRect(0, lanePositions[i] - heightOffset - spacing, target.getWidth(), spacing);

                } else if (i > 0){ // draw white lines if not first lane
                    for (int j = 0; j < target.getWidth(); j += 120){
                        target.setColor (Color.WHITE);
                        target.fillRect (j, lanePositions[i] - heightOffset - spacing, 60, spacing);
                    }
                } 

            } else if (twoWay){ // not center split
                if ( i % 2 == 0){
                    spawners[i] = new VehicleSpawner(false, heightPerLane);
                    world.addObject(spawners[i], target.getWidth(), lanePositions[i]);
                } else {
                    spawners[i] = new VehicleSpawner(true, heightPerLane);
                    world.addObject(spawners[i], 0, lanePositions[i]);
                }

                // draw Grey Border if between two "Streets"
                if (i > 0){ // but not in first position
                    if (i % 2 == 0){
                        target.setColor(GREY_BORDER);
                        target.fillRect(0, lanePositions[i] - heightOffset - spacing, target.getWidth(), spacing);

                    } else { // draw dotted lines
                        for (int j = 0; j < target.getWidth(); j += 120){
                            target.setColor (YELLOW_LINE);
                            target.fillRect (j, lanePositions[i] - heightOffset - spacing, 60, spacing);
                        }
                    } 
                }
            } else { // One way traffic
                spawners[i] = new VehicleSpawner(true, heightPerLane);
                world.addObject(spawners[i], 0, lanePositions[i]);
                if (i > 0){
                    for (int j = 0; j < target.getWidth(); j += 120){
                        target.setColor (Color.WHITE);
                        target.fillRect (j, lanePositions[i] - heightOffset - spacing, 60, spacing);
                    }
                }
            }
        }
        // draws bottom border
        target.setColor (GREY_BORDER);
        target.fillRect (0, lanePositions[lanes-1] + heightOffset, target.getWidth(), spacing);

        return lanePositions;
    }
    
    

}
