package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.Comment;
import dgu.triple.aeterna.domain.Post;
import dgu.triple.aeterna.domain.User;

public record CommentRequestDto(
        Long userId,
        String content
) {
    public Comment toEntity(Post post, User user) {
        return Comment.builder()
                .post(post)
                .user(user)
                .content(content)
                .status(Comment.CommentStatus.ACTIVE)
                .build();
    }
}
