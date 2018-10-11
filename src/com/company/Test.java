package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class Test {
    Beast beast = Beast.getInstance();
    Player mainPlayer = MainPlayer.getInstance();


    public Test(){

    }

    public void testTry(){


    }


}
/*


public int leadsToPlayer(Player player, Location start) {
    boolean foundGirl = false;
    Stack<Location> forks = new Stack();
    Stack<Location> path = new Stack();
    path.push(start);

    //check if only one exit, then take that one.
    if (start.getDirections().size() == 1) return start.getDirections().get(0);

    //Obtain the amount of direction the beast has to choose from, from his current Location.
    int amountOfDirectionOptions = start.getDirections().size(); //Could be two or more
    forks.add(start);

    for (int i = 0; i < amountOfDirectionOptions; i++) { //main loop for the method.
        int directionOfChoice = start.getDirections().get(i);

        if (start.isDeadEnd(player, start, directionOfChoice)) break;   //Check if its dead end.

        start = start.getExit(directionOfChoice); //Start is the new direction from the original location of the beast, its not a dead end.
        path.push(start);  //Add this location to the path stack.

        while (!foundGirl) { //loop this path until the player is found along this path, or the player is not found.
            if (player.getCurrentLocation().equals(start)) {
                foundGirl = true; // break out of this while loop.
                break;
            }
            int amountOfExits = start.getDirections().size(); //Amount of direction of the location being tested.
            int forwardDirection; //used if the amount of directions is only 2, this way the direction to go is clear.
            if (amountOfExits == 2) {
                Location previous = path.elementAt(path.size() - 2);
                forwardDirection = start.getDirections().get(0);  //Set forward direction to the first one.
                if (start.getExit(forwardDirection).equals(previous)) { //Check if the first direction actually just goes to where you came from.
                    forwardDirection = start.getDirections().get(1); //Set forward to the second direction.
                }

                //Now the forward direction is obtained. travel along this path.
                start = start.getExit(forwardDirection);
                path.push(start);

            } else {
                forks.push(start); // The new start location has more than 2 exits and both need to be considered at one point.
                int amountOfForkDirections = start.getDirections().size();
                //Take one of the directions.
                Location previous = path.elementAt(path.size() - 2);
                forwardDirection = start.getDirections().get(0);  //Set forward direction to the first one.
                if (start.getExit(forwardDirection).equals(previous)) { //Check if the first direction actually just goes to where you came from.
                    forwardDirection = start.getDirections().get(1); //Set forward to the second direction.
                }
            }

        }
    }
}

    public boolean leadsToPlayer(Player player, Location start, int direction){
        if(start.getExit(direction).equals(player.getCurrentLocation())) return true;
        else if (start.isDeadEnd(player,start,direction)) return false;
        else {
            for(int i = 0; i < start.getDirections().size(); i++){
                return leadsToPlayer(player,start.getExit(direction),start.getDirections().get(i));
            }
        }

    }




    public int findDirectionTowardsPlayer(Player player) {
        Location test = this.getCurrentLocation();

        while (!test.equals(player.getCurrentLocation())) {

            int amountOfExits = test.getDirections().size();   //Obtain the amount of directions available to you.

            for (int i = 0; i < amountOfExits; i++) {

                int testDirection = test.getDirections().get(i);
                if (leadsToPlayer(player, test, testDirection)) {
                    test = test.getExit(testDirection);
                }
            }

        }
    }

           /* while (!test.equals(player.getCurrentLocation())) {


            }
        }



         //if(test.equals(player.getCurrentLocation())) return direction; //The case which is defined. Recursion works backwards from here.

        //Obtain the directions available to you.
      //  int amountOfExits = test.getDirections().size();

        for(int i = 0; i < amountOfExits; i++) {
            int directionTest = test.getDirections().get(i);
            if (test.hasDirection(directionTest)) {
                if (test.equals(player.getCurrentLocation()))
                    return directionTest; //The case which is defined. Recursion works backwards from here.
                if (!test.getExit(directionTest).equals(previous)) {
                    return findDirectionTowardsPlayer(player, test.getExit(directionTest), test);
                }
            }
            return i;
        }

          //  int correctDirection = findDirectionTowardsPlayer(player,test.getExit(direction),newDirection);
      //  if(test.equals(player.getCurrentLocation())) return correctDirection; //The case which is defined. Recursion works backwards from here.

    }

    /**
     * Recursive method to find the best direction to go.
     * @param player
     * @param test
     * @param direction
     * @return
     */
