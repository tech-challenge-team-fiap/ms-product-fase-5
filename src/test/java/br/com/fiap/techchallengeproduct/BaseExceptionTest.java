package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.domain.exception.BaseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseExceptionTest {

    @Test
    public void ok() {
        BaseException baseException = new BaseException("ERROR");

        assertEquals("ERROR", baseException.getMessage());
    }
}
