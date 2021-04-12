package accela.coding.consoleapp.CustomException;

public class InvalidUserInputException extends RuntimeException{
    public InvalidUserInputException() {
        super("User enter input is invalid");
    }
}
