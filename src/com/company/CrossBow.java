package com.company;

import java.util.ArrayList;

/**
 * A cross bow is a weapon used to kill a beast.
 */
public class CrossBow extends Item{



    /**
     * Create a crossBow.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     */
    public CrossBow(String name, String description, double mass, int code) {
        super(name, description, mass, code);
    }

    /**
     * Use the cross bow to kill the beast.
     *
     * @param mainPlayer The mainPlayer of the game.
     * @param item The item being used.
     */
    public void use(Player mainPlayer, Item item) {
        Beast beast = Beast.getInstance();
        if(mainPlayer.getCurrentLocation().equals(beast.getCurrentLocation())){
            System.out.println("You have killed the bear! ");
            Game.amountOfActions++; //Increase amount of actions made.
            Game.score += 40; //Increase the score of the user.
            beast.setIsDead(true);  //Kill the beast.
        } else System.out.println("The cross-bow is no use to you here. ");
    }


}
