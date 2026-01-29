package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.MealLog;
import dgu.triple.aeterna.domain.User;


import java.time.LocalDate;

public record MealLogRequestDto(
        String name,
        LocalDate eatenAt,
        MealLog.MealType mealType,
        String memo,
        Integer totalKcal,
        Integer totalCarbG,
        Integer totalProteinG,
        Integer totalFatG
) {
    public MealLog toEntity(User user) {
        return MealLog.builder()
                .user(user)
                .name(name)
                .eatenAt(eatenAt)
                .mealType(mealType)
                .memo(memo)
                .totalKcal(totalKcal)
                .totalCarbG(totalCarbG)
                .totalProteinG(totalProteinG)
                .totalFatG(totalFatG)
                .build();
    }
}
