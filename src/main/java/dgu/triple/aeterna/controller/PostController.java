package dgu.triple.aeterna.controller;

import dgu.triple.aeterna.domain.Post.BoardType;
import dgu.triple.aeterna.dto.ResponseDto;
import dgu.triple.aeterna.dto.request.PostRequestDto;
import dgu.triple.aeterna.service.PostLikeService;
import dgu.triple.aeterna.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardType}/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostLikeService postLikeService;

    @PostMapping
    public ResponseDto<?> createPost(
            @PathVariable BoardType boardType,
            @RequestBody PostRequestDto postRequestDto
    ) {
        return ResponseDto.ok(postService.createPost(boardType, postRequestDto));
    }

    @GetMapping("/{postId}/{userId}")
    public ResponseDto<?> getPost(
            @PathVariable Long postId,
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(postService.getPost(postId,  userId));
    }

    @GetMapping
    public ResponseDto<?> getPostList(
            @PathVariable BoardType boardType,
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ResponseDto.ok(postService.getPostList(boardType, pageNum, size));
    }

    @PutMapping("/{postId}")
    public ResponseDto<?> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto
    ) {
        return ResponseDto.ok(postService.updatePost(postId, postRequestDto));
    }

    @DeleteMapping("/{postId}/{userId}")
    public ResponseDto<?> delete(
            @PathVariable Long postId,
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(postService.deletePost(postId, userId));
    }

    @PostMapping("/{postId}/like/{userId}")
    public ResponseDto<?> doPostLike(
            @PathVariable Long postId,
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(postLikeService.doPostLike(postId,  userId));
    }

    @DeleteMapping("/{postId}/like/{userId}")
    public ResponseDto<?> undoPostLike(
            @PathVariable Long postId,
            @PathVariable Long userId
    ) {
        return ResponseDto.ok(postLikeService.undoPostLike(postId,  userId));
    }
}

