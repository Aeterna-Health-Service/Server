package dgu.triple.aeterna.repository;

import dgu.triple.aeterna.domain.Comment;
import dgu.triple.aeterna.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostAndStatus(
            Post post,
            Comment.CommentStatus status,
            Pageable pageable
    );
}
