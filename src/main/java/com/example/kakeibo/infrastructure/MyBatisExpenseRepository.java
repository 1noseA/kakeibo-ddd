package com.example.kakeibo.infrastructure;

import org.springframework.stereotype.Repository;

import com.example.kakeibo.domain.model.expense.Expense;
import com.example.kakeibo.domain.repository.ExpenseRepository;

@Repository
public class MyBatisExpenseRepository implements ExpenseRepository {

    private final ExpenseMapper mapper;

    public MyBatisExpenseRepository(ExpenseMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Expense expense) {
        mapper.insert(
                expense.id().value(),
                expense.entryDate().value(),
                expense.amount().amount(),
                expense.categoryId().value(),
                expense.memo().value()
        );
    }
}
