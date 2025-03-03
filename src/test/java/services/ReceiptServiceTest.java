package services;

import io.itsf.dtos.ProductDto;
import io.itsf.dtos.ReceiptDto;
import io.itsf.dtos.builders.ProductDtoBuilder;
import io.itsf.dtos.builders.ProductReceiptDtoBuilder;
import io.itsf.dtos.builders.ReceiptDtoBuilder;
import io.itsf.enums.ProductType;
import io.itsf.services.ReceiptService;
import io.itsf.services.TaxComputationService;
import io.itsf.services.impl.ReceiptServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTest {

    @Mock
    private TaxComputationService taxComputationService;

    private ReceiptService receiptService;

    @BeforeEach
    public void init() {
        receiptService = new ReceiptServiceImpl(taxComputationService);
    }

    @Test
    public void shouldGenerateReceipt() {
        // prepare
        Mockito.when(taxComputationService.computeProductTaxes(Mockito.any())).thenReturn(
                BigDecimal.valueOf(4.2),
                BigDecimal.valueOf(1.9),
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.6));
        List<ProductDto> products = Arrays.asList(
                new ProductDtoBuilder()
                    .name("imported bottle of perfume")
                    .type(ProductType.OTHER)
                    .imported()
                    .price(BigDecimal.valueOf(27.99))
                    .build(),
                new ProductDtoBuilder()
                    .name("bottle of perfume")
                    .type(ProductType.OTHER)
                    .price(BigDecimal.valueOf(18.99))
                    .build(),
                new ProductDtoBuilder()
                    .name("packet of headache pills")
                    .type(ProductType.MEDICAL)
                    .price(BigDecimal.valueOf(9.75))
                    .build(),
                new ProductDtoBuilder()
                    .name("imported box of chocolates")
                    .type(ProductType.FOOD)
                    .imported()
                    .price(BigDecimal.valueOf(11.25))
                    .build());
        // execute
        ReceiptDto receipt = receiptService.generateReceipt(products);
        // verify
        Mockito.verify(taxComputationService, Mockito.times(4)).computeProductTaxes(Mockito.any());
        Mockito.verify(taxComputationService).computeProductTaxes(products.get(0));
        Mockito.verify(taxComputationService).computeProductTaxes(products.get(1));
        Mockito.verify(taxComputationService).computeProductTaxes(products.get(2));
        Mockito.verify(taxComputationService).computeProductTaxes(products.get(3));

        assertThat(receipt).usingRecursiveComparison().isEqualTo(new ReceiptDtoBuilder()
            .productReceipts(Arrays.asList(
                new ProductReceiptDtoBuilder()
                    .product(new ProductDtoBuilder()
                        .name("imported bottle of perfume")
                        .type(ProductType.OTHER)
                        .imported()
                        .price(BigDecimal.valueOf(27.99))
                        .build())
                    .totalTaxes(BigDecimal.valueOf(4.2))
                    .totalPrice(BigDecimal.valueOf(32.19))
                    .build(),
                new ProductReceiptDtoBuilder()
                    .product(new ProductDtoBuilder()
                        .name("bottle of perfume")
                        .type(ProductType.OTHER)
                        .price(BigDecimal.valueOf(18.99))
                        .build())
                    .totalTaxes(BigDecimal.valueOf(1.9))
                    .totalPrice(BigDecimal.valueOf(20.89))
                    .build(),
                new ProductReceiptDtoBuilder()
                    .product(new ProductDtoBuilder()
                        .name("packet of headache pills")
                        .type(ProductType.MEDICAL)
                        .price(BigDecimal.valueOf(9.75))
                        .build())
                    .totalTaxes(BigDecimal.valueOf(0.0))
                    .totalPrice(BigDecimal.valueOf(9.75))
                    .build(),
                new ProductReceiptDtoBuilder()
                    .product(new ProductDtoBuilder()
                        .name("imported box of chocolates")
                        .type(ProductType.FOOD)
                        .imported()
                        .price(BigDecimal.valueOf(11.25))
                        .build())
                    .totalTaxes(BigDecimal.valueOf(0.6))
                    .totalPrice(BigDecimal.valueOf(11.85))
                    .build()
            ))
            .totalTaxes(BigDecimal.valueOf(6.70))
            .totalPrice(BigDecimal.valueOf(74.68))
            .build()
        );
    }
}
