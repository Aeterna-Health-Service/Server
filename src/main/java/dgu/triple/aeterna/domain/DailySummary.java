package dgu.triple.aeterna.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

 
import java.time.LocalDate;

@Entity
@Getter
@RequiredArgsConstructor
@DynamicUpdate
@Table(name = "daily_summary")
public class DailySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "intake_kcal", nullable = false)
    private Integer intakeKcal;

    @Column(name = "intake_carb", nullable = false)
    private Integer intakeCarb;

    @Column(name = "intake_protein", nullable = false)
    private Integer intakeProtein;

    @Column(name = "intake_fat", nullable = false)
    private Integer intakeFat;

    @Column(name = "burned_kcal", nullable = false)
    private Integer burnedKcal;

    @Column(name = "steps", nullable = false)
    private Integer steps;

    @Column(name = "weight_kg")
    private Integer weight;

    @Column(name = "water_ml", nullable = false)
    private Integer water;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    public DailySummary(User user) {
        this.user = user;
        this.intakeKcal = 0;
        this.intakeCarb = 0;
        this.intakeProtein = 0;
        this.intakeFat = 0;
        this.burnedKcal = 0;
        this.steps = 0;
        this.weight = 0;
        this.water = 0;
    }
}
