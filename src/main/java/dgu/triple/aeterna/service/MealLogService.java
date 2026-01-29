package dgu.triple.aeterna.service;

import dgu.triple.aeterna.domain.MealLog;
import dgu.triple.aeterna.domain.User;
import dgu.triple.aeterna.dto.request.MealLogRequestDto;
import dgu.triple.aeterna.exception.CommonException;
import dgu.triple.aeterna.exception.ErrorCode;
import dgu.triple.aeterna.repository.MealLogRepository;
import dgu.triple.aeterna.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MealLogService {
    private final MealLogRepository mealLogRepository;
    private final UserRepository userRepository;

    // public  confirmMeal

    public Long addMeal(Long userId, MealLogRequestDto mealLogRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        MealLog mealLog = mealLogRequestDto.toEntity(user);
        mealLogRepository.save(mealLog);

        //DailySummary 수정도 같이해야됨 -> 동훈이한테 보내야됨 meal 이전에 선행작업으로 들어가야됨

        return mealLog.getId();
    }

    public Long updateMeal(Long mealId, MealLogRequestDto mealLogRequestDto) {
        MealLog mealLog = mealLogRepository.findById(mealId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));


        //DailySummary 수정도 같이해야됨 -> 동훈이한테 보내야됨
        //meal update

        return mealLog.getId();
    }

    public Boolean deleteMeal(Long mealId) {
        mealLogRepository.deleteById(mealId);
        //이미지도 있으면 삭제

        return true;
    }


}
