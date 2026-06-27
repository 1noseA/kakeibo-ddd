package com.example.kakeibo.infrastructure;

import java.time.LocalDate;
import java.util.UUID;

public record ExpenseRecord(
        UUID id,
        LocalDate entryDate,
        Integer amount,
        String categoryName,
        String memo
) {
}
