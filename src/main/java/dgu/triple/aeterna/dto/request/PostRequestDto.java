package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.Post;
import dgu.triple.aeterna.domain.User;

public record PostRequestDto(
        Long userId,
        Post.PostType postType,
        String title,
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

