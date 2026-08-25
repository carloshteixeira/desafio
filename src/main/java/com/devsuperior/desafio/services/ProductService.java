package com.devsuperior.desafio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafio.dto.CategoryDTO;
import com.devsuperior.desafio.dto.ProductDTO;
import com.devsuperior.desafio.entities.Category;
import com.devsuperior.desafio.entities.Product;
import com.devsuperior.desafio.repositories.CategoryRepository;
import com.devsuperior.desafio.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        return result.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = repository.findById(id);

        Product entity = result.orElseThrow(
                () -> new RuntimeException("Resource not found"));

        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        Product entity = new Product();

        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {

        try {

            Product entity = repository.getReferenceById(id);

            copyDtoToEntity(dto, entity);

            entity = repository.save(entity);

            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {

            throw new RuntimeException("Resource not found");
        }
    }

    @Transactional
    public void delete(Long id) {

        repository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDate(dto.getDate());

        entity.getCategories().clear();

        for (CategoryDTO catDto : dto.getCategories()) {

            Category category =
                    categoryRepository.getReferenceById(catDto.getId());

            entity.getCategories().add(category);
        }
    }
}