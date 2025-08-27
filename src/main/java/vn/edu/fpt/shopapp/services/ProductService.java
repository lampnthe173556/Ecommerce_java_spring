package vn.edu.fpt.shopapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.shopapp.dto.ProductDTO;
import vn.edu.fpt.shopapp.dto.ProductImageDTO;
import vn.edu.fpt.shopapp.exceptions.DataNotFoundException;
import vn.edu.fpt.shopapp.exceptions.InvalidParamException;
import vn.edu.fpt.shopapp.models.Category;
import vn.edu.fpt.shopapp.models.Product;
import vn.edu.fpt.shopapp.models.ProductImage;
import vn.edu.fpt.shopapp.repositories.CategoryRepository;
import vn.edu.fpt.shopapp.repositories.ProductImageRepository;
import vn.edu.fpt.shopapp.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
        Category exitstingCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find category with id: "
                                + productDTO.getCategoryId()));

        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .category(exitstingCategory)
                .build();

        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(Long id) throws DataNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id: " + id));
    }

    @Override
    public Page<Product> getAllProducts(PageRequest pageRequest) {
        //lay danh sach san pham theo page va limit

        return productRepository.findAll(pageRequest);
    }

    @Override
    public Product updateProduct(
            Long id,
            ProductDTO productDTO) throws DataNotFoundException {
        Product existingProduct = productRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Data not found with id: " + id));
        if (existingProduct != null) {
            Category existingCategory = categoryRepository
                    .findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new DataNotFoundException("Cannot find category with id: " + productDTO.getCategoryId()));
            existingProduct.setName(productDTO.getName());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setCategory(existingCategory);
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) throws DataNotFoundException {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id: " + id));
        productRepository.delete(product);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
    @Override
    public ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws DataNotFoundException, InvalidParamException {
        Product existingProduct = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find product with id: " + productId));
        ProductImage newProductImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        int size = productImageRepository.findByProductId(productId).size();
        if (size >= 5) {
            throw new InvalidParamException("Number of images <= 5");
        }
        return productImageRepository.save(newProductImage);
    }
}
