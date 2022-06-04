package javaCalculator;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
    }

    public InvalidInputException(String text) {
        super(text);
    }
}
