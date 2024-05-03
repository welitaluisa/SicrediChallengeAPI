package br.com.softdesign.api.smoke;

import org.junit.jupiter.api.Test;
import br.com.softdesign.api.base.BaseTest;
import br.com.softdesign.api.factory.ProductDataFactory;
import br.com.softdesign.api.factory.UserDataFactory;
import br.com.softdesign.api.pojo.Product;
import br.com.softdesign.api.pojo.User;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

public class SmokeTest extends BaseTest  {

    @Test
    public void testValidaQueOStatusDaAplicacaoEstaOk() {
        given()
        .when()
            .get("/test")
        .then()
            .assertThat()
                .statusCode(200)
                .body("status", equalTo("ok"))
                .body("method", equalTo("GET"));
    }

    @Test
    public void testValidaBuscaUsuarioRetorna200() throws IOException {
        given()
        .when()
            .get("/users")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testValidaQueACriacaoDoTokenRetorna201() {
        User primeiroUsuarioDaLista = UserDataFactory.buscarUsuarioParaAutentificacao();
        given()
            .header("Content-Type", "application/json")
            .body(primeiroUsuarioDaLista)
        .when()
            .post("/auth/login")
        .then()
            .assertThat()
            .statusCode(201);
    }

    @Test
    public void testValidaBuscaProdutoComAutentificacaoRetorna200() throws IOException {
        given()
            .header("Content-Type", "application/json")
            .header("Authorization", token)
        .when()
            .get("/auth/products")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testValidaAdicaoNovoProdutoRetorna201() throws IOException {

        Product productValido = ProductDataFactory.addNewProduct();
        given()
            .header("Content-Type", "application/json")
            .body(productValido)
            .header("Authorization", token)
        .when()
            .post("/products/add")
        .then()
            .assertThat()
            .statusCode(201);
    }

    @Test
    public void testValidaBuscaTodosOsProdutoSemAutentificacaoRetorna200() throws IOException {
        given()
        .when()
            .get("/products")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testValidaBuscaPorUnicoProdutoRetorna200() throws IOException {
        int productIdExistente = 1;
        given()
        .when()
        .get("/products/"+ productIdExistente)
        .then()
            .assertThat()
            .statusCode(200);
    }
}
