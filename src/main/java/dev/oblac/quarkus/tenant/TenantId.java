package dev.oblac.quarkus.tenant;

import org.jetbrains.annotations.NotNull;

public record TenantId(String value) implements Comparable<TenantId> {
    @Override
    public int compareTo(@NotNull final TenantId o) {
        return this.value.compareTo(o.value);
    }
}
