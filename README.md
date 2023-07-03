# Projeto de pós graduação | Tech challenge

O projeto Tech Challenge proposto para a pós-graduação [FIAP](https://www.fiap.com.br/) em [Arquitetura e desenvolvimento Java](https://postech.fiap.com.br/curso/arquitetura-desenvolvimento-java) consiste, na primeira fase do curso, no desenvolvimento de uma API para o cadastro e gerenciamento de informações relacionadas a eletrodomésticos, endereços e pessoas. Essa API será desenvolvida utilizando as tecnologias Spring Boot, Java 17 e o banco de dados PostgreSQL. O objetivo é criar uma solução eficiente e segura, fornecendo endpoints para operações de criação, atualização, recuperação e exclusão de registros.

## Índice

1. [Tecnologias](#tecnologias)
2. [Ferramentas](#ferramentas)
3. [Instalação](#instalação)
5. [Documentação](/DOCUMENTACAO.md)
6. [Jornada](#jornada)
7. [Autores](#autores)
8. [Licença](#licença)

## Tecnologias

As tecnologias utilizadas durante o desenvolvimento do projeto.

### [Java 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
Linguagem de programação escolhida para implementar a lógica do sistema, garantindo confiabilidade, segurança e escalabilidade.

### [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
Utilizado como framework para agilizar o desenvolvimento da API, oferecendo recursos como injeção de dependências, mapeamento de URLs, tratamento de requisições HTTP e muito mais.

### [PostgreSQL](https://www.postgresql.org/docs/)
Banco de dados utilizado para armazenar e gerenciar as informações relacionadas a eletrodomésticos, endereços e pessoas. O PostgreSQL é conhecido por sua robustez e confiabilidade, adequado para aplicações de larga escala.

### [Maven](https://maven.apache.org/guides/index.html)
Gerenciador de dependências utilizado para facilitar a configuração e o gerenciamento das bibliotecas e dependências do projeto.

### [Git](https://git-scm.com/doc)
Sistema de controle de versão utilizado para rastrear alterações no código-fonte, facilitando o trabalho em equipe, o versionamento e a colaboração no projeto.

### [Lombok](https://projectlombok.org/features/)
Biblioteca que permite reduzir a quantidade de código boilerplate, como getters, setters e construtores, através de anotações, melhorando a produtividade do desenvolvimento.

### [ModelMapper](https://modelmapper.org/getting-started/)
Biblioteca utilizada para mapear automaticamente objetos de uma classe para outra, facilitando a conversão de DTOs (Data Transfer Objects) para entidades e vice-versa.

## Ferramentas

### [Intellij IDEA](https://www.jetbrains.com/pt-br/idea/)
Ambiente de desenvolvimento integrado (IDE) utilizado para escrever, depurar e testar o código-fonte do projeto. O Intellij IDEA oferece recursos avançados de produtividade, facilitando o desenvolvimento em Java e agilizando o processo de construção da API.

### [Postman](https://www.postman.com/)
Plataforma de colaboração e testes de API que permite enviar e receber solicitações HTTP, testar os endpoints da API, verificar as respostas e realizar a depuração de forma eficiente.

### [GitHub](https://github.com/)
Plataforma de hospedagem de repositórios de controle de versão Git, utilizada para armazenar e gerenciar o código-fonte do projeto. O GitHub permite o trabalho colaborativo, controle de versões, rastreamento de alterações e facilita a integração com ferramentas de desenvolvimento.

### [Discord](https://discord.com/)
Plataforma de comunicação por voz, vídeo e chat, utilizada para facilitar a colaboração e a comunicação em equipe durante o desenvolvimento do projeto.

### [Trello](https://trello.com/)
Ferramenta de gerenciamento de projetos baseada em quadros Kanban, utilizada para organizar e acompanhar as tarefas do projeto.

## Instalação
Estes são os passos para a execução do projeto num ambiente local.
1. Clone este repositório git na sua máquina local
   ``` 
   git clone https://github.com/samuelclinton/techchallenge.git
   ```
2. Instale e configure o PostgreSQL: 
   - Crie o database com o nome: `techchallenge`;
   - Por padrão as credenciais são `postgres` para usuário e senha, porém isso pode ser configurado através das variáveis de ambiente `DB_USER` e `DB_PASSWORD`;
3. Faça a build do projeto utilizando o Maven ([saiba como](https://www.alura.com.br/artigos/processo-de-build-com-o-maven)) e execute o JAR do projeto.
4. Execute as chamadas HTTP utilizando o endereço base padrão `http://localhost:8080`

## Jornada

### Primeira fase
O desenvolvimento do projeto por enquanto foi simples e sem grandes dificuldades, mas como resolvemos implementar a persistência (que era opcional nessa fase) logo de início, nos deparamos com o relacionamento de Pessoas com Pessoas, no caso o parentesco. No nosso entendimento isso é mais uma questão de vínculo entre clientes do que o grau de parentesco em sí (filhos, pais, sobrinhos, tios). Com isso em mente, fizemos um relacionamento de autorreferência, onde uma pessoa pode ou não ser vinculada a um parente. O parente que for vinculado a esse parente tem sua "conta" na aplicação vinculada, sendo essa excluída caso o parente da conta principal resolva excluir a sua. A nova feature da Netflix de contas subsidiárias foi uma inspiração aqui.
Outro conhecimento interessante, que o Junior trouxe de um projeto prévio, foi a criação de um mapeador genérico de classes utilizando o ModelMapper, isso agilizou muito o desenvolvimento dos mapeadores, onde antes teríamos diversos mapeadores "repetidos", apenas com suas classes de origem e destino alteradas, agora temos uma implementação genérica (utilizando a Generics API do Java) que pode ser instanciada para diversas classes com a mesma implementação. Isso aumenta um pouco a complexidade do código porém a produtividade é beneficiada.

## Autores

- [Samuel Clinton](https://www.linkedin.com/in/samuelclinton)
- [Eric Moraes](https://www.linkedin.com/in/eric-silva-352082185)

Um agradecimento ao [Junior Martins](https://www.linkedin.com/in/juniorsmartins) e [Matheus Dias](https://www.linkedin.com/in/matheusapdias) que deixaram o curso mas contribuíram com o projeto na primeira fase

## Licença

[Licença MIT](https://opensource.org/license/mit/): permite o uso, a modificação e a distribuição do software sem restrições significativas.
