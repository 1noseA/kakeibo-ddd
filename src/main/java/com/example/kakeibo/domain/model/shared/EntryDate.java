package com.example.kakeibo.domain.model.shared;

import java.time.LocalDate;

// 記録日
public record EntryDate(LocalDate value) {

    public EntryDate {
        if (value == null) {
            throw new IllegalArgumentException("記録日は必須です。");
        }
        if (value.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("未来の日付は指定できません。");
        }
    }

    public TargetMonth toTargetMonth() {
        return new TargetMonth(value.getYear(), value.getMonthValue());
    }
}
