package br.com.fiap.techchallengeproduct;

import br.com.fiap.techchallengeproduct.application.dto.product.ProductDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductEditFormDto;
import br.com.fiap.techchallengeproduct.application.dto.product.ProductFormDto;
import br.com.fiap.techchallengeproduct.application.usecases.ProductUseCaseImpl;
import br.com.fiap.techchallengeproduct.domain.enums.TypeProduct;
import br.com.fiap.techchallengeproduct.domain.enums.TypeStatus;
import br.com.fiap.techchallengeproduct.domain.exception.InvalidProcessException;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidPriceException;
import br.com.fiap.techchallengeproduct.domain.exception.products.InvalidQuantityException;
import br.com.fiap.techchallengeproduct.external.infrastructure.entities.ProductDB;
import br.com.fiap.techchallengeproduct.external.infrastructure.gateway.ProductGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductUseCaseImplTest {

    @Mock
    private ProductGatewayImpl productGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ProductUseCaseImpl useCase() {
        return new ProductUseCaseImpl(productGateway);
    }

    @Test
    public void registerOk() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductFormDto productFormDto = new ProductFormDto("valid_product_1", "description_product_1", 10,
                TypeProduct.DESSERT, new BigDecimal(3),TypeStatus.ACTIVE, LocalDateTime.now());

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        when(productGateway.register(any())).thenReturn(productDto);

        ProductDto response = useCase.register(productFormDto);

        assertEquals("3add1fbb-379a-4277-aef0-b57b0e6e740d", response.getId().toString());
        assertEquals("valid_product_1", response.getName());
        assertEquals("description_product_1", response.getDescription());
        assertEquals(10, response.getQuantity());
        assertEquals(TypeProduct.DESSERT, response.getTypeProduct());
        assertEquals(new BigDecimal(3), response.getPrice());
        assertEquals(TypeStatus.ACTIVE, response.getTypeStatus());
        assertNotNull(response.getDateRegister());

    }

    @Test
    public void registerInvalidQuantity() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductFormDto productFormDto = new ProductFormDto("valid_product_1", "description_product_1", -1,
                TypeProduct.DESSERT, new BigDecimal(3),TypeStatus.ACTIVE, LocalDateTime.now());

        try {
            useCase.register(productFormDto);
            fail("The find can be did");
        } catch (InvalidQuantityException ex) {
            assertEquals("Invalid product quantity", ex.getTittle());
            assertEquals("The quantity -1 not is invalid", ex.getMessage());
        }
    }

    @Test
    public void registerInvalidPrice() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductFormDto productFormDto = new ProductFormDto("valid_product_1", "description_product_1", 5,
                TypeProduct.DESSERT, new BigDecimal(-2),TypeStatus.ACTIVE, LocalDateTime.now());

        try {
            useCase.register(productFormDto);
            fail("The find can be did");
        } catch (InvalidPriceException ex) {
            assertEquals("Invalid product price", ex.getTittle());
            assertEquals("The price -2 not is invalid", ex.getMessage());
        }
    }

    @Test
    public void editOk() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductDB product = ProductDB.builder()
                .id(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"))
                .name("valid_product_1")
                .description("description_product_1")
                .quantity(10)
                .typeProduct(TypeProduct.DESSERT)
                .price(new BigDecimal(3))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        when(productGateway.findById(any())).thenReturn(product);
        when(productGateway.edit(any())).thenReturn(productDto);

        ProductEditFormDto productEditFormDto = new ProductEditFormDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, new Date());

        ProductDto response = useCase.edit(productEditFormDto);

        assertEquals("3add1fbb-379a-4277-aef0-b57b0e6e740d", response.getId().toString());
        assertEquals("valid_product_1", response.getName());
        assertEquals("description_product_1", response.getDescription());
        assertEquals(10, response.getQuantity());
        assertEquals(TypeProduct.DESSERT, response.getTypeProduct());
        assertEquals(new BigDecimal(3), response.getPrice());
        assertEquals(TypeStatus.ACTIVE, response.getTypeStatus());
        assertNotNull(response.getDateRegister());

    }

    @Test
    public void editInvalidQuantity() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductEditFormDto productEditFormDto = new ProductEditFormDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                -1, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, new Date());

        try {
            useCase.edit(productEditFormDto);
            fail("The find can be did");
        } catch (InvalidQuantityException ex) {
            assertEquals("Invalid product quantity", ex.getTittle());
            assertEquals("The quantity -1 not is invalid", ex.getMessage());
        }
    }

    @Test
    public void editInvalidPrice() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductEditFormDto productEditFormDto = new ProductEditFormDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(-2),
                TypeStatus.ACTIVE, new Date());

        try {
            useCase.edit(productEditFormDto);
            fail("The find can be did");
        } catch (InvalidPriceException ex) {
            assertEquals("Invalid product price", ex.getTittle());
            assertEquals("The price -2 not is invalid", ex.getMessage());
        }
    }

    @Test
    public void findByProductTypeOk() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductDB product1 = ProductDB.builder()
                .id(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"))
                .name("valid_product_1")
                .description("description_product_1")
                .quantity(10)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(3))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        ProductDB product2 = ProductDB.builder()
                .id(UUID.fromString("4086e52e-7ecd-4397-b358-260d771f4e8d"))
                .name("valid_product_2")
                .description("description_product_2")
                .quantity(5)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(5))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        when(productGateway.findByProductType(any())).thenReturn(List.of(product1, product2));

        List<ProductDto> response = useCase.findByProductType("DRINK");

        ProductDto response1 = response.get(0);
        assertEquals("3add1fbb-379a-4277-aef0-b57b0e6e740d", response1.getId().toString());
        assertEquals("valid_product_1", response1.getName());
        assertEquals("description_product_1", response1.getDescription());
        assertEquals(10, response1.getQuantity());
        assertEquals(TypeProduct.DRINK, response1.getTypeProduct());
        assertEquals(new BigDecimal(3), response1.getPrice());
        assertEquals(TypeStatus.ACTIVE, response1.getTypeStatus());
        assertNotNull(response1.getDateRegister());

        ProductDto response2 = response.get(1);
        assertEquals("4086e52e-7ecd-4397-b358-260d771f4e8d", response2.getId().toString());
        assertEquals("valid_product_2", response2.getName());
        assertEquals("description_product_2", response2.getDescription());
        assertEquals(5, response2.getQuantity());
        assertEquals(TypeProduct.DRINK, response2.getTypeProduct());
        assertEquals(new BigDecimal(5), response2.getPrice());
        assertEquals(TypeStatus.ACTIVE, response2.getTypeStatus());
        assertNotNull(response2.getDateRegister());
    }

    @Test
    public void findByProductTypeNoFound() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        when(productGateway.findByProductType(any())).thenReturn(null);

        try {
            useCase.findByProductType("SNACK_ACCOMPANIMENT");
        } catch (InvalidProcessException ex) {
            assertEquals("Product type not found", ex.getTittle());
            assertEquals("The products with product type SNACK_ACCOMPANIMENT not found", ex.getMessage());
        }
    }

    @Test
    public void findAll() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductDB product1 = ProductDB.builder()
                .id(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"))
                .name("valid_product_1")
                .description("description_product_1")
                .quantity(10)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(3))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        ProductDB product2 = ProductDB.builder()
                .id(UUID.fromString("4086e52e-7ecd-4397-b358-260d771f4e8d"))
                .name("valid_product_2")
                .description("description_product_2")
                .quantity(5)
                .typeProduct(TypeProduct.DRINK)
                .price(new BigDecimal(5))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        when(productGateway.findAll()).thenReturn(List.of(product1, product2));

        List<ProductDto> response = useCase.findAll();

        ProductDto response1 = response.get(0);
        assertEquals("3add1fbb-379a-4277-aef0-b57b0e6e740d", response1.getId().toString());
        assertEquals("valid_product_1", response1.getName());
        assertEquals("description_product_1", response1.getDescription());
        assertEquals(10, response1.getQuantity());
        assertEquals(TypeProduct.DRINK, response1.getTypeProduct());
        assertEquals(new BigDecimal(3), response1.getPrice());
        assertEquals(TypeStatus.ACTIVE, response1.getTypeStatus());
        assertNotNull(response1.getDateRegister());

        ProductDto response2 = response.get(1);
        assertEquals("4086e52e-7ecd-4397-b358-260d771f4e8d", response2.getId().toString());
        assertEquals("valid_product_2", response2.getName());
        assertEquals("description_product_2", response2.getDescription());
        assertEquals(5, response2.getQuantity());
        assertEquals(TypeProduct.DRINK, response2.getTypeProduct());
        assertEquals(new BigDecimal(5), response2.getPrice());
        assertEquals(TypeStatus.ACTIVE, response2.getTypeStatus());
        assertNotNull(response2.getDateRegister());
    }

    @Test
    public void removeOk() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductDB product = ProductDB.builder()
                .id(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"))
                .name("valid_product_1")
                .description("description_product_1")
                .quantity(10)
                .typeProduct(TypeProduct.DESSERT)
                .price(new BigDecimal(3))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        ProductDto productDto = new ProductDto(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"),
                "valid_product_1", "description_product_1",
                10, TypeProduct.DESSERT, new BigDecimal(3),
                TypeStatus.ACTIVE, LocalDateTime.now());

        when(productGateway.findById(any())).thenReturn(product);
        when(productGateway.remove(any())).thenReturn(productDto);

        ProductDto response = useCase.remove(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"));

        assertEquals("3add1fbb-379a-4277-aef0-b57b0e6e740d", response.getId().toString());
        assertEquals("valid_product_1", response.getName());
        assertEquals("description_product_1", response.getDescription());
        assertEquals(10, response.getQuantity());
        assertEquals(TypeProduct.DESSERT, response.getTypeProduct());
        assertEquals(new BigDecimal(3), response.getPrice());
        assertEquals(TypeStatus.ACTIVE, response.getTypeStatus());
        assertNotNull(response.getDateRegister());
    }

    @Test
    public void findByIdOk() throws Exception {
        ProductUseCaseImpl useCase = useCase();

        ProductDB product = ProductDB.builder()
                .id(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"))
                .name("valid_product_1")
                .description("description_product_1")
                .quantity(10)
                .typeProduct(TypeProduct.DESSERT)
                .price(new BigDecimal(3))
                .typeStatus(TypeStatus.ACTIVE)
                .dateRegister(LocalDateTime.now())
                .build();

        when(productGateway.findById(any())).thenReturn(product);

        ProductDto response = useCase.findById(UUID.fromString("3add1fbb-379a-4277-aef0-b57b0e6e740d"));

        assertEquals("3add1fbb-379a-4277-aef0-b57b0e6e740d", response.getId().toString());
        assertEquals("valid_product_1", response.getName());
        assertEquals("description_product_1", response.getDescription());
        assertEquals(10, response.getQuantity());
        assertEquals(TypeProduct.DESSERT, response.getTypeProduct());
        assertEquals(new BigDecimal(3), response.getPrice());
        assertEquals(TypeStatus.ACTIVE, response.getTypeStatus());
        assertNotNull(response.getDateRegister());
    }
}
