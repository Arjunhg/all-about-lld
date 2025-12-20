package G_Projects.I_Vending_Machine.CommonExceptions;

public class ItemSoldOutException extends RuntimeException {
    public ItemSoldOutException(String message){
        super(message);
    }
}
