package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Page<Product> getProductByPage(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    public List<Product> getProductByContext(String name, String description) {
        return repository.findProductByProductNameStartingWithOrProductDescriptionContaining(name, description);
    }

    public List<Product> getProductsPriceBetween(double lower, double higher) {
        if (lower > higher) {
            return repository.findProductByPriceBetweenOrderByPrice(higher, lower);
        } else {
            return repository.findProductByPriceBetweenOrderByPrice(lower, higher);
        }
    }

    public List<Product> getProductsByProductLine(String productLine, String sortBy){
        Sort sort = Sort.by(sortBy);
        return repository.findByProductLine(productLine, sort);
    }

    public Product updateProduct(String productCode, Product product) {
        Product existingProduct = repository.findById(productCode).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Product Code " + productCode + " DOES NOT EXIST !!!")
        );
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantityInStock(product.getQuantityInStock());
        existingProduct.setBuyPrice(product.getBuyPrice());
        return repository.saveAndFlush(existingProduct);
    }

    public Product addNewProduct(Product product) {
        return repository.saveAndFlush(product);
    }
}
