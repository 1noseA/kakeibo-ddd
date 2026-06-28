package com.example.kakeibo.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.kakeibo.domain.model.category.Category;

public interface CategoryRepository {

    Optional<Category> findById(UUID id);

    List<Category> findIncomeCategories();

    List<Category> findExpenseCategories();
}
