package com.expensemanager.controller;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.dto.ExpenseFilterDTO;
import com.expensemanager.entity.Expense;
import com.expensemanager.service.ExpenseService;
import com.expensemanager.validator.ExpenseValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public String showExpenseList(Model model) {
        List<ExpenseDTO> expenseDTOList = expenseService.getAllExpenses();
        String totalExpense = expenseService.totalExpense(expenseDTOList);
        model.addAttribute("expenses", expenseDTOList);
        model.addAttribute("filter", new ExpenseFilterDTO());
        model.addAttribute("totalExpense", totalExpense);
        return "expenses-list";
    }

    @GetMapping("/create-expense")
    public String showAddExpenseForm(Model model) {
        model.addAttribute("expense", new ExpenseDTO());
        return "expense-form";
    }

    @PostMapping("/saveOrUpdateExpense")
    public String saveOrUpdateExpenseDetails(@Valid @ModelAttribute("expense") ExpenseDTO expenseDTO, BindingResult result) throws ParseException {
        new ExpenseValidator().validate(expenseDTO, result);

        if (result.hasErrors()) {
            return "expense-form";
        }

        expenseService.saveExpenseDetails(expenseDTO);
        return "redirect:/expenses";
    }

    @GetMapping("/deleteExpense/{expenseId}")
    public String deleteExpense(@PathVariable("expenseId") String expenseId) {
        expenseService.deleteExpenseById(expenseId);
        System.out.println(expenseId);
        return "redirect:/expenses";
    }

    @GetMapping("/updateExpense/{expenseId}")
    public String updateExpense(@PathVariable("expenseId") String expenseId, Model model) {
        ExpenseDTO expenseDTO = expenseService.getExpenseById(expenseId);
        model.addAttribute("expense", expenseDTO);
        return "expense-form";
    }
}
