# :construction: - Desafio: Projeto Spring Boot estruturado

- Projeto DSCommerce estruturado e com todas as funcionalidades implementadas;
- Projeto implementado com Java e Spring Boot, usando banco de dados H2;

##

## :desktop_computer: - Layout:

![image](https://github.com/carloshenriquefs/dscommerce-java/assets/54969405/cb6bada3-b6f3-4950-a27c-fdfe808824d7)
![image](https://github.com/carloshenriquefs/dscommerce-java/assets/54969405/3a4cd519-b95f-40f4-b751-720b891fac38)

##

## :clipboard: - Diagrama:

![dscommerce drawio](https://github.com/carloshenriquefs/dscommerce-java/assets/54969405/bec2b868-5997-4668-957a-8d621f48c715)

##

## :gear: - Endpoints:

* :label: - Category:

    ```GET``` - findAll <br /> 

##

* :bellhop_bell: - Order:

    ```GET``` - /{id} - findById <br />
    ```POST``` - insert <br />

##

* :package: - Product:

    ```GET``` - /{id} - findById <br />
    ```GET``` - findAll <br /> 
    ```POST``` - insert <br />
    ```PUT``` - /{id} - update <br />
    ```DELETE``` - /{id} - delete <br />

##

* :bust_in_silhouette: - User: 

    ```GET``` - /me - getME

##

### :white_check_mark: - Critérios:

##

- [x] - Endpoints públicos GET /produts e GET /products/{id} funcionam sem necessidade de login;
- [x] - Endpoint de login funcionando e retornando o token de acesso;
- [x] - Endpoints privados de produto (POST/PUT/DELETE) funcionam somente para usuário ADMIN;
- [x] - Endpoint GET /users/me retorna usuário logado;
- [x] - Endpoints GET /orders/{id} e POST /orders funcionando;
- [x] - Usuário que não é ADMIN não consegue acessar pedido que não é dele em GET /orders/{id};
- [x] - Endpoint GET /categories retorna todas categorias;



