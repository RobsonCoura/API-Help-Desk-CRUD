
# Este Dockerfile inicia a construção de um contêiner Docker, usando a imagem base do Ubuntu mais recente, e nomeia esta etapa de construção como "build".
FROM ubuntu:latest AS build

# Executar dentro do contêiner e atualizar os pacotes
RUN apt-get update && apt-get install -y \
    openjdk-11-jdk \
    maven

# Criar diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml separadamente para evitar re-instalação de dependências quando apenas os arquivos de código fonte forem alterados
COPY pom.xml .

# Baixar as dependências do Maven (somente pom.xml é necessário aqui)
RUN mvn dependency:go-offline

# Copiar todo o código fonte
COPY src ./src

# Compilar o projeto e gerar o arquivo JAR
RUN mvn clean package

# Imagem que vai utilizar para rodar a aplicação
FROM openjdk:11-jre-slim

# Expor a porta 8080 (opcional, apenas para documentação)
EXPOSE 8080

# Copiar o arquivo JAR para dentro da imagem
COPY --from=build /app/target/helpdesk-0.0.1-SNAPSHOT.jar /app/app.jar

# Configuração do que você quer que execute
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
