package br.com.fiap.techchallengeproduct.domain.utils;

import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;

import java.util.HashMap;
import java.util.Map;

public class ProblemAware {

    public static Map<String, String> problemOf(InvalidProcessException ex) {
        Map<String, String> details = new HashMap<>();

        details.put("tittle", ex.getTittle());
        details.put("message", ex.getMessage());

        return details;
    }

}
