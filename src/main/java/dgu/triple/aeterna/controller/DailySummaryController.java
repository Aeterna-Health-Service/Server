package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.annotation.Date;
import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.service.DailySummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/summary")
@RequiredArgsConstructor
public class DailySummaryController {
    private final DailySummaryService dailySummaryService;

    @GetMapping("/{userId}/{date}")
    public ResponseDto<?> getDailySummary(@PathVariable Long userId, @PathVariable @Date LocalDate date) {
        return ResponseDto.ok(dailySummaryService.getDailySummary(userId, date));
    }
}
