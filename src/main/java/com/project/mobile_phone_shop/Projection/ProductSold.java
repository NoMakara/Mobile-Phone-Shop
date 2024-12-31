package com.project.mobile_phone_shop.Projection;

import java.math.BigDecimal;

public interface ProductSold {
    Long getProductId();
    String getProductName();
    Integer getUnit();
    BigDecimal getTotalAmount();

}
