# 🌦️ Zephyr API

Plataforma backend inteligente desenvolvida com **Java e Spring Boot** para monitoramento climático em tempo real, autenticação de usuários, gerenciamento de cidades favoritas e geração de recomendações climáticas personalizadas utilizando **Inteligência Artificial**.

O projeto integra serviços externos, mensageria assíncrona e arquitetura moderna para entregar uma solução escalável, desacoplada e orientada a eventos.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT Authentication
- Spring Cache
- PostgreSQL
- Hibernate / JPA
- RabbitMQ
- Docker & Docker Compose
- Swagger / OpenAPI
- Maven
- JUnit 5
- Mockito
- OpenWeather API
- OpenRouter AI (GPT-4o)

---

## 📌 Funcionalidades

### 👤 Usuários e autenticação

- ✅ Cadastro de usuários
- ✅ Login com JWT
- ✅ Rotas protegidas com Spring Security
- ✅ Integração do Swagger com autenticação Bearer Token

---

### 🌤️ Clima e previsões

- ✅ Consulta de clima atual por cidade
- ✅ Consulta de previsão diária
- ✅ Consulta de previsão horária
- ✅ Conversão automática da velocidade do vento para km/h
- ✅ Sistema inteligente de alertas climáticos
- ✅ Cache para otimização das consultas externas

---

### ⭐ Cidades favoritas

- ✅ Adicionar cidade favorita
- ✅ Listar cidades favoritas do usuário autenticado
- ✅ Remover cidade favorita
- ✅ Consultar o clima atual de todas as cidades favoritas

---

### 📨 Arquitetura orientada a eventos

Quando um usuário favorita uma cidade, a aplicação executa automaticamente um fluxo assíncrono:

- ✅ Publicação do evento utilizando RabbitMQ
- ✅ Processamento assíncrono pelo Consumer
- ✅ Consulta automática da OpenWeather API
- ✅ Geração de recomendação personalizada utilizando IA
- ✅ Fluxo desacoplado entre Producer e Consumer

---

## 🤖 Inteligência Artificial

O Zephyr utiliza **OpenRouter AI (GPT-4o)** para gerar recomendações climáticas inteligentes.

A IA recebe informações como:

- Cidade
- Temperatura
- Umidade
- Velocidade do vento
- Alertas meteorológicos

E produz recomendações amigáveis e contextualizadas.

### Exemplo

> Hoje em Florianópolis a umidade está alta, então um casaco leve pode ajudar. Aproveite o dia com calma e curta os momentos agradáveis!

---

## 🌪️ Sistema de alertas climáticos

A aplicação gera alertas automáticos conforme as condições meteorológicas.

| Condição | Alerta |
|----------|---------|
| Ventos fortes | Evite deslocamentos de moto ou bicicleta |
| Tempestades | Evite permanecer em áreas abertas |
| Chuva intensa | Leve guarda-chuva e dirija com atenção |

---

## 🔄 Fluxo da aplicação

```text
Usuário
   │
   ▼
JWT Authentication
   │
   ▼
Favoritar Cidade
   │
   ▼
RabbitMQ (Producer)
   │
   ▼
RabbitMQ (Consumer)
   │
   ├──► OpenWeather API
   │
   └──► OpenRouter AI
            │
            ▼
Recomendação Inteligente
```

---

## 📂 Estrutura do projeto

```text
src/main/java/com/zephyr/api
│
├── client
├── config
│   └── security
├── controller
├── dto
│   ├── ai
│   ├── request
│   └── response
├── entity
├── exception
├── messaging
├── prompt
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
GET /weather/forecast/hourly/{city}
```

### ⭐ Favoritos

```http
POST   /city/favorite
GET    /city/favorite
DELETE /city/favorite/{id}
GET    /city/favorite/weather
```

---

## 📖 Documentação (Swagger)

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

O projeto possui testes unitários utilizando:

- JUnit 5
- Mockito
- Mock de SecurityContext
- Mock de dependências externas

---

## 🧠 Conceitos aplicados

- Arquitetura em camadas
- REST API Design
- Spring Security + JWT
- Consumo de APIs externas
- Integração entre múltiplas APIs
- DTO Pattern
- Prompt Engineering
- Cache
- RabbitMQ
- Arquitetura orientada a eventos
- Mensageria assíncrona
- Inteligência Artificial Generativa
- Docker
- Testes unitários
- Tratamento global de exceções

---

## 🚀 Roadmap

- [ ] Deploy em ambiente cloud
- [ ] Interface web em React consumindo a API

---

## 👩‍💻 Desenvolvido por

**Pamella Binotto**

Projeto pessoal desenvolvido para aprofundar conhecimentos em desenvolvimento backend moderno com Java e Spring Boot, explorando segurança com JWT, arquitetura orientada a eventos, mensageria com RabbitMQ, integração com APIs externas e Inteligência Artificial Generativa.
