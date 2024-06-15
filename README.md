# Projeto de Cadastro Simples

Este projeto é uma aplicação de cadastro simples que utiliza MongoDB e RabbitMQ como dependências. Este guia ajudará a configurar e executar o projeto usando Docker Compose.

## Pré-requisitos

- **Docker**: [Instalar Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Instalar Docker Compose](https://docs.docker.com/compose/install/)
- **Gradle**: [Instalar Gradle](https://gradle.org/releases/)
- **JDK**: [Instalar JDK17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

Tenha em mente que será necessário pelo menos gradle na versão 7.3 para suportar o JDK17
## Instruções de Uso

### Clonar o Repositório

Primeiro, clone o repositório do projeto para sua máquina local:

```bash
git clone https://github.com/RafaelMoraes26/cadastro-simples
cd cadastro-simples
```
### Construir e Executar com Docker Compose

Execute o seguinte comando no diretório do projeto para construir e rodar a aplicação e suas dependências utilizando Docker:

```bash
# Buildar, construir e subir imagens no docker
gradle clean build && docker-compose build --no-cache && docker-compose up
```
Após executar esses comandos, a aplicação estará acessível em [http://localhost:8080](http://localhost:8080).

### Verificar Logs

Para acessar os logs da aplicação em tempo real, utilize o comando:

```bash
docker-compose logs -f app
```

Este comando permite acompanhar os logs de eventos da aplicação, facilitando o diagnóstico de problemas e a verificação de operações normais.

### Parar e Remover os Contêineres

Quando precisar parar e remover todos os serviços relacionados ao projeto, execute:

```bash
docker-compose down
```

Este comando desliga todos os contêineres iniciados pelo docker-compose up, removendo os contêineres, redes gerenciadas e volumes montados, caso não sejam volumes persistentes.

### Diagnóstico e Solução de Problemas

Se encontrar dificuldades para acessar a aplicação ou se ocorrerem erros durante a execução, verifique os logs detalhados dos contêineres, como mencionado anteriormente. Além disso, certifique-se de que todas as portas necessárias não estão sendo bloqueadas ou já utilizadas por outros processos em sua máquina.

Para verificar o status dos contêineres e identificar qualquer contêiner que não esteja rodando corretamente, utilize:

```bash
docker ps -a
```

Este comando lista todos os contêineres, permitindo ver quais estão em execução e quais pararam. Para problemas mais específicos, consultar a documentação oficial do Docker e Docker Compose pode fornecer soluções mais detalhadas e ajustadas ao seu ambiente específico.
