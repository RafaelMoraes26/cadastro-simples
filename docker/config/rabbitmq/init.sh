#!/bin/sh

# Inicia o RabbitMQ em segundo plano
rabbitmq-server &

# Espera o RabbitMQ iniciar completamente
sleep 10

# Cria a fila UserPostsQueue
rabbitmqadmin declare queue name=UserPostsQueue durable=false

# Mantém o contêiner em execução
tail -f /dev/null