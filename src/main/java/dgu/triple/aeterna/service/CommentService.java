package dgu.triple.aeterna.service;

import dgu.triple.aeterna.domain.Comment;
import dgu.triple.aeterna.domain.Post;
import dgu.triple.aeterna.domain.User;
import dgu.triple.aeterna.dto.request.CommentRequestDto;
import dgu.triple.aeterna.dto.response.CommentResponseDto;
import dgu.triple.aeterna.dto.response.PageResponseDto;
import dgu.triple.aeterna.exception.CommonException;
import dgu.triple.aeterna.exception.ErrorCode;
import dgu.triple.aeterna.repository.CommentRepository;
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
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Long createComment(Long postId, CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(commentRequestDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        Comment comment = commentRequestDto.toEntity(post, user);

        comment = commentRepository.save(comment);
        post.increaseCommentCount();

        return comment.getId();
    }

    public PageResponseDto<CommentResponseDto> getComments(Long postId, Integer pageNum, Integer size) {
        int safePage = pageNum == null || pageNum < 0 ? 0 : pageNum;
        int safeSize = size == null || size <= 0 ? 10 : size;

        Post post = postRepository.findById(postId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        Pageable pageable = PageRequest.of(
                safePage,
                safeSize,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<CommentResponseDto> page = commentRepository
                .findByPostAndStatus(post, Comment.CommentStatus.ACTIVE, pageable)
                .map(CommentResponseDto::fromEntity);

        return PageResponseDto.from(page);
    }

    public Boolean deleteComment(Long commentId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        if (!user.getId().equals(comment.getUser().getId())) {
            throw new CommonException(ErrorCode.ACCESS_DENIED_ERROR);
        }

        comment.getPost().decreaseCommentCount();
        comment.deleteComment();

        return true;
    }


}
