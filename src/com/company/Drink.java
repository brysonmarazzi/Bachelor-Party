package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * A drink is an item which gives the player the ability to move.
 */
public class Drink extends Item {

    /**
     * Create BearMace
     * @param name The brief name of the item.
     * @param description The detailed description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     */
    public Drink(String name, String description, double mass, int code) {
        super(name, description, mass, code);
    }

    /**
     * Drink the drink, and sober the player.
     * @param mainPlayer The mainPlayer of the game.
     * @param item The item being used.
     */
    public void use(Player mainPlayer, Item item) {
        //Check if the player is at the water fall
        if(mainPlayer.getCurrentLocation().getCode() == 17){
            System.out.println("hmmm! Wow that was well needed! Full health again! ");
            mainPlayer.setHealth(100);
            Game.amountOfActions++; //Update amount of actions taken.
        }

        //Drink the liquid, set mainPlayer to sober.
        mainPlayer.setIsDrunk(false);

        //Remove Drink from the game.
        if(mainPlayer.getBackpack().hasItem(item)) mainPlayer.getBackpack().remove(item);
        else mainPlayer.getCurrentLocation().removeItem(item);
        Game.score += 10; //Update the score.
        Game.amountOfActions++; //Increase the amountOFActions performed.
        System.out.println("mhhhh much better!\n");
    }
}