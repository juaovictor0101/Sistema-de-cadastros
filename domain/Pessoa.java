package domain;

public class Pessoa {
    private String nome;
    private String email;
    private Integer idade;
    private float altura;

    public Pessoa(String nome, String email, Integer idade, float altura) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
    }

    public void imprime() {
        System.out.println("------DADOS------");
        System.out.println("1- " + nome);
        System.out.println("2- " + email);
        System.out.println("3- " + idade);
        System.out.println("4- " + altura);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
}

