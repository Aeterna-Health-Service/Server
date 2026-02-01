package dgu.triple.aeterna.service;

import dgu.triple.aeterna.domain.DailySummary;
import dgu.triple.aeterna.domain.User;
import dgu.triple.aeterna.dto.request.SocialLoginRequestDto;
import dgu.triple.aeterna.dto.request.UserRequestDto;
import dgu.triple.aeterna.dto.response.SocialLoginResponseDto;
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

    public Long createUser(Long userId, UserRequestDto userRequestDto)  {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        user.updateUser(userRequestDto);

        Double bmr = ((user.getGender() == User.EGender.FEMALE) ?
                447.593 + (9.247 * user.getWeight()) + (3.098 * user.getHeight()) - (4.330 - user.getAge()) :
                88.362 + (13.397 * user.getWeight()) + (4.799 * user.getHeight()) - (5.677 - user.getAge()))
                * 1.375;

        user.updateBmr(bmr);

        DailySummary dailySummary = new DailySummary(user);
        dailySummaryRepository.save(dailySummary);

        //아바타 생성

        return user.getId();
    }

    public SocialLoginResponseDto registerUser(SocialLoginRequestDto request) {
        return userRepository.findByProviderAndSocialToken(request.provider(), request.token())
                .map(user -> SocialLoginResponseDto.existing(user.getId(), UserResponseDto.fromEntity(user)))
                .orElseGet(() -> {
                    User newUser = User.register(request.provider(), request.token());
                    userRepository.save(newUser);

                    return SocialLoginResponseDto.newUser(newUser.getId());
                });
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
