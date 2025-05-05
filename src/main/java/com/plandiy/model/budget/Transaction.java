package com.plandiy.model.budget;

import java.math.BigDecimal;

/**
 * Represents an individual transaction in the budget, such as an expense.
 * Contains the amount spent and a description of the expense.
 */
public class Transaction {
    private final BigDecimal amount;
    private final String description;

    /**
     * Constructs a new Transaction with a specified amount and description.
     *
     * @param amount The amount of the expense.
     * @param description A description of the expense.
     */
    public Transaction(BigDecimal amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

}
