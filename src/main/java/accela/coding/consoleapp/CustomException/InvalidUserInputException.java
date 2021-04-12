package accela.coding.consoleapp.CustomException;

public class InvalidUserInputException extends RuntimeException{
    public InvalidUserInputException() {
        super(String.format("User enter input is invalid"));
    }
}
