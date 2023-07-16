package com.expensemanager.service.impl;

import com.expensemanager.dto.ExpenseDTO;
import com.expensemanager.dto.ExpenseFilterDTO;
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
import java.util.Optional;
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

    @Override
    public ExpenseDTO getExpenseById(String Id) {
        return mapToDTO(getExpense(Id));
    }

    @Override
    public void deleteExpenseById(String Id) {
        Expense oldExpense = getExpense(Id);
        expenseRepository.delete(oldExpense);
    }

    @Override
    public List<ExpenseDTO> getFilteredExpenses(ExpenseFilterDTO expenseFilterDTO) throws ParseException {
        String keyword = expenseFilterDTO.getKeyword();
        String sortBy = expenseFilterDTO.getSortBy();
        String startDateString = expenseFilterDTO.getStartDate();
        String endDateString = expenseFilterDTO.getEndDate();

        Date startDate = !startDateString.isEmpty() ? DateTimeUtil.convertStringToDate(startDateString) : new Date(0);
        Date endDate = !endDateString.isEmpty() ? DateTimeUtil.convertStringToDate(endDateString) : new Date(System.currentTimeMillis());

        List<Expense> filteredList = expenseRepository.findByNameContainingAndDateBetween(keyword, startDate, endDate);
        List<ExpenseDTO> expenseDTOList = filteredList.stream().map(this::mapToDTO).collect(Collectors.toList());

        if (sortBy.equals("date")) {
            expenseDTOList.sort((e1, e2) -> e2.getDate().compareTo(e1.getDate()));
        } else {
            expenseDTOList.sort((e1, e2) -> e2.getAmount().compareTo(e1.getAmount()));
        }

        return expenseDTOList;
    }

    private Expense getExpense(String Id) {
        return expenseRepository.findByExpenseId(Id).orElseThrow(() -> new RuntimeException("Expense not found with Id :" + Id));
    }

    private ExpenseDTO mapToDTO(Expense expense) {
        ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
        expenseDTO.setDateString(DateTimeUtil.convertDateToString(expenseDTO.getDate()));

        return expenseDTO;
    }

    private Expense mapToEntity(ExpenseDTO expenseDTO) throws ParseException {
        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        // generate the expense id
        if (expense.getExpenseId() == null) {
            expense.setExpenseId(UUID.randomUUID().toString());
        }
        // set the expense date
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));
        return expense;
    }
}
