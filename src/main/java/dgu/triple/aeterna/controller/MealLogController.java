package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.dto.request.MealLogRequestDto;
import dgu.triple.aeterna.service.MealLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meal")
@RequiredArgsConstructor
public class MealLogController {

    private final MealLogService mealLogService;

    @PostMapping("/{userId}")
    public ResponseDto<?> addMealLog(
            @PathVariable Long userId,
            @RequestBody MealLogRequestDto mealLogRequestDto
    ) {
        return ResponseDto.created(mealLogService.addMeal(userId, mealLogRequestDto));
    }

//    @GetMapping("/{userId}/{date}")
//    public ResponseDto<MealLogResponseDto> getMealLog(@PathVariable Long mealId) {
//        return ResponseDto.ok(mealLogService.)
//    }

    @PatchMapping("/{mealId}")
    public ResponseDto<?> updateMealLog(@PathVariable Long mealId, @RequestBody MealLogRequestDto mealLogRequestDto) {
        return ResponseDto.ok(mealLogService.updateMeal(mealId, mealLogRequestDto));
    }

    @DeleteMapping("/{mealId}")
    public ResponseDto<?> deleteMealLog(@PathVariable Long mealId) {
        return ResponseDto.ok(mealLogService.deleteMeal(mealId));
    }
}
