package com.example.kakeibo.presentation;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kakeibo.application.GetExpenseListUseCase;
import com.example.kakeibo.application.RecordExpenseUseCase;
import com.example.kakeibo.domain.repository.CategoryRepository;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final RecordExpenseUseCase recordExpenseUseCase;
    private final GetExpenseListUseCase getExpenseListUseCase;
    private final CategoryRepository categoryRepository;

    public ExpenseController(
            RecordExpenseUseCase recordExpenseUseCase,
            GetExpenseListUseCase getExpenseListUseCase,
            CategoryRepository categoryRepository
    ) {
        this.recordExpenseUseCase = recordExpenseUseCase;
        this.getExpenseListUseCase = getExpenseListUseCase;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("expenseForm", new ExpenseForm());
        setupModel(model);
        return "expenses/index";
    }

    @PostMapping
    public String record(
            @Valid @ModelAttribute ExpenseForm expenseForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            setupModel(model);
            return "expenses/index";
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
            setupModel(model);
            return "expenses/index";
        }

        return "redirect:/expenses";
    }

    private void setupModel(Model model) {
        model.addAttribute("categories", categoryRepository.findExpenseCategories());

        model.addAttribute("expenses", getExpenseListUseCase.handle());
    }
}
