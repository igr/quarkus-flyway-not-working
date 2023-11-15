package dev.oblac.quarkus.tenant;

/**
 * Thread-local storage for the {@link Ctx tenant context}.
 */
public class TenantContext {

    private static final ThreadLocal<Ctx> currentTenant = new InheritableThreadLocal<>();

    public static Ctx ctx() {
        return currentTenant.get();
    }

    public static void ctx(final Ctx ctx) {
        currentTenant.set(ctx);
    }

    public static void clear() {
        currentTenant.remove();
    }
}
