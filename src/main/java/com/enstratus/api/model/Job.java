package com.enstratus.api.model;

import com.google.common.base.Objects;

public class Job {

    private String startDate;
    private String endDate;
    private String jobId;
    private String description;
    private String message;
    private Status status;
    
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("jobId", this.jobId)               
                .add("description", this.description)
                .add("message", this.message)
                .add("status", this.status)
                .add("startDate", this.startDate)
                .toString();
    }
}
