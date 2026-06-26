package com.example.kakeibo.domain.model.income;

import java.util.UUID;

public record IncomeId(UUID value) {

    public static IncomeId newId() {
        return new IncomeId(UUID.randomUUID());
    }
}
