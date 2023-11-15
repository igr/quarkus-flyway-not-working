package dev.oblac.quarkus.db.repository;

import dev.oblac.quarkus.db.entity.SyncError;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class SyncErrorRepository implements PanacheRepositoryBase<SyncError, Long> {
    public Optional<SyncError> findSyncErrorForTask(final UUID taskId) {
        return find("from SyncError s where s.taskUuid = :taskUuid", Parameters.with("taskUuid", taskId))
            .list()
            .stream()
            .findFirst();
    }
}
