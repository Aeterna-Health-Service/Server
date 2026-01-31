package dgu.triple.aeterna.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "meal_log")
public class MealLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;

    @Column(name = "memo", length = 500)
    private String memo;

    @Column(name = "name")
    private String name;

    @Column(name = "total_kcal", nullable = false)
    private Integer totalKcal;

    @Column(name = "total_carb_g")
    private Integer totalCarbG;

    @Column(name = "total_protein_g", nullable = false)
    private Integer totalProteinG;

    @Column(name = "total_fat_g", nullable = false)
    private Integer totalFatG;

    @Column(name = "eaten_at", nullable = false, updatable = false)
    private LocalDate eatenAt;

    @Builder
    public MealLog(
            User user,
            String name,
            MealType mealType,
            String memo,
            Integer totalKcal,
            Integer totalCarbG,
            Integer totalProteinG,
            Integer totalFatG,
            LocalDate eatenAt
    ) {
        this.user = user;
        this.mealType = mealType;
        this.name = name;
        this.memo = memo;
        this.totalKcal = totalKcal;
        this.totalCarbG = totalCarbG;
        this.totalProteinG = totalProteinG;
        this.totalFatG = totalFatG;
        this.eatenAt = eatenAt;
    }

    public enum MealType { BREAKFAST, LUNCH, DINNER, SNACK }
}
