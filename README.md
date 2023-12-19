# Sistema de Gestão Escolar - Spring Data 

Este projeto é uma aplicação Java que utiliza o framework Spring Data em conjunto com JPA para criar um sistema de gestão escolar básico. O sistema gerencia informações sobre alunos, professores e disciplinas em um ambiente escolar. Esse projeto foi implementado o código diretamente pelo CommandLineRunner.   

## Tecnologias Utilizadas

- Java
- Spring Data
- JPA (Java Persistence API)
- MySQL
- Maven (Gerenciador de Dependências)

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- `src/main/java/com/data/data_spring`: Contém o código-fonte da aplicação.
- `pacote.entities`: Contém as classes que representam as entidades do sistema.
- `pacote.repository`: Contém as interfaces que definem os repositórios para cada entidade.
- `pacote.service`: Contém as classes de serviço que implementam a lógica de negócios.
- `src/main/resources/application.properties`: Contém arquivos de configuração do Spring Data e JPA, incluindo configurações do banco de dados.

## Configuração do Banco de Dados
Certifique-se de ter um servidor MySQL em execução. Você pode configurar as propriedades de conexão com o banco de dados no arquivo `application.properties`:
~~~Propeties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
~~~

## Executando o Projeto
Certifique-se de ter o Maven instalado. Na raiz do projeto, execute o seguinte comando:
~~~
mvn spring-boot:run
~~~

O programa abrirá no próprio terminal de execução, temos opções no de Menu.
- Aluno: Cadastrar - Atualizar - Visualizar - Deletar - VisualizarAluno
- Professor: Cadastrar - Atualizar - Visualizar - Deletar - VisualizarProfessor
- Disciplina: Cadastrar - Atualizar - Visualizar - Deletar - matricularAlunos

  ### Esperamos que este README ajude na compreensão e utilização do projeto. Se houver dúvidas ou problemas, sinta-se à vontade para acrescentar.
