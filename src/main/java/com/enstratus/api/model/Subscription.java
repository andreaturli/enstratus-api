package com.enstratus.api.model;

import com.google.common.base.Objects;

public class Subscription {

    private boolean subscribedPushNotification;
    private boolean privateImageSharing;
    private boolean publicImageSharing;
    private boolean subscribedNetwork;
    private boolean subscribedSnapshot;
    private boolean subscribedMachineImage;
    private boolean customImages;
    private boolean subscribedCDN;
    private boolean hypervisorAnalytics;
    private boolean subscribedFirewall;
    private boolean publicIPAddressesAssigned;
    private boolean networkWithSubnets;
    private boolean privateSnapshotSharing;
    private boolean rdbmsMaintenance;
    private boolean publicImageLibrary;
    private boolean privateIPAddressesAssigned;
    private boolean rdbmsLA;
    private boolean publicIPAddressesRequestable;
    private String regionId;
    private boolean serverKeyBootstrapped;
    private boolean subscribedVPN;
    private boolean subnetDataCenterConstrained;
    private boolean customNetworks;
    private boolean rdbmsHA;
    private boolean subscribedAutoScaling;
    private boolean subscribedEmail;
    private boolean loadBalancerCreateWithServer;
    private boolean networkDataCenterConstrained;
    private boolean rdbmsSnapshots;
    private boolean ipAddressesForwarded;
    private boolean subscribedDNS;
    private boolean subscribedRDBMS;
    private int maxLoadBalancerListeners;
    private boolean subscribedBlobStore;
    private boolean loadBalancerDataCenterLimited;
    private boolean rdbmsFirewalled;
    private boolean subscribedServer;
    private boolean subscribedVolume;
    private boolean subscribedMQ;
    private boolean loadBalancerAddressAssigned;
    private boolean subscribedStaticIP;
    private boolean privateIPAddressesRequestable;
    private boolean customSubnets;
    private boolean subscribedKVDB;
    private boolean subscribedLoadBalancer;
    
