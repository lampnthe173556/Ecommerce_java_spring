package vn.edu.fpt.shopapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.shopapp.dto.ProductDTO;
import vn.edu.fpt.shopapp.dto.ProductImageDTO;
import vn.edu.fpt.shopapp.exceptions.DataNotFoundException;
import vn.edu.fpt.shopapp.models.Product;
import vn.edu.fpt.shopapp.models.ProductImage;
import vn.edu.fpt.shopapp.services.IProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/${api.prefix}/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("")
    public ResponseEntity<String> createProduct(
            @Valid @ModelAttribute ProductDTO productDTO,

            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errors = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
            }
            Product newProduct = iProductService.createProduct(productDTO);

        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("product created successfully");

    }

    @PostMapping(value = "uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @PathVariable Long id,
            @ModelAttribute("files") List<MultipartFile> files
    ) {
        try {
            Product existingProduct = iProductService.getProductById(id);
            files = files == null ? new ArrayList<>() : files;
            List<ProductImage> productImages = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file != null) {
                    if (file.getSize() > 10 * 1024 * 1024) {
                        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is to large");
                    }
                    String contentType = file.getContentType();
                    if (file.getSize() == 0) {
                        continue;
                    }
                    if (contentType == null || !contentType.startsWith("image/")) {
                        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                    }
                }
                //check size and fomart file
                String fileName = storeFile(file);
                ProductImage productImage = iProductService.createProductImage(
                        existingProduct.getId(), ProductImageDTO
                                .builder()
                                .productId(existingProduct.getId())
                                .imageUrl(fileName)
                                .build());
                productImages.add(productImage);
            }
            return ResponseEntity.ok(productImages.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("") //http://localhost:8080/api/v1/product
    public ResponseEntity<String> getProducts(
//            @RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "limit", defaultValue = "2") int limit
    ) {
        return ResponseEntity.ok("get products here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String productId) {
        return ResponseEntity.ok("Product with ID: " + productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body("delete product successfully");
    }

    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //them uuid vao trc ten de file dam bao la ten duy nhat
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        //duong dan den thu muc chua file
        Path uploadDir = Paths.get("uploads");
        //check exits
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        //path day du den file
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        //copy vao thu muc dich
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}
