# 🌦️ Zephyr API

API REST desenvolvida com Java e Spring Boot para monitoramento climático em tempo real, consumo de APIs externas de previsão do tempo, geração de alertas inteligentes e processamento de eventos meteorológicos.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Hibernate
- Swagger / OpenAPI
- Maven
- Docker
- OpenWeather API

---

## 📌 Funcionalidades atuais

- ✅ CRUD de dados meteorológicos
- ✅ Integração com API externa de clima (OpenWeather)
- ✅ Consulta de clima atual por cidade
- ✅ Consulta de previsão climática
- ✅ Conversão automática de velocidade do vento para km/h
- ✅ Sistema de alertas climáticos baseado em velocidade do vento
- ✅ Tratamento global de exceções
- ✅ DTOs personalizados para desacoplamento da API externa
- ✅ Documentação automática com Swagger
- ✅ Containerização com Docker
- ✅ Estrutura preparada para processamento de eventos climáticos

---

## 🌪️ Sistema de alertas climáticos

A aplicação gera alertas automáticos conforme a intensidade dos ventos:

| Velocidade do vento | Alerta gerado |
|---|---|
| Acima de 40 km/h | Alerta de ventos fortes |
| Acima de 60 km/h | Recomendação para evitar deslocamentos de moto ou bicicleta |
| Acima de 80 km/h | Alerta severo de ventos fortes |

---

## 📨 Arquitetura orientada a eventos e mensageria

O projeto está sendo estruturado para trabalhar com processamento assíncrono de eventos climáticos e notificações em tempo real. A arquitetura prevê:

- Envio assíncrono de alertas climáticos
- Processamento de eventos meteorológicos
- Desacoplamento entre serviços
- Escalabilidade horizontal da aplicação
- Notificações em tempo real

**Tecnologias planejadas:**

- RabbitMQ
- Apache Kafka
- Spring Events

---

## 📂 Estrutura do projeto

```bash
src/main/java/com/zephyr/api
│
├── client
├── controller
├── dto
│   ├── external
│   └── response
├── entity
├── exception
├── repository
├── service
```

---

## 🔌 Endpoints principais

### 📍 Clima atual

```http
GET /weather/current/{city}
```

Exemplo:

```http
GET /weather/current/Florianopolis
```

### 📍 Previsão do tempo

```http
GET /weather/forecast/{city}
```

### 📍 CRUD meteorológico

```http
POST   /weather
GET    /weather
GET    /weather/{id}
PUT    /weather/{id}
DELETE /weather/{id}
```

---

## 📖 Swagger

Após iniciar a aplicação, acesse a documentação em:

```
http://localhost:8080/swagger-ui.html
```

---

## ⚙️ Configuração

No arquivo `application.yml`, configure sua chave da OpenWeather API:

```yml
weather:
  api:
    key: SUA_API_KEY
```

---

## 🧠 Conceitos aplicados

- Arquitetura em camadas (Controller, Service, Repository)
- Consumo de APIs REST externas
- DTO Pattern e desacoplamento de entidades
- Tratamento global de exceções
- Conversão e transformação de dados
- Persistência com JPA / Hibernate
- Boas práticas REST
- Containerização com Docker
- Arquitetura orientada a eventos
- Mensageria e comunicação assíncrona

---

## 📌 Roadmap

- [ ] Filtragem inteligente de previsões por período
- [ ] Histórico climático
- [ ] Alertas avançados baseados em clima extremo
- [ ] Integração com mensageria (RabbitMQ / Kafka)
- [ ] Deploy em nuvem
- [ ] Testes automatizados

---

## 👩‍💻 Desenvolvido por

**Pamella Binotto**

Projeto pessoal com foco em boas práticas de backend Java, arquitetura distribuída e processamento de eventos.