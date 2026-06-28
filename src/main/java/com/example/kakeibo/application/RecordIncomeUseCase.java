package com.example.kakeibo.application;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakeibo.domain.model.category.Category;
import com.example.kakeibo.domain.model.income.Income;
import com.example.kakeibo.domain.model.shared.EntryDate;
import com.example.kakeibo.domain.model.shared.Memo;
import com.example.kakeibo.domain.model.shared.Money;
import com.example.kakeibo.domain.repository.CategoryRepository;
import com.example.kakeibo.domain.repository.IncomeRepository;

@Service
public class RecordIncomeUseCase {

    private final CategoryRepository categoryRepository;
    private final IncomeRepository incomeRepository;

    public RecordIncomeUseCase(
            CategoryRepository categoryRepository,
            IncomeRepository incomeRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.incomeRepository = incomeRepository;
    }

    @Transactional
    public void handle(
            LocalDate entryDate,
            int amount,
            UUID categoryId,
            String memo
    ) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("カテゴリーが存在しません。"));

        Income income = Income.record(
                new EntryDate(entryDate),
                new Money(amount),
                category,
                Memo.of(memo)
        );

        incomeRepository.save(income);
    }
}
