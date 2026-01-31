package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.dto.request.CommentRequestDto;
import dgu.triple.aeterna.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "게시글 댓글 API")
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "댓글 작성",
            description = """
                    특정 게시글(postId)에 댓글을 작성합니다.
                    요청 body의 userId는 작성자 식별에 사용됩니다.
                    """
    )
    @ApiResponse(responseCode = "200", description = "댓글 작성 성공")
    @PostMapping
    public ResponseDto<?> createComment(
            @Parameter(description = "게시글 ID", example = "10")
            @PathVariable Long postId,

            @RequestBody CommentRequestDto request
    ) {
        return ResponseDto.ok(commentService.createComment(postId, request));
    }

    @Operation(
            summary = "댓글 목록 조회",
            description = """
                    특정 게시글(postId)에 달린 댓글 목록을 페이징 조회합니다.
                    pageNum은 0부터 시작합니다.
                    """
    )
    @ApiResponse(responseCode = "200", description = "댓글 목록 조회 성공")
    @GetMapping
    public ResponseDto<?> getComments(
            @Parameter(description = "게시글 ID", example = "10")
            @PathVariable Long postId,

            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") Integer pageNum,

            @Parameter(description = "페이지 크기", example = "10")
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

    @Operation(
            summary = "댓글 삭제",
            description = """
                    댓글을 삭제합니다.
                    userId는 작성자 본인 여부 검증용으로 사용됩니다.
                    """
    )
    @ApiResponse(responseCode = "200", description = "댓글 삭제 성공")
    @DeleteMapping("/{commentId}/{userId}")
    public ResponseDto<?> deleteComment(
            @Parameter(description = "댓글 ID", example = "100")
            @PathVariable Long commentId,

            @Parameter(description = "요청 사용자 ID (작성자 검증용)", example = "1")
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(commentService.deleteComment(commentId, userId));
    }
}
