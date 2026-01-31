package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.annotation.Date;
import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.dto.response.DailySummaryResponseDto;
import dgu.triple.aeterna.service.DailySummaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/summary")
@RequiredArgsConstructor
@Tag(name = "DailySummary", description = "일일 요약(섭취/소모/걸음/체중/물) API")
public class DailySummaryController {

    private final DailySummaryService dailySummaryService;

    @Operation(
            summary = "일일 요약 조회",
            description = """
                    특정 사용자(userId)의 특정 날짜(date)에 대한 일일 요약 정보를 조회합니다.
                    date 포맷: YYYY-MM-DD
                    """
    )
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/{userId}/{date}")
    public ResponseDto<DailySummaryResponseDto> getDailySummary(
            @Parameter(description = "사용자 ID", example = "1")
            @PathVariable Long userId,

            @Parameter(description = "조회 날짜 (YYYY-MM-DD)", example = "2026-01-31")
            @PathVariable @Date LocalDate date
    ) {
        return ResponseDto.ok(dailySummaryService.getDailySummary(userId, date));
    }
}
