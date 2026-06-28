package com.example.kakeibo.application;

import java.time.LocalDate;
import java.time.YearMonth;
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

    public List<ExpenseRecord> handle(YearMonth targetMonth) {
        LocalDate startDate = targetMonth.atDay(1);
        LocalDate endDate = targetMonth.plusMonths(1).atDay(1);

        return expenseMapper.findForListByEntryDateRange(startDate, endDate);
    }
}
