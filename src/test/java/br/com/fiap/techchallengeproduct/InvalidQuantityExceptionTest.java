package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidQuantityException;
import br.com.fiap.techchallengeproduct.domain.utils.ProblemAware;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class InvalidQuantityExceptionTest {

    @Test
    public void valid() {
        // Given
        Integer quantity = 123;
        InvalidProcessException ex = new InvalidQuantityException(quantity);

        // When
        Map<String, String> result = ProblemAware.problemOf(ex);

        // Then
        assertNotNull("Result should not be null", result);
        assertEquals("Invalid product quantity", result.get("tittle"), "The title was not set correctly.");
        assertEquals("The quantity 123 not is invalid", result.get("message"), "The message was not set correctly.");
    }
}
