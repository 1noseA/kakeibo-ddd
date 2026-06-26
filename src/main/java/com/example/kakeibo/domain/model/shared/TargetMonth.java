package com.example.kakeibo.domain.model.shared;

// 対象月
public record TargetMonth(int year, int month) {

    public TargetMonth {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("対象月は1〜12で指定してください。");
        }
    }
}
