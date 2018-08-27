package example.exception;

public class BankTransactionExeption extends Exception {

    private static final long SerialVersionUID = -3128681006635769411L;
    public BankTransactionExeption (String message) {
        super (message);
    }
}
