package com.devsuperior.desafio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafio.dto.CategoryDTO;
import com.devsuperior.desafio.entities.Category;
import com.devsuperior.desafio.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {

        List<Category> list = repository.findAll();

        return list.stream()
                .map(CategoryDTO::new)
                .toList();
    }
}