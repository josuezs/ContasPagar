
# Contas a pagar

Aplicação desenvolvida na IDE eclipse, utilizando Java 8, EJB, JPA com Hibernate e Liquibase.

Possui uma interface simples em Primefaces, bem como um serviço RESTfull para incluir e obter contas a pagar.

É possível rodar alguns casos de teste elaborados em JUnit 5, para validar o cálculo de multas e juros da aplicação.


## Instruções para deploy

- No seu banco criar o database "deliver_it", enquanto que o liquibase se encarregará de criar a estrutura de tabelas. O projeto está configurado para rodar o Liquibase via Servlet Listener, ocorrendo no deploy da aplicação de forma automática.
- No servidor de aplicação configurar um datasource `"java:jboss/datasources/contasPagar"` apontando para o database criado.


## Uso

- O endpoint para manipulação das contas a pagar fica na URL:
http://localhost:8080/ContasPagarWeb/service/conta
	- GET: obter uma lista com as contas cadastradas
	- PUT: cadastrar uma conta

Exemplo do body para a request de inclusão de conta:
```json
{
 	"nome": "Joao",
	"vlrOriginal": "100.21",
	"dtaVencimento": "2020-09-01",
	"dtaPagamento": "2020-09-07"
}
```

## Regra de negócio

- Não foi considerado cálculo de juros compostos. Ou seja, a multa diária incide sempre sobre o valor base.
- As contas são únicas por nome e vencimento.


## Considerações

Projeto desenvolvido em ambiente com servidor de aplicação Wildfly versão 18, com Java 8 update 221 64 bits e banco Postgre.
