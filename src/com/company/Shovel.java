package com.company;

import java.util.ArrayList;

/**
 * A shovel is an item that can dig a tunnel and find the ring.
 */
public class Shovel extends Item {

    /**
     * Locations where the shovel is of use
     **/
    private ArrayList<Location> useLocations;
    /**
     * The ring this shovel is used to find.
     **/
    private Ring ring;

    /** The tunnel this shovel can dig **/ private Tunnel tunnel;

    /**
     * @param name The name of the item.
     * @param description The description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     * @param tunnel The tunnel this shovel can dig.
     * @param ring The ring this shovel helps to find.
     */
    public Shovel(String name, String description, double mass, int code, Ring ring,Tunnel tunnel) {
        super(name, description, mass, code);
        this.ring = ring;
        this.useLocations = new ArrayList<Location>();
        this.tunnel = tunnel;
    }

    /**
     * Use the shovel to find the ring. And dig a tunnel.
     *
     * @param mainPlayer The mainPlayer of the game.
     */
    public void use(Player mainPlayer, Item item) {

        //Check that the current location of the mainPlayer is the beach and that the ring was found first.
        if (mainPlayer.getCurrentLocation().equals(this.useLocations.get(0)) && this.ring.isFound()) {

            //Add the ring to the player's backpack.
            mainPlayer.getBackpack().add(ring);
            System.out.println("You now have the " + ring.getName() + " in your backpack!\n" +
                    "Your wife never has to know that you had lost her ring on an island.\n");
            Game.amountOfActions++; //Increase the amountOFActions performed.
            Game.score += 20; //Update the score.
            //Check the current location is the hole. and the tunnel has not been dug yet.
            } else if (mainPlayer.getCurrentLocation().equals(this.useLocations.get(1)) && !this.tunnel.isTunnelDug()) {

            //Add a tunnel to the players current location (the hole) and to the tunnel's other side. And set the tunnels side1 as the hole.
            mainPlayer.getCurrentLocation().addItem(tunnel);
            tunnel.getSide2().addItem(tunnel);
            tunnel.setSide1(mainPlayer.getCurrentLocation());
            System.out.println("You have dug a tunnel!\n");
            this.tunnel.setisTunnelDug(true);
            if(!tunnel.isTunnelDug()) Game.score += 20; //Update the score.
            Game.amountOfActions++; //Increase the amountOFActions performed.
            //The ring is not in this location so the shovel has no use.
        } else System.out.println(getName() + " is no use to you now.\n");

    }

    /**
     * Add a location to the useLocations list.
     * @param useLocation A location to add.
     */
    public void addUseLocation (Location useLocation){
        useLocations.add(useLocation);
    }
}
