package br.com.fiap.techchallengeproduct.domain.exception.products;

import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;

public abstract class InvalidProductsProcessException extends InvalidProcessException {

    public InvalidProductsProcessException(String tittle, String message) {
        super(tittle, message);
    }
}
