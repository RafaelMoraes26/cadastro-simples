#!/bin/sh

# Inicia o RabbitMQ em segundo plano
rabbitmq-server &

# Espera o RabbitMQ iniciar completamente
sleep 10

# Cria a exchange UserPostDLX
rabbitmqadmin declare exchange name=UserPostDLX type=direct durable=true

# Cria a fila UserPostsQueue e define a DLX
rabbitmqadmin declare queue name=UserPostsQueue durable=true arguments='{"x-dead-letter-exchange":"UserPostDLX", "x-dead-letter-routing-key":"UserPostDLQ"}'

# Cria a fila UserPostDLQ
rabbitmqadmin declare queue name=UserPostDLQ durable=true

# Vincula a fila UserPostDLQ à exchange UserPostDLX
rabbitmqadmin declare binding source=UserPostDLX destination=UserPostDLQ destination_type=queue routing_key=UserPostDLQ

# Mantém o contêiner em execução
tail -f /dev/null