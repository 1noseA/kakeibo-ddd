package com.example.kakeibo.presentation;

import java.time.YearMonth;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kakeibo.application.GetExpenseListUseCase;
import com.example.kakeibo.application.GetMonthlyIncomeTotalUseCase;
import com.example.kakeibo.application.RecordExpenseUseCase;
import com.example.kakeibo.application.RecordIncomeUseCase;
import com.example.kakeibo.domain.repository.CategoryRepository;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final RecordIncomeUseCase recordIncomeUseCase;
    private final RecordExpenseUseCase recordExpenseUseCase;
    private final GetMonthlyIncomeTotalUseCase getMonthlyIncomeTotalUseCase;
    private final GetExpenseListUseCase getExpenseListUseCase;
    private final CategoryRepository categoryRepository;

    public TransactionController(
            RecordIncomeUseCase recordIncomeUseCase,
            RecordExpenseUseCase recordExpenseUseCase,
            GetMonthlyIncomeTotalUseCase getMonthlyIncomeTotalUseCase,
            GetExpenseListUseCase getExpenseListUseCase,
            CategoryRepository categoryRepository
    ) {
        this.recordIncomeUseCase = recordIncomeUseCase;
        this.recordExpenseUseCase = recordExpenseUseCase;
        this.getMonthlyIncomeTotalUseCase = getMonthlyIncomeTotalUseCase;
        this.getExpenseListUseCase = getExpenseListUseCase;
        this.categoryRepository = categoryRepository;
    }

    // 取引画面の初期表示
    @GetMapping
    public String index(Model model) {
        model.addAttribute("showIncomeDialog", false);
        model.addAttribute("showExpenseDialog", false);
        setupModel(model);
        return "transactions/index";
    }

    // 収入登録
    @PostMapping("/incomes")
    public String recordIncome(
            @Valid @ModelAttribute IncomeForm incomeForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("showIncomeDialog", true);
            model.addAttribute("showExpenseDialog", false);
            setupModel(model);
            return "transactions/index";
        }

        try {
            recordIncomeUseCase.handle(
                    incomeForm.getEntryDate(),
                    incomeForm.getAmount(),
                    incomeForm.getCategoryId(),
                    incomeForm.getMemo()
            );
        } catch (IllegalArgumentException e) {
            bindingResult.reject("income.record.error", e.getMessage());
            model.addAttribute("showIncomeDialog", true);
            model.addAttribute("showExpenseDialog", false);
            setupModel(model);
            return "transactions/index";
        }

        return "redirect:/transactions";
    }

    // 支出登録
    @PostMapping("/expenses")
    public String recordExpense(
            @Valid @ModelAttribute ExpenseForm expenseForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("showIncomeDialog", false);
            model.addAttribute("showExpenseDialog", true);
            setupModel(model);
            return "transactions/index";
        }

        try {
            recordExpenseUseCase.handle(
                    expenseForm.getEntryDate(),
                    expenseForm.getAmount(),
                    expenseForm.getCategoryId(),
                    expenseForm.getMemo()
            );
        } catch (IllegalArgumentException e) {
            bindingResult.reject("expense.record.error", e.getMessage());
            model.addAttribute("showIncomeDialog", false);
            model.addAttribute("showExpenseDialog", true);
            setupModel(model);
            return "transactions/index";
        }

        return "redirect:/transactions";
    }

    // 画面共通のModel
    private void setupModel(Model model) {
        YearMonth targetMonth = YearMonth.now();

        if (!model.containsAttribute("incomeForm")) {
            model.addAttribute("incomeForm", new IncomeForm());
        }
        if (!model.containsAttribute("expenseForm")) {
            model.addAttribute("expenseForm", new ExpenseForm());
        }

        model.addAttribute("incomeCategories", categoryRepository.findIncomeCategories());
        model.addAttribute("expenseCategories", categoryRepository.findExpenseCategories());
        model.addAttribute("targetMonth", targetMonth);
        model.addAttribute("incomeTotal", getMonthlyIncomeTotalUseCase.handle(targetMonth));
        model.addAttribute("expenses", getExpenseListUseCase.handle());
    }
}
