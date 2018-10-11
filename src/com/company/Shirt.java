package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Shirt is an item that can be worn and has an item in its pocket. Which is revealed when the shirt is worn.
 */
public class Shirt extends Item{

    /** A drink the player find in the pocket of the shirt **/ private Drink drink;
    /** Boolean to indicate if the player is wearing shirt already **/ private boolean isWearing = false;

    /**
     * Create a shirt.
     * @param name A brief description of the item.
     * @param description The detailed description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     * @param drink The item that is in the shirts pocket.
     */
    public Shirt(String name, String description, double mass, int code, Drink drink){
        super(name,description, mass, code);
        this.drink = drink;
    }

    /**
     * Put the shirt on and reveal a hidden item in the pocket.
     * @param mainPlayer The mainPlayer of the game.
     * @param item The item which is being used.
     */
    public void use(Player mainPlayer, Item item){

        //Check if the player is already wearing the shirt.
        if(!this.isWearing) {

            this.isWearing = true;
            System.out.println("Now you look a little less homeless! In the pocket of the " + getName() + " you find " + this.drink.getDescription());

            //Remove Shirt from the game.
            if(mainPlayer.getBackpack().hasItem(item)) mainPlayer.getBackpack().remove(item);
            else mainPlayer.getCurrentLocation().removeItem(item);

            Game.score += 10; //Update the score.
            Game.amountOfActions++; //Increase the amountOFActions performed.
            //Add the drink to the player's inventory.
            mainPlayer.getBackpack().add(this.drink);
        }
    }

    /**
     * Save this item's current state.
     * @param saveFile The BufferedWriter to write to the file.
     * @throws IOException
     */
    public void save(BufferedWriter saveFile) throws IOException{
        //Output the code of the item.
        saveFile.write(Integer.toString(getCode()));
        saveFile.newLine();

        //output the status of the shirt as a string.
        saveFile.write(Boolean.toString(isWearing));
        saveFile.newLine();
    }


    /**
     * Restore the status of the drink.
     * @param saveFile The bufferedReader to read the file.
     */
    public void restore(BufferedReader saveFile){
        try {
            //Check if the shirt has been worn in the previous game.
            if (saveFile.readLine().equalsIgnoreCase("TRUE")) this.isWearing = true;

                //The shirt has been worn already.
            else this.isWearing = false;

        } catch (IOException ieo){
            System.out.println("Could not restore the saved file.");
            System.out.println(ieo.getMessage());
            System.out.println("\n");
        }
    }
}
