package G_Projects.I_Vending_Machine.CommonExceptions;

public class InvalidShelfCodeException extends RuntimeException {
    public InvalidShelfCodeException(String message){
        super(message);
    }
}