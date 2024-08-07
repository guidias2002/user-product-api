package com.example.user.domains.product;

import com.example.user.dtos.productDto.CreateProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    private Integer price;

    public Product(CreateProductDto createProductDto) {
        this.id = createProductDto.id();
        this.name = createProductDto.name();
        this.price = createProductDto.price();
    }
}
