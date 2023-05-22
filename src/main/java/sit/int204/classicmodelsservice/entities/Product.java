package sit.int204.classicmodelsservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    private String productCode;
    private String productName;
    private String productLine;
    private String productDescription;
    private String productScale;
    private String productVendor;
    private Integer quantityInStock;
    private Double buyPrice;
    @Column(name = "MSRP")
    private Double price;
}
