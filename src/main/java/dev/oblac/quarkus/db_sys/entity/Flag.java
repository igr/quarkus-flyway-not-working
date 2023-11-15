package dev.oblac.quarkus.db_sys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
public class Flag {

    @Id
    @NotNull
    @Column(nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "a_log", nullable = false)
    private boolean aLog;

}
