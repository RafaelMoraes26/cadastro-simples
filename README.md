# Como Rodar o RabbitMQ via Docker

Este repositório contém um Dockerfile que configura um servidor RabbitMQ e cria uma fila não durável chamada `UserPostsQueue`.

## Pré-requisitos

- Docker instalado em seu sistema. Você pode fazer o download e seguir as instruções de instalação [aqui](https://www.docker.com/get-started).

## Instruções de Uso

1. Clone este repositório em sua máquina local:

    ```bash
    git clone https://github.com/RafaelMoraes26/cadastro-simples
    ```

2. Navegue até o diretório clonado:

    ```bash
    cd cadastro-simples
    ```

3. Construa a imagem Docker:

    ```bash
    docker build -t rabbitmq-custom .
    ```

4. Após a construção da imagem, execute o contêiner:

    ```bash
    docker run -d --name rabbitmq-server -p 5672:5672 -p 15672:15672 rabbitmq-custom
    ```

5. Agora o RabbitMQ estará em execução no seu sistema. Você pode acessar o painel de gerenciamento do RabbitMQ em [http://localhost:15672](http://localhost:15672) utilizando as seguintes credenciais padrão:
    - **Usuário:** guest
    - **Senha:** guest

6. Para parar o contêiner, execute o seguinte comando:

    ```bash
    docker stop rabbitmq-server
    ```

7. Para remover o contêiner, execute o seguinte comando:

    ```bash
    docker rm rabbitmq-server
    ```

## Personalizações

- Se desejar fazer alterações na configuração do RabbitMQ ou na fila criada, você pode modificar o arquivo `Dockerfile` e reconstruir a imagem conforme necessário.

- Para outras customizações ou configurações avançadas, consulte a documentação oficial do RabbitMQ em [https://www.rabbitmq.com/](https://www.rabbitmq.com/).
