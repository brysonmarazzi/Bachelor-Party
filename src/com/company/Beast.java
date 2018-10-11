package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

/**
 * Beast is a participant in an adventure game.
 *
 *
 */
public class Beast extends Player{

    /**
     * A boolean variable to indicate if the beast is calm.
     **/
    private boolean isCalm = false;

    /**
     * A boolean variable to indicate if the beast is dead.
     **/
    private boolean isDead = false;

    /**
     * A boolean variable to indicate if the beast is awake.
     **/
    private boolean isAwake = false;

   /** The beast instance. */ private static final Beast INSTANCE = new Beast();
    /** The baby beast instance. */ private static final Beast BABY_INSTANCE = new Beast();
    /** **/ private Location home = null;

    /**
     * Create a Beast.
     */
    private Beast() {
        super();
    }

    /**
     * Obtain the beast.
     * @return the beast instance.
     */
    public static Beast getInstance(){
        return INSTANCE;
    }

    /**
     * Obtain the beast.
     * @return the beast instance.
     */
    public static Beast getBABY_Instance(){
        return BABY_INSTANCE;
    }

    /**
     * Set the home of the beast.
     * @param home THe home of the beast.
     */
    public void setHome(Location home){
        this.home = home;
    }

    /**
     * Set the location of the beast to it's home.
     */
    public void goHome(){
        this.setLocation(this.home);
    }

    /**
     * Follow the player indicated.
     * @param player THe player to find, then follow.
     */
    public void follow(Player player) {
        if(this.getCurrentLocation().getExits().size() > 1) {
            Location test = this.getCurrentLocation();
            for (int i = 8; i < 14; i++) { //Search through all the directions in the game.
                if (this.getCurrentLocation().hasDirection(i)) { //Confirm that the location has a exit in this direction.

                    int distanceFromPlayer = Math.abs(player.getCurrentLocation().getCode() - test.getCode());
                    int distanceFromPlayerPotential = Math.abs(player.getCurrentLocation().getCode() - this.getCurrentLocation().getExit(i).getCode());

                    //Compare the location in the specific direction with the location already considered. Which is closer is kept as the correct location to take.
                    if (distanceFromPlayer > distanceFromPlayerPotential) test = this.getCurrentLocation().getExit(i);
                }
            }
            this.setLocation(test); //Take the direction.
        }else this.setLocation(this.getCurrentLocation().getExits().get(0)); //Take the only exit available.
    }

    /**
     * Move the beast.
     *
     * @param direction The direction the user went.
     */
    public void move(Player beast, int direction){
        Random random = new Random();
        int randomNumber = random.nextInt(8) + 8;   //Obtain a random number between 8 and 15 (the directions in the game).

        //Obtain a random number for a direction to go and continue until you obtain
        // a number that corresponds to a location accessible in that direction.
        while (!beast.getCurrentLocation().hasDirection(randomNumber)) randomNumber = random.nextInt(8) + 8;
        beast.getCurrentLocation().takeExit(beast, randomNumber);
    }

    /**
     * Obtain the knowledge of the state of the beast's calmness.
     *
     * @return A boolean, true, if the beast is calm, false if the beast is dangerous.
     */
    public boolean isCalm() {
        return this.isCalm;
    }

    /**
     * Set the status of the beast's calmness.
     *
     * @param isCalm
     */
    public void setIsCalm(boolean isCalm) {
        this.isCalm = isCalm;
    }

    /**
     * Obtain the knowledge of the if the beast is dead or not. .
     *
     * @return A boolean, true, if the beast is dead, false if the beast is alive.
     */
    public boolean isDead() {
        return this.isDead;
    }

    /**
     * Set the status of the beast's alive or dead.
     *
     * @param isDead
     */
    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    /**
     * Obtain the knowledge of the state of the beast.
     *
     * @return A boolean, true, if the beast is awake, false if the beast is sleeping.
     */
    public boolean isAwake() {
        return this.isAwake;
    }

    /**
     * Set the status of the beast's calmness.
     *
     * @param isAwake
     */
    public void setIsAwake(boolean isAwake) {
        this.isAwake = isAwake;
    }


    /**
     * Save this beast.
     *
     * @param saveFile A BufferedWriter for the file to be saved
     * @throws IOException Throws exception if an error occurs during the save operation
     **/
    public void save(BufferedWriter saveFile) throws IOException {

        //Output the Code of this current Location.
        saveFile.write(Integer.toString(this.getCurrentLocation().getCode()));
        saveFile.newLine();

        //Output the health of the beast.
        saveFile.write(Integer.toString(this.getHealth()));
        saveFile.newLine();

        //Output the state of the beast.
        saveFile.write(Boolean.toString(this.isAwake));
        saveFile.newLine();

        //Output the emotional state of the beast.
        saveFile.write(Boolean.toString(this.isCalm));
        saveFile.newLine();

    }

    /**
     * Restore the beast.
     *
     * @param saveFile       The bufferedReader to read the file.
     * @param locationFinder A hashMap used to obtain locations.
     */
    public void restore(BufferedReader saveFile,HashMap<Integer, Location> locationFinder,HashMap<Integer, Item> itemsMap) {

        try {

            //Restore this current Location.
            this.setLocation(locationFinder.get(Integer.parseInt(saveFile.readLine())));

            //Restore the health of the beast.
            this.setHealth(Integer.parseInt(saveFile.readLine()));

            ////Restore the state of the beast.
            this.isAwake = Boolean.parseBoolean(saveFile.readLine());

            //Restore the emotional state of the beast.
            this.isCalm = Boolean.parseBoolean(saveFile.readLine());

        } catch (IOException ieo) {
            System.out.println("Could not restore the saved file.");
            System.out.println(ieo.getMessage());
            System.out.println("\n");
        }
    }
}
