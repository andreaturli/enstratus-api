package com.enstratus.api.model;

import com.google.common.base.Objects;

public class MachineImage {

    private String name;
    private String description;
    private Region region;
    private String budget;
    private String platfrom;
    private Status status;
    private boolean removable;
    private String owningCloudAccountNumber;
    private String architecture;
    private Customer customer;
    private Cloud cloud;
    private String machineImageId;
    private String providerId;
    private String creationTimestamp;
    private OwningAccount owningAccount;
    private Platform platform;
    private boolean sharable;

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

    public String getPlatfrom() {
        return platfrom;
    }

    public void setPlatfrom(String platfrom) {
        this.platfrom = platfrom;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public String getOwningCloudAccountNumber() {
        return owningCloudAccountNumber;
    }

    public void setOwningCloudAccountNumber(String owningCloudAccountNumber) {
        this.owningCloudAccountNumber = owningCloudAccountNumber;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }

    public String getMachineImageId() {
        return machineImageId;
    }

    public void setMachineImageId(String machineImageId) {
        this.machineImageId = machineImageId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(String creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public OwningAccount getOwningAccount() {
        return owningAccount;
    }

    public void setOwningAccount(OwningAccount owningAccount) {
        this.owningAccount = owningAccount;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public boolean isSharable() {
        return sharable;
    }

    public void setSharable(boolean sharable) {
        this.sharable = sharable;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", this.name)
                .add("description", this.description)
                .add("region", this.region)
                .add("status", this.status)
                .add("providerId", this.providerId)
                .add("machineImageId", this.machineImageId)
                .toString();
    }
}
