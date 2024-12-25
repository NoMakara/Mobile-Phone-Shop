package com.project.mobile_phone_shop.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductImportDto {
    private Long product_id;
    private Integer importUnit;
    private BigDecimal importPrice;
    private LocalDateTime importDate;
}
