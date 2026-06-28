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

    @NotNull(message = "日付を入力してください。")
    @PastOrPresent(message = "未来の日付は指定できません。")
    private LocalDate entryDate = LocalDate.now();

    @Min(value = 1, message = "金額は1円以上で入力してください。")
    private int amount;

    @NotNull(message = "カテゴリを選択してください。")
    private UUID categoryId;

    private String memo;
}
