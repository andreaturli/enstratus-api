package com.enstratus.api.model;

import com.google.common.base.Objects;

public class ServerProduct {

    private int cpuCount;
    private Platform platform;
    private String hourlyRate;
    private String providerRegionId;
    private String ramInMb;
    private String architecture;
    private String cpuSpeedInMhz;
    private String productId;
    private String currency;
    private String software;
    private String diskSizeInGb;
    private String description;
    private String name;
    private Cloud cloud;
    private String providerProductId;
    public int getCpuCount() {
        return cpuCount;
    }
    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }
    public Platform getPlatform() {
        return platform;
    }
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
    public String getHourlyRate() {
        return hourlyRate;
    }
    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    public String getProviderRegionId() {
        return providerRegionId;
    }
    public void setProviderRegionId(String providerRegionId) {
        this.providerRegionId = providerRegionId;
    }
    public String getRamInMb() {
        return ramInMb;
    }
    public void setRamInMb(String ramInMb) {
        this.ramInMb = ramInMb;
    }
    public String getArchitecture() {
        return architecture;
    }
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }
    public String getCpuSpeedInMhz() {
        return cpuSpeedInMhz;
    }
    public void setCpuSpeedInMhz(String cpuSpeedInMhz) {
        this.cpuSpeedInMhz = cpuSpeedInMhz;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getSoftware() {
        return software;
    }
    public void setSoftware(String software) {
        this.software = software;
    }
    public String getDiskSizeInGb() {
        return diskSizeInGb;
    }
    public void setDiskSizeInGb(String diskSizeInGb) {
        this.diskSizeInGb = diskSizeInGb;
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
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", this.name)
                .add("description", this.description)
                .add("platform", this.platform)
                .add("cloud", this.cloud)
                .add("providerProductId", this.providerProductId)
                .toString();
    }
    
}