package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.*;

//TO DO LIST

//make funnier with outlandish things.


public class Game {

    //Create a new scanner.
   /** a scanner for this game **/ public static Scanner CONSOLE = new Scanner(System.in);
    /**
     * A HashMap to find locations.
     **/
    private HashMap<Integer, Location> locationFinder = new HashMap<Integer, Location>();
    /**
     * A HashMap to aid in the drop and take command
     **/
    private HashMap<Integer, Item> itemsMap = new HashMap<Integer, Item>();
    /**
     * The name of the file for the the save command
     **/
    private static final String FILE_NAME = "AdventureGameFileSave.txt";
    /**
     * Variable to store the number of actions a player has made.
     **/
    public static int amountOfActions = 0;

    /** The user's score **/ public static int score = 0;

    /**
     * Initialize the command int array outside the loop
     **/
    private int command[] = {-1, -1};

    /**
     * A list of all the locations in the game.
     **/
    private ArrayList<Location> locations = new ArrayList<Location>();

    /** An indicator to indicate if something has happened already. **/
    private int indicator[] = {1000,1000,1000,1000,1000,1000,1000,1000,1000};

    /**
    All the directions in the game that never change.
    **/
    public static final int NORTH  = 8;
    public static final int EAST  = 9;
    public static final int SOUTH = 10;
    public static final int WEST = 11;
    public static final int UP  = 12;
    public static final int DOWN  = 13;
    public static final int IN  = 14;
    public static final int OUT  = 15;

    /**
     * Create a Game.
     */
    public void Game() {
        this.locationFinder = new HashMap<Integer, Location>();
        this.itemsMap = new HashMap<Integer,Item>();
        this.locations = new ArrayList<Location>();
    }

