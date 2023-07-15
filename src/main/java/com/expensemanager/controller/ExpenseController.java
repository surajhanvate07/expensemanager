package com.expensemanager.controller;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.entity.Expense;
import com.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public String showExpenseList(Model model) {
        List<ExpenseDTO> expenseDTOList = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenseDTOList);
        return "expenses-list";
    }

    @GetMapping("/create-expense")
    public String showAddExpenseForm(Model model) {
        model.addAttribute("expense", new ExpenseDTO());
        return "expense-form";
    }

    @PostMapping("/saveOrUpdateExpense")
    public String saveOrUpdateExpenseDetails(@ModelAttribute("expense") ExpenseDTO expenseDTO) throws ParseException {
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
