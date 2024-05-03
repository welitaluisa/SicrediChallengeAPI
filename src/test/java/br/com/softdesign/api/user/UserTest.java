package br.com.softdesign.api.user;

import br.com.softdesign.api.base.BaseTest;
import br.com.softdesign.api.pojo.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import br.com.softdesign.api.factory.UserDataFactory;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest extends BaseTest {

    @Test
    @DisplayName("Teste: Busca a lista de usuário e valida as informações retornada do usuario com id = 1")
    public void testBuscaUsuarioEValidaInformacoesDoPrimeiroUsuarioDaLista() {

        given()
        .when()
            .get("/users")
        .then()
            .assertThat()
            .statusCode(200)
            .body("users[0].id", equalTo(1))
            .body("users[0].lastName", equalTo("Medhurst"))
            .body("users[0].maidenName", equalTo("Smitham"))
            .body("users[0].age", equalTo(50))
            .body("users[0].gender", equalTo("male"))
            .body("users[0].email", equalTo("atuny0@sohu.com"))
            .body("users[0].phone", equalTo("+63 791 675 8914"))
            .body("users[0].username", equalTo("atuny0"))
            .body("users[0].password", equalTo("9uQFF1Lh"))
            .body("users[0].birthDate", equalTo("2000-12-25"))
            .body("users[0].image", containsString("https://robohash.org"))
            .body("users[0].height", equalTo(189))
            .body("users[0].weight", equalTo(75.4F))
            .body("users[0].eyeColor", equalTo("Green"))
            .body("users[0].hair.color", equalTo("Black"))
            .body("users[0].hair.type", equalTo("Strands"))
            .body("users[0].password", equalTo("9uQFF1Lh"))
            .body("users[0].domain", containsString("slashdot.org"));
    }

    @Test
    @DisplayName("Teste:Valida a criação do token e o response da requisição usando credenciais validas")
    public void testValidaCriacaoTokenUsandoCredenciasValidas() {

        User primeiroUsuarioDaLista = UserDataFactory.buscarUsuarioParaAutentificacao();

        given()
            .header("Content-Type", "application/json")
            .body(primeiroUsuarioDaLista)
        .when()
        .post("/auth/login")
        .then()
            .assertThat()
            .statusCode(201)
            .body("id", equalTo(1))
            .body("username", equalTo("atuny0"))
            .body("email", equalTo("atuny0@sohu.com"))
            .body("firstName", equalTo("Terry"))
            .body("lastName", equalTo("Medhurst"))
            .body("gender", equalTo("male"))
            .body("image", equalTo("https://robohash.org/Terry.png?set=set4"))
            .body("token", notNullValue());
    }

    @Test
    @DisplayName("Teste:Valida que não é permitido passar username vazio")
     public void testNaoEPermitidoGerarTokenQuandoUsernameEstaVazio() {

        User usuarioVazio = UserDataFactory.buscarUsuarioPassandoUsernameVazio();

        given()
            .header("Content-Type", "application/json")
            .body(usuarioVazio)
        .when()
            .post("/auth/login")
        .then()
            .assertThat()
            .statusCode(401)
            .body("message",equalTo("Invalid credentials"));
     }

    @Test
    @DisplayName("Teste:Valida que não é permitido passar Password vazio")
    public void testNaoEPermitidoGerarTokenQuandoPasswordEstaVazio() {

        User senhaVazio = UserDataFactory.buscarUsuarioPassandoPasswordVazio();

        given()
            .header("Content-Type", "application/json")
            .body(senhaVazio)
        .when()
            .post("/auth/login")
        .then()
            .assertThat()
            .statusCode(401)
            .body("message",equalTo("Invalid credentials"));
    }

    @Test
    @DisplayName("Teste:Valida que não é permitido usar senha de outro usuário")
    public void testUsandoCredenciaisInvalida() {

        User senhaDeUsuarioDiferente = UserDataFactory.senhaUsuarioUsandoCredenciaisDeOutros();

        given()
            .header("Content-Type", "application/json")
            .body(senhaDeUsuarioDiferente)
        .when()
            .post("/auth/login")
        .then()
            .assertThat()
            .statusCode(401)
            .body("message",equalTo("Invalid credentials"));
    }
}
