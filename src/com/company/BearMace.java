package com.company;

/**
 * Bear Mace is an item that is used to send the bear back to it's home.
 */
public class BearMace extends Item {

    /**
     * Create BearMace
     * @param name The name of the item.
     * @param description The description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     */
    public BearMace(String name, String description, double mass, int code){
        super(name,description, mass, code);
    }

    public void use(Player player,Item item){
        //Beast.getInstance().setIsAwake(false);
        if(player.getCurrentLocation().equals(Beast.getInstance().getCurrentLocation())) {
            System.out.println("You sprayed bear-mace right in the eyes of the bear!\n" +
                    "The bear is so stunned it ran all the way to it's home. ");
            Game.amountOfActions++; //Increment the amountOfActions.
            Beast.getInstance().goHome();
        }else System.out.println(getName() + " is no use to you right now. ");
    }
}
