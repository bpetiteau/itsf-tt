package io.itsf.dtos.builders;

import io.itsf.dtos.ProductDto;
import io.itsf.dtos.ProductReceiptDto;

import java.math.BigDecimal;

public class ProductReceiptDtoBuilder {
    private final ProductReceiptDto productReceiptDto = new ProductReceiptDto();

    public ProductReceiptDtoBuilder product(ProductDto product) {
        productReceiptDto.setProduct(product);
        return this;
    }

    public ProductReceiptDtoBuilder totalTaxes(BigDecimal totalTaxes) {
        productReceiptDto.setTotalTaxes(totalTaxes);
        return this;
    }

    public ProductReceiptDtoBuilder totalPrice(BigDecimal totalPrice) {
        productReceiptDto.setTotalPrice(totalPrice);
        return this;
    }

    public ProductReceiptDto build() {
        return productReceiptDto;
    }
}
