package com.expensemanager.validator;

import com.expensemanager.dto.ExpenseDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ExpenseValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ExpenseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExpenseDTO expenseDTO = (ExpenseDTO) target;
        if (expenseDTO.getDateString().equals("") || expenseDTO.getDateString().isEmpty() || expenseDTO.getDateString() == null) {
            errors.rejectValue("dateString", null, "Expense date should not be null");
        }
    }
}
