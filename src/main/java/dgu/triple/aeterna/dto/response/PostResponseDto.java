package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "게시글 응답 DTO")
public record PostResponseDto(

        @Schema(description = "게시글 ID", example = "10")
        Long id,

        @Schema(description = "작성자 사용자 ID", example = "1")
        Long userId,

        @Schema(description = "작성자 이름", example = "홍길동")
        String userName,

        @Schema(
                description = """
                        게시글 타입(콘텐츠 분류)
                        MEAL(식단), WORKOUT(운동), BODY(바디 변화), DAILY(일상)
                        """,
                allowableValues = {"MEAL", "WORKOUT", "BODY", "DAILY"},
                example = "DAILY"
        )
        Post.PostType postType,

        @Schema(description = "제목", example = "오늘의 루틴 공유합니다")
        String title,

        @Schema(description = "내용", example = "오늘은 하체 위주로 진행했고, ...")
        String content,

        @Schema(description = "조회수", example = "123")
        Integer viewCount,

        @Schema(description = "좋아요 수", example = "7")
        Integer likeCount,

        @Schema(description = "댓글 수", example = "3")
        Integer commentCount,

        @Schema(description = "현재 사용자의 좋아요 여부", example = "true")
        Boolean isLiked,

        @Schema(description = "작성 일시", example = "2026-01-30T12:34:56")
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
