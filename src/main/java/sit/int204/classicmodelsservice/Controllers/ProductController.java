package sit.int204.classicmodelsservice.Controllers;

import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Page<Product> getProductPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam(defaultValue = "productCode") String sortBy
            ) {
        return productService.getProductByPage(page, size, sortBy);
    }

    @GetMapping("/contexts")
    public List<Product> getProductByContext(@RequestParam String name, @RequestParam String description) {
        return productService.getProductByContext(name, description);
    }

    @GetMapping("/{low}/{higher}")
    public List<Product> getProductByPrice(@PathVariable double low, @PathVariable double higher) {
        return productService.getProductsPriceBetween(low, higher);
    }

    @GetMapping("/{productLine}")
    public List<Product> getProductByProductLine(@PathVariable String productLine, @RequestParam(defaultValue = "productCode") String sort) {
        return productService.getProductsByProductLine(productLine, sort);
    }

    @PutMapping("/{productCode}")
    public Product updateProduct(@RequestBody Product product, @PathVariable String productCode) {
        return productService.updateProduct(productCode, product);
    }

    @PostMapping("")
    public Product addNewProducts(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }
}