    public boolean isSubscribedPushNotification() {
        return subscribedPushNotification;
    }
    public void setSubscribedPushNotification(boolean subscribedPushNotification) {
        this.subscribedPushNotification = subscribedPushNotification;
    }
    public boolean isPrivateImageSharing() {
        return privateImageSharing;
    }
    public void setPrivateImageSharing(boolean privateImageSharing) {
        this.privateImageSharing = privateImageSharing;
    }
    public boolean isPublicImageSharing() {
        return publicImageSharing;
    }
    public void setPublicImageSharing(boolean publicImageSharing) {
        this.publicImageSharing = publicImageSharing;
    }
    public boolean isSubscribedNetwork() {
        return subscribedNetwork;
    }
    public void setSubscribedNetwork(boolean subscribedNetwork) {
        this.subscribedNetwork = subscribedNetwork;
    }
    public boolean isSubscribedSnapshot() {
        return subscribedSnapshot;
    }
    public void setSubscribedSnapshot(boolean subscribedSnapshot) {
        this.subscribedSnapshot = subscribedSnapshot;
    }
    public boolean isSubscribedMachineImage() {
        return subscribedMachineImage;
    }
    public void setSubscribedMachineImage(boolean subscribedMachineImage) {
        this.subscribedMachineImage = subscribedMachineImage;
    }
    public boolean isCustomImages() {
        return customImages;
    }
    public void setCustomImages(boolean customImages) {
        this.customImages = customImages;
    }
    public boolean isSubscribedCDN() {
        return subscribedCDN;
    }
    public void setSubscribedCDN(boolean subscribedCDN) {
        this.subscribedCDN = subscribedCDN;
    }
    public boolean isHypervisorAnalytics() {
        return hypervisorAnalytics;
    }
    public void setHypervisorAnalytics(boolean hypervisorAnalytics) {
        this.hypervisorAnalytics = hypervisorAnalytics;
    }
    public boolean isSubscribedFirewall() {
        return subscribedFirewall;
    }
    public void setSubscribedFirewall(boolean subscribedFirewall) {
        this.subscribedFirewall = subscribedFirewall;
    }
    public boolean isPublicIPAddressesAssigned() {
        return publicIPAddressesAssigned;
    }
    public void setPublicIPAddressesAssigned(boolean publicIPAddressesAssigned) {
        this.publicIPAddressesAssigned = publicIPAddressesAssigned;
    }
    public boolean isNetworkWithSubnets() {
        return networkWithSubnets;
    }
    public void setNetworkWithSubnets(boolean networkWithSubnets) {
        this.networkWithSubnets = networkWithSubnets;
    }
    public boolean isPrivateSnapshotSharing() {
        return privateSnapshotSharing;
    }
    public void setPrivateSnapshotSharing(boolean privateSnapshotSharing) {
        this.privateSnapshotSharing = privateSnapshotSharing;
    }
    public boolean isRdbmsMaintenance() {
        return rdbmsMaintenance;
    }
    public void setRdbmsMaintenance(boolean rdbmsMaintenance) {
        this.rdbmsMaintenance = rdbmsMaintenance;
    }
    public boolean isPublicImageLibrary() {
        return publicImageLibrary;
    }
    public void setPublicImageLibrary(boolean publicImageLibrary) {
        this.publicImageLibrary = publicImageLibrary;
    }
    public boolean isPrivateIPAddressesAssigned() {
        return privateIPAddressesAssigned;
    }
    public void setPrivateIPAddressesAssigned(boolean privateIPAddressesAssigned) {
        this.privateIPAddressesAssigned = privateIPAddressesAssigned;
    }
    public boolean isRdbmsLA() {
        return rdbmsLA;
    }
    public void setRdbmsLA(boolean rdbmsLA) {
        this.rdbmsLA = rdbmsLA;
    }
    public boolean isPublicIPAddressesRequestable() {
        return publicIPAddressesRequestable;
    }
    public void setPublicIPAddressesRequestable(boolean publicIPAddressesRequestable) {
        this.publicIPAddressesRequestable = publicIPAddressesRequestable;
    }
    public String getRegionId() {
        return regionId;
    }
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    public boolean isServerKeyBootstrapped() {
        return serverKeyBootstrapped;
    }
    public void setServerKeyBootstrapped(boolean serverKeyBootstrapped) {
        this.serverKeyBootstrapped = serverKeyBootstrapped;
    }
    public boolean isSubscribedVPN() {
        return subscribedVPN;
    }
    public void setSubscribedVPN(boolean subscribedVPN) {
        this.subscribedVPN = subscribedVPN;
    }
    public boolean isSubnetDataCenterConstrained() {
        return subnetDataCenterConstrained;
    }
    public void setSubnetDataCenterConstrained(boolean subnetDataCenterConstrained) {
        this.subnetDataCenterConstrained = subnetDataCenterConstrained;
    }
    public boolean isCustomNetworks() {
        return customNetworks;
    }
    public void setCustomNetworks(boolean customNetworks) {
        this.customNetworks = customNetworks;
    }
    public boolean isRdbmsHA() {
        return rdbmsHA;
    }
    public void setRdbmsHA(boolean rdbmsHA) {
        this.rdbmsHA = rdbmsHA;
    }
    public boolean isSubscribedAutoScaling() {
        return subscribedAutoScaling;
    }
    public void setSubscribedAutoScaling(boolean subscribedAutoScaling) {
        this.subscribedAutoScaling = subscribedAutoScaling;
    }
    public boolean isSubscribedEmail() {
        return subscribedEmail;
    }
    public void setSubscribedEmail(boolean subscribedEmail) {
        this.subscribedEmail = subscribedEmail;
    }
    public boolean isLoadBalancerCreateWithServer() {
        return loadBalancerCreateWithServer;
    }
    public void setLoadBalancerCreateWithServer(boolean loadBalancerCreateWithServer) {
        this.loadBalancerCreateWithServer = loadBalancerCreateWithServer;
    }
    public boolean isNetworkDataCenterConstrained() {
        return networkDataCenterConstrained;
    }
    public void setNetworkDataCenterConstrained(boolean networkDataCenterConstrained) {
        this.networkDataCenterConstrained = networkDataCenterConstrained;
    }
    public boolean isRdbmsSnapshots() {
        return rdbmsSnapshots;
    }
    public void setRdbmsSnapshots(boolean rdbmsSnapshots) {
        this.rdbmsSnapshots = rdbmsSnapshots;
    }
    public boolean isIpAddressesForwarded() {
        return ipAddressesForwarded;
    }
    public void setIpAddressesForwarded(boolean ipAddressesForwarded) {
        this.ipAddressesForwarded = ipAddressesForwarded;
    }
    public boolean isSubscribedDNS() {
        return subscribedDNS;
    }
    public void setSubscribedDNS(boolean subscribedDNS) {
        this.subscribedDNS = subscribedDNS;
    }
    public boolean isSubscribedRDBMS() {
        return subscribedRDBMS;
    }
    public void setSubscribedRDBMS(boolean subscribedRDBMS) {
        this.subscribedRDBMS = subscribedRDBMS;
    }
    public int getMaxLoadBalancerListeners() {
        return maxLoadBalancerListeners;
    }
    public void setMaxLoadBalancerListeners(int maxLoadBalancerListeners) {
        this.maxLoadBalancerListeners = maxLoadBalancerListeners;
    }
    public boolean isSubscribedBlobStore() {
        return subscribedBlobStore;
    }
    public void setSubscribedBlobStore(boolean subscribedBlobStore) {
        this.subscribedBlobStore = subscribedBlobStore;
    }
    public boolean isLoadBalancerDataCenterLimited() {
        return loadBalancerDataCenterLimited;
    }
    public void setLoadBalancerDataCenterLimited(boolean loadBalancerDataCenterLimited) {
        this.loadBalancerDataCenterLimited = loadBalancerDataCenterLimited;
    }
    public boolean isRdbmsFirewalled() {
        return rdbmsFirewalled;
    }
    public void setRdbmsFirewalled(boolean rdbmsFirewalled) {
        this.rdbmsFirewalled = rdbmsFirewalled;
    }
    public boolean isSubscribedServer() {
        return subscribedServer;
    }
    public void setSubscribedServer(boolean subscribedServer) {
        this.subscribedServer = subscribedServer;
    }
    public boolean isSubscribedVolume() {
        return subscribedVolume;
    }
    public void setSubscribedVolume(boolean subscribedVolume) {
        this.subscribedVolume = subscribedVolume;
    }
    public boolean isSubscribedMQ() {
        return subscribedMQ;
    }
    public void setSubscribedMQ(boolean subscribedMQ) {
        this.subscribedMQ = subscribedMQ;
    }
    public boolean isLoadBalancerAddressAssigned() {
        return loadBalancerAddressAssigned;
    }
    public void setLoadBalancerAddressAssigned(boolean loadBalancerAddressAssigned) {
        this.loadBalancerAddressAssigned = loadBalancerAddressAssigned;
    }
    public boolean isSubscribedStaticIP() {
        return subscribedStaticIP;
    }
    public void setSubscribedStaticIP(boolean subscribedStaticIP) {
        this.subscribedStaticIP = subscribedStaticIP;
    }
    public boolean isPrivateIPAddressesRequestable() {
        return privateIPAddressesRequestable;
    }
    public void setPrivateIPAddressesRequestable(boolean privateIPAddressesRequestable) {
        this.privateIPAddressesRequestable = privateIPAddressesRequestable;
    }
    public boolean isCustomSubnets() {
        return customSubnets;
    }
    public void setCustomSubnets(boolean customSubnets) {
        this.customSubnets = customSubnets;
    }
    public boolean isSubscribedKVDB() {
        return subscribedKVDB;
    }
    public void setSubscribedKVDB(boolean subscribedKVDB) {
        this.subscribedKVDB = subscribedKVDB;
    }
    public boolean isSubscribedLoadBalancer() {
        return subscribedLoadBalancer;
    }
    public void setSubscribedLoadBalancer(boolean subscribedLoadBalancer) {
        this.subscribedLoadBalancer = subscribedLoadBalancer;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("regionId", this.regionId)
                .toString();
    }
}