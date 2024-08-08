package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.adapter.controllers.ProductController;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductEditFormDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductFormDto;
import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;
import br.com.fiap.techchallengeproduct.domain.enums.TypeStatus;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidQuantityException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductNotFoundException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductTypeNotFoundException;
import br.com.fiap.techchallengeproduct.domain.interfaces.ProductUseCaseInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @Mock
    private ProductUseCaseInterface productUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ProductController controller() {
        return new ProductController(productUseCase);
    }

    @Test
    public void registerOk() throws Exception {
        ProductController controller = controller();

        ProductFormDto productFormDto = new ProductFormDto("valid_product_1", "description_product_1", 10,
                TypeProduct.DESSERT, new BigDecimal(3),TypeStatus.ACTIVE, LocalDateTime.now());

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        when(productUseCase.register(any())).thenReturn(productDto);

        ResponseEntity<?> response = controller.register(productFormDto);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void registerInvalid() throws Exception {
        ProductController controller = controller();

        when(productUseCase.register(any())).thenThrow(new InvalidQuantityException(-5));

        ProductFormDto productFormDto = new ProductFormDto("valid_product_1", "description_product_1", 10,
                TypeProduct.DESSERT, new BigDecimal(3),TypeStatus.ACTIVE, LocalDateTime.now());

        ResponseEntity<?> response = controller.register(productFormDto);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(response.getBody());
    }

    @Test
    public void editOk() throws Exception {
        ProductController controller = controller();

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        when(productUseCase.edit(any())).thenReturn(productDto);

        ProductEditFormDto productEditFormDto = new ProductEditFormDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, new Date());

        ResponseEntity<?> response = controller.edit(productEditFormDto);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void editInvalid() throws Exception {
        ProductController controller = controller();

        when(productUseCase.edit(any())).thenThrow(new InvalidQuantityException(-5));

        ProductEditFormDto productEditFormDto = new ProductEditFormDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, new Date());

        ResponseEntity<?> response = controller.edit(productEditFormDto);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(response.getBody());
    }

    @Test
    public void removeOk() throws Exception {
        ProductController controller = controller();

        ProductDto productDto = new ProductDto(UUID.fromString("a97a34c9-cee8-4785-8bb0-8552f5048105"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        when(productUseCase.remove(any())).thenReturn(productDto);

        ResponseEntity<?> response = controller.remove(UUID.fromString("a97a34c9-cee8-4785-8bb0-8552f5048105"));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void removeInvalid() throws Exception {
        ProductController controller = controller();

        when(productUseCase.remove(any())).thenThrow(new ProductNotFoundException(UUID.fromString("38713cd4-4bad-4eb8-a4e6-08fd533fbfe5")));

        ResponseEntity<?> response = controller.remove(UUID.fromString("38713cd4-4bad-4eb8-a4e6-08fd533fbfe5"));

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(response.getBody());
    }

    @Test
    public void findByProductTypeOk() throws Exception {
        ProductController controller = controller();

        ProductDto product1 = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        ProductDto product2 = new ProductDto(UUID.fromString("5b2d4463-a365-459b-98a9-d5e238b1d457"),
                "valid_product_2", "description_product_2",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        when(productUseCase.findByProductType(any())).thenReturn(List.of(product1, product2));

        ResponseEntity<?> response = controller.findByProductType("DRINK");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void findByProductTypeInvalid() throws Exception {
        ProductController controller = controller();

        when(productUseCase.findByProductType(any())).thenThrow(new ProductTypeNotFoundException(TypeProduct.SNACK_ACCOMPANIMENT));

        ResponseEntity<?> response = controller.findByProductType("DRINK");

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(response.getBody());
    }

    @Test
    public void findAllOk() throws Exception {
        ProductController controller = controller();

        ProductDto product1 = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        ProductDto product2 = new ProductDto(UUID.fromString("5b2d4463-a365-459b-98a9-d5e238b1d457"),
                "valid_product_2", "description_product_2",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        when(productUseCase.findAll()).thenReturn(List.of(product1, product2));

        ResponseEntity<?> response = controller.findAll();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void findByIdOk() throws Exception {
        ProductController controller = controller();

        ProductDto product1 = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        when(productUseCase.findById(any())).thenReturn(product1);

        ResponseEntity<?> response = controller.findById(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void findByIdInvalid() throws Exception {
        ProductController controller = controller();

        when(productUseCase.findById(any())).thenThrow(new ProductNotFoundException(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d")));

        ResponseEntity<?> response = controller.findById(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"));

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(response.getBody());
    }
}
