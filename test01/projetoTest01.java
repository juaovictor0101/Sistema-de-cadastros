package test01;

import domain.Pessoa;

import java.io.*;
import java.util.Scanner;

public class projetoTest01 {
    public static void main(String[] args) throws IOException {
        File pasta = new File("arquivosTXT");
        boolean isPastaCriada = pasta.mkdir();

        File formularioFile = new File(pasta, "formulario.txt");
        boolean isFormularioFileCriado = formularioFile.createNewFile();
        System.out.println(isFormularioFileCriado);
        try (FileWriter fwformularioBase = new FileWriter(formularioFile, true);
            BufferedWriter brFormularioBase = new BufferedWriter(fwformularioBase)){
            brFormularioBase.write("1 - Qual seu nome completo?");
            brFormularioBase.newLine();
            brFormularioBase.write("2 - Qual seu email de contato?");
            brFormularioBase.newLine();
            brFormularioBase.write("3 - Qual sua idade?");
            brFormularioBase.newLine();
            brFormularioBase.write("4 - Qual sua altura?");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileReader frFormulario = new FileReader("C:\\Users\\Samsung\\Documents\\Estudos\\projeto-crud-txt-java\\arquivosTXT\\formulario.txt");
             BufferedReader brFormulario = new BufferedReader(frFormulario)){
            String linha;
            while ((linha = brFormulario.readLine()) != null){
                System.out.println(linha);
            }
            brFormulario.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner entrada = new Scanner(System.in);
        System.out.print("1. ");
        String nomeCompleto = entrada.nextLine();
        System.out.print("2. ");
        String email = entrada.nextLine();
        System.out.print("3. ");
        Integer idade = entrada.nextInt();
        System.out.print("4. ");
        float altura = entrada.nextFloat();

        Pessoa pessoa1 = new Pessoa(nomeCompleto,email,idade,altura);
        File formularioPessoa1 = new File(pasta,"pessoas.txt");
        try (FileWriter fwPessoa1 = new FileWriter(formularioPessoa1, true);
            BufferedWriter brPessoa1 = new BufferedWriter(fwPessoa1)){
            brPessoa1.write("1- "+nomeCompleto);
            brPessoa1.newLine();
            brPessoa1.write("2- "+email);
            brPessoa1.newLine();
            brPessoa1.write("3- "+Integer.toString(idade));
            brPessoa1.newLine();
            brPessoa1.write("4- "+Float.toString(altura));
            brPessoa1.newLine();
//            brPessoa1.write((Float.toString(altura));
        } catch (Exception e) {
            e.printStackTrace();
        }

        File arquivoRenomeado = new File(pasta,  "1- "+nomeCompleto.toUpperCase());
        boolean isRenomeado = formularioPessoa1.renameTo(arquivoRenomeado);
        pessoa1.imprime();




    }
}
