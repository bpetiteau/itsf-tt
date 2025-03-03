package io.itsf.services.impl;

import io.itsf.dtos.ProductDto;
import io.itsf.enums.ProductType;
import io.itsf.services.TaxComputationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Service
public class TaxComputationServiceImpl implements TaxComputationService {

    private static final List<ProductType> TAX_FREE_PRODUCT_TYPES =
            Arrays.asList(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL);

    @Override
    public BigDecimal computeProductTaxes(ProductDto product) {
        BigDecimal taxRate = BigDecimal.valueOf(0.0);
        if (product.getType() != null && !TAX_FREE_PRODUCT_TYPES.contains(product.getType())) {
            taxRate = taxRate.add(BigDecimal.valueOf(0.1));
        }
        if (product.isImported()) {
            taxRate = taxRate.add(BigDecimal.valueOf(0.05));
        }
        return product.getPrice()
                .multiply(taxRate)
                .multiply(BigDecimal.valueOf(20))
                .setScale(0, RoundingMode.UP)
                .divide(BigDecimal.valueOf(20));
    }
}
