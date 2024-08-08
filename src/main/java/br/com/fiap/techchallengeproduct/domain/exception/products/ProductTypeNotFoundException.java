package br.com.fiap.techchallengeproduct.domain.exception.products;

import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;

public class ProductTypeNotFoundException extends InvalidProductsProcessException{
    private static final String tittle = "Product type not found";
    private static final String message = "The products with product type %s not found";

    public ProductTypeNotFoundException(TypeProduct type) {
        super(tittle, String.format(message, type.name()));
    }
}
