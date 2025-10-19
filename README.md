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
    *(Se você viu um print com o nome `postgres`, ignore. O nome correto que a aplicação está configurada para usar é `serratec_music`)*.

---

### Passo 2: Configurar o Projeto

1.  **Clone** ou baixe este repositório.
2.  **Abra o projeto** na sua IDE.
3.  **Abra o arquivo `application.properties`** (localizado em `src/main/resources/`).
4.  **Edite** as linhas `spring.datasource.username` e `spring.datasource.password` para corresponder ao **SEU** usuário e senha do PostgreSQL.
5.  **Verifique** se o arquivo `application.properties` está idêntico a este (mudando apenas seu usuário e senha):

    ```properties
    # Nome da aplicação
    spring.application.name=serratec-music

    # --- Configuração do Banco de Dados ---
    # Garanta que o nome do banco é 'serratec_music'
    spring.datasource.url=jdbc:postgresql://localhost:5432/serratec_music

    # !!! MUDE O USUÁRIO E SENHA ABAIXO !!!
    spring.datasource.username=postgres
    spring.datasource.password=123456

    # --- Configuração do JPA / Hibernate ---
    # (Cria/atualiza as tabelas automaticamente ao ligar)
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

    # --- Porta do Servidor ---
    # (Usamos 8082 para evitar conflitos com a porta 8080)
    server.port=8082
    ```

---

### Passo 3: O "Reset" do Eclipse (Passo CRÍTICO de Desafio)

O Eclipse/STS pode ficar "preso" em cache antigo ou em modo "Offline". Se o projeto apresentar erros (como `Found 0 JPA repository interfaces` ou `Downloading external resources is disabled`), **siga estes 4 sub-passos**:

1.  **Desativar o Modo Offline:**
    * No Eclipse, vá em **Window -> Preferences**.
    * Na busca, digite `Maven`.
    * Clique na **palavra "Maven"** (não nas sub-pastas).
    * **DESMARQUE** a caixa de seleção **"Offline"**.
    * Clique em "Apply and Close".

2.  **Deletar o Cache Antigo (Pasta `target`):**
    * Feche o Eclipse.
    * Vá até a pasta do projeto no Windows Explorer (ex: `C:\.../serratec-music/`).
    * Delete a pasta `target` manualmente.

3.  **Forçar a Atualização do Maven:**
    * Abra o Eclipse novamente.
    * Clique com o **botão direito** no projeto (`serratec-music`) na árvore de arquivos.
    * Vá em **Maven -> Update Project...**.
    * **MARQUE** a caixa **"Force Update of Snapshots/Releases"**.
    * Clique em **OK** (e espere ele baixar as dependências - isso pode levar um minuto).

4.  **Limpar o Projeto (Clean):**
    * No menu superior do Eclipse, vá em **Project -> Clean...**.
    * Selecione seu projeto e clique em **Clean**.

---

### Passo 4: Executar a Aplicação

Depois de seguir os passos 1, 2 e 3:

1.  Encontre o arquivo `SerratecMusicApplication.java` (em `src/main/java/com/serratec/serratec_music/`).
2.  Clique com o **botão direito** nele.
3.  Vá em **Run As -> Java Application**.

### Verificando o Sucesso

Observe o Console. A aplicação iniciou com sucesso se você vir estas 3 linhas (sem nenhum `ERROR` no final):
1.  `... Finished Spring Data repository scanning in ... ms. Found 5 JPA repository interfaces.`
2.  `... HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection...`
3.  `... Tomcat started on port 8082 (http)`

---

## 4. Acessando a API

Sua API está no ar e pronta para ser testada.

### 1. Documentação Swagger (Recomendado)

Para ver e testar todos os endpoints de forma interativa, acesse o Swagger UI no seu navegador (note a porta **8082**):

➡️ **`http://localhost:8082/swagger-ui.html`**

### 2. Teste via Postman

* **`GET` http://localhost:8082/artistas**
    * *Resultado esperado:* Um status `200 OK` e uma lista vazia `[ ]`.

* **`POST` http://localhost:8082/artistas**
    * *Body (em modo `raw` e `JSON`):*
        ```json
        {
            "nome": "Renato Russo",
            "nacionalidade": "Brasileiro"
        }
        ```
    * *Resultado esperado:* Um status `201 Created` e o JSON do artista criado com o ID.
