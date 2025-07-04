package src.main.java.entity.domain;

public class MenorIdadeExeception extends Exception{
    public MenorIdadeExeception() {
        super ("O usuário deve ter mais de 18 anos de idade!");
    }

    public MenorIdadeExeception(String message) {
        super(message);
    }
}
