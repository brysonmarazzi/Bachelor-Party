package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Player is a participant in an adventure game.
 */
public class Player {
    /** Max capacity for backpack. */ private static final double MAX_BACKPACK_CAPACITY = 10000.0;
    /** The player's backpack. */ private Backpack backpack;
    /** The player's location. */ private Location currentLocation;
    /** A boolean variable to indicate if the player is wearing sunglasses **/ private boolean isWearingSunGlasses = false;
    /** A boolean variable to indicate if the player is drunk **/ private boolean isDrunk = true;
    /** The state of the player. **/ private int health = 100;
    /** A Stack to store the previous location of a player. **/ private Stack<Location> previousLocations = new Stack();

    /**
     * Create a Player.
     */
    public Player() {
        this.backpack = new Backpack(MAX_BACKPACK_CAPACITY);
        this.currentLocation = null;
    }

    /**
     * The player pick's up an item.
     * @param item An Item.
     */
    public void take(Item item) {

        boolean wasTaken = true;  // Local boolean variable to indicate for the output if the item was taken or not.

        //Check if the item is present in the current location and if so, remove it.
        if (this.currentLocation.removeItem(item)){

            //Item is available in the location but can it fit in backpack?
            if(!this.backpack.add(item)) {

                //Item couldn't fit in backpack so add it back into the location.
                this.currentLocation.addItem(item);

                //Change the status of the taken indicator since item could not be taken.
                wasTaken = false;
            }

            //Output 'ok' only if item was successfully taken.
            if(wasTaken){
                Game.amountOfActions++; //Increment the amountOfActions if the player successfully takes an item.
                System.out.println("OK\n");
            }
        }
    }

    /**
     * The player drops an item.
     * @param item An Item.
     */
    public void drop(Item item) {
        if (this.backpack.remove(item)){
            this.currentLocation.addItem(item);
            System.out.println("OK\n"); //Output 'ok' because the item was successfully dropped.
            Game.amountOfActions++; //Increment the amountOfActions if the player successfully drops an item.
        }
    }

    /**
     * Obtain this player's backpack.
     * @return The player's backpack.
     */
    public Backpack getBackpack(){
        return this.backpack;
    }

    /**
     * Describe what the player is carrying.
     */
    public void describeInventory() {
        String inventory = this.backpack.inventory();
        if (inventory == null) {
            System.out.println("You are not carrying anything.\n");
        }
        else System.out.println(inventory);
    }

    /**
     * Set this player's current location.
     * @param currentLocation A Location.
     */
    public void setLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Obtain the player's current location.
     * @return The player's current location.
     */
    public Location getCurrentLocation(){
        return this.currentLocation;
    }

    /**
     * Move the player
     * @param direction The direction the user wants to go.
     * @throws IllegalCommandException
     */
    public void move(Player player,int direction) throws IllegalCommandException {
        //Check if there is an exit in the direction the user inputted.
        if (this.currentLocation.hasDirection(direction)) {
            //Add the current location to the stack.
            this.previousLocations.push(this.currentLocation);
            //Transport the player to the next location.
            this.currentLocation.takeExit(player, direction);
            //Output the location description to the user.
            //System.out.println(getDescription());
        }else throw new IllegalCommandException("You can't go in that direction!\n");
    }

    /**
     * Return the player to his previous location and remove a previous location from the stack.
     * @throws IllegalCommandException
     */
    public void goBack() throws IllegalCommandException {
        if(this.currentLocation.isExit(this.previousLocations.peek())){
            this.currentLocation = this.previousLocations.pop();
        } else throw new IllegalCommandException("You can't go back from here!\n");
    }


    /**
     * Obtain the stack of previous locations.
     * @return The stack of previous locations.
     */
    public Stack<Location> getPreviousLocations(){
        return this.previousLocations;
    }


    /**
     * Set the status of the player's relationship with sunGlasses.
     * @param wearingSunglasses The boolean variable indicating whether the player is wearing sunGlasses or not.
     */
    public void setIsWearingSunglasses(boolean wearingSunglasses){
        this.isWearingSunGlasses = wearingSunglasses;
    }

    /**
     * Set the status of the player's drunkenness.
     * @param isDrunk
     */
    public void setIsDrunk(boolean isDrunk){
        this.isDrunk = isDrunk;
    }

    /**
     * Obtain the knowledge of the state of the player's drunkenness.
     * @return A boolean, true, if the player is drunk, false if the player is sober.
     */
    public boolean isDrunk(){
        return this.isDrunk;
    }

