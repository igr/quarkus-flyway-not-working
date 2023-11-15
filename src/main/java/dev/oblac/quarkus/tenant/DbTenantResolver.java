package dev.oblac.quarkus.tenant;

import io.quarkus.arc.Unremovable;
import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@Unremovable
@PersistenceUnitExtension("tenants")
public class DbTenantResolver implements TenantResolver {

    @Override
    public String getDefaultTenantId() {
        return "default";
    }

    @Override
    public String resolveTenantId() {
        final var ctx = TenantContext.ctx();
        if (ctx == null) {
            throw new RuntimeException("Tenant not specified in the thread-local context for DB access.");
        }
	    return ctx.tenantId().value();
    }
}
