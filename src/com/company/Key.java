package com.company;

/**
 * A key is an item that can open a door or operate a helicopter.
 */
public class Key extends Item {

/**  A key is an item that opens a door. **/  private Door door = null;

/** The helicopter that the key is used to operate. **/ private Helicopter helicopter = null;

    /**
     * Create a Key for a door.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     * @param door The door this key opens.
     */
    public Key(String name, String description, double mass, int code,Door door){
        super(name,description, mass, code);
        this.door = door;
    }


    /**
     * Create a Key for a helicopter.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     * @param helicopter The helicopter the key operates.
     */
    public Key(String name, String description, double mass, int code,Helicopter helicopter){
        super(name,description, mass, code);
        this.helicopter = helicopter;
    }


    /**
     * Open the door.
     * @param mainPlayer The mainPlayer of the game.
     * @param item
     */
    public void use(Player mainPlayer, Item item) {
        if(this.door != null) {
            //Check if the player is at the door or inside the room the door leads to.
            if (MainPlayer.getInstance().getCurrentLocation().equals(door) || MainPlayer.getInstance().getCurrentLocation().equals(door.getInsideRoom())){
                this.door.toggleLock();
                if (door.getIsLocked()){
                    System.out.println("You have locked the door. \n");
                    Game.removeLocationConnector(door,door.getInsideRoom(),Game.NORTH); //Remove the room that the door leads to.
                    Game.amountOfActions++; //Increase the amountOFActions performed.
                }
                else {
                    System.out.println("You have unlocked the door. \n");
                    Game.createLocationConnector(door,door.getInsideRoom(),Game.NORTH); //Make the inside room accessible.
                    Game.amountOfActions++; //Increase the amountOFActions performed.
                }//If the player is not close to the door then the key cant be used.
            } else System.out.println("You can't use the " + getName() + " here.\n");
        }

        if(this.helicopter != null){
            //Check if the player is in the helicopter.
            if(MainPlayer.getInstance().getCurrentLocation().equals(helicopter)){
                this.helicopter.operate(helicopter);
                Game.amountOfActions++; //Increase the amountOFActions performed.
            } else {
                System.out.println(this.getName() + " is no use to you here! ");
            }


        }

    }





}
