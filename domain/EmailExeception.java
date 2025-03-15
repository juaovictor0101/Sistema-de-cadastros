package domain;

public class EmailExeception extends Exception {
    public EmailExeception() {
        super ("O endereço de email deve ter um @");
    }

    public EmailExeception(String message) {
        super(message);
    }
}
