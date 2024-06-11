
# Este Dockerfile inicia a construção de um contêiner Docker, usando a imagem base do Ubuntu mais recente, e nomeia esta etapa de construção como "build".
FROM ubuntu:latest AS build

# Executar dentro do contêiner e atualizar os pacotes
RUN apt-get update
# Instalar e configurar java na aplicação
RUN apt-get install openjdk-11-jdk -y

#Copiar tudo que tem dentro do projeto para dentro do contêiner
COPY . .

# Instalação do maven para rodar o projeto
RUN apt-get install maven -y

# Gerar um arquivo JAR
RUN mvn clean install

# Imagem que vai utilizar para rodar a aplicação
FROM openjdk:11-jre-slim

# Expor a porta 8080
EXPOSE 8080

# Copiar o arquivo JAR para dentro da imagem
COPY --from=build /target/helpdesk-0.0.1-SNAPSHOT.jar /app.jar

# Configuração do que você quer que execute
ENTRYPOINT [ "java", "-jar", "app.jar" ]

# Comando para executar o aplicativo quando o contêiner for iniciado
CMD ["java", "-jar", "/app.jar"]

