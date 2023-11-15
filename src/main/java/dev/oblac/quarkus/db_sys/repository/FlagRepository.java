package dev.oblac.quarkus.db_sys.repository;

import dev.oblac.quarkus.db_sys.entity.Flag;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@ApplicationScoped
public class FlagRepository implements PanacheRepositoryBase<Flag, Integer> {

    private static final Integer ID = 1;

    public Flag flags() {
        return this.findById(ID);
    }
}
