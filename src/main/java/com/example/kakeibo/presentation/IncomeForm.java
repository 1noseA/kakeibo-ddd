package com.example.kakeibo.presentation;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeForm {

    @NotNull
    @PastOrPresent(message = "未来の日付は指定できません。")
    private LocalDate entryDate = LocalDate.now();

    @Min(1)
    private int amount;

    @NotNull
    private UUID categoryId;

    private String memo;
}
