package ecommerce.Controller;

import ecommerce.Model.Product;
import ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * View all products
     * GET localhost:9000/products
     */

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> product = productService.getAllProduct();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    /**
     * Find products by name or keywords
     * "w" will bring all items with the word "w" in them
     * GET localhost:9000/products/name/{name}
     */
    @GetMapping("products/name/{name}")
    public List<Product> searchProduct(@PathVariable String name) {
        return productService.searchProduct(name);
    }

}
