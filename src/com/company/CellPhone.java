package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class CellPhone extends Item {
    /**
     * The battery life of the Phone.
     **/
    private int battery = 5;

    /**
     * The taxi that this phone calls
     **/
    private Taxi taxi = null;

    /**
     * Create cellphone.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param mass The mass of the item.
     * @param code The indicator code of the item.
     */
    public CellPhone(String name, String description, double mass, int code) {
        super(name, description, mass, code);
    }

    /**
     * Check what the phone has on the front screen.
     *
     * @param mainPlayer The mainPlayer of the game.
     */
    public void use(Player mainPlayer, Item item) {

        boolean inService; //A boolean to indicate whether the phone has service or not

        //Check if the iphone is off the island.
        if (mainPlayer.getCurrentLocation().getCode() == 19 || mainPlayer.getCurrentLocation().getCode() == 21
                || mainPlayer.getCurrentLocation().getCode() == 22 || mainPlayer.getCurrentLocation().getCode() == 24) inService = true;
        else inService = false;

        if (this.battery == 0) System.out.println("The Iphone died.\n\n");
        else if (!inService) {
            System.out.println("The Iphone is not in service. The Iphone only has " + this.battery + " percent battery life left. There is a text on the phone that reads\n" +
                    "'Bro! We just barely got away!! We were so out of it we didn't even know you were gone.\nWhere are you dude?" +
                    " The wedding starts in 5 hours! Oh btw i'm pretty sure you\nlost the ring at the beach.\n\n");
            this.battery--;
            Game.amountOfActions++; //Increase the amountOFActions performed.
        } else {
            //If the mainPlayer is on the street, or the wedding then a taxi can be called.
            if (MainPlayer.getInstance().getCurrentLocation().getCode() == 21 || MainPlayer.getInstance().getCurrentLocation().equals(this.taxi.getDestination())) {
                System.out.println("A taxi has been called! The " + getName() + " has " + this.battery + " percent. ");
                this.taxi.call(this.taxi);
                this.battery--;
                Game.amountOfActions++; //Increase the amountOFActions performed.
            } else {
                System.out.println("The Iphone is in service. But the phone is no use to you now.\n" +
                        "The Iphone only has " + this.battery + " percent battery life left. ");
                Game.amountOfActions++; //Increase the amountOFActions performed.
            }
        }
    }

    /**
     * Set the taxi that this phone calls.
     * @param taxi The taxt this phone calls.
     */
    public void setTaxi(Taxi taxi){
        this.taxi = taxi;
    }

    /**
     * Save the phone.
     * @param saveFile The BufferedWriter to write to the file.
     * @throws IOException Throws an exception if there are any complications in the saving of the file.
     */
    public void save(BufferedWriter saveFile) throws IOException{

        //Output the code for this Phone.
        super.save(saveFile);

        //Output the percentage of battery life left.
        saveFile.write(Integer.toString(this.battery));
        saveFile.newLine();
    }

    /**
     * Restore the battery life percentage.
     * @param saveFile The bufferedReader to read the file.
     */
    public void restore(BufferedReader saveFile){

        try {
            //Update the battery life percentage.
           this.battery = Integer.parseInt(saveFile.readLine());

        } catch (IOException ieo){
            System.out.println("Could not restore the saved file.");
            System.out.println(ieo.getMessage());
            System.out.println("\n");
        }
    }

    /**
     * Get the battery life of the phone.
     * @return An integer of the battery life of the iphone.
     */
    public int getBattery(){
        return this.battery;
    }

}
