package dgu.triple.aeterna.service;

import dgu.triple.aeterna.domain.Post;
import dgu.triple.aeterna.domain.PostLike;
import dgu.triple.aeterna.domain.User;
import dgu.triple.aeterna.exception.CommonException;
import dgu.triple.aeterna.exception.ErrorCode;
import dgu.triple.aeterna.repository.PostLikeRepository;
import dgu.triple.aeterna.repository.PostRepository;
import dgu.triple.aeterna.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    public Boolean doPostLike(Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        PostLike postLike = PostLike.builder()
                .post(post)
                .user(user)
                .build();

        postLikeRepository.save(postLike);
        post.increaseLikeCount();

        return true;
    }

    public Boolean undoPostLike(Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        PostLike postLike = postLikeRepository.findByUserAndPost(user, post);
        post.decreaseLikeCount();

        postLikeRepository.delete(postLike);
        return true;
    }
}
