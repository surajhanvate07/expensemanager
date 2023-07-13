package com.expensemanager.controller;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
