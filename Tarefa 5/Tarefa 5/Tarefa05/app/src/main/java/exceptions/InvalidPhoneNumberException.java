package exceptions;

public class InvalidPhoneNumberException extends CabbieManagerException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
