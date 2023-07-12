package com.expensemanager.service.impl;

import com.expensemanager.entity.Expense;
import com.expensemanager.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }
}
