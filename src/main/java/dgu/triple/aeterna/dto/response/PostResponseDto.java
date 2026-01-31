package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.Post;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        Long userId,
        String userName,
        Post.PostType postType,
        String title,
        String content,
        Integer viewCount,
        Integer likeCount,
        Integer commentCount,
        Boolean isLiked,
        LocalDateTime createdAt
) {
    public static PostResponseDto fromEntity(Post e, Boolean isLiked) {
        return new PostResponseDto(
                e.getId(),
                e.getUser().getId(),
                e.getUser().getName(),
                e.getPostType(),
                e.getTitle(),
                e.getContent(),
                e.getViewCount(),
                e.getLikeCount(),
                e.getCommentCount(),
                isLiked,
                e.getCreatedAt()
        );
    }

    public static PostResponseDto fromEntity(Post e) {
        return new PostResponseDto(
                e.getId(),
                e.getUser().getId(),
                e.getUser().getName(),
                e.getPostType(),
                e.getTitle(),
                e.getContent(),
                e.getViewCount(),
                e.getLikeCount(),
                e.getCommentCount(),
                true,
                e.getCreatedAt()
        );
    }
}
