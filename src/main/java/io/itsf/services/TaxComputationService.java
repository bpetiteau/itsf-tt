package io.itsf.services;

import io.itsf.dtos.ProductDto;

import java.math.BigDecimal;

public interface TaxComputationService {
    BigDecimal computeProductTaxes(ProductDto product);
}
