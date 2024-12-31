package com.project.mobile_phone_shop.Filter;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductImportHistoryFilter {
    private LocalDate startDate;
    private LocalDate endDate;
}
