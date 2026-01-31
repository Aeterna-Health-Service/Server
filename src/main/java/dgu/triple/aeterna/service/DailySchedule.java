package dgu.triple.aeterna.service;

import dgu.triple.aeterna.domain.DailySummary;
import dgu.triple.aeterna.domain.User;
import dgu.triple.aeterna.repository.DailySummaryRepository;
import dgu.triple.aeterna.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailySchedule {

    private final DailySummaryRepository dailySummaryRepository;
    private final UserRepository userRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void createDailySummary() {

        List<User> userList = userRepository.findAll();

        userList.forEach(u -> {
            DailySummary dailySummary =  new DailySummary(u);
            dailySummaryRepository.save(dailySummary);
        });
    }
}
