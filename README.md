# Sicredi Desafio - Projeto de Testes de API

## Descrição
Este projeto visa testar e automatizar a API responsável por gerenciar produtos eletrônicos da Sicredi. Utiliza o framework Rest-Assured para realizar requisições HTTP e o Junit Jupiter para escrever e executar os testes.

## Estrutura do Projeto
- **src/test/java/br.com.softdesign.api/**
    - **base:** Contém classes de configuração e setup comuns para os testes.
    - **factory:** Contém classes de fábrica para criar objetos de teste.
    - **pojo:** Contém classes de objetos de modelo para representar entidades da API.
    - **product:** Contém classes de teste para os endpoints relacionados aos produtos.
    - **user:** Contém classes de teste para os endpoints relacionados aos usuários.
    - **smoke:** Contém testes de fumaça para garantir o funcionamento básico da API, validando apenas o retorno do status code.
    - **resources:** Contém o arquivo postProduct.Json como template para adicionar um novo produto e o allure.properties

## Configuração e Execução

1. Clonar o projeto git clone https://gitlab.com/welita/desafio-qe-soft-design.git
2. Certifique-se de ter as dependências do Maven instaladas e configuradas corretamente. 
3. Executar as classes de teste desejadas.
    - Para executar todos os testes, execute `mvn test` no terminal.

## Configuração e Execução do Allure

Pré-requisitos: as dependências e plugins necessários devem estar instalados no arquivo pom.xml. Para mais informações, consulte: [Documentação do Allure](https://allurereport.org/docs/junit5/)

Passos para rodar o Allure no Windows:

1. Baixe o Scoop em (https://github.com/ScoopInstaller/Install#for-admin) usando o comando: `irm get.scoop.sh | iex`
2. Instale o Allure via Scoop usando o comando: `scoop install allure`
3. Configure o Allure:
    - Vá até a pasta `bin` onde o Allure foi instalado.
    - Copie o caminho e adicione-o às variáveis de ambiente.
4. Após instalar e configurar o Allure, execute os testes usando o comando `mvn test`.
5. Os resultados serão exibidos na pasta `target`.
6. Para gerar o relatório HTML do Allure, use o comando `allure serve <endereço path da pasta do allure-results>`. Por exemplo: `allure serve C:\Projects\SoftDesign\target\allure-results`.

## Dependências
- JUnit Jupiter 5.10.1
- Rest-Assured 5.3.0
- Jackson Core 2.16.1
- Gson 2.10.1
- Allure 2.25.0
- AspectJ 1.9.21
- Allure Maven 2.12.0
- Maven Compiler Plugin 3.12.1

# Planejamento de Testes Sicredi

### Funcionalidades da API

- **reference**
    - `GET /test`: Busca o status da aplicação
        - Response: 200 OK
    - `GET /users`: Busca usuário para autenticação
        - Response: 200 OK
    - `POST /auth/login`: Criação de token para Autenticação
        - Response: 201 OK
    - `GET /auth/products`: Busca produtos com autenticação
        - Response:
            - 200 OK
            - 403 Forbidden
            - 401 Unauthorized
    - `POST /products/add`: Criação de produto
        - Response: 201 Created
    - `GET /products`: Busca todos os produtos
        - Response: 200 OK
    - `GET /products/{id}`: Busca um produto por id
        - Response:
            - 200 OK
            - 404 Not Found

### Eficiência esperada

- A API "Busca Todos os Produtos" deve responder em menos de 3 segundos.

### Riscos

- **Risco desconhecido**: Possíveis problemas ao adicionar um novo produto com estoque zerado.
    - Probabilidade: 4
    - Impacto: 5
- **Risco desconhecido**: Tentativa de adicionar um produto sem título.
    - Probabilidade: 5
    - Impacto: 2
- **Risco desconhecido**: Tentativa de gerar o token usando as credenciais de outro usuário.
    - Probabilidade: 5
    - Impacto: 5

## Abordagem de Testes

### Teste exploratório

- Utilizarei o check-list sugerido por Julio de Lima para testar a API, abrangendo aspectos como regras de negócio, continuidade dos fluxos, tipagem de dados, parâmetros, uso de tokens, validação de métodos, estrutura da resposta, códigos de estado HTTP e documentos dos contratos da API.
- Heurística VADER será aplicada, considerando verbos, autenticação, estrutura de dados, códigos de erro HTTP e responsividade.

### Baseada na Regra de Negócio

- Utilizarei técnicas como Partição de Equivalência, Tabela de Decisão, Análise de Valor Limite e Análise de Risco.

### Cobertura de Teste

- Cobrirei 100% dos Status Code, caminhos felizes e alternativos.
- Criação dos Smoke Test

### Teste Destrutivo

- Realizarei testes para verificar como a aplicação se comporta diante de mudanças nos dados do JSON e tipos de recursos não documentados.

## Ferramentas

- Postman
- Java JDK
- JUnit Jupiter
- Rest-Assured
- Git
- GitLab
- Allure reporter

## Estimativas e Cronograma

- Prazo de entrega: 18/03
- Planejamento: 6 horas
- Configuração do ambiente: 6 horas
- Execução dos testes funcionais no Postman: 4 horas
- Automação dos testes: 12 horas
- Análise de resultados e redação de bugs: 8 horas

### Automação de Testes

- Utilizarei REST Assured e Maven para automação dos testes.
- Os relatórios serão gerados pelo Allure Report.

### Resultado Final dos Testes Automatizados
- Foram 22 testes sendo:
    - 5 Testes na Classe UserTest - Falharam  4 Testes, desses 4 apenas 1 seria bug e os outros é nescessario avaliar melhor a documentação da API, proposta no testes pois não consta as informações. 
    - 10 Testes na Classe ProdutoTest - Falharam 2 Testes, e 3 Testes com a validação comentada, por se tratar de uma hipotese.
    - 7 na Classe de Smoke Testes - Falharam 2 Testes , que são a validação do status code que deveria retornr 201 e esta retornando 200.
    - No total 63,63% dos testes passaram e 36,37% dos testes falharam.
    - Baseando apenas no smoke testes que valida apenas o status code foram 71,42% dos testes que passaram e 28,57% falharam.


## Bugs Encontrados

| Bug | Descrição                                                                                                                                                                                                              | Correção Sugerida |
|-----|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------|
| 01  | Ao enviar a requisição "POST/products/add", o status retornado é 200, quando o correto seria 201 - Created conforme documentação.                                                                                      | Corrigir a resposta da API para refletir o status code correto. |
| 02  | Ao enviar a requisição "POST/auth/login", o status retornado é 200, quando o correto seria 201 - Created conforme documentação.                                                                                        | Corrigir a resposta da API para refletir o status code correto. |
| 03  | Ao fazer uma requisição para o endpoint "GET/auth/products" com um token expirado, o retorno não inclui a informação "expiredAt" e o parâmetro "name" está incorreto.                                                  | Corrigir a estrutura do retorno para incluir as informações corretas ou atualizar a documentação. |
| 04  | As informações retornadas do endpoint "GET/users" diferem da documentação fornecida.                                                                                                                                   | Ajustar o endpoint para retornar as informações conforme a documentação ou atualizar a documentação. |
| 05  | Ao enviar requisição "POST/auth/login" com as credenciais de outro usuário, a API retorna o status code 400, quando deveria retornar 401 Unauthorized.                                                                 | Corrigir a lógica de autenticação para retornar o status code correto. |
| 06  | Ao enviar requisição "POST/auth/login" com senha vazia ou usuário vazio, a API retorna o status code 400, quando deveria retornar 401 Unauthorized.                                                                    | Ajustar a validação das credenciais para retornar o status code correto. |
| 07  | A API "https://dummyjson.com/test" retorna 200 para todas as solicitações, incluindo métodos como Post, Put, Patch, Delete, e retorna 204 para o método Options.                                                       | Investigar e corrigir o comportamento inconsistente da API. |
| 08  | Ao enviar a requisição DELETE/users/10, a API retorna o status 200 e no corpo da resposta são retornados os dados do usuário excluído, indicando que o recurso foi excluído com sucesso.                               | Ajustar a API para retornar o status code 204 ou 404 quando o recurso for excluído. |
| 09  | Ao enviar a requisição PUT/users/10 com dados de usuário atualizados, a API retorna o status code 200, indicando que a atualização foi bem-sucedida. No entanto, alguns parâmetros retornados são exibidos em branco.  | Corrigir a lógica de atualização para manter os parâmetros não enviados em branco. |
| 10  | Ao enviar a requisição OPTIONS/https://dummyjson.com/auth/login com ou sem informações no corpo da requisição, a API retorna o status code 204, quando deveria retornar outro status code de acordo com a especificação. | Investigar e corrigir o comportamento inconsistente da API em relação ao método OPTIONS. |
| 11  | Ao enviar a requisição DELETE/auth/products/10, a API retorna o status code 200 e no corpo da resposta são retornados os dados do produto excluído, indicando que o recurso foi excluído com sucesso.                  | Ajustar a API para retornar o status code 204 ou 404 quando o recurso for excluído. |

## Sugestões ou Melhorias
- Incluir a documentação do Swagger para facilitar a compreensão da estrutura da API e validar a aderencia do contrato.
- Realizar investigações adicionais sobre problemas de persistência de dados durante testes destrutivos. (Como era uma api de para Testes, não sei o correto funcionamento, e se realmente os Bug 7 ao 11, acontecem na api real)
- A sugestão do retorno da API referente a requisição "POST/auth/login", nos bugs (5 e 6), foram apenas uma sugestão baseada na documentação (https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)
- Os Testes Destrutivos Foram realizados no Postman.
- Tentei simular alguns testes referentes à regra do negócio, como: "Não permitir estoque zerado", "Produto não deve ser adicionado sem título" e "Preço negativo". Porém, foram apenas hipóteses já que esses dados não constam na documentação.
- Os Testes de simulação estão comentados, pois são testes de hipóteses. Seria necessário conversar com o pessoal de negócios para verificar se os testes fazem sentido ou não.


***  Qualquer dúvida estou á disposição!!! ***

