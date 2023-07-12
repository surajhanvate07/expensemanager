package com.expensemanager.service;

import com.expensemanager.entity.Expense;

import java.util.List;

public interface ExpenseService {
    public List<Expense> getAllExpenses();
}
