package br.com.fiap.techchallengeproduct.domain.model;

import br.com.fiap.techchallengeproduct.application.dto.product.ProductFormDto;
import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;
import br.com.fiap.techchallengeproduct.domain.enums.TypeStatus;
import br.com.fiap.techchallengeproduct.external.infrastructure.entities.ProductDB;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Product {

    private String name;

    private String description;

    private Integer quantity;

    private TypeProduct typeProduct;

    private String category;

    private BigDecimal price;

    private TypeStatus typeStatus;

    private LocalDateTime dateRegister;

    public Product() {}

    public Product(ProductFormDto productFormDto) {
        this.name = productFormDto.getName();
        this.description = productFormDto.getDescription();
        this.quantity = productFormDto.getQuantity();
        this.typeProduct = productFormDto.getTypeProduct();
        this.price = productFormDto.getPrice();
        this.typeStatus = productFormDto.getTypeStatus();
        this.dateRegister = productFormDto.getDateRegister();
    }

    public ProductDB build() {
        return ProductDB.builder()
                                  .name(getName())
                                  .description(getDescription())
                                  .quantity(getQuantity())
                                  .typeProduct(getTypeProduct())
                                  .price(getPrice())
                                  .typeStatus(getTypeStatus())
                                  .dateRegister(getDateRegister())
                                  .build();
    }
}
