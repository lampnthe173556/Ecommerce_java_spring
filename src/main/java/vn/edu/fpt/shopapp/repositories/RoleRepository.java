package vn.edu.fpt.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.shopapp.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);
}
