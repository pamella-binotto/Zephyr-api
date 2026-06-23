# 🌦️ Zephyr API

API REST desenvolvida com Java e Spring Boot para monitoramento climático em tempo real, autenticação de usuários, gerenciamento de cidades favoritas e geração de alertas inteligentes com base em dados meteorológicos obtidos da OpenWeather API.

---

## 🚀 Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT Authentication
* Spring Cache
* PostgreSQL
* Hibernate / JPA
* Swagger / OpenAPI
* JUnit 5
* Mockito
* Maven
* Docker & Docker Compose
* OpenWeather API

---

## 📌 Funcionalidades atuais

### 👤 Usuários e autenticação

* ✅ Cadastro de usuários
* ✅ Login com JWT
* ✅ Rotas protegidas com Spring Security
* ✅ Integração do Swagger com autenticação Bearer Token

### 🌤️ Clima e previsões

* ✅ Consulta de clima atual por cidade
* ✅ Consulta de previsão diária
* ✅ Consulta de previsão por período
* ✅ Conversão automática de velocidade do vento para km/h
* ✅ Sistema inteligente de alertas climáticos
* ✅ Resumos automáticos para previsão diária
* ✅ Cache para otimização das consultas externas

### ⭐ Cidades favoritas

* ✅ Adicionar cidade favorita
* ✅ Listar cidades favoritas do usuário autenticado
* ✅ Remover cidade favorita
* ✅ Consultar clima atual de todas as cidades favoritas

### 🛠️ Arquitetura

* ✅ DTO Pattern
* ✅ Tratamento global de exceções
* ✅ Documentação automática com Swagger
* ✅ Containerização com Docker
* ✅ Testes unitários com JUnit e Mockito

---

## 🌪️ Sistema de alertas climáticos

A aplicação gera alertas automáticos conforme a intensidade dos ventos:

| Velocidade do vento | Alerta gerado                            |
| ------------------- | ---------------------------------------- |
| Acima de 40 km/h    | Ventos fortes no dia de hoje             |
| Acima de 60 km/h    | Evite deslocamentos de moto ou bicicleta |
| Acima de 80 km/h    | Alerta severo de ventos fortes           |

Além disso, o sistema analisa probabilidade de chuva para gerar recomendações e resumos diários.

---

## 📂 Estrutura do projeto

```bash
src/main/java/com/zephyr/api
│
├── client
├── config
│   └── security
├── controller
├── dto
│   ├── external
│   ├── request
│   └── response
├── entity
├── exception
├── repository
├── service
```

---

## 🔌 Principais endpoints

### 🔐 Autenticação

```http
POST /auth/register
POST /auth/login
GET  /auth/me
```

### 🌤️ Clima

```http
GET /weather/current/{city}
GET /weather/forecast/{city}
GET /weather/hourly/{city}
```

### ⭐ Favoritos

```http
POST   /city/favorite
GET    /city/favorite
DELETE /city/favorite/{id}
GET    /city/favorite/weather
```

---

## 📖 Swagger

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui.html
```

Para acessar rotas protegidas:

1. Faça login em `/auth/login`
2. Copie o JWT retornado
3. Clique em **Authorize**
4. Informe:

```text
Bearer SEU_TOKEN
```

---

## 🧪 Testes

Atualmente o projeto possui testes unitários cobrindo as principais regras de negócio do módulo de cidades favoritas utilizando:

* JUnit 5
* Mockito
* Mock de SecurityContext
* Mock de dependências externas

---

## 📨 Roadmap

### Mensageria

* [ ] RabbitMQ para processamento assíncrono
* [ ] Publicação de eventos meteorológicos
* [ ] Notificações climáticas em background

### Inteligência Artificial

* [ ] Geração de recomendações climáticas com IA
* [ ] Resumos avançados de previsão
* [ ] Sugestões inteligentes para o usuário


---

## 🧠 Conceitos aplicados

* Arquitetura em camadas
* REST API Design
* Spring Security + JWT
* Consumo de APIs externas
* DTO Pattern
* Cache
* Testes unitários
* Tratamento global de exceções
* Dockerização
* Arquitetura orientada a eventos (em evolução)

---

## 👩‍💻 Desenvolvido por

**Pamella Binotto**

Projeto pessoal criado para aprofundar conhecimentos em desenvolvimento backend com Java e Spring, arquitetura distribuída, mensageria, segurança de aplicações e integração com Inteligência Artificial.
