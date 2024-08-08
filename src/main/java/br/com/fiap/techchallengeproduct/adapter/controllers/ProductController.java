package br.com.fiap.techchallengeproduct.adapter.controllers;

import br.com.fiap.techchallengeproduct.application.dto.product.ProductEditFormDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductFormDto;
import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductNotFoundException;
import br.com.fiap.techchallengeproduct.domain.interfaces.ProductUseCaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import static br.com.fiap.techchallengeproduct.domain.utils.ProblemAware.problemOf;

@Controller
public class ProductController {

    private final ProductUseCaseInterface productUseCase;
    @Autowired
    public ProductController(ProductUseCaseInterface productUseCase) {
        this.productUseCase = productUseCase;
    }

    public ResponseEntity<?> register(@RequestBody ProductFormDto productFormDto) {
        try {
            return ResponseEntity.ok(productUseCase.register(productFormDto));
        } catch (InvalidProcessException ex) {
            return ResponseEntity.badRequest().body(problemOf(ex));
        }
    }

    public ResponseEntity<?> edit(@RequestBody ProductEditFormDto product) {
        try {
            return ResponseEntity.ok(productUseCase.edit(product));
        } catch (InvalidProcessException ex) {
            return ResponseEntity.badRequest().body(problemOf(ex));
        }
    }

    public ResponseEntity<?> remove(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(productUseCase.remove(id));
        } catch (InvalidProcessException ex) {
            return ResponseEntity.badRequest().body(problemOf(ex));
        }
    }
    public ResponseEntity<?> findByProductType(@PathVariable("productType") String productType) {
        try {
            return ResponseEntity.ok(productUseCase.findByProductType(productType));
        } catch (InvalidProcessException ex) {
            return ResponseEntity.badRequest().body(problemOf(ex));
        }
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productUseCase.findAll());
    }

    public ResponseEntity<?> findById(UUID id) {
        try {
            return ResponseEntity.ok(productUseCase.findById(id));
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.badRequest().body(problemOf(ex));
        }
    }
}