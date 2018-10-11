package com.company;
/**
 * An IllegalCommandException defines a situation
 * 				 when the input is not valid. (i.e.
 * 				 the imputed action is not a valid
 * 				 action to perform)
 * @author 300136773
 *
 */
public class IllegalCommandException extends Exception {
    /**
     * Create the exception.
     * @param message The output to the user.
     */
    public IllegalCommandException(String message){
        super(message);
    }

}
