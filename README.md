# 🏦 MiniBank API

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17+"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Data JPA"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge" alt="License MIT"/>
</p>

<p align="center">
  <b>🇧🇷 Português</b> | <a href="#-minibank-api-english">🇺🇸 English</a>
</p>

---

## 📌 Sobre o projeto

**MiniBank API** é uma API RESTful para gerenciamento de contas bancárias e operações financeiras básicas. Ela permite **criar contas, listar, consultar por ID, realizar depósitos e saques**, com persistência em banco de dados relacional **MySQL**.

O projeto foi desenvolvido com **Java 17+** e **Spring Boot**, organizado em camadas (`controller`, `service`, `repository`, `model` e `dto`) e utilizando **Spring Data JPA** para a persistência. Os valores monetários utilizam `BigDecimal`, evitando erros de precisão comuns em tipos de ponto flutuante.

---

## 🚀 Tecnologias utilizadas

| Tecnologia | Finalidade |
|---|---|
| **Java 17+** | Linguagem principal do projeto |
| **Spring Boot** | Framework para criação da aplicação |
| **Spring Web MVC** | Construção dos endpoints REST |
| **Spring Data JPA** | Persistência e abstração do acesso a dados |
| **MySQL** | Banco de dados relacional |
| **Maven** | Gerenciamento de dependências e build |

---

## 🗂️ Estrutura do projeto

```
minibank/
└── src/main/java/luizgustavo/minibank/
    ├── controller/   # Endpoints REST (AccountController)
    ├── services/     # Regras de negócio (AccountService)
    ├── repository/   # Acesso a dados (AccountRepository)
    ├── model/        # Entidades JPA (Account)
    ├── dto/          # Objetos de requisição (TransactionRequest)
    └── MinibankApplication.java
```

---

## ⚙️ Instalação

### Pré-requisitos

