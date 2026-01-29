package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.dto.request.UserRequestDto;
import dgu.triple.aeterna.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseDto<?> registerUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseDto.created(userService.createUser(userRequestDto));
    }

    @GetMapping("/{userId}")
    public ResponseDto<?> getUser(@PathVariable Long userId) {
        return ResponseDto.ok(userService.getUserInfo(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseDto<?> updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequestDto userRequestDto
    ) {
        return ResponseDto.ok(userService.updateUserInfo(userId, userRequestDto));
    }
}
