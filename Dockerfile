# Use a imagem base do OpenJDK 21
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o jar da aplicação para o diretório de trabalho no contêiner
COPY build/libs/cadastro-simples-0.0.1-SNAPSHOT.jar /app/cadastro-simples.jar

# Expõe a porta 8080 para acessar a aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "cadastro-simples.jar"]
