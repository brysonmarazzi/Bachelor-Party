package com.company;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    /** The name of this description**/ private String name;
    /** Detailed description*/ private String description;
    /** Items at this location*/ private ArrayList<Item> items;
    /** A boolean variable indicating whether a location is indoors or outdoors **/ private boolean isIndoors;
    /** A hashMap to find the exits of the location **/ private HashMap<Integer,Location> exitFinder;
    /** An identifier code for the location, which are put in order of locations on map. **/ private int code;
    /** A boolean variable indicating whether a location is marked or not**/ private boolean marked = false;

    /**
     * Create a location
     * @param description A detailed description of the location and any items that exist in the location.
     */
    public Location(String description, boolean isIndoors,int code,Integer direction,Location exit){
        this.description = description;
        this.items = new ArrayList<Item>();
        this.isIndoors = isIndoors;
        this.exitFinder = new HashMap<Integer, Location>();
        exitFinder.put(direction,exit);
        this.code = code;
    }

    /**
     * Create a location
     * @param description A detailed description of the location and any items that exist in the location.
     */
    public Location(String name,String description, boolean isIndoors,int code){
        this.name = name;
        this.description = description;
        this.items = new ArrayList<Item>();
        this.isIndoors = isIndoors;
        this.exitFinder = new HashMap<Integer, Location>();
        this.code = code;
    }

    /**
     * Mark this location as unique.
     */
    public void mark(){
        this.marked = true;
    }

    /**
     * Unmark this location.
     */
    public void unMark(){
        this.marked = false;
    }

    /**
     * Obtain information about whether the location is marked or not.
     * @return Boolean, true if marked, false if not.
     */
    public boolean isMarked(){
        return this.marked;
    }

    /**
     * Obtain the description of this location.
     * @return a detailed description of this location with descriptions of the items that are present.
     */
    public String getDescription(){
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(this.description).append("\n");

        //Add the adjacent location's names to the description of this location. If they have names.
        if(this.exitFinder.containsKey(Game.SOUTH)){
            if(!this.exitFinder.get(Game.SOUTH).getName().isEmpty())
                sBuilder.append("There is " + this.exitFinder.get(Game.SOUTH).getName() + " in the south. ");
        }

        if(this.exitFinder.containsKey(Game.NORTH)) {
            if (!this.exitFinder.get(Game.NORTH).getName().isEmpty())
                sBuilder.append("There is " + this.exitFinder.get(Game.NORTH).getName() + " in the north. ");
        }

        if(this.exitFinder.containsKey(Game.EAST)) {
            if (!this.exitFinder.get(Game.EAST).getName().isEmpty())
                sBuilder.append("There is " + this.exitFinder.get(Game.EAST).getName() + " in the east. ");
        }

        if(this.exitFinder.containsKey(Game.WEST)){
            if(!this.exitFinder.get(Game.WEST).getName().isEmpty())
                sBuilder.append("There is " + this.exitFinder.get(Game.WEST).getName() + " in the west. ");
        }

        if(this.exitFinder.containsKey(Game.UP)) {
            if (!this.exitFinder.get(Game.UP).getName().isEmpty())
                sBuilder.append("There is " + this.exitFinder.get(Game.UP).getName() + " above you. ");
        }

        if(this.exitFinder.containsKey(Game.DOWN)) {
            if (!this.exitFinder.get(Game.DOWN).getName().isEmpty())
                sBuilder.append("There is " + this.exitFinder.get(Game.DOWN).getName() + " below you. ");
        }

        sBuilder.append("\n"); //Create a new line.

        //Add the description of the items to the location description.
        for ( int i = 0; i < this.items.size(); i++) {
            sBuilder.append(this.items.get(i).getDescription());
        }

        //sBuilder.append("\n");
        return sBuilder.toString();
    }

    /**
     * Obtain the exits of this location.
     * @return The exits locations of this location.
     */
    public ArrayList<Location> getExits(){

        ArrayList<Location> exits = new ArrayList<Location>();
        for (Integer direction : this.exitFinder.keySet()) {
            if(direction != Game.IN && direction != Game.OUT && direction != 90) {
                Location exitLocation = exitFinder.get(direction);
                exits.add(exitLocation);
            }
        }
        return exits;
    }

    /**
     * Obtain the directions from this location.
     * @return The directions from this location.
     */
    public ArrayList<Integer> getDirections(){
        ArrayList<Integer> directions = new ArrayList<Integer>();
        for (Integer direction : this.exitFinder.keySet()){
            if (direction != Game.IN && direction != Game.OUT && direction != 90) directions.add(direction);
        }
        return directions;
    }

    /**
     * Set the description of this location.
     * @param description A string to set as the description of this Location.
     */
   public void setDescription(String description){
        this.description = description;
    }

    /**
     * Obtain the name of this location.
     */
    public String getName(){
       return this.name;
    }

    /**
     * A method to see if a certain direction, from a specific location will lead to a dead end.
     * @param player The player who is being followed, which has a current location of interest.
     * @param start The starting location of the path. ( also the beast's current location.).
     * @param direction The direction that is being questioned.
     * @return Boolean, true if the path leads directly to a location with only one exit.
     * False, if the direction leads to the location of the player indicated, or if the direction leads to a fork in the road.
     */
    public boolean isDeadEnd(Player player,Location start, int direction) {

        if(start.getExit(direction).getExits().size() == 1) return true;
        Location previous = start;
        start = start.getExit(direction);

        while (!start.equals(player.getCurrentLocation())) {
            int amountOfExits = start.getDirections().size();
            if (amountOfExits > 2) return false;
            if (start.getExits().size() == 1) return true;
            direction = start.getDirections().get(0);
            if (!start.getExit(direction).equals(previous)) {
                previous = start;
                start = start.getExit(direction);
            } else {
                direction = start.getDirections().get(1);
                previous = start;
                start = start.getExit(direction);
            }
        }
        return false;
    }




    /**
     * Add an exit to this location.
     * @param direction The String of the direction towards the exit.
     * @param exit The exits location.
     */
    public void addExit(int direction, Location exit){
        if(exit.isIndoors() && direction != Game.UP && direction != Game.DOWN && direction != 90 && direction != Game.IN) this.exitFinder.put(Game.IN,exit);
        if(this.isIndoors && direction != Game.UP && direction != Game.DOWN && direction != 90 && direction != Game.OUT) this.exitFinder.put(Game.OUT,exit);
        this.exitFinder.put(direction,exit);
    }

    /**
     * Obtain knowledge of whether an location is a exit for this location.
     * @param exit The location being tested.
     * @return A boolean, true if the location is an exit, false if not.
     */
    public boolean isExit(Location exit){
        return this.exitFinder.containsValue(exit);
    }

    /**
     * Obtain the exit that pertains to a specific direction.
     * @param direction String of direction of location.
     * @return Null if there is no location in that direction or The location of this exit that leads in the specified direction.
     */
    public Location getExit(int direction){
        if (exitFinder.containsKey(direction)) return this.exitFinder.get(direction);
        else return null;
    }

    /**
     * Take the exit out of this location.
     * @param direction The direction the user wants to go
     */
    public void takeExit(Player player,int direction){
        player.setLocation(getExit(direction));
    }

    /**
     * Remove an exit from this location
     * @param direction The direction to remove.
     */
    public void removeExit(int direction,Location exit){

        if(exit.isIndoors() && direction != Game.UP && direction != Game.DOWN && direction != 90) this.exitFinder.remove(Game.IN);
        if(this.isIndoors && direction != Game.UP && direction != Game.DOWN && direction != 90) this.exitFinder.remove(Game.OUT);
        this.exitFinder.remove(direction);
    }

    /**
     * Obtain knowledge of whether this location has a location in the direction specified.
     * @param direction The direction integer.
     * @return True if  this location has a location in the direction specified, false if not.
     */
    public boolean hasDirection(int direction){
        if(exitFinder.containsKey(direction)) return true;
        else return false;
    }


    /**
     * Obtain the list of items in this Location.
     * @return List of Items.
     */
    public ArrayList<Item> getItems(){
        return this.items;
    }

    /**
     * Add an item to this location.
     * @param item An item.
     */
     public void addItem(Item item) {
        this.items.add(item);
     }

    /**
     * Save this Location.
     * @param saveFile A BufferedWriter for the file to be saved
     * @throws IOException Throws exception if an error occurs during the save operation
     **/
    public void save(BufferedWriter saveFile) throws IOException{

        //Output amount of exits in this location.
        saveFile.write(Integer.toString(this.exitFinder.size()));
        saveFile.newLine();

        //Output the exits and their directions.
        for (Integer direction : exitFinder.keySet()) {
                Location exitLocation = exitFinder.get(direction);
                saveFile.write(Integer.toString(direction));
                saveFile.newLine();
                saveFile.write(Integer.toString(exitLocation.getCode()));
                saveFile.newLine();
        }

        //Output the amount of items in this location.
        saveFile.write(Integer.toString(items.size()));
        saveFile.newLine();

        //Save all the items in this location.
        for (Item item : items){
            item.save(saveFile);
        }
    }

    /**
     * Restore the Location.
     * @param saveFile The bufferedReader to read the file.
     * @param locationFinder A hashMap used to obtain locations.
     */
    public void restore(BufferedReader saveFile, HashMap<Integer,Location> locationFinder,HashMap<Integer, Item> itemsMap){

        try {
            //Restore the amount of exits in this Location.
            int amountOfExits = Integer.parseInt(saveFile.readLine());

            //Empty the exit finder to update.
            this.exitFinder.clear();

            //Refill the exit finder for this location.
            for (int i = 0; i < amountOfExits; i++){
                int direction = Integer.parseInt(saveFile.readLine()); //Obtain the direction for this exit.
                int exitCode = Integer.parseInt(saveFile.readLine()); //Obtain the location code of this exit.
                Location exitLocation = locationFinder.get(exitCode); //Obtain the location of this exit.
                this.exitFinder.put(direction,exitLocation);  //Fill the exitFinder hashMap with exits.
            }

            //Obtain the amount of items in this location.
            int amountOfItems = Integer.parseInt(saveFile.readLine());

            //Clear the location's items.
            this.items.clear();

            //Restore each item in this location.
            for (int i = 0; i < amountOfItems; i++){
                int itemCode = Integer.parseInt(saveFile.readLine()); //Obtain the item code.
                Item item = itemsMap.get(itemCode); //Obtain the item.

                // Add the item to the items arrayList for this location.
                this.items.add(item);

                //Restore anything that changed about this item.
                item.restore(saveFile);
            }

        } catch (IOException ieo){
            System.out.println("Could not restore the saved file.");
            System.out.println(ieo.getMessage());
            System.out.println("\n");
        }
    }



    /**
     * Remove an item from this location.
     * @param item An item.
     * @return true if the item was found in the location, and false is the item was not found.
     **/
    public boolean removeItem(Item item){
        //Check to see if there are any items present
        if(items.size() < 1) {
            System.out.println("There are no items around to take!\n");
            return false;
        }

        //Find item
         for (int i = 0; i < this.items.size(); i++) {
             if (item == this.items.get(i)){

                 //Item is found, remove it.
                 this.items.remove(i);
                 return true;
             }
         }

         //Item was not found
         System.out.print(item.getName() + " is not in this location!\n");
         return false;
     }

    /**
     * Obtain the code for this location.
     * @return The code int.
     */
     public int getCode(){
        return this.code;
     }

    /**
     * Remove all the items from this Location
     */
    public void removeAllItems(){
        this.items.clear();
     }

    /**
     * A boolean statement indicating whether or not the location is indoors.
     * @return The boolean variable of the location.
     */
    public boolean isIndoors(){
        return this.isIndoors;
     }

}
