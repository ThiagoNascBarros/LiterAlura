# LiterAlura - Catálogo de Livros
O LiterAlura é uma aplicação de linha de comando (CLI) desenvolvida como parte de um desafio prático para a formação Java do programa Oracle Next Education (ONE). O sistema realiza o consumo da API Gutendex, permitindo a busca de obras literárias, a persistência de dados em um banco relacional e a geração de estatísticas sobre autores e idiomas.

## 🛠️ Funcionalidades
O sistema oferece uma interface interativa via console com as seguintes opções:

- Buscar livro por título: Consulta a API externa e salva o livro e seu autor no banco de dados local.
- Listar livros registrados: Exibe todos os livros que já foram persistidos no sistema.
- Listar todos os livros: Realiza uma consulta geral de obras disponíveis diretamente na API Gutendex.
- Listar livros por idioma: Filtra as obras salvas no banco de dados por siglas de idioma (ex: en, pt, es).
- Listar autores vivos em determinado ano: Consulta a API por autores que estavam vivos no ano informado pelo utilizador.
- Listar autores vivos e registrados: Filtra no banco de dados local os autores cadastrados que atendem ao critério de ano.

## 🏗️ Estrutura e Tecnologias
O projeto foi construído seguindo princípios de arquitetura desacoplada para facilitar a manutenção e escalabilidade:

- Linguagem: Java 17.
- Framework: Spring Boot 4.0.1.
- Persistência: Spring Data JPA com Hibernate.
- Banco de Dados: PostgreSQL.
- Comunicação: HttpClient para consumo de API REST e Jackson para a desserialização de JSON.
- Gerenciamento de Dependências: Gradle.
- Arquitetura: Organização baseada em Portas e Adaptadores (Arquitetura Hexagonal).

## 🚀 Como Executar
### Pré-requisitos
- Java JDK 17 ou superior.
- PostgreSQL instalado e em execução.
- Criação de um banco de dados local chamado literalura.

### Configuração
No arquivo src/main/resources/application.yaml, certifique-se de que as credenciais de acesso ao seu banco de dados local estão configuradas corretamente:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/literalura
    username: seu_usuario
    password: sua_senha
```

### Execução via terminal
```bash
./gradlew bootRun
```

# 📄 Licença
Este projeto está sob a licença MIT.
Projeto desenvolvido por Thiago Nascimento Barros como parte do desafio da formação ONE (Alura & Oracle).
