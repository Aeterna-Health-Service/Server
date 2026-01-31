package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "사용자 정보 응답 DTO")
public record UserResponseDto(

        @Schema(description = "사용자 ID", example = "1")
        Long id,

        @Schema(description = "이름", example = "홍길동")
        String name,

        @Schema(description = "닉네임", example = "gildong")
        String nickname,

        @Schema(description = "전화번호", example = "010-1234-5678")
        String phone,

        @Schema(description = "이메일", example = "test@example.com")
        String email,

        @Schema(description = "프로필 이미지 URL", example = "https://cdn.example.com/profile/1.png")
        String profileImageUrl,

        @Schema(
                description = "성별 (MALE: 남성, FEMALE: 여성)",
                allowableValues = {"MALE", "FEMALE"},
                example = "MALE"
        )
        User.EGender gender,

        @Schema(description = "나이", example = "25")
        Integer age,

        @Schema(description = "키(cm)", example = "175")
        Integer height,

        @Schema(
                description = """
                        활동량 단계
                        VERY_LOW  : 거의 운동 안 함
                        LOW       : 주 1~2회 가벼운 활동
                        NORMAL    : 주 3~5회 일반 운동
                        HIGH      : 거의 매일 고강도 운동
                        """,
                allowableValues = {"VERY_LOW", "LOW", "NORMAL", "HIGH"},
                example = "NORMAL"
        )
        User.ActivityLevel activityLevel,

        @Schema(
                description = """
                        목표 유형
                        DIET     : 감량
                        BULK     : 증량
                        MAINTAIN : 유지
                        """,
                allowableValues = {"DIET", "BULK", "MAINTAIN"},
                example = "DIET"
        )
        User.GoalType goalType,

        @Schema(description = "목표 체중(kg)", example = "65.0")
        Double targetWeight,

        @Schema(
                description = """
                        식단 유형
                        BALANCE       : 균형 식단
                        HIGH_PROTEIN  : 고단백
                        KETO          : 키토
                        VEGAN         : 비건
                        """,
                allowableValues = {"BALANCE", "HIGH_PROTEIN", "KETO", "VEGAN"},
                example = "BALANCE"
        )
        User.DietType dietType,

        @Schema(description = "기초대사량(BMR, kcal)", example = "1500.5")
        Double bmr,

        @Schema(description = "총 에너지 소비량(TDEE, kcal)", example = "2200")
        Integer tdeeKcal,

        @Schema(
                description = """
                        사용자 상태
                        ACTIVE    : 정상
                        SUSPENDED : 정지
                        DELETED   : 탈퇴
                        """,
                allowableValues = {"ACTIVE", "SUSPENDED", "DELETED"},
                example = "ACTIVE"
        )
        User.UserStatus status,

        @Schema(description = "일일 목표 섭취 칼로리", example = "1800")
        Integer goalKcal,

        @Schema(description = "일일 목표 탄수화물(g)", example = "200")
        Integer targetCarb,

        @Schema(description = "일일 목표 단백질(g)", example = "120")
        Integer targetProtein,

        @Schema(description = "일일 목표 지방(g)", example = "50")
        Integer targetFatG,

        @Schema(description = "일일 물 섭취 목표(ml)", example = "2000")
        Integer waterTargetMl,

        @Schema(description = "일일 운동 소모 칼로리 목표", example = "400")
        Integer exerciseKcalTarget,

        @Schema(description = "일일 걸음 수 목표", example = "10000")
        Integer stepsTarget,

        @Schema(description = "회원 가입 일시", example = "2026-01-30T12:34:56")
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
