package com.example.kakeibo.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.kakeibo.infrastructure.ExpenseMapper;
import com.example.kakeibo.infrastructure.ExpenseRecord;

@Service
public class GetExpenseListUseCase {

    private final ExpenseMapper expenseMapper;

    public GetExpenseListUseCase(ExpenseMapper expenseMapper) {
        this.expenseMapper = expenseMapper;
    }

    public List<ExpenseRecord> handle() {
        return expenseMapper.findAllForList();
    }
}
