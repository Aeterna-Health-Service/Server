package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.MealLog;
import dgu.triple.aeterna.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "식단 기록 생성/수정 요청 DTO")
public record MealLogRequestDto(

        @Schema(description = "식단 이름/메뉴", example = "닭가슴살 샐러드")
        String name,

        @Schema(
                description = "섭취 날짜 (YYYY-MM-DD)",
                example = "2026-01-31"
        )
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
                example = "LUNCH"
        )
        MealLog.MealType mealType,

        @Schema(description = "메모", example = "탄수 조금 줄였음")
        String memo,

        @Schema(description = "총 칼로리(kcal)", example = "520")
        Integer totalKcal,

        @Schema(description = "총 탄수화물(g)", example = "45")
        Integer totalCarbG,

        @Schema(description = "총 단백질(g)", example = "38")
        Integer totalProteinG,

        @Schema(description = "총 지방(g)", example = "18")
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
