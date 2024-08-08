package br.com.fiap.techchallengeproduct.external.api;

import br.com.fiap.techchallengeproduct.adapter.controllers.ProductController;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductEditFormDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductFormDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductApi {

    private final ProductController productController;

    @Autowired
    public ProductApi(ProductController productController){
        this.productController = productController;
    }

    @GetMapping("/check")
    public ResponseEntity<?> healthCheckApi(){
        return ResponseEntity.ok("Api Product UP");
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> register(@RequestBody ProductFormDto productFormDto) {
        return productController.register(productFormDto);
    }

    @PutMapping()
    public ResponseEntity<?> edit(@RequestBody ProductEditFormDto product) {
        return productController.edit(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable UUID id) {
        return productController.remove(id);
    }

    @GetMapping("/{productType}")
    @Transactional
    public ResponseEntity<?> findByProductType(@PathVariable("productType") String productType) {
        return productController.findByProductType(productType);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> findById(@PathVariable("id") UUID id) {
        return productController.findById(id);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> findAll() {
        return productController.findAll();
    }
}
