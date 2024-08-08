package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.application.dto.product.ProductEditFormDto;
import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;
import br.com.fiap.techchallengeproduct.domain.enums.TypeStatus;
import br.com.fiap.techchallengeproduct.external.infrastructure.entities.ProductDB;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDBTest {

    @Test
    public void noArgsConstructor() {
        ProductDB product = new ProductDB();

        assertNull(product.getName());
    }

    @Test
    public void allArgsConstructor() {
        ProductDB product = new ProductDB(UUID.randomUUID(), "name", "description", 1, TypeProduct.DRINK, new BigDecimal(1), TypeStatus.ACTIVE, LocalDateTime.now());

        assertNotNull(product.getId());
        assertEquals("name", product.getName());
        assertEquals("description", product.getDescription());
        assertEquals(1, product.getQuantity());
        assertEquals(TypeProduct.DRINK, product.getTypeProduct());
        assertEquals(new BigDecimal(1), product.getPrice());
        assertEquals(TypeStatus.ACTIVE, product.getTypeStatus());
        assertNotNull(product.getDateRegister());
    }

    @Test
    public void testProductDBGetterMethods() {
        ProductDB product = getProductDB();

        assertNotNull(product.getId());
        assertEquals("product_test_1", product.getName());
        assertEquals("description_test_1", product.getDescription());
        assertEquals(123, product.getQuantity());
        assertEquals(TypeProduct.DRINK, product.getTypeProduct());
        assertEquals(new BigDecimal(2), product.getPrice());
        assertEquals(TypeStatus.ACTIVE, product.getTypeStatus());
        assertNotNull(product.getDateRegister());
    }

    @Test
    public void updateFrom() {
        ProductDB product = getProductDB();

        ProductEditFormDto formDto = new ProductEditFormDto(UUID.randomUUID(), "BANANA", "BANANA_DESCRIPTION", 5, TypeProduct.DESSERT, new BigDecimal(50), TypeStatus.ACTIVE, new Date());

        product.updateFrom(formDto);

        assertNotNull(product.getId());
        assertEquals("BANANA", product.getName());
        assertEquals("BANANA_DESCRIPTION", product.getDescription());
        assertEquals(5, product.getQuantity());
        assertEquals(TypeProduct.DESSERT, product.getTypeProduct());
        assertEquals(new BigDecimal(50), product.getPrice());
        assertEquals(TypeStatus.ACTIVE, product.getTypeStatus());
        assertNotNull(product.getDateRegister());
    }

    private  ProductDB getProductDB() {
        return ProductDB.builder()
                        .name("product_test_1")
                        .description("description_test_1")
                        .quantity(123)
                        .typeProduct(TypeProduct.DRINK)
                        .price(new BigDecimal(2))
                        .typeStatus(TypeStatus.ACTIVE)
                        .dateRegister(LocalDateTime.now())
                        .build();
    }
}
