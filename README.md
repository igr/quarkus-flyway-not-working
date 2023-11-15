# quarkus-flyway-not-working

This is a simple project to demonstrate that Quarkus 3.5.1 is not running Flyway migrations on all schemas.

See: https://github.com/quarkusio/quarkus/issues/37080

## Datasource: `default` ✅

This is a default datasource, that has just one schema: `wiresui`.
Flyway _creates_ this schema and _runs_ migrations.

## Datasource: `tenants` ❌

This is tenants datasources, that has two schemas: `foo` and `bar`.
Flyway _creates_ schemas, but runs migration on the first schema only.

Here is the relevant output of the application:

```
2023-11-15 17:28:58,945 INFO  [io.qua.dat.dep.dev.DevServicesDatasourceProcessor] (build-15) Dev Services for default datasource (postgresql) started - container ID is 10edc154170d
2023-11-15 17:28:59,817 INFO  [org.fly.cor.int.lic.VersionPrinter] (Quarkus Main Thread) Flyway Community Edition 9.22.2 by Redgate
2023-11-15 17:28:59,818 INFO  [org.fly.cor.int.lic.VersionPrinter] (Quarkus Main Thread) See release notes here: https://rd.gt/416ObMi
2023-11-15 17:28:59,818 INFO  [org.fly.cor.int.lic.VersionPrinter] (Quarkus Main Thread) 
2023-11-15 17:28:59,822 INFO  [org.fly.cor.FlywayExecutor] (Quarkus Main Thread) Database: jdbc:postgresql://localhost:5439/quarkus (PostgreSQL 14.9)
2023-11-15 17:28:59,836 INFO  [org.fly.cor.int.dat.bas.Schema] (Quarkus Main Thread) Creating schema "tenant_foo" ...
2023-11-15 17:28:59,837 INFO  [org.fly.cor.int.dat.bas.Schema] (Quarkus Main Thread) Creating schema "tenant_bar" ...
2023-11-15 17:28:59,841 INFO  [org.fly.cor.int.sch.JdbcTableSchemaHistory] (Quarkus Main Thread) Creating Schema History table "tenant_foo"."flyway_schema_history" ...
2023-11-15 17:28:59,888 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Current version of schema "tenant_foo": null
2023-11-15 17:28:59,890 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Migrating schema "tenant_foo" to version "1.0.0 - initial"
2023-11-15 17:28:59,898 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Successfully applied 1 migration to schema "tenant_foo", now at version v1.0.0 (execution time 00:00.004s)
2023-11-15 17:28:59,917 INFO  [org.fly.cor.FlywayExecutor] (Quarkus Main Thread) Database: jdbc:postgresql://localhost:5439/quarkus (PostgreSQL 14.9)
2023-11-15 17:28:59,921 INFO  [org.fly.cor.int.sch.JdbcTableSchemaHistory] (Quarkus Main Thread) Repair of failed migration in Schema History table "wiresui"."flyway_schema_history" not necessary as table doesn't exist.
2023-11-15 17:28:59,923 INFO  [org.fly.cor.int.sch.JdbcTableSchemaHistory] (Quarkus Main Thread) Schema history table "wiresui"."flyway_schema_history" does not exist yet
2023-11-15 17:28:59,924 INFO  [org.fly.cor.int.com.DbRepair] (Quarkus Main Thread) Successfully repaired schema history table "wiresui"."flyway_schema_history" (execution time 00:00.005s).
2023-11-15 17:28:59,944 INFO  [org.fly.cor.int.dat.bas.Schema] (Quarkus Main Thread) Creating schema "wiresui" ...
2023-11-15 17:28:59,946 INFO  [org.fly.cor.int.sch.JdbcTableSchemaHistory] (Quarkus Main Thread) Creating Schema History table "wiresui"."flyway_schema_history" ...
2023-11-15 17:28:59,955 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Current version of schema "wiresui": null
2023-11-15 17:28:59,960 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Migrating schema "wiresui" to version "1.0.0 - quartz"
2023-11-15 17:28:59,961 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_fired_triggers" does not exist, skipping
2023-11-15 17:28:59,962 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_paused_trigger_grps" does not exist, skipping
2023-11-15 17:28:59,962 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_scheduler_state" does not exist, skipping
2023-11-15 17:28:59,963 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_locks" does not exist, skipping
2023-11-15 17:28:59,963 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_simple_triggers" does not exist, skipping
2023-11-15 17:28:59,964 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_cron_triggers" does not exist, skipping
2023-11-15 17:28:59,964 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_simprop_triggers" does not exist, skipping
2023-11-15 17:28:59,964 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_blob_triggers" does not exist, skipping
2023-11-15 17:28:59,965 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_triggers" does not exist, skipping
2023-11-15 17:28:59,965 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_job_details" does not exist, skipping
2023-11-15 17:28:59,966 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "qrtz_calendars" does not exist, skipping
2023-11-15 17:28:59,966 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: table "tasks" does not exist, skipping
2023-11-15 17:28:59,967 INFO  [org.fly.cor.int.sql.DefaultSqlScriptExecutor] (Quarkus Main Thread) DB: sequence "hibernate_sequence" does not exist, skipping
2023-11-15 17:28:59,997 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Migrating schema "wiresui" to version "1.0.1 - flags"
2023-11-15 17:29:00,002 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Successfully applied 2 migrations to schema "wiresui", now at version v1.0.1 (execution time 00:00.035s)
```

As you can see, Flyway migrates schemas `wiresui` for the default datasource and `tenant_foo` for the tenants datasource, but not `tenant_bar`.
