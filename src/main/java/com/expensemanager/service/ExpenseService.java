package com.expensemanager.service;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.entity.Expense;

import java.util.List;

public interface ExpenseService {
    public List<ExpenseDTO> getAllExpenses();
}
