# Controle de Patrimônio do NERDS - API

API REST desenvolvida para o Desafio Técnico Prático da 2ª Fase do processo seletivo do NERDS-UFC. 

O sistema gerencia o inventário do projeto, com funcionalidades de adição, atualização e exclusão de patrimônio.

## 🛠 Tecnologias
- Java 17 / 21
- Spring Boot 3
- Spring Data JPA
- H2 Database (Banco em memória)
- Lombok

## 🚀 Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git repo clone muliro2/nerds-patrimonio-api
   ```
2. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```

## 🚀 Como Executar os teste
1. Execute os testes:
   ```bash
   .\mvnw test
   ```
   
## 🧪 Testando a API

### 🔌 Swagger UI (Documentação Interativa)
Acesse a documentação automática e teste os endpoints diretamente pelo navegador:
- **URL:** `http://localhost:8080/swagger-ui/index.html`

### 🚀 Postman Collection
Para testar via Postman, incluí um arquivo de collection na raiz do porjeto.

1. **Baixe o arquivo:** [nerds_patrimonio_collection.json](./nerds_patrimonio_collection.json) 📥
2. Abra o Postman e clique em **Import**.
3. Arraste o arquivo baixado para dentro do Postman.

---
