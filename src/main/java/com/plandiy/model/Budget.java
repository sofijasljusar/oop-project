package com.plandiy.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Budget {
    private BigDecimal totalBudget;
    private BigDecimal expenses;
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public Budget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
        this.expenses = BigDecimal.ZERO;
    }

    public BigDecimal getRemainingAmount() {
        return totalBudget.subtract(expenses);
    }

    public void addExpense(BigDecimal expense, String description) {
        transactions.add(new Transaction(expense, description));
        expenses = expenses.add(expense);
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public String generateFinancialReport() {
        StringBuilder report = new StringBuilder();
        report.append("Financial Report\n");
        report.append("Total Budget: ").append(totalBudget).append("\n");
        report.append("Total Expenses: ").append(expenses).append("\n");
        report.append("Remaining Balance: ").append(getRemainingAmount()).append("\n");

        report.append("Transactions:\n");
        for (Transaction transaction : transactions) {
            report.append(" - ").append(transaction.getDescription()).append(": ").append(transaction.getAmount()).append("\n");
        }

        return report.toString();
    }


}
