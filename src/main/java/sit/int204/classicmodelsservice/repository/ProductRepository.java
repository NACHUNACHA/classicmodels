package sit.int204.classicmodelsservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findProductByProductNameStartingWithOrProductDescriptionContaining(String name, String description);
    List<Product> findProductByPriceBetweenOrderByPrice(Double lower, Double higher);
    List<Product> findByProductLine(String productLine, Sort sort);




//    Page<Product> findProductByProductNameStartingWithOrProductDescriptionContaining(String name, String description, Pageable pageable);
//    List<Product> findProductByProductNameStartingWithOrProductDescriptionContaining(String name, String description, Sort sort);
//    Page<Product> findProductByPriceBetweenOrderByPriceDesc(Double lower, Double higher, Pageable pageable);
}
