package com.enstratus.api.model;

import com.google.common.base.Objects;

public class Datacenter {
    
    /*
    * {region":{"regionId":20827},"status":"ACTIVE","description":"eu-west-1a","name":"eu-west-1a","dataCenterId":66634,"providerId":"eu-west-1a"},
    */
    public enum Status { ACTIVE };

    private Status status;
    private String description;
    private String name;
    private String dataCenterId;
    private String providerId;
    
    private Region region;
    
    public Region getRegion() {
        return region;
    }
    public void setRegion(Region region) {
        this.region = region;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
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
    public String getDataCenterId() {
        return dataCenterId;
    }
    public void setDataCenterId(String dataCenterId) {
        this.dataCenterId = dataCenterId;
    }
    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", this.name)               
                .add("description", this.description)
                .add("status", this.status)
                .add("region", this.region)
                .add("dataCenterId", this.dataCenterId)
                .add("providerId", this.providerId)
                .toString();
    }
 
}
