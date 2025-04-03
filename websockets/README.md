# Monitoramento de Temperatura em Tempo Real 

##  Sobre a aplicação

A aplicação realiza o monitoramento em tempo real da temperatura de diferentes cidades ao redor do mundo. Utilizando Spring Boot com WebSockets, o sistema consulta a cada 5 segundos a API do OpenWeatherMap, selecionando uma cidade aleatória de uma lista predefinida. As informações de clima, como cidade, país, temperatura, descrição e horário, são empacotadas em um DTO e transmitidas via WebSocket para todos os clientes conectados. No front-end, uma página HTML com JavaScript, SockJS e Stomp.js exibe automaticamente os dados recebidos em uma lista dinâmica. A atualização ocorre em tempo real, sem necessidade de recarregar a página, simulando o funcionamento de um painel de monitoramento climático.

---

## Como iniciar a aplicação

### Pré-requisitos

- Java 17 ou superior
- Maven
- Conexão com a internet (para acessar a API do OpenWeatherMap)
- Navegador moderno (para o cliente HTML)

### Organização dos arquivos

Certifique-se de que o projeto esteja estruturado assim:

```
.
├── index.html
└── websockets/          # Pasta do projeto Spring Boot
```

> O arquivo `index.html` deve estar **fora da pasta Spring**, no mesmo nível do projeto.

---

###  Passos para rodar o back-end

1. **Acesse a pasta do projeto:**

```bash
cd websockets
```

2. **Execute a aplicação com Maven:**

```bash
./mvnw spring-boot:run
```

> Ou compile e execute com:

```bash
./mvnw clean package
java -jar target/websockets-0.0.1-SNAPSHOT.jar
```

O servidor será iniciado em `http://localhost:8080`.

---

###  Passos para rodar o front-end

1. **Abra o arquivo `index.html`** com seu navegador favorito:
   - Duplo clique no arquivo `index.html`
   - Ou arraste-o para dentro de uma aba do navegador

2. A página exibirá as temperaturas recebidas em tempo real via WebSocket, atualizando automaticamente a cada 5 segundos.

---

## Observações

- A aplicação usa a API pública do OpenWeatherMap (uma chave de API válida é necessária).
- O WebSocket utiliza `SockJS` para compatibilidade com navegadores antigos.
- Este projeto simula um cenário real de streaming de dados, útil para dashboards ou sistemas de monitoramento (como IoT).

---

## 📡 Tecnologias utilizadas

- **Spring Boot** (Web, Scheduling, WebSocket)
- **SockJS + STOMP.js** (cliente WebSocket)
- **OpenWeatherMap API** (consulta de dados de clima)
- **JavaScript / HTML5**
