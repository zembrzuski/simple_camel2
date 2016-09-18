#Simple Camel

Esse projeto serve para fazer uma simples demonstração do uso do Apache Camel. 

Possui os seguintes atributos

- Usa o Spring Boot para fazer boot da aplicação
- Escuta uma fila rabbit mq
- Na mensagem que aparece na fila do rabbit mq deve aparecer um id.
- Entao ele verifica se existe um dado com esse id em um outro serviço REST
- Se existe esse dado, ele faz um PUT usando esse id
- Se nao existe esse dado, ele faz um POST usando esse id
- Apos tudo, ele escreve o dado em disco.


