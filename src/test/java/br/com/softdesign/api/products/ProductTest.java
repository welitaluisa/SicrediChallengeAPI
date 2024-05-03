package br.com.softdesign.api.products;

import br.com.softdesign.api.base.BaseTest;
import br.com.softdesign.api.pojo.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import br.com.softdesign.api.factory.ProductDataFactory;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductTest extends BaseTest {

    @Test
    @DisplayName("Teste: Buscar produto com autenticação e validar alguns  parâmetros retornados do primeiro produto da lista")
    public void testBuscarProdutoComAutentificacaoEValidaAlgunsParametrosRetornadosDoPrimeiroProdutoDaLista() {

        given()
            .header("Content-Type", "application/json")
            .header("Authorization", token)
        .when()
            .get("/auth/products")
        .then()
            .assertThat()
            .statusCode(200)
            .body("products[0].id", equalTo(1))
            .body("products[0].title", equalTo("iPhone 9"))
            .body("products[0].description", equalTo("An apple mobile which is nothing like apple"))
            .body("products[0].price", equalTo(549))
            .body("products[0].discountPercentage", equalTo(12.96F))
            .body("products[0].rating", equalTo(4.69F))
            .body("products[0].stock", equalTo(94))
            .body("products[0].brand", equalTo("Apple"))
            .body("products[0].category", equalToIgnoringCase("smartphones"))
            .body("products[0].thumbnail", containsString("thumbnail.jpg"))
            .body("products[0].images[3]", equalTo("https://cdn.dummyjson.com/product-images/1/4.jpg"))
            .body("total", equalTo(100))
            .body("skip", equalTo(0))
            .body("limit", equalTo(30));
    }

    @Test
    @DisplayName("Teste: Valida que a busca de produto sem autentificar retorna 403 Forbidden")
    public void testBuscaProdutoPassandoTokenVazio() throws IOException {

        given()
            .header("Content-Type", "application/json")
            .header("Authorization", "")
        .when()
            .get("/auth/products")
        .then()
            .assertThat()
            .statusCode(403)
            .body("message", equalTo("Authentication Problem"));
        }

    @Test
    @DisplayName("Teste: Valida que a busca de produto usando token expirado retorna 401 Unauthorized")
    public void testBuscaProdutoUsandoUmTokenInvalido() throws IOException {
        String tokenExpirado = "\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MiwidXNlcm5hbWUiOiJoYmluZ2xleTEiLCJlbWFpbCI6ImhiaW5nbGV5MUBwbGFsYS5vci5qcCIsImZpcnN0TmFtZSI6IlNoZWxkb24iLCJsYXN0TmFtZSI6IlF1aWdsZXkiLCJnZW5kZXIiOiJtYWxlIiwiaW1hZ2UiOiJodHRwczovL3JvYm9oYXNoLm9yZy9TaGVsZG9uLnBuZz9zZXQ9c2V0NCIsImlhdCI6MTcwOTMyMjg4NCwiZXhwIjoxNzA5MzI2NDg0fQ.Rg4tFKigq_Z-bw-qRkrcV8E_DoUGY-1XbygTbAc55Xk\"";
        given()
            .header("Content-Type", "application/json")
            .header("Authorization", tokenExpirado)
        .when()
            .get("/auth/products")
        .then()
            .assertThat()
            .statusCode(401);
        }

    @Test
    @DisplayName("Teste: Adiciona um novo produto usando Data Factory e valida o retorna da resposta ")
    public void testAddNewProdutoComSucessoEValidaORetornoDaResposta() throws IOException {

        Product productValido = ProductDataFactory.addNewProductValido();

        given()
            .header("Content-Type", "application/json")
            .body(productValido)
            .header("Authorization", token)
        .when()
            .post("/products/add")
        .then()
            .assertThat()
            .statusCode(201)
            .body("id", equalTo(101))
            .body("title", equalTo("Smartphone Galaxy S22"))
            .body("price", equalTo(999))
            .body("stock", equalTo(45))
            .body("rating", equalTo(4.8F))
            .body("thumbnail", equalTo("https://example.com/samsung_s22.jpg"))
            .body("description", equalTo("Ultimate performance and camera"))
            .body("brand", equalTo("Samsung"))
            .body("category", equalToIgnoringCase("Electronics"));
        }

    @Test
    @DisplayName("Teste: Valida a hipotese que não é permitido adicionar um novo produto passando o title vazio")
    public void testAddNewProdutoSemtitle() throws IOException {

        Product productSemTitulo = ProductDataFactory.addNewProductSemTitulo();

        given()
            .header("Content-Type", "application/json")
            .body(productSemTitulo)
            .header("Authorization", token)
        .when()
            .post("/products/add")
        .then()
            .assertThat();
        //.statusCode(400); // Supondo uma regra onde o titulo é obrigatório o correto era retornar 400 , Aqui como não conheço a regra do negocio, deixei o teste comentado, apenas para confirmar se o testes faz sentido ou não
    }

    @Test
    @DisplayName("Teste: Valida a hipotese que não é permitido adicionar um novo produto com o price negativo")

    public void testAddNewProdutoPriceNegativo() throws IOException {

        Product productPriceNegativo = ProductDataFactory.addNewProductPriceNegativo();

        given()
            .header("Content-Type", "application/json")
            .body(productPriceNegativo)
            .header("Authorization", token)
        .when()
            .post("/products/add")
        .then()
            .assertThat();
            //.statusCode(400); // Supondo uma regra onde Não pode add produto com preço negativo, Por isto o codigo esta comentado, porque precisaria confirma se a regra é aplicavél
    }

    @Test
    @DisplayName("Teste: Valida a hipotese que não é permitido adicionar um novo produto passando o estoque como vazio")
    public void testAddNewProdutoproductStockZerado() throws IOException {

        Product productStockZerado = ProductDataFactory.addNewProductStockZerado();

        given()
            .header("Content-Type", "application/json")
            .body(productStockZerado)
            .header("Authorization", token)
        .when()
            .post("/products/add")
        .then()
            .assertThat();
        // .statusCode(400); // Supondo uma regra onde Não é permitido add um novo produto onde o stock seja Zero (0). Código com comentario apenas para verificar se a regra é valida
    }

    @Test
    @DisplayName("Teste: Valida que na busca de todos os produtos contém o produto com id = 30")
    public void testBuscarTodosOsProdutosEValidaRetornoIdEscifico() {

        given()
        .when()
            .get("/products")
        .then()
            .assertThat()
            .statusCode(200)
            .body("products[29].id", equalTo(30));
    }

    @Test
    @DisplayName("Teste: Busca o produto com id = 29 e valida o price e title")
    public void testBuscarApenasProdutoPassandoIdEspecifico() {

        int productIdExistente = 29;

        given()
        .when()
            .get("/products/"+ productIdExistente)
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", equalTo(29))
            .body("title", equalTo("Handcraft Chinese style"))
            .body("price", equalTo(60));
    }

    @Test
    @DisplayName("Teste: Valida que ao buscar um produto inexistente retorna 404")
    public void testBucarProdutoInexistente() {

        String productId = "100000";

        given()
        .when()
            .get("/products/" + productId)
        .then()
            .assertThat()
            .statusCode(404)
            .body("message", containsString("Product with id '" + productId + "' not found"));
    }
}
