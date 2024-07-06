package br.com.fiap.techchallengeproduct.application.usecases;

import br.com.fiap.techchallengeproduct.application.dto.product.ProductDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductEditFormDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductFormDto;
import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;
import br.com.fiap.techchallengeproduct.domain.enums.TypeStatus;
import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidProductsProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductNotFoundException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductTypeNotFoundException;
import br.com.fiap.techchallengeproduct.domain.interfaces.AbstractProductUserCase;
import br.com.fiap.techchallengeproduct.domain.interfaces.ProductUseCaseInterface;
import br.com.fiap.techchallengeproduct.domain.model.Product;
import br.com.fiap.techchallengeproduct.external.infrastructure.entities.ProductDB;
import br.com.fiap.techchallengeproduct.external.infrastructure.gateway.ProductGatewayImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductUseCaseImpl extends AbstractProductUserCase implements ProductUseCaseInterface {

    private static final Logger logger = LoggerFactory.getLogger(ProductUseCaseImpl.class);
    private final ProductGatewayImpl productGateway;

    @Autowired
    public ProductUseCaseImpl(ProductGatewayImpl productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public ProductDto register(ProductFormDto productFormDto) throws InvalidProductsProcessException {
        validateQuantity(productFormDto.getQuantity());
        validatePrice(productFormDto.getPrice());

        productFormDto.setTypeStatus(TypeStatus.ACTIVE);
        productFormDto.setDateRegister(LocalDateTime.now());

        return productGateway.register(new Product(productFormDto));
    }

    @Override
    public ProductDto edit(ProductEditFormDto productFormEditDto) throws InvalidProductsProcessException {
        validateQuantity(productFormEditDto.getQuantity());
        validatePrice(productFormEditDto.getPrice());

        ProductDB productDB = productGateway.findById(productFormEditDto.getId());

        productDB.updateFrom(productFormEditDto);

        logger.info("[Edit] - Product edit sucessfull.");
        return productGateway.edit(productDB);
    }

    @Override
    public List<ProductDto> findByProductType(String productType) throws InvalidProcessException {
        return Optional.ofNullable(this.productGateway.findByProductType(productType))
                .map(products -> products.stream()
                        .map(ProductDto::toDto)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ProductTypeNotFoundException(TypeProduct.valueOf(productType)));
    }

    @Override
    public List<ProductDto> findAll() {
        return this.productGateway
                .findAll()
                .stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto remove(UUID id) throws InvalidProductsProcessException {
        ProductDB productDB = productGateway.findById(id);

        logger.info("[Remove] - Product remove sucessfull.");
        return productGateway.remove(productDB);
    }

    @Override
    public ProductDto findById(UUID id) throws ProductNotFoundException {
        return ProductDto.toDto(productGateway.findById(id));
    }
}