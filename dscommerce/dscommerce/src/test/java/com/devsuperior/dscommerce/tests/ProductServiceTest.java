package com.devsuperior.dscommerce.tests;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.dto.ProductMinDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.ProductService;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    private long existingProductId, nonExistingProductId;
    private Product product;
    private ProductDTO productDTO;
    private String productName;
    private PageImpl<Product> page;

    @BeforeEach
    void setUp() throws Exception {
        existingProductId = 1L;
        nonExistingProductId = 2L;

        productName = "PC Gamer";

        product = ProductFactory.createProduct(productName);

        productDTO = new ProductDTO(product);
        page = new PageImpl<>(List.of(product));

        when(repository.findById(existingProductId)).thenReturn(Optional.of(product));
        when(repository.findById(nonExistingProductId)).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(product);

        when(repository.searchByName(any(), (Pageable) any())).thenReturn(page);
        //doThrow(ResourceNotFoundException.class).when(repository).findById(nonExistingProductId);
    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists() {

        ProductDTO productDTO = service.findById(existingProductId);

        Assertions.assertNotNull(productDTO);
        Assertions.assertEquals(productDTO.getId(), existingProductId);
        Assertions.assertEquals(productDTO.getName(), product.getName());
    }

    @Test
    public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(nonExistingProductId));
    }

    @Test
    public void findAllShouldReturnPagedProductMinDTO() {

        Pageable pageable = PageRequest.of(0, 12);

        Page<ProductMinDTO> result = service.findAll(productName, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getSize(), 1);
        Assertions.assertEquals(result.iterator().next().getName(), productName);
    }

    @Test
    public void insertShouldReturnProductDTO() {

        ProductDTO result = service.insert(productDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), product.getId());
    }
}
