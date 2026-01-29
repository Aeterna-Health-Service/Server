package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.User;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String name,
        String nickname,
        String phone,
        String email,
        String profileImageUrl,
        User.EGender gender,
        Integer age,
        Integer height,
        User.ActivityLevel activityLevel,
        User.GoalType goalType,
        Double  targetWeight,
        User.DietType dietType,
        Double bmr,
        Integer tdeeKcal,
        User.UserStatus status,
        Integer goalKcal,
        Integer targetCarb,
        Integer targetProtein,
        Integer targetFatG,
        Integer waterTargetMl,
        Integer exerciseKcalTarget,
        Integer stepsTarget,
        LocalDateTime createdAt
) {
    public static UserResponseDto fromEntity(User e) {
        return new UserResponseDto(
                e.getId(),
                e.getName(),
                e.getNickname(),
                e.getPhone(),
                e.getEmail(),
                e.getProfileImageUrl(),
                e.getGender(),
                e.getAge(),
                e.getHeight(),
                e.getActivityLevel(),
                e.getGoalType(),
                e.getTargetWeight(),
                e.getDietType(),
                e.getBmr(),
                e.getTdeeKcal(),
                e.getStatus(),
                e.getGoalKcal(),
                e.getTargetCarb(),
                e.getTargetProtein(),
                e.getTargetFatG(),
                e.getWaterTargetMl(),
                e.getExerciseKcalTarget(),
                e.getStepsTarget(),
                e.getCreatedAt()
        );
    }
}


