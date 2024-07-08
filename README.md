# Health Insurance Beneficiary

## 📃 Introdução

A aplicação visa disponibilizar rotas de API para acessar, criar, editar e excluir beneficiários de plano de saúde, além de dispobilizar rotas para registros de documentos.

## 💻 Tecnologias usadas:

- #### Linguagem: Java

- Spring Boot (Extenção do framework Spring)
- MySQL (Banco de dados relacional)
- Docker-Compose (Execução da Aplicação e do Banco de Dados em contêineres)
- JUnit (Framework de testes unitários)

## 💾 Instalações
Há 2 maneiras diferentes para execução da aplicação, através do docker ou localmente:</br>
 - **Docker** : Para execução de maneira mais simples da aplicação é necessário ter instalado a ferramenta [docker-compose](https://docs.docker.com/compose/install/).</br>
 - **Localmente** : Para execução local da aplicação é necessário ter instalado [MySQL](https://www.mysql.com/downloads/), [Java](https://www.java.com/pt-BR/download/) (recomendável 17) e [Maven](https://maven.apache.org/download.cgi).

## 💿 Rodando a aplicação

**Docker** : Para iniciar a aplicação utilizando docker, apenas utilize o comando:

    $ docker-compose up
**Local** : Para iniciar a aplicação localmente deve-se adicionar um banco de dados MySQL com os parametros definidos em src\main\resources\application.properties e executar os seguintes comandos para buildar a aplicação e executa-la:

    $ mvn package
    $ java -jar target/HealthInsuranceBeneficiary.jar
    
## ✅ Testes unitários 

Os testes já são executados quando há o build da aplicação, porém caso queira pode rodar o teste localmente: 

    $ mvn test

## 🔗 Rotas
Path                                | Método |  Descrição
----------------------------------- | ------ | -----
/api/beneficiary/:id                |  GET   |  Retorna o registro de um Beneficiary a patir de sua ID
/api/beneficiary/:id/documents      |  GET   |  Retorna o registro de todos os documentos a partir da ID de um Beneficiary
/api/beneficiary                    |  POST  |  Inseri o registro de um Beneficiary
/api/beneficiary/:id                |  PUT   |  Altera dados de uma Beneficiary
/api/beneficiary/:id                | DELETE |  Exclui o registro de uma Beneficiary a patir de sua ID
/api/beneficiary/document/:id       |  GET   |  Retorna o registro de um Document a patir de sua ID
/api/beneficiary/document/          |  POST  |  Inseri o registro de um Document
/api/beneficiary/document/:id       |  PUT   |  Altera dados de uma Document
/api/beneficiary/document/:id       | DELETE |  Exclui o registro de uma Document a patir de sua ID

## 💬 Status Code Previstos

| Código |       Status          | Observação                                                      |
|--------|-----------------------|-----------------------------------------------------------------|
| 200    | Ok                    |Requisição foi bem-sucedida.                                     | 
| 201    | Created               |Requisição foi bem-sucedida e um novo recurso foi criado         | 
| 204    | No content            |Requisição foi bem-sucedida, mas não há conteúdo para retornar   | 
| 400    | Bad Request           |Sintaxe inválida para algum atributo da requisição               | 
| 404    | Not Found             |Recurso requisitado não foi encontrado                           |
| 429    | Too Many Requests     |Muitas requisições referente à API Ninjas ou Brasil.io           | 
| 500    | Internal Server Error |Um erro interno no servidor impediu de atender à requisição      | 

## 📖 API

- ### GET /api/beneficiary/:id
#### response.body:
```json
[
  {
  "id": 1,
  "name": "Fulan da Silva",
  "phone": "123-456-7890",
  "birthDate": "1985-05-20",
  "inclusionDate": "2024-07-07T15:45:30",
  "updateDate": "2024-07-08T10:30:00",
  "documents": [
      {
        "id": 1,
        "type": "CPF",
        "number": "12345678900",
        "issueDate": "2015-05-01",
        "expiryDate": "2025-05-01"
      }
  ]
}
]
```


- ### GET /api/beneficiary/:id/documents
#### response.body:
```json
[
  {
        "id": 1,
        "type": "CPF",
        "number": "12345678900",
        "issueDate": "2015-05-01",
        "expiryDate": "2025-05-01"
      },
  {
    "id": 2,
    "type": "RG",
    "number": "12345678",
    "issueDate": "2015-05-01",
    "expiryDate": "2025-05-01"
  }
]
```


- ### POST /api/beneficiary/
#### request.body:
```json
{
  "name": "Fulano da Silva",
  "phone": "987-654-3210",
  "birthDate": "1990-10-25",
  "inclusionDate": "2024-07-08T10:45:00",
  "updateDate": "2024-07-08T10:45:00",
  "documents": [
      {
        "type": "CPF",
        "number": "12345678900",
        "issueDate": "2020-01-01",
        "expiryDate": "2030-01-01"
      }
  ]
}

```
#### response.body :
```json
{
  "id": 1,
  "name": "Fulano da Silva",
  "phone": "987-654-3210",
  "birthDate": "1990-10-25",
  "inclusionDate": "2024-07-08T10:45:00",
  "updateDate": "2024-07-08T10:45:00",
  "documents": [
      {
        "type": "CPF",
        "number": "12345678900",
        "issueDate": "2020-01-01",
        "expiryDate": "2030-01-01"
      }
  ]
}
```


- ### PUT /api/beneficiary/:id
#### request.body:
```json
{
  "name": "Fulano da Silva",
  "phone": "987-654-3210",
  "birthDate": "1990-10-25",
  "inclusionDate": "2024-07-08T10:45:00",
  "updateDate": "2024-07-08T10:45:00",
  "documents": []
}
```
#### request.body:
```json
{
  "id": 1,
  "name": "Fulano da Silva",
  "phone": "987-654-3210",
  "birthDate": "1990-10-25",
  "inclusionDate": "2024-07-08T10:45:00",
  "updateDate": "2024-07-08T10:45:00",
  "documents": []
}
```


- ### GET /api/beneficiary/document/:id
#### response.body:
```json
[
  {
    "id": 1,
    "type": "CPF",
    "number": "12345678900",
    "issueDate": "2015-05-01",
    "expiryDate": "2025-05-01"
  }
]
```


- ### POST /api/beneficiary/document/
#### request.body:
```json
{
  "type": "CPF",
  "number": "12345678900",
  "issueDate": "2020-01-01",
  "expiryDate": "2030-01-01"
}
```
#### response.body :
```json
{
  "id": 1,
  "type": "CPF",
  "number": "12345678900",
  "issueDate": "2015-05-01",
  "expiryDate": "2025-05-01"
}
```



- ### PUT /api/beneficiary/document/:id
#### request.body:
```json
{
  "type": "CPF",
  "number": "12345678900",
  "issueDate": "2020-01-01",
  "expiryDate": "2030-01-01"
}
```
#### request.body:
```json
{
    "id": 1,
    "type": "CPF",
    "number": "12345678900",
    "issueDate": "2020-01-01",
    "expiryDate": "2030-01-01"
}
```


## 💡 Possíveis pontos de avanço

- Criação de mais classes e métodos de test
- Implementação de monitoramento de LOGs
- Implementação arquitetura hexagonal

## ☕ Considerações finais
Este projeto foi criado com doses generosas de café.

[Contato Linkedin](https://www.linkedin.com/in/dev-lucas-marques-sjc/)
