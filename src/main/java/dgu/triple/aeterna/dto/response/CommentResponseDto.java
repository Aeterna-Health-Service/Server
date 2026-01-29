package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.Comment;
import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        Long userId,
        String content,
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
