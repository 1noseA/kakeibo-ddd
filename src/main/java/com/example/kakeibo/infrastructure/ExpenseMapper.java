package com.example.kakeibo.infrastructure;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExpenseMapper {

	void insert(
            @Param("id") UUID id,
            @Param("entryDate") LocalDate entryDate,
            @Param("amount") int amount,
            @Param("categoryId") UUID categoryId,
            @Param("memo") String memo
    );

    List<ExpenseRecord> findForListByEntryDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
