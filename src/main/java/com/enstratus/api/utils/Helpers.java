package com.enstratus.api.utils;

import static com.google.common.collect.Iterables.tryFind;

import com.enstratus.api.EnstratusAPI;
import com.enstratus.api.model.BillingCode;
import com.enstratus.api.model.Datacenter;
import com.enstratus.api.model.Firewall;
import com.enstratus.api.model.Jurisdiction;
import com.enstratus.api.model.MachineImage;
import com.enstratus.api.model.Region;
import com.google.common.base.Predicates;

public class Helpers {

    public static Region tryFindRegionOrNull(Jurisdiction jurisdiction) {
            return tryFind(EnstratusAPI.getGeographyApi().listRegions(null, jurisdiction, null),
                    Predicates.notNull()).orNull();
    }

    public static BillingCode tryFindBillingCodeOrNull(String regionId) {
            return tryFind(EnstratusAPI.getAdminApi().listBillingCodes(regionId), Predicates.notNull()).orNull();

    }

    public static Datacenter tryFindDatacenterOrNull(String regionId) {
            return tryFind(EnstratusAPI.getGeographyApi().listDatacenters(regionId), Predicates.notNull()).orNull();
    }

    public static MachineImage tryFindMachineImageOrNull(String regionId) {
            return tryFind(EnstratusAPI.getInfrastructureApi().listMachineImages(regionId), Predicates.notNull())
                    .orNull();
    }

    public static Firewall tryFindFirewallOrNull(String regionId) {
            return tryFind(EnstratusAPI.getNetworkApi().listFirewalls(regionId), Predicates.notNull()).orNull();
    }
}
