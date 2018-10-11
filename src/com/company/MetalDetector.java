package com.company;

/**
 * A MetalDetector is an item that finds a ring.
 */
public class MetalDetector  extends Item{

/** The ring this metal detector finds. **/ private Ring ring;


    /**
     * Create a MetalDetector
     * @param name The brief name of the item.
     * @param description The detailed description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     */
    public MetalDetector(String name, String description, double mass, int code, Ring ring){
        super(name,description, mass, code);
        this.ring = ring;
    }

    /**
     * Find the ring.
     * @param mainPlayer The mainplayer of the game.
     * @param item the item.
     */
    public void use(Player mainPlayer, Item item) {

        //Check that the current location of the mainPlayer is the beach.
        if (mainPlayer.getCurrentLocation().getCode() == 14){

            //The ring is found.
            System.out.println("BEEP BEEP BEEP\nYou have found the " + ring.getName() + "! It is buried too deep to simply grab it.\n");
            ring.setIsFound(true);

            Game.amountOfActions++; //Increase the amountOFActions performed.
            if(!ring.isFound()) Game.score += 40; //Update the score.
            //The ring is not in this location so the metal detector has no use.
        }else  System.out.println(getName() + " is no use to you here!\n");
    }

}
