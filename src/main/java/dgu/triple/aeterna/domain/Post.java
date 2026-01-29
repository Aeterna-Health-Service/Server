package dgu.triple.aeterna.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type", nullable = false)
    private PostType postType;

    @Enumerated(EnumType.STRING)
    @Column(name = "board_type")
    private BoardType boardType;

    @Column(name = "title", length = 200)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "media_url", length = 500)
    private String mediaUrl;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @Column(name = "comment_count", nullable = false)
    private Integer commentCount;

    @Column(name = "view_count", nullable = false)
    private Integer viewCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PostStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Post(
            User user,
            PostType postType,
            BoardType boardType,
            String title,
            String content,
            Integer likeCount,
            Integer commentCount,
            Integer viewCount,
            PostStatus status
    ) {
        this.user = user;
        this.postType = postType;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.status = status;
    }

    public void addBoardType(BoardType boardType) {
        this.boardType = boardType;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }

    public void increaseCommentCount() {
        this.commentCount++;
    }

    public void decreaseCommentCount() {
        this.commentCount--;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void deletePost() {
        this.status = PostStatus.DELETED;
    }

    public enum PostType { MEAL, WORKOUT, BODY, DAILY }
    public enum BoardType { TIP, ROUTINE, MEAL, FREE }
    public enum PostStatus { ACTIVE, DELETED }
}

