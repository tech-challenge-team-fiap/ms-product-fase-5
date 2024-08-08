package br.com.fiap.techchallengeproduct.domain.exception.products;

import java.math.BigDecimal;

public class InvalidPriceException extends InvalidProductsProcessException {
    private static final String tittle = "Invalid product price";
    private static final String message = "The price %s not is invalid";

    public InvalidPriceException(BigDecimal price) {
        super(tittle, String.format(message, price));
    }
}
