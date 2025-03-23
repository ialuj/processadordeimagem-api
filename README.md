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

Pode compilar o projeto e gerar o jar com o comando - mvn clean package -DskipTests

# 2. Configure o ambiente

As configurações de base de dados encontram-se no ficheiro application-dev.properties (src/main/resource), pois aqui estamos usando o perfil de desenvolvimento "dev".
A base de dados é MySQL e está um container do docker.
Bem na raíz do projecto podemos encontrar os ficheiros Dockerfile e docker-compose que possuem todas as configurações dos containers. São esperados 3 containers:

- Container de Banco de Dados: processador_imagem_db com a porta interna 3306 e externa 3333, usuário e senha 'root', 'root'.
- Container da aplicação: processadordeimagem-api exposta na porta 8080.
- Container do RabbitMQ: rabbitmq exposto na porta 15672 com o usuário e a senha 'guest', 'guest'.

NB: Não precisamos preocupar-nos com a criação do Banco de Dados e Tabelas pois o Liquibase trata de tudo isso.

# 3. Inicie os serviços Docker

docker-compose up -d

Isso iniciará os containers do MariaDB, Aplicação e RabbitMQ.

A API estará disponível em `http://localhost:8080`.

O Swagger está disponível em:
📌 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Para ver os endpoints disponíveis, acesse:
📌 [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

# 3️⃣ Testando a API

Você pode testar os endpoints via **Postman**, **cURL**, ou diretamente pelo **Swagger UI**.

- Primeiro crie um usuário no seguinte endpoint: http://localhost:8080/api/users/register

{
"name": "Seu nome",
"email": "seuemail@exemplo.com",
"password": "seuusuario",
"subscriptions": [
{
"plan": "Basic"
}
]
}

Por favor coloque um e-mail verdadeiro ou existente porque nele vai receber as credenciais de acesso a aplicação

- Login: http://localhost:8082/api/auth/Login

{
"username":"seuemail@examplo.com",
"password":"seuusuario"
}

NB: copie o token gerado após a autenticação para poder o usar o endpoint de processamento de imagens. Use o Baerer Token

- Pode Solicitar o Processamento de uma Imagem: http://localhost:8082/api/imagens/process
  Atencao, coloque a sua imagem na pasta ou diretório 'uploads' que foi criado dentro da raiz do projeto, esse é o volume onde será feito o upload das imagens que deverão ser processadas, assim como
  as imagens após o processamento.

{
"imageUrl": "/app/uploads/suaimagem.jpg",
"resizePercentage": 50.0,
"filter": "NONE",
"image": {
"originalFileName": "suaimagem",
"filePath": "/app/uploads/suaimagem.jpg",
"width": 2455,
"height": 2804,
"filter": "NONE"
}
}

Vai receber um e-mail a confirmar o registo da sua solicitação de processamento da imagem.
Depois receberá outro e-mail do Resultado da Solicitação de Processamento da Imagem com o link para baixar a imagem (ainda funciona o link, mas pode ver a imagem processada no diretório de uploadas).

# 4️⃣ Parando os Containers

Para interromper os serviços do Docker, use:

docker-compose down

Caso queira limpar os volumes e dados persistentes:

docker-compose down -v

## 5️⃣ Contribuição

Para contribuir com este projeto, faça um fork e abra um pull request com suas melhorias.

## 6️⃣ Licença

Este projeto está sob a licença # BIX Tecnologia.