- [Java JDK 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/) (ou use o wrapper `./mvnw` incluso no projeto)
- [MySQL](https://www.mysql.com/) instalado e em execução

### Passo a passo

1. **Clone o repositório**

   ```bash
   git clone https://github.com/Gutstavo35/MiniBankAPI.git
   cd MiniBankAPI/minibank
   ```

2. **Crie o banco de dados**

   O Hibernate cria as tabelas automaticamente, mas o banco precisa existir:

   ```sql
   CREATE DATABASE minibank;
   ```

3. **Configure as credenciais**

   O `application.properties` lê o usuário e a senha do MySQL a partir de **variáveis de ambiente**, para que nenhuma credencial fique no código:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/minibank
   spring.datasource.username=${DB_USERNAME}
   spring.datasource.password=${DB_PASSWORD}
   spring.jpa.hibernate.ddl-auto=update
   ```

   Defina as variáveis antes de executar:

   **Linux / macOS**
   ```bash
   export DB_USERNAME=seu_usuario
   export DB_PASSWORD=sua_senha
   ```

   **Windows (PowerShell)**
   ```powershell
   $env:DB_USERNAME="seu_usuario"
   $env:DB_PASSWORD="sua_senha"
   ```

   > 💡 Na IntelliJ IDEA, você também pode definir as variáveis em **Run → Edit Configurations → Environment variables**.

4. **Compile o projeto**

   ```bash
   ./mvnw clean install
   ```

5. **Execute a aplicação**

   Pela classe principal (`MinibankApplication`) na sua IDE, ou via terminal:

   ```bash
   ./mvnw spring-boot:run
   ```

6. A API estará disponível em:

   ```
   http://localhost:8080
   ```

---

## 📡 Uso da API

### Endpoints disponíveis

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/accounts` | Cria uma nova conta (retorna a lista de contas) |
| `GET` | `/accounts` | Lista todas as contas |
| `GET` | `/accounts/{id}` | Busca uma conta pelo ID |
| `PATCH` | `/accounts/{id}/deposit` | Realiza um depósito na conta |
| `PATCH` | `/accounts/{id}/withdraw` | Realiza um saque na conta |

### Modelo de dados — `Account`

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `Long` | Identificador gerado automaticamente |
| `cpf` | `String` | CPF do titular (único, até 14 caracteres) |
| `name` | `String` | Nome do titular |
| `amount` | `BigDecimal` | Saldo da conta |

### Exemplos de payloads

#### ➕ Criar conta — `POST /accounts`

**Request:**

```json
{
  "cpf": "123.456.789-00",
  "name": "Maria Silva",
  "amount": 1000.00
}
```

**Response `200 OK`** (lista com todas as contas cadastradas):

```json
[
  {
    "id": 1,
    "cpf": "123.456.789-00",
    "name": "Maria Silva",
    "amount": 1000.00
  }
]
```

#### 📋 Listar contas — `GET /accounts`

**Response `200 OK`:**

```json
[
  {
    "id": 1,
    "cpf": "123.456.789-00",
    "name": "Maria Silva",
    "amount": 1000.00
  }
]
```

#### 🔍 Buscar por ID — `GET /accounts/{id}`

**Response `200 OK`:**

```json
{
  "id": 1,
  "cpf": "123.456.789-00",
  "name": "Maria Silva",
  "amount": 1000.00
}
```

#### 💰 Depositar — `PATCH /accounts/{id}/deposit`

**Request:**

```json
{
  "amount": 250.00
}
```

**Response `200 OK`:**

```json
{
  "id": 1,
  "cpf": "123.456.789-00",
  "name": "Maria Silva",
  "amount": 1250.00
}
```

#### 💸 Sacar — `PATCH /accounts/{id}/withdraw`

**Request:**

```json
{
  "amount": 300.00
}
```

**Response `200 OK`:**

```json
{
  "id": 1,
  "cpf": "123.456.789-00",
  "name": "Maria Silva",
  "amount": 950.00
}
```

### Regras de negócio

- O valor (`amount`) de depósitos e saques deve ser **maior que zero**; caso contrário a operação é rejeitada.
- Operações em contas inexistentes são rejeitadas com a mensagem `account not found`.

---

## 🧪 Testando a API

Na pasta `minibank/src/test/docs` você encontrará o arquivo `accounts.http` com requisições prontas para testar todos os endpoints da API, incluindo casos de erro (depósito com valor zero e conta inexistente). Basta abrir o arquivo no **IntelliJ IDEA** (ou no VS Code com a extensão **REST Client**) e clicar no botão ▶ ao lado de cada requisição, sem precisar de ferramentas externas como Postman ou Insomnia.

Se preferir o terminal, um exemplo rápido (em uma única linha, funciona em qualquer sistema):

```bash
curl http://localhost:8080/accounts
```

---

## 🛣️ Próximos passos

- [ ] Validação de saldo insuficiente nos saques
- [ ] Endpoint de transferência entre contas
- [ ] Tratamento global de exceções com respostas HTTP adequadas (`404`, `400`)
- [ ] Validação de CPF e dos campos de entrada (Bean Validation)
- [ ] Histórico de transações (extrato)
- [ ] Documentação interativa com Swagger/OpenAPI

---

## 👤 Autor

**Luiz Gustavo V. Gonzaga**

[![GitHub](https://img.shields.io/badge/GitHub-Gutstavo35-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/Gutstavo35)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Luiz%20Gustavo-0A66C2?style=flat-square&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luiz-gustavo-vilela-gonzaga-7857a7319/)

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---
---

<br>

# 🏦 MiniBank API (English)

<p align="center">
  <a href="#-minibank-api">🇧🇷 Português</a> | <b>🇺🇸 English</b>
</p>

---

## 📌 About the project

**MiniBank API** is a RESTful API for managing bank accounts and basic financial operations. It allows you to **create accounts, list them, look them up by ID, make deposits and withdrawals**, with persistence in a **MySQL** relational database.

The project was built with **Java 17+** and **Spring Boot**, organized in layers (`controller`, `service`, `repository`, `model` and `dto`) and using **Spring Data JPA** for persistence. Monetary values use `BigDecimal`, avoiding the precision errors common with floating-point types.

---

## 🚀 Technologies used

| Technology | Purpose |
|---|---|
| **Java 17+** | Main project language |
| **Spring Boot** | Framework for building the application |
| **Spring Web MVC** | Building the REST endpoints |
| **Spring Data JPA** | Data persistence and abstraction |
| **MySQL** | Relational database |
| **Maven** | Dependency management and build |

---

## 🗂️ Project structure

```
minibank/
└── src/main/java/luizgustavo/minibank/
    ├── controller/   # REST endpoints (AccountController)
    ├── services/     # Business rules (AccountService)
    ├── repository/   # Data access (AccountRepository)
    ├── model/        # JPA entities (Account)
    ├── dto/          # Request objects (TransactionRequest)
    └── MinibankApplication.java
```

---

## ⚙️ Installation

### Prerequisites

- [Java JDK 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/) (or use the included `./mvnw` wrapper)
- [MySQL](https://www.mysql.com/) installed and running

### Step by step

1. **Clone the repository**

   ```bash
   git clone https://github.com/Gutstavo35/MiniBankAPI.git
   cd MiniBankAPI/minibank
   ```

2. **Create the database**

   Hibernate creates the tables automatically, but the database must already exist:

   ```sql
   CREATE DATABASE minibank;
   ```

3. **Set up credentials**

   `application.properties` reads the MySQL username and password from **environment variables**, so no credentials live in the code:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/minibank
   spring.datasource.username=${DB_USERNAME}
   spring.datasource.password=${DB_PASSWORD}
   spring.jpa.hibernate.ddl-auto=update
   ```

   Set the variables before running:

   **Linux / macOS**
   ```bash
   export DB_USERNAME=your_username
   export DB_PASSWORD=your_password
   ```

   **Windows (PowerShell)**
   ```powershell
   $env:DB_USERNAME="your_username"
   $env:DB_PASSWORD="your_password"
   ```

   > 💡 In IntelliJ IDEA, you can also set them under **Run → Edit Configurations → Environment variables**.

4. **Build the project**

   ```bash
   ./mvnw clean install
   ```

5. **Run the application**

   From the main class (`MinibankApplication`) in your IDE, or via terminal:

   ```bash
   ./mvnw spring-boot:run
   ```

6. The API will be available at:

   ```
   http://localhost:8080
   ```

---

## 📡 API usage

### Available endpoints

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/accounts` | Creates a new account (returns the list of accounts) |
| `GET` | `/accounts` | Lists all accounts |
| `GET` | `/accounts/{id}` | Retrieves an account by ID |
| `PATCH` | `/accounts/{id}/deposit` | Makes a deposit into the account |
| `PATCH` | `/accounts/{id}/withdraw` | Makes a withdrawal from the account |

### Data model — `Account`

| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Auto-generated identifier |
| `cpf` | `String` | Holder's CPF (Brazilian tax ID; unique, up to 14 characters) |
| `name` | `String` | Account holder's name |
| `amount` | `BigDecimal` | Account balance |

### Payload examples

#### ➕ Create account — `POST /accounts`

**Request:**

```json
{
  "cpf": "123.456.789-00",
  "name": "Maria Silva",
  "amount": 1000.00
}
```

**Response `200 OK`** (list with all registered accounts):

```json
[
  {
    "id": 1,
    "cpf": "123.456.789-00",
    "name": "Maria Silva",
    "amount": 1000.00
  }
]
```

#### 📋 List accounts — `GET /accounts`

**Response `200 OK`:**

```json
[
  {
    "id": 1,
    "cpf": "123.456.789-00",
    "name": "Maria Silva",
    "amount": 1000.00
  }
]
```

#### 🔍 Get by ID — `GET /accounts/{id}`

**Response `200 OK`:**

```json
{
  "id": 1,
  "cpf": "123.456.789-00",
  "name": "Maria Silva",
  "amount": 1000.00
}
```

#### 💰 Deposit — `PATCH /accounts/{id}/deposit`

**Request:**

```json
{
  "amount": 250.00
}
```

**Response `200 OK`:**

```json
{
  "id": 1,
  "cpf": "123.456.789-00",
  "name": "Maria Silva",
  "amount": 1250.00
}
```

#### 💸 Withdraw — `PATCH /accounts/{id}/withdraw`

**Request:**

```json
{
  "amount": 300.00
}
```

**Response `200 OK`:**

```json
{
  "id": 1,
  "cpf": "123.456.789-00",
  "name": "Maria Silva",
  "amount": 950.00
}
```

### Business rules

- The `amount` of deposits and withdrawals must be **greater than zero**; otherwise the operation is rejected.
- Operations on non-existent accounts are rejected with the message `account not found`.

---

## 🧪 Testing the API

Inside the `minibank/src/test/docs` folder you'll find the `accounts.http` file with ready-to-use requests to test all API endpoints, including error cases (zero-amount deposit and non-existent account). Simply open the file in **IntelliJ IDEA** (or VS Code with the **REST Client** extension) and click the ▶ button next to each request, without needing external tools like Postman or Insomnia.

If you prefer the terminal, a quick one-line example that works on any system:

```bash
curl http://localhost:8080/accounts
```

---

## 🛣️ Roadmap

- [ ] Insufficient-funds validation on withdrawals
- [ ] Transfer endpoint between accounts
- [ ] Global exception handling with proper HTTP responses (`404`, `400`)
- [ ] CPF and input field validation (Bean Validation)
- [ ] Transaction history (statement)
- [ ] Interactive documentation with Swagger/OpenAPI

---

## 👤 Author

**Luiz Gustavo V. Gonzaga**

[![GitHub](https://img.shields.io/badge/GitHub-Gutstavo35-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/Gutstavo35)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Luiz%20Gustavo-0A66C2?style=flat-square&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luiz-gustavo-vilela-gonzaga-7857a7319/)

---

## 📄 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.
