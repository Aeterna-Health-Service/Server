package dgu.triple.aeterna.repository;

import dgu.triple.aeterna.domain.DailySummary;
import dgu.triple.aeterna.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailySummaryRepository extends JpaRepository<DailySummary, Long> {
    Optional<DailySummary> findDailySummaryByCreatedAtAndUser(LocalDate date, User user);
}
