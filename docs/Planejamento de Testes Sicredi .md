#  Planejamento de Testes Sicredi 

## Requisitos Funcionais e Não Funcionais

### Quais são as Funcionalidades da API?

- reference

	- GET /test

		- Buscar o status da aplicação

			- Response - 200 OK

	- GET /users

		- Buscar usuário para autenticação

			- Response - 200 OK

	- POST /auth/login

		- Criação de token para Autenticação

			- Response - 201 OK

	- GET /auth/products

		- Buscar produtos com autenticação

			- Response - 200 OK
			- Response - 403 Forbidden
			- Response - 401 Unauthorized

	- POST /products/add

		- Criação de produto

			- Response - 201 OK

	- GET /products

		- Buscar todos os produtos

			- Response - 200 OK

	- GET /products/{id}

		- Buscar apenas um produto por id

			- Response - 200 OK
			- Response - 404 Not found

### Eficiência esperada

- Suponha que a API Busca Todos os Produtos demora mais de 3 segundos para responder

### Compatibilidade que deve possuir

### Nível de Maturidade

### Confiabilidade esperada

### Usabilidade que suas interfaces precisam ter

### Diretrizes de Segurança

- A API Criação de token para Autenticação só pode 

### Diretrizes de Portabilidade

## Riscos 

### Risco desconhecido 

- Quais risco pode ocorrer ao adicionar um novo produto com estoque zerado?

	- Probabilidade 

		- 4

	- Impacto

		- 5

- É possivel adicionar um produto sem titulo?

	- Probabilidade 

		- 5

	- Impacto

		- 2

- Tentar gerar o token usando as credenciais de outro usuário 

	- Probabilidade 

		- 5

	- Impacto

		- 5

## Integrações

### A API se conecta a quais outros servicos? 

### Pensar teste de dependência

### Precisa usar moks?

- Não

## Ambiente de Testes

### Instalar o Intellij

### Criar conta no GitLab

## Abordagem de Testes

### teste exploratório

- Para testar a API Utilizarei o check List  sugerido por Julio de Lima 

	-  Regra de negócio
	-  Continuidade dos fluxos
	-  Tipagem de dados
	-  Parâmetro ( corpo, filtro, path, etc)
	-  Uso de token de usuário diferente
	-  Validação de métodos
	-  Listagem de 0, 1, e muitos recursos
	-  Estrutura da resposta
	-  Códigos dos estados HTPP
	-  Documentos dos contratos da API

- Heuristica VADER

	- Verbos - Métodos da API
	- Autentificação e Autorização
	- Dados e sua Estrutura
	- Codigo de HTTP para Erros
	- Responsibilidade - Tempo de respost

### Baseada na Regra Negocio

- Partição de Equivalência 
- Tabela de decisão
- Analise valor Limite
- Analise de Risco

### Cobertura de Teste

- 100% dos Status Code
- 100% caminhos Feliz 
- 100 % Caminhos alternativo

### Teste destrutivo

- Validar como a aplicação se comporta ao mudar alguns dados do Json, e os tipos de recurso , não apresentado da documentação

## Ferramentas

### Postman

### Java JDK

### Java 5

### Intellij

### RestAssurd

### Git

### GitLab

## Allure Report

## Estimativas E Cronograma

### Prazo de entrega dia  18/03

- 6 Horas para planejar 
- 6 horas para configurar o ambiente
- 4 Horas executar teste funcionalidade no Postman
- 12 Horas para automatizar os testes
- 8 horas para analisar / colher resultados e escrever os bugs

## Automação de Testes

### Teste API

- REST Assured

### MVN

### Relatórios

- Surefire

*XMind - Trial Version*