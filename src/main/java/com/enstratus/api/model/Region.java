package com.enstratus.api.model;

import com.google.common.base.Objects;

public class Region {
    
    private String regionId;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("regionId", this.regionId)
                .toString();
    }
}
