package dev.oblac.quarkus.tenant;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import io.quarkus.arc.Unremovable;
import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.customized.QuarkusConnectionProvider;
import io.quarkus.hibernate.orm.runtime.tenant.TenantConnectionResolver;
import io.quarkus.logging.Log;
import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

import static io.quarkus.arc.ComponentsProvider.LOG;
import static jakarta.interceptor.Interceptor.Priority.PLATFORM_AFTER;

@Alternative
@Priority(PLATFORM_AFTER)
@Singleton
@Transactional
@Default
@Unremovable
@PersistenceUnitExtension("tenants")
public class DbTenantConnectionResolver implements TenantConnectionResolver {
    @Inject
    AgroalDataSource defaultDataSource;
    @Inject
    @DataSource("tenants")
    AgroalDataSource tenantsDataSource;

    @Inject
    Tenants tenants;

    @Override
    public ConnectionProvider resolve(final String tenantId) {
        Log.debugv("DB Resolve ({0})", tenantId);

        if (tenantId.equals("default")) {
            return new QuarkusConnectionProvider(defaultDataSource);
        }

        final var tenantConfig = tenants.configFor(new TenantId(tenantId));
        return new SchemaTenantConnectionProvider(tenantConfig.schema(), tenantsDataSource);
    }
    private static class SchemaTenantConnectionProvider extends QuarkusConnectionProvider {

        private final String schema;

        public SchemaTenantConnectionProvider(final String tenantSchema, final AgroalDataSource dataSource) {
            super(dataSource);
            this.schema = tenantSchema;
        }

        @Override
        public Connection getConnection() throws SQLException {
            final Connection conn = super.getConnection();
            conn.setSchema(schema);
            LOG.debugv("Set tenant schema {0} for connection: {1}", schema, conn);
            return conn;
        }

    }

}
