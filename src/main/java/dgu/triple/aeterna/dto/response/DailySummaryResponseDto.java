package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.DailySummary;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "일일 요약 응답 DTO")
public record DailySummaryResponseDto(

        @Schema(description = "일일 요약 ID", example = "100")
        Long id,

        @Schema(description = "기록 날짜 (YYYY-MM-DD)", example = "2026-01-31")
        LocalDate createdAt,

        @Schema(description = "섭취 칼로리(kcal)", example = "1800")
        Integer intakeKcal,

        @Schema(description = "섭취 탄수화물(g)", example = "210")
        Integer intakeCarb,

        @Schema(description = "섭취 단백질(g)", example = "120")
        Integer intakeProtein,

        @Schema(description = "섭취 지방(g)", example = "50")
        Integer intakeFat,

        @Schema(description = "소모 칼로리(kcal)", example = "450")
        Integer burnedKcal,

        @Schema(description = "걸음 수", example = "9800")
        Integer steps,

        @Schema(description = "체중(kg) (정수 저장 정책이면 그대로, 소수면 타입 변경 필요)", example = "70")
        Integer weight,

        @Schema(description = "물 섭취량(ml)", example = "2000")
        Integer water
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
                e.getWeight(),
                e.getWater()
        );
    }
}
