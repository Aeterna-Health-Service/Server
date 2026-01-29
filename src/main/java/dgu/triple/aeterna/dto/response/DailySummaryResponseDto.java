package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.DailySummary;

import java.time.LocalDate;

public record DailySummaryResponseDto(
        Long id,
        LocalDate createAt,
        Integer intakeKcal,
        Integer intakeCarb,
        Integer intakeProtein,
        Integer intakeFat,
        Integer burnedKcal,
        Integer steps,
        Integer weightKg,
        Integer waterMl
) {
    public static DailySummaryResponseDto fromEntity(DailySummary e) {
        return new DailySummaryResponseDto(
                e.getId(),
                e.getCreatedAt(),
                e.getIntakeKcal(),
                e.getIntakeCarb(),
                e.getIntakeProtein(),
                e.getIntakeFat(),
                e.getBurnedKcal(),
                e.getSteps(),
                e.getWeightKg(),
                e.getWaterMl()
        );
    }
}

