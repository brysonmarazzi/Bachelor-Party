package com.company;

/**
 * The main Player of the game.
 * @author Bryson Marazzi SN: 300136773
 */
public class MainPlayer extends Player {

    /** The player instance. */ private static final Player INSTANCE = new Player();
    /**
     * Create a Player.
     */
    private MainPlayer() {
        super();
    }

    /**
     * Obtain the player.
     * @return the Player instance.
     */
    public static Player getInstance() {
        return MainPlayer.INSTANCE;
    }

}
