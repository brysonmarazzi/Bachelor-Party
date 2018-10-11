package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tunnel extends Item {

    /** One side of the tunnel **/ private Location side1 = null;
    /** Other side of the tunnel **/ private Location side2 = null;
    /** Boolean to indicate if the shovel has dug a tunnel or not **/ private boolean isTunnelDug = false;

    /**
     * Create a tunnel
     * @param name Brief name.
     * @param description Detailed name.
     */
    public Tunnel(String name, String description, double mass, int code){
        super(name,description, mass, code);
    }

    /**
     * Transport the user from one side of the tunnel to another.
     * @param mainPlayer The mainplayer of the game.
     * @param item The tunnel.
     */
    public void use(Player mainPlayer, Item item){
        try {
            System.out.println("You traveled from one end of the tunnel to the other.");
            mainPlayer.move(mainPlayer,90);
            Game.amountOfActions++; //Increase the amountOfActions performed.

    } catch (IllegalCommandException e){
        System.out.println(e.getMessage());
    }
    }

    /**
     * Set the first side of the tunnel.
     * @param side1 The first side of this tunnel.
     */
    public void setSide1(Location side1){
        this.side1 = side1;
    }

    /**
     * Set the second side of the tunnel.
     * @param side2 The second side of this tunnel.
     */
    public void setSide2(Location side2){
        this.side2 = side2;
    }

    /**
     * Set the state of the tunnel.
     * @param isTunnelDug The boolean of the tunnels state.
     */
    public void setisTunnelDug(boolean isTunnelDug){
        this.isTunnelDug = isTunnelDug;

        //Add the sides as exits that cannot be accessed without going through the tunnel but just so the back function will work.
        side1.addExit(90,side2);
        side2.addExit(90,side1);
    }

    /**
     * Obtain knowledge of whether the tunnel has been dug or not.
     * @return
     */
    public boolean isTunnelDug(){
        return this.isTunnelDug;
    }

    /**
     * Obtain this tunnel's second location.
     * @return This tunnel's second location.
     */
    public Location getSide2(){
        return this.side2;
    }

    /**
     * Save the tunnel state.
     * @param saveFile The BufferedWriter to write to the file.
     * @throws IOException Throws an exception if there are any complications in the saving of the file.
     */
    public void save(BufferedWriter saveFile) throws IOException{

        //Output the code for this tunnel.
        saveFile.write(Integer.toString(getCode()));
        saveFile.newLine();

        //Output the state of the tunnel
        saveFile.write(Boolean.toString(this.isTunnelDug));
        saveFile.newLine();
    }

    /**
     * Restore the state of the tunnel.
     * @param saveFile The bufferedReader to read the file.
     */
    public void restore(BufferedReader saveFile){

        try {
            //Update the status of the tunnel.
            this.isTunnelDug = Boolean.parseBoolean(saveFile.readLine());

        } catch (IOException ieo){
            System.out.println("Could not restore the saved file.");
            System.out.println(ieo.getMessage());
            System.out.println("\n");
        }
    }

}
