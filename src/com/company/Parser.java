package com.company;

//Name: Bryson Marazzi , SN: 300136773
/**
 * A Parser is a simple 2-word (action + object) string parser.
 * Note: This code was adapted from Professor Paul Rushton's code.
 */
public class Parser {
    /** The valid words. */
    private static final String[] WORDS = {
            "HELP", "QUIT", "SAVE", "LOAD", "GO", //4
            "TAKE", "DROP", "USE", "NORTH", "EAST", // 9
            "SOUTH", "WEST", "UP", "DOWN","IN","OUT", "KEYS", "WALLET", "SUNGLASSES", //16
            "GOLD", "RING", "BOAT" , "LOOK", "INVENTORY", //21
            "BACK", "SCORE", "WEAR", "SUNGLASSES", "LIQUID", "DRINK", //27
            "SHIRT", "SHOVEL", "IPHONE","LOAD","KEY","DOOR","METAL-DETECTOR",
            "WATER", "WOOD","FLINT","ZIPLINE","TUNNEL","VOWS","WRITE","4317","BRIDGEVIEW",
            "HELICOPTER-KEY","2007","FORNOW", "PET", "Momma-bear","BEAR-MACE","CROSS-BOW",
    };

    /** Boolean variable to state whether the input is proper**/ private static boolean isInputProper = false;
    /**
     * Find a word.
     * @param word A String.
     * @return the index position for the word in WORDS,
     * or -1 if the word does not exist in WORDS.
     */
    public static final int find(String word) {
        for (int i = 0; i < WORDS.length; i++) {
            if (WORDS[i].equalsIgnoreCase(word)) return i;
        }
        return -1;
    }
    /** The valid commands. All entries are the index position
     * of a word in the WORDS table. The first entry in each
     * row defines a valid action. The remaining entries,
     * if any, define the valid objects.
     */
    private static final int[][] PARSE_TABLE = {
            { find("HELP") },
            { find("QUIT") },
            { find("SAVE") },
            { find("LOAD") },
            { find("GO"), find("NORTH"), find("EAST"), find("SOUTH"), find("WEST") ,
                    find("UP") , find("DOWN") ,find("IN") ,find("OUT")},
            { find("TAKE"), find("RING"), find("WALLET"), find("SUNGLASSES"), find("LIQUID"),
                    find("SHIRT"), find("IPHONE") ,find("SHOVEL"),find("KEY"),find("METAL-DETECTOR"),
                    find("WOOD"),find("FLINT"),find("HELICOPTER-KEY"),find("VOWS"),find("BEAR-MACE"),find("CROSS-BOW")},
            { find("DROP"), find("RING"), find("WALLET"), find("SUNGLASSES"), find("LIQUID"),
                    find("SHIRT"), find("IPHONE"),find("SHOVEL"),find("KEY"),find("METAL-DETECTOR"),
                    find("WOOD"),find("FLINT"),find("HELICOPTER-KEY"),find("VOWS"),find("BEAR-MACE"),find("CROSS-BOW")},
            { find("USE"), find("KEYS"), find("WALLET"),find("SHOVEL"),find("IPHONE"),find("KEY"),
                    find("METAL-DETECTOR"),find("WOOD"),find("FLINT"),find("ZIPLINE"), find("TUNNEL"),
                    find("HELICOPTER-KEY"),find("BEAR-MACE"),find("CROSS-BOW")},
            { find("LOOK")},
            { find("INVENTORY")},
            { find("BACK")},
            { find("SCORE")},
            { find("WEAR"), find("SUNGLASSES"), find("SHIRT")},
            { find("DRINK"), find("LIQUID"), find("WATER")},
            { find("LOAD")},
            { find("transport")},
            { find("WRITE"), find("VOWS")},
            {find("4317"),find("BRIDGEVIEW")},
            {find("2007"),find("FORNOW")},
            {find("PET"),find("Momma-bear")},

    };
    /**
     * Parse a sentence.
     * @param sentence An input String.
     * @return an int[] that represents the parsed sentence. If
     * the sentence contains no words then "null" is
     * returned. If the sentence contains a valid 1-word
     * command then an int[] of length 1 is returned and
     * the row number, in PARSE_TABLE, for the command is
     * the entry value. If the sentence contains a valid
     * 2-word command then an int[] of length 2 is
     * returned and the row number for the command, in
     * PARSE_TABLE, is the first entry value and the word
     * number, in WORDS, is the second entry value. If
     * the sentence contains more than two words or if
     * the sentence is invalid then an int[] of length 1
     * is returned and the entry value is -1.
     */
    public static int[] parse(String sentence) throws IllegalCommandException {
// Obtain the individual words in the sentence.
        String[] words = sentence.trim().split("\\s+");
        if (words[0].length() < 1) throw new IllegalCommandException("You did not enter anything!");
// The first word must be a valid action.
        int index = find(words[0]);
        if (index < 0) {
            throw new IllegalCommandException("What?\n");
        }
        int action = 0;
        while (action < PARSE_TABLE.length) {
            if (index == PARSE_TABLE[action][0]) break;
            action++;
        }
        if (action >= PARSE_TABLE.length){
            throw new IllegalCommandException("I don't understand.\n");
        }

        // Check for valid action word that requires object but only a 1-word command was given.
        if(words.length == 1 && PARSE_TABLE[action].length > 1){
            throw new IllegalCommandException("I don't understand.\n");
        }

// Check for a 1-word command.
        if (words.length == 1){
            isInputProper = true;
            return new int[] { find(words[0]) };
        }

// The second word must be a valid object.
        index = find(words[1]);
        for (int i = 1; i < PARSE_TABLE[action].length && index > 0; i++) {
            if (index == PARSE_TABLE[action][i]) {
                if (words.length == 2){
                    isInputProper = true;
                    return new int[] { find(words[0]), index };
                } else{
                    throw new IllegalCommandException("Too many words! \n");
                }
            }
        }
        throw new IllegalCommandException("You can't " + words[0] + " '" + words[1] + "'.\n");
    }


    /**
     * return the success of the input given
     * @return The state of the input.
     */
    public static boolean isInputProper(){
        return isInputProper;
    }

    /**
     * Set the status of the Input Success Indicator Variable
     * @param newStatus Boolean to indicate the success.
     */
    public static void setInputSuccess(boolean newStatus){
        isInputProper = newStatus;
    }
}


