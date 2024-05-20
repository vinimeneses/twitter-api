# Projeto Twitter API

Este é um projeto onde implementei, de forma simplificada uma API do twitter.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Security
- JWT para autenticação
- SQL
- Maven

## Funcionalidades

O projeto inclui as seguintes funcionalidades:

- Autenticação de usuários através de JWT.
- Autorização de usuários com base em suas funções (roles).
- Criação de novos tweets.
- Deleção de tweets existentes.
- Visualização de um feed de tweets.

## Como Executar

Para executar este projeto, você precisará ter o Java e o Maven instalados em sua máquina. Siga os passos abaixo:

1. Clone o repositório para a sua máquina local usando `git clone`.
2. Navegue até o diretório do projeto.
3. Configure o DATABASE.
4. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

## Estrutura do Projeto

O projeto está estruturado da seguinte maneira:

- `src/main/java/com/br/Spring/Security/controller`: Contém os controladores da aplicação, que manipulam as solicitações HTTP.
- `src/main/java/com/br/Spring/Security/controller/dto`: Contém os Data Transfer Objects (DTOs) usados para enviar e receber dados dos endpoints.
- `src/main/java/com/br/Spring/Security/entities`: Contém as entidades que representam as tabelas do banco de dados.
- `src/main/java/com/br/Spring/Security/repository`: Contém os repositórios usados para interagir com o banco de dados.

## Contribuição

Contribuições são bem-vindas!
