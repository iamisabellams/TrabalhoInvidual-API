# API Serratec Music

**Autor:** Isabella Medeiros

---

## 1. Descrição do Projeto

API RESTful desenvolvida como projeto para a disciplina de API do curso do Serratec.

O projeto simula o backend de uma plataforma de streaming de música, permitindo o gerenciamento completo (CRUD) de Usuários (com seus Perfis), Artistas, Músicas e Playlists. A API também implementa os desafios de lógica de negócio, como a criação de playlists associadas a usuários e a adição de músicas a uma playlist.

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

## 3. Instruções de Execução

Siga os passos abaixo para configurar e executar o projeto localmente.

### Pré-requisitos

Antes de começar, garanta que você tenha os seguintes softwares instalados:
1.  **Java 17 (JDK)**
2.  **Maven**
3.  **PostgreSQL** (rodando na porta padrão `5432`)
4.  Um cliente de API (como **Postman** ou **Insomnia**)

### Passos para Executar

**1. Criar o Banco de Dados:**
Abra seu gerenciador de banco de dados (como DBeaver ou pgAdmin) e execute o seguinte comando SQL para criar o banco que a aplicação usará:
```sql
CREATE DATABASE serratec_music;
