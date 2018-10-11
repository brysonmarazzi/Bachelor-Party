package com.company;

/**
 * Sunglasses is an item that can help the player see.
 */
public class SunGlasses extends Item{

    /**
     * Create sunglasses.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     */
    public SunGlasses(String name, String description, double mass, int code){
        super(name, description, mass, code);
    }

    /**
     * Put on the sunglasses and change the Players vision.
     * @param mainPlayer The mainPlayer of the game.
     */
    public void use(Player mainPlayer, Item item){

            //Wear SunGlasses.
            mainPlayer.setIsWearingSunglasses(true);

            //Remove Sunglasses from the game.
            if(mainPlayer.getBackpack().hasItem(item)) mainPlayer.getBackpack().remove(item);
           else mainPlayer.getCurrentLocation().removeItem(item);
            Game.amountOfActions++; //Increase the amountOfActions performed.
            Game.score += 10; //Update the score.
            System.out.println("OK\n");

    }
}
