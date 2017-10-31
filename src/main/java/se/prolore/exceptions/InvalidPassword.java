package se.prolore.exceptions;

/**
 * Created by mats on 17/10/17.
 */

public class InvalidPassword extends Exception {
    public InvalidPassword(String message) {
        super(message);
    }
}
