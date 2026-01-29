package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.dto.request.CommentRequestDto;
import dgu.triple.aeterna.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseDto<?> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto request
    ) {
        return ResponseDto.ok(commentService.createComment(postId, request));
    }

    @GetMapping
    public ResponseDto<?> getComments(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ResponseDto.ok(commentService.getComments(postId, pageNum, size));
    }

    //댓글 수정 보류
//    @PutMapping("/{commentId}")
//    public ResponseDto<?> update(
//            @PathVariable Long postId,
//            @PathVariable Long commentId,
//            @RequestBody Object request
//    ) {
//        return ResponseDto.ok().build();
//    }

    @DeleteMapping("/{commentId}/{userId}")
    public ResponseDto<?> deleteComment(
            @PathVariable Long commentId,
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(commentService.deleteComment(commentId, userId));
    }
}
