package com.expensemanager.service;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.entity.Expense;

import java.text.ParseException;
import java.util.List;

public interface ExpenseService {
    public List<ExpenseDTO> getAllExpenses();

    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) throws ParseException;

    public void deleteExpenseById(String Id);
}
