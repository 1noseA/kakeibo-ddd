package com.example.kakeibo.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.kakeibo.domain.model.category.Category;
import com.example.kakeibo.domain.model.category.CategoryId;
import com.example.kakeibo.domain.model.category.CategoryType;
import com.example.kakeibo.domain.repository.CategoryRepository;

@Repository
public class MyBatisCategoryRepository implements CategoryRepository {

    private final CategoryMapper mapper;

    public MyBatisCategoryRepository(CategoryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return mapper.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Category> findExpenseCategories() {
        return mapper.findExpenseCategories()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private Category toDomain(CategoryRecord record) {
        return new Category(
        	        new CategoryId(record.id()),
                record.name(),
                CategoryType.valueOf(record.type()),
                record.active()
        );
    }
}