package Util;

import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.ProductImportHistory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReportTestHelper {
    private static Product product1 = Product.builder()
            .id(1L)
            .name("Iphone 14 Pro Black")
            .build();

    private static Product product2 = Product.builder()
            .id(2L)
            .name("Samsung 12 Pro Max Gold")
            .build();

    private static Product product3 = Product.builder()
            .id(3L)
            .name("Samsung Galaxy Ultra 10 White")
            .build();

    public static List<Product> getProduct() {
        return List.of(product1,product2,product3);
    }

    public static List<ProductImportHistory> getProductImportHistory() {
        ProductImportHistory history1 = ProductImportHistory
                .builder()
                .product(product1)
                .importUnit(1)
                .pricePerUnit(BigDecimal.valueOf(1500))
                .importDate(LocalDateTime.of(2024,12,30,8,30))
                .build();

        ProductImportHistory history2 = ProductImportHistory
                .builder()
                .product(product2)
                .importUnit(2)
                .pricePerUnit(BigDecimal.valueOf(1200))
                .importDate(LocalDateTime.of(2024,12,31,12,30))
                .build();

        ProductImportHistory history3 = ProductImportHistory
                .builder()
                .product(product3)
                .importUnit(1)
                .pricePerUnit(BigDecimal.valueOf(1360))
                .importDate(LocalDateTime.of(2024,12,25,5,30))
                .build();
        return List.of(history1,history2,history3);
    }
}
