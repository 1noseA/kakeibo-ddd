package com.example.kakeibo.domain.model.budget;

import java.util.UUID;

import com.example.kakeibo.domain.model.category.Category;
import com.example.kakeibo.domain.model.category.CategoryId;
import com.example.kakeibo.domain.model.shared.Money;
import com.example.kakeibo.domain.model.shared.TargetMonth;

public class CategoryBudget {

    private final UUID id;
    private final TargetMonth targetMonth;
    private final CategoryId categoryId;
    private final Money amount;

    private CategoryBudget(
            UUID id,
            TargetMonth targetMonth,
            CategoryId categoryId,
            Money amount
    ) {
        this.id = id;
        this.targetMonth = targetMonth;
        this.categoryId = categoryId;
        this.amount = amount;
    }

    public static CategoryBudget create(
            TargetMonth targetMonth,
            Category category,
            Money amount
    ) {
        if (!category.canUseForExpense()) {
            throw new IllegalArgumentException("カテゴリー予算には支出カテゴリーを指定してください。");
        }

        return new CategoryBudget(
                UUID.randomUUID(),
                targetMonth,
                category.id(),
                amount
        );
    }

    public UUID id() {
        return id;
    }

    public TargetMonth targetMonth() {
        return targetMonth;
    }

    public CategoryId categoryId() {
        return categoryId;
    }

    public Money amount() {
        return amount;
    }
}