    /**
     * Obtain the health of the player.
     * @return The integer of the player's health.
     */
    public int getHealth(){
        return this.health;
    }

    /**
     * Set the health of this player.
     * @param health The integer of the player's health.
     */
    public void setHealth(int health){
        this.health = health;
    }

    /**
     * Obtain the previous location the player was at.
     * @return The previous location.
     */
    public Location getPreviousLocation(){
        return this.previousLocations.peek();
    }

    /**
     * Save this Player.
     * @param saveFile A BufferedWriter for the file to be saved
     * @throws IOException Throws exception if an error occurs during the save operation
     **/
    public void save(BufferedWriter saveFile) throws IOException{

        //Output the Code of this current Location.
        saveFile.write(Integer.toString(this.currentLocation.getCode()));
        saveFile.newLine();

        //Output if the player is wearing sunglasses
        saveFile.write(Boolean.toString(this.isWearingSunGlasses));
        saveFile.newLine();

        //Output if the player is drunk
        saveFile.write(Boolean.toString(this.isDrunk));
        saveFile.newLine();

        //Output the health of the player.
        saveFile.write(Integer.toString(this.health));
        saveFile.newLine();

        //Output the amount of previous locations in the stack.
        saveFile.write(Integer.toString(this.previousLocations.size()));
        saveFile.newLine();

        //Output the code for each location in the previous location stack.
        for(Location location : previousLocations) {
            saveFile.write(Integer.toString(location.getCode()));
            saveFile.newLine();
        }

        //Output the amount of items in the player's backpack.
        saveFile.write(Integer.toString(this.backpack.getItems().size()));
        saveFile.newLine();

        //Output the code of the items in the player's backpack.
        for (Item item : this.backpack.getItems()){
            item.save(saveFile);
        }

    }

    /**
     * Restore the player.
     * @param saveFile The bufferedReader to read the file.
     * @param locationFinder A hashMap used to obtain locations.
     */
    public void restore(BufferedReader saveFile,HashMap<Integer, Location> locationFinder,HashMap<Integer, Item> itemsMap){

        try {
            //Restore this current Location.
            this.setLocation(locationFinder.get(Integer.parseInt(saveFile.readLine())));

            //Restore if the player is wearing sunglasses
            this.isWearingSunGlasses = Boolean.parseBoolean(saveFile.readLine());

            //Restore if the player is drunk
            this.isDrunk = Boolean.parseBoolean(saveFile.readLine());

            //Restore the health of the player.
           this.health = Integer.parseInt(saveFile.readLine());

            //Obtain the amount of previous locations in the stack.
            int numberOfPreviousLocations = Integer.parseInt(saveFile.readLine());

            //Clear the current previous locations stack.
            this.previousLocations.clear();

            //Restore the previous locations stack.
            for(int i = 0; i < numberOfPreviousLocations; i++) {
                Location nextPreviousLocation = locationFinder.get(Integer.parseInt(saveFile.readLine()));
                this.previousLocations.push(nextPreviousLocation);
            }

            //Obtain the amount of items in the player's backpack.
            int amountOfItems = Integer.parseInt(saveFile.readLine());

            //Empty the player's backPack.
            this.backpack.getItems().clear();

            //Refill the backPack with items
            for(int i = 0; i < amountOfItems; i++){
                int itemCode = Integer.parseInt(saveFile.readLine()); //Obtain the item code.
                Item item = itemsMap.get(itemCode); //Obtain the item.

                // Add the item to the items arrayList for this location.
                this.backpack.add(item);

                //Restore anything that changed about this item.
                item.restore(saveFile);
            }

        } catch (IOException ieo){
            System.out.println("Could not restore the saved file.");
            System.out.println(ieo.getMessage());
            System.out.println("\n");
        }
    }

    /**
     * Obtain the surroundings Description.
     * @return A string of a description of what the player sees.
     */
    public String getDescription() {

        String descriptionWeakState = "You are feeling pretty weak, drinking water would really help.\n" + this.currentLocation.getDescription();

        //Check is sunglasses are not being worn, and the location is not indoors, vision is limited.
       if(!this.isWearingSunGlasses && !this.currentLocation.isIndoors()) return "You are outside and the sun is making it too bright to see.\n";

       //Vision is at full capacity, if player is weak, notify the user.
        else if (this.health < 31) return descriptionWeakState;

        //The player is healthy and can see perfectly.
        else return this.currentLocation.getDescription();

    }
}

