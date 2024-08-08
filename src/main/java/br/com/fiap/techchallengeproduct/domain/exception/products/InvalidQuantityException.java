package br.com.fiap.techchallengeproduct.domain.exception.products;

public class InvalidQuantityException extends InvalidProductsProcessException {
    private static final String tittle = "Invalid product quantity";
    private static final String message = "The quantity %s not is invalid";

    public InvalidQuantityException(Integer quantity) {
        super(tittle, String.format(message, quantity));
    }
}
