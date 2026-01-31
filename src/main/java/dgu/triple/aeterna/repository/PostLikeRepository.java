package dgu.triple.aeterna.repository;

import dgu.triple.aeterna.domain.Post;
import dgu.triple.aeterna.domain.PostLike;
import dgu.triple.aeterna.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    PostLike findByUserAndPost(User user, Post post);
}
