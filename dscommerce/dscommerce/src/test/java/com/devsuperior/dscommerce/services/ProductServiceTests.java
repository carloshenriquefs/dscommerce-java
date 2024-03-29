package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.dto.ProductMinDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.tests.ProductFactory;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    private long existingProductId, nonExistingProductId, dependentProductId;
    private Product product;
    private ProductDTO productDTO;
    private String productName;
    private PageImpl<Product> page;

    @BeforeEach
    void setUp() throws Exception {
        existingProductId = 1L;
        nonExistingProductId = 2L;
        dependentProductId = 3L;

        productName = "PC Gamer";

        product = ProductFactory.createProduct(productName);
        productDTO = new ProductDTO(product);
        page = new PageImpl<>(List.of(product));

        when(repository.findById(existingProductId)).thenReturn(Optional.of(product));
        when(repository.findById(nonExistingProductId)).thenReturn(Optional.empty());

        when(repository.save(any())).thenReturn(product);

        when(repository.getReferenceById(existingProductId)).thenReturn(product);
        when(repository.getReferenceById(nonExistingProductId)).thenThrow(EntityNotFoundException.class);

        when(repository.existsById(existingProductId)).thenReturn(true);
        when(repository.existsById(dependentProductId)).thenReturn(true);
        when(repository.existsById(nonExistingProductId)).thenReturn(false);

        doNothing().when(repository).deleteById(existingProductId);
        doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentProductId);

        when(repository.searchByName(any(), (Pageable) any())).thenReturn(page);

        //doThrow(ResourceNotFoundException.class).when(repository).findById(nonExistingProductId);
    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists() {

        ProductDTO productDTO = service.findById(existingProductId);

        assertNotNull(productDTO);
        assertEquals(productDTO.getId(), existingProductId);
        assertEquals(productDTO.getName(), product.getName());
    }

    @Test
    public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {

        assertThrows(ResourceNotFoundException.class, () -> service.findById(nonExistingProductId));
    }

    @Test
    public void findAllShouldReturnPagedProductMinDTO() {

        Pageable pageable = PageRequest.of(0, 12);

        Page<ProductMinDTO> result = service.findAll(productName, pageable);

        assertNotNull(result);
        assertEquals(result.getSize(), 1);
        assertEquals(result.iterator().next().getName(), productName);
    }

    @Test
    public void insertShouldReturnProductDTO() {

        ProductDTO result = service.insert(productDTO);

        assertNotNull(result);
        assertEquals(result.getId(), product.getId());
    }

    @Test
    public void updateShouldReturnProductDTOWhenIdExists() {

        ProductDTO result = service.update(existingProductId, productDTO);

        assertNotNull(result);
        assertEquals(result.getId(), existingProductId);
        assertEquals(result.getName(), productDTO.getName());
    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {

        assertThrows(ResourceNotFoundException.class, () -> service.update(nonExistingProductId, productDTO));
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        assertDoesNotThrow(() -> {
            service.delete(existingProductId);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExistingProductId));
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenIdDoesNotExist() {

        assertThrows(DatabaseException.class, () -> service.delete(dependentProductId));
    }
}
