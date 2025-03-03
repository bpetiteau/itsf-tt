package io.itsf.dtos;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReceiptDto {
    private List<ProductReceiptDto> productReceipts;
    private BigDecimal totalTaxes;
    private BigDecimal totalPrice;

    public List<ProductReceiptDto> getProductReceipts() {
        return productReceipts;
    }

    public void setProductReceipts(List<ProductReceiptDto> productReceipts) {
        this.productReceipts = productReceipts;
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
        StringBuilder sb = new StringBuilder();
        Optional.of(productReceipts).orElse(Collections.emptyList())
                .forEach(productReceipt -> sb.append(productReceipt).append('\n'));
        sb.append("Sales Taxes: " )
                .append(String.format( "%.2f", totalTaxes))
                .append(" Total: ")
                .append(String.format( "%.2f", totalPrice));
        return sb.toString();
    }
}
