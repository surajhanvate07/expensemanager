package com.expensemanager.service;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.dto.ExpenseFilterDTO;
import com.expensemanager.entity.Expense;

import java.text.ParseException;
import java.util.List;

public interface ExpenseService {
    public List<ExpenseDTO> getAllExpenses();

    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) throws ParseException;

    public ExpenseDTO getExpenseById(String Id);

    public void deleteExpenseById(String Id);

    public List<ExpenseDTO> getFilteredExpenses(ExpenseFilterDTO expenseFilterDTO) throws ParseException;
}
