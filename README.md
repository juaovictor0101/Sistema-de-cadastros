# Sistema de Cadastro via CLI

## Descrição do Projeto
Este é um sistema de cadastro via terminal (CLI) desenvolvido em Java. O programa permite cadastrar, listar, buscar, alterar e deletar registros de usuários. Ele também oferece funcionalidades para gerenciar um formulário de perguntas dinâmico, possibilitando adicionar e remover perguntas.
Foi desenvolvido com o intuito de fixar alguns conceitos do que venho estudando por meio do curso de Java do DevDojo.


## Funcionalidades
1. **Cadastrar Usuário**
   - O sistema lê um arquivo `formulario.txt` contendo perguntas.
   - O usuário responde às perguntas via terminal.
   - As respostas são salvas em um arquivo no formato `1-NOME.TXT`.
   
2. **Listar Todos os Usuários**
   - Exibe apenas os nomes dos usuários cadastrados.

3. **Cadastrar Nova Pergunta no Formulário**
   - Permite adicionar novas perguntas automaticamente numeradas.

4. **Deletar Pergunta do Formulário**
   - Permite excluir perguntas a partir da 5ª pergunta.

5. **Pesquisar Usuário**
   - Busca por nome, idade ou e-mail, permitindo consultas parciais.

## Regras de Negócio
1. **Cadastro de Usuário**
   - Nome deve ter no mínimo 10 caracteres.
   - E-mail deve conter `@`.
   - Idade deve ser maior que 18 anos.
   - Altura deve ser informada com vírgula (`1,75`).
   - Não é permitido cadastrar dois usuários com o mesmo e-mail.

2. **Cadastro de Perguntas**
   - O número da nova pergunta é gerado automaticamente.

3. **Deleção de Perguntas**
   - Apenas perguntas a partir da 5ª podem ser deletadas.

## Tecnologias Utilizadas
- **Java** (Manipulação de arquivos, entrada e saída via terminal)
- **Paradigma Orientado a Objetos (OO)**
- **Tratamento de Exceções**

## Como Executar o Projeto
1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
