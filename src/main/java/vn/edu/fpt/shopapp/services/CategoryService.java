package vn.edu.fpt.shopapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.fpt.shopapp.dto.CategoryDTO;
import vn.edu.fpt.shopapp.models.Category;
import vn.edu.fpt.shopapp.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = Category
                .builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Category categoryUpdate = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryUpdate.setName(categoryDTO.getName());
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
