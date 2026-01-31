package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.UserBodyLog;

 
import java.time.LocalDateTime;

public record UserBodyLogResponseDto(
        Long id,
        Long userId,
        LocalDateTime measuredAt,
        Integer weight,
        Integer bodyFatPct,
        Integer muscleMassKg,
        LocalDateTime createdAt
) {
    public static UserBodyLogResponseDto fromEntity(UserBodyLog e) {
        return new UserBodyLogResponseDto(
                e.getId(),
                e.getUser().getId(),
                e.getMeasuredAt(),
                e.getWeight(),
                e.getBodyFatPct(),
                e.getMuscleMassKg(),
                e.getCreatedAt()
        );
    }
}

