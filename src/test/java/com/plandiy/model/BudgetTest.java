package com.plandiy.model;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    @Test
    void testRemainingAmount() {
        Budget budget = new Budget(new BigDecimal("1000.00"));
        budget.addExpense(new BigDecimal("200.50"));

        assertEquals(new BigDecimal("799.50"), budget.getRemainingAmount());
    }

    @Test
    void testAddMultipleExpenses() {
        Budget budget = new Budget(new BigDecimal("1000.00"));
        budget.addExpense(new BigDecimal("300.00"));
        budget.addExpense(new BigDecimal("150.75"));

        assertEquals(new BigDecimal("549.25"), budget.getRemainingAmount());
    }
}

