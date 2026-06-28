package com.example.kakeibo.application;

import java.time.LocalDate;
import java.time.YearMonth;

import org.springframework.stereotype.Service;

import com.example.kakeibo.infrastructure.ExpenseMapper;

@Service
public class GetMonthlyExpenseTotalUseCase {

	private final ExpenseMapper expenseMapper;

    public GetMonthlyExpenseTotalUseCase(ExpenseMapper expenseMapper) {
        this.expenseMapper = expenseMapper;
    }

    public int handle(YearMonth targetMonth) {
        LocalDate startDate = targetMonth.atDay(1);
        LocalDate endDate = targetMonth.plusMonths(1).atDay(1);

        return expenseMapper.sumAmountByEntryDateRange(startDate, endDate);
    }
}
