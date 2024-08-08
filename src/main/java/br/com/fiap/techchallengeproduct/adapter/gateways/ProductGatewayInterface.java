package br.com.fiap.techchallengeproduct.adapter.gateways;

import br.com.fiap.techchallengeproduct.application.dto.product.ProductDto;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidProductsProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductNotFoundException;
import br.com.fiap.techchallengeproduct.domain.model.Product;
import br.com.fiap.techchallengeproduct.external.infrastructure.entities.ProductDB;

import java.util.List;
import java.util.UUID;

public interface ProductGatewayInterface {

    ProductDto register(Product product) throws Exception;

    List<ProductDB> findByProductType(String type) throws InvalidProductsProcessException;

    ProductDto edit(ProductDB product);

    ProductDto remove(ProductDB product);

    List<ProductDB> findAll();

    ProductDB findById(UUID id) throws ProductNotFoundException;
}