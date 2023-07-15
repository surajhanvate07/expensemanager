package com.expensemanager.service.impl;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.entity.Expense;
import com.expensemanager.repository.ExpenseRepository;
import com.expensemanager.service.ExpenseService;
import com.expensemanager.util.DateTimeUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        List<ExpenseDTO> expenseDTOS = expenses.stream().map(this::mapToDTO).collect(Collectors.toList());

        return expenseDTOS;
    }

    @Override
    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) throws ParseException {
        // map the dto to entity
        Expense newExpense = mapToEntity(expenseDTO);

        // save to database
        newExpense = expenseRepository.save(newExpense);

        // map the entity to dto
        return mapToDTO(newExpense);
    }

    private ExpenseDTO mapToDTO(Expense expense) {
        ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
        expenseDTO.setDateString(DateTimeUtil.convertDateToString(expenseDTO.getDate()));

        return expenseDTO;
    }

    private Expense mapToEntity(ExpenseDTO expenseDTO) throws ParseException {
        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        // generate the expense id
        expense.setExpenseId(UUID.randomUUID().toString());
        // set the expense date
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));
        return expense;
    }
}
