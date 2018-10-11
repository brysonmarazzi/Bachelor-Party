package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
/**
 * The door is a location that contains a door.
 **/
public class Door extends Location {

    /** This lock status of the door. **/ private boolean isLocked;
    /** The inside location of this door. **/ private Location insideRoom = null;


    /**
     * Create a door that is initially locked.
     * @param name A brief description of the location.
     * @param description A detailed description of the location and any items that exist in the location.
     * @param code The indicator code of the door.
     */
    public Door(String name, String description, int code) {
        super(name, description, true, code);
        this.isLocked = true;
    }

    /**
     * Change the lock status.
     */
    public void toggleLock(){
        this.isLocked = !this.isLocked;
    }

    /**
     * Obtain the description of the door which indicates whether it is open or not.
     * @return The description of the door's status.
     */
    public String getDescription() {
        if (this.isLocked) return super.getDescription() + "The door is locked. ";
        else return super.getDescription() + "The door is unlocked. ";
    }

    /**
     * Obtain knowledge of the status of the door.
     * @return Boolean, true if the door is locked, false otherwise.
     */
    public boolean getIsLocked(){
        return this.isLocked;
    }

    /**
     * Set the room in which the door is located in for this door.
     * @param insideRoom The location of the inside room.
     */
    public void setInsideRoom(Location insideRoom){
        this.insideRoom = insideRoom;
    }

    /**
     * Get the room in which the door is located in for this door.
     * @return insideRoom The location of the inside room.
     */
    public Location getInsideRoom(){
        return this.insideRoom;
    }

    /**
     * Save this Location.
     * @param saveFile A BufferedWriter for the file to be saved
     * @throws IOException Throws exception if an error occurs during the save operation
     **/
    public void save(BufferedWriter saveFile) throws IOException{

            super.save(saveFile);

        //output the status of the door as a string.
        saveFile.write(Boolean.toString(isLocked));
        saveFile.newLine();

    }

    /**
     * Restore the Location.
     * @param saveFile The bufferedReader to read the file.
     * @param locationFinder A hashMap used to obtain locations.
     */
    public void restore(BufferedReader saveFile, HashMap<Integer, Location> locationFinder, HashMap<Integer, Item> itemsMap){

        try{
            super.restore(saveFile,locationFinder,itemsMap);

            //Check if the door was opened in the previous game.
            if (saveFile.readLine().equalsIgnoreCase("FALSE")) this.isLocked = false;

                //The door is locked.
            else this.isLocked = true;

        } catch (IOException ieo){
            System.out.println("Could not restore the saved file.");
            System.out.println(ieo.getMessage());
            System.out.println("\n");
        }
    }

}
