package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductNotFoundException;
import br.com.fiap.techchallengeproduct.domain.utils.ProblemAware;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class ProductNotFoundExceptionTest {

    @Test
    public void valid() {
        // Given
        UUID id = UUID.fromString("7b06a7c8-5fa7-46ad-94d4-3dc05d5f8996");
        InvalidProcessException ex = new ProductNotFoundException(id);

        // When
        Map<String, String> result = ProblemAware.problemOf(ex);

        // Then
        assertNotNull("Result should not be null", result);
        assertEquals("Product not found", result.get("tittle"), "The title was not set correctly.");
        assertEquals("The product with id 7b06a7c8-5fa7-46ad-94d4-3dc05d5f8996 not found", result.get("message"), "The message was not set correctly.");
    }
}
