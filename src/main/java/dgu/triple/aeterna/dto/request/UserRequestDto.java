package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.User;

public record UserRequestDto (
        String name,
        String nickname,
        String phone,
        String email,
        User.EGender gender,
        Integer age,
        Integer height,
        User.ActivityLevel activityLevel,
        User.GoalType goalType,
        Double weight,
        Double targetWeight,
        User.DietType dietType,
        Integer goalKcal,
        Integer waterTargetMl,
        Integer exerciseKcalTarget,
        Integer stepsTarget
) {
    public User toEntity() {
        return User.builder()
                .name(name)
                .nickname(nickname)
                .phone(phone)
                .email(email)
                .gender(gender)
                .age(age)
                .height(height)
                .activityLevel(activityLevel)
                .goalType(goalType)
                .weight(weight)
                .targetWeight(targetWeight)
                .dietType(dietType)
                .goalKcal(goalKcal)
                .waterTargetMl(waterTargetMl)
                .exerciseKcalTarget(exerciseKcalTarget)
                .stepsTarget(stepsTarget)
                .build();
    }
}