/*    public int findDirectionTowardsPlayer(Player player,Location test, int direction){
       // if(test.equals(player.getCurrentLocation())) return direction; //The case which is defined. Recursion works backwards from here.
        int newDirection=direction;
        for(int i = 8; i < 14; i++){

            if(test.hasDirection(direction)){
                if(test.getExit(direction).equals(test)) newDirection = i;
            }
        }
        if(newDirection == direction) return direction;
        else
            int correctDirection = findDirectionTowardsPlayer(player,test.getExit(direction),newDirection);
        if(test.equals(player.getCurrentLocation())) return correctDirection; //The case which is defined. Recursion works backwards from here.

    }


    public int findBestDirection(Player player,Location test, Location previous) {

        int testDirection;
        for (int i = 8; i < 14; i++) { //Obtain a new direction.
            if (test.hasDirection(i)) {
                if (test.getExit(i).equals(player.getCurrentLocation())) return i; //The case which is defined. Recursion works backwards from here.
                if (!test.getExit(i).equals(previous)) testDirection = i;//Don't use a direction which leads to location you came from.
                if (findBestDirection(player,test.getExit(i),test)) return i;
            }
        }
    }
*/
/*
 private static int findSmallest(int[] values, int start, int finish) {
	  if(start == finish) return finish;

	  //Recursively call the method, increasing the starting position, until
	  	//the end of the array is reached.
	  int smallestValuePosition = findSmallest(values, start+1, finish);

	  //Return which position has the smaller value.
	  if(values[start] < values[smallestValuePosition]) return start;
	  else return smallestValuePosition;
  }



public boolean canReachPlayer(Player player, Location test, int direction,Location previous){
    if (test.getExit(direction).equals(player.getCurrentLocation())) return true; //The case which is defined. Recursion works backwards from here.
    if (!test.getExit(direction).equals(previous)) return false;//Don't use a direction which leads to location you came from.
    //else return canReachPlayer(player, test, direction);
}
 */

/*
/**
     * Test
     */
