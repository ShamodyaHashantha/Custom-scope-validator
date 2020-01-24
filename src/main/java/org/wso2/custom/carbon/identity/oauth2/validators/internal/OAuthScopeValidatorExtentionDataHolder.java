package org.wso2.custom.carbon.identity.oauth2.validators.internal;

import org.wso2.carbon.identity.entitlement.EntitlementService;

public class OAuthScopeValidatorExtentionDataHolder {
    private static OAuthScopeValidatorExtentionDataHolder instance = new OAuthScopeValidatorExtentionDataHolder();
    private EntitlementService entitlementService = null;

    public static OAuthScopeValidatorExtentionDataHolder getInstance() {

        return instance;
    }

    public EntitlementService getEntitlementService() {

        return entitlementService;
    }

    public void setEntitlementService(EntitlementService entitlementService) {

        this.entitlementService = entitlementService;
    }
}
