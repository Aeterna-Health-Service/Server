package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.Comment;
import dgu.triple.aeterna.domain.Post;
import dgu.triple.aeterna.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "댓글 생성 요청 DTO")
public record CommentRequestDto(

        @Schema(description = "댓글 작성자 사용자 ID", example = "1")
        Long userId,

        @Schema(description = "댓글 내용", example = "이 글 정말 도움됐어요!")
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
