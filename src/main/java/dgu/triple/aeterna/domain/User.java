package dgu.triple.aeterna.domain;

import dgu.triple.aeterna.dto.request.UserRequestDto;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "nickname", length = 30, nullable = false)
    private String nickname;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "profile_image_url", length = 500)
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private EGender gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "height_cm")
    private Integer height;

    @Column(name = "weight")
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_level", nullable = false)
    private ActivityLevel activityLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal_type", nullable = false)
    private GoalType goalType;

    @Column(name = "target_weight")
    private Double targetWeight;

    @Enumerated(EnumType.STRING)
    @Column(name = "diet_type", nullable = false)
    private DietType dietType;

    @Column(name = "bmr_kcal")
    private Double bmr;

    @Column(name = "tdee_kcal")
    private Integer tdeeKcal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @Column(name = "goal_kcal", nullable = false)
    private Integer goalKcal;

    @Column(name = "target_carb_g", nullable = false)
    private Integer targetCarb;

    @Column(name = "target_protein_g", nullable = false)
    private Integer targetProtein;

    @Column(name = "target_fat_g", nullable = false)
    private Integer targetFatG;

    @Column(name = "water_target_ml")
    private Integer waterTargetMl;

    @Column(name = "exercise_kcal_target")
    private Integer exerciseKcalTarget;

    @Column(name = "steps_target")
    private Integer stepsTarget;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public User(
            String name,
            String nickname,
            String phone,
            String email,
            String profileImageUrl,
            EGender gender,
            Integer age,
            Integer height,
            Double weight,
            ActivityLevel activityLevel,
            GoalType goalType,
            Double targetWeight,
            DietType dietType,
            Double bmr,
            Integer tdeeKcal,
            UserStatus status,
            Integer goalKcal,
            Integer targetCarb,
            Integer targetProtein,
            Integer targetFatG,
            Integer waterTargetMl,
            Integer exerciseKcalTarget,
            Integer stepsTarget
    ) {
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.goalType = goalType;
        this.targetWeight =  targetWeight;
        this.dietType = dietType;
        this.bmr = bmr;
        this.tdeeKcal = tdeeKcal;
        this.status = status;
        this.goalKcal = goalKcal;
        this.targetCarb = targetCarb;
        this.targetProtein = targetProtein;
        this.targetFatG = targetFatG;
        this.waterTargetMl = waterTargetMl;
        this.exerciseKcalTarget = exerciseKcalTarget;
        this.stepsTarget = stepsTarget;
    }

    public void updateBmr(Double bmr) {
        this.bmr = bmr;
    }

    // User.java (도메인)
    public void updateUser(UserRequestDto dto) {

        if (dto.nickname() != null) this.nickname = dto.nickname();
        if (dto.phone() != null) this.phone = dto.phone();
        if (dto.email() != null) this.email = dto.email();
        if (dto.age() != null) this.age = dto.age();
        if (dto.height() != null) this.height = dto.height();
        if (dto.activityLevel() != null) this.activityLevel = dto.activityLevel();
        if (dto.goalType() != null) this.goalType = dto.goalType();
        if (dto.weight() != null) this.weight = dto.weight();
        if (dto.targetWeight() != null) this.targetWeight = dto.targetWeight();
        if (dto.dietType() != null) this.dietType = dto.dietType();
        if (dto.goalKcal() != null) this.goalKcal = dto.goalKcal();
        if (dto.waterTargetMl() != null) this.waterTargetMl = dto.waterTargetMl();
        if (dto.exerciseKcalTarget() != null) this.exerciseKcalTarget = dto.exerciseKcalTarget();
        if (dto.stepsTarget() != null) this.stepsTarget = dto.stepsTarget();
    }

    public enum EGender { MALE, FEMALE}
    public enum ActivityLevel { VERY_LOW, LOW, NORMAL, HIGH }
    public enum GoalType { DIET, BULK, MAINTAIN }
    public enum DietType { BALANCE, HIGH_PROTEIN, KETO, VEGAN }
    public enum UserStatus { ACTIVE, SUSPENDED, DELETED }
}
