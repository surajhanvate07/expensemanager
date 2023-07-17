package com.expensemanager.controller;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.dto.ExpenseFilterDTO;
import com.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.util.List;

@Controller
public class ExpenseFilterController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/filterExpenses")
    public String filterExpenses(@ModelAttribute("filter") ExpenseFilterDTO filterDTO, Model model) throws ParseException {
        List<ExpenseDTO> list = expenseService.getFilteredExpenses(filterDTO);
        String totalExpense = expenseService.totalExpense(list);
        model.addAttribute("expenses", list);
        model.addAttribute("totalExpense", totalExpense);
        return "expenses-list";
    }
}
