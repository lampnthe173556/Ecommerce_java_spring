package vn.edu.fpt.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.shopapp.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
