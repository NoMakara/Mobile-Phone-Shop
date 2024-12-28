package com.project.mobile_phone_shop.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDto {
    private List<ProductSoldDto> products;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime saleDate;
}
