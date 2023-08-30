# Desafio: Projeto Spring Boot estruturado

- Projeto DSCommerce estruturado e com todas as funcionalidades implementadas;
- Projeto implementado com Java e Spring Boot, usando banco de dados H2;

##

## :clipboard: - Diagrama:

![dscommerce drawio](https://github.com/carloshenriquefs/dscommerce-java/assets/54969405/244d212b-dcba-4d19-9442-431cb4994b21)

##

## :gear: - Endpoints:

* Category:

    ```GET``` - findAll <br /> 

##

* Order:

    ```GET``` - /{id} - findById <br />
    ```POST``` - insert <br />

##

* Product:

    ```GET``` - /{id} - findById <br />
    ```GET``` - findAll <br /> 
    ```POST``` - insert <br />
    ```PUT``` - /{id} - update <br />
    ```DELETE``` - /{id} - delete <br />

##

* User: 

    ```GET``` - /me - getME

##

### :white_check_mark: - Critérios:

- [x] - Endpoints públicos GET /produts e GET /products/{id} funcionam sem necessidade de login;
- [x] - Endpoint de login funcionando e retornando o token de acesso;
- [x] - Endpoints privados de produto (POST/PUT/DELETE) funcionam somente para usuário ADMIN;
- [x] - Endpoint GET /users/me retorna usuário logado;
- [x] - Endpoints GET /orders/{id} e POST /orders funcionando;
- [x] - Usuário que não é ADMIN não consegue acessar pedido que não é dele em GET /orders/{id};
- [x] - Endpoint GET /categories retorna todas categorias;



