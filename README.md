# BIX Tecnologia - API de Processamento de Imagens

Este projeto consiste em uma API Spring Boot para processar imagens, utilizando RabbitMQ para filas de mensagens e MariaDB como banco de dados.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MariaDB
- RabbitMQ
- Docker & Docker Compose
- Liquibase
- Swagger para documentação

## 1️⃣ Como Executar a Aplicação

### Pré-requisitos

Antes de executar, certifique-se de ter instalado:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)

# Passos para execução

# 1. Clone o repositório (branch - develop)

git clone https://github.com/ialuj/processadordeimagem-api.git
cd processadordeimagem-api

# 2. Configure o ambiente

Caso necessário, edite o `application.properties` ou `application-dev.properties`:

spring.datasource.url=jdbc:mariadb://db:3306/processador_imagem_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.rabbitmq.host=rabbitmq
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# 3. Inicie os serviços Docker

docker-compose up -d

Isso iniciará os containers do MariaDB e RabbitMQ.

# 4. Compile e rode a aplicação

mvn clean install
mvn spring-boot:run

A API estará disponível em `http://localhost:8082`.

## 2️⃣ Documentação da API

O Swagger está disponível em:
📌 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Para ver os endpoints disponíveis, acesse:
📌 [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

# 3️⃣ Testando a API

Você pode testar os endpoints via **Postman**, **cURL**, ou diretamente pelo **Swagger UI**.

Exemplo de requisição para envio de imagem para processamento:

curl -X POST "http://localhost:8082/api/processar" -H "Content-Type: application/json" -d '{"imageUrl": "https://example.com/imagem.jpg"}'

# 4️⃣ Parando os Containers

Para interromper os serviços do Docker, use:

docker-compose down

Caso queira limpar os volumes e dados persistentes:

docker-compose down -v

## 5️⃣ Contribuição

Para contribuir com este projeto, faça um fork e abra um pull request com suas melhorias.

## 6️⃣ Licença

Este projeto está sob a licença # BIX Tecnologia.
