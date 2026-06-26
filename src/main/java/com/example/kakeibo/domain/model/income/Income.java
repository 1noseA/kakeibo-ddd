package com.example.kakeibo.domain.model.income;

import com.example.kakeibo.domain.model.category.Category;
import com.example.kakeibo.domain.model.category.CategoryId;
import com.example.kakeibo.domain.model.shared.EntryDate;
import com.example.kakeibo.domain.model.shared.Memo;
import com.example.kakeibo.domain.model.shared.Money;

public class Income {

    private final IncomeId id;
    private final EntryDate entryDate;
    private final Money amount;
    private final CategoryId categoryId;
    private final Memo memo;

    private Income(
            IncomeId id,
            EntryDate entryDate,
            Money amount,
            CategoryId categoryId,
            Memo memo
    ) {
        this.id = id;
        this.entryDate = entryDate;
        this.amount = amount;
        this.categoryId = categoryId;
        this.memo = memo;
    }

    public static Income record(
            EntryDate entryDate,
            Money amount,
            Category category,
            Memo memo
    ) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("収入金額は1円以上である必要があります。");
        }
        if (!category.canUseForIncome()) {
            throw new IllegalArgumentException("収入には収入カテゴリーを指定してください。");
        }

        return new Income(
                IncomeId.newId(),
                entryDate,
                amount,
                category.id(),
                memo
        );
    }

    public IncomeId id() {
        return id;
    }

    public EntryDate entryDate() {
        return entryDate;
    }

    public Money amount() {
        return amount;
    }

    public CategoryId categoryId() {
        return categoryId;
    }

    public Memo memo() {
        return memo;
    }
}
