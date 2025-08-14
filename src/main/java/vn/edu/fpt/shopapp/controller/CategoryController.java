package vn.edu.fpt.shopapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.shopapp.dto.CategoryDTO;

import java.util.List;

@RestController // danh dau la tra ve dang rest api
@RequestMapping("api/v1/categories") //request mapping
//@Validated => kiem tra toan bo ơ muc class
public class CategoryController {
    @GetMapping("") //http://localhost:8080/api/v1/categories?page=1&limit=10
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("success page=" + page + " ,limit=" + limit);
    }

    //neu tam so truyen vao la 1 object thi sao => data transfer object => dto = request objetc
    @PostMapping("")
    public ResponseEntity<String> createCategories(
            @RequestBody @Valid CategoryDTO categoryDTO, //@valid ở phạm vi phương thức
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> list = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body("Error " + list.toString());
        }
        return ResponseEntity.ok("this is insert " + categoryDTO.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategories(@PathVariable Long id) {
        return ResponseEntity.ok("this is update " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategories(@PathVariable Long id) {
        return ResponseEntity.ok("this is delete " + id);
    }
}
