package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidPriceException;
import br.com.fiap.techchallengeproduct.domain.utils.ProblemAware;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class InvalidPriceExceptionTest {

    @Test
    public void valid() {
        // Given
        BigDecimal price = new BigDecimal(1);
        InvalidProcessException ex = new InvalidPriceException(price);

        // When
        Map<String, String> result = ProblemAware.problemOf(ex);

        // Then
        assertNotNull("Result should not be null", result);
        assertEquals("Invalid product price", result.get("tittle"), "The title was not set correctly.");
        assertEquals("The price 1 not is invalid", result.get("message"), "The message was not set correctly.");
    }
}
