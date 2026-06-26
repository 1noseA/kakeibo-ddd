package com.example.kakeibo.domain.model.shared;

// 金額
public record Money(int amount) {

    public Money {
        if (amount < 0) {
            throw new IllegalArgumentException("金額は0円以上である必要があります。");
        }
    }

    public boolean isPositive() {
        return amount > 0;
    }
}
