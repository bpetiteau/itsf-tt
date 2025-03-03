package io.itsf.dtos.builders;

import io.itsf.dtos.ProductReceiptDto;
import io.itsf.dtos.ReceiptDto;

import java.math.BigDecimal;
import java.util.List;

public class ReceiptDtoBuilder {
    private final ReceiptDto receiptDto = new ReceiptDto();

    public ReceiptDtoBuilder productReceipts(List<ProductReceiptDto> productReceipts) {
        receiptDto.setProductReceipts(productReceipts);
        return this;
    }

    public ReceiptDtoBuilder totalTaxes(BigDecimal totalTaxes) {
        receiptDto.setTotalTaxes(totalTaxes);
        return this;
    }

    public ReceiptDtoBuilder totalPrice(BigDecimal totalPrice) {
        receiptDto.setTotalPrice(totalPrice);
        return this;
    }

    public ReceiptDto build() {
        return receiptDto;
    }
}
