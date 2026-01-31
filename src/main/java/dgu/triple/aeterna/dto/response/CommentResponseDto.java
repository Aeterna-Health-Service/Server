package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "댓글 응답 DTO")
public record CommentResponseDto(

        @Schema(description = "댓글 ID", example = "100")
        Long id,

        @Schema(description = "작성자 사용자 ID", example = "1")
        Long userId,

        @Schema(description = "댓글 내용", example = "이 글 정말 도움됐어요!")
        String content,

        @Schema(description = "작성 일시", example = "2026-01-31T14:22:10")
        LocalDateTime createdAt
) {
    public static CommentResponseDto fromEntity(Comment e) {
        return new CommentResponseDto(
                e.getId(),
                e.getUser().getId(),
                e.getContent(),
                e.getCreatedAt()
        );
    }
}
