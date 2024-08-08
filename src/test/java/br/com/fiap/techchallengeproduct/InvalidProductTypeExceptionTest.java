package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidProductTypeException;
import br.com.fiap.techchallengeproduct.domain.utils.ProblemAware;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class InvalidProductTypeExceptionTest {

    @Test
    public void valid() {
        // Given
        String type = "AVOCADO";
        InvalidProcessException ex = new InvalidProductTypeException(type);

        // When
        Map<String, String> result = ProblemAware.problemOf(ex);

        // Then
        assertNotNull("Result should not be null", result);
        assertEquals("Product type invalid", result.get("tittle"), "The title was not set correctly.");
        assertEquals("The product type AVOCADO is invalid", result.get("message"), "The message was not set correctly.");
    }
}
