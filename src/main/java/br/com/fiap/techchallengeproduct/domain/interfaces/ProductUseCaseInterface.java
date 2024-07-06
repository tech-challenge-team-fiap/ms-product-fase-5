package br.com.fiap.techchallengeproduct.domain.interfaces;

import br.com.fiap.techchallengeproduct.application.dto.product.ProductDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductEditFormDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductFormDto;
import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidProductsProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductUseCaseInterface {

    ProductDto edit(final ProductEditFormDto productFormEditDto) throws InvalidProductsProcessException;

    List<ProductDto> findByProductType(String productType) throws InvalidProcessException;

    List<ProductDto> findAll();

    ProductDto register(final ProductFormDto productFormDto) throws InvalidProductsProcessException;

    ProductDto remove(final UUID id) throws InvalidProductsProcessException;

    ProductDto findById(UUID id) throws ProductNotFoundException;
}
