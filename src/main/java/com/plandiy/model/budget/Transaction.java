package com.plandiy.model.budget;

import java.math.BigDecimal;

public class Transaction {
    private BigDecimal amount;
    private String description;

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
