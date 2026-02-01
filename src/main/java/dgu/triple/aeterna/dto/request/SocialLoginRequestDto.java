package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "소셜 로그인 요청 DTO")
public record SocialLoginRequestDto(

        @Schema(
                description = "소셜 로그인 제공자",
                allowableValues = {"GOOGLE", "APPLE"},
                example = "GOOGLE"
        )
        User.Provider provider,

        @Schema(
                description = "소셜 로그인 SDK에서 발급받은 토큰"
        )
        String token
) { }
