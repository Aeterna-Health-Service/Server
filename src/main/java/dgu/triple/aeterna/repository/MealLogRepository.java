package dgu.triple.aeterna.repository;

import dgu.triple.aeterna.domain.MealLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealLogRepository extends JpaRepository<MealLog, Long> {
}
