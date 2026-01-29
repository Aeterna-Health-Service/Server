package dgu.triple.aeterna.service;

import dgu.triple.aeterna.domain.Post;
import dgu.triple.aeterna.domain.User;
import dgu.triple.aeterna.dto.request.PostRequestDto;
import dgu.triple.aeterna.dto.response.PageResponseDto;
import dgu.triple.aeterna.dto.response.PostResponseDto;
import dgu.triple.aeterna.exception.CommonException;
import dgu.triple.aeterna.exception.ErrorCode;
import dgu.triple.aeterna.repository.PostRepository;
import dgu.triple.aeterna.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long createPost(Post.BoardType boardType, PostRequestDto postRequestDto) {
        User user = userRepository.findById(postRequestDto.userId()).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Post newPost = postRequestDto.toEntity(user);

        postRepository.save(newPost);
        newPost.addBoardType(boardType);

        return newPost.getId();
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        post.increaseViewCount();

        return PostResponseDto.fromEntity(post);
    }

    public PageResponseDto<PostResponseDto> getPostList(Post.BoardType boardType, Integer pageNum, Integer size) {
        int safePage = pageNum == null || pageNum < 0 ? 0 : pageNum;
        int safeSize = size == null || size <= 0 ? 10 : size;

        Pageable pageable = PageRequest.of(
                safePage,
                safeSize,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<PostResponseDto> page = postRepository
                .findByBoardTypeAndStatus(boardType, Post.PostStatus.ACTIVE, pageable)
                .map(PostResponseDto::fromEntity);

        return PageResponseDto.from(page);
    }

    public Long updatePost(Long postId, PostRequestDto postRequestDto) {
        User user = userRepository.findById(postRequestDto.userId()).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        if (!user.getId().equals(post.getUser().getId())) {
            throw new CommonException(ErrorCode.ACCESS_DENIED_ERROR);
        }

        post.updatePost(
                postRequestDto.title(),
                postRequestDto.content()
        );

        return post.getId();
    }

    public Boolean deletePost(Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        if (!user.getId().equals(post.getUser().getId())) {
            throw new CommonException(ErrorCode.ACCESS_DENIED_ERROR);
        }

        post.deletePost();

        return true;
    }
}
