package dgu.triple.aeterna.repository;

import dgu.triple.aeterna.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderAndSocialToken(User.Provider provider, String socialToken);
}
