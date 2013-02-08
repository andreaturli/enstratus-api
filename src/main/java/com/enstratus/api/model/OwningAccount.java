package com.enstratus.api.model;

import com.google.common.base.Objects;

public class OwningAccount {
    
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("accountId", this.accountId)
                .toString();
    }
}
