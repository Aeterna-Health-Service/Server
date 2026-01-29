package dgu.triple.aeterna.service;

import dgu.triple.aeterna.domain.DailySummary;
import dgu.triple.aeterna.domain.User;
import dgu.triple.aeterna.dto.response.DailySummaryResponseDto;
import dgu.triple.aeterna.exception.CommonException;
import dgu.triple.aeterna.exception.ErrorCode;
import dgu.triple.aeterna.repository.DailySummaryRepository;
import dgu.triple.aeterna.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class DailySummaryService {
    private final DailySummaryRepository dailySummaryRepository;
    private final UserRepository userRepository;

    public DailySummaryResponseDto getDailySummary(Long userId, LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        DailySummary dailySummary = dailySummaryRepository.findDailySummaryByCreatedAtAndUser(date, user).orElseThrow(() -> new CommonException(ErrorCode.SERVER_ERROR));

        return DailySummaryResponseDto.fromEntity(dailySummary);
    }
}
