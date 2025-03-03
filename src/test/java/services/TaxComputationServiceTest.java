package services;

import io.itsf.dtos.ProductDto;
import io.itsf.dtos.builders.ProductDtoBuilder;
import io.itsf.enums.ProductType;
import io.itsf.services.TaxComputationService;
import io.itsf.services.impl.TaxComputationServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxComputationServiceTest {

    private final TaxComputationService taxComputationService = new TaxComputationServiceImpl();

    @Test
    public void shouldComputeProductTaxes_noTaxes() {
        // prepare
        ProductDto product = new ProductDtoBuilder().type(ProductType.BOOK).price(BigDecimal.valueOf(10.0)).build();
        // execute
        BigDecimal taxes = taxComputationService.computeProductTaxes(product);
        // verify
        assertThat(taxes).usingComparator(BigDecimal::compareTo).isEqualTo(BigDecimal.valueOf(0.0));
    }

    @Test
    public void shouldComputeProductTaxes_productTypeTaxes() {
        // prepare
        ProductDto product = new ProductDtoBuilder().type(ProductType.OTHER).price(BigDecimal.valueOf(10.0)).build();
        // execute
        BigDecimal taxes = taxComputationService.computeProductTaxes(product);
        // verify
        assertThat(taxes).usingComparator(BigDecimal::compareTo).isEqualTo(BigDecimal.valueOf(1.0));
    }

    @Test
    public void shouldComputeProductTaxes_importTaxes() {
        // prepare
        ProductDto product = new ProductDtoBuilder().type(ProductType.BOOK).price(BigDecimal.valueOf(10.0)).imported().build();
        // execute
        BigDecimal taxes = taxComputationService.computeProductTaxes(product);
        // verify
        assertThat(taxes).usingComparator(BigDecimal::compareTo).isEqualTo(BigDecimal.valueOf(0.5));
    }

    @Test
    public void shouldComputeProductTaxes_productTypeAndImportTaxes() {
        // prepare
        ProductDto product = new ProductDtoBuilder().type(ProductType.OTHER).imported().price(BigDecimal.valueOf(10.0)).build();
        // execute
        BigDecimal taxes = taxComputationService.computeProductTaxes(product);
        // verify
        assertThat(taxes).usingComparator(BigDecimal::compareTo).isEqualTo(BigDecimal.valueOf(1.5));
    }

    @Test
    public void shouldComputeProductTaxes_roundedResult() {
        // prepare
        ProductDto product = new ProductDtoBuilder().type(ProductType.FOOD).imported().price(BigDecimal.valueOf(11.25)).build();
        // execute
        BigDecimal taxes = taxComputationService.computeProductTaxes(product);
        // verify
        assertThat(taxes).usingComparator(BigDecimal::compareTo).isEqualTo(BigDecimal.valueOf(0.6));
    }
}
