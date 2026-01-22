# LiterAlura
Projeto linha de comando que faz buscas de livros usando API da Gutendex, Salva os livros buscados e lista os livros salvos com diferentes tipos de exibição

## Estrutura do projeto
```
LiterAlura/
├── application/
│   ├── ports/
│   │   ├── inbound/
│   │   │   └── // Contém interfaces que definem os pontos de entrada para a aplicação. Estas portas são usadas por agentes externos que interagem com a aplicação, como interfaces de usuário ou requisições de API REST.
│   │   └── outbound/
│   │       └── // Define interfaces para serviços externos que a aplicação precisa consumir, como bancos de dados ou serviços REST externos. Estas portas ajudam a desacoplar a lógica de negócio dos detalhes de implementação do acesso a recursos externos.
│   └── services/
│       └── // Implementa a lógica da aplicação coordenando as atividades entre as portas e o domínio. Os serviços de aplicação desempenham um papel crucial na orquestração de operações de domínio, executando a lógica de negócio e atuando como uma ponte entre o domínio e os adaptadores de infraestrutura.
├── domain/
│   ├── exceptions/
│   │   └── // Define exceções de domínio específicas que podem ser lançadas pela lógica de negócio.
│   ├── entities/
│   │   └── // Contém as entidades de domínio que encapsulam a lógica de negócio crítica e os dados.
│   └── other domain folders/
│       └── // Pode incluir objetos de valor (value objects), agregados, eventos de domínio, etc., que são fundamentais para a lógica de negócio e as regras de domínio.
└── infrastructure/
    ├── adapters/
    │   ├── inbound/
    │   │   ├── rest/
    │   │   │   └── // Implementa adaptadores para interfaces web, tratando requisições HTTP recebidas e transformando-as em chamadas para as portas de entrada apropriadas.
    │   │   ├── tasks/
    │   │   │   └── // Para tarefas agendadas que realizam operações periódicas dentro da aplicação.
    │   │   └── events/
    │   │       └── // Gerencia a captura e o processamento de eventos do sistema ou eventos de integração com outros sistemas.
    │   └── outbound/
    │       ├── persistence/
    │       │   └── // Implementa a persistência de dados, por exemplo, usando JPA para interagir com bancos de dados, encapsulando toda a lógica de acesso a dados.
    │       └── rest/
    │           └── // Contém os adaptadores necessários para fazer chamadas a APIs externas, encapsulando a lógica de como interagir com outros serviços web.
    └── configuration/
        └── // Configurações específicas do framework e da infraestrutura, como configurações de segurança, configuração de beans do Spring, etc.```