package com.plandiy.model;

import java.math.BigDecimal;

public class Budget {
    private BigDecimal totalBudget;
    private BigDecimal   expenses;

    public Budget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
        this.expenses = BigDecimal.ZERO;
    }

    public BigDecimal getRemainingAmount() {
        return totalBudget.subtract(expenses);
    }

    public void addExpense(BigDecimal expense) {
        expenses = expenses.add(expense);
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void generateFinancialReport() {}



}
