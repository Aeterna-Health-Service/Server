package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.dto.request.MealLogRequestDto;
import dgu.triple.aeterna.service.MealLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meal")
@RequiredArgsConstructor
@Tag(name = "MealLog", description = "식단 기록 API")
public class MealLogController {

    private final MealLogService mealLogService;

    @Operation(
            summary = "식단 기록 추가",
            description = """
                    사용자의 식단 기록을 추가합니다.
                    mealType 값:
                    BREAKFAST(아침), LUNCH(점심), DINNER(저녁), SNACK(간식)
                    """
    )
    @ApiResponse(responseCode = "201", description = "추가 성공")
    @PostMapping("/{userId}")
    public ResponseDto<Long> addMealLog(
            @Parameter(description = "사용자 ID", example = "1")
            @PathVariable Long userId,

            @RequestBody MealLogRequestDto mealLogRequestDto
    ) {
        return ResponseDto.created(mealLogService.addMeal(userId, mealLogRequestDto));
    }

    // TODO: read method 필요

    @Operation(
            summary = "식단 기록 수정",
            description = """
                    식단 기록을 수정합니다.
                    현재 구현 기준으로 요청 DTO의 값으로 갱신됩니다.
                    """
    )
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @PatchMapping("/{mealId}")
    public ResponseDto<Long> updateMealLog(
            @Parameter(description = "식단 기록 ID", example = "10")
            @PathVariable Long mealId,

            @RequestBody MealLogRequestDto mealLogRequestDto
    ) {
        return ResponseDto.ok(mealLogService.updateMeal(mealId, mealLogRequestDto));
    }

    @Operation(
            summary = "식단 기록 삭제",
            description = "식단 기록을 삭제합니다."
    )
    @ApiResponse(responseCode = "200", description = "삭제 성공")
    @DeleteMapping("/{mealId}")
    public ResponseDto<Boolean> deleteMealLog(
            @Parameter(description = "식단 기록 ID", example = "10")
            @PathVariable Long mealId
    ) {
        return ResponseDto.ok(mealLogService.deleteMeal(mealId));
    }
}
