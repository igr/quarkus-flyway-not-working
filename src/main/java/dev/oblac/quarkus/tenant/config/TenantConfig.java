package dev.oblac.quarkus.tenant.config;

import java.util.Optional;

public record TenantConfig(
    String name,
    String schema
) {

}

