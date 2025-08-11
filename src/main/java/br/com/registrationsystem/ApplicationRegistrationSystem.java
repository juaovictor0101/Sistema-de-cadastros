package br.com.registrationsystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta anotação ativa a auto-configuração, a varredura de componentes e mais.
@SpringBootApplication
public class ApplicationRegistrationSystem {


    public static void main(String[] args) {
        SpringApplication.run(ApplicationRegistrationSystem.class, args);
    }
}