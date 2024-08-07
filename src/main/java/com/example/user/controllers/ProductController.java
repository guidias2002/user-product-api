package com.example.user.controllers;

import com.example.user.domains.product.Product;
import com.example.user.dtos.productDto.CreateProductDto;
import com.example.user.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody CreateProductDto createProductDto){
        this.productService.createProduct(createProductDto);

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> products = this.productService.listProducts();

        return ResponseEntity.ok(products);
    }
}