    /**
     * The main driver of the program, where the Items and Locations are created
     */
    public void play() {

        //Create Items and add them to the itemsMap

        Item wallet = new Item("Wallet", "On the ground there is a black leather wallet. ", 200, Parser.find("WALLET"));
        itemsMap.put(wallet.getCode(), wallet);
        wallet.setUseMessage("Inside wallet you find a picture of you are your fiance at a halloween party hugging.\n" +
                "There is also a note that reads '4317 Bridgeview'\n");

        SunGlasses sunGlasses = new SunGlasses("SunGlasses", "There is a pair of red SunGlasses. ", 300, Parser.find("SUNGLASSES"));
        this.itemsMap.put(sunGlasses.getCode(), sunGlasses);

        Drink hangOverLiquid = new Drink("Liquid", "There is a little bottle of specially concocted\n" +
                "liquid to expedite the rate at which an individual can overcome a hangover.\n", 20, Parser.find("LIQUID"));
        itemsMap.put(hangOverLiquid.getCode(), hangOverLiquid);

        Shirt shirt = new Shirt("shirt", "There is a blue shirt hanging off the side of the bed. ", 1, Parser.find("SHIRT"), hangOverLiquid);
        itemsMap.put(shirt.getCode(), shirt);

        Ring ring = new Ring("rose gold wedding ring", "There is a ring here. ", 20, Parser.find("RING"));
        itemsMap.put(ring.getCode(), ring);

        Tunnel tunnel = new Tunnel("tunnel", "A long narrow tunnel has been built here. ", Double.NaN, Parser.find("TUNNEL"));
        itemsMap.put(tunnel.getCode(), tunnel);

        Shovel shovel = new Shovel("shovel", "There is a small shovel here. ", 3500, Parser.find("SHOVEL"), ring, tunnel);
        itemsMap.put(shovel.getCode(), shovel);

        CellPhone cellphone = new CellPhone("Iphone", "There is an Iphone here. ", 500, Parser.find("IPHONE"));
        itemsMap.put(cellphone.getCode(), cellphone);

        MetalDetector metalDetector = new MetalDetector("A silver metal-detector ", "A giant metal-detector is " +
                "here. ", 5000, Parser.find("METAL-DETECTOR"), ring);
        itemsMap.put(metalDetector.getCode(), metalDetector);

        Drink water = new Drink("water", "", Double.NaN, Parser.find("WATER"));
        itemsMap.put(water.getCode(), water);

        Item fireWood = new Item("fire wood", "There is a pile of fire wood here. ", 4000, Parser.find("WOOD"));
        itemsMap.put(fireWood.getCode(), fireWood);

        Flint flint = new Flint("flint", "There is flint here. ", 400, Parser.find("FLINT"), fireWood);
        itemsMap.put(flint.getCode(), flint);

        ZipLine zipLine = new ZipLine("Zip Line", "There is a zipLine here. ", Double.NaN, Parser.find("ZIPLINE"));
        itemsMap.put(zipLine.getCode(), zipLine);

        Vows vows = new Vows("Vows", "There is a piece of paper that is titled 'VOWS'. It can be used to write down your vows. ", 200, Parser.find("VOWS"));
        itemsMap.put(vows.getCode(), vows);

        Item bearTrap = new Item("Bear-Trap", "There is a bear-trap here that can be placed in a location to catch the beast. ", 1000, Parser.find("BEAR-TRAP"));
        itemsMap.put(bearTrap.getCode(), bearTrap);
        bearTrap.setUseMessage("The bear-trap has been placed. Make sure not to get caught in it yourself. ");

        BearMace bearMace = new BearMace("bear-mace", "There is a can of bear-mace here. ", 200, Parser.find("BEAR-MACE"));
        itemsMap.put(bearMace.getCode(), bearMace);

        CrossBow crossBow = new CrossBow("cross-bow","There is a cross-bow here. ",2000,Parser.find("CROSS-BOW"));
        itemsMap.put(crossBow.getCode(),crossBow);

        //Create Locations, add them to the locations list, and put items in them.


        Location heliPad = new Location("a helicopter pad", "You are standing on a helicopter pad. South of you is the top of the volcano. ", false, 0);
        locations.add(heliPad);

        Location topVolcano = new Location("", "You are now above the clouds. You have made it to the top of the volcano.\n" +
                "If you go east you will fall into the fiery pit! ", false, 1);
        locations.add(topVolcano);

        Location firePit = new Location("", "You fell into the fire pit! unfortunately you died a pretty painful death\n" +
                "but the worst thing is that you missed your wedding, and your wife ended up marrying your best friend,\n" +
                "if it makes you feel any better they named their first child after you. ", true, 2);
        locations.add(firePit);

        Location halfVolcano = new Location("", "You are half way up the volcano. ", false, 3);
        locations.add(halfVolcano);

        Location volcano = new Location("", "You are at the bottom of a volcano. ", false, 4);
        locations.add(volcano);

        Location clearing = new Location("the clearing", "You are at a clearing in the forest. The trees no longer cover the\n" +
                "sky. You can see a sign for a restaurant that reads 'Pun on a Bun'. In the distance you can hear a low rumble. ", false, 5);
        locations.add(clearing);

        Location bar = new Location("the bar", "You are inside a dingy bar, the walls are filled with old rock and roll " +
                "posters from the seventies.\nThe ground is littered with garbage. It looks like there was a huge " +
                "party here the night before.\nThere is a stairway leading upwards. ", true, 6);
        locations.add(bar);
        bar.addItem(cellphone);

        Door doorWay = new Door("", "You are at the top of the stairs. ", 7);
        locations.add(doorWay);

        Location attic = new Location("the attic", "You are in the attic of the restaurant. The room is dusty,\n" +
                "which makes it hard to breath. ", true, 8);
        locations.add(attic);
        attic.addItem(metalDetector);
        doorWay.setInsideRoom(attic);
        attic.addItem(crossBow);

        Location roofTop = new Location("the roof top", "You are on top of the roof of the attic. It is windy here.\n" +
                "You can see a huge volcano from here. The volcano is accessible from the ground. ", false, 9);
        locations.add(roofTop);
        roofTop.addItem(zipLine);

        Location campSite = new Location("the campsite", "You are standing at an abandoned camp site. There is still smoke " +
                "rising from the fire pit.\nThere are a bunch of empty beer bottles on the ground. Behind the smoke there" +
                " is an arrow pointing downwards.\nThere is a big pile of dirt on the ground beside a huge hole. ", false, 10);
        locations.add(campSite);
        campSite.addItem(shovel);

        Location hole = new Location("", "You climbed in to a hole in the ground. The hole is not too\n" +
                "deep but it looks as if it someone starting digging a tunnel here. ", true, 11);
        locations.add(hole);
        hole.addItem(flint);

        Location base = new Location("the base of the tree house", "You are at the base of the tree house. Around you is dense forest," +
                " the sky is completely covered\nby the thick canopy of trees. ", false, 12);
        locations.add(base);
        base.addItem(wallet);

        Location treeHouse = new Location("the tree house", "You are in a tree house, 40 feet above the ground.\n" +
                "It is more of a tree fort considering it's only a 10 foot by 10 foot platform with \n" +
                "walls made of rickety planks of wood. The only way out is down. ", true, 13);
        locations.add(treeHouse);
        treeHouse.addItem(shirt);
        treeHouse.addItem(bearMace);

        Location beach = new Location("the beach", "You are on a sandy beach looking out into the vast ocean. The hot sun\n" +
                "is scorching your dry skin. The only sounds that can be heard for miles are birds chirping. ", false, 14);
        locations.add(beach);
        zipLine.setBaseLocation(beach);

        Location rocks = new Location("the rocks", "You are heading north on a pathway made of huge rocks along the shore of the island.\n" +
                "The waves are crashing violently against the side of the path. ", false, 15);
        locations.add(rocks);

        Location oceanDeath = new Location("", "You fell into the ocean and the powerful waves slammed you against\n" +
                "the rock face. Unfortunately you died and your wife ended up marrying your best friend,\n" +
                "if it makes you feel any better they named their first child after you. ", true, 16);
        locations.add(oceanDeath);

        Location waterFall = new Location("the water fall", "You are standing in front of a beautiful display of water cascading over a mountain side\n" +
                "with palm trees on either side.",
                false, 17);
        locations.add(waterFall);
        waterFall.addItem(water);

        Location cave = new Location("", "You found a hidden cave that is actually the inside of a volcano.\n" +
                "On the wall of the cave there is a drawing etched in the stone of a big bon fire, with smoke\n" +
                "rising up to the sky, and a helicopter flying over head. ", true, 18);
        locations.add(cave);
        cave.addItem(fireWood);
        tunnel.setSide2(cave);

        Location skyScraper = new Location("a skyscraper", "You are on top of a tall building. The helicopter is here. Be careful to not fall off of it.\n" +
                "There is a fire escape that leads you down to the street below. ", false, 19);
        locations.add(skyScraper);

        Location heightDeath = new Location("", "You fell off the building and suffered a hundred foot drop to your death. Unfortunately you died and your wife ended up marrying your best friend,\n" +
                "if it makes you feel any better they named their first child after you. ", false, 20);
        locations.add(heightDeath);

        Location street = new Location("a street", "You are on a busy street. Be careful to not walk into the middle of the road.\n" +
                "There is a street sign that says '2007 fornow' there are also a call-by-phone taxi service that may be useful for you to get to the wedding. ", false, 21);
        locations.add(street);

        Location steetDeath = new Location("", "You walked out into the middle of the street and a massive semi truck hit you head on.\n" +
                "Unfortunately you died and your wife ended up marrying your best friend,\n" +
                "if it makes you feel any better they named their first child after you. ", false, 22);
        locations.add(steetDeath);

        Helicopter helicopter = new Helicopter("a helicopter", "You are in a helicopter! ", 23, skyScraper, heliPad, "You took off and made it to your destination. ", Game.OUT, Game.IN);
        locations.add(helicopter);
        flint.setHelicopter(helicopter);
        helicopter.addItem(vows);

        Location wedding = new Location("the wedding", "You are at the wedding! ", false, 24);
        locations.add(wedding);

        Taxi taxi = new Taxi("taxi", "You are inside the taxi! The taxi driver needs to know the address of where to go. ", 25, wedding, "You have been driven to your destination. ", Game.WEST, Game.EAST);
        locations.add(taxi);
        cellphone.setTaxi(taxi);

        //Create a location to throw the beast when he is dead, the location has no exits to leave.
        Location death = new Location("","",true,26);
        locations.add(death);

        Key heliKey = new Key("key to the helicopter.", "There is a helicopter-key here, which can be used to operate the helicopter by yourself! ", 300, Parser.find("HELICOPTER-KEY"), helicopter);
        itemsMap.put(heliKey.getCode(), heliKey);

        helicopter.addItem(heliKey);

        Key key = new Key("A small black key", "There is a small black key here. ", 100, Parser.find("KEY"), doorWay);
        itemsMap.put(key.getCode(), key);

        bar.addItem(key);

        shovel.addUseLocation(beach);
        shovel.addUseLocation(hole);

        //Input each location into the hashMap.
        for (Location location : locations) {
            locationFinder.put(location.getCode(), location);
        }

        //Create the pathways between locations.
        createLocationConnector(treeHouse, base, DOWN);
        createLocationConnector(base, campSite, WEST);
        createLocationConnector(base, beach, EAST);
        createLocationConnector(beach, rocks, NORTH);
        createLocationConnector(rocks, waterFall, NORTH);
        createLocationConnector(rocks, oceanDeath, EAST);
        createLocationConnector(waterFall, cave, WEST);
        createLocationConnector(campSite, clearing, NORTH);
        createLocationConnector(clearing, volcano, NORTH);
        createLocationConnector(volcano, halfVolcano, UP);
        createLocationConnector(halfVolcano, topVolcano, UP);
        createLocationConnector(topVolcano, firePit, EAST);
        createLocationConnector(clearing, bar, WEST);
        createLocationConnector(bar, doorWay, UP);
        createLocationConnector(attic, roofTop, UP);
        createLocationConnector(campSite, hole, DOWN);
        createLocationConnector(skyScraper, street, DOWN);
        createLocationConnector(topVolcano, heliPad, NORTH);
        rocks.addExit(EAST, oceanDeath);
        skyScraper.addExit(EAST, heightDeath);
        skyScraper.addExit(WEST, heightDeath);
        skyScraper.addExit(NORTH, heightDeath);
        skyScraper.addExit(SOUTH, heightDeath);
        street.addExit(EAST, steetDeath);

        //Create the main player of the game and set his starting location. and give the player sunglasses in his backpack.
        Player mainPlayer = MainPlayer.getInstance();
        mainPlayer.setLocation(treeHouse);
        mainPlayer.getBackpack().add(sunGlasses);

        //Create the secondary players of the game which are not controlled by the user.
        Beast beast = Beast.getInstance();
        Beast babyBeast = Beast.getBABY_Instance();
        beast.setHome(oceanDeath);
        babyBeast.setHome(rocks);
        beast.goHome();
        babyBeast.goHome();

        //create a scanner so the user to answer beginning question and sift through beginning blurb.
        Scanner user = new Scanner(System.in);

        //Start menu
        System.out.println("Welcome to Bachelor Party\nBy: Bryson Marazzi\n" +
                "If you would like to load a previously saved game enter 'load'\n" +
                        "Otherwise, press any key to start a new game." );
        String input = user.nextLine();
        if(input.equalsIgnoreCase("LOAD")){
            restoreGame(mainPlayer,FILE_NAME);
            System.out.println("Welcome back.\n" + mainPlayer.getDescription());
        }else {

            //Start of the game blurb.

            System.out.print("Wow it’s nice to be active again! Hi, i’m your brain. You are probably wondering\n" +
                    "what’s going on here. Good morning by the way. You just woke up. What a night\nyou had last night! " +
                    "Your friends really know how to throw you a bachelors party.");
            user.nextLine(); //Make the user hit enter to continue reading.
            System.out.println("\nThe bad news is you downed a few too many cold ones last night and you are having\ntrouble " +
                    "remembering anything at all.");
            user.nextLine();
            System.out.println("Unfortunately it gets worse… Your 'friends' left you passed out shirtless in a tree house\non an abandoned " +
                    "island!!! But don’t freak out. All you need to do is figure out\nhow to get off this island without dying.");
            user.nextLine();
            System.out.print("Oh and did I mention your wedding is today? Ok, now you can freak out! You better get\noff " +
                    "this island quick. The love of your life is not going to enjoy a solo wedding.\n\n");
            user.nextLine();
            System.out.print("\nTo make progress type 1 or 2 word commands.\nHere are the actions available to you:\ngo north/south/east/west/up/down\nback (return to your" +
                    " previous location)\ntake/drop/use/wear/drink (some item)\nInventory (see what you have in your backpack)" +
                    "\nlook\nscore\nhelp\nsave\nquit\n\nGood luck! And make sure to keep an eye out for any items that may be of value\n" +
                    "to you. Make sure you arrive at your wedding prepared. Make that wedding!\n\n");

            System.out.println("You woke up feeling pretty dizzy. In your pocket, there is sand where your wedding ring used to be.");
            System.out.println(mainPlayer.getDescription()); // Out put the surroundings to the user to start the game.
        }
        String originalTopVolcanoDescription = "You are now above the clouds. You have made it to the top of the volcano. ";
        String originalRoofTopDescription = "You are on top of the roof of the attic. It is windy here.\n" +
                "You can see a huge volcano from here. The volcano is accessible from the ground. ";

        /**
         * The main code for the game. Begin the game.
         **/
        //Run until the user quits the game.

        while (command[0] != 1) {

            //Update the description of the top locations that show where the baby bear is.
            if(!babyBeast.getCurrentLocation().getName().isEmpty()) {
                roofTop.setDescription(originalRoofTopDescription + "\nFrom way up here you can see a baby bear in "  + babyBeast.getCurrentLocation().getName()+ ".");
            topVolcano.setDescription(originalTopVolcanoDescription + "\nFrom way up here you can see a baby bear in " + babyBeast.getCurrentLocation().getName()+ ".");
            } else {
                roofTop.setDescription(originalRoofTopDescription);
                topVolcano.setDescription(originalTopVolcanoDescription);
            }

            //Obtain input from user
            command = getInput();

            //Deal with user requests:

            //If the user requests to move.
            if (command[0] == Parser.find("GO")) {

                //First check special cases.
                //Check if the player is trying to walk while drunk.
                if (mainPlayer.isDrunk()) {
                    System.out.println("As you begin to walk, you trip and bang your head \n" +
                            "against the wall. Unfortunately you are so dizzy\nfrom last night that you can barely walk.\n");
                    mainPlayer.setHealth(mainPlayer.getHealth() - 20);
                } else if (!mainPlayer.getCurrentLocation().hasDirection(command[1]))
                    System.out.println("There is no where to go in that direction!\n");
                    //None of the special cases apply, it is safe to move normally.
                else {
                    try {
                        mainPlayer.move(mainPlayer, command[1]);
                        amountOfActions++;  //Increment the amountOfActions if the player successfully moves.
                        System.out.println(mainPlayer.getDescription());
                    } catch (IllegalCommandException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            //If the user wants to go back to his/her original location.
            if (command[0] == Parser.find("BACK")) {
                try {
                    mainPlayer.goBack();
                    System.out.println(mainPlayer.getDescription());
                    amountOfActions++;  //Increment the amountOfActions if the player successfully moves.
                } catch (EmptyStackException e) {
                    System.out.print("You can't go back, you are at your origin!\n");
                } catch (IllegalCommandException e) {
                    System.out.println(e.getMessage());
                }
            }

            //If the user requests to take or drop an Item.
            if (command[0] == Parser.find("DROP") || command[0] == Parser.find("TAKE")) {

                //If take
                if (command[0] == Parser.find("TAKE")) {
                    mainPlayer.take(itemsMap.get(command[1]));

                    //If drop
                } else {
                    mainPlayer.drop(itemsMap.get(command[1]));
                }
            }

            //If the user wants to interact with an object.
            if (command[0] == Parser.find("USE") || command[0] == Parser.find("WEAR") || command[0] == Parser.find("DRINK") ||
                    command[0] == Parser.find("WRITE") || command[0] == Parser.find("DRIVE")) {

                //Check if the item is present in location or player's backpack.
                if (mainPlayer.getCurrentLocation().getItems().contains(itemsMap.get(command[1]))) {

                    //Use the item.
                    itemsMap.get(command[1]).use(mainPlayer, itemsMap.get(command[1]));

                } else {

                    //The item is not in the current location, check if it is in the players backpack.
                    if (mainPlayer.getBackpack().getItems().contains(itemsMap.get(command[1]))) {

                        //Use the item.
                        itemsMap.get(command[1]).use(mainPlayer, itemsMap.get(command[1]));

                        //The item is neither in the location or backpack.
                    } else System.out.print(itemsMap.get(command[1]).getName() + " is not available to you.\n\n");
                }
            }

            //Check if the mainPlayer moved to a location that results in death.
            if (mainPlayer.getCurrentLocation().equals(oceanDeath) || mainPlayer.getCurrentLocation().equals(firePit) ||
                    mainPlayer.getCurrentLocation().equals(heightDeath)) {
                //Kill the player.
                mainPlayer.setHealth(0);
            }

            //Check if the player is in the taxi and has input the correct address, then operate the taxi.
            if (command[0] == Parser.find("4317") && command[1] == Parser.find("bridgeview")) {
                if (mainPlayer.getCurrentLocation().equals(taxi) && taxi.getDestination().getCode() == 24) {
                    taxi.operate(taxi);
                   amountOfActions++; //Increment the amountOfActions.
                } else System.out.println("That is the wrong address! ");
            }

            //Check if the player is in the taxi and has input the correct address, then operate the taxi.
            if (command[0] == Parser.find("2007") && command[1] == Parser.find("fornow")) {
                if (mainPlayer.getCurrentLocation().equals(taxi) && taxi.getDestination().getCode() == 21) {
                    taxi.operate(taxi);
                    amountOfActions++; //Increment the amountOfActions.
                } else System.out.println("That is the wrong address! ");
            }

            //If the user requests to look around
            if (command[0] == Parser.find("LOOK")) {
                System.out.println(mainPlayer.getDescription());
                amountOfActions++; //Increment the amountOfActions.
            }

            //If the user requests to see what's in their inventory.
            if (command[0] == Parser.find("INVENTORY")) {
                mainPlayer.describeInventory();
                amountOfActions++; //Increment the amountOfActions.
            }

            //If the user requests help, return a list of available actions
            if (command[0] == Parser.find("HELP")) {
                System.out.println("You are the protagonist of this adventure! Input 1 or 2 word commands to make progress through this game.\n" +
                        "You have the following actions available to you:\ngo north/south/east/west/up/down/in/out\nTake/Drop/Use/Drink/Wear (some item)\n" +
                        "Look\nInventory\nScore\nSave\nLoad\n");
            }

            //If the user requests the score
            if (command[0] == Parser.find("SCORE")) {
                System.out.println("You have made " + amountOfActions + " moves.\n" +
                        "Your score is: " + score + "\n");
            }

            //If the user would like to load a previous game.
            if (command[0] == Parser.find("LOAD")) {
                restoreGame(mainPlayer, FILE_NAME);
            }

            //If the user would like to save the current status of the game.
            if (command[0] == Parser.find("SAVE")) {
                saveGame(FILE_NAME);
                System.out.println("OK");
            }

            //Check if the player has won the game.
            if (mainPlayer.getCurrentLocation().equals(wedding)) {

                if (!mainPlayer.getBackpack().hasItem(ring)) {
                    System.out.println("You don't have the ring! How do you think you are going to marry your wife without a ring?\n" +
                            "You better go get that ring before your wife sees you. ");
                } else if (!mainPlayer.getBackpack().hasItem(vows)) {
                    System.out.println("You have your ring, but what are you going to say? You better go get your vows! ");
                } else if (vows.getWriting().isEmpty()) {
                    System.out.println("You haven't written any vows! Make sure you write something down to say to the women\n" +
                            "you will be spending the rest of your life with. Come on man! ");
                } else {
                    System.out.println("Congratulations! You made it to your wedding!\n" +
                            "You made it off the island. You found your lost wedding ring and you have prepared your vows.\n" +
                            "Even though you picked the wrong friends (they left you on an island)\n" +
                            "You sure picked the right wife! As you two stand at the alter, your fiance blushes as\n" +
                            "you read\n'" + vows.getWriting() + "'How beautiful! She is lucky to have you. \nThanks for playing!");
                    System.out.println("You made " + amountOfActions + " moves.\n" +
                            "Your final score is: " + score + ".");
                    break;  //End the game.
                }
            }

            //Check if the player has no possible way to get to the wedding.
            if( cellphone.getBattery() < 1 && (!mainPlayer.getCurrentLocation().equals(wedding) || !mainPlayer.getCurrentLocation().equals(taxi))){
                System.out.println("Unfortunately you ran out of time, and your wife got fed up with you.\n" +
                        "She married your best friend instead. GAME OVER.");
                break; //End the game.
            }

            //Check if any player has died.
            if (mainPlayer.getHealth() < 1) {
                System.out.println("You have died!");
                command[0] = Parser.find("QUIT"); //Quit the game.
            }

            if (beast.isDead()) {
                beast.setLocation(death); //Put the beast in the death location so it can't move.
            }


            //Perform commands for the independent players. If the momma bear is alive.

            if(!beast.isDead()) {

                //Move the baby beast.
                 babyBeast.move(babyBeast, 0);

                //If the players runs into the baby beast, the beast wakes up(if its not awake) then becomes angry (if it was calmed down).
                if (babyBeast.getCurrentLocation().equals(mainPlayer.getCurrentLocation())) {
                    if (!beast.isAwake())
                        System.out.println("There is a baby bear here! Be careful because every baby cub has an angry momma-bear... ");
                    else if (beast.isCalm()) {
                        beast.setIsCalm(false);
                        System.out.println("You bumped into the baby cub again! I don't think momma bear is your friend anymore.. ");
                    }
                    beast.setIsAwake(true);
                }

                //Perform tasks for the beast.

                //If the user decides to pet the beast.
                if (command[0] == Parser.find("PET") && command[1] == Parser.find("Momma-bear")) {
                    if (mainPlayer.getCurrentLocation().equals(beast.getCurrentLocation())) {
                        System.out.println("Momma-bear seems to like that, she has calmed down, she is now your friend. ");
                        beast.setIsCalm(true);
                    } else System.out.println("I'm sorry... what??");
                }

                //Perform beast actions if the beast is awake and alive.
                if (beast.isAwake()) {
                    //Move the beast.
                    //Only move the beast if the beast is not in the same location as the player.
                    if (!mainPlayer.getCurrentLocation().equals(beast.getCurrentLocation())) {
                        ArrayList<Location> path = new ArrayList<Location>();
                        path.clear();
                        boolean playerReachable;
                        playerReachable = findPath(mainPlayer.getCurrentLocation(), beast.getCurrentLocation(), path);
                        for (Location location : locations) {
                            location.unMark();
                        }
                        if (playerReachable && path.size() > 1) beast.setLocation(path.get(1));
                        else if (playerReachable && path.size() == 1) {
                            beast.setLocation(mainPlayer.getCurrentLocation());
                        }
                    }
                    //Deal with the situation where the mainPlayer and the beast meet up.
                    if (mainPlayer.getCurrentLocation().equals(beast.getCurrentLocation())) {
                        if (!beast.isCalm()) {
                            mainPlayer.setHealth(mainPlayer.getHealth() - 15);
                            System.out.println("Momma-bear is in this location!! She attacked you and you lost some health! ");
                        } else {
                            System.out.println("Momma-bear is here, but don't worry she likes you. ");
                        }
                    }
                }

            }
            //Update the score at important landmarks, and use indicator to make sure the score only updates once.
            if(mainPlayer.getCurrentLocation().equals(skyScraper) && indicator[0] > amountOfActions) {
                indicator[0] = amountOfActions;
                Game.score += 20; //Update the score.
            }
            if(mainPlayer.getCurrentLocation().equals(helicopter) && indicator[1] > amountOfActions){
                indicator[1] = amountOfActions;
                Game.score += 20; //Update the score.
            }
            if(mainPlayer.getCurrentLocation().equals(taxi) && indicator[2] > amountOfActions){
                indicator[2] = amountOfActions;
                Game.score += 20; //Update the score.
            }
            if(mainPlayer.getCurrentLocation().equals(wedding) && indicator[3] > amountOfActions){
                indicator[3] = amountOfActions;
                Game.score += 40; //Update the score.
            }
            if(mainPlayer.getCurrentLocation().equals(cave) && indicator[4] > amountOfActions){
                indicator[4] = amountOfActions;
                Game.score += 30; //Update the score.
            }
            if(mainPlayer.getCurrentLocation().equals(cave) && indicator[5] > amountOfActions){
                indicator[5] = amountOfActions;
                Game.score += 20; //Update the score.
            }
            if(mainPlayer.getCurrentLocation().equals(hole) && indicator[6] > amountOfActions){
                indicator[6] = amountOfActions;
                Game.score += 20; //Update the score.
            }

            //if the player quits.
            if(command[0] == 1){
                System.out.println("You have quit the game.");
                System.out.println("You have made " + amountOfActions + " moves.\n" +
                        "Your score is: " + score + ".");
            }

        }
    }

    /**
     * restore the Game.
     * @param FILE_NAME The name of the file.
     */
    private void restoreGame(Player mainPlayer, String FILE_NAME) {
       try {
            //open the save file.
            BufferedReader saveFile = new BufferedReader(new FileReader(FILE_NAME));

            //Update the player and items in the player's backPack.
            mainPlayer.restore(saveFile,this.locationFinder,this.itemsMap);

           //Restore the state of the locations in the game, and each item in the location.
           for (Location location : this.locations) {
               location.restore(saveFile,this.locationFinder,itemsMap);
           }

            //Update the amount of actions made.
            amountOfActions = Integer.parseInt(saveFile.readLine());

            //Update the score.
            score = Integer.parseInt(saveFile.readLine());

           //Restore the beast.
            Beast.getInstance().restore(saveFile,this.locationFinder,itemsMap);

           //Restore the babyBeast.
           Beast.getBABY_Instance().restore(saveFile,this.locationFinder,itemsMap);


           //Obtain the amount of values in indicator array.
           int indicatorLength = Integer.parseInt(saveFile.readLine());

           //Save the indicator values.
           for(int i =0; i < indicatorLength; i++){
               indicator[i] = Integer.parseInt(saveFile.readLine());
           }

           // Close the save File.
           saveFile.close();

           System.out.println("Your game has been restored!\n");

       }catch (IOException ieo){
        System.out.println("Could not restore the saved file.");
        System.out.println(ieo.getMessage());
        System.out.println("\n");
        }
    }


    /**
     * Save the current state of the game.
     * @param FILE_NAME The name of the file.
     */
          public void saveGame (String FILE_NAME) {
            try {

                Player mainPlayer = MainPlayer.getInstance();
                //Create a save File.
                BufferedWriter saveFile = new BufferedWriter(new FileWriter(FILE_NAME));

                //Save the state of the main Player, and each item in the backPack.
                mainPlayer.save(saveFile);

                //Save the state of the locations in the game, and each item in the location.
                for (Location location : this.locations) {
                    location.save(saveFile);
                }

                //Output the amount of actions made.
                saveFile.write(Integer.toString(this.amountOfActions));
                saveFile.newLine();

                //Save the score.
                saveFile.write(Integer.toString(this.score));
                saveFile.newLine();

                //Save the beast.
                Beast.getInstance().save(saveFile);

                //Save the babyBeast.
                Beast.getBABY_Instance().save(saveFile);

                //Output the amount of values in indicator array.
                saveFile.write(Integer.toString(this.indicator.length));
                saveFile.newLine();

                //Save the indicator values.
                for(int i =0; i < indicator.length; i++){
                    saveFile.write(Integer.toString(this.indicator[i]));
                    saveFile.newLine();
                }

                // Close the save File.
                saveFile.close();
            } catch (IOException ieo) {
                System.out.println("Could not create saved file");
                System.out.println(ieo.getMessage());
            }
        }

    /**
     * Create a two way pathway between two locations.
     * @param location1 The first location that is being connected to the second location.
     * @param location2 The second location that is being connected to the first location.
     * @param direction The direction you have to go to get from the first location to the second location.
     */
        public static void createLocationConnector(Location location1 , Location location2, int direction){

            int oppositeDirection = 0;
            if(direction == NORTH) oppositeDirection = SOUTH;
            if(direction == SOUTH) oppositeDirection = NORTH;
            if(direction == EAST) oppositeDirection = WEST;
            if(direction == WEST) oppositeDirection = EAST;
            if(direction == UP) oppositeDirection = DOWN;
            if(direction == DOWN) oppositeDirection = UP;
            location1.addExit(direction,location2);
            location2.addExit(oppositeDirection,location1);
    }

    /**
     * Remove a two way pathway between two locations.
     * @param location1 The first location that is being connected to the second location.
     * @param location2 The second location that is being connected to the first location.
     * @param direction The direction you have to go to get from the first location to the second location.
     */
    public static void removeLocationConnector(Location location1 , Location location2, int direction){
        int oppositeDirection = 0;
        if(direction == NORTH) oppositeDirection = SOUTH;
        if(direction == SOUTH) oppositeDirection = NORTH;
        if(direction == EAST) oppositeDirection = WEST;
        if(direction == WEST) oppositeDirection = EAST;
        if(direction == UP) oppositeDirection = DOWN;
        location1.removeExit(direction,location2);
        location2.removeExit(oppositeDirection,location1);

    }

        /**
         * A method to prompt the user for input, respond automatically if the input is invalid,
         * and loop until valid input is inputted.
         * @return An integer array of numbers corresponding to valid actions + objects in the game.
         */
        private static int[] getInput () {

            int command[] = {0, 0};

            //Loop until proper command is inputted
            while (!Parser.isInputProper()) {

                //Obtain a command
                System.out.println("\nWhat do you want to do?\n");
                String input = CONSOLE.nextLine();
                try {
                    command = Parser.parse(input);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            //Reset the Input Success Variable to false so that the
            // next time this method is called the above while loop will run.
            Parser.setInputSuccess(false);
            return command;
        }



    /**
     * A recursive method to find a path from one location to another.
     * @param destination The location to find.
     * @param start The initial starting position.
     * @param path An arrayList to be filled with locations which lead to the destination.
     * @return A boolean, true if a path can be made to the location, false if not.
     * This method was adapted from an algorithm found on the internet at: https://www.cs.bu.edu/teaching/alg/maze/
     */
    public boolean findPath(Location destination,Location start,ArrayList<Location> path){
        if(start == null) return false; //Base Case.
        if(start.equals(destination)) return true; //Base Case.
        if(start.isMarked()) return false; //Base Case.
        start.mark(); //Initially mark the location.
        path.add(start); //Add the location to the path.

        //Recursively call the same method to learn which direction leads to the destination.
        if(findPath(destination,start.getExit(Game.NORTH),path)) return true;
        if(findPath(destination,start.getExit(Game.EAST),path)) return true;
        if(findPath(destination,start.getExit(Game.DOWN),path)) return true;
        if(findPath(destination,start.getExit(Game.SOUTH),path)) return true;
        if(findPath(destination,start.getExit(Game.WEST),path)) return true;
        if(findPath(destination,start.getExit(Game.UP),path)) return true;

        //The location is not a solution to the path, un mark it.
        start.unMark();
        path.remove(path.size()-1 ); //Remove the location from the path.

        return false;
    }

}
