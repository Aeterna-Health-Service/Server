package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 생성/수정 요청 DTO")
public record UserRequestDto(

        @Schema(description = "이름", example = "홍길동")
        String name,

        @Schema(description = "닉네임", example = "gildong")
        String nickname,

        @Schema(description = "전화번호", example = "010-1234-5678")
        String phone,

        @Schema(description = "이메일", example = "test@example.com")
        String email,

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

        @Schema(description = "현재 체중(kg)", example = "72.5")
        Double weight,

        @Schema(description = "목표 체중(kg)", example = "65.0")
        Double targetWeight,

        @Schema(
                description = """
                        식단 유형
                        BALANCE       : 균형 식단
                        HIGH_PROTEIN  : 고단백
                        KETO          : 키토제닉
                        VEGAN         : 비건
                        """,
                allowableValues = {"BALANCE", "HIGH_PROTEIN", "KETO", "VEGAN"},
                example = "BALANCE"
        )
        User.DietType dietType,

        @Schema(description = "목표 섭취 칼로리(kcal)", example = "1800")
        Integer goalKcal,

        @Schema(description = "하루 물 섭취 목표(ml)", example = "2000")
        Integer waterTargetMl,

        @Schema(description = "하루 운동 소모 칼로리 목표", example = "400")
        Integer exerciseKcalTarget,

        @Schema(description = "하루 걸음 수 목표", example = "10000")
        Integer stepsTarget
) {
}