package com.devsuperior.dscommerce.tests;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    private long existingProductId, nonExistingProductId;
    private Product product;

    @BeforeEach
    void setUp() throws Exception {
        existingProductId = 1L;
        nonExistingProductId = 2l;

        product = ProductFactory.createProduct();
        when(repository.findById(existingProductId)).thenReturn(Optional.of(product));
    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists() {

        ProductDTO productDTO = service.findById(existingProductId);

        Assertions.assertNotNull(productDTO);
        Assertions.assertEquals(productDTO.getId(), existingProductId);
        Assertions.assertEquals(productDTO.getName(), product.getName());
    }
}
