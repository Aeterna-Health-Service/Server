package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.Post;
import dgu.triple.aeterna.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 생성/수정 요청 DTO")
public record PostRequestDto(

        @Schema(description = "작성자 사용자 ID", example = "1")
        Long userId,

        @Schema(
                description = """
                        게시글 타입(콘텐츠 분류)
                        MEAL(식단), WORKOUT(운동), BODY(바디 변화), DAILY(일상)
                        근데 필요없을듯 함
                        """,
                allowableValues = {"MEAL", "WORKOUT", "BODY", "DAILY"},
                example = "DAILY"
        )
        Post.PostType postType,

        @Schema(description = "제목", example = "오늘의 루틴 공유합니다")
        String title,

        @Schema(description = "내용", example = "오늘은 하체 위주로 진행했고, ...")
        String content
) {
    public Post toEntity(User user) {
        return Post.builder()
                .user(user)
                .postType(postType)
                .title(title)
                .content(content)
                .likeCount(0)
                .commentCount(0)
                .viewCount(0)
                .status(Post.PostStatus.ACTIVE)
                .build();
    }
}
