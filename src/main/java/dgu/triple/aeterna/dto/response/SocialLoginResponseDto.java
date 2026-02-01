package dgu.triple.aeterna.dto.response;

public record SocialLoginResponseDto(
        String accessToken,
        String refreshToken,
        boolean isNewUser,
        Long userId,
        UserResponseDto user
) {
    public static SocialLoginResponseDto newUser(Long userId) {
        return new SocialLoginResponseDto("", "", true, userId, null);
    }

    public static SocialLoginResponseDto existing(Long userId, UserResponseDto user) {
        return new SocialLoginResponseDto("", "", false, userId, user);
    }
}