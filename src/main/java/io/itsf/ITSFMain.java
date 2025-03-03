package io.itsf;

import io.itsf.config.DependencyInjectionConfig;
import io.itsf.constants.InputConstants;
import io.itsf.dtos.ProductDto;
import io.itsf.dtos.ReceiptDto;
import io.itsf.services.ReceiptService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class ITSFMain {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DependencyInjectionConfig.class);
        ReceiptService receiptService = context.getBean(ReceiptService.class);
        generateAndPrintReceipt(receiptService, args[0]);
        context.close();
    }

    public static void generateAndPrintReceipt(ReceiptService receiptService, String arg) {
        List<ProductDto> products = switch (arg) {
            case "1" -> InputConstants.INPUT_1;
            case "2" -> InputConstants.INPUT_2;
            case "3" -> InputConstants.INPUT_3;
            default -> new ArrayList<>();
        };
        ReceiptDto receipt = receiptService.generateReceipt(products);
        System.out.println(receipt);
    }
}
