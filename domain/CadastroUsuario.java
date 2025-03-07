package domain;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class CadastroUsuario {

    //metodo para criar uma pasta e dentro da pasta um arquivo.txt com as perguntas do formulario

    public static void CriarPasta() {
        File pasta = new File("arquivosTXT");
        boolean isPastaCriada = pasta.mkdir();

        File formularioFile = new File(pasta, "formulario.txt");
        boolean isFormularioFileCriado = false;
        try {
            isFormularioFileCriado = formularioFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(isFormularioFileCriado);
        try (FileWriter fwformularioBase = new FileWriter(formularioFile);
             BufferedWriter brFormularioBase = new BufferedWriter(fwformularioBase)) {
            brFormularioBase.write("1 - Qual seu nome completo?");
            brFormularioBase.newLine();
            brFormularioBase.write("2 - Qual seu email de contato?");
            brFormularioBase.newLine();
            brFormularioBase.write("3 - Qual sua idade?");
            brFormularioBase.newLine();
            brFormularioBase.write("4 - Qual sua altura?");
            brFormularioBase.newLine();
            brFormularioBase.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LerFormulario() {
        try (FileReader frFormulario = new FileReader("C:\\Users\\Samsung\\Documents\\Estudos\\projeto-crud-txt-java\\arquivosTXT\\formulario.txt");
             BufferedReader brFormulario = new BufferedReader(frFormulario)) {
            String linha;
            while ((linha = brFormulario.readLine()) != null) {
                System.out.println(linha);
            }
            brFormulario.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Pessoa Cadastro() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("1. ");
        String nomeCompleto = entrada.nextLine();
        System.out.print("2. ");
        String email = entrada.nextLine();
        System.out.print("3. ");
        Integer idade = entrada.nextInt();
        System.out.print("4. ");
        float altura = entrada.nextFloat();
        return new Pessoa (nomeCompleto, email, idade, altura);
    }

    public static void SalvarDados (Pessoa pessoa) {
        File pasta = new File("arquivosTXT");
        File arquivoPessoa = new File(pasta, "pessoa.txt");

        try (FileWriter fwPessoa = new FileWriter(arquivoPessoa, true);
             BufferedWriter brPessoa = new BufferedWriter(fwPessoa)) {
            brPessoa.write("1- " + pessoa.getNome());
            brPessoa.newLine();
            brPessoa.write("2- " + pessoa.getEmail());
            brPessoa.newLine();
            brPessoa.write("3- " + pessoa.getIdade());
            brPessoa.newLine();
            brPessoa.write("4- " + pessoa.getAltura());
            brPessoa.newLine();
            brPessoa.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        File arquivoRenomeado = new File(pasta, Objects.requireNonNull(pasta.listFiles()).length - 1 + "-" + pessoa.getNome().toUpperCase().replace(" ", "") + ".txt");
        boolean isRenomeado = arquivoPessoa.renameTo(arquivoRenomeado);

    }
    public static void Cadastrar (){
        CriarPasta();
        LerFormulario();
        Pessoa pessoa = Cadastro();
        SalvarDados(pessoa);
    }

    public static void Menu (){
        Scanner entrada = new Scanner(System.in);
        System.out.println("--------Menu principal--------");
        System.out.println();
        System.out.println("  1 - Cadastrar o usuário       ");
        System.out.println("  2 - Listar todos usuários cadastrados       ");
        System.out.println("  3 - Cadastrar nova pergunta no formulário       ");
        System.out.println("  4 - Deletar pergunta do formulário       ");
        System.out.println("  5 - Pesquisar usuário por nome ou idade ou email      ");
        System.out.println("  6 - Sair     ");
        System.out.println();
        int escolha = entrada.nextInt();

        switch (escolha) {
            case 1:
                Cadastrar();
                break;
            case 2:
                ExibirDados();
                break;
            default:
                System.out.println("Informe um número entre 1 e 2");
                break;
        }
    }

    public static void ExibirDados() {
        try (FileReader frNome = new FileReader("C:\\Users\\Samsung\\Documents\\Estudos\\projeto-crud-txt-java\\arquivosTXT\\2-MARIAJOSÉ.txt");
             BufferedReader brNome = new BufferedReader(frNome)) {
            String linha;
            linha = brNome.readLine();
            System.out.println(linha);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
