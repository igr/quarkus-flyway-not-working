package dev.oblac.quarkus.tenant;

import dev.oblac.quarkus.security.AuthenticationContext;

/**
 * Running context containing all relevant information.
 */
public class Ctx {

    private final TenantId tenantId;

    Ctx(final TenantId tenantId) {
        this.tenantId = tenantId;
    }

    public static Ctx of(final Tenants tenants, final AuthenticationContext auth) {
        if (auth.getTenantId() != null) {
            final var tenantIdFromRequest = tenants.fromNameOrAlias(auth.getTenantId());
            if (tenantIdFromRequest != null) {
                final var ctx = new Ctx(tenantIdFromRequest);
                // only now we can set the tenant context!
                TenantContext.ctx(ctx);
                return ctx;
            }
        }
        throw new RuntimeException("Tenant not specified in the request.");
    }

    public TenantId tenantId() {
        return tenantId;
    }

    /**
     * Creates a context for the given tenant.
     * Should be used in pair with `release` method.
     */
    public static void runWith(final TenantId tenantId, final CtxRunnable runnable) {
        final var ctx = new Ctx(tenantId);
        // only now we can set the tenant context!
        TenantContext.ctx(ctx);
        try {
            runnable.run(ctx);
        } finally {
            TenantContext.clear();
        }
    }
    public static <T> T callWith(final TenantId tenantId, final CtxCallable<T> callable) {
        final var ctx = new Ctx(tenantId);
        // only now we can set the tenant context!
        TenantContext.ctx(ctx);
        try {
            return callable.call(ctx);
        } finally {
            TenantContext.clear();
        }
    }

    @FunctionalInterface
    public interface CtxRunnable {
        void run(final Ctx ctx);
    }
    @FunctionalInterface
    public interface CtxCallable<T> {
        T call(final Ctx ctx);
    }
}
