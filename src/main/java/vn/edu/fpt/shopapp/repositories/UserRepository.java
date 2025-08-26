package vn.edu.fpt.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.shopapp.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
