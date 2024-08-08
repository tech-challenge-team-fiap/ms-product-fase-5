package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;
import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductNotFoundException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductTypeNotFoundException;
import br.com.fiap.techchallengeproduct.domain.utils.ProblemAware;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class ProductTypeNotFoundExceptionTest {

    @Test
    public void valid() {
        // Given
        TypeProduct typeProduct = TypeProduct.SNACK;
        InvalidProcessException ex = new ProductTypeNotFoundException(typeProduct);

        // When
        Map<String, String> result = ProblemAware.problemOf(ex);

        // Then
        assertNotNull("Result should not be null", result);
        assertEquals("Product type not found", result.get("tittle"), "The title was not set correctly.");
        assertEquals("The products with product type SNACK not found", result.get("message"), "The message was not set correctly.");
    }
}
