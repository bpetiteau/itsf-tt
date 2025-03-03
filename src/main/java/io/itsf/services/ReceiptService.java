package io.itsf.services;

import io.itsf.dtos.ProductDto;
import io.itsf.dtos.ReceiptDto;

import java.util.List;

public interface ReceiptService {
    ReceiptDto generateReceipt(List<ProductDto> products);
}
