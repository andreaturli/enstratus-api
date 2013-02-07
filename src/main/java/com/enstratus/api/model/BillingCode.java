package com.enstratus.api.model;

import com.google.common.base.Objects;

public class BillingCode {
    private String financeCode;
    private String budgetState;
    private String status;
    private String description;
    private String billingCodeId;
    private String name;
    private ProjectedUsage projectedUsage;
    private Customer customer;
    private String currency;
    private CurrentUsage currentUsage;

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public String getBudgetState() {
        return budgetState;
    }

    public void setBudgetState(String budgetState) {
        this.budgetState = budgetState;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBillingCodeId() {
        return billingCodeId;
    }

    public void setBillingCodeId(String billingCodeId) {
        this.billingCodeId = billingCodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectedUsage getProjectedUsage() {
        return projectedUsage;
    }

    public void setProjectedUsage(ProjectedUsage projectedUsage) {
        this.projectedUsage = projectedUsage;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CurrentUsage getCurrentUsage() {
        return currentUsage;
    }

    public void setCurrentUsage(CurrentUsage currentUsage) {
        this.currentUsage = currentUsage;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("description", this.description).add("name", this.name)
                .add("projectedUsage", this.projectedUsage).add("customer", this.customer)
                .add("currency", this.currency).toString();
    }
}
