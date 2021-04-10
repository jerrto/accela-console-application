package accela.coding.consoleapp.CustomException;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(int id) {
        super(String.format("Person ID is invalid. Please enter a valid Person ID:", id));
    }
}
