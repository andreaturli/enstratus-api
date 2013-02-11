package com.enstratus.api.model;

import com.google.common.base.Objects;

public class Server {
    private String serverId;
    private Region region;
    private String budget;
    private Platform platform;
    private String startDate;
    private String stopDate;
    private Status status;
    private Datacenter dataCenter;
    private Customer customer;
    private MachineImage machineImage;
    private String description;;
    private String name;
    private String providerId;
    private Cloud cloud;
    private String providerProductId;
    private String agentVersion;
    public String[] privateIpAddresses;
    private String publicIpAddress;
    private User owningUser;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
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

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Datacenter getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(Datacenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public MachineImage getMachineImage() {
        return machineImage;
    }

    public void setMachineImage(MachineImage machineImage) {
        this.machineImage = machineImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProviderProductId() {
        return providerProductId;
    }

    public void setProviderProductId(String providerProductId) {
        this.providerProductId = providerProductId;
    }

    public String getAgentVersion() {
        return agentVersion;
    }

    public void setAgentVersion(String agentVersion) {
        this.agentVersion = agentVersion;
    } 
    
    public String[] getPrivateIpAddresses() {
        return privateIpAddresses;
    }
    
    public void setPrivateIpAddresses(String[] privateIpAddresses) {
        this.privateIpAddresses = privateIpAddresses;
    }
    
    public String getPublicIpAddress() {
        return publicIpAddress;
    }

    public void setPublicIpAddress(String publicIpAddress) {
        this.publicIpAddress = publicIpAddress;
    }

    public User getOwningUser() {
        return owningUser;
    }

    public void setOwningUser(User owningUser) {
        this.owningUser = owningUser;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", this.name)
                .add("description", this.description)
                .add("region", this.region)
                .add("status", this.status)
                .add("providerId", this.providerId)
                .toString();
    }
}
