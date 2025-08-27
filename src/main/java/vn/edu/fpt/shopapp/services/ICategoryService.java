package vn.edu.fpt.shopapp.services;

import org.springframework.stereotype.Service;
import vn.edu.fpt.shopapp.dto.CategoryDTO;
import vn.edu.fpt.shopapp.models.Category;

import java.util.List;
@Service
public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}
