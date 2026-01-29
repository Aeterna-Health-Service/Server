package dgu.triple.aeterna.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "avatar_state")
public class AvatarState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "exp", nullable = false)
    private Integer exp;

    @Column(name = "token_balance", nullable = false)
    private Integer tokenBalance;

    @Column(name = "body_type_id")
    private Long bodyTypeId;

    @Column(name = "background_id")
    private Long backgroundId;

    @Column(name = "top_id")
    private Long topId;

    @Column(name = "bottom_id")
    private Long bottomId;

    @Column(name = "shoes_id")
    private Long shoesId;

    @Column(name = "streak_success", nullable = false)
    private Integer streakSuccess;

    @Column(name = "streak_fail", nullable = false)
    private Integer streakFail;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public AvatarState(
            User user,
            Integer level,
            Integer exp,
            Integer tokenBalance,
            Long bodyTypeId,
            Long backgroundId,
            Long topId,
            Long bottomId,
            Long shoesId,
            Integer streakSuccess,
            Integer streakFail
    ) {
        this.user = user;
        this.level = level;
        this.exp = exp;
        this.tokenBalance = tokenBalance;
        this.bodyTypeId = bodyTypeId;
        this.backgroundId = backgroundId;
        this.topId = topId;
        this.bottomId = bottomId;
        this.shoesId = shoesId;
        this.streakSuccess = streakSuccess;
        this.streakFail = streakFail;
    }
}

