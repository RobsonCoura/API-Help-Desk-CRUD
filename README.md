# API-Help-Desk-CRUD
### API RESTful construída com Spring Boot + Angular
---
### ⏳ Status do Projeto
> :construction: Projeto concluído :construction:
---

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/RobsonCoura/API-Clinica-Medica-CRUD/blob/main/LICENSE) 

## Sobre o projeto

O projeto Helpdesk é uma plataforma de suporte técnico que foi projetada para facilitar a gestão de tickets de atendimento ao cliente.

A origem dessa API está centrada na criação de um sistema eficiente para registrar, monitorar e resolver problemas reportados por usuários, fornecendo um meio estruturado para equipes de suporte técnico gerenciarem suas interações e resolverem solicitações de maneira organizada.

Ela possibilita respostas rápidas e organizadas às solicitações de suporte, essencial para empresas que buscam otimizar seu serviço ao cliente e manter uma comunicação eficaz.

## ⚙️ Funcionalidades

- [x] Tela de login;
- [x] CRUD de técnico;
- [x] CRUD de cliente;
- [x] Create, Read e Update de chamados;
- [x] Button access Home;
- [x] Button access Github;
- [x] Button de Logout;
---

## 🎨 Layout

O layout da aplicação mobile está disponível neste link: <a href="https://www.figma.com/file/N4CgpJqsg7gjbKuDmra3EV/Voll.med">Figma</a>

---

## 📄 Documentação

A documentação das funcionalidades da aplicação pode ser acessada neste link: <a href="">Trello</a>
Para acessar o Swagger: <a href="">Swagger UI</a>

---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 11](https://www.oracle.com/java)**
- **[Spring Boot](https://spring.io/projects/spring-boot)**
- **[Intellij IDEA](https://www.jetbrains.com/help/idea/getting-started.html)**
- **[Maven](https://maven.apache.org)**
- **[JPA/Hibernate](https://hibernate.org/)**
- **[MySQL](https://www.mysql.com)**
- **[H2-dataBase](https://www.h2database.com/html/main.html)**
- **[TypeScript](https://www.typescriptlang.org/docs/)**
- **[HTML](https://developer.mozilla.org/pt-BR/docs/Web/HTML)**
- **[CSS](https://developer.mozilla.org/pt-BR/docs/Web/CSS)**
- **[Angular](https://projectlombok.org](https://angular.dev/))**
- **[JWT](https://jwt.io/)**
- **[Postman](https://learning.postman.com/docs/introduction/overview/)**
- **[Git](https://git-scm.com/doc)**
- **[GitHub](https://github.com/)**
- **[Render](https://render.com/)**
- **[Vercel](https://vercel.com/docs)**

---

## 🎯 Regras de negócio:

1. O sistema deve possuir uma funcionalidade que faz validacao de formulario para o login, na qual as seguintes informações deverão ser preenchidas:

- Email
- Senha

2. O sistema deve possuir uma funcionalidade que faz uma validacao de nivel de PERMISSAO de usuario no sistema:

- ADMIN
- CLIENTE
- TECNICO

3. O sistema deve possuir uma funcionalidade que faz uma verificacao de nivel de PRIORIDADE no chamado do sistema:

- BAIXA
- MEDIA
- ALTA

4. O sistema deve possuir uma funcionalidade que faz uma verificacao de nivel do STATUS do chamado no sistema:

- ABERTO
- EM ANDAMENTO
- ALTA

### Neste projeto foi praticado:
<ul>
<li>Instalar e configurar as ferramentas corretas para iniciar um projeto Web Fullstack do zero;</li>
<li>Conceitos de Spring Boot 2.x.x;</li>
<li>Spring Data JPA;</li>
<li>Mapear uma entidade JPA e criar uma interface Repository para ela;</li>
<li>Mapear parâmetros dinâmicos em URL com a anotação @PathVariable;</li>
<li>Utilizar a interface Pageable do Spring para realizar consultas com paginação;</li>
<li>Hibernate;</li>
<li>Autenticação com Tokens JWT;</li>
<li>Devolver um token gerado na API quando um usuário se autenticar nela;</li>
<li>Utilizar a biblioteca Auth0 java-jwt para realizar a validação dos tokens recebidos na API;</li>
<li>Autorização com Tokens JWT;</li>
<li>Liberar e restringir requisições, de acordo com a URL e o verbo do protocolo HTTP;</li>
<li>Conceitos de Angular 12;</li>
<li>Conceitos básicos de Typescript, HTML5 e CSS3;</li>
<li>Criar uma API RESTful;</li>
<li>Consumir uma API REST usando a ferramenta Postman;</li>
<li>Consumir uma API REST usando o front desenvolvido no curso;</li>
<li>Utilização do banco de dados h2 em tempo de compilação;</li>
<li>Tratamento de exceções de forma personalizada;</li>
<li>Desenvolvimento em camadas usando o padrão MVC;</li>
<li>Padrão DTO (Data Transfer Objects);</li>
<li>MySQL em projeto Spring Boot;</li>
<li>Simplificar o JSON devolvido pela API em casos de erro de validação do Bean Validation;</li>
<li>Implementar perfis de Teste e Desenvolvimento;</li>
<li>Validações com Validations;</li>
<li>Controlar a paginação e a ordenação dos dados devolvidos pela API com os parâmetros page;</li>
<li>Protocolo HTTP no padrão REST;</li>
<li>Realizar deploy de ambos os sistemas na nuvem.</li>
</ul>
