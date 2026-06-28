package com.example.kakeibo.infrastructure;

import java.time.LocalDate;
import java.util.UUID;

public record IncomeRecord(
        UUID id,
        LocalDate entryDate,
        Integer amount,
        String categoryName,
        String memo
) {
}
