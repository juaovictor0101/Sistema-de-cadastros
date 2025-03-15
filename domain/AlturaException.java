package domain;

public class AlturaException extends Exception{
    public AlturaException() {
        super("A altura deve conter vírgula ( , ) ");
    }

    public AlturaException(String message) {
        super(message);
    }
}
