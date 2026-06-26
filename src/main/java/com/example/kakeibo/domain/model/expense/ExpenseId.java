package com.example.kakeibo.domain.model.expense;

import java.util.UUID;

public record ExpenseId(UUID value) {

    public static ExpenseId newId() {
        return new ExpenseId(UUID.randomUUID());
    }
}
