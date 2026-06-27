package com.example.kakeibo.application;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakeibo.domain.model.category.Category;
import com.example.kakeibo.domain.model.expense.Expense;
import com.example.kakeibo.domain.model.shared.EntryDate;
import com.example.kakeibo.domain.model.shared.Memo;
import com.example.kakeibo.domain.model.shared.Money;
import com.example.kakeibo.domain.repository.CategoryRepository;
import com.example.kakeibo.domain.repository.ExpenseRepository;

@Service
public class RecordExpenseUseCase {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    public RecordExpenseUseCase(
            CategoryRepository categoryRepository,
            ExpenseRepository expenseRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
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

        Expense expense = Expense.record(
                new EntryDate(entryDate),
                new Money(amount),
                category,
                Memo.of(memo)
        );

        expenseRepository.save(expense);
    }
}
