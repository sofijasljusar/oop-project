package com.plandiy.model;

public class Budget {
    private double totalBudget;
    private double expenses;

    public double getRemainingAmount() {
        return totalBudget - expenses;
    }

    public void addExpense(double expense) {
        expenses += expense;
    }

    public void checkBalance() {}  // i think same as getRemainingAmount

    public void generateFinancialReport() {}



}
