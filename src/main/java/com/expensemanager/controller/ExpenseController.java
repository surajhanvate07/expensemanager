package com.expensemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpenseController {

    @GetMapping("/expenses")
    public String showExpenseList() {
        return "expenses-list";
    }
}
