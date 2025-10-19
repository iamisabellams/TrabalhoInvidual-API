# API Serratec Music

**Aluna:** Isabella Medeiros

---

## 1. Descrição do Projeto

Esta é uma API RESTful desenvolvida como projeto para a disciplina de API do curso do Serratec.

O projeto simula o backend de uma plataforma de streaming de música, permitindo o gerenciamento completo (CRUD) de:
* Usuários (e seus Perfis associados)
* Artistas
* Músicas (com Gêneros)
* Playlists

A API também implementa os desafios de lógica de negócio, como a criação de playlists associadas a um usuário específico e a atualização da lista de músicas de uma playlist.

---

## 2. Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.x**
* **Spring Data JPA (Hibernate)**: Para persistência de dados.
* **PostgreSQL**: Banco de dados relacional.
* **Maven**: Gerenciador de dependências do projeto.
* **Springdoc OpenAPI (Swagger)**: Para documentação e teste interativo da API.
* **Spring Web Validation**: Para validação dos dados de entrada (`@Valid`).
* **ControllerAdvice**: Para tratamento de exceções centralizado.

---

## 3. Instruções Detalhadas de Execução

Siga **todos** os passos abaixo para configurar e executar o projeto localmente.

### Pré-requisitos

* **Java 17 (JDK)**: [Link para download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* **Maven**: [Link para download](https://maven.apache.org/download.cgi) (Geralmente já vem embutido em IDEs como Eclipse/STS).
* **PostgreSQL**: [Link para download](https://www.postgresql.org/download/) (Recomendado rodar na porta padrão `5432`).
* **IDE Java**: **Eclipse STS** (recomendado), IntelliJ ou VSCode.
* **Cliente de API**: **Postman** ou **Insomnia**.

---

### Passo 1: Configurar o Banco de Dados

1.  Abra seu gerenciador de banco de dados (DBeaver, pgAdmin, etc.).
2.  Execute o seguinte comando SQL para criar o banco que a aplicação usará:

    ```sql
    CREATE DATABASE serratec_music;
    ```

---

### Passo 2: Configurar o Projeto

1.  **Clone** ou baixe este repositório.
2.  **Abra o projeto** na sua IDE.
3.  **Abra o arquivo `application.properties`** (localizado em `src/main/resources/`).
4.  **Edite** as linhas `spring.datasource.username` e `spring.datasource.password` para corresponder ao **SEU** usuário e senha do PostgreSQL.
5.  **Verifique** se o arquivo `application.properties` está idêntico a este (mudando apenas seu usuário e senha):

    ```properties
    spring.application.name=serratec-music
    spring.datasource.url=jdbc:postgresql://localhost:5432/serratec_music
    spring.datasource.username=postgres
    spring.datasource.password=123456
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    # (Usei 8082 para evitar conflitos com a porta 8080)
    server.port=8082
    ```
---

### Instalar Dependências
No terminal, dentro da pasta do projeto:

```
    mvn clean install
```

## Executar a Aplicação
Opção 1 - Via Maven:
```
    mvn spring-boot:run
```

Execute a classe MAIN


## Acessar a API
```
    Aplicação: http://localhost:8082
```
