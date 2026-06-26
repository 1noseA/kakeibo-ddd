package com.example.kakeibo.domain.model.category;

public class Category {

    private final CategoryId id;
    private String name;
    private final CategoryType type;
    private boolean active;

    public Category(CategoryId id, String name, CategoryType type, boolean active) {
        if (id == null) {
            throw new IllegalArgumentException("カテゴリーIDは必須です。");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("カテゴリー名は必須です。");
        }
        if (type == null) {
            throw new IllegalArgumentException("カテゴリー種別は必須です。");
        }

        this.id = id;
        this.name = name;
        this.type = type;
        this.active = active;
    }

    public CategoryId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public CategoryType type() {
        return type;
    }

    public boolean active() {
        return active;
    }

    public boolean canUseForIncome() {
        return active && type == CategoryType.INCOME;
    }

    public boolean canUseForExpense() {
        return active && type == CategoryType.EXPENSE;
    }
}
