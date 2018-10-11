package com.company;

import java.util.ArrayList;

/**
 * Zipline is an item that transports you to another location lower down.
 * You can't go back to the top with the back method at the bottom of the zipLIne.
 */
public class ZipLine extends Item {

    /** The location that the zipline connects to. **/ private Location baseLocation = null;

    /**
     * Create a zipLine
     * @param name Brief name for the the zipline.
     * @param description Detailed name for the zip Line.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     */
    public ZipLine(String name, String description, double mass, int code){
        super(name,description, mass, code);
    }


    /**
     * Travel from the top of the zipLIne to the base location.
     * @param mainPlayer The mainplayer of the game.
     */
    public void use(Player mainPlayer, Item item){
        //Transport the player to the baseLocation.
        mainPlayer.setLocation(baseLocation);
        System.out.println("WEEEE!! " + mainPlayer.getCurrentLocation().getDescription());
        Game.amountOfActions++; //Increase the amountOFActions performed.
    }

    /**
     * Set the location for this zipLine
     * @param baseLocation The location that this zip Line connects to.
     */
    public void setBaseLocation(Location baseLocation){
        this.baseLocation = baseLocation;
    }


}
