package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.application.dto.product.ProductDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductDtoTest {
    @Test
    public void noArgsConstructor() {
        ProductDto product = new ProductDto();

        assertNull(product.getName());
    }

    @Test
    public void setter() {
        ProductDto product = new ProductDto();

        product.setName("TEST");

        assertEquals("TEST", product.getName());
    }
}
