package dev.oblac.quarkus.tenant.config;

/**
 * Configuration value that holds tenant-related information.
 * This class is used to replace the {TENANT} placeholder in the value.
 * The value of the placeholder is replaced with the tenant name or default value.
 */
public record TenantString(String value) {
}
