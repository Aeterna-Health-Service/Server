package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.User;
import dgu.triple.aeterna.domain.UserBodyLog;

 
import java.time.LocalDateTime;

public record UserBodyLogRequestDto(
        LocalDateTime measuredAt,
        Integer weightKg,
        Integer bodyFatPct,
        Integer muscleMassKg
) {
    public UserBodyLog toEntity(User user) {
        return UserBodyLog.builder()
                .user(user)
                .measuredAt(measuredAt)
                .weightKg(weightKg)
                .bodyFatPct(bodyFatPct)
                .muscleMassKg(muscleMassKg)
                .build();
    }
}
