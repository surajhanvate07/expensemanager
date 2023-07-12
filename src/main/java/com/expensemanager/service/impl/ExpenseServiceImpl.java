package com.expensemanager.service.impl;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.entity.Expense;
import com.expensemanager.repository.ExpenseRepository;
import com.expensemanager.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses =  expenseRepository.findAll();
        List<ExpenseDTO> expenseDTOS = expenses.stream().map(this::mapToDTO).collect(Collectors.toList());

        return expenseDTOS;
    }

    private ExpenseDTO mapToDTO(Expense expense) {
        return modelMapper.map(expense, ExpenseDTO.class);
    }
}