/*
public void test(){
    ArrayList<Location> locations = new ArrayList<Location>();
    Location heliPad = new Location("helicopter pad", "You are standing on a helicopter pad. South of you is the top of the volcano. ", false, 0);
    locations.add(heliPad);

    Location topVolcano = new Location("TOP_VOLCANO", "You are now above the clouds. You have made it to the top of the volcano.\n" +
            "If you go east you will fall into the fiery pit! ", false, 1);
    locations.add(topVolcano);

    Location firePit = new Location("FIRE DEATH", "You fell into the fire pit! unfortunately you died a pretty painful death\n" +
            "but the worst thing is that you missed your wedding, and your wife ended up marrying your best friend,\n" +
            "if it makes you feel any better they named their first child after you. ", true, 2);
    locations.add(firePit);

    Location halfVolcano = new Location("HALF-VOLCANO", "You are half way up the volcano. ", false, 3);
    locations.add(halfVolcano);

    Location volcano = new Location("BOTTOMM-VOLCANO", "You are at the bottom of a volcano. ", false, 4);
    locations.add(volcano);

    Location clearing = new Location("clearing", "You are at a clearing in the forest. The trees no longer cover the\n" +
            "sky. You can see a sign for a restaurant that reads 'Pun on a Bun'. ", false, 5);
    locations.add(clearing);

    Location bar = new Location("bar", "You are inside a dingy bar, the walls are filled with old rock and roll " +
            "posters from the seventies.\nThe ground is littered with garbage. It looks like there was a huge " +
            "party here the night before.\nThere is a stairway leading upwards. ", true, 6);
    locations.add(bar);


    Door doorWay = new Door("", "You are at the top of the stairs. ", 7);
    locations.add(doorWay);

    Location attic = new Location("attic", "You are in the attic of the restaurant. The room is dusty,\n" +
            "which makes it hard to breath. ", true, 8);
    locations.add(attic);
    doorWay.setInsideRoom(attic);

    Location roofTop = new Location("roof top", "You are on top of the roof of the attic. It is windy here.\n" +
            "You can see a huge volcano from here. The volcano is accessible from the ground. ", false, 9);
    locations.add(roofTop);


    Location campSite = new Location("campsite", "You are standing at an abandoned camp site. There is still smoke " +
            "rising from the fire pit.\nThere are a bunch of empty beer bottles on the ground. Behind the smoke there" +
            " is an arrow pointing downwards.\nThere is a big pile of dirt on the ground beside a huge hole. ", false, 10);
    locations.add(campSite);


    Location hole = new Location("", "You climbed in to a hole in the ground. The hole is not too\n" +
            "deep but it looks as if it was dug with a shovel. ", true, 11);
    locations.add(hole);

    Location base = new Location("tree house base", "You are at the base of the tree house. Around you is dense forest," +
            " the sky is completely covered\nby the thick canopy of trees. ", false, 12);
    locations.add(base);

    Location treeHouse = new Location("tree house", "You are in a tree house, 40 feet above the ground.\n" +
            "It is more of a tree fort considering it's only a 10 foot by 10 foot platform with \n" +
            "walls made of rickety planks of wood. The only way out is down. ", true, 13);
    locations.add(treeHouse);


    Location beach = new Location("beach", "You are on a sandy beach looking out into the vast ocean. The hot sun\n" +
            "is scorching your dry skin. The only sounds that can be heard for miles are birds chirping. ", false, 14);
    locations.add(beach);


    Location rocks = new Location("rocks", "You are heading north on a pathway made of huge rocks along the shore of the island.\n" +
            "The waves are crashing violently against the side of the path. ", false, 15);
    locations.add(rocks);

    Location oceanDeath = new Location("OCEAN DEATH", "You fell into the ocean and the powerful waves slammed you against\n" +
            "the rock face. Unfortunately you died and your wife ended up marrying your best friend,\n" +
            "if it makes you feel any better they named their first child after you. ", true, 16);
    locations.add(oceanDeath);

    Location waterFall = new Location("water fall", "You are standing in front of a beautiful display of water cascading over a mountain side\n" +
            "with palm trees on either side.",
            false, 17);
    locations.add(waterFall);


    Location cave = new Location("CAVE", "You found a hidden cave that is actually the inside of a volcano.\n" +
            "On the wall of the cave there is a drawing etched in the stone of a big bon fire, with smoke\n" +
            "rising up to the sky, and a helicopter flying over head. ", true, 18);
    locations.add(cave);


    Location skyScraper = new Location("skyscraper", "You are on top of a tall building. The helicopter is here. Be careful to not fall off of it.\n" +
            "There is a fire escape that leads you down to the street below. ", false, 19);
    locations.add(skyScraper);

    Location heightDeath = new Location("", "You fell off the building and suffered a hundred foot drop to your death. Unfortunately you died and your wife ended up marrying your best friend,\n" +
            "if it makes you feel any better they named their first child after you. ", false, 20);
    locations.add(heightDeath);

    Location street = new Location("street", "You are on a busy street. Be careful to not walk into the middle of the road.\n" +
            "There is a street sign that says '2007 fornow' there are also a call-by-phone taxi service that may be useful for you to get to the wedding. ", false, 21);
    locations.add(street);

    Location steetDeath = new Location("", "You walked out into the middle of the street and a massive semi truck hit you head on.\n" +
            "Unfortunately you died and your wife ended up marrying your best friend,\n" +
            "if it makes you feel any better they named their first child after you. ", false, 22);
    locations.add(steetDeath);


    Helicopter helicopter = new Helicopter("helicopter", "You are in a helicopter! ", 23, skyScraper, heliPad, "You took off and made it to your destination. ", Game.OUT, Game.IN);
    locations.add(helicopter);


    Location wedding = new Location("wedding", "You are at the wedding! ", false, 24);
    locations.add(wedding);

    Taxi taxi = new Taxi("taxi", "You are inside the taxi! The taxi driver needs to know the address of where to go. ", 25, wedding, "You have been driven to your destination. ", Game.WEST, Game.EAST);
    locations.add(taxi);


    Key heliKey = new Key("key to the helicopter.", "There is a helicopter-key here, which can be used to operate the helicopter by yourself! ", 300, Parser.find("HELICOPTER-KEY"), helicopter);
    itemsMap.put(heliKey.getCode(), heliKey);

    helicopter.addItem(heliKey);


    Key key = new Key("A small back key", "There is a small black key here. ", 100, Parser.find("KEY"), doorWay);
    itemsMap.put(key.getCode(), key);

    bar.addItem(key);



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
    //topVolcano.addExit(EAST, firePit);
    skyScraper.addExit(EAST, heightDeath);
    skyScraper.addExit(WEST, heightDeath);
    skyScraper.addExit(NORTH, heightDeath);
    skyScraper.addExit(SOUTH, heightDeath);
    street.addExit(EAST, steetDeath);


    //Players
    Beast beast = Beast.getInstance();
    beast.setLocation(treeHouse);
    Player mainPlayer = MainPlayer.getInstance();
    mainPlayer.setLocation(volcano);
    // System.out.println(Boolean.toString(findPath(MainPlayer.getInstance().getCurrentLocation(),beast.getCurrentLocation(),path)));
    while(!mainPlayer.getCurrentLocation().equals(beast.getCurrentLocation())){
        System.out.println("\n\nPlayer location: " + mainPlayer.getCurrentLocation().getName() + "\nBeast location: "  + beast.getCurrentLocation().getName());
        ArrayList<Location>  path = new ArrayList<Location>();
        path.clear();
        for (Location location : locations) {
            location.unMark();
        }
        boolean reachable = (findPath(MainPlayer.getInstance().getCurrentLocation(),beast.getCurrentLocation(),path));
        System.out.println("Path size: " + path.size()) ;
        for (Location pathx : path){
            System.out.println("PATHX " + pathx.getName());
        }
        if(reachable && path.size() > 1) beast.setLocation(path.get(1));
        else if(path.size()==1){
            beast.setLocation(mainPlayer.getCurrentLocation());
        }
        else{
            System.out.println("Unreachable!");
            break;
        }
    }
    System.out.println("Together\nPlayer location: " + mainPlayer.getCurrentLocation().getName() + "\nBeast location: "  + beast.getCurrentLocation().getName());
            /*
System.out.println("Amount of pathways: " + path.size());
            for(Location location : path){
                System.out.println(location.getName());
            }

       */
