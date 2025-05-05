package com.plandiy.model.budget;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Represents a budget with a total amount and a collection of transactions (expenses).
 * It tracks the remaining budget and provides functionality to add expenses
 * and generate a financial report.
 */
public class Budget {
    private final BigDecimal totalBudget;
    private BigDecimal expenses;
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    /**
     * Constructs a new Budget with a specified total budget.
     *
     * @param totalBudget The total budget allocated to this budget.
     */
    public Budget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
        this.expenses = BigDecimal.ZERO;
    }

    /**
     * Calculates the remaining amount in the budget after expenses.
     *
     * @return The remaining budget amount (total budget - expenses).
     */
    public BigDecimal getRemainingAmount() {
        return totalBudget.subtract(expenses);
    }

    /**
     * Adds a new expense to the budget and records it as a transaction.
     *
     * @param expense The amount of the expense.
     * @param description A description of the expense.
     */
    public void addExpense(BigDecimal expense, String description) {
        transactions.add(new Transaction(expense, description));
        expenses = expenses.add(expense);
    }

    /**
     * Retrieves the total budget allocated to this budget.
     *
     * @return The total budget.
     */
    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    /**
     * Retrieves the total expenses incurred in the budget so far.
     *
     * @return The total expenses.
     */
    public BigDecimal getExpenses() {
        return expenses;
    }

    /**
     * Generates a financial report summarizing the budget details and transactions.
     *
     * @return A string representing the financial report.
     */
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
