# ProgMod
Repositorio para o trabalho em grupo da disciplina de Programação Modular - DCC UFMG

## Configurações


### Database

Para criar o banco de dados, execute os seguintes comandos no mysql, antes de rodar o código:

create database db_example;

create user 'springuser'@'localhost' identified by 'ThePassword';

grant all on db_example.* to 'springuser'@'localhost';


### Frontend

Para executar o frontend é necessario instalar o [Node.js](https://nodejs.org "Node's Homepage")

Após a instação, executar os comandos no terminal:

* npm i

Para instalar as dependencias

* npm start

para executar o front

para parar a execução, feche o terminal ou aperte `ctrl+c`


