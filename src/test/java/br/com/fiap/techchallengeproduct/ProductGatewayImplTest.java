package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.application.dto.product.ProductDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductFormDto;
import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;
import br.com.fiap.techchallengeproduct.domain.enums.TypeStatus;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidProductTypeException;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidProductsProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.ProductNotFoundException;
import br.com.fiap.techchallengeproduct.domain.model.Product;
import br.com.fiap.techchallengeproduct.external.infrastructure.entities.ProductDB;
import br.com.fiap.techchallengeproduct.external.infrastructure.gateway.ProductGatewayImpl;
import br.com.fiap.techchallengeproduct.external.infrastructure.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductGatewayImplTest {

    @Mock
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ProductGatewayImpl gateway() {
        return new ProductGatewayImpl(repository);
    }

    private ProductDB getProductDB() {
        return ProductDB.builder()
                .name("product_test_1")
                .description("description_test_1")
                .quantity(123)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(2))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();
    }

    private Product getProduct() {
        ProductFormDto formDto = new ProductFormDto("BANANA", "BANANA_DESCRIPTION", 5, TypeProduct.DESSERT, new BigDecimal(50), TypeStatus.ACTIVE, LocalDateTime.now());

        return new Product(formDto);
    }

    @Test
    public void registerOk() {
        ProductGatewayImpl gateway = gateway();

        when(repository.save(any())).thenReturn(getProductDB());

        ProductDto response = gateway.register(getProduct());

        assertNotNull(response.getId());
    }

    @Test
    public void registerNonOk() {
        ProductGatewayImpl gateway = gateway();

        when(repository.save(any())).thenThrow(new RuntimeException());

        try {
            gateway.register(getProduct());
            fail("Register product error");
        } catch (RuntimeException ex) {
            assertNotNull(ex);
        }
    }


    @Test
    public void validFindByProductType() throws InvalidProductsProcessException {
        ProductGatewayImpl gateway = gateway();

        ProductDB product1 = ProductDB.builder()
                .name("product_test_1")
                .description("description_test_1")
                .quantity(123)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(2))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        ProductDB product2 = ProductDB.builder()
                .name("product_test_2")
                .description("description_test_2")
                .quantity(321)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(1))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        when(repository.findByTypeProductAndTypeStatus(any(), any())).thenReturn(List.of(product1, product2));

        List<ProductDB> products = gateway.findByProductType("DRINK");

        ProductDB response1 = products.get(0);
        assertNotNull(response1.getId());
        assertEquals("product_test_1", response1.getName());
        assertEquals("description_test_1", response1.getDescription());
        assertEquals(123, response1.getQuantity());
        assertEquals(TypeProduct.DRINK, response1.getTypeProduct());
        assertEquals(new BigDecimal(2), response1.getPrice());
        assertEquals(TypeStatus.ACTIVE, response1.getTypeStatus());
        assertNotNull(response1.getDateRegister());

        ProductDB response2 = products.get(1);
        assertNotNull(response2.getId());
        assertEquals("product_test_2", response2.getName());
        assertEquals("description_test_2", response2.getDescription());
        assertEquals(321, response2.getQuantity());
        assertEquals(TypeProduct.DRINK, response2.getTypeProduct());
        assertEquals(new BigDecimal(1), response2.getPrice());
        assertEquals(TypeStatus.ACTIVE, response2.getTypeStatus());
        assertNotNull(response2.getDateRegister());
    }

    @Test
    public void invalidFindByProductType() throws InvalidProductsProcessException {
        ProductGatewayImpl gateway = gateway();

        when(repository.findByTypeProductAndTypeStatus(any(), any())).thenReturn(null);

        try {
            gateway.findByProductType("XPTO");
            fail("The find can be did");
        } catch (InvalidProductTypeException ex) {
            assertEquals("Product type invalid", ex.getTittle());
            assertEquals("The product type XPTO is invalid", ex.getMessage());
        }
    }

    @Test
    public void validEdit() {
        ProductGatewayImpl gateway = gateway();

        ProductDB product = ProductDB.builder()
                .name("product_test_1")
                .description("description_test_1")
                .quantity(123)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(2))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        when(repository.save(any())).thenReturn(getProductDB());

        ProductDto response = gateway.edit(product);

        assertNotNull(response.getId());
    }

    @Test
    public void validRemove() {
        ProductGatewayImpl gateway = gateway();

        ProductDB product = ProductDB.builder()
                .name("product_test_1")
                .description("description_test_1")
                .quantity(123)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(2))
                .typeStatus(TypeStatus.INACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        when(repository.save(any())).thenReturn(getProductDB());

        ProductDto response = gateway.remove(product);

        assertNotNull(response.getId());
    }

    @Test
    public void validFindAll() {
        ProductGatewayImpl gateway = gateway();

        ProductDB product1 = ProductDB.builder()
                .name("product_test_1")
                .description("description_test_1")
                .quantity(123)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(2))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        ProductDB product2 = ProductDB.builder()
                .name("product_test_2")
                .description("description_test_2")
                .quantity(321)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(1))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        when(repository.findAll()).thenReturn(List.of(product1, product2));

        List<ProductDB> products = gateway.findAll();

        ProductDB response1 = products.get(0);
        assertNotNull(response1.getId());
        assertEquals("product_test_1", response1.getName());
        assertEquals("description_test_1", response1.getDescription());
        assertEquals(123, response1.getQuantity());
        assertEquals(TypeProduct.DRINK, response1.getTypeProduct());
        assertEquals(new BigDecimal(2), response1.getPrice());
        assertEquals(TypeStatus.ACTIVE, response1.getTypeStatus());
        assertNotNull(response1.getDateRegister());

        ProductDB response2 = products.get(1);
        assertNotNull(response2.getId());
        assertEquals("product_test_2", response2.getName());
        assertEquals("description_test_2", response2.getDescription());
        assertEquals(321, response2.getQuantity());
        assertEquals(TypeProduct.DRINK, response2.getTypeProduct());
        assertEquals(new BigDecimal(1), response2.getPrice());
        assertEquals(TypeStatus.ACTIVE, response2.getTypeStatus());
        assertNotNull(response2.getDateRegister());
    }

    @Test
    public void validFindById() throws Exception {
        ProductGatewayImpl gateway = gateway();

        ProductDB product = ProductDB.builder()
                .name("product_test_1")
                .description("description_test_1")
                .quantity(123)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(2))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        when(repository.findById(any())).thenReturn(Optional.ofNullable(product));

        ProductDB response = gateway.findById(UUID.randomUUID());

        assertNotNull(response.getId());
        assertEquals("product_test_1", response.getName());
        assertEquals("description_test_1", response.getDescription());
        assertEquals(123, response.getQuantity());
        assertEquals(TypeProduct.DRINK, response.getTypeProduct());
        assertEquals(new BigDecimal(2), response.getPrice());
        assertEquals(TypeStatus.ACTIVE, response.getTypeStatus());
        assertNotNull(response.getDateRegister());
    }

    @Test
    public void invalidFindById() {
        ProductGatewayImpl gateway = gateway();

        when(repository.findById(any())).thenReturn(Optional.empty());

        try {
            gateway.findById(UUID.fromString("c3e726b9-a400-4367-9918-7f5cfb983b6b"));
            fail("The find can be did");
        } catch (ProductNotFoundException ex) {
            assertEquals("Product not found", ex.getTittle());
            assertEquals("The product with id c3e726b9-a400-4367-9918-7f5cfb983b6b not found", ex.getMessage());

        }

    }
}
