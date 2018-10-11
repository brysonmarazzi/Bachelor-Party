package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * A ring is an item that needs to be found in order to win the game.
 */
public class Ring extends Item {

    /** A boolean to indicate whether the ring is found or not. **/ private boolean isFound = false;

    /**
     * @param name The name of the item.
     * @param description The description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     */
    public Ring(String name, String description, double mass, int code){
        super(name, description, mass, code);
    }

    /**
     * Set the state of the ring.
     * @param isFound A boolean to indicate whether the ring is found or not.
     */
    public void setIsFound(boolean isFound){
        this.isFound = isFound;
    }

    /**
     * Obtain knowledge of whether the ring was found by the player or not.
     * @return A boolean to indicate whether the ring is found or not.
     */
    public boolean isFound(){
        return this.isFound;
    }

    /**
     * Save this ring's current state.
     * @param saveFile The BufferedWriter to write to the file.
     * @throws IOException
     */
    public void save(BufferedWriter saveFile) throws IOException{
        //Output the code of the item.
        saveFile.write(Integer.toString(getCode()));
        saveFile.newLine();

        //output the status of the ring as a string.
        saveFile.write(Boolean.toString(isFound));
        saveFile.newLine();
    }

    /**
     * Restore the status of the ring.
     * @param saveFile The bufferedReader to read the file.
     */
    public void restore(BufferedReader saveFile){

        try {

            //Check if the ring has been found in the previous game.
            if (saveFile.readLine().equalsIgnoreCase("TRUE")) this.isFound = true;

            //The ring has been found already.
            else this.isFound = false;

        } catch (IOException ieo){
            System.out.println("Could not restore the saved file.");
            System.out.println(ieo.getMessage());
            System.out.println("\n");
        }

    }



}
