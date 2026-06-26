package com.example.kakeibo.domain.model.budget;

import java.util.UUID;

import com.example.kakeibo.domain.model.shared.Money;
import com.example.kakeibo.domain.model.shared.TargetMonth;

public class MonthlyBudget {

    private final UUID id;
    private final TargetMonth targetMonth;
    private final Money amount;

    public MonthlyBudget(UUID id, TargetMonth targetMonth, Money amount) {
        if (id == null) {
            throw new IllegalArgumentException("月次予算IDは必須です。");
        }
        this.id = id;
        this.targetMonth = targetMonth;
        this.amount = amount;
    }

    public static MonthlyBudget create(TargetMonth targetMonth, Money amount) {
        return new MonthlyBudget(UUID.randomUUID(), targetMonth, amount);
    }

    public UUID id() {
        return id;
    }

    public TargetMonth targetMonth() {
        return targetMonth;
    }

    public Money amount() {
        return amount;
    }
}
