package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.MealLog;
import java.time.LocalDate;

public record MealLogResponseDto(
        Long id,
        Long userId,
        LocalDate eatenAt,
        MealLog.MealType mealType,
        String memo,
        Integer totalKcal,
        Integer totalCarb,
        Integer totalProtein,
        Integer totalFat
) {
    public static MealLogResponseDto fromEntity(MealLog e) {
        return new MealLogResponseDto(
                e.getId(),
                e.getUser().getId(),
                e.getEatenAt(),
                e.getMealType(),
                e.getMemo(),
                e.getTotalKcal(),
                e.getTotalCarbG(),
                e.getTotalProteinG(),
                e.getTotalFatG()
        );
    }
}
