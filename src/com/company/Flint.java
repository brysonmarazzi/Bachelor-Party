package com.company;

/**
 * Flint is an object used to make fires that calls a helicopter to the location of the fire when the fire is made.
 * @author 300136773
 *
 */
public class Flint extends Item {

    /** The wood that works in association with this flint. **/ private Item fireWood;
    /** The transportation that picks up the mainPlayer once the flint has successfully been used. **/ private Helicopter helicopter = null;

    /**
     * Create Flint.
     * @param name The brief name of the item.
     * @param description The detailed description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     */
    public Flint(String name, String description, double mass, int code, Item wood){
        super(name,description, mass, code);
        this.fireWood = wood;
    }

    /**
     * Set the transportation for this flint.
     * @param helicopter The Transportation that the fire made by this flint calls
     */
    public void setHelicopter(Helicopter helicopter){
        this.helicopter = helicopter;
    }

    /**
     * The use method for flint which is used with fire wood.
     * @param mainPlayer The mainplayer of the game.
     * @param item
     */
    public void use(Player mainPlayer, Item item){

        //Check if there is fire wood on the ground.
        if(!mainPlayer.getCurrentLocation().getItems().contains(this.fireWood)) System.out.println("You can't make a fire without wood on the ground!\n");
        //Check if the player is outdoors
        else if(mainPlayer.getCurrentLocation().isIndoors()) System.out.println("You can't make a fire inside!\n");
        //The player is outdoors and has wood on the ground.
        else {
            //Remove flint from the game.
            if(mainPlayer.getBackpack().hasItem(item)) mainPlayer.getBackpack().remove(item);
            else mainPlayer.getCurrentLocation().removeItem(item);
            //call the helicopter
            this.helicopter.call(this.helicopter);
            System.out.println("You made a fire! A search and rescue helicopter saw the smoke and landed on the helicopter pad on the volcano. ");
            Game.amountOfActions++; //Increase the amountOFActions performed.
        }
    }
}
