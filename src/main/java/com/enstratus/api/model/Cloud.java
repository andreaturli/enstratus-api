package com.enstratus.api.model;

import com.google.common.base.Objects;

public class Cloud {

    public enum Status {
        ACTIVE, INACTIVE
    };

    private String cloudId;
    private Status status;
    private String computeX509KeyLabel;
    private String computeSecretKeyLabel;
    private String computeX509CertLabel;
    private String computeAccessKeyLabel;
    private String computeAccountNumberLabel;
    private String documentationLabel;
    private String name;
    private String cloudProviderConsoleURL;
    private String cloudProviderLogoURL;
    private String computeDelegate;
    private String computeEndpoint;
    private String cloudProviderName;
    private boolean privateCloud;

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComputeX509KeyLabel() {
        return computeX509KeyLabel;
    }

    public void setComputeX509KeyLabel(String computeX509KeyLabel) {
        this.computeX509KeyLabel = computeX509KeyLabel;
    }

    public String getComputeSecretKeyLabel() {
        return computeSecretKeyLabel;
    }

    public void setComputeSecretKeyLabel(String computeSecretKeyLabel) {
        this.computeSecretKeyLabel = computeSecretKeyLabel;
    }

    public String getComputeX509CertLabel() {
        return computeX509CertLabel;
    }

    public void setComputeX509CertLabel(String computeX509CertLabel) {
        this.computeX509CertLabel = computeX509CertLabel;
    }

    public String getComputeAccessKeyLabel() {
        return computeAccessKeyLabel;
    }

    public void setComputeAccessKeyLabel(String computeAccessKeyLabel) {
        this.computeAccessKeyLabel = computeAccessKeyLabel;
    }

    public String getComputeAccountNumberLabel() {
        return computeAccountNumberLabel;
    }

    public void setComputeAccountNumberLabel(String computeAccountNumberLabel) {
        this.computeAccountNumberLabel = computeAccountNumberLabel;
    }

    public String getDocumentationLabel() {
        return documentationLabel;
    }

    public void setDocumentationLabel(String documentationLabel) {
        this.documentationLabel = documentationLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCloudProviderConsoleURL() {
        return cloudProviderConsoleURL;
    }

    public void setCloudProviderConsoleURL(String cloudProviderConsoleURL) {
        this.cloudProviderConsoleURL = cloudProviderConsoleURL;
    }

    public String getCloudProviderLogoURL() {
        return cloudProviderLogoURL;
    }

    public void setCloudProviderLogoURL(String cloudProviderLogoURL) {
        this.cloudProviderLogoURL = cloudProviderLogoURL;
    }

    public String getComputeDelegate() {
        return computeDelegate;
    }

    public void setComputeDelegate(String computeDelegate) {
        this.computeDelegate = computeDelegate;
    }

    public String getComputeEndpoint() {
        return computeEndpoint;
    }

    public void setComputeEndpoint(String computeEndpoint) {
        this.computeEndpoint = computeEndpoint;
    }

    public String getCloudProviderName() {
        return cloudProviderName;
    }

    public void setCloudProviderName(String cloudProviderName) {
        this.cloudProviderName = cloudProviderName;
    }

    public boolean isPrivateCloud() {
        return privateCloud;
    }

    public void setPrivateCloud(boolean privateCloud) {
        this.privateCloud = privateCloud;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("cloudId", this.cloudId)
                .add("name", this.name)               
                .add("status", this.status)
                .add("computeEndpoint", this.computeEndpoint)
                .add("cloudProviderName", this.cloudProviderName)
                .toString();
    }

}
