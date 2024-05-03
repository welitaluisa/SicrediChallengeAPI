package br.com.softdesign.api.base;

import br.com.softdesign.api.pojo.User;
import org.junit.jupiter.api.BeforeAll;
import br.com.softdesign.api.factory.UserDataFactory;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BaseTest {

    public static String token;

    @BeforeAll
    public static void setUp() {

        baseURI = "https://dummyjson.com/";
        if (token == null) {
            token = gerarToken();
         }
    }
    public static String gerarToken() {
        User primeiroUsuarioDaLista = UserDataFactory.buscarUsuarioParaAutentificacao();
        String token = given()
                .header("Content-Type", "application/json")
                .body(primeiroUsuarioDaLista)
        .when()
            .post("/auth/login")
        .then()
            .assertThat()
            .extract()
            .path("token");
        return token;
    }
}
