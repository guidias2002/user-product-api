package com.example.user.services;

import com.example.user.domains.product.Product;
import com.example.user.dtos.productDto.CreateProductDto;
import com.example.user.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(CreateProductDto createProductDto) {
        Product newProduct = new Product(createProductDto);

        this.productRepository.save(newProduct);
    }

    public List<Product> listProducts(){
        return this.productRepository.findAll();
    }
}
