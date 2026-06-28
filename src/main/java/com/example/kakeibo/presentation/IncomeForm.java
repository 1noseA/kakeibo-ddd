package com.example.kakeibo.presentation;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeForm {

    @NotNull
    private LocalDate entryDate = LocalDate.now();

    @Min(1)
    private int amount;

    @NotNull
    private UUID categoryId;

    private String memo;
}
