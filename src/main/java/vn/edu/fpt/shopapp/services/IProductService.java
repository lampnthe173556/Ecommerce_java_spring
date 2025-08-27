package vn.edu.fpt.shopapp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.shopapp.dto.ProductDTO;
import vn.edu.fpt.shopapp.dto.ProductImageDTO;
import vn.edu.fpt.shopapp.exceptions.DataNotFoundException;
import vn.edu.fpt.shopapp.exceptions.InvalidParamException;
import vn.edu.fpt.shopapp.models.Product;
import vn.edu.fpt.shopapp.models.ProductImage;
@Service
public interface IProductService {
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException;

    Product getProductById(Long id) throws DataNotFoundException;

    Page<Product> getAllProducts(PageRequest pageRequest);

    Product updateProduct(Long id, ProductDTO productDTO) throws DataNotFoundException;

    void deleteProduct(Long id) throws DataNotFoundException;

    boolean existsByName(String name);

    ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws DataNotFoundException, InvalidParamException;
}
