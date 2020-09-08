
# Contas a pagar

Aplicação desenvolvida em EJB, JPA com Hibernate. Possui uma interface simples em Primefaces, bem como um serviço RESTfull para incluir e obter contas a pagar.

## Instruções para deploy

- No seu banco criar o database "deliver_it", enquanto que o liquibase se encarregará de criar a estrutura de tabelas. O projeto está configurado para rodar o Liquibase via Servlet Listener, ocorrendo no deploy da aplicação de forma automática.
- No servidor de aplicação configurar um datasource "java:jboss/datasources/contasPagar" apontando para o database criado.

## Uso

- O endpoint para das contas a pagar fica na URL:
http://localhost:8080/ContasPagarWeb/service/conta
	- GET: lista as contas cadastradas
	- PUT: cadastra uma conta, onde deve enviar o body abaixo na sua request:
{
 	"nome": "Joao",
	"vlrOriginal": "100.21",
	"dtaVencimento": "2020-09-01",
	"dtaPagamento": "2020-09-07"
}

## Regra de negócio

- Não foi considerado cálculo de juros compostos. Ou seja, a multa diária incide sempre sobre o valor base.
- As contas são únicas por nome e vencimento.

## Considerações

Projeto desenvolvido em ambiente com servidor de aplicação Wildfly versão 18, com Java 8 update 221 64 bits e banco Postgre.
