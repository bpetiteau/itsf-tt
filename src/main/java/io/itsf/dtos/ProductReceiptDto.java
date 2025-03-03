package io.itsf.dtos;

import java.math.BigDecimal;

public class ProductReceiptDto {
    private ProductDto product;
    private BigDecimal totalTaxes;
    private BigDecimal totalPrice;

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        if (product == null || product.getName() == null) {
            return "";
        }
        return "1 " + product.getName() + ": " + String.format("%.2f", totalPrice);
    }
}
