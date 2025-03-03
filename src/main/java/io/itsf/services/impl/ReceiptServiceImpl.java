package io.itsf.services.impl;

import io.itsf.dtos.ProductDto;
import io.itsf.dtos.ProductReceiptDto;
import io.itsf.dtos.ReceiptDto;
import io.itsf.dtos.builders.ProductReceiptDtoBuilder;
import io.itsf.dtos.builders.ReceiptDtoBuilder;
import io.itsf.services.ReceiptService;
import io.itsf.services.TaxComputationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final TaxComputationService taxComputationService;

    public ReceiptServiceImpl(TaxComputationService taxComputationService) {
        this.taxComputationService = taxComputationService;
    }

    @Override
    public ReceiptDto generateReceipt(List<ProductDto> products) {
        List<ProductReceiptDto> productReceipts = products.stream().map(this::generateProductReceipt).toList();
        BigDecimal totalTaxes = productReceipts.stream().reduce(BigDecimal.ZERO, (subtotal, element) ->
                subtotal.add(element.getTotalTaxes()), BigDecimal::add);
        BigDecimal totalPrice = productReceipts.stream().reduce(BigDecimal.ZERO, (subtotal, element) ->
                subtotal.add(element.getTotalPrice()), BigDecimal::add);
        return new ReceiptDtoBuilder()
                .productReceipts(productReceipts)
                .totalTaxes(totalTaxes)
                .totalPrice(totalPrice)
                .build();
    }

    private ProductReceiptDto generateProductReceipt(ProductDto product) {
        BigDecimal totalTaxes = taxComputationService.computeProductTaxes(product);
        BigDecimal totalPrice = product.getPrice().add(totalTaxes);
        return new ProductReceiptDtoBuilder()
                .product(product)
                .totalTaxes(totalTaxes)
                .totalPrice(totalPrice)
                .build();
    }
}
