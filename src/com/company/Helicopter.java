package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Helicopter is a location that can move and transport a player.
 */
public class Helicopter extends Location {

    /** The Location that this transportation gets the user to **/ private Location destination;
    /** The Location where this location is called **/ private Location landingPad;
    /** The description of the **/ private String operateDescription;
    /** The direction to go to dismount out of this transportation **/ private int dismountDirection;
    /** The direction to go to mount into this transportation **/ private int mountDirection;

    /**
     * Create a location
     * @param description A detailed description of the location and any items that exist in the location.
     */
    public Helicopter(String name, String description,int code,Location destination,Location landingPad,String operateDescription,int dismountDirection,int mountDirection){
        super(name,description,true,code);
        this.destination = destination;
        this.landingPad = landingPad;
        this.operateDescription = operateDescription;
        this.dismountDirection = dismountDirection;
        this.mountDirection = mountDirection;
    }

    /**
     * Obtain the called Location of this transportation.
     * @return The called Location of this transportation.
     */
    public Location getLandingPad(){
        return this.landingPad;
    }

    /**
     * Obtain the destination Location of this transportation.
     * @return the destination Location of this transportation.
     */
    public Location getDestination(){
        return this.destination;
    }


    /**
     * The Transportation is ok to take off, remove the exit so the player cannot go back to the island, add an exit to the main land.
     */
    public void operate(Helicopter helicopter){
        //Check if the player is already at their destination and wants to go back.
        //Change the destination to where ever the player first used the transportation.
        if(this.getExit(this.dismountDirection).equals(this.destination)){
            this.destination = this.landingPad;
            this.landingPad = this.getExit(this.dismountDirection); //Change the landing pad to the players current location.
        }

        //Remove the exit between the transportation and the location the transportation was called to.
        Game.removeLocationConnector(this.landingPad,helicopter,mountDirection);

        //Add an exit between the transportation and the destination.
        addExit(this.dismountDirection,this.destination);

        Game.createLocationConnector(helicopter,this.destination,this.dismountDirection);
        //Tell the player that they have successfully used the transportation to move to another location.
        System.out.println(this.operateDescription);
    }

    /**
     * Call the transportation which makes a exit between the player's current location and this transportation location.
     * @param helicopter This transportation location.
     */
    public void call(Helicopter helicopter){

        //Make the transportation location accessible to the user.
        Game.createLocationConnector(this.landingPad,helicopter,mountDirection);

        //Notify the user that there is a helicopter accessible at the landing pad.
        this.landingPad.setDescription(this.landingPad.getDescription() + "There is a helicopter here. Get in!");
    }


}