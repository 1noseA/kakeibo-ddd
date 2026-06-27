package com.example.kakeibo.domain.repository;

import com.example.kakeibo.domain.model.expense.Expense;

public interface ExpenseRepository {

    void save(Expense expense);
}
