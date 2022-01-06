package com.productbackendbtvn.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        })
})
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfManufacture;
    private String avatar;
    private String description;
}
