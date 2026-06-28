package com.example.kakeibo.infrastructure;

import org.springframework.stereotype.Repository;

import com.example.kakeibo.domain.model.income.Income;
import com.example.kakeibo.domain.repository.IncomeRepository;

@Repository
public class MyBatisIncomeRepository implements IncomeRepository {

    private final IncomeMapper mapper;

    public MyBatisIncomeRepository(IncomeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Income income) {
        mapper.insert(
                income.id().value(),
                income.entryDate().value(),
                income.amount().amount(),
                income.categoryId().value(),
                income.memo().value()
        );
    }
}
