package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.adapter.controllers.ProductController;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductEditFormDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductFormDto;
import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;
import br.com.fiap.techchallengeproduct.domain.enums.TypeStatus;
import br.com.fiap.techchallengeproduct.external.api.ProductApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductApiTest {

    @Mock
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ProductApi api() {
        return new ProductApi(productController);
    }

    @Test
    public void register() {
        ProductApi api = api();

        ProductFormDto productFormDto = new ProductFormDto("valid_product_1", "description_product_1", 10,
                TypeProduct.DESSERT, new BigDecimal(3), TypeStatus.ACTIVE, LocalDateTime.now());

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        ResponseEntity responseEntity = ResponseEntity.ok(productDto);

        when(productController.register(any())).thenReturn(responseEntity);

        ResponseEntity<?> response = api.register(productFormDto);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void edit() {
        ProductApi api = api();

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        ResponseEntity responseEntity = ResponseEntity.ok(productDto);

        when(productController.edit(any())).thenReturn(responseEntity);

        ProductEditFormDto productEditFormDto = new ProductEditFormDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, new Date());

        ResponseEntity<?> response = api.edit(productEditFormDto);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void remove() {
        ProductApi api = api();

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        ResponseEntity responseEntity = ResponseEntity.ok(productDto);

        when(productController.remove(any())).thenReturn(responseEntity);

        ResponseEntity<?> response = api.remove(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void findByProductType() {
        ProductApi api = api();

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        ResponseEntity responseEntity = ResponseEntity.ok(productDto);

        when(productController.findByProductType(any())).thenReturn(responseEntity);

        ResponseEntity<?> response = api.findByProductType("XPTO");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void findById() {
        ProductApi api = api();

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        ResponseEntity responseEntity = ResponseEntity.ok(productDto);

        when(productController.findById(any())).thenReturn(responseEntity);

        ResponseEntity<?> response = api.findById(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void findAll() {
        ProductApi api = api();

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        ResponseEntity responseEntity = ResponseEntity.ok(productDto);

        when(productController.findAll()).thenReturn(responseEntity);

        ResponseEntity<?> response = api.findAll();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }
}
