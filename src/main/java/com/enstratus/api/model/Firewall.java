package com.enstratus.api.model;

import com.google.common.base.Objects;

public class Firewall {

    private String name;
    private String description;
    private String firewallId;
    private Status status;
    private Region region;
    private String budget;
    private Color label;
    private OwningAccount owningAccount;
    private String providerId;
    private Cloud cloud;
    private boolean removable;
    private Customer customer;
    private User owningUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirewallId() {
        return firewallId;
    }

    public void setFirewallId(String firewallId) {
        this.firewallId = firewallId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public Color getLabel() {
        return label;
    }

    public void setLabel(Color label) {
        this.label = label;
    }

    public OwningAccount getOwningAccount() {
        return owningAccount;
    }

    public void setOwningAccount(OwningAccount owningAccount) {
        this.owningAccount = owningAccount;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getOwningUser() {
        return owningUser;
    }

    public void setOwningUser(User owningUser) {
        this.owningUser = owningUser;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("description", this.description)
                .add("region", this.region).add("providerId", this.providerId).add("status", this.status).toString();
    }
}
