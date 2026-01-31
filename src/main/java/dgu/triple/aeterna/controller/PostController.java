package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.domain.Post.BoardType;
import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.dto.request.PostRequestDto;
import dgu.triple.aeterna.dto.response.PageResponseDto;
import dgu.triple.aeterna.dto.response.PostResponseDto;
import dgu.triple.aeterna.service.PostLikeService;
import dgu.triple.aeterna.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardType}/posts")
@RequiredArgsConstructor
@Tag(name = "Post", description = "게시글/게시판 API")
public class PostController {

    private final PostService postService;
    private final PostLikeService postLikeService;

    @Operation(
            summary = "게시글 작성",
            description = """
                    특정 게시판(boardType)에 게시글을 작성합니다.
                    boardType 값:
                    TIP(팁), ROUTINE(루틴), MEAL(식단), FREE(자유)
                    """
    )
    @ApiResponse(responseCode = "200", description = "작성 성공")
    @PostMapping
    public ResponseDto<Long> createPost(
            @Parameter(
                    description = """
                            게시판 타입
                            TIP(팁), ROUTINE(루틴), MEAL(식단), FREE(자유)
                            """,
                    example = "FREE"
            )
            @PathVariable BoardType boardType,

            @RequestBody PostRequestDto postRequestDto
    ) {
        return ResponseDto.ok(postService.createPost(boardType, postRequestDto));
    }

    @Operation(
            summary = "게시글 단건 조회",
            description = """
                    게시글을 조회합니다.
                    userId는 '좋아요 여부(isLiked)' 판별을 위해 필요합니다.
                    """
    )
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/{postId}/{userId}")
    public ResponseDto<PostResponseDto> getPost(
            @Parameter(description = "게시글 ID", example = "10")
            @PathVariable Long postId,

            @Parameter(description = "조회 사용자 ID (좋아요 여부 판단용)", example = "1")
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(postService.getPost(postId, userId));
    }

    @Operation(
            summary = "게시글 목록 조회",
            description = """
                    특정 게시판(boardType)의 게시글 목록을 페이징 조회합니다.
                    boardType 값:
                    TIP(팁), ROUTINE(루틴), MEAL(식단), FREE(자유)
                    """
    )
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseDto<PageResponseDto<PostResponseDto>> getPostList(
            @Parameter(
                    description = """
                            게시판 타입
                            TIP(팁), ROUTINE(루틴), MEAL(식단), FREE(자유)
                            """,
                    example = "TIP"
            )
            @PathVariable BoardType boardType,

            @Parameter(description = "페이지 번호(0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") Integer pageNum,

            @Parameter(description = "페이지 크기", example = "10")
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ResponseDto.ok(postService.getPostList(boardType, pageNum, size));
    }

    @Operation(
            summary = "게시글 수정",
            description = """
                    게시글을 수정합니다.
                    현재 구현 기준으로 title/content를 수정 대상으로 가정합니다.
                    """
    )
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @PutMapping("/{postId}")
    public ResponseDto<Long> updatePost(
            @Parameter(description = "게시글 ID", example = "10")
            @PathVariable Long postId,

            @RequestBody PostRequestDto postRequestDto
    ) {
        return ResponseDto.ok(postService.updatePost(postId, postRequestDto));
    }

    @Operation(
            summary = "게시글 삭제",
            description = """
                    게시글을 삭제(soft delete)합니다.
                    userId는 작성자/권한 검증용으로 사용됩니다.
                    """
    )
    @ApiResponse(responseCode = "200", description = "삭제 성공")
    @DeleteMapping("/{postId}/{userId}")
    public ResponseDto<Boolean> delete(
            @Parameter(description = "게시글 ID", example = "10")
            @PathVariable Long postId,

            @Parameter(description = "요청 사용자 ID(작성자/권한 검증용)", example = "1")
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(postService.deletePost(postId, userId));
    }

    @Operation(
            summary = "게시글 좋아요",
            description = "특정 게시글에 좋아요를 누릅니다."
    )
    @ApiResponse(responseCode = "200", description = "좋아요 성공")
    @PostMapping("/{postId}/like/{userId}")
    public ResponseDto<Boolean> doPostLike(
            @Parameter(description = "게시글 ID", example = "10")
            @PathVariable Long postId,

            @Parameter(description = "요청 사용자 ID", example = "1")
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(postLikeService.doPostLike(postId, userId));
    }

    @Operation(
            summary = "게시글 좋아요 취소",
            description = "특정 게시글의 좋아요를 취소합니다."
    )
    @ApiResponse(responseCode = "200", description = "좋아요 취소 성공")
    @DeleteMapping("/{postId}/like/{userId}")
    public ResponseDto<Boolean> undoPostLike(
            @Parameter(description = "게시글 ID", example = "10")
            @PathVariable Long postId,

            @Parameter(description = "요청 사용자 ID", example = "1")
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(postLikeService.undoPostLike(postId, userId));
    }
}
