package dgu.triple.aeterna.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

 
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "user_body_log")
public class UserBodyLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "measured_at", nullable = false)
    private LocalDateTime measuredAt;

    @Column(name = "weight_kg", nullable = false)
    private Integer weight;

    @Column(name = "body_fat_pct")
    private Integer bodyFatPct;

    @Column(name = "muscle_mass_kg")
    private Integer muscleMassKg;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public UserBodyLog(
            User user,
            LocalDateTime measuredAt,
            Integer weight,
            Integer bodyFatPct,
            Integer muscleMassKg
    ) {
        this.user = user;
        this.measuredAt = measuredAt;
        this.weight = weight;
        this.bodyFatPct = bodyFatPct;
        this.muscleMassKg = muscleMassKg;
    }
}
