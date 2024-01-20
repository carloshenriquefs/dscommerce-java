package com.devsuperior.dscommerce.tests;

import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;

public class ProductFactory {

    public static Product createProduct() {
        Category category = CategoryFactory.createCategory();
        Product product = new Product(1L, "PC Gamer", "Gabinete para jogos completo", 1200.00, "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/4-big.jpg");
        product.getCategories().add(category);
        return product;
    }

    public static Product createProduct(Long id, String name, String description, Double price, String imgUrl) {
        return new Product(id, name, description, price, imgUrl);
    }

    public static Product createProduct(String name) {
        Product product = createProduct();
        product.setName(name);
        return product;
    }
}
