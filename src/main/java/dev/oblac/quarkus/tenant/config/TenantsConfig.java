package dev.oblac.quarkus.tenant.config;

import dev.oblac.quarkus.tenant.TenantId;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

import java.util.Map;
import java.util.Optional;

/**
 * All tenants configuration. Used just for the purpose of loading all tenants.
 */
@ConfigMapping(prefix = "tenants")
public interface TenantsConfig {

    @WithName("schemas")
    String[] schemas();

    @WithName("config")
    Map<TenantId, SingleConfig> tenants();

    interface SingleConfig {

        /**
         * Mandatory name, used for display purposes only.
         */
        @WithName("name")
        String name();

        @WithName("schema")
        String schema();

        @WithName("enabled")
        Boolean enabled();

        /**
         * Optional alternative alias for the tenant id.
         */
        @WithName("alias")
        Optional<String> alias();

    }

}
