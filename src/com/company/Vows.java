package com.company;

/**
 * Vows is an item that can be written and it needs to be with the player at the end to win.
 */
public class Vows extends Item {
/** The writing that make up the actual vows **/ private String writing = "";
/** A counter to indicate how many times the user has written a vow**/ private int counter = 0;

    /**
     * Create blank vows.
     * @param name A brief description for the item.
     * @param description A detailed description for the item.
     * @param mass The mass of the item.
     * @param code An indicator for the item.
     */
    public Vows(String name, String description, double mass, int code){
        super(name,description, mass, code);
    }

    /**
     * Write the vows.
     * @param mainPlayer The mainPlayer of the game.
     * @param item The item that is being used.
     */
    public void use(Player mainPlayer, Item item) {
        //Add vows to the players backpack
        if(!mainPlayer.getBackpack().hasItem(item)) mainPlayer.take(item);

        //Prompt the user to write a line of vows.
        System.out.println("Write some vows: \n");
        this.writing = " " + this.writing + Game.CONSOLE.nextLine();
        if(this.counter==0) {
            System.out.println("Wow brilliant! That was so good that your fiance might forget that you almost missed your own wedding.\n" +
                    "You can write more if anything comes to you.\n");
            Game.amountOfActions++; //Increase the amountOFActions performed.
        }else System.out.println("That is truly poetic! Feel free to write more if you want. \n");
        Game.amountOfActions++; //Increase the amountOFActions performed.
        this.counter++; //Increase the counter because a line of vows has been written.
    }

    /**
     * Obtain the vows written by the user.
     * @return The string of vows.
     */
    public String getWriting(){
        return this.writing;
    }

}
