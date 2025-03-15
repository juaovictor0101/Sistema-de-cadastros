package domain;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroUsuario {

    //metodo para criar uma pasta e dentro da pasta um arquivo.txt com as perguntas do formulario

    public static void CriarPasta() {

        File pasta = new File("arquivosTXT");
        boolean isPastaCriada = pasta.mkdir();

        File indiceFile = new File(pasta, "indice.txt");

        File formularioFile = new File(pasta, "formulario.txt");
        boolean isFormularioFileCriado = false;
        try {
            isFormularioFileCriado = formularioFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static Pessoa Cadastro()  {
        Scanner entrada = new Scanner(System.in);
        System.out.print("1. ");
        String nomeCompleto = entrada.nextLine();
        if(nomeCompleto.length()<10){
            try {
                throw new NomeInvalidoExeception();
            } catch (NomeInvalidoExeception e) {
                String message = e.getMessage();
                System.out.println(message);
                throw new RuntimeException(e);
            }
        }
        System.out.print("2. ");
        String email = entrada.nextLine();
        if(!email.contains("@")){
            try {
                throw new EmailExeception();
            } catch (EmailExeception e) {
                String message = e.getMessage();
                System.out.println(message);
                throw new RuntimeException(e);
            }
        }
        System.out.print("3. ");
        Integer idade = entrada.nextInt();
        if (idade <=18){
            try {
                throw new MenorIdadeExeception();
            } catch (MenorIdadeExeception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.print("4. ");
        String alturaStr = entrada.nextLine();
        if(!alturaStr.contains(",")){
            try {
                throw new AlturaException();
            } catch (AlturaException e) {
                throw new RuntimeException(e);
            }
        }
        float altura = Float.parseFloat(alturaStr);
        return new Pessoa(nomeCompleto, email, idade, altura);
    }

    public static void SalvarDados(Pessoa pessoa) {
        File pasta = new File("arquivosTXT");
        File arquivoPessoa = new File(pasta, "pessoa.txt");
        File arquivoNome = new File(pasta, "indice.txt");

        try (FileWriter fwArquivoIndice = new FileWriter(arquivoNome, true);
             BufferedWriter brIndice = new BufferedWriter(fwArquivoIndice)) {
            brIndice.write(pessoa.getNome());
            brIndice.newLine();
            brIndice.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fwPessoa = new FileWriter(arquivoPessoa, true);
             BufferedWriter brPessoa = new BufferedWriter(fwPessoa)) {
            brPessoa.write("1- " + pessoa.getNome());
            brPessoa.newLine();
            brPessoa.write("2- " + pessoa.getEmail());
            brPessoa.newLine();
            brPessoa.write("3- " + pessoa.getIdade());
            brPessoa.newLine();
            brPessoa.write("4- " + pessoa.getAltura());
            brPessoa.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        File arquivoRenomeado = new File(pasta, Objects.requireNonNull(pasta.listFiles()).length - 2 + "-" + pessoa.getNome().toUpperCase().replace(" ", "") + ".txt");
        boolean isRenomeado = arquivoPessoa.renameTo(arquivoRenomeado);
    }

    public static String[] listarDados(Pessoa pessoa) {
        String[] nomesCompletos = new String[]{pessoa.getNome()};
        return nomesCompletos;
    }


    public static void exibirDados() {
        try (FileReader frIndice = new FileReader("C:\\Users\\Samsung\\Documents\\Estudos\\projeto-crud-txt-java\\arquivosTXT\\indice.txt");
             BufferedReader brIndice = new BufferedReader(frIndice)) {

            String linha;
            int contadorIndice = 0;

            while ((linha = brIndice.readLine()) != null) {
                String nomeFormatado = formatarTitleCase(linha);
                System.out.println(contadorIndice + "-" + nomeFormatado);
                contadorIndice++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void novaPergunta() {
        File pasta = new File("arquivosC:\\Users\\Samsung\\Documents\\Estudos\\projeto-crud-txt-java\\arquivosTXTTXT");
        File arquivoPessoa = new File("C:\\Users\\Samsung\\Documents\\Estudos\\projeto-crud-txt-java\\arquivosTXT\\formulario.txt");
        Scanner entrada = new Scanner(System.in);

        int contador = 1;
        try (FileReader frContador = new FileReader(arquivoPessoa);
             BufferedReader brContador = new BufferedReader(frContador)) {
            while (brContador.readLine() != null) {
                contador++;
            }
        } catch (Exception ex) {
            System.out.println("Erro índice nova pergunta.");
            ex.printStackTrace();
        }

        try (FileWriter fwPergunta = new FileWriter(arquivoPessoa, true);
             BufferedWriter bwPergunta = new BufferedWriter(fwPergunta)) {
            System.out.println("Informe a pergunta que deseja cadastrar no formulário: ");
            bwPergunta.write(contador + " - " + entrada.nextLine());
            bwPergunta.newLine();
            bwPergunta.flush();
            System.out.println("Pergunta adicionada com sucesso!");


        } catch (Exception e) {
            System.out.println("Erro ao adicionar uma nova pergunta.");
            e.printStackTrace();
        }
    }

    public static void apagarPergunta() {
        File arquivoOriginal = new File("C:\\Users\\Samsung\\Documents\\Estudos\\projeto-crud-txt-java\\arquivosTXT\\formulario.txt");
        File arquivoTemporario = new File("C:\\Users\\Samsung\\Documents\\Estudos\\projeto-crud-txt-java\\arquivosTXT\\temp.txt");
        Scanner entrada = new Scanner(System.in);

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoOriginal))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        System.out.print("Informe o número da pergunta que deseja apagar: ");
        int numeroPergunta;
        try {
            numeroPergunta = Integer.parseInt(entrada.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
            e.printStackTrace();
            return;
        }

        if (numeroPergunta <= 4) {
            System.out.println("Erro: Apenas perguntas a partir da 5ª podem ser apagadas.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario))) {
            String linha;
            int linhaAtual = 1;
            int contadorIndice = 1;

            while ((linha = br.readLine()) != null) {
                if (linhaAtual != numeroPergunta) {
                    String[] partes = linha.split(" - ", 2);
                    if (partes.length == 2) {
                        bw.write(contadorIndice + " - " + partes[1]);
                    } else {
                        bw.write(contadorIndice + " - " + linha);
                    }
                    bw.newLine();
                    contadorIndice++;
                }
                linhaAtual++;
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
            return;
        }
        if (arquivoOriginal.delete()) {
            if (!arquivoTemporario.renameTo(arquivoOriginal)) {
                System.out.println("Erro ao renomear o arquivo temporário.");
            } else {
                System.out.println("Pergunta removida com sucesso!");
            }
        } else {
            System.out.println("Erro ao excluir o arquivo original.");
        }
    }

    public static void buscarUsuario() {
        File pasta = new File("arquivosTXT");
        File[] nomes = pasta.listFiles((dir, nome) -> nome.matches("^\\d+-\\w+\\.txt$"));
        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe o nome que busca: ");
        String nomeBuscado = entrada.nextLine();

        if (nomes == null) {
            System.out.println("A pasta está vazia ou não existe.");
            return;
        }
        System.out.println("Buscando por '" + nomeBuscado + "' em " + nomes.length + " arquivos...");

        for (File nome : nomes) {
            try (BufferedReader brNomes = new BufferedReader(new FileReader(nome))) {
                String linha;
                StringBuilder dadosUsuario = new StringBuilder();

                while ((linha = brNomes.readLine()) != null) {
                    if (dadosUsuario.length() == 0) {
                        if (!linha.toLowerCase().contains(nomeBuscado.toLowerCase())) {
                            dadosUsuario.setLength(0);
                            continue;
                        }
                    }
                    dadosUsuario.append(linha).append("\n");
                }
                if (dadosUsuario.length() > 0) {
                    System.out.println(dadosUsuario);
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }


    public static void cadastrar() throws NomeInvalidoExeception {
        File pasta = new File("arquivosTXT");
        File formularioFile = new File(pasta, "formulario.txt");

        if (!pasta.exists()) {
            CriarPasta();
        }
        LerFormulario();
        Pessoa pessoa = Cadastro();
        SalvarDados(pessoa);
        System.out.println("Cadastro realizado com sucesso!");
    }


    public static String formatarTitleCase(String str) {
        String[] palavras = str.split(" ");
        StringBuilder resultado = new StringBuilder();
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                resultado.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return resultado.toString().trim();
    }

    public static void Menu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("--------Menu principal--------");
        System.out.println();
        System.out.println("  1 - Cadastrar o usuário       ");
        System.out.println("  2 - Listar todos usuários cadastrados       ");
        System.out.println("  3 - Cadastrar nova pergunta no formulário       ");
        System.out.println("  4 - Deletar pergunta do formulário       ");
        System.out.println("  5 - Pesquisar usuário por nome     ");
        System.out.println("  6 - Sair     ");
        System.out.println();
        int escolha = entrada.nextInt();

        switch (escolha) {
            case 1:
                try {
                    cadastrar();
                } catch (NomeInvalidoExeception e) {
                    e.getMessage();
                    throw new RuntimeException(e);
                }
                Menu();
                break;
            case 2:
                exibirDados();
                Menu();
                break;
            case 3:
                novaPergunta();
                Menu();
                break;
            case 4:
                apagarPergunta();
                Menu();
                break;
            case 5:
                buscarUsuario();
                Menu();
                break;
            case 6:
                break;
            default:
                System.out.println("Informe um número entre 1 e 2");
                Menu();
        }
    }
}
