package dev.oblac.quarkus.tenant;

import dev.oblac.quarkus.tenant.config.LoadTenantConfiguration;
import dev.oblac.quarkus.tenant.config.TenantConfig;
import io.quarkus.logging.Log;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class Tenants {
    private final Map<TenantId, TenantConfig> cfg = new HashMap<>();
    private Set<TenantId> allTenants;
    @Inject
    LoadTenantConfiguration loadTenantConfiguration;

    @PostConstruct
    void init() {
        loadTenantConfiguration.load(cfg);
        allTenants = Collections.unmodifiableSet(cfg.keySet());
        Log.info("Tenants loaded: " + cfg.size());
    }

    // ---------------------------------------------------------------- public

    public TenantId fromNameOrAlias(final String alias) {
        return this.existingTenantId(alias);
    }

    private TenantId existingTenantId(final String value) {
        final var tenantId = new TenantId(value);
        if (!cfg.containsKey(tenantId)) {
            throw new RuntimeException("Tenant id '" + value + "' does not exist");
        }
        return tenantId;
    }

    /**
     * Returns set of all enabled tenant ids.
     */
    public Set<TenantId> all() {
        return allTenants;
    }


    public TenantConfig configFor(final TenantId tenantId) {
        final var config = cfg.get(tenantId);
        if (config == null) {
            throw new RuntimeException("Tenant id " + tenantId + " not found in config");
        }
        return config;
    }
}
