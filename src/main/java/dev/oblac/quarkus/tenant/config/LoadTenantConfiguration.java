package dev.oblac.quarkus.tenant.config;

import dev.oblac.quarkus.tenant.TenantId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class LoadTenantConfiguration {

    @Inject
    TenantsConfig allConfigs;

    public void load(final Map<TenantId, TenantConfig> cfg) {
        allConfigs.tenants().forEach((tenantId, tenantConfig) -> {
            final var enabled = tenantConfig.enabled();
            if (!enabled) {
                return;
            }
            cfg.put(tenantId, new TenantConfig(
                tenantConfig.name(),
                tenantConfig.schema()
            ));
        });
        allConfigs = null;
    }


}
