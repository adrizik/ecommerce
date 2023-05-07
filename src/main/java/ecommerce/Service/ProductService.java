package ecommerce.Service;

import ecommerce.Model.Product;
import ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public  ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get all products
     */
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    /**
     * Search through products
     */
    public List<Product> searchProduct(String title) {
        return productRepository.searchProduct(title);
    }
}
