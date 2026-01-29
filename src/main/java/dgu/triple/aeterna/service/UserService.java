package dgu.triple.aeterna.service;

import dgu.triple.aeterna.domain.DailySummary;
import dgu.triple.aeterna.domain.User;
import dgu.triple.aeterna.dto.request.UserRequestDto;
import dgu.triple.aeterna.dto.response.UserResponseDto;
import dgu.triple.aeterna.exception.CommonException;
import dgu.triple.aeterna.exception.ErrorCode;
import dgu.triple.aeterna.repository.DailySummaryRepository;
import dgu.triple.aeterna.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DailySummaryRepository dailySummaryRepository;

    public Long createUser(UserRequestDto userRequestDto) {
        User newUser = userRequestDto.toEntity();
        userRepository.save(newUser);

        Double bmr = ((newUser.getGender() == User.EGender.FEMALE) ?
                447.593 + (9.247 * newUser.getWeight()) + (3.098 * newUser.getHeight()) - (4.330 - newUser.getAge()) :
                88.362 + (13.397 * newUser.getWeight()) + (4.799 * newUser.getHeight()) - (5.677 - newUser.getAge()))
                * 1.375;

        newUser.updateBmr(bmr);

        DailySummary dailySummary = new DailySummary(newUser);
        dailySummaryRepository.save(dailySummary);

        //아바타 생성

        return newUser.getId();
    }

    public UserResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        return UserResponseDto.fromEntity(user);
    }

    public Boolean updateUserInfo(Long userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        user.updateUser(userRequestDto);

        return true;
    }
}
