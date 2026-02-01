package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.dto.request.SocialLoginRequestDto;
import dgu.triple.aeterna.dto.request.UserRequestDto;
import dgu.triple.aeterna.dto.response.SocialLoginResponseDto;
import dgu.triple.aeterna.dto.response.UserResponseDto;
import dgu.triple.aeterna.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "사용자 관리 API")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "회원 가입",
            description = "사용자 기본 정보 및 목표 정보를 등록합니다. userId를 반환합니다."
    )
    @ApiResponse(responseCode = "201", description = "회원 가입 성공")
    @PostMapping("/{userId}")
    public ResponseDto<Long> registerUser(
            @Parameter(description = "사용자 ID", example = "1")
            @PathVariable Long userId,
            @RequestBody @Schema(description = "회원 가입 요청 DTO")
            UserRequestDto userRequestDto
    ) {
        return ResponseDto.created(userService.createUser(userId, userRequestDto));
    }

    @Operation(
            summary = "소셜 로그인 / 회원 생성",
            description = "provider + token으로 로그인 처리합니다. 기존 회원이면 토큰 발급 + user 정보 반환, 신규면 user 생성 후 신규 여부를 반환합니다."
    )
    @ApiResponse(responseCode = "200", description = "소셜 로그인 성공")
    @PostMapping("/social")
    public ResponseDto<SocialLoginResponseDto> socialLogin(
            @RequestBody @Schema(description = "소셜 로그인 요청 DTO")
            SocialLoginRequestDto request
    ) {
        return ResponseDto.ok(userService.registerUser(request));
    }

    @Operation(
            summary = "사용자 조회",
            description = "userId로 사용자 상세 정보를 조회합니다."
    )
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/{userId}")
    public ResponseDto<UserResponseDto> getUser(
            @Parameter(description = "사용자 ID", example = "1")
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(userService.getUserInfo(userId));
    }

    @Operation(
            summary = "사용자 정보 수정",
            description = "사용자의 목표/신체/기본 정보를 수정합니다. (부분 수정 가능)"
    )
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @PatchMapping("/{userId}")
    public ResponseDto<Boolean> updateUser(
            @Parameter(description = "사용자 ID", example = "1")
            @PathVariable Long userId,

            @RequestBody
            @Schema(description = "사용자 정보 수정 DTO (null 값은 변경되지 않음)")
            UserRequestDto userRequestDto
    ) {
        return ResponseDto.ok(userService.updateUserInfo(userId, userRequestDto));
    }
}

