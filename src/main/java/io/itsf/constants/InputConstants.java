package io.itsf.constants;

import io.itsf.dtos.ProductDto;
import io.itsf.dtos.builders.ProductDtoBuilder;
import io.itsf.enums.ProductType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public final class InputConstants {
    private InputConstants() {}

    public static final List<ProductDto> INPUT_1 = Arrays.asList(
        new ProductDtoBuilder().name("book").type(ProductType.BOOK).price(BigDecimal.valueOf(12.49)).build(),
        new ProductDtoBuilder().name("music CD").type(ProductType.OTHER).price(BigDecimal.valueOf(14.99)).build(),
        new ProductDtoBuilder().name("chocolate bar").type(ProductType.FOOD).price(BigDecimal.valueOf(0.85)).build()
    );
    public static final List<ProductDto> INPUT_2 = Arrays.asList(
        new ProductDtoBuilder().name("imported box of chocolates").type(ProductType.FOOD).imported().price(BigDecimal.valueOf(10.00)).build(),
        new ProductDtoBuilder().name("imported bottle of perfume").type(ProductType.OTHER).imported().price(BigDecimal.valueOf(47.50)).build()
    );
    public static final List<ProductDto> INPUT_3 = Arrays.asList(
        new ProductDtoBuilder().name("imported bottle of perfume").type(ProductType.OTHER).imported().price(BigDecimal.valueOf(27.99)).build(),
        new ProductDtoBuilder().name("bottle of perfume").type(ProductType.OTHER).price(BigDecimal.valueOf(18.99)).build(),
        new ProductDtoBuilder().name("packet of headache pills").type(ProductType.MEDICAL).price(BigDecimal.valueOf(9.75)).build(),
        new ProductDtoBuilder().name("imported box of chocolates").type(ProductType.FOOD).imported().price(BigDecimal.valueOf(11.25)).build()
    );
}