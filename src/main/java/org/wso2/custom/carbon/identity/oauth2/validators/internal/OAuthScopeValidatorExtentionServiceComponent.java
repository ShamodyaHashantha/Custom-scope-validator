package org.wso2.custom.carbon.identity.oauth2.validators.internal;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.wso2.carbon.identity.entitlement.EntitlementService;
import org.wso2.carbon.identity.oauth2.validators.OAuth2ScopeValidator;
import org.wso2.custom.carbon.identity.oauth2.validators.OAuthScopeValidatorExtention;

@Component(
        name = "identity.inbound.auth.scope.validators.component",
        immediate = true
)
public class OAuthScopeValidatorExtentionServiceComponent {
    private static final Log log = LogFactory.getLog(OAuthScopeValidatorExtentionServiceComponent.class);

    @SuppressWarnings("unchecked")
    @Activate
    protected void activate(ComponentContext context) {

        try {
            OAuthScopeValidatorExtention oAuthScopeValidatorExtention =
                    OAuthScopeValidatorExtention.getInstance();
            context.getBundleContext().registerService(OAuth2ScopeValidator.class.getName(),
                    oAuthScopeValidatorExtention, null);

        } catch (Throwable e) {
            log.error("Error while activating oAuthScopeValidatorExtention.", e);
        }
    }

    @Reference(
            name = "identity.entitlement.service",
            service = EntitlementService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetEntitlementService"
    )
    protected void setEntitlementService(EntitlementService entitlementService) {

        if (log.isDebugEnabled()) {
            log.debug("EntitlementService is set in the extended scope validator bundle.");
        }
        OAuthScopeValidatorExtentionDataHolder.getInstance().setEntitlementService(entitlementService);
    }

    protected void unsetEntitlementService(EntitlementService entitlementService) {

        if (log.isDebugEnabled()) {
            log.debug("EntitlementService is unset in the extended scope validator bundle.");
        }
        OAuthScopeValidatorExtentionDataHolder.getInstance().setEntitlementService(null);
    }
}
