package com.example.kakeibo.domain.model.expense;

import com.example.kakeibo.domain.model.category.Category;
import com.example.kakeibo.domain.model.category.CategoryId;
import com.example.kakeibo.domain.model.shared.EntryDate;
import com.example.kakeibo.domain.model.shared.Memo;
import com.example.kakeibo.domain.model.shared.Money;

public class Expense {

    private final ExpenseId id;
    private final EntryDate entryDate;
    private final Money amount;
    private final CategoryId categoryId;
    private final Memo memo;

    private Expense(
            ExpenseId id,
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

    public static Expense record(
            EntryDate entryDate,
            Money amount,
            Category category,
            Memo memo
    ) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("支出金額は1円以上である必要があります。");
        }
        if (!category.canUseForExpense()) {
            throw new IllegalArgumentException("支出には支出カテゴリーを指定してください。");
        }

        return new Expense(
                ExpenseId.newId(),
                entryDate,
                amount,
                category.id(),
                memo
        );
    }

    public ExpenseId id() {
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
