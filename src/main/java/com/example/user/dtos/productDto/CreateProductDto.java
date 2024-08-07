package com.example.user.dtos.productDto;

import java.util.UUID;

public record CreateProductDto(UUID id, String name, Integer price) {
}
