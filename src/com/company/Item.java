package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Item {
/** com.company.Item name */ private String description;
/** Detailed description */private String name;
/** Its mass */private double mass;
/** The code associated with this item **/ private int code;
/** The String that the default use method spits out. **/ private String useMessage = "";

    /**
     * Create a new Item
     * @param name The name of the Item.
     * @param description A detailed description of the item.
     * @param mass The mass of the Item in grams.
     * @param code The integer associated with the item.
     */
    public Item(String name, String description, double mass, int code){
        this.description = description;
        this.name = name;
        this.mass = mass;
        this.code = code;
    }

    /**
     * Obtain the brief description.
     * @return the description for this item.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Obtain the brief name for this item
     * @return the name for this item
     */
    public String getName(){
        return this.name;
    }

    /**
     * Obtain the mass for this item.
     * @return the mass for this item.
     */
    public double getMass(){
        return this.mass;
    }

    /**
     * Obtain the code for this item.
     * @return Integer of the code for this item.
     */
    public int getCode(){
        return this.code;
    }

    /**
     * Set the mass.
     * @param mass
     */
    public void setMass(double mass){
        this.mass = mass;
    }

    /**
     * Use an item.
     * @param mainPlayer The mainplayer of the game.
     */
    public void use(Player mainPlayer, Item item){
      System.out.println(this.useMessage);
      Game.amountOfActions++; //Increase the amountOFActions performed.
    }

    /**
     * Set what the default use method will output to the user.
     * @param useMessage The string for the use method.
     */
    public void setUseMessage(String useMessage){
        this.useMessage = useMessage;
    }

    /**
     * Restore this item.
     */
    public void restore(BufferedReader saveFile){
        //Only the items which have a unique characteristic to save will override this method.
        //Other items have a restore method which does nothing.
    }

    /**
     * Save this item.
     * @param saveFile The BufferedWriter to write to the file.
     * @throws IOException Checks for an error in the process of writing to the file.
     */
    public void save(BufferedWriter saveFile) throws IOException{
        saveFile.write(Integer.toString(this.code));
        saveFile.newLine();
    }

}
