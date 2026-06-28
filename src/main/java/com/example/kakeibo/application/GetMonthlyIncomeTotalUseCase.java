package com.example.kakeibo.application;

import java.time.LocalDate;
import java.time.YearMonth;

import org.springframework.stereotype.Service;

import com.example.kakeibo.infrastructure.IncomeMapper;

@Service
public class GetMonthlyIncomeTotalUseCase {

    private final IncomeMapper incomeMapper;

    public GetMonthlyIncomeTotalUseCase(IncomeMapper incomeMapper) {
        this.incomeMapper = incomeMapper;
    }

    public int handle(YearMonth targetMonth) {
        LocalDate startDate = targetMonth.atDay(1);
        LocalDate endDate = targetMonth.plusMonths(1).atDay(1);

        return incomeMapper.sumAmountByEntryDateRange(startDate, endDate);
    }
}
