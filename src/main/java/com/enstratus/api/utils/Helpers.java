package com.enstratus.api.utils;

import static com.google.common.collect.Iterables.tryFind;

import com.enstratus.api.features.AdminApi;
import com.enstratus.api.features.GeographyApi;
import com.enstratus.api.features.InfrastructureApi;
import com.enstratus.api.features.NetworkApi;
import com.enstratus.api.model.BillingCode;
import com.enstratus.api.model.Datacenter;
import com.enstratus.api.model.Firewall;
import com.enstratus.api.model.Jurisdiction;
import com.enstratus.api.model.MachineImage;
import com.enstratus.api.model.Region;
import com.google.common.base.Predicates;

public class Helpers {

    public static Region tryFindRegionOrNull(Jurisdiction jurisdiction, GeographyApi geographyApi) {
            return tryFind(geographyApi.listRegions(null, jurisdiction, null),
                    Predicates.notNull()).orNull();
    }

    public static BillingCode tryFindBillingCodeOrNull(String regionId, AdminApi adminApi) {
            return tryFind(adminApi.listBillingCodes(regionId), Predicates.notNull()).orNull();
    }

    public static Datacenter tryFindDatacenterOrNull(String regionId, GeographyApi geographyApi) {
            return tryFind(geographyApi.listDatacenters(regionId), Predicates.notNull()).orNull();
    }

    public static MachineImage tryFindMachineImageOrNull(String regionId, InfrastructureApi infrastructureApi) {
            return tryFind(infrastructureApi.listMachineImages(regionId), Predicates.notNull())
                    .orNull();
    }

    public static Firewall tryFindFirewallOrNull(String regionId, NetworkApi networkApi) {
            return tryFind(networkApi.listFirewalls(regionId), Predicates.notNull()).orNull();
    }
}
