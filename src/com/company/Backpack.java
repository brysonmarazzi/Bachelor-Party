package com.company;

import java.util.ArrayList;

public class Backpack {
    /** Remaining capacity. */ private double capacityRemaining;
    /** The contents. */ private ArrayList<Item> items;

    /**
     * Create an empty com.company.Backpack with a capacity limit.
     * @param capacity The carrying capacity of the backpack.
     */
    public Backpack(double capacity){
    this.capacityRemaining = capacity;
    this.items = new ArrayList<Item>();
    }

    /**
     * Add an item.
     * @param item An item to be added.
     * @return true if the item was added, or false if the item could not be added for some reason.
     */
    public boolean add(Item item){

        //The item must fit
        if( item.getMass() > this.capacityRemaining ) {
            System.out.println("You don't have enough space in your bag for a " + item.getName() + "!\n");
            return false;
        }
        if (Double.isNaN(item.getMass())){
            System.out.println("You can't take " + item.getName() + "!\n");
            return false;
        }

        //Add the item and reduce the capacity available
        this.items.add(item);
        this.capacityRemaining -= item.getMass();
        return true;
    }


    /**
     * Remove an item.
     * @param item An item to be removed.
     * @return true if the item was removed, or false if the item could not be removed for some reason.
     */
    public boolean remove(Item item){

        //False if there are no items.
        if(this.items.size() < 1){
            System.out.println("You don't have any Items!\n");
            return false;
        }

        //Find the item
        for ( int i = 0; i < this.items.size(); i++){
            if(items.get(i) == item ){

                //Item has been found, remove it and increase remaining capacity
                this.capacityRemaining += this.items.get(i).getMass();
                items.remove(i);
                return true;
            }
        }

        //Item was not in backpack.
        System.out.println("You don't have a " + item.getName() + " in your inventory!\n");
        return false;

    }

    /**
     * Obtain the items in this backpack
     * @return An ArrayList of object Item.
     */
    public ArrayList<Item> getItems(){
        return this.items;
    }

    /**
     * Obtain knowledge of where or not the item is present is this backpack.
     * @param parameterItem The item that is being checked.
     * @return A boolean statement. True if the item is in the backpack, false if not.
     */
    public boolean hasItem(Item parameterItem){
        for ( Item item : this.items){
            if (item.equals(parameterItem)) return true;
        }
        return false;
    }

    /**
     * Describe the contents of this backpack.
     * @return A String that lists the contents of this backpack or a message if this backpack is empty
     */
    public String inventory(){

        if(this.items.size() < 1 ) return null;

        //Separate the item names with commas.
        StringBuilder sBuilder = new StringBuilder();
        for ( int i = 0; i< this.items.size(); i++){
            if (i > 0) sBuilder.append(',').append(' ');
            sBuilder.append(this.items.get(i).getName());
        }
        return sBuilder.append("\n").toString();
    }
}
