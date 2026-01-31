package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.MealLog;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "식단 기록 응답 DTO")
public record MealLogResponseDto(

        @Schema(description = "식단 기록 ID", example = "10")
        Long id,

        @Schema(description = "사용자 ID", example = "1")
        Long userId,

        @Schema(description = "섭취 날짜 (YYYY-MM-DD)", example = "2026-01-31")
        LocalDate eatenAt,

        @Schema(
                description = """
                        식사 타입
                        BREAKFAST : 아침
                        LUNCH     : 점심
                        DINNER    : 저녁
                        SNACK     : 간식
                        """,
                allowableValues = {"BREAKFAST", "LUNCH", "DINNER", "SNACK"},
                example = "DINNER"
        )
        MealLog.MealType mealType,

        @Schema(description = "메모", example = "외식이라 대략 입력")
        String memo,

        @Schema(description = "총 칼로리(kcal)", example = "520")
        Integer totalKcal,

        @Schema(description = "총 탄수화물(g)", example = "45")
        Integer totalCarb,

        @Schema(description = "총 단백질(g)", example = "38")
        Integer totalProtein,

        @Schema(description = "총 지방(g)", example = "18")
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
