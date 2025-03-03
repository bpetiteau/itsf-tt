package io.itsf.dtos.builders;

import io.itsf.dtos.ProductDto;
import io.itsf.enums.ProductType;

import java.math.BigDecimal;

public class ProductDtoBuilder {
    private final ProductDto productDto = new ProductDto();

    public ProductDtoBuilder name(String name) {
        productDto.setName(name);
        return this;
    }

    public ProductDtoBuilder type(ProductType type) {
        productDto.setType(type);
        return this;
    }

    public ProductDtoBuilder imported() {
        productDto.setImported(true);
        return this;
    }

    public ProductDtoBuilder price(BigDecimal price) {
        productDto.setPrice(price);
        return this;
    }

    public ProductDto build() {
        return productDto;
    }
}
