import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <h1>The new and vastly improved 2022 Vehicle Simulation Assignment.</h1>
 * <p> This is the first redo of the 8 year old project. Lanes are now drawn dynamically, allowing for
 *     much greater customization. Pedestrians can now move in two directions. The graphics are better
 *     and the interactions smoother.</p>
 * <p> The Pedestrians are not as dumb as before (they don't want straight into Vehicles) and the Vehicles
 *     do a somewhat better job detecting Pedestrians.</p>
 *     
 * Improved Vehicle Simulation created by Benny :D
 * Features: 
 * - "Businessman": Don't underestimate these seemingly old men (took a while to draw the figure)! 
 *    They are extremely wealthy and drop gold coins wherever they go. However, being slow as they are,
 *    they are the worst victim of traffic accidents!
 *    
 * - "Hero": They are extremely powerful figures - whenever a vehicle or pedestrian gets too close within their 
 *    range, they will shoot out deadly fireballs at them and knock them down!
 *    
 * - "RunningMan": These people just can't stop running. They run around randomly and have the ability to excape 
 *    all attacks and car accidents!
 *   
 * - "Soldier": Whenever there are no immediate targets (ex. the Hero), the soldiers fire at random directions. 
 *    Just as useless as the military is in all hollywood movies, aren't they?
 *    
 * - "Ambulance": They will revive all the people who have been knocked down - accompanied by a cool effect too!
 * 
 * - "Bus": Buses will stop and pick up all pedestrians along its way - not the Running Mans and Heroes though, 
 *    they prefer to do some exercises.
 *    
 * - "Car": Extremely fast vehicles - They will knock down the poor victims that get on its way.
 * 
 * - "Lightning Vehicle": Yes, this is a variation of the "Tesla" from the dsupercell Universe (I drew this!). 
 *    They move extremely slow, but they have the ability to summon lightning from the sky and release them to 
 *    nearby pedestrians!
 *    
 * - "Plane": Just some planes flying around - nobody wants to get stucked in a traffic jam.
 *    
 *    
 * -  Other cool effects: I have implemented effects such as "Explosion" whenever things get blown up,  
 *   "AddOne" when a pedestrain enters a bus, "Heart" when a pedestrain gets revived (either by themselves or by an ambulance). 
 *    I also made the snowstorm start at random intervals to make the simulation more unique.
 *    
 *    I'd also like to point out the implemention of the change lane algorithm because I AM SUPER PROUD OF IT :D 
 *    I mentioned this idea with you during class and it worked out perfectly. You can uncomment the debugging code
 *    in Vehicle's drive method as well as change the transparency in Rectangle's constructor to 30, you will see the 
 *    detecion boxes in the simulation immediately. It is just so satisfying to see how precise and smooth the algorithm runs.
 *    
 *    Enjoy the game!
 *    
 *    Credits:
 *    - Airplane sound effect: https://www.freesoundeffects.com/free-sounds/airplane-10004/20/tot_sold/20/2/
 *    - Ambient noice: https://www.youtube.com/watch?v=cX-1TfLP_y0, account: Ninja Nomaj
 *    - Car honk sound effect: https://www.youtube.com/watch?v=FQc5zRy6wBU, account: SoundEffects
 *    - Fire ball sound effect: https://www.youtube.com/watch?v=FJGdoPmspiU, account Free audio zone
 *    - Lightining sound effect: https://www.youtube.com/watch?v=TEFn1sB_XzI, account: Sandra Mitchell
 *    - Explosion sound effect: https://www.youtube.com/watch?v=bhZs3ALdL7Y, account: LibraryDealer
 *    - Background image: https://www.vectorstock.com/royalty-free-vector/forest-game-background-2d-application-vector-9827363
 *    - Add One image: https://commons.wikimedia.org/wiki/File:%2B1_ICON.png
 *    - Running man gif: https://dribbble.com/shots/2995181-Red-Bearded-Running-And-Jumping-Game-Character-Gif
 *    - Fire ball gif: https://en.picmix.com/stamp/comet-fire-ball-planet-universe-universum-univers-tube-deco-animation-gif-anime-animated-planete-planete-1606910
 *    - Explosion gif: https://www.reddit.com/r/NoStupidQuestions/comments/jpeyjd/where_does_the_infamous_realistic_explosion/
 *    - Gold coin image: https://tenor.com/view/coin-spin-gold-pixel-art-gif-16374894
 *    - Heart image: https://imgbin.com/png/u96AGs9T/minecraft-pocket-edition-minecraft-story-mode-video-games-heart-png
 *    
 *    Other characters/images are either provided by Greenfoot, from Mr. Cohen, or created by me.
 * 
 * @author Benny
 * @version Mar 30, 2022
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
    private int weatherTicks, titleTicks;
    private boolean snowy;
    private Effect weather;
    private Label title;
    private int vehicleCount;

    // Create collision blocks for lane change
    private Rectangle midBlock, lowerBlock, upperBlock;
    
    // Background ambient sound
    private GreenfootSound ambientMusic;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public VehicleWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false); 

        setPaintOrder (Label.class, Effect.class, Plane.class, LightningVehicle.class, Hero.class, Bus.class, Car.class, Pedestrian.class, Ambulance.class);

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
        weatherTicks = 500;
        
        // Create title
        title = new Label("Vehicle Simulation", 80);
        title.setFillColor(Color.BLACK.brighter());
        addObject(title, 400, 300);
        titleTicks = 150;
        
        // Create collision blocks for lane change
        
        lowerBlock = new Rectangle(-25, 0, 1000, 45);
        midBlock = new Rectangle(-25, 0, 1000, 45);
        upperBlock = new Rectangle(-25, 0, 1000, 45);
        
        addObject(lowerBlock, 400, 575);
        addObject(midBlock, 400, 395);
        addObject(upperBlock, 400, 210);
        
        // set traffic noice
        if (ambientMusic == null) ambientMusic = new GreenfootSound("Traffic.wav");
        
        
        
    }
    public void act () {
        
        // Calculate titleTicks for the initial display of "Vehicle Simulation"
        if (titleTicks < 100 && titleTicks > -50){
            title.setLocation(title.getX(), title.getY() - 4);
        }
        if (titleTicks > -50) titleTicks --;
        else spawn();
        
        // Only start the music once the header is gone
        if (titleTicks == -30){
            ambientMusic.setVolume(30);
            ambientMusic.playLoop();
        }
        
        // Create a snow storm after a random interval between 1000 to 1650 acts
        if (weatherTicks == 0){
            weather = new Snowstorm(getWidth()/2, getHeight()/2);
            addObject(weather, getWidth()/2, getHeight()/2);
            snowy = true;
            weatherTicks = (int)(1000 + Math.random() * 650);
        }
        
        // Keep track of the state "snowy" for vehicle spawning purposes
        if (weather == null || weather.getWorld() == null){
            snowy = false;
        }
        
        weatherTicks --;
    }

    public void started(){
        // Start the music and set its volume to 30
        if (titleTicks < 0) {
            ambientMusic.setVolume(30);
            ambientMusic.playLoop();
        }
    }
    
    public void stopped(){
        // pause music
        ambientMusic.stop();
    }
    
    private void spawn () {
        // Chance to spawn a vehicle
        if (Greenfoot.getRandomNumber (32) == 0){
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
                    if (Greenfoot.getRandomNumber(4) == 0) vehicleAdded = new Plane(laneSpawners[lane]);
                    else if (Greenfoot.getRandomNumber(4) == 0) vehicleAdded = new LightningVehicle(laneSpawners[lane]);
                    else return;
                } else{
                    if (Greenfoot.getRandomNumber(3) == 0) vehicleAdded = new Car(laneSpawners[lane]);
                    else vehicleAdded = new Ambulance(laneSpawners[lane]);
                    
                }
                // Incoportate the weather condition
                vehicleAdded.setSnowState(snowy);
                addObject(vehicleAdded, 0, 0);
                vehicleAdded.setLane(lane);
                vehicleCount ++;
            }
            
            
            
            
        }
        
        // Chance to spawn a Pedestrian
        
        if (Greenfoot.getRandomNumber (45) == 0){
            
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
            } else if (pedestrianType == 3 && Greenfoot.getRandomNumber(4) == 0){
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
    
    /*
     * Gets the lane position
     */
    public int getLanePos(int lane){
        return lanePositionsY[lane];
    }
    
    /*
     * Similar to getLanePos, this methods gets the spawner y-positions.
     */
    public int getSpawnerPos(int lane){
        return laneSpawners[lane].getY();
    }
    
    // Check if the blocks has intersected any detection blocks (for lane change purposes);
    public boolean intersectBlock(Rectangle block){
        return upperBlock.intersects(block) || midBlock.intersects(block) || lowerBlock.intersects(block);
    }
}
