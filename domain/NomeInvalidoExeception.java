package domain;

public class NomeInvalidoExeception extends Exception {
    public NomeInvalidoExeception() {
        super("O nome deve ter pelo menos 10 caracteres");
    }

    public NomeInvalidoExeception(String message) {
        super(message);
    }
}
