package com.company;
/**
 * Taxi is a location that can move and transport a player and can be called.
 */
public class Taxi extends Location {

    /**
     * The Location that this transportation gets the user to
     **/
    private Location destination;
    /**
     * The Location where this location is called
     **/
    private Location calledLocation;
    /**
     * The description of the
     **/
    private String operateDescription;
    /**
     * The direction to go to dismount out of this transportation
     **/
    private int dismountDirection;
    /**
     * The direction to go to mount into this transportation
     **/
    private int mountDirection;

    /**
     *  Create a taxi.
     * @param name A short description of the taxi.
     * @param description An in depth description of the taxi.
     * @param code The indicator code for the taxi.
     * @param destination The location where the taxi takes you.
     * @param operateDescription The description that outputs when the taxi is used.
     * @param dismountDirection The direction to get out.
     * @param mountDirection The direction to go to get in the taxi.
     */
    public Taxi(String name,String description, int code, Location destination, String operateDescription, int dismountDirection, int mountDirection) {
        super(name,description, true, code);
        this.destination = destination;
        this.operateDescription = operateDescription;
        this.dismountDirection = dismountDirection;
        this.mountDirection = mountDirection;
    }

    /**
     * Obtain the called Location of this transportation.
     *
     * @return The called Location of this transportation.
     */
    public Location getCalledLocation() {
        return this.calledLocation;
    }

    /**
     * Obtain the destination Location of this transportation.
     *
     * @return the destination Location of this transportation.
     */
    public Location getDestination() {
        return this.destination;
    }

    /**
     * The Transportation is ok to take off, remove the exit so the player cannot go back to the island, add an exit to the main land.
     */
    public void operate(Taxi taxi) {

        //Remove the exit between the transportation and the location the transportation was called to.
        Game.removeLocationConnector(this.calledLocation, taxi, mountDirection);

        //Add an exit between the transportation and the destination.
        addExit(this.dismountDirection, this.destination);

        //Tell the player that they have successfully used the transportation to move to another location.
        System.out.println(this.operateDescription);
    }

    /**
     * Call the transportation which makes a exit between the player's current location and this transportation location.
     *
     * @param taxi This transportation location.
     */
    public void call(Taxi taxi) {

        Location playersLocation = MainPlayer.getInstance().getCurrentLocation(); //Get the players currentLocation.

        //Make the taxi location accessible to the user.
        Game.createLocationConnector(playersLocation, taxi, mountDirection);

        //Check if the player is already at their destination and wants to go back.
        //Change the destination to where ever the player first used the transportation.
        if (playersLocation.equals(destination)) this.destination = this.calledLocation;

        this.calledLocation = playersLocation; //Change the called location to the player's current location.
    }
}