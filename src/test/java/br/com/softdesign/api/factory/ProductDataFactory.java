package br.com.softdesign.api.factory;

import br.com.softdesign.api.pojo.Product;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class ProductDataFactory {
    public static Product addNewProduct() throws IOException {
        Gson gson = new Gson();
        Product product = gson.fromJson(new FileReader("C:\\Projects\\SoftDesign\\src\\test\\resources\\requestBody\\postProduct.json"), Product.class);
        return product;
    }

    public static Product addNewProductValido() throws IOException {
        Product productValido = addNewProduct();
        return productValido;
    }

    public static Product addNewProductSemTitulo() throws IOException {
        Product productSemTitulo = addNewProduct();
        productSemTitulo.setTitle("");
        return productSemTitulo;
    }

    public static Product addNewProductPriceNegativo() throws IOException {
        Product productPriceNegativo = addNewProduct();
        productPriceNegativo.setPrice(-100);
        return productPriceNegativo;
    }

    public static Product addNewProductStockZerado() throws IOException {
        Product productStockZerado = addNewProduct();
        productStockZerado.setPrice(0);
        return productStockZerado;
    }
}
