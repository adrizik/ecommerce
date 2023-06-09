package ecommerce.Repository;

import ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("FROM Product WHERE LOWER(title) LIKE %:title%")
    List<Product> searchProduct(@Param("title") String productName);
}
