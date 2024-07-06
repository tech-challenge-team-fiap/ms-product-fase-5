package br.com.fiap.techchallengeproduct.domain.exception.products;

import java.util.UUID;

public class ProductNotFoundException extends InvalidProductsProcessException {
    private static final String tittle = "Product not found";
    private static final String message = "The product with id %s not found";

    public ProductNotFoundException(UUID id) {
        super(tittle, String.format(message, id));
    }
}